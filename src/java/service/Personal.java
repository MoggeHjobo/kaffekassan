/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Morgan.Augustsson
 */
@Entity
@Table(name = "personal")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Personal.findAll", query = "SELECT p FROM Personal p")
    , @NamedQuery(name = "Personal.findByPersonalnummer", query = "SELECT p FROM Personal p WHERE p.personalnummer = :personalnummer")
    , @NamedQuery(name = "Personal.findByFnamn", query = "SELECT p FROM Personal p WHERE p.fnamn = :fnamn")
    , @NamedQuery(name = "Personal.findByEnamn", query = "SELECT p FROM Personal p WHERE p.enamn = :enamn")})
public class Personal implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "personalnummer")
    private Integer personalnummer;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "fnamn")
    private String fnamn;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "enamn")
    private String enamn;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personal")
    private Collection<Transaktion> transaktionCollection;

    public Personal() {
    }

    public Personal(Integer personalnummer) {
        this.personalnummer = personalnummer;
    }

    public Personal(Integer personalnummer, String fnamn, String enamn) {
        this.personalnummer = personalnummer;
        this.fnamn = fnamn;
        this.enamn = enamn;
    }

    public Integer getPersonalnummer() {
        return personalnummer;
    }

    public void setPersonalnummer(Integer personalnummer) {
        this.personalnummer = personalnummer;
    }

    public String getFnamn() {
        return fnamn;
    }

    public void setFnamn(String fnamn) {
        this.fnamn = fnamn;
    }

    public String getEnamn() {
        return enamn;
    }

    public void setEnamn(String enamn) {
        this.enamn = enamn;
    }

    @XmlTransient
    public Collection<Transaktion> getTransaktionCollection() {
        return transaktionCollection;
    }

    public void setTransaktionCollection(Collection<Transaktion> transaktionCollection) {
        this.transaktionCollection = transaktionCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (personalnummer != null ? personalnummer.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Personal)) {
            return false;
        }
        Personal other = (Personal) object;
        if ((this.personalnummer == null && other.personalnummer != null) || (this.personalnummer != null && !this.personalnummer.equals(other.personalnummer))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "service.Personal[ personalnummer=" + personalnummer + " ]";
    }
    
}
