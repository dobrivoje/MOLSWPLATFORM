/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.retail.ent.criteria;

public class NameIDLogicSearch {

    private String byName;
    private boolean byLogic;
    private int byID;

    public NameIDLogicSearch(String byName, boolean byLogic, int byID) {
        this.byName = byName;
        this.byLogic = byLogic;
        this.byID = byID;
    }

    public String getByName() {
        return byName;
    }

    public void setByName(String byName) {
        this.byName = byName;
    }

    public boolean isByLogic() {
        return byLogic;
    }

    public void setByLogic(boolean byLogic) {
        this.byLogic = byLogic;
    }

    public int getByID() {
        return byID;
    }

    public void setByID(int byID) {
        this.byID = byID;
    }

}
