/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Morgan.Augustsson
 */
@Entity
@Table(name = "transaktioner")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Transaktioner.findAll", query = "SELECT t FROM Transaktioner t")
    , @NamedQuery(name = "Transaktioner.findByDatum", query = "SELECT t FROM Transaktioner t WHERE t.datum = :datum")
    , @NamedQuery(name = "Transaktioner.findByPersonal", query = "SELECT t FROM Transaktioner t WHERE t.personal = :personal")
    , @NamedQuery(name = "Transaktioner.findByTyp", query = "SELECT t FROM Transaktioner t WHERE t.typ = :typ")
    , @NamedQuery(name = "Transaktioner.findBySumma", query = "SELECT t FROM Transaktioner t WHERE t.summa = :summa")})
public class Transaktioner implements Serializable {

    private static final long serialVersionUID = 1L;
    @Size(max = 72)
    @Column(name = "datum")
    @Id
    private String datum;
    @Size(max = 101)
    @Column(name = "personal")
    private String personal;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "typ")
    private String typ;
    @Size(max = 25)
    @Column(name = "summa")
    private String summa;

    public Transaktioner() {
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public String getPersonal() {
        return personal;
    }

    public void setPersonal(String personal) {
        this.personal = personal;
    }

    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }

    public String getSumma() {
        return summa;
    }

    public void setSumma(String summa) {
        this.summa = summa;
    }
    
}
