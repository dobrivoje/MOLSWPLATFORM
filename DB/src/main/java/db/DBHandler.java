/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import db.ent.HSE.FuelStation;
import db.ent.HSE.WorkPlan;

/**
 *
 * @author root
 */
public class DBHandler {

    //<editor-fold defaultstate="collapsed" desc="System definitions">
    private static DBHandler instance;
    private static final String PERSISTENCE_UNIT_ID = "org.superb.apps.ws.FSOfficeCom_PU";
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_ID);
    private static final EntityManager em = emf.createEntityManager();
    
    public static synchronized EntityManager getEm() throws NullPointerException, Exception, java.net.UnknownHostException, java.sql.SQLException {
        return em;
    }
    
    private DBHandler() {
    }
    
    public static DBHandler getDefault() {
        return instance == null ? instance = new DBHandler() : instance;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="FuelStation">
    //<editor-fold defaultstate="collapsed" desc="Read Data">
    public List<FuelStation> getAllFuelStations() {
        try {
            return (List<FuelStation>) getEm().createNamedQuery("FuelStation.findAll").getResultList();
        } catch (Exception ex) {
            return null;
        }
    }
    
    public FuelStation getFuelStationByID(Long fsID) {
        try {
            return (FuelStation) getEm().createNamedQuery("FuelStation.findById")
                    .setParameter("id", fsID)
                    .getSingleResult();
        } catch (Exception ex) {
            return null;
        }
    }
    
    public List<FuelStation> getFuelStation(String partialName) {
        try {
            return getEm().createNamedQuery("FuelStation.findByName")
                    .setParameter("name", partialName)
                    .getResultList();
        } catch (Exception ex) {
            return null;
        }
    }
    //</editor-fold>
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Work Plan">
    //<editor-fold defaultstate="collapsed" desc="Read Data">
    public List<WorkPlan> getAllWorkPlans() {
        try {
            return (List<WorkPlan>) getEm().createNamedQuery("WorkPlan.findAll").getResultList();
        } catch (Exception ex) {
            return null;
        }
    }
    
    public List<WorkPlan> getFSWorkPlans(FuelStation fs) {
        try {
            return (List<WorkPlan>) getEm().createNamedQuery("WorkPlan.getByFS")
                    .setParameter("FSID", fs)
                    .getResultList();
        } catch (Exception ex) {
            return null;
        }
    }
    
    public List<WorkPlan> getNewestWorkPlans() {
        try {
            return (List<WorkPlan>) getEm().createNamedQuery("WorkPlan.findAllSortByDateDesc").getResultList();
        } catch (Exception ex) {
            return null;
        }
    }
    
    public List<WorkPlan> getAllWorkPlansFinished(boolean finished) {
        try {
            return getEm().createNamedQuery("WorkPlan.findAllFinishedSortByDateAsc")
                    .setParameter("finished", finished)
                    .getResultList();
        } catch (Exception ex) {
            return null;
        }
    }
    
    public FuelStation getWorkPlanByID(Long wpID) {
        try {
            return (FuelStation) getEm().createNamedQuery("WorkPlan.findByIdwp")
                    .setParameter("idwp", wpID)
                    .getSingleResult();
        } catch (Exception ex) {
            return null;
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Write Data">
    public void addNewWorkPlan(WorkPlan workPlan) throws Exception {
        getEm().getTransaction().begin();
        em.persist(workPlan);
        getEm().getTransaction().commit();
    }
    
    public void updateWorkPlan(WorkPlan workPlan) throws Exception {
        getEm().getTransaction().begin();
        em.merge(workPlan);
        getEm().getTransaction().commit();
    }
    //</editor-fold>
    //</editor-fold>
}
