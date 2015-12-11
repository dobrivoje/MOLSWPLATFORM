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
 * @author root
 */
@Entity
@Table(name = "KOEF")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Koef.findAll", query = "SELECT k FROM Koef k"),
    @NamedQuery(name = "Koef.findByIdkfs", query = "SELECT k FROM Koef k WHERE k.idkfs = :idkfs"),
    @NamedQuery(name = "Koef.findByNaziv", query = "SELECT k FROM Koef k WHERE k.naziv = :naziv"),
    @NamedQuery(name = "Koef.findByKOd", query = "SELECT k FROM Koef k WHERE k.kOd = :kOd"),
    @NamedQuery(name = "Koef.findByKDo", query = "SELECT k FROM Koef k WHERE k.kDo = :kDo"),
    @NamedQuery(name = "Koef.findByObracunReport", query = "SELECT k FROM Koef k WHERE k.obracunReport = :obracunReport"),
    @NamedQuery(name = "Koef.findByObavezan", query = "SELECT k FROM Koef k WHERE k.obavezan = :obavezan")})
public class Koef implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDKFS")
    private Integer idkfs;
    @Column(name = "Naziv")
    private String naziv;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "K_OD")
    private Double kOd;
    @Column(name = "K_DO")
    private Double kDo;
    @Column(name = "ObracunReport")
    private Boolean obracunReport;
    @Column(name = "Obavezan")
    private Boolean obavezan;
    @OneToMany(mappedBy = "fkIdkfs")
    private List<KljucRaspodele> kljucRaspodeleList;
    @JoinColumn(name = "FK_IDGK", referencedColumnName = "IDGK")
    @ManyToOne
    private GrupaKoef fkIdgk;
    @JoinColumn(name = "FK_IDRD", referencedColumnName = "IDRD")
    @ManyToOne
    private ReportDetails fkIdrd;

    public Koef() {
    }

    public Koef(Integer idkfs) {
        this.idkfs = idkfs;
    }

    public Integer getIdkfs() {
        return idkfs;
    }

    public void setIdkfs(Integer idkfs) {
        this.idkfs = idkfs;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Double getKOd() {
        return kOd;
    }

    public void setKOd(Double kOd) {
        this.kOd = kOd;
    }

    public Double getKDo() {
        return kDo;
    }

    public void setKDo(Double kDo) {
        this.kDo = kDo;
    }

    public Boolean getObracunReport() {
        return obracunReport;
    }

    public void setObracunReport(Boolean obracunReport) {
        this.obracunReport = obracunReport;
    }

    public Boolean getObavezan() {
        return obavezan;
    }

    public void setObavezan(Boolean obavezan) {
        this.obavezan = obavezan;
    }

    @XmlTransient
    public List<KljucRaspodele> getKljucRaspodeleList() {
        return kljucRaspodeleList;
    }

    public void setKljucRaspodeleList(List<KljucRaspodele> kljucRaspodeleList) {
        this.kljucRaspodeleList = kljucRaspodeleList;
    }

    public GrupaKoef getFkIdgk() {
        return fkIdgk;
    }

    public void setFkIdgk(GrupaKoef fkIdgk) {
        this.fkIdgk = fkIdgk;
    }

    public ReportDetails getFkIdrd() {
        return fkIdrd;
    }

    public void setFkIdrd(ReportDetails fkIdrd) {
        this.fkIdrd = fkIdrd;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idkfs != null ? idkfs.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Koef)) {
            return false;
        }
        Koef other = (Koef) object;
        if ((this.idkfs == null && other.idkfs != null) || (this.idkfs != null && !this.idkfs.equals(other.idkfs))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "db.retail.Koef[ idkfs=" + idkfs + " ]";
    }
    
}
