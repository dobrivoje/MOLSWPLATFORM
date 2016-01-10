package db.retail.controllers;

import db.retail.DBHandler_RETAIL;
import db.retail.ent.ReportDetails;
import db.retail.ent.criteria.NameIDLogicSearch;
import db.retail.interfaces.IAdvancedSearchController;
import java.util.List;

/**
 *
 * @author Dobri
 */
public class AS_REPDETAILS_Controller implements IAdvancedSearchController<ReportDetails, NameIDLogicSearch> {

    private static DBHandler_RETAIL dbh;

    public AS_REPDETAILS_Controller(DBHandler_RETAIL dbh) {
        AS_REPDETAILS_Controller.dbh = dbh;
    }

    /**
     * @param initSubList True=init sublist
     */
    @Override
    public List<ReportDetails> getAll(boolean initSubList) {
        return dbh.getAll_REPDETAILS();
    }

    /**
     * @param criteria NameIDLogicSearch, <br>
     * logic parameter == true -> return active reports !
     * @return
     */
    @Override
    public List<ReportDetails> get(NameIDLogicSearch criteria) {
        return dbh.getAll_REPDETAILS_ByActive(criteria.isByLogic());

    }

    @Override
    public ReportDetails getByID(NameIDLogicSearch criteria) {
        return dbh.getByID_REPDETAILS(criteria.getByID());
    }

}
