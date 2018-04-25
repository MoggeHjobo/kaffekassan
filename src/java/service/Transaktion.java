/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Morgan.Augustsson
 */
@Entity
@Table(name = "transaktion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Transaktion.findAll", query = "SELECT t FROM Transaktion t")
    , @NamedQuery(name = "Transaktion.findByTranskatiionsnummer", query = "SELECT t FROM Transaktion t WHERE t.transkatiionsnummer = :transkatiionsnummer")
    , @NamedQuery(name = "Transaktion.findByDatum", query = "SELECT t FROM Transaktion t WHERE t.datum = :datum")
    , @NamedQuery(name = "Transaktion.findBySumma", query = "SELECT t FROM Transaktion t WHERE t.summa = :summa")})
public class Transaktion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "transkatiionsnummer")
    private Integer transkatiionsnummer;
    @Column(name = "datum")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datum;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "summa")
    private Double summa;
    @JoinColumn(name = "personal", referencedColumnName = "personalnummer")
    @ManyToOne(optional = false)
    private Personal personal;
    @JoinColumn(name = "konto", referencedColumnName = "kontonummer")
    @ManyToOne(optional = false)
    private Konto konto;

    public Transaktion() {
    }

    public Transaktion(Integer transkatiionsnummer) {
        this.transkatiionsnummer = transkatiionsnummer;
    }

    public Integer getTranskatiionsnummer() {
        return transkatiionsnummer;
    }

    public void setTranskatiionsnummer(Integer transkatiionsnummer) {
        this.transkatiionsnummer = transkatiionsnummer;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public Double getSumma() {
        return summa;
    }

    public void setSumma(Double summa) {
        this.summa = summa;
    }

    public Personal getPersonal() {
        return personal;
    }

    public void setPersonal(Personal personal) {
        this.personal = personal;
    }

    public Konto getKonto() {
        return konto;
    }

    public void setKonto(Konto konto) {
        this.konto = konto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (transkatiionsnummer != null ? transkatiionsnummer.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Transaktion)) {
            return false;
        }
        Transaktion other = (Transaktion) object;
        if ((this.transkatiionsnummer == null && other.transkatiionsnummer != null) || (this.transkatiionsnummer != null && !this.transkatiionsnummer.equals(other.transkatiionsnummer))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "service.Transaktion[ transkatiionsnummer=" + transkatiionsnummer + " ]";
    }
    
}
