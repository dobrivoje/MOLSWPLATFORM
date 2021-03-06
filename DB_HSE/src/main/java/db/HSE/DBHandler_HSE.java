package db.HSE;

import java.util.List;
import db.HSE.ent.FuelStation;
import db.HSE.ent.WorkPlan;

public class DBHandler_HSE extends DBHandler {

    //<editor-fold defaultstate="collapsed" desc="System defs">
    private static DBHandler_HSE instance;

    private DBHandler_HSE() {
        super("FSOfficeCom_PU");
    }

    public static DBHandler_HSE getDefault() {
        return instance == null ? instance = new DBHandler_HSE() : instance;
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

    public Long getWorkPlansCountByStation(FuelStation fs, boolean finished) {
        try {
            return (Long) getEm().createNamedQuery("WorkPlan.getFinishedWPByFS")
                    .setParameter("FSID", fs)
                    .setParameter("finished", finished)
                    .getSingleResult();
        } catch (Exception ex) {
            return 0L;
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
        try {
            getEm().getTransaction().begin();
            em.persist(workPlan);
            getEm().getTransaction().commit();
        } catch (Exception e) {
            rollBackTransaction(ADD_FAILED);
        }
    }

    public void updateWorkPlan(WorkPlan workPlan) throws Exception {
        try {
            getEm().getTransaction().begin();
            em.merge(workPlan);
            getEm().getTransaction().commit();
        } catch (Exception e) {
            rollBackTransaction(UPDATE_FAILED);
        }
    }
    //</editor-fold>
    //</editor-fold>
}
