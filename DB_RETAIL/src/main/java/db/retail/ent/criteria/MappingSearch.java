/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.retail.ent.criteria;

import db.retail.ent.Kategorija;

public class MappingSearch {

    private String name;
    private String code;
    private Kategorija kategorija;

    public MappingSearch(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public MappingSearch(String name, String code, Kategorija kategorija) {
        this(name, code);
        this.kategorija = kategorija;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Kategorija getKategorija() {
        return kategorija;
    }

    public void setKategorija(Kategorija kategorija) {
        this.kategorija = kategorija;
    }

}
