package db.retail.controllers;

import db.retail.DBHandler_RETAIL;
import db.retail.ent.criteria.OS_Search;
import db.retail.interfaces.IMasterDetail4;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Dobri
 */
public class MD_FSPerformance_Controller2 implements IMasterDetail4<String, OS_Search> {

    private static DBHandler_RETAIL dbh;

    public MD_FSPerformance_Controller2(DBHandler_RETAIL dbh) {
        MD_FSPerformance_Controller2.dbh = dbh;
    }

    @Override
    public Map<String, List> getMasterDetail(OS_Search criteria) {
        Map<String, List> M = new LinkedHashMap<>();

        for (Map.Entry<String, Object> entrySet : dbh.get_FS_Performance(criteria.getDateFrom(), criteria.getDateTo(), criteria.getFsCode()).entrySet()) {
            M.put(entrySet.getKey(), Arrays.asList(entrySet.getValue()));
        }

        return M;
    }
}
