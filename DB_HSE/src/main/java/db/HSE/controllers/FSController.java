/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.HSE.controllers;

import db.HSE.DBHandler;
import db.HSE.ent.FuelStation;
import java.util.List;

/**
 *
 * @author dprtenjak
 */
public class FSController extends Controller<FuelStation> {

    public FSController(DBHandler DBH) {
        super(DBH);
    }

    public List<FuelStation> getFuelStation(String partialName) {
        return DBH.getFuelStation(partialName);
    }

    @Override
    public List<FuelStation> getAll() {
        return DBH.getAllFuelStations();
    }

    @Override
    public FuelStation getByID(Long ID) {
        return DBH.getFuelStationByID(ID);
    }

    @Override
    public void delete(FuelStation t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addNew(FuelStation t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateExisting(FuelStation t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
