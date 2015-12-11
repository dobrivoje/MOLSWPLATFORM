/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.retail.beans.criteria;

public class OS_Search {

    private DateIntervalSearch dis;
    private String fsCode;

    public OS_Search(DateIntervalSearch dis, String fsCode) {
        this.dis = dis;
        this.fsCode = fsCode;
    }

    public DateIntervalSearch getDis() {
        return dis;
    }

    public void setDis(DateIntervalSearch dis) {
        this.dis = dis;
    }

    public String getDateFrom() {
        return dis.getDateFrom();
    }

    public String getDateTo() {
        return dis.getDateTo();
    }

    public void setDateFrom(String dateFrom) {
        this.dis.setDateFrom(dateFrom);
    }

    public void setDateTo(String dateTo) {
        this.dis.setDateTo(dateTo);
    }

    public String getFsCode() {
        return fsCode;
    }

    public void setFsCode(String fsCode) {
        this.fsCode = fsCode;
    }

}
