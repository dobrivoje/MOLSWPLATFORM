package db.retail.controllers;

import db.retail.DBHandler_RETAIL;
import db.retail.ent.criteria.OS_Search;
import db.retail.interfaces.IMasterDetail2;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Dobri
 */
public class MD_FSPerformance_Controller implements IMasterDetail2<String, String, OS_Search> {

    private static DBHandler_RETAIL dbh;

    public MD_FSPerformance_Controller(DBHandler_RETAIL dbh) {
        MD_FSPerformance_Controller.dbh = dbh;
    }

    @Override
    public Map<String, List<String>> getMasterDetail(OS_Search criteria) {
        return dbh.get_FS_Performance(criteria.getDateFrom(), criteria.getDateTo(), criteria.getFsCode());
    }

}
