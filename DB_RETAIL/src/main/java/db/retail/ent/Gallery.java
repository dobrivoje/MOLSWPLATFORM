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
 * @author root
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Gallery.findAll", query = "SELECT g FROM Gallery g"),
    @NamedQuery(name = "Gallery.findByIdg", query = "SELECT g FROM Gallery g WHERE g.idg = :idg"),
    @NamedQuery(name = "Gallery.findByName", query = "SELECT g FROM Gallery g WHERE g.name = :name")})
public class Gallery implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDG")
    private Integer idg;
    @Basic(optional = false)
    @Column(name = "Name")
    private String name;
    @Basic(optional = false)
    @Column(name = "StoreLocation")
    private String storeLocation;
    @OneToMany(mappedBy = "fkIdg")
    private List<Document> documentList;

    public Gallery() {
    }

    public Gallery(String name, String storeLocation) {
        this.name = name;
        this.storeLocation = storeLocation;
    }

    public Gallery(String name, String storeLocation, List<Document> documentList) {
        this(name, storeLocation);
        this.documentList = documentList;
    }

    public Gallery(Integer idg, String name, String storeLocation) {
        this.idg = idg;
        this.name = name;
        this.storeLocation = storeLocation;
    }

    public Integer getIdg() {
        return idg;
    }

    public void setIdg(Integer idg) {
        this.idg = idg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStoreLocation() {
        return storeLocation;
    }

    public void setStoreLocation(String storeLocation) {
        this.storeLocation = storeLocation;
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
        hash += (idg != null ? idg.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Gallery)) {
            return false;
        }
        Gallery other = (Gallery) object;
        if ((this.idg == null && other.idg != null) || (this.idg != null && !this.idg.equals(other.idg))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getName()
                + " [" + getStoreLocation() + "]";
    }

}
