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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Dobri
 */
@Entity
@Table(name = "PARTNER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Partner.findAll", query = "SELECT p FROM Partner p"),
    @NamedQuery(name = "Partner.findByIdp", query = "SELECT p FROM Partner p WHERE p.idp = :idp"),
    @NamedQuery(name = "Partner.findByNaziv", query = "SELECT p FROM Partner p WHERE p.naziv LIKE :naziv"),
    @NamedQuery(name = "Partner.findByKompanija", query = "SELECT p FROM Partner p WHERE p.kompanija = :kompanija")})
public class Partner implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDP")
    private Integer idp;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "Naziv")
    private String naziv;
    @Size(max = 100)
    @Column(name = "Kompanija")
    private String kompanija;
    @Size(max = 10)
    @Column(name = "MatBroj", unique = true)
    private String matBroj;

    @Size(max = 30)
    @Column(name = "Type")
    private String type;

    @Size(max = 30)
    @Column(name = "PartnerPhoneNo")
    private String partnerPhoneNo;

    @Size(max = 30)
    @Column(name = "BSPhoneNo")
    private String bsPhoneNo;

    @Size(max = 50)
    @Column(name = "MGREmail")
    private String mgrEmail;

    @Size(max = 50)
    @Column(name = "PrivateEmail")
    private String privateEmail;

    @Size(max = 10)
    @Column(name = "AccessDataDelivery")
    private String accessDataDelivery;

    @OneToMany(mappedBy = "fkIdp")
    private List<Ugovor> ugovorList;

    public Partner() {
    }

    public Partner(String naziv, String kompanija) {
        this.naziv = naziv;
        this.kompanija = kompanija;
    }

    public Partner(String naziv, String kompanija, String matBroj) {
        this(naziv, kompanija);
        this.matBroj = matBroj;
    }

    public Partner(String naziv, String kompanija, List<Ugovor> ugovorList) {
        this(naziv, kompanija);
        this.ugovorList = ugovorList;
    }

    public Partner(String naziv, String kompanija, String matBroj, List<Ugovor> ugovorList) {
        this(naziv, kompanija, ugovorList);
        this.matBroj = matBroj;
    }

    public Partner(String naziv, String kompanija, String matBroj, String type, String partnerPhoneNo, String bsPhoneNo, String mgrEmail, String privateEmail, String accessDataDelivery, List<Ugovor> ugovorList) {
        this(naziv, kompanija, matBroj, ugovorList);
        this.type = type;
        this.partnerPhoneNo = partnerPhoneNo;
        this.bsPhoneNo = bsPhoneNo;
        this.mgrEmail = mgrEmail;
        this.privateEmail = privateEmail;
        this.accessDataDelivery = accessDataDelivery;
    }

    public Partner(Integer idp) {
        this.idp = idp;
    }

    public Partner(Integer idp, String naziv) {
        this.idp = idp;
        this.naziv = naziv;
    }

    public Integer getIdp() {
        return idp;
    }

    public void setIdp(Integer idp) {
        this.idp = idp;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getKompanija() {
        return kompanija;
    }

    public void setKompanija(String kompanija) {
        this.kompanija = kompanija;
    }

    public String getMatBroj() {
        return matBroj;
    }

    public void setMatBroj(String matBroj) {
        this.matBroj = matBroj;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPartnerPhoneNo() {
        return partnerPhoneNo;
    }

    public void setPartnerPhoneNo(String partnerPhoneNo) {
        this.partnerPhoneNo = partnerPhoneNo;
    }

    public String getBsPhoneNo() {
        return bsPhoneNo;
    }

    public void setBsPhoneNo(String bsPhoneNo) {
        this.bsPhoneNo = bsPhoneNo;
    }

    public String getMgrEmail() {
        return mgrEmail;
    }

    public void setMgrEmail(String mgrEmail) {
        this.mgrEmail = mgrEmail;
    }

    public String getPrivateEmail() {
        return privateEmail;
    }

    public void setPrivateEmail(String privateEmail) {
        this.privateEmail = privateEmail;
    }

    public String getAccessDataDelivery() {
        return accessDataDelivery;
    }

    public void setAccessDataDelivery(String accessDataDelivery) {
        this.accessDataDelivery = accessDataDelivery;
    }

    @XmlTransient
    public List<Ugovor> getUgovorList() {
        return ugovorList;
    }

    public void setUgovorList(List<Ugovor> ugovorList) {
        this.ugovorList = ugovorList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idp != null ? idp.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Partner)) {
            return false;
        }
        Partner other = (Partner) object;
        return !((this.idp == null && other.idp != null) || (this.idp != null && !this.idp.equals(other.idp)));
    }

    @Override
    public String toString() {
        return getNaziv();
    }

}
