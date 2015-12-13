package db.retail.dataservice;

import db.retail.DBHandler_RETAIL;
import db.retail.beans.criteria.CSRSearch;
import db.retail.beans.criteria.FSSearch;
import db.retail.beans.criteria.MappingSearch;
import db.retail.beans.criteria.OS_Search;
import db.retail.beans.reports.ObracunFinal;
import db.retail.beans.reports.Specifikacija;
import db.retail.controllers.AS_CSR_Controller;
import db.retail.controllers.AS_FS_Controller;
import db.retail.controllers.AS_GN_Controller;
import db.retail.controllers.AS_KATEG_Controller;
import db.retail.controllers.AS_MAPPING_Controller;
import db.retail.controllers.FS_Controller;
import db.retail.controllers.FinalObracun_Controller;
import db.retail.controllers.Specifikacija_Controller;
import db.retail.ent.CompositeSellReport;
import db.retail.ent.FS;
import db.retail.ent.GrupniNaziv;
import db.retail.ent.Kategorija;
import db.retail.ent.Klasifikacija;
import db.retail.ent.Mapping;
import db.retail.interfaces.IAdvancedSearchController;
import db.retail.interfaces.ICRUDController;
import db.retail.interfaces.IController;

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
    //</editor-fold>

    private final IAdvancedSearchController<ObracunFinal, OS_Search> iof = new FinalObracun_Controller(DBH_RETAIL);
    private final IAdvancedSearchController<Specifikacija, OS_Search> sc = new Specifikacija_Controller(DBH_RETAIL);
    private final IAdvancedSearchController<CompositeSellReport, CSRSearch> csc = new AS_CSR_Controller(DBH_RETAIL);
    private final IAdvancedSearchController<FS, FSSearch> fss = new AS_FS_Controller(DBH_RETAIL);
    private final ICRUDController<FS> icc = new FS_Controller(DBH_RETAIL);
    private final IController<Mapping, MappingSearch> mc = new AS_MAPPING_Controller(DBH_RETAIL);
    private final IAdvancedSearchController<GrupniNaziv, String> gnc = new AS_GN_Controller(DBH_RETAIL);
    private final IAdvancedSearchController<Kategorija, String> kc = new AS_KATEG_Controller(DBH_RETAIL);

    /**
     * Advanced Search Controller - Obračun
     *
     * @return
     */
    public IAdvancedSearchController<ObracunFinal, OS_Search> getASC_FinalObracun_C() {
        return iof;
    }

    /**
     * Advanced Search Controller - Specifikacija
     *
     * @return
     */
    public IAdvancedSearchController<Specifikacija, OS_Search> getASC_Specifikacija_C() {
        return sc;
    }

    /**
     * Advanced Search Controller - FS
     *
     * @return
     */
    public IAdvancedSearchController<FS, FSSearch> getASC_FS_C() {
        return fss;
    }

    /**
     * FS CRD Controller
     *
     * @return
     */
    public ICRUDController<FS> getFS_C() {
        return icc;
    }

    /**
     * Advanced Search Controller - Composite Sell Report
     *
     * @return
     */
    public IAdvancedSearchController<CompositeSellReport, CSRSearch> getASC_CS_C() {
        return csc;
    }

    /**
     * Advanced Mapping Controller - Search and CRUD Controller
     *
     * @return
     */
    public IController<Mapping, MappingSearch> getASC_MAPPING_C() {
        return mc;
    }

    /**
     * Advanced Grupni Naziv Controller - Search Controller
     *
     * @return
     */
    public IAdvancedSearchController<GrupniNaziv, String> getASC_GN_C() {
        return gnc;
    }

    /**
     * Advanced Kategorija Controller - Search Controller
     *
     * @return
     */
    public IAdvancedSearchController<Kategorija, String> getASC_KATEG_C() {
        return kc;
    }

}
