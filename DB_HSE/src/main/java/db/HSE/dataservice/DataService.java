/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.HSE.dataservice;

import db.HSE.DBHandler;
import db.HSE.DBHandler;
import db.HSE.DBHandler;
import db.HSE.controllers.FSController;
import db.HSE.controllers.WPController;
import javax.persistence.EntityManager;
import db.HSE.ent.reportbeans.HSE_SysNotifController;

/**
 *
 * @author root
 */
public class DataService {

    //<editor-fold defaultstate="collapsed" desc="System Defs">
    private static DataService instance;

    private static final DBHandler DBH = DBHandler.getDefault();

    private DataService() {
    }

    public static synchronized DataService getDefault() {
        return instance == null ? instance = new DataService() : instance;
    }
    
    public static synchronized EntityManager getEM() throws Exception {
        return DBHandler.getEm();
    }
    //</editor-fold>

    private final FSController fsController = new FSController(DBH);
    private final WPController wpController = new WPController(DBH);
    private final HSE_SysNotifController hseSysNotifController = new HSE_SysNotifController(DBH);

    public FSController getFSController() {
        return fsController;
    }

    public WPController getWPController() {
        return wpController;
    }

    public HSE_SysNotifController getHSESysNotifController() {
        return hseSysNotifController;
    }

}
