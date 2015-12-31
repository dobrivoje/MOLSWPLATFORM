package db.retail.controllers;

import db.retail.DBHandler_RETAIL;
import db.retail.ent.criteria.OS_Search;
import db.retail.interfaces.IMasterDetail;
import java.util.List;

/**
 *
 * @author Dobri
 */
public class MD_FSPerformance_Controller implements IMasterDetail<String> {

    private static DBHandler_RETAIL dbh;
    private OS_Search criteria;

    public MD_FSPerformance_Controller(DBHandler_RETAIL dbh) {
        MD_FSPerformance_Controller.dbh = dbh;
    }

    @Override
    public List getDetails(String master) {
        return (List) dbh.get_FS_Performance(
                criteria.getDateFrom(),
                criteria.getDateTo(),
                criteria.getFsCode()
        ).values();
    }

    @Override
    public List<String> getAllDetails() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
