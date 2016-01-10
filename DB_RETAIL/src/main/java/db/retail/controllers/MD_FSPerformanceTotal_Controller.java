package db.retail.controllers;

import db.retail.DBHandler_RETAIL;
import db.retail.ent.criteria.OS_Search;
import db.retail.interfaces.IMDSearch;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Dobri
 */
public class MD_FSPerformanceTotal_Controller implements IMDSearch<String, OS_Search> {

    private static DBHandler_RETAIL dbh;
    private OS_Search criteria;

    public MD_FSPerformanceTotal_Controller(DBHandler_RETAIL dbh) {
        MD_FSPerformanceTotal_Controller.dbh = dbh;
    }

    @Override
    public Map<String, List> getTree() {
        Map<String, List> M = new LinkedHashMap<>();

        for (Map.Entry<String, Object> E : dbh.get_FS_Performance_Total(criteria.getDateFrom(), criteria.getDateTo(), criteria.getFsCode()).entrySet()) {
            if (!M.containsKey(E.getKey())) {
                M.put(E.getKey(), new ArrayList());
            }

            M.get(E.getKey()).add(E.getValue());
        }

        return M;
    }

    @Override
    public List getDetails(String rootNode) {
        return Arrays.asList(
                dbh.get_FS_Performance_Total(criteria.getDateFrom(), criteria.getDateTo(), criteria.getFsCode()
                )
                .get(rootNode));
    }

    @Override
    public List<String> getAllDetails() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setCriteria(OS_Search criteria) {
        this.criteria = criteria;
    }

}
