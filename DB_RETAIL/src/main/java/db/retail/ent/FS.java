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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Dobri
 */
@Entity
@Table(name = "FS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FS.findAll", query = "SELECT f FROM FS f"),
    @NamedQuery(name = "FS.findByIdfs", query = "SELECT f FROM FS f WHERE f.idfs = :idfs"),
    @NamedQuery(name = "FS.findByNaziv", query = "SELECT f FROM FS f WHERE f.naziv = :naziv"),
    @NamedQuery(name = "FS.findByCode", query = "SELECT f FROM FS f WHERE f.code = :code"),
    @NamedQuery(name = "FS.findByModel", query = "SELECT f FROM FS f WHERE f.model = :model"),
    @NamedQuery(name = "FS.findByCocaModel", query = "SELECT f FROM FS f WHERE f.cocaModel = :cocaModel")})
public class FS implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDFS")
    private Integer idfs;
    @NotNull
    @Column(name = "Naziv")
    private String naziv;
    @NotNull
    @Column(name = "Code")
    private String code;
    @Column(name = "Model")
    private String model;
    @Column(name = "Coca_Model")
    private Boolean cocaModel;
    @OneToMany(mappedBy = "fkIdfs")
    private List<KljucRaspodele> kljucRaspodeleList;
    @OneToMany(mappedBy = "fkIdfs")
    private List<Ugovor> ugovorList;
    @OneToMany(mappedBy = "fkIdfs")
    private List<CompositeSellReport> compositeSellReportList;

    public FS() {
    }

    public FS(Integer idfs) {
        this.idfs = idfs;
    }

    public Integer getIdfs() {
        return idfs;
    }

    public void setIdfs(Integer idfs) {
        this.idfs = idfs;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Boolean getCocaModel() {
        return cocaModel;
    }

    public void setCocaModel(Boolean cocaModel) {
        this.cocaModel = cocaModel;
    }

    @XmlTransient
    public List<KljucRaspodele> getKljucRaspodeleList() {
        return kljucRaspodeleList;
    }

    public void setKljucRaspodeleList(List<KljucRaspodele> kljucRaspodeleList) {
        this.kljucRaspodeleList = kljucRaspodeleList;
    }

    @XmlTransient
    public List<Ugovor> getUgovorList() {
        return ugovorList;
    }

    public void setUgovorList(List<Ugovor> ugovorList) {
        this.ugovorList = ugovorList;
    }

    @XmlTransient
    public List<CompositeSellReport> getCompositeSellReportList() {
        return compositeSellReportList;
    }

    public void setCompositeSellReportList(List<CompositeSellReport> compositeSellReportList) {
        this.compositeSellReportList = compositeSellReportList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idfs != null ? idfs.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FS)) {
            return false;
        }
        FS other = (FS) object;
        if ((this.idfs == null && other.idfs != null) || (this.idfs != null && !this.idfs.equals(other.idfs))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getNaziv() + ", " + getCode();
    }

}
