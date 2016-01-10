package db.retail.controllers;

import db.retail.DBHandler_RETAIL;
import db.retail.ent.ReportDetails;
import db.retail.ent.criteria.OS_Search;
import db.retail.interfaces.IMDSearch;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * @author Dobri
 */
public class MD_FSPerformanceDetailed_Controller implements IMDSearch<ReportDetails, OS_Search> {

    private static DBHandler_RETAIL dbh;
    private OS_Search criteria;

    public MD_FSPerformanceDetailed_Controller(DBHandler_RETAIL dbh) {
        MD_FSPerformanceDetailed_Controller.dbh = dbh;
    }

    @Override
    public Map<ReportDetails, List> getTree() {
        Map<ReportDetails, List> M_Report_FS_DanOst = new LinkedHashMap<>();

        for (ReportDetails rd : dbh.getAll_REPDETAILS_ByActive(true)) {
            M_Report_FS_DanOst.put(
                    rd,
                    dbh.get_FS_Performance_Detailjno(criteria.getDateFrom(), criteria.getDateTo(), criteria.getFsCode())
                    .stream()
                    .filter(p -> p.getIdRd().equals(rd.getIdrd()))
                    .collect(Collectors.toList())
            );
        }

        return M_Report_FS_DanOst;
    }

    @Override
    public List getDetails(ReportDetails rd) {
        return dbh.get_FS_Performance_Detailjno(criteria.getDateFrom(), criteria.getDateTo(), criteria.getFsCode())
                .stream()
                .filter(p -> p.getIdRd().equals(((ReportDetails) rd).getIdrd()))
                .collect(Collectors.toList());
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
