/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.retail.controllers;

import db.retail.DBHandler_RETAIL;
import db.retail.ent.Mapping;
import db.retail.beans.criteria.MappingSearch;
import db.retail.interfaces.IAdvancedSearchController;
import java.util.List;

/**
 *
 * @author root
 */
public class Mapping_Controller implements IAdvancedSearchController<Mapping, MappingSearch> {

    private static DBHandler_RETAIL dbh;

    public Mapping_Controller(DBHandler_RETAIL dbh) {
        Mapping_Controller.dbh = dbh;
    }

    @Override
    public List<Mapping> getAll() {
        return dbh.getAll_Mapping();
    }

    @Override
    public List<Mapping> get(MappingSearch criteria) {
        return dbh.getByID_Mapping(criteria.getCode());
    }

    @Override
    public Mapping getByID(MappingSearch criteria) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
