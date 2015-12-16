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
 * @author Dobri
 */
@Entity
@Table(name = "GRUPNI_NAZIV")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GrupniNaziv.findAll", query = "SELECT g FROM GrupniNaziv g"),
    @NamedQuery(name = "GrupniNaziv.findByIdgn", query = "SELECT g FROM GrupniNaziv g WHERE g.idgn = :idgn"),
    @NamedQuery(name = "GrupniNaziv.findByGNaziv", query = "SELECT g FROM GrupniNaziv g WHERE g.gNaziv = :gNaziv")})
public class GrupniNaziv implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDGN")
    private Integer idgn;
    @Column(name = "GNaziv")
    private String gNaziv;
    @OneToMany(mappedBy = "fkIdgn")
    private List<Mapping> mappingList;

    public GrupniNaziv() {
    }

    public GrupniNaziv(Integer idgn) {
        this.idgn = idgn;
    }

    public Integer getIdgn() {
        return idgn;
    }

    public void setIdgn(Integer idgn) {
        this.idgn = idgn;
    }

    public String getGNaziv() {
        try {
            return gNaziv;
        } catch (Exception e) {
            return "";
        }
    }

    public void setGNaziv(String gNaziv) {
        this.gNaziv = gNaziv;
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
        hash += (idgn != null ? idgn.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof GrupniNaziv)) {
            return false;
        }
        GrupniNaziv other = (GrupniNaziv) object;
        return !((this.idgn == null && other.idgn != null) || (this.idgn != null && !this.idgn.equals(other.idgn)));
    }

    @Override
    public String toString() {
        try {
            return getGNaziv();
        } catch (Exception e) {
            return "";
        }
    }

}
