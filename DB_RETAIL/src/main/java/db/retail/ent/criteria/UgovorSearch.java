package db.retail.ent.criteria;

import db.retail.ent.FS;
import db.retail.ent.Partner;
import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Dobri
 */
public class UgovorSearch {

    private FS fs;
    private Partner p;
    private Date dateFrom;
    private Date dateTo;

    public UgovorSearch(FS fs, Partner p) {
        this.fs = fs;
        this.p = p;
    }

    public UgovorSearch(FS fs, Partner p, Date dateFrom, Date dateTo) {
        this(fs, p);

        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    public FS getFs() {
        return fs;
    }

    public void setFs(FS fs) {
        this.fs = fs;
    }

    public Partner getP() {
        return p;
    }

    public void setP(Partner p) {
        this.p = p;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

}
