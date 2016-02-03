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
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Dobri
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Document.findAll", query = "SELECT d FROM Document d"),
    @NamedQuery(name = "Document.findByIdd", query = "SELECT d FROM Document d WHERE d.idd = :idd"),
    
    @NamedQuery(name = "Document.findByGallery", 
            query = "SELECT d FROM Document d WHERE d.fkIdg = :IDG"),
    
    @NamedQuery(name = "Document.findByDocType", 
            query = "SELECT d FROM Document d WHERE d.fkIddt = :IDDT"),
    
    @NamedQuery(name = "Document.findByName", query = "SELECT d FROM Document d WHERE d.name = :name"),
    @NamedQuery(name = "Document.findByDocLocation", query = "SELECT d FROM Document d WHERE d.docLocation = :docLocation"),
    @NamedQuery(name = "Document.findByUploadDate", query = "SELECT d FROM Document d WHERE d.uploadDate = :uploadDate")})
public class Document implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDD")
    private Integer idd;
    @Column(name = "Name")
    private String name;
    @Lob
    @Column(name = "DocData")
    private Serializable docData;
    @Column(name = "DocLocation")
    private String docLocation;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UploadDate")
    private Date uploadDate;
    @OneToMany(mappedBy = "fkIdd")
    private List<RelFSDocument> relFSDocumentList;
    @JoinColumn(name = "FK_IDDT", referencedColumnName = "IDDT")
    @ManyToOne
    private DocumentType fkIddt;
    @JoinColumn(name = "FK_IDG", referencedColumnName = "IDG")
    @ManyToOne
    private Gallery fkIdg;

    public Document() {
    }

    public Document(String name, Serializable docData, String docLocation, Date uploadDate, DocumentType fkIddt, Gallery fkIdg) {
        this.name = name;
        this.docData = docData;
        this.docLocation = docLocation;
        this.uploadDate = uploadDate;
        this.fkIddt = fkIddt;
        this.fkIdg = fkIdg;
    }

    public Document(String name, Serializable docData, String docLocation, Date uploadDate, List<RelFSDocument> relFSDocumentList, DocumentType fkIddt, Gallery fkIdg) {
        this(name, docData, docLocation, uploadDate, fkIddt, fkIdg);

        this.relFSDocumentList = relFSDocumentList;
    }

    public Integer getIdd() {
        return idd;
    }

    public void setIdd(Integer idd) {
        this.idd = idd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Serializable getDocData() {
        return docData;
    }

    public void setDocData(Serializable docData) {
        this.docData = docData;
    }

    public String getDocLocation() {
        return docLocation;
    }

    public void setDocLocation(String docLocation) {
        this.docLocation = docLocation;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    @XmlTransient
    public List<RelFSDocument> getRelFSDocumentList() {
        return relFSDocumentList;
    }

    public void setRelFSDocumentList(List<RelFSDocument> relFSDocumentList) {
        this.relFSDocumentList = relFSDocumentList;
    }

    public DocumentType getFkIddt() {
        return fkIddt;
    }

    public void setFkIddt(DocumentType fkIddt) {
        this.fkIddt = fkIddt;
    }

    public Gallery getFkIdg() {
        return fkIdg;
    }

    public void setFkIdg(Gallery fkIdg) {
        this.fkIdg = fkIdg;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idd != null ? idd.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Document)) {
            return false;
        }
        Document other = (Document) object;
        return !((this.idd == null && other.idd != null) || (this.idd != null && !this.idd.equals(other.idd)));
    }

    @Override
    public String toString() {
        return 
                getName() +", " +
                getDocLocation() +", " +
                getFkIddt().toString();
    }

}
