package db.retail.controllers;

import db.retail.DBHandler_RETAIL;
import db.retail.ent.Ugovor;
import db.retail.ent.criteria.UgovorSearch;
import db.retail.interfaces.IController;
import java.util.List;

/**
 *
 * @author Dobri
 */
public class AS_UGOVOR_Controller implements IController<Ugovor, UgovorSearch> {

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
        return dbh.get_FS_Ugovori(criteria.getFs());
    }

    @Override
    public Ugovor getByID(UgovorSearch criteria) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void update(Ugovor item) throws Exception {
        dbh.updateUgovor(item);
    }

    @Override
    public void add(Ugovor newItem) throws Exception {
        dbh.addNew_Ugovor(newItem);
    }

}
