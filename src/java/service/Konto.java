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
@Table(name = "konto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Konto.findAll", query = "SELECT k FROM Konto k")
    , @NamedQuery(name = "Konto.findByKontonummer", query = "SELECT k FROM Konto k WHERE k.kontonummer = :kontonummer")
    , @NamedQuery(name = "Konto.findByKonto", query = "SELECT k FROM Konto k WHERE k.konto = :konto")})
public class Konto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "kontonummer")
    private Integer kontonummer;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "konto")
    private String konto;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "konto")
    private Collection<Transaktion> transaktionCollection;

    public Konto() {
    }

    public Konto(Integer kontonummer) {
        this.kontonummer = kontonummer;
    }

    public Konto(Integer kontonummer, String konto) {
        this.kontonummer = kontonummer;
        this.konto = konto;
    }

    public Integer getKontonummer() {
        return kontonummer;
    }

    public void setKontonummer(Integer kontonummer) {
        this.kontonummer = kontonummer;
    }

    public String getKonto() {
        return konto;
    }

    public void setKonto(String konto) {
        this.konto = konto;
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
        hash += (kontonummer != null ? kontonummer.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Konto)) {
            return false;
        }
        Konto other = (Konto) object;
        if ((this.kontonummer == null && other.kontonummer != null) || (this.kontonummer != null && !this.kontonummer.equals(other.kontonummer))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "service.Konto[ kontonummer=" + kontonummer + " ]";
    }
    
}
