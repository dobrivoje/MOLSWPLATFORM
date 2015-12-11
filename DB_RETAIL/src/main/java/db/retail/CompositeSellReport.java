/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.retail;

import java.io.Serializable;
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
@Table(name = "COMPOSITE_SELL_REPORT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CompositeSellReport.findAll", query = "SELECT c FROM CompositeSellReport c"),
    @NamedQuery(name = "CompositeSellReport.findById", query = "SELECT c FROM CompositeSellReport c WHERE c.id = :id"),
    @NamedQuery(name = "CompositeSellReport.findByTimeCode", query = "SELECT c FROM CompositeSellReport c WHERE c.timeCode = :timeCode"),
    @NamedQuery(name = "CompositeSellReport.findByQuantity", query = "SELECT c FROM CompositeSellReport c WHERE c.quantity = :quantity"),
    @NamedQuery(name = "CompositeSellReport.findByCogs", query = "SELECT c FROM CompositeSellReport c WHERE c.cogs = :cogs"),
    @NamedQuery(name = "CompositeSellReport.findByRevenue", query = "SELECT c FROM CompositeSellReport c WHERE c.revenue = :revenue"),
    @NamedQuery(name = "CompositeSellReport.findByVat", query = "SELECT c FROM CompositeSellReport c WHERE c.vat = :vat"),
    @NamedQuery(name = "CompositeSellReport.findByNetto", query = "SELECT c FROM CompositeSellReport c WHERE c.netto = :netto"),
    @NamedQuery(name = "CompositeSellReport.findBySellValue", query = "SELECT c FROM CompositeSellReport c WHERE c.sellValue = :sellValue"),
    @NamedQuery(name = "CompositeSellReport.findByDatumImporta", query = "SELECT c FROM CompositeSellReport c WHERE c.datumImporta = :datumImporta")})
public class CompositeSellReport implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "TIME_CODE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeCode;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "QUANTITY")
    private Double quantity;
    @Column(name = "COGS")
    private Double cogs;
    @Column(name = "REVENUE")
    private Double revenue;
    @Column(name = "VAT")
    private Double vat;
    @Column(name = "NETTO")
    private Double netto;
    @Column(name = "SELL_VALUE")
    private Double sellValue;
    @Column(name = "DatumImporta")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datumImporta;
    @JoinColumn(name = "FK_IDFS", referencedColumnName = "IDFS")
    @ManyToOne
    private FS fkIdfs;
    @JoinColumn(name = "FK_IDM", referencedColumnName = "IDM")
    @ManyToOne
    private Mapping fkIdm;

    public CompositeSellReport() {
    }

    public CompositeSellReport(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getTimeCode() {
        return timeCode;
    }

    public void setTimeCode(Date timeCode) {
        this.timeCode = timeCode;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Double getCogs() {
        return cogs;
    }

    public void setCogs(Double cogs) {
        this.cogs = cogs;
    }

    public Double getRevenue() {
        return revenue;
    }

    public void setRevenue(Double revenue) {
        this.revenue = revenue;
    }

    public Double getVat() {
        return vat;
    }

    public void setVat(Double vat) {
        this.vat = vat;
    }

    public Double getNetto() {
        return netto;
    }

    public void setNetto(Double netto) {
        this.netto = netto;
    }

    public Double getSellValue() {
        return sellValue;
    }

    public void setSellValue(Double sellValue) {
        this.sellValue = sellValue;
    }

    public Date getDatumImporta() {
        return datumImporta;
    }

    public void setDatumImporta(Date datumImporta) {
        this.datumImporta = datumImporta;
    }

    public FS getFkIdfs() {
        return fkIdfs;
    }

    public void setFkIdfs(FS fkIdfs) {
        this.fkIdfs = fkIdfs;
    }

    public Mapping getFkIdm() {
        return fkIdm;
    }

    public void setFkIdm(Mapping fkIdm) {
        this.fkIdm = fkIdm;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CompositeSellReport)) {
            return false;
        }
        CompositeSellReport other = (CompositeSellReport) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "db.retail.CompositeSellReport[ id=" + id + " ]";
    }
    
}
