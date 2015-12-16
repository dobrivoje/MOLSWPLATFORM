package db.retail.ent.criteria;

import db.retail.ent.FS;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Dobri
 */
public class CSRSearch {

    private FS fs;
    private String date;

    public CSRSearch(String date) {
        this.date = date;
    }

    public CSRSearch(FS fs) {
        this.fs = fs;
    }

    public CSRSearch(FS fs, String date) {
        this.fs = fs;
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public FS getFs() {
        return fs;
    }

    public void setFs(FS fs) {
        this.fs = fs;
    }

}
