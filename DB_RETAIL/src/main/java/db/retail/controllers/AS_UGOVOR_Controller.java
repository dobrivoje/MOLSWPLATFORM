package db.retail.controllers;

import db.retail.DBHandler_RETAIL;
import db.retail.ent.Ugovor;
import db.retail.ent.criteria.UgovorSearch;
import db.retail.interfaces.IAdvancedSearchController;
import java.util.List;

/**
 *
 * @author Dobri
 */
public class AS_UGOVOR_Controller implements IAdvancedSearchController<Ugovor, UgovorSearch> {

    private static DBHandler_RETAIL dbh;

    public AS_UGOVOR_Controller(DBHandler_RETAIL dbh) {
        AS_UGOVOR_Controller.dbh = dbh;
    }

    @Override
    public List<Ugovor> getAll() {
        return dbh.getAll_UGOVOR();
    }

    @Override
    public List<Ugovor> get(UgovorSearch criteria) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Ugovor getByID(UgovorSearch criteria) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
