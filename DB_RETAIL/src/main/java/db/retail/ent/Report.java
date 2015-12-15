/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.retail.ent;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author root
 */
@Entity
@Table(name = "REPORT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Report.findAll", query = "SELECT r FROM Report r"),
    @NamedQuery(name = "Report.findByIdr", query = "SELECT r FROM Report r WHERE r.idr = :idr"),
    @NamedQuery(name = "Report.findByNaziv", query = "SELECT r FROM Report r WHERE r.naziv = :naziv"),
    @NamedQuery(name = "Report.findByActive", query = "SELECT r FROM Report r WHERE r.active = :active"),
    @NamedQuery(name = "Report.findByDatumOD", query = "SELECT r FROM Report r WHERE r.datumOD = :datumOD"),
    @NamedQuery(name = "Report.findByDatumDO", query = "SELECT r FROM Report r WHERE r.datumDO = :datumDO")})
public class Report implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDR")
    private Integer idr;
    @Column(name = "Naziv")
    private String naziv;
    @Column(name = "Active")
    private Boolean active;
    @Column(name = "DatumOD")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datumOD;
    @Column(name = "DatumDO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datumDO;
    @OneToMany(mappedBy = "fkIdr")
    private List<ReportDetails> reportDetailsList;

    public Report() {
    }

    public Report(Integer idr) {
        this.idr = idr;
    }

    public Integer getIdr() {
        return idr;
    }

    public void setIdr(Integer idr) {
        this.idr = idr;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Date getDatumOD() {
        return datumOD;
    }

    public String getDatumOD1() {
        try {
            return new SimpleDateFormat("dd.MM.yyyy").format(datumOD);
        } catch (Exception e) {
            return "";
        }
    }

    public void setDatumOD(Date datumOD) {
        this.datumOD = datumOD;
    }

    public Date getDatumDO() {
        return datumDO;
    }

    public String getDatumDO1() {
        try {
            return new SimpleDateFormat("dd.MM.yyyy").format(datumDO);
        } catch (Exception e) {
            return "";
        }
    }

    public void setDatumDO(Date datumDO) {
        this.datumDO = datumDO;
    }

    @XmlTransient
    public List<ReportDetails> getReportDetailsList() {
        return reportDetailsList;
    }

    public void setReportDetailsList(List<ReportDetails> reportDetailsList) {
        this.reportDetailsList = reportDetailsList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idr != null ? idr.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Report)) {
            return false;
        }
        Report other = (Report) object;
        if ((this.idr == null && other.idr != null) || (this.idr != null && !this.idr.equals(other.idr))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "db.retail.Report[ idr=" + idr + " ]";
    }

}
