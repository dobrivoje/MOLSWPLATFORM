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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Dobri
 */
@Entity
@Table(name = "KATEGORIJA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Kategorija.findAll", query = "SELECT k FROM Kategorija k"),
    @NamedQuery(name = "Kategorija.findByIdk", query = "SELECT k FROM Kategorija k WHERE k.idk = :idk"),
    @NamedQuery(name = "Kategorija.findByNaziv", query = "SELECT k FROM Kategorija k WHERE k.naziv = :naziv"),
    @NamedQuery(name = "Kategorija.findByVolType", query = "SELECT k FROM Kategorija k WHERE k.volType = :volType"),
    @NamedQuery(name = "Kategorija.findByFkIdt", query = "SELECT k FROM Kategorija k WHERE k.fkIdt = :fkIdt"),
    @NamedQuery(name = "Kategorija.findByAktivno", query = "SELECT k FROM Kategorija k WHERE k.aktivno = :aktivno"),
    @NamedQuery(name = "Kategorija.findByDatumOD", query = "SELECT k FROM Kategorija k WHERE k.datumOD = :datumOD"),
    @NamedQuery(name = "Kategorija.findByDatumDO", query = "SELECT k FROM Kategorija k WHERE k.datumDO = :datumDO")})
public class Kategorija implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDK")
    private Integer idk;
    @NotNull
    @Column(name = "Naziv")
    private String naziv;
    @Column(name = "Vol_Type")
    private String volType;
    @Column(name = "FK_IDT")
    private Integer fkIdt;
    @Column(name = "Aktivno")
    private Boolean aktivno;
    @Column(name = "DatumOD")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datumOD;
    @Column(name = "DatumDO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datumDO;
    @OneToMany(mappedBy = "fkIdk")
    private List<Mapping> mappingList;

    public Kategorija() {
    }

    public Kategorija(Integer idk) {
        this.idk = idk;
    }

    public Integer getIdk() {
        return idk;
    }

    public void setIdk(Integer idk) {
        this.idk = idk;
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

    public String getVolType() {
        return volType;
    }

    public void setVolType(String volType) {
        this.volType = volType;
    }

    public Integer getFkIdt() {
        return fkIdt;
    }

    public void setFkIdt(Integer fkIdt) {
        this.fkIdt = fkIdt;
    }

    public Boolean getAktivno() {
        return aktivno;
    }

    public void setAktivno(Boolean aktivno) {
        this.aktivno = aktivno;
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
    public List<Mapping> getMappingList() {
        return mappingList;
    }

    public void setMappingList(List<Mapping> mappingList) {
        this.mappingList = mappingList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idk != null ? idk.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Kategorija)) {
            return false;
        }
        Kategorija other = (Kategorija) object;
        return !((this.idk == null && other.idk != null) || (this.idk != null && !this.idk.equals(other.idk)));
    }

    @Override
    public String toString() {
        try {
            return getNaziv() + " [" + getVolType() + "]";
        } catch (Exception e) {
            return "";
        }
    }

}
