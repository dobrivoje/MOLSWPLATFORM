/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.retail.beans.reports;

public class Specifikacija {

    private String proizvod;
    private String reportName;
    private String mapping;
    private String fsName;
    private String fsCode;
    private double sumQty;
    private String volType;
    private double sumNetto;
    private String brUgovora;
    private String bu1;
    private String bu2;
    private String bu3;
    private String partner;

    public Specifikacija() {
    }

    public Specifikacija(String proizvod, String reportName, String mapping, String fsName, String fsCode, double sumQty, String volType, double sumNetto, String brUgovora, String bu1, String bu2, String bu3, String partner) {
        this.proizvod = proizvod;
        this.reportName = reportName;
        this.mapping = mapping;
        this.fsName = fsName;
        this.fsCode = fsCode;
        this.sumQty = sumQty;
        this.volType = volType;
        this.sumNetto = sumNetto;
        this.brUgovora = brUgovora;
        this.bu1 = bu1;
        this.bu2 = bu2;
        this.bu3 = bu3;
        this.partner = partner;
    }

    //<editor-fold defaultstate="collapsed" desc="getters/setters">
    public String getProizvod() {
        return proizvod;
    }

    public void setProizvod(String proizvod) {
        this.proizvod = proizvod;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public String getMapping() {
        return mapping;
    }

    public void setMapping(String mapping) {
        this.mapping = mapping;
    }

    public String getFsName() {
        return fsName;
    }

    public void setFsName(String fsName) {
        this.fsName = fsName;
    }

    public String getFsCode() {
        return fsCode;
    }

    public void setFsCode(String fsCode) {
        this.fsCode = fsCode;
    }

    public double getSumQty() {
        return sumQty;
    }

    public void setSumQty(double sumQty) {
        this.sumQty = sumQty;
    }

    public String getVolType() {
        return volType;
    }

    public void setVolType(String volType) {
        this.volType = volType;
    }

    public double getSumNetto() {
        return sumNetto;
    }

    public void setSumNetto(double sumNetto) {
        this.sumNetto = sumNetto;
    }

    public String getBrUgovora() {
        return brUgovora;
    }

    public void setBrUgovora(String brUgovora) {
        this.brUgovora = brUgovora;
    }

    public String getBu1() {
        return bu1;
    }

    public void setBu1(String bu1) {
        this.bu1 = bu1;
    }

    public String getBu2() {
        return bu2;
    }

    public void setBu2(String bu2) {
        this.bu2 = bu2;
    }

    public String getBu3() {
        return bu3;
    }

    public void setBu3(String bu3) {
        this.bu3 = bu3;
    }

    public String getPartner() {
        return partner;
    }

    public void setPartner(String partner) {
        this.partner = partner;
    }
    //</editor-fold>

    @Override
    public String toString() {
        return "[" + proizvod + "] ["
                + reportName + "] ["
                + mapping + "] ["
                + fsName + "] ["
                + fsCode + "] ["
                + sumQty + "] ["
                + volType + "] ["
                + sumNetto + "] ["
                + brUgovora + "] ["
                + bu1 + "] ["
                + bu2 + "] ["
                + bu3 + "] ["
                + partner + "]";
    }

}
