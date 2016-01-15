/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import db.retail.DBHandler_RETAIL;
import db.retail.dataservice.DataService_RETAIL;
import db.retail.ent.FS;
import db.retail.ent.ReportDetails;
import db.retail.ent.criteria.DateIntervalSearch;
import db.retail.ent.criteria.NameIDLogicSearch;
import db.retail.ent.criteria.OS_Search;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * @author root
 */
public class test2 {

    private static final DataService_RETAIL DS = DataService_RETAIL.getDefault();
    private static final DBHandler_RETAIL DBH = DBHandler_RETAIL.getDefault();

    public static void main(String[] args) {
        String oD = "2015-11-1";
        String dO = "2015-11-30";
        //String fs = "90431";
        String fs = null;

        List reportNames = DS.getAS_ReportDetails_C().get(new NameIDLogicSearch(null, false, -1));
        System.err.println(reportNames);

        for (FS f : DS.getASC_FS_C().getAll(false)) {
            System.err.println("------------------------------");
            System.err.println("----" + f + "-----");
            System.err.println("------------------------------");
            for (Map.Entry<ReportDetails, List> entrySet : DS.getMD_FS_Performace_C2(new OS_Search(new DateIntervalSearch(dO, dO), f.getCode())).getTree().entrySet()) {
                ReportDetails key = entrySet.getKey();
                List value = entrySet.getValue();

                System.err.println("k : " + key);
                System.err.println("v : " + value);

            }
        }

        List<FS> fsNames = DS.getASC_FS_C().getAll(false).subList(0, 10)
                .stream()
                .sorted(new Comparator<FS>() {

                    @Override
                    public int compare(FS f1, FS f2) {
                        try {
                            return Integer.valueOf(f2.getCode()) - Integer.valueOf(f1.getCode());
                        } catch (Exception e) {
                            return 0;
                        }
                    }
                })
                .collect(Collectors.toList());

        for (FS fsn : fsNames) {
            System.err.println(fsn + ", " + fsn.getNaziv().length()+", "+fsn.getCode());
        }
    }

}
