/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.ent.HSE;

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
 * @author dprtenjak
 */
@Entity
@Table(name = "FuelStation")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FuelStation.findAll", query = "SELECT f FROM FuelStation f"),
    @NamedQuery(name = "FuelStation.findByIdfs", query = "SELECT f FROM FuelStation f WHERE f.idfs = :idfs"),
    @NamedQuery(name = "FuelStation.findByName", query = "SELECT f FROM FuelStation f WHERE f.name LIKE :name"),
    @NamedQuery(name = "FuelStation.findById", query = "SELECT f FROM FuelStation f WHERE f.id = :id")})
public class FuelStation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDFS")
    private Long idfs;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 6)
    @Column(name = "ID")
    private String id;
    @OneToMany(mappedBy = "FK_FuelStation")
    private List<WorkPlan> workPlanList;

    public FuelStation() {
    }

    public FuelStation(Long idfs) {
        this.idfs = idfs;
    }

    public FuelStation(Long idfs, String name, String id) {
        this.idfs = idfs;
        this.name = name;
        this.id = id;
    }

    public Long getIdfs() {
        return idfs;
    }

    public void setIdfs(Long idfs) {
        this.idfs = idfs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @XmlTransient
    public List<WorkPlan> getWorkPlanList() {
        return workPlanList;
    }

    public void setWorkPlanList(List<WorkPlan> workPlanList) {
        this.workPlanList = workPlanList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idfs != null ? idfs.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FuelStation)) {
            return false;
        }
        FuelStation other = (FuelStation) object;
        return !((this.idfs == null && other.idfs != null) || (this.idfs != null && !this.idfs.equals(other.idfs)));
    }

    @Override
    public String toString() {
        return name + ", " + id;
    }

}
