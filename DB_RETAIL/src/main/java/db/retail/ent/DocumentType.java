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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Dobri
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DocumentType.findAll", query = "SELECT d FROM DocumentType d"),
    @NamedQuery(name = "DocumentType.findByIddt", query = "SELECT d FROM DocumentType d WHERE d.iddt = :iddt"),
    @NamedQuery(name = "DocumentType.findByDocType", query = "SELECT d FROM DocumentType d WHERE d.docType = :docType")})
public class DocumentType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDDT")
    private Integer iddt;
    @Basic(optional = false)
    @Column(name = "DocType")
    private String docType;
    @OneToMany(mappedBy = "fkIddt")
    private List<Document> documentList;

    public DocumentType() {
    }

    public DocumentType(String docType) {
        this.docType = docType;
    }

    public DocumentType(String docType, List<Document> documentList) {
        this.docType = docType;
        this.documentList = documentList;
    }

    public Integer getIddt() {
        return iddt;
    }

    public void setIddt(Integer iddt) {
        this.iddt = iddt;
    }

    public String getDocType() {
        return docType;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }

    @XmlTransient
    public List<Document> getDocumentList() {
        return documentList;
    }

    public void setDocumentList(List<Document> documentList) {
        this.documentList = documentList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iddt != null ? iddt.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DocumentType)) {
            return false;
        }
        DocumentType other = (DocumentType) object;
        return !((this.iddt == null && other.iddt != null) || (this.iddt != null && !this.iddt.equals(other.iddt)));
    }

    @Override
    public String toString() {
        return getDocType();
    }

}
