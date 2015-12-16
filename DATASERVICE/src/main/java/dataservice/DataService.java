/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataservice;

import db.HSE.dataservice.DataService_HSE;
import db.retail.dataservice.DataService_RETAIL;

/**
 *
 * @author Dobri
 */
public class DataService {

    //<editor-fold defaultstate="collapsed" desc="System Defs">
    private static DataService instance;

    private static final DataService_RETAIL DS_RETAIL = DataService_RETAIL.getDefault();
    private static final DataService_HSE DBH_HSE = DataService_HSE.getDefault();

    private DataService() {
    }

    public static synchronized DataService getDefault() {
        return instance == null ? instance = new DataService() : instance;
    }
    //</editor-fold>

    public static DataService_RETAIL getDS_RETAIL() {
        return DS_RETAIL;
    }

    public static DataService_HSE getDS_HSE() {
        return DBH_HSE;
    }
}
