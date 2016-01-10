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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "Report_Details")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReportDetails.findAll", query = "SELECT r FROM ReportDetails r"),

    @NamedQuery(name = "ReportDetails.ActiveReportDetails", query = "SELECT r FROM ReportDetails r WHERE r.aktivno = :aktivno"),

    @NamedQuery(name = "ReportDetails.findByIdrd", query = "SELECT r FROM ReportDetails r WHERE r.idrd = :idrd"),
    @NamedQuery(name = "ReportDetails.findByNaziv", query = "SELECT r FROM ReportDetails r WHERE r.naziv = :naziv"),
    @NamedQuery(name = "ReportDetails.findByRBr", query = "SELECT r FROM ReportDetails r WHERE r.rBr = :rBr"),
    @NamedQuery(name = "ReportDetails.findByAktivno", query = "SELECT r FROM ReportDetails r WHERE r.aktivno = :aktivno")})
public class ReportDetails implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDRD")
    private Integer idrd;
    @Column(name = "Naziv")
    private String naziv;
    @Column(name = "RBr")
    private Short rBr;
    @Column(name = "Aktivno")
    private Boolean aktivno;
    @JoinColumn(name = "FK_IDR", referencedColumnName = "IDR")
    @ManyToOne
    private Report fkIdr;
    @OneToMany(mappedBy = "fkIdrd")
    private List<Koef> koefList;
    @OneToMany(mappedBy = "fkIdr")
    private List<Mapping> mappingList;

    public ReportDetails() {
    }

    public ReportDetails(Integer idrd) {
        this.idrd = idrd;
    }

    public Integer getIdrd() {
        return idrd;
    }

    public void setIdrd(Integer idrd) {
        this.idrd = idrd;
    }

    public String getNaziv() {
        try {
            return naziv;
        } catch (Exception e) {
            return "";
        }
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Short getRBr() {
        return rBr;
    }

    public void setRBr(Short rBr) {
        this.rBr = rBr;
    }

    public Boolean getAktivno() {
        return aktivno;
    }

    public void setAktivno(Boolean aktivno) {
        this.aktivno = aktivno;
    }

    public Report getFkIdr() {
        return fkIdr;
    }

    public void setFkIdr(Report fkIdr) {
        this.fkIdr = fkIdr;
    }

    @XmlTransient
    public List<Koef> getKoefList() {
        return koefList;
    }

    public void setKoefList(List<Koef> koefList) {
        this.koefList = koefList;
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
        hash += (idrd != null ? idrd.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReportDetails)) {
            return false;
        }
        ReportDetails other = (ReportDetails) object;
        if ((this.idrd == null && other.idrd != null) || (this.idrd != null && !this.idrd.equals(other.idrd))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        try {
            return getNaziv();
        } catch (Exception e) {
            return "";
        }
    }

}
