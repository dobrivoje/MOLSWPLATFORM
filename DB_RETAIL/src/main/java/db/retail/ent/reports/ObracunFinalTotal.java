/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.retail.ent.reports;

public class ObracunFinalTotal {

    //<editor-fold defaultstate="collapsed" desc="polja">
    private Integer idfs;
    private String fsName;
    private String fsCode;
    private String reportName;
    private Integer rbrReport;
    private Double prodato;
    private Double plan;
    private Double ostvarenje;
    //</editor-fold>

    public ObracunFinalTotal(Integer idfs, String fsName, String fsCode, String reportName, Integer rbrReport, Double prodato, Double plan, Double ostvarenje) {
        this.idfs = idfs;
        this.fsName = fsName;
        this.fsCode = fsCode;
        this.reportName = reportName;
        this.rbrReport = rbrReport;
        this.prodato = prodato;
        this.plan = plan;
        this.ostvarenje = ostvarenje;
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

    public Double getProdato() {
        return prodato;
    }

    public void setProdato(Double prodato) {
        this.prodato = prodato;
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
    //</editor-fold>

    @Override
    public String toString() {
        return "[" + idfs + "] ["
                + fsName + "] ["
                + fsCode + "] ["
                + reportName + "] ["
                + rbrReport + "] ["
                + prodato + "] ["
                + plan + "] ["
                + ostvarenje + "]";
    }

    public String getCalcToString() {
        return "[" + fsName + "] ["
                + fsCode + "] ["
                + reportName + "] ["
                + rbrReport + "] ["
                + prodato + "] ["
                + plan + "] ["
                + ostvarenje + "]";
    }

}
