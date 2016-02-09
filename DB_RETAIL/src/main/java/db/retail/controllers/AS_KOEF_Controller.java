package db.retail.controllers;

import db.retail.DBHandler_RETAIL;
import db.retail.ent.Koef;
import db.retail.interfaces.IAdvancedSearchController;
import java.util.List;

/**
 *
 * @author Dobri
 */
public class AS_KOEF_Controller implements IAdvancedSearchController<Koef, Integer> {

    private static DBHandler_RETAIL dbh;

    public AS_KOEF_Controller(DBHandler_RETAIL dbh) {
        AS_KOEF_Controller.dbh = dbh;
    }

    @Override
    public List<Koef> getAll(boolean initSubList) {
        return dbh.getALL_KOEF();
    }

    @Override
    public List<Koef> get(Integer ID) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Koef getByID(Integer ID) {
        return dbh.getByID_KOEF(ID);
    }

}
