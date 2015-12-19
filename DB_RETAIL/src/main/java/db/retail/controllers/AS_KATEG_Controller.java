package db.retail.controllers;

import db.retail.DBHandler_RETAIL;
import db.retail.ent.Kategorija;
import db.retail.interfaces.IAdvancedSearchController;
import java.util.List;

/**
 *
 * @author Dobri
 */
public class AS_KATEG_Controller implements IAdvancedSearchController<Kategorija, String> {

    private static DBHandler_RETAIL dbh;

    public AS_KATEG_Controller(DBHandler_RETAIL dbh) {
        AS_KATEG_Controller.dbh = dbh;
    }

    @Override
    public List<Kategorija> getAll(boolean initSubList) {
        return dbh.getAll_KATEG();
    }

    @Override
    public List<Kategorija> get(String criteria) {
        return dbh.getByName_KATEG(criteria);
    }

    @Override
    public Kategorija getByID(String criteria) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
