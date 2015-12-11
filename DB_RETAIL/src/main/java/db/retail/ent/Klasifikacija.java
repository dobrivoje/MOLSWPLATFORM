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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author root
 */
@Entity
@Table(name = "KLASIFIKACIJA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Klasifikacija.findAll", query = "SELECT k FROM Klasifikacija k"),
    @NamedQuery(name = "Klasifikacija.findByIdkl", query = "SELECT k FROM Klasifikacija k WHERE k.idkl = :idkl"),
    @NamedQuery(name = "Klasifikacija.findByNaziv", query = "SELECT k FROM Klasifikacija k WHERE k.naziv = :naziv")})
public class Klasifikacija implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDKL")
    private Integer idkl;
    @Column(name = "Naziv")
    private String naziv;
    @OneToMany(mappedBy = "fkIdkl")
    private List<Mapping> mappingList;

    public Klasifikacija() {
    }

    public Klasifikacija(Integer idkl) {
        this.idkl = idkl;
    }

    public Integer getIdkl() {
        return idkl;
    }

    public void setIdkl(Integer idkl) {
        this.idkl = idkl;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    @XmlTransient
    public List<Mapping> getMappingList() {
        return mappingList;
    }

    public void setMappingList(List<Mapping> mappingList) {
        this.mappingList = mappingList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idkl != null ? idkl.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Klasifikacija)) {
            return false;
        }
        Klasifikacija other = (Klasifikacija) object;
        if ((this.idkl == null && other.idkl != null) || (this.idkl != null && !this.idkl.equals(other.idkl))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "db.retail.Klasifikacija[ idkl=" + idkl + " ]";
    }
    
}
