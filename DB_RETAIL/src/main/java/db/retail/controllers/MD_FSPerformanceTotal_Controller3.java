package db.retail.controllers;

import db.retail.DBHandler_RETAIL;
import db.retail.ent.criteria.OS_Search;
import db.retail.interfaces.IMDSearch;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Dobri
 */
public class MD_FSPerformanceTotal_Controller3 implements IMDSearch<Object, OS_Search> {

    private static DBHandler_RETAIL dbh;
    private OS_Search criteria;

    public MD_FSPerformanceTotal_Controller3(DBHandler_RETAIL dbh) {
        MD_FSPerformanceTotal_Controller3.dbh = dbh;
    }

    @Override
    public Map<Object, List> getTree() {
        return dbh.get_FS_Performance_Total3(criteria.getDateFrom(), criteria.getDateTo(), criteria.getFsCode());
    }

    @Override
    public List getDetails(Object rootNode) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Object> getAllDetails() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setCriteria(OS_Search criteria) {
        this.criteria = criteria;
    }

}
