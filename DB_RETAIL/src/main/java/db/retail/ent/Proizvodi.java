/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.retail.ent;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
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
 * @author DPrtenjak
 */
@Entity
@Table(name = "PROIZVODI")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Proizvodi.findAll", query = "SELECT p FROM Proizvodi p"),
    @NamedQuery(name = "Proizvodi.findByIDPR", query = "SELECT p FROM Proizvodi p WHERE p.idpr = :IDPR"),
    @NamedQuery(name = "Proizvodi.findByNaziv", query = "SELECT p FROM Proizvodi p WHERE p.naziv = :naziv")})
public class Proizvodi implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDPR")
    private Integer idpr;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "Naziv")
    private String naziv;
    @OneToMany(mappedBy = "FK_IDPR")
    private List<Kategorija> kategorijaList;

    public Proizvodi() {
    }

    public Proizvodi(Integer idt) {
        this.idpr = idt;
    }

    public Proizvodi(Integer idt, String naziv) {
        this.idpr = idt;
        this.naziv = naziv;
    }

    public Integer getIdpr() {
        return idpr;
    }

    public void setIdpr(Integer idpr) {
        this.idpr = idpr;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    @XmlTransient
    public List<Kategorija> getKategorijaList() {
        return kategorijaList;
    }

    public void setKategorijaList(List<Kategorija> kategorijaList) {
        this.kategorijaList = kategorijaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpr != null ? idpr.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Proizvodi)) {
            return false;
        }
        Proizvodi other = (Proizvodi) object;
        if ((this.idpr == null && other.idpr != null) || (this.idpr != null && !this.idpr.equals(other.idpr))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        try {
            return naziv;
        } catch (Exception e) {
            return "";
        }
    }

}
