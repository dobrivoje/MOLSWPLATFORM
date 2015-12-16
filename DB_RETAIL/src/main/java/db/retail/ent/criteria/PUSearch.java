package db.retail.ent.criteria;

import db.retail.ent.FS;
import db.retail.ent.Partner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * Partner - Ugovor kriterijum pretrage
 *
 * @author Dobri
 */
public class PUSearch {

    private FS fs;
    private Partner partner;

    public PUSearch(FS fs, Partner partner) {
        this.fs = fs;
        this.partner = partner;
    }

    public FS getFs() {
        return fs;
    }

    public void setFs(FS fs) {
        this.fs = fs;
    }

    public Partner getPartner() {
        return partner;
    }

    public void setPartner(Partner partner) {
        this.partner = partner;
    }

}
