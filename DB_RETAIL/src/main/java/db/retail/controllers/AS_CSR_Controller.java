/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.retail.controllers;

import db.retail.ent.CompositeSellReport;
import db.retail.DBHandler_RETAIL;
import db.retail.ent.criteria.CSRSearch;
import db.retail.interfaces.IAdvancedSearchController;
import java.util.List;

/**
 *
 * @author Dobri
 */
public class AS_CSR_Controller implements IAdvancedSearchController<CompositeSellReport, CSRSearch> {

    private static DBHandler_RETAIL dbh;

    public AS_CSR_Controller(DBHandler_RETAIL dbh) {
        AS_CSR_Controller.dbh = dbh;
    }

    @Override
    public List<CompositeSellReport> getAll(boolean initSubList) {
        return dbh.getAll_CSR();
    }

    @Override
    public List<CompositeSellReport> get(CSRSearch criteria) {
        return dbh.getBy_FSID(criteria.getFs());
    }

    @Override
    public CompositeSellReport getByID(CSRSearch criteria) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
