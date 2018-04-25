/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classses;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import service.Konto;
import service.Transaktion;

/**
 *
 * @author Morgan.Augustsson
 */
@WebServlet(name = "ceateKonto", urlPatterns = {"/ceateKonto"})
public class ceateKonto extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ceateKonto</title>"); 
            Konto k = new Konto();
            k.setKontonummer(100);
            k.setKonto("Vetekrans");
            new KontoFacadeREST_JerseyClient().create_JSON(k);
            /*
            Transaktion tr = new Transaktion();
            tr.setPersonal(personal);
            */
            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ceateKonto at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    static class KontoFacadeREST_JerseyClient {

        private WebTarget webTarget;
        private Client client;
        private static final String BASE_URI = "http://localhost:8080/service/webresources";

        public KontoFacadeREST_JerseyClient() {
            client = javax.ws.rs.client.ClientBuilder.newClient();
            webTarget = client.target(BASE_URI).path("service.konto");
        }

        public String countREST() throws ClientErrorException {
            WebTarget resource = webTarget;
            resource = resource.path("count");
            return resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(String.class);
        }

        public void edit_XML(Object requestEntity, String id) throws ClientErrorException {
            webTarget.path(java.text.MessageFormat.format("{0}", new Object[]{id})).request(javax.ws.rs.core.MediaType.APPLICATION_XML).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_XML));
        }

        public void edit_JSON(Object requestEntity, String id) throws ClientErrorException {
            webTarget.path(java.text.MessageFormat.format("{0}", new Object[]{id})).request(javax.ws.rs.core.MediaType.APPLICATION_JSON).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON));
        }

        public <T> T find(Class<T> responseType, String id) throws ClientErrorException {
            WebTarget resource = webTarget;
            resource = resource.path(java.text.MessageFormat.format("{0}", new Object[]{id}));
            return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
        }

        public <T> T findRange(Class<T> responseType, String from, String to) throws ClientErrorException {
            WebTarget resource = webTarget;
            resource = resource.path(java.text.MessageFormat.format("{0}/{1}", new Object[]{from, to}));
            return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
        }

        public void create_XML(Object requestEntity) throws ClientErrorException {
            webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_XML).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_XML));
        }

        public void create_JSON(Object requestEntity) throws ClientErrorException {
            webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON));
        }

        public <T> T findAll(Class<T> responseType) throws ClientErrorException {
            WebTarget resource = webTarget;
            return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
        }

        public void remove(String id) throws ClientErrorException {
            webTarget.path(java.text.MessageFormat.format("{0}", new Object[]{id})).request().delete();
        }

        public void close() {
            client.close();
        }
    }

}
