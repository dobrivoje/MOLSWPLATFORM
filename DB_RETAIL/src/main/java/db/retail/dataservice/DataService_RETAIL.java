package db.retail.dataservice;

import db.retail.DBHandler_RETAIL;
import db.retail.ent.criteria.CSRSearch;
import db.retail.ent.criteria.FSSearch;
import db.retail.ent.criteria.MappingSearch;
import db.retail.ent.criteria.OS_Search;
import db.retail.ent.reports.ObracunFinal;
import db.retail.ent.reports.Specifikacija;
import db.retail.controllers.AS_CSR_Controller;
import db.retail.controllers.AS_FS_Controller;
import db.retail.controllers.AS_GN_Controller;
import db.retail.controllers.AS_KATEG_Controller;
import db.retail.controllers.AS_MAPPING_Controller;
import db.retail.controllers.AS_PARTN_Controller;
import db.retail.controllers.AS_REPDETAILS_Controller;
import db.retail.controllers.AS_UGOVOR_Controller;
import db.retail.controllers.FS_Controller;
import db.retail.controllers.FinalObracun_Controller;
import db.retail.controllers.MD_FSPerformanceTotal_Controller;
import db.retail.controllers.MD_FSPerformanceDetailed_Controller;
import db.retail.controllers.MD_Partner_Controller;
import db.retail.controllers.MD_Ugovor_Controller;
import db.retail.controllers.Specifikacija_Controller;
import db.retail.ent.CompositeSellReport;
import db.retail.ent.FS;
import db.retail.ent.GrupniNaziv;
import db.retail.ent.Kategorija;
import db.retail.ent.Mapping;
import db.retail.ent.Partner;
import db.retail.ent.ReportDetails;
import db.retail.ent.Ugovor;
import db.retail.ent.criteria.DateIntervalSearch;
import db.retail.ent.criteria.NameIDLogicSearch;
import db.retail.ent.criteria.PUSearch;
import db.retail.ent.criteria.UgovorSearch;
import db.retail.interfaces.IAdvancedSearchController;
import db.retail.interfaces.ICRUDController;
import db.retail.interfaces.IController;
import db.retail.interfaces.IMDSearch;
import db.retail.interfaces.IMasterDetail;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Dobri
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
    private final IAdvancedSearchController<Kategorija, Integer> kc = new AS_KATEG_Controller(DBH_RETAIL);
    private final IMasterDetail<Partner> mdp = new MD_Partner_Controller(DBH_RETAIL);
    private final IMasterDetail<FS> mduc = new MD_Ugovor_Controller(DBH_RETAIL);
    private final IController<Partner, PUSearch> asp = new AS_PARTN_Controller(DBH_RETAIL);
    private final IController<Ugovor, UgovorSearch> usc = new AS_UGOVOR_Controller(DBH_RETAIL);
    private final IMDSearch<String, OS_Search> mds1 = new MD_FSPerformanceTotal_Controller(DBH_RETAIL);
    private final IAdvancedSearchController<ReportDetails, NameIDLogicSearch> rd = new AS_REPDETAILS_Controller(DBH_RETAIL);
    private final IMDSearch<ReportDetails, OS_Search> fspdtc = new MD_FSPerformanceDetailed_Controller(DBH_RETAIL);

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
    public IAdvancedSearchController<Kategorija, Integer> getASC_KATEG_C() {
        return kc;
    }

    /**
     * Master detail Partner Controller
     *
     * @return
     */
    public IMasterDetail<Partner> getMD_Partner_C() {
        return mdp;
    }

    /**
     * Master detail Ugovor Controller
     *
     * @return
     */
    public IMasterDetail<FS> getMD_Ugovor_C() {
        return mduc;
    }

    public IController<Partner, PUSearch> getASC_Partner_C() {
        return asp;
    }

    public IController<Ugovor, UgovorSearch> getASC_Ugovor_C() {
        return usc;
    }

    public IMDSearch<String, OS_Search> getMD_FS_Performace_C() {
        return mds1;
    }

    public IMDSearch<String, OS_Search> getMD_FS_Performace_C(String from, String to, String fsCode) {
        mds1.setCriteria(new OS_Search(new DateIntervalSearch(from, to), fsCode));

        return mds1;
    }

    public IMDSearch<String, OS_Search> getMD_FS_Performace_C(OS_Search criteria) {
        mds1.setCriteria(criteria);

        return mds1;
    }

    public IMDSearch<ReportDetails, OS_Search> getMD_FSPerformanceDetailed_C() {
        return fspdtc;
    }

    public IMDSearch<ReportDetails, OS_Search> getMD_FSPerformanceDetailed_C(String from, String to, String fsCode) {
        fspdtc.setCriteria(new OS_Search(new DateIntervalSearch(from, to), fsCode));

        return fspdtc;
    }

    public IMDSearch<ReportDetails, OS_Search> getMD_FSPerformanceDetailed_C(OS_Search criteria) {
        fspdtc.setCriteria(criteria);

        return fspdtc;
    }

    public IAdvancedSearchController<ReportDetails, NameIDLogicSearch> getAS_ReportDetails_C() {
        return rd;
    }
}
