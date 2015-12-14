/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.retail.controllers;

import db.retail.DBHandler_RETAIL;
import db.retail.ent.criteria.MappingSearch;
import db.retail.ent.Mapping;
import db.retail.interfaces.IController;
import java.util.List;

/**
 *
 * @author root
 */
public class AS_MAPPING_Controller implements IController<Mapping, MappingSearch> {

    private static DBHandler_RETAIL dbh;

    public AS_MAPPING_Controller(DBHandler_RETAIL dbh) {
        AS_MAPPING_Controller.dbh = dbh;
    }

    @Override
    public Mapping getByID(MappingSearch criteria) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Mapping> get(MappingSearch criteria) {
        return dbh.getByID_Mapping(criteria.getCode());
    }

    @Override
    public List<Mapping> getAll() {
        return dbh.getAll_Mapping();
    }

    @Override
    public void update(Mapping item) throws Exception {
        dbh.update_Mapping(item);
    }

    @Override
    public void add(Mapping newItem) throws Exception {
        dbh.addNew_Mapping(newItem);
    }
}
