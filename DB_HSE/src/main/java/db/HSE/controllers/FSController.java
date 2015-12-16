/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.HSE.controllers;

import db.HSE.interfaces.Controller;
import db.HSE.DBHandler_HSE;
import db.HSE.ent.FuelStation;
import java.util.List;

/**
 *
 * @author Dobri
 */
public class FSController extends Controller<FuelStation> {

    public FSController(DBHandler_HSE DBH) {
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
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void addNew(FuelStation t) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void updateExisting(FuelStation t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
