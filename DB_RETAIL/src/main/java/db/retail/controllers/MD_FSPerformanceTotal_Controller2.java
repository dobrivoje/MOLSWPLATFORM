package db.retail.controllers;

import db.retail.DBHandler_RETAIL;
import db.retail.ent.ReportDetails;
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
public class MD_FSPerformanceTotal_Controller2 implements IMDSearch<ReportDetails, OS_Search> {

    private static DBHandler_RETAIL dbh;
    private OS_Search criteria;

    public MD_FSPerformanceTotal_Controller2(DBHandler_RETAIL dbh) {
        MD_FSPerformanceTotal_Controller2.dbh = dbh;
    }

    @Override
    public Map<ReportDetails, List> getTree() {
        Map<ReportDetails, List> M = new LinkedHashMap<>();

        for (Map.Entry<ReportDetails, Object> E : dbh.get_FS_Performance_Total2(criteria.getDateFrom(), criteria.getDateTo(), criteria.getFsCode()).entrySet()) {
            if (!M.containsKey(E.getKey())) {
                M.put(E.getKey(), new ArrayList());
            }

            M.get(E.getKey()).add(E.getValue());
        }

        return M;
    }

    @Override
    public List getDetails(ReportDetails rootNode) {
        return Arrays.asList(
                dbh.get_FS_Performance_Total2(criteria.getDateFrom(), criteria.getDateTo(), criteria.getFsCode()
                )
                .get(rootNode));
    }

    @Override
    public List<ReportDetails> getAllDetails() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setCriteria(OS_Search criteria) {
        this.criteria = criteria;
    }

}
