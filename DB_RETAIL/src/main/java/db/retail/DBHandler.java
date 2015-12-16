/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.retail;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Dobri
 */
public abstract class DBHandler {

    //<editor-fold defaultstate="collapsed" desc="System definitions">
    protected static String PERSISTENCE_UNIT_ID;
    protected static EntityManagerFactory emf;
    protected static EntityManager em;

    protected static synchronized EntityManager getEm() throws NullPointerException, Exception, java.net.UnknownHostException, java.sql.SQLException {
        return em;
    }

    protected DBHandler(String PERSISTENCE_UNIT) {
        DBHandler.PERSISTENCE_UNIT_ID = PERSISTENCE_UNIT;
        DBHandler.emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_ID);
        DBHandler.em = emf.createEntityManager();
    }

    protected void rollBackTransaction(String message) throws Exception {
        if (getEm().getTransaction().isActive()) {
            getEm().getTransaction().rollback();
        }
        throw new Exception(message);
    }
    //</editor-fold>
}
