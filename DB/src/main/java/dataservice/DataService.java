/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataservice;

import db.DBHandler;
import db.controllers.HSE.FSController;
import db.controllers.HSE.WPController;
import javax.persistence.EntityManager;
import reports.ent.HSE.HSE_SysNotifController;

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
