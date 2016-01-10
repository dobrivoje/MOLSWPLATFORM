/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.retail.ent.reports;

public class Obracun_FS_PerfDetaljno {

    //<editor-fold defaultstate="collapsed" desc="polja">
    private Integer idfs;
    private String fsName;
    private String fsCode;
    private String reportName;
    private Integer rbrReport;
    private String volType;
    private Integer idRd;
    private Double sumQty;
    private Double plan;
    private Double ostvarenje;
    private int dan;
    private int mesec;
    private int godina;
    //</editor-fold>

    public Obracun_FS_PerfDetaljno(Integer idfs, String fsName, String fsCode, String reportName, Integer rbrReport, String volType, Integer idRd, Double sumQty, Double plan, Double ostvarenje, int dan, int mesec, int godina) {
        this.idfs = idfs;
        this.fsName = fsName;
        this.fsCode = fsCode;
        this.reportName = reportName;
        this.rbrReport = rbrReport;
        this.volType = volType;
        this.idRd = idRd;
        this.sumQty = sumQty;
        this.plan = plan;
        this.ostvarenje = ostvarenje;
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

    public Integer getIdRd() {
        return idRd;
    }

    public void setIdRd(Integer idRd) {
        this.idRd = idRd;
    }

    public Double getSumQty() {
        return sumQty;
    }

    public void setSumQty(Double sumQty) {
        this.sumQty = sumQty;
    }

    public Double getPlan() {
        return plan;
    }

    public void setPlan(Double plan) {
        this.plan = plan;
    }

    public Double getOstvarenje() {
        return ostvarenje;
    }

    public void setOstvarenje(Double ostvarenje) {
        this.ostvarenje = ostvarenje;
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
                + volType + "] ["
                + idRd + "] ["
                + sumQty + "] ["
                + plan + "] ["
                + ostvarenje + "] ["
                + dan + "] ["
                + mesec + "] ["
                + godina + "]";
    }

}
