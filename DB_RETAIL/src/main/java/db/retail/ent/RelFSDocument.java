package db.retail.ent;

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
 * @author Dobri
 */
@Entity
@Table(name = "Rel_FS_Document")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RelFSDocument.findAll", query = "SELECT r FROM RelFSDocument r"),
    @NamedQuery(name = "RelFSDocument.findByIdfsd", query = "SELECT r FROM RelFSDocument r WHERE r.idfsd = :idfsd"),
    @NamedQuery(name = "RelFSDocument.findByDocumentDate", query = "SELECT r FROM RelFSDocument r WHERE r.documentDate = :documentDate"),
    @NamedQuery(name = "RelFSDocument.findByDefaultDocument", query = "SELECT r FROM RelFSDocument r WHERE r.defaultDocument = :defaultDocument"),
    @NamedQuery(name = "RelFSDocument.findByPriority", query = "SELECT r FROM RelFSDocument r WHERE r.priority = :priority")})
public class RelFSDocument implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDFSD")
    private Integer idfsd;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DocumentDate")
    private Date documentDate;
    @Column(name = "DefaultDocument")
    private Boolean defaultDocument;
    @Column(name = "Priority")
    private Integer priority;
    @JoinColumn(name = "FK_IDD", referencedColumnName = "IDD")
    @ManyToOne
    private Document fkIdd;
    @JoinColumn(name = "FK_IDFS", referencedColumnName = "IDFS")
    @ManyToOne
    private FS fkIdfs;

    public RelFSDocument() {
    }

    public RelFSDocument(Date documentDate, Boolean defaultDocument, Integer priority, Document fkIdd, FS fkIdfs) {
        this.documentDate = documentDate;
        this.defaultDocument = defaultDocument;
        this.priority = priority;
        this.fkIdd = fkIdd;
        this.fkIdfs = fkIdfs;
    }

    public Integer getIdfsd() {
        return idfsd;
    }

    public void setIdfsd(Integer idfsd) {
        this.idfsd = idfsd;
    }

    public Date getDocumentDate() {
        return documentDate;
    }

    public void setDocumentDate(Date documentDate) {
        this.documentDate = documentDate;
    }

    public Boolean getDefaultDocument() {
        return defaultDocument;
    }

    public void setDefaultDocument(Boolean defaultDocument) {
        this.defaultDocument = defaultDocument;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Document getFkIdd() {
        return fkIdd;
    }

    public void setFkIdd(Document fkIdd) {
        this.fkIdd = fkIdd;
    }

    public FS getFkIdfs() {
        return fkIdfs;
    }

    public void setFkIdfs(FS fkIdfs) {
        this.fkIdfs = fkIdfs;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idfsd != null ? idfsd.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RelFSDocument)) {
            return false;
        }
        RelFSDocument other = (RelFSDocument) object;
        return !((this.idfsd == null && other.idfsd != null) || (this.idfsd != null && !this.idfsd.equals(other.idfsd)));
    }

    @Override
    public String toString() {
        return getFkIdfs() + ", "
                + getFkIdd() + ", "
                + getDocumentDate();
    }

}
