package db.retail.dataservice;

import db.retail.DBHandler_RETAIL;
import db.retail.beans.criteria.FSSearch;
import db.retail.beans.criteria.OS_Search;
import db.retail.beans.reports.ObracunFinal;
import db.retail.beans.reports.Specifikacija;
import db.retail.controllers.AS_FS_Controller;
import db.retail.controllers.FinalObracun_Controller;
import db.retail.controllers.Specifikacija_Controller;
import db.retail.ent.FS;
import db.retail.interfaces.IAdvancedSearchController;
import javax.persistence.EntityManager;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author DPrtenjak
 */
public class DataService_RETAIL {

    //<editor-fold defaultstate="collapsed" desc="System Defs">
    private static DataService_RETAIL instance;

    private static final DBHandler_RETAIL DBH_RETAIL = DBHandler_RETAIL.getDefault();

    private DataService_RETAIL() {
    }

    public static synchronized DataService_RETAIL getDefault() {
        return instance == null ? instance = new DataService_RETAIL() : instance;
    }

    public static synchronized EntityManager getEM() throws Exception {
        return DBHandler_RETAIL.getEm();
    }
    //</editor-fold>

    private final IAdvancedSearchController<ObracunFinal, OS_Search> iof = new FinalObracun_Controller(DBH_RETAIL);
    private final IAdvancedSearchController<Specifikacija, OS_Search> sc = new Specifikacija_Controller(DBH_RETAIL);
    private final IAdvancedSearchController<FS, FSSearch> fss = new AS_FS_Controller(DBH_RETAIL);

    public IAdvancedSearchController<ObracunFinal, OS_Search> getASC_FinalObracun() {
        return iof;
    }

    public IAdvancedSearchController<Specifikacija, OS_Search> getASC_Specifikacija() {
        return sc;
    }

    public IAdvancedSearchController<FS, FSSearch> getASC_FS() {
        return fss;
    }

}
