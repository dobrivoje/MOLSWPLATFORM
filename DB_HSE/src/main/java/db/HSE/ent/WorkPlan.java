/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.HSE.ent;

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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Dobri
 */
@Entity
@Table(name = "WorkPlan")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "WorkPlan.findAll", query = "SELECT w FROM WorkPlan w"),
    @NamedQuery(name = "WorkPlan.findAllSortByDateDesc", query = "SELECT w FROM WorkPlan w ORDER BY w.startDate DESC"),
    @NamedQuery(name = "WorkPlan.getByFS", query = "SELECT w FROM WorkPlan w WHERE w.FK_FuelStation = :FSID"),

    @NamedQuery(name = "WorkPlan.getFinishedWPByFS",
            query = "SELECT COUNT(w) FROM WorkPlan w WHERE w.FK_FuelStation = :FSID AND w.finished = :finished"),

    @NamedQuery(name = "WorkPlan.findAllFinishedSortByDateAsc", query = "SELECT w FROM WorkPlan w WHERE w.finished = :finished ORDER BY w.startDate ASC"),
    @NamedQuery(name = "WorkPlan.findByIdwp", query = "SELECT w FROM WorkPlan w WHERE w.idwp = :idwp"),
    @NamedQuery(name = "WorkPlan.findByDate", query = "SELECT w FROM WorkPlan w WHERE w.startDate = :date"),
    @NamedQuery(name = "WorkPlan.findByContractor", query = "SELECT w FROM WorkPlan w WHERE w.contractor = :contractor"),
    @NamedQuery(name = "WorkPlan.findBySubContractor", query = "SELECT w FROM WorkPlan w WHERE w.subContractor = :subContractor"),
    @NamedQuery(name = "WorkPlan.findByWorktype", query = "SELECT w FROM WorkPlan w WHERE w.worktype = :worktype"),
    @NamedQuery(name = "WorkPlan.findByTermin", query = "SELECT w FROM WorkPlan w WHERE w.termin = :termin"),
    @NamedQuery(name = "WorkPlan.findByDuration", query = "SELECT w FROM WorkPlan w WHERE w.duration = :duration"),
    @NamedQuery(name = "WorkPlan.findByFinished", query = "SELECT w FROM WorkPlan w WHERE w.finished = :finished")})
public class WorkPlan implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDWP")
    private Long idwp;
    @Basic(optional = false)
    @Column(name = "StartDate")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date startDate;
    @Column(name = "EndDate")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date endDate;
    @Size(max = 50)
    @Basic(optional = false)
    @Column(name = "Contractor")
    private String contractor;
    @Size(max = 50)
    @Column(name = "SubContractor")
    private String subContractor;
    @Size(max = 50)
    @Basic(optional = false)
    @Column(name = "Worktype")
    private String worktype;
    @Size(max = 50)
    @Column(name = "Termin")
    private String termin;
    @Size(max = 10)
    @Basic(optional = false)
    @Column(name = "Duration")
    private String duration;
    @Column(name = "Comment")
    private String comment;
    @Column(name = "Finished")
    private boolean finished;
    @JoinColumn(name = "FK_IDFS", referencedColumnName = "IDFS")
    @ManyToOne
    private FuelStation FK_FuelStation;

    public WorkPlan() {
    }

    public WorkPlan(Long idwp) {
        this.idwp = idwp;
    }

    public Long getIdwp() {
        return idwp;
    }

    public void setIdwp(Long idwp) {
        this.idwp = idwp;
    }

    public Date getStartDate() {
        return startDate;
    }

    public String getStartDate1() {
        try {
            return new SimpleDateFormat("dd.MM.yyyy").format(startDate);
        } catch (Exception e) {
            return "";
        }
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getEndDate1() {
        try {
            return new SimpleDateFormat("d.M.yyyy").format(endDate);
        } catch (Exception e) {
            return "";
        }
    }

    public String getContractor() {
        return contractor;
    }

    public void setContractor(String contractor) {
        this.contractor = contractor;
    }

    public String getSubContractor() {
        return subContractor;
    }

    public void setSubContractor(String subContractor) {
        this.subContractor = subContractor;
    }

    public String getWorktype() {
        return worktype;
    }

    public void setWorktype(String worktype) {
        this.worktype = worktype;
    }

    public String getTermin() {
        return termin;
    }

    public void setTermin(String termin) {
        this.termin = termin;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public boolean getFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public FuelStation getFK_FuelStation() {
        return FK_FuelStation;
    }

    public void setFK_FuelStation(FuelStation FK_FuelStation) {
        this.FK_FuelStation = FK_FuelStation;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idwp != null ? idwp.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof WorkPlan)) {
            return false;
        }
        WorkPlan other = (WorkPlan) object;
        return !((this.idwp == null && other.idwp != null) || (this.idwp != null && !this.idwp.equals(other.idwp)));
    }

    @Override
    public String toString() {
        return "db.ent.WorkPlan[ idwp=" + idwp + " ]";
    }

}
