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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "MAPPING")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mapping.findAll", query = "SELECT m FROM Mapping m"),
    @NamedQuery(name = "Mapping.findByIdm", query = "SELECT m FROM Mapping m WHERE m.idm = :idm"),
    @NamedQuery(name = "Mapping.findByNaziv", query = "SELECT m FROM Mapping m WHERE m.naziv = :naziv"),
    @NamedQuery(name = "Mapping.findByCode", query = "SELECT m FROM Mapping m WHERE m.code = :code"),
    @NamedQuery(name = "Mapping.findByAktivan", query = "SELECT m FROM Mapping m WHERE m.aktivan = :aktivan"),
    @NamedQuery(name = "Mapping.findByReport", query = "SELECT m FROM Mapping m WHERE m.report = :report"),
    @NamedQuery(name = "Mapping.findByDatumUnosa", query = "SELECT m FROM Mapping m WHERE m.datumUnosa = :datumUnosa")})
public class Mapping implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDM")
    private Integer idm;
    @Column(name = "Naziv")
    private String naziv;
    @Column(name = "Code")
    private String code;
    @Column(name = "Aktivan")
    private Boolean aktivan;
    @Column(name = "Report")
    private Boolean report;
    @Column(name = "Datum_Unosa")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datumUnosa;
    @JoinColumn(name = "FK_IDGN", referencedColumnName = "IDGN")
    @ManyToOne
    private GrupniNaziv fkIdgn;
    @JoinColumn(name = "FK_IDK", referencedColumnName = "IDK")
    @ManyToOne
    private Kategorija fkIdk;
    @JoinColumn(name = "FK_IDKL", referencedColumnName = "IDKL")
    @ManyToOne
    private Klasifikacija fkIdkl;
    @JoinColumn(name = "FK_IDR", referencedColumnName = "IDRD")
    @ManyToOne
    private ReportDetails fkIdr;
    @OneToMany(mappedBy = "fkIdm")
    private List<CompositeSellReport> compositeSellReportList;

    public Mapping() {
    }

    public Mapping(String naziv, String code, Boolean aktivan, Boolean report, Date datumUnosa, GrupniNaziv fkIdgn, Kategorija fkIdk, Klasifikacija fkIdkl, ReportDetails fkIdr, List<CompositeSellReport> compositeSellReportList) {
        this.naziv = naziv;
        this.code = code;
        this.aktivan = aktivan;
        this.report = report;
        this.datumUnosa = datumUnosa;
        this.fkIdgn = fkIdgn;
        this.fkIdk = fkIdk;
        this.fkIdkl = fkIdkl;
        this.fkIdr = fkIdr;
        this.compositeSellReportList = compositeSellReportList;
    }

    public Mapping(Integer idm) {
        this.idm = idm;
    }

    public Integer getIdm() {
        return idm;
    }

    public void setIdm(Integer idm) {
        this.idm = idm;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getCode() {
        try {
            return code;
        } catch (Exception e) {
            return "";
        }
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Boolean getAktivan() {
        return aktivan;
    }

    public void setAktivan(Boolean aktivan) {
        this.aktivan = aktivan;
    }

    public Boolean getReport() {
        return report;
    }

    public void setReport(Boolean report) {
        this.report = report;
    }

    public Date getDatumUnosa() {
        return datumUnosa;
    }

    public String getDatumUnosa1() {
        try {
            return new SimpleDateFormat("dd.MM.yyyy").format(getDatumUnosa());
        } catch (Exception e) {
            return "";
        }
    }

    public void setDatumUnosa(Date datumUnosa) {
        this.datumUnosa = datumUnosa;
    }

    public GrupniNaziv getFkIdgn() {
        return fkIdgn;
    }

    public String getIzvSpecif1() {
        try {
            return fkIdgn.getGNaziv();
        } catch (Exception e) {
            return "";
        }
    }

    public void setFkIdgn(GrupniNaziv fkIdgn) {
        this.fkIdgn = fkIdgn;
    }

    public Kategorija getFkIdk() {
        return fkIdk;
    }

    public String getKategorija1() {
        try {
            return fkIdk.getNaziv();
        } catch (Exception e) {
            return "";
        }
    }

    public void setFkIdk(Kategorija fkIdk) {
        this.fkIdk = fkIdk;
    }

    public Klasifikacija getFkIdkl() {
        return fkIdkl;
    }

    public void setFkIdkl(Klasifikacija fkIdkl) {
        this.fkIdkl = fkIdkl;
    }

    public ReportDetails getFkIdr() {
        return fkIdr;
    }

    public String getIzvObracun1() {
        try {
            return getFkIdr().getNaziv();
        } catch (Exception e) {
            return "";
        }
    }

    public void setFkIdr(ReportDetails fkIdr) {
        this.fkIdr = fkIdr;
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
        hash += (idm != null ? idm.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mapping)) {
            return false;
        }
        Mapping other = (Mapping) object;
        if ((this.idm == null && other.idm != null) || (this.idm != null && !this.idm.equals(other.idm))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "db.retail.Mapping[ idm=" + idm + " ]";
    }

}
