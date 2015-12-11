/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.retail.ent;

import java.io.Serializable;
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
@Table(name = "GRUPA_KOEF")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GrupaKoef.findAll", query = "SELECT g FROM GrupaKoef g"),
    @NamedQuery(name = "GrupaKoef.findByIdgk", query = "SELECT g FROM GrupaKoef g WHERE g.idgk = :idgk"),
    @NamedQuery(name = "GrupaKoef.findByNaziv", query = "SELECT g FROM GrupaKoef g WHERE g.naziv = :naziv"),
    @NamedQuery(name = "GrupaKoef.findByDatumOD", query = "SELECT g FROM GrupaKoef g WHERE g.datumOD = :datumOD"),
    @NamedQuery(name = "GrupaKoef.findByDatumDO", query = "SELECT g FROM GrupaKoef g WHERE g.datumDO = :datumDO"),
    @NamedQuery(name = "GrupaKoef.findByRbr", query = "SELECT g FROM GrupaKoef g WHERE g.rbr = :rbr")})
public class GrupaKoef implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDGK")
    private Integer idgk;
    @Column(name = "Naziv")
    private String naziv;
    @Column(name = "DatumOD")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datumOD;
    @Column(name = "DatumDO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datumDO;
    @Column(name = "RBR")
    private Short rbr;
    @OneToMany(mappedBy = "fkIdgk")
    private List<Koef> koefList;

    public GrupaKoef() {
    }

    public GrupaKoef(Integer idgk) {
        this.idgk = idgk;
    }

    public Integer getIdgk() {
        return idgk;
    }

    public void setIdgk(Integer idgk) {
        this.idgk = idgk;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Date getDatumOD() {
        return datumOD;
    }

    public void setDatumOD(Date datumOD) {
        this.datumOD = datumOD;
    }

    public Date getDatumDO() {
        return datumDO;
    }

    public void setDatumDO(Date datumDO) {
        this.datumDO = datumDO;
    }

    public Short getRbr() {
        return rbr;
    }

    public void setRbr(Short rbr) {
        this.rbr = rbr;
    }

    @XmlTransient
    public List<Koef> getKoefList() {
        return koefList;
    }

    public void setKoefList(List<Koef> koefList) {
        this.koefList = koefList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idgk != null ? idgk.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GrupaKoef)) {
            return false;
        }
        GrupaKoef other = (GrupaKoef) object;
        if ((this.idgk == null && other.idgk != null) || (this.idgk != null && !this.idgk.equals(other.idgk))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "db.retail.GrupaKoef[ idgk=" + idgk + " ]";
    }
    
}
