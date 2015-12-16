package db.retail.controllers;

import db.retail.DBHandler_RETAIL;
import db.retail.ent.Partner;
import db.retail.ent.criteria.PUSearch;
import db.retail.interfaces.IAdvancedSearchController;
import java.util.List;

/**
 *
 * @author Dobri
 */
public class AS_PARTN_Controller implements IAdvancedSearchController<Partner, PUSearch> {

    private static DBHandler_RETAIL dbh;

    public AS_PARTN_Controller(DBHandler_RETAIL dbh) {
        AS_PARTN_Controller.dbh = dbh;
    }

    @Override
    public List<Partner> getAll() {
        return dbh.getAll_Partner();
    }

    @Override
    public List<Partner> get(PUSearch criteria) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Partner getByID(PUSearch criteria) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
