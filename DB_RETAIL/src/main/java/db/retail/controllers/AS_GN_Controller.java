package db.retail.controllers;

import db.retail.DBHandler_RETAIL;
import db.retail.ent.GrupniNaziv;
import db.retail.interfaces.IAdvancedSearchController;
import java.util.List;

/**
 *
 * @author Dobri
 */
public class AS_GN_Controller implements IAdvancedSearchController<GrupniNaziv, String> {

    private static DBHandler_RETAIL dbh;

    public AS_GN_Controller(DBHandler_RETAIL dbh) {
        AS_GN_Controller.dbh = dbh;
    }

    @Override
    public List<GrupniNaziv> getAll() {
        return dbh.getAll_GN();
    }

    @Override
    public List<GrupniNaziv> get(String criteria) {
        return dbh.getByName_GN(criteria);
    }

    @Override
    public GrupniNaziv getByID(String criteria) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
