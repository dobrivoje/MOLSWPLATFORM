/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.retail.controllers;

import db.retail.DBHandler_RETAIL;
import db.retail.ent.criteria.OS_Search;
import db.retail.ent.reports.Specifikacija;
import db.retail.interfaces.IAdvancedSearchController;
import java.util.List;

/**
 *
 * @author Dobri
 */
public class Specifikacija_Controller implements IAdvancedSearchController<Specifikacija, OS_Search> {

    private static DBHandler_RETAIL dbh;

    public Specifikacija_Controller(DBHandler_RETAIL dbh) {
        Specifikacija_Controller.dbh = dbh;
    }

    @Override
    public List<Specifikacija> getAll(boolean initSubList) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Specifikacija> get(OS_Search criteria) {
        return dbh.get_Specifikacija(criteria.getDateFrom(), criteria.getDateTo(), criteria.getFsCode());
    }

    @Override
    public Specifikacija getByID(OS_Search criteria) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
