/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CustomBeans;

import db.ent.HSE.FuelStation;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author dprtenjak
 */
public class HSE_SysNotif_Bean {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    @Column(name = "fs")
    private FuelStation fs;
    @Column(name = "sumFinishedWorkPlans")
    private int sumFinishedWorkPlans;
    @Column(name = "total")
    private int total;

    public HSE_SysNotif_Bean() {
    }

    public HSE_SysNotif_Bean(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public FuelStation getFs() {
        return fs;
    }

    public void setFs(FuelStation fs) {
        this.fs = fs;
    }

    public int getSumFinishedWorkPlans() {
        return sumFinishedWorkPlans;
    }

    public void setSumFinishedWorkPlans(int sumFinishedWorkPlans) {
        this.sumFinishedWorkPlans = sumFinishedWorkPlans;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (fs != null ? fs.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HSE_SysNotif_Bean)) {
            return false;
        }

        HSE_SysNotif_Bean other = (HSE_SysNotif_Bean) object;
        return this == other;
    }

    @Override
    public String toString() {
        return fs.getName() + ", " + fs.getId();
    }
}
