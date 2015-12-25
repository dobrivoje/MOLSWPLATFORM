/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.retail.ent.reports;

public class ObracunFinal {

    //<editor-fold defaultstate="collapsed" desc="polja">
    private Integer idfs;
    private String fsName;
    private String fsCode;
    private String reportName;
    private String volType;
    private Double prodato;
    private Double rKoef;
    private Double plan;
    private Double ostvarenje;
    // private Double ostvarenje1;
    private Boolean obavezan;
    private String koefNaziv;
    private Double total;
    private Integer idrd;
    private Integer rbrReport;
    private Integer rbrKoef;
    private String startObracuna;
    private String krajObracuna;
    private String partner;
    private String brUgovora;
    private String bu1;
    private String bu2;
    private String bu3;
    private Double fiksniIznos;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="konstruktor">
    public ObracunFinal() {
    }

    public ObracunFinal(Integer idfs, String fsName, String fsCode, String reportName, String volType, Double prodato, Double rKoef, Double plan, Double ostvarenje, /*Double ostvarenje1, */ Boolean obavezan, String koefNaziv, Double total, Integer idrd, Integer rbrReport, Integer rbrKoef, String startObracuna, String krajObracuna, String partner, String brUgovora, String bu1, String bu2, String bu3, Double fiksniIznos) {
        this.idfs = idfs;
        this.fsName = fsName;
        this.fsCode = fsCode;
        this.reportName = reportName;
        this.volType = volType;
        this.prodato = prodato;
        this.rKoef = rKoef;
        this.plan = plan;
        this.ostvarenje = ostvarenje;
        //this.ostvarenje1 = ostvarenje1;
        this.obavezan = obavezan;
        this.koefNaziv = koefNaziv;
        this.total = total;
        this.idrd = idrd;
        this.rbrReport = rbrReport;
        this.rbrKoef = rbrKoef;
        this.startObracuna = startObracuna;
        this.krajObracuna = krajObracuna;
        this.partner = partner;
        this.brUgovora = brUgovora;
        this.bu1 = bu1;
        this.bu2 = bu2;
        this.bu3 = bu3;
        this.fiksniIznos = fiksniIznos;
    }
    //</editor-fold>

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

    public Double getrKoef() {
        return rKoef;
    }

    public void setrKoef(Double rKoef) {
        this.rKoef = rKoef;
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

    /*
     public Double getOstvarenje1() {
     return ostvarenje1;
     }
    
     public void setOstvarenje1(Double ostvarenje1) {
     this.ostvarenje1 = ostvarenje1;
     }
     */
    public Boolean isObavezan() {
        return obavezan;
    }

    public void setObavezan(Boolean obavezan) {
        this.obavezan = obavezan;
    }

    public String getKoefNaziv() {
        return koefNaziv;
    }

    public void setKoefNaziv(String koefNaziv) {
        this.koefNaziv = koefNaziv;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Integer getIdrd() {
        return idrd;
    }

    public void setIdrd(Integer idrd) {
        this.idrd = idrd;
    }

    public Integer getRbrReport() {
        return rbrReport;
    }

    public void setRbrReport(Integer rbrReport) {
        this.rbrReport = rbrReport;
    }

    public Integer getRbrKoef() {
        return rbrKoef;
    }

    public void setRbrKoef(Integer rbrKoef) {
        this.rbrKoef = rbrKoef;
    }

    public String getStartObracuna() {
        return startObracuna;
    }

    public void setStartObracuna(String startObracuna) {
        this.startObracuna = startObracuna;
    }

    public String getKrajObracuna() {
        return krajObracuna;
    }

    public void setKrajObracuna(String krajObracuna) {
        this.krajObracuna = krajObracuna;
    }

    public String getPartner() {
        return partner;
    }

    public void setPartner(String partner) {
        this.partner = partner;
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

    public Double getFiksniIznos() {
        return fiksniIznos;
    }

    public void setFiksniIznos(Double fiksniIznos) {
        this.fiksniIznos = fiksniIznos;
    }
    //</editor-fold>

    @Override
    public String toString() {
        return "[" + idfs + "] ["
                + fsName + "] ["
                + fsCode + "] ["
                + reportName + "] ["
                + volType + "] ["
                + prodato + "] ["
                + rKoef + "] ["
                + plan + "] ["
                + ostvarenje + "] ["
                //+ ostvarenje1 + "] ["
                + obavezan + "] ["
                + koefNaziv + "] ["
                + total + "] ["
                + idrd + "] ["
                + rbrReport + "] ["
                + rbrKoef + "] ["
                + startObracuna + "] ["
                + krajObracuna + "] ["
                + partner + "] ["
                + brUgovora + "] ["
                + bu1 + "] ["
                + bu2 + "] ["
                + bu3 + "] ["
                + fiksniIznos + "]";
    }

    public String getCalcToString() {
        return "[" + fsName + "] ["
                + fsCode + "] ["
                + reportName + "] ["
                + prodato + "] ["
                + ostvarenje + "] ["
                //+ ostvarenje1 + "] ["
                + koefNaziv + "] ["
                + total + "] ["
                + partner + "] ["
                + brUgovora + "]";
    }

}
