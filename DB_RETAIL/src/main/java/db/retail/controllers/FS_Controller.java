package db.retail.controllers;

import db.retail.DBHandler_RETAIL;
import db.retail.ent.FS;
import db.retail.interfaces.ICRUDController;

/**
 *
 * @author root
 */
public class FS_Controller implements ICRUDController<FS> {

    private static DBHandler_RETAIL dbh;

    public FS_Controller(DBHandler_RETAIL dbh) {
        FS_Controller.dbh = dbh;
    }

    @Override
    public void add(FS newItem) throws Exception {
        dbh.addNew_FS(newItem);
    }

    @Override
    public void update(FS item) throws Exception {
        dbh.updateFS(item);
    }

}
