/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.retail.controllers;

import db.retail.DBHandler_RETAIL;
import db.retail.beans.criteria.OS_Search;
import db.retail.beans.reports.ObracunFinal;
import db.retail.interfaces.IAdvancedSearchController;
import java.util.List;

/**
 *
 * @author root
 */
public class FinalObracun_Controller implements IAdvancedSearchController<ObracunFinal, OS_Search> {

    private static DBHandler_RETAIL dbh;

    public FinalObracun_Controller(DBHandler_RETAIL dbh) {
        FinalObracun_Controller.dbh = dbh;
    }

    @Override
    public List<ObracunFinal> getAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<ObracunFinal> get(OS_Search criteria) {
        return dbh.get_ObracunFinal(criteria.getDateFrom(), criteria.getDateTo(), criteria.getFsCode());
    }

    @Override
    public ObracunFinal getByID(OS_Search criteria) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
