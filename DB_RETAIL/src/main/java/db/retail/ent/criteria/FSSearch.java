/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.retail.ent.criteria;

public class FSSearch {

    private String name;
    private String code;
    private String model;
    private boolean cocaModel;

    public FSSearch(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public FSSearch(String name, String code, String model, boolean cocaModel) {
        this(name, code);

        this.model = model;
        this.cocaModel = cocaModel;
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

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public boolean isCocaModel() {
        return cocaModel;
    }

    public void setCocaModel(boolean cocaModel) {
        this.cocaModel = cocaModel;
    }

}
