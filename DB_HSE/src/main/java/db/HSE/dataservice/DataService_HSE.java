/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.HSE.dataservice;

import db.HSE.DBHandler_HSE;
import db.HSE.controllers.FSController;
import db.HSE.controllers.WPController;
import db.HSE.ent.reportbeans.HSE_SysNotifController;

/**
 *
 * @author Dobri
 */
public class DataService_HSE {

    //<editor-fold defaultstate="collapsed" desc="System Defs">
    private static DataService_HSE instance;

    private static final DBHandler_HSE DBH = DBHandler_HSE.getDefault();

    private DataService_HSE() {
    }

    public static synchronized DataService_HSE getDefault() {
        return instance == null ? instance = new DataService_HSE() : instance;
    }
    //</editor-fold>

    private final FSController FSC = new FSController(DBH);
    private final WPController WPC = new WPController(DBH);
    private final HSE_SysNotifController HSNC = new HSE_SysNotifController(DBH);

    public FSController getFSController() {
        return FSC;
    }

    public WPController getWPController() {
        return WPC;
    }

    public HSE_SysNotifController getHSESysNotifController() {
        return HSNC;
    }

}
