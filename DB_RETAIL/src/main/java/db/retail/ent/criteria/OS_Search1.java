/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.retail.ent.criteria;

import java.util.Date;

public class OS_Search1 {

    private DateIntervalSearch1 dis;
    private String fsCode;

    public OS_Search1(DateIntervalSearch1 dis, String fsCode) {
        this.dis = dis;
        this.fsCode = fsCode;
    }

    public DateIntervalSearch1 getDis() {
        return dis;
    }

    public void setDis(DateIntervalSearch1 dis) {
        this.dis = dis;
    }

    public Date getDateFrom() {
        return dis.getDateFrom();
    }

    public Date getDateTo() {
        return dis.getDateTo();
    }

    public void setDateFrom(Date dateFrom) {
        this.dis.setDateFrom(dateFrom);
    }

    public void setDateTo(Date dateTo) {
        this.dis.setDateTo(dateTo);
    }

    public String getFsCode() {
        return fsCode;
    }

    public void setFsCode(String fsCode) {
        this.fsCode = fsCode;
    }

}
