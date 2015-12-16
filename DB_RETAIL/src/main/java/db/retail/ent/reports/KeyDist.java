package db.retail.ent.reports;

import db.retail.ent.FS;
import db.retail.ent.GrupaKoef;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Dobri
 */
public class KeyDist {

    private FS fs;
    private GrupaKoef gk;
    private Date mappingDatumOD;
    private Date mappingDatumDO;
    private double koef;
    private double iznos;

    private int dayFrom;
    private int monthFrom;
    private int yearFrom;

    private int dayTo;
    private int monthTo;
    private int yearTo;

    //<editor-fold defaultstate="collapsed" desc="Konstruktor, getter/setter">
    public KeyDist() {
    }

    public KeyDist(FS fs, GrupaKoef gk, Date mappingDatumOD, Date mappingDatumDO, double koef, double iznos) {
        this.fs = fs;
        this.gk = gk;
        this.mappingDatumOD = mappingDatumOD;
        this.mappingDatumDO = mappingDatumDO;
        this.koef = koef;
        this.iznos = iznos;
    }

    public KeyDist(FS fs, GrupaKoef gk, Date mappingDatumOD, Date mappingDatumDO, double koef, double iznos, int dayFrom, int monthFrom, int yearFrom, int dayTo, int monthTo, int yearTo) {
        this(fs, gk, mappingDatumOD, mappingDatumDO, koef, iznos);

        this.dayFrom = dayFrom;
        this.monthFrom = monthFrom;
        this.yearFrom = yearFrom;

        this.dayTo = dayTo;
        this.monthTo = monthTo;
        this.yearTo = yearTo;
    }

    public FS getFs() {
        return fs;
    }

    public void setFs(FS fs) {
        this.fs = fs;
    }

    public GrupaKoef getGk() {
        return gk;
    }

    public void setGk(GrupaKoef gk) {
        this.gk = gk;
    }

    public Date getMappingDatumOD() {
        return mappingDatumOD;
    }

    public void setMappingDatumOD(Date mappingDatumOD) {
        this.mappingDatumOD = mappingDatumOD;
    }

    public Date getMappingDatumDO() {
        return mappingDatumDO;
    }

    public void setMappingDatumDO(Date mappingDatumDO) {
        this.mappingDatumDO = mappingDatumDO;
    }

    public double getKoef() {
        return koef;
    }

    public void setKoef(double koef) {
        this.koef = koef;
    }

    public double getIznos() {
        return iznos;
    }

    public void setIznos(double iznos) {
        this.iznos = iznos;
    }

    public int getMonthFrom() {
        return monthFrom;
    }

    public void setMonthFrom(int monthFrom) {
        this.monthFrom = monthFrom;
    }

    public int getYearFrom() {
        return yearFrom;
    }

    public void setYearFrom(int yearFrom) {
        this.yearFrom = yearFrom;
    }

    public int getMonthTo() {
        return monthTo;
    }

    public void setMonthTo(int monthTo) {
        this.monthTo = monthTo;
    }

    public int getYearTo() {
        return yearTo;
    }

    public void setYearTo(int yearTo) {
        this.yearTo = yearTo;
    }

    public int getDayFrom() {
        return dayFrom;
    }

    public void setDayFrom(int dayFrom) {
        this.dayFrom = dayFrom;
    }

    public int getDayTo() {
        return dayTo;
    }

    public void setDayTo(int dayTo) {
        this.dayTo = dayTo;
    }
    //</editor-fold>

    @Override
    public String toString() {
        try {
            return fs + ", "
                    + gk + ", "
                    + new SimpleDateFormat("d.M.yyyy").format(mappingDatumOD) + " - "
                    + new SimpleDateFormat("d.M.yyyy").format(mappingDatumDO) + ", ["
                    + Double.toString(koef) + "] ["
                    + Double.toString(iznos) + "] ["
                    + Integer.toString(getDayFrom()) + "-"
                    + Integer.toString(getMonthFrom()) + "-"
                    + Integer.toString(getYearFrom()) + "-"
                    + Integer.toString(getDayTo()) + "-"
                    + Integer.toString(getMonthTo()) + "-"
                    + Integer.toString(getYearTo()) + "]";
        } catch (Exception e) {
            return "";
        }
    }

}
