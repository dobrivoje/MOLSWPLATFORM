package db.retail;

import db.retail.ent.FS;
import db.retail.ent.Mapping;
import db.retail.beans.reports.ObracunFinal;
import db.retail.beans.reports.Specifikacija;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author DPrtenjak
 */
public class DBHandler_RETAIL {

    //<editor-fold defaultstate="collapsed" desc="System definitions">

    private static DBHandler_RETAIL instance;
    private static final String PERSISTENCE_UNIT_ID = "retail_DB.PU";
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_ID);
    private static final EntityManager em = emf.createEntityManager();

    public static synchronized EntityManager getEm() throws NullPointerException, Exception, java.net.UnknownHostException, java.sql.SQLException {
        return em;
    }

    private DBHandler_RETAIL() {
    }

    public static DBHandler_RETAIL getDefault() {
        return instance == null ? instance = new DBHandler_RETAIL() : instance;
    }

    private void rollBackTransaction(String message) throws Exception {
        if (getEm().getTransaction().isActive()) {
            getEm().getTransaction().rollback();
        }
        throw new Exception(message);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="RETAIL">
    //<editor-fold defaultstate="collapsed" desc="FS">
    //<editor-fold defaultstate="collapsed" desc="Read Data">
    public List<FS> getAll_FS() {
        try {
            return (List<FS>) getEm().createNamedQuery("FS.findAll").getResultList();
        } catch (Exception ex) {
            return null;
        }
    }

    public FS getByID_FS(String FSID) {
        try {
            return (FS) getEm().createNamedQuery("FS.findByCode")
                    .setParameter("code", FSID)
                    .getSingleResult();
        } catch (Exception ex) {
            return null;
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Add/update data">
    public void addNew_FS(FS newFS) throws Exception {
        try {
            getEm().getTransaction().begin();
            em.persist(newFS);
            getEm().getTransaction().commit();
        } catch (Exception ex) {
            rollBackTransaction("New FS Addition Failed");
        }
    }

    public void addNew_FS(String naziv, String code, String model) throws Exception {
        FS newFS = new FS();

        newFS.setNaziv(naziv);
        newFS.setCode(code);
        newFS.setModel(model);

        addNew_FS(newFS);
    }

    public void updateCustomer(FS fs) throws Exception {
        try {
            getEm().getTransaction().begin();
            em.merge(fs);
            getEm().getTransaction().commit();
        } catch (Exception ex) {
            rollBackTransaction("FS Update Failed");
        }
    }
    //</editor-fold>
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="COMPOSITE SELL REPORT">
    //<editor-fold defaultstate="collapsed" desc="Add/update data">
    //</editor-fold>
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="MAPPING">
    //<editor-fold defaultstate="collapsed" desc="Read Data">
    public List<Mapping> getAll_Mapping() {
        try {
            return (List<Mapping>) getEm().createNamedQuery("Mapping.findAll").getResultList();
        } catch (Exception ex) {
            return null;
        }
    }

    public List<Mapping> getByID_Mapping(String mappingCode) {
        try {
            return getEm().createNamedQuery("Mapping.findByCode")
                    .setParameter("code", mappingCode)
                    .getResultList();
        } catch (Exception ex) {
            return null;
        }
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Add/update data">
    //</editor-fold>
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="SQL Server functions calls">
    public synchronized List<ObracunFinal> get_ObracunFinal(String DatumOD, String DatumDO, String FSCode) {
        ObracunFinal of;
        List<ObracunFinal> lista = new ArrayList<>();

        try {
            if (!em.getTransaction().isActive()) {
                em.getTransaction().begin();
            }

            Connection c = em.unwrap(java.sql.Connection.class);

            CallableStatement cs = c.prepareCall("{call [COCA].[dbo].[sp_OBRACUN_FINAL] (?1, ?2, ?3)} ");
            cs.setString(1, DatumOD);
            cs.setString(2, DatumDO);
            cs.setString(3, FSCode);

            ResultSet rs = cs.executeQuery();

            while (rs.next()) {
                of = new ObracunFinal(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getDouble(6),
                        rs.getDouble(7),
                        rs.getDouble(8),
                        rs.getDouble(9),
                        rs.getBoolean(10),
                        rs.getString(11),
                        rs.getDouble(12),
                        rs.getInt(13),
                        rs.getInt(14),
                        rs.getInt(15),
                        rs.getString(16),
                        rs.getString(17),
                        rs.getString(18),
                        rs.getString(19),
                        rs.getString(20),
                        rs.getString(21),
                        rs.getString(22),
                        rs.getDouble(23)
                );

                lista.add(of);

                of = null;
            }

            em.getTransaction().commit();

            return lista;

        } catch (SQLException e1) {
            em.getTransaction().rollback();
            return null;
        } catch (Exception e2) {
            em.getTransaction().rollback();
            return null;
        } finally {
            try {
                getEm().flush();
                getEm().close();
            } catch (Exception ex) {
            }
        }
    }

    public synchronized List<Specifikacija> get_Specifikacija(String DatumOD, String DatumDO, String FSCode) {
        Specifikacija of;
        List<Specifikacija> lista = new ArrayList<>();

        try {
            if (!em.getTransaction().isActive()) {
                em.getTransaction().begin();
            }

            Connection c = em.unwrap(java.sql.Connection.class);

            CallableStatement cs = c.prepareCall("{call [COCA].[dbo].[sp_SPECIFIKACIJA] (?1, ?2, ?3)} ");
            cs.setString(1, DatumOD);
            cs.setString(2, DatumDO);
            cs.setString(3, FSCode);

            ResultSet rs = cs.executeQuery();

            while (rs.next()) {
                of = new Specifikacija(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getDouble(6),
                        rs.getString(7),
                        rs.getDouble(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12),
                        rs.getString(13)
                );

                lista.add(of);

                of = null;
            }

            em.getTransaction().commit();

            return lista;

        } catch (SQLException e1) {
            em.getTransaction().rollback();
            return null;
        } catch (Exception e2) {
            em.getTransaction().rollback();
            return null;
        } finally {
            try {
                getEm().flush();
                getEm().close();
            } catch (Exception ex) {
            }
        }
    }
    //</editor-fold>
    //</editor-fold>

}
