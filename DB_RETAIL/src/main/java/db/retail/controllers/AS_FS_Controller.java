/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.retail.controllers;

import db.retail.DBHandler_RETAIL;
import db.retail.ent.FS;
import db.retail.beans.criteria.FSSearch;
import db.retail.interfaces.IAdvancedSearchController;
import java.util.List;

/**
 *
 * @author root
 */
public class AS_FS_Controller implements IAdvancedSearchController<FS, FSSearch> {

    private static DBHandler_RETAIL dbh;

    public AS_FS_Controller(DBHandler_RETAIL dbh) {
        AS_FS_Controller.dbh = dbh;
    }

    @Override
    public List<FS> getAll() {
        return dbh.getAll_FS();
    }

    @Override
    public List<FS> get(FSSearch criteria) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public FS getByID(FSSearch criteria) {
        return dbh.getByID_FS(criteria.getCode());
    }

}
