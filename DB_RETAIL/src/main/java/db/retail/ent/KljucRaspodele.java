/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.retail.ent;

import java.io.Serializable;
import java.text.SimpleDateFormat;
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
 * @author root
 */
@Entity
@Table(name = "KLJUC_RASPODELE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "KljucRaspodele.findAll", query = "SELECT k FROM KljucRaspodele k"),
    @NamedQuery(name = "KljucRaspodele.findByIdkr", query = "SELECT k FROM KljucRaspodele k WHERE k.idkr = :idkr"),
    @NamedQuery(name = "KljucRaspodele.findByDatumOD", query = "SELECT k FROM KljucRaspodele k WHERE k.datumOD = :datumOD"),
    @NamedQuery(name = "KljucRaspodele.findByDatumDO", query = "SELECT k FROM KljucRaspodele k WHERE k.datumDO = :datumDO"),
    @NamedQuery(name = "KljucRaspodele.findByKoef", query = "SELECT k FROM KljucRaspodele k WHERE k.koef = :koef"),
    @NamedQuery(name = "KljucRaspodele.findByIznos", query = "SELECT k FROM KljucRaspodele k WHERE k.iznos = :iznos")})
public class KljucRaspodele implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDKR")
    private Integer idkr;
    @Column(name = "DatumOD")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datumOD;
    @Column(name = "DatumDO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datumDO;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Koef")
    private Double koef;
    @Column(name = "Iznos")
    private Double iznos;
    @JoinColumn(name = "FK_IDFS", referencedColumnName = "IDFS")
    @ManyToOne
    private FS fkIdfs;
    @JoinColumn(name = "FK_IDKFS", referencedColumnName = "IDKFS")
    @ManyToOne
    private Koef fkIdkfs;

    public KljucRaspodele() {
    }

    public KljucRaspodele(Integer idkr) {
        this.idkr = idkr;
    }

    public Integer getIdkr() {
        return idkr;
    }

    public void setIdkr(Integer idkr) {
        this.idkr = idkr;
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

    public Double getKoef() {
        return koef;
    }

    public void setKoef(Double koef) {
        this.koef = koef;
    }

    public Double getIznos() {
        return iznos;
    }

    public void setIznos(Double iznos) {
        this.iznos = iznos;
    }

    public FS getFkIdfs() {
        return fkIdfs;
    }

    public void setFkIdfs(FS fkIdfs) {
        this.fkIdfs = fkIdfs;
    }

    public Koef getFkIdkfs() {
        return fkIdkfs;
    }

    public void setFkIdkfs(Koef fkIdkfs) {
        this.fkIdkfs = fkIdkfs;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idkr != null ? idkr.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof KljucRaspodele)) {
            return false;
        }
        KljucRaspodele other = (KljucRaspodele) object;
        if ((this.idkr == null && other.idkr != null) || (this.idkr != null && !this.idkr.equals(other.idkr))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "db.retail.KljucRaspodele[ idkr=" + idkr + " ]";
    }

}
