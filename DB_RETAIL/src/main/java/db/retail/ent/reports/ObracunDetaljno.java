/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.retail.ent.reports;

public class ObracunDetaljno {

    //<editor-fold defaultstate="collapsed" desc="polja">
    private Integer idfs;
    private String fsName;
    private String fsCode;
    private String reportName;
    private Integer rbrReport;
    private String volType;
    private Double prodato;
    private int dan;
    private int mesec;
    private int godina;
    //</editor-fold>

    public ObracunDetaljno(Integer idfs, String fsName, String fsCode, String reportName, Integer rbrReport, String volType, Double prodato, int dan, int mesec, int godina) {
        this.idfs = idfs;
        this.fsName = fsName;
        this.fsCode = fsCode;
        this.reportName = reportName;
        this.rbrReport = rbrReport;
        this.volType = volType;
        this.prodato = prodato;
        this.dan = dan;
        this.mesec = mesec;
        this.godina = godina;
    }

    //<editor-fold defaultstate="collapsed" desc="getters/setters">
    public Integer getIdfs() {
        return idfs;
    }

    public void setIdfs(Integer idfs) {
        this.idfs = idfs;
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

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public Integer getRbrReport() {
        return rbrReport;
    }

    public void setRbrReport(Integer rbrReport) {
        this.rbrReport = rbrReport;
    }

    public String getVolType() {
        return volType;
    }

    public void setVolType(String volType) {
        this.volType = volType;
    }

    public Double getProdato() {
        return prodato;
    }

    public void setProdato(Double prodato) {
        this.prodato = prodato;
    }

    public int getDan() {
        return dan;
    }

    public void setDan(int dan) {
        this.dan = dan;
    }

    public int getMesec() {
        return mesec;
    }

    public void setMesec(int mesec) {
        this.mesec = mesec;
    }

    public int getGodina() {
        return godina;
    }

    public void setGodina(int godina) {
        this.godina = godina;
    }
    //</editor-fold>

    @Override
    public String toString() {
        return "[" + idfs + "] ["
                + fsName + "] ["
                + fsCode + "] ["
                + reportName + "] ["
                + rbrReport + "] ["
                + prodato + "] ["
                + dan + "] ["
                + mesec + "] ["
                + godina + "]";
    }

}
