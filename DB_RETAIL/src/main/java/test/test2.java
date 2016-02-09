/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import db.retail.DBHandler_RETAIL;
import db.retail.dataservice.DataService_RETAIL;
import db.retail.ent.FS;
import db.retail.ent.Koef;
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
 * @author Dobri
 */
public class test2 {

    private static final DataService_RETAIL DS = DataService_RETAIL.getDefault();
    private static final DBHandler_RETAIL DBH = DBHandler_RETAIL.getDefault();

    public static void main(String[] args) {
        String oD = "2015-11-1";
        String dO = "2015-11-30";
        //String fs = "90431";
        String fs = null;

        List reportNames = DS.getASC_ReportDetails_C().get(new NameIDLogicSearch(null, false, -1));
        System.err.println(reportNames);

        /*
        for (FS f : DS.getASC_FS_C().getAll(false)) {
            System.err.println("");
            System.err.println("");
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
            System.err.println(fsn + ", " + fsn.getNaziv().length() + ", " + fsn.getCode());
        }

        System.err.println("");
        System.err.println("");
        System.err.println("Koeficijenti :");
        System.err.println("");
        for (Koef k : DS.getASC_KOEF_C().getAll(false)) {
            System.err.println(k + "  [" + k.getKOd() + ", " + k.getKDo() + "]");
        }

        System.err.println("");
        System.err.println("");
        System.err.println("test :");
        System.err.println("");

        OS_Search criteria = new OS_Search(new DateIntervalSearch("2015-11-1", "2015-11-30"), "90431");
        Map<String, List> data = DS.getMD_FS_Performace_C(criteria.getDateFrom(), criteria.getDateTo(), criteria.getFsCode()).getTree();

        for (Map.Entry<String, List> E : data.entrySet()) {
            String key = E.getKey();
            List value = E.getValue();

            System.err.println("key: " + key);
            System.err.println("value: " + value);
        }
        */
        
        System.err.println("");
        System.err.println("");
        System.err.println("test : get_FS_Performance_Total3 :");
        System.err.println("");
        
        OS_Search criteria = new OS_Search(new DateIntervalSearch("2015-11-1", "2015-11-30"), fs);
        Map<Object, List> d1 = DBH.get_FS_Performance_Total3(criteria.getDateFrom(), criteria.getDateTo(), criteria.getFsCode());

        for (Map.Entry<Object, List> E : d1.entrySet()) {
            Object key = E.getKey();
            List value = E.getValue();

            System.err.println("key: " + key);
            System.err.println("value: " + value);
        }

    }

}
