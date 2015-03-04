/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.controllers.HSE;

import db.DBHandler;
import db.ent.HSE.FuelStation;
import db.ent.HSE.WorkPlan;
import java.util.List;

/**
 *
 * @author dprtenjak
 */
public class WPController extends Controller<WorkPlan> {

    public WPController(DBHandler DBH) {
        super(DBH);
    }

    //<editor-fold defaultstate="collapsed" desc="Read">
    @Override
    public List<WorkPlan> getAll() {
        return DBH.getAllWorkPlans();
    }

    public List<WorkPlan> getFSWorkPlans(FuelStation fuelStation) {
        return DBH.getFSWorkPlans(fuelStation);
    }

    @Override
    public WorkPlan getByID(Long ID) {
        throw new UnsupportedOperationException("Not supported.");
    }

    public List<WorkPlan> getNewestWorkPlans() {
        return DBH.getNewestWorkPlans();
    }

    public List<WorkPlan> getAllWorkPlansFinished(boolean finished) {
        return DBH.getAllWorkPlansFinished(finished);
    }

    public Long getWorkPlansCountByStation(FuelStation fs, boolean finished) {
        return DBH.getWorkPlansCountByStation(fs, finished);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Change">
    @Override
    public void delete(WorkPlan t) {
        // return DBH.deleteWorkPlan(workPlan);
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void update(WorkPlan workPlan) {
        // return DBH.deleteWorkPlan(workPlan);
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addNew(WorkPlan t) throws Exception {
        DBH.addNewWorkPlan(t);
    }

    @Override
    public void updateExisting(WorkPlan t) throws Exception {
        DBH.updateWorkPlan(t);
    }
    //</editor-fold>

}
