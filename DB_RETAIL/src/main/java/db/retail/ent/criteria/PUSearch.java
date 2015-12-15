package db.retail.ent.criteria;

import db.retail.ent.FS;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * Partner - ugovor kriterijum pretrage
 * 
 * @author Dobri
 */
public class PUSearch {

    private String partner;
    private String brugovora;
    private FS fs;

    public PUSearch(String partner, String brugovora, FS fs) {
        this.partner = partner;
        this.brugovora = brugovora;
        this.fs = fs;
    }

    public String getPartner() {
        return partner;
    }

    public void setPartner(String partner) {
        this.partner = partner;
    }

    public String getBrugovora() {
        return brugovora;
    }

    public void setBrugovora(String brugovora) {
        this.brugovora = brugovora;
    }

    public FS getFs() {
        return fs;
    }

    public void setFs(FS fs) {
        this.fs = fs;
    }

}
