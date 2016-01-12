/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import db.retail.DBHandler_RETAIL;
import db.retail.dataservice.DataService_RETAIL;
import db.retail.ent.ReportDetails;
import db.retail.ent.criteria.NameIDLogicSearch;
import db.retail.ent.reports.Obracun_FS_PerfDetaljno;
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
        String fs = "90431";
        //String fs = null;

        Map<ReportDetails, List> data = DS.getMD_FSPerformanceDetailed_C(oD, dO, fs).getTree();
        List cats = data.keySet()
                .stream()
                //.filter(p -> p.getIdrd() == 1)
                //.map(ReportDetails::getNaziv)
                .collect(Collectors.toList());

        System.err.println("cats : " + ((ReportDetails) cats.get(0)).getIdrd());
        System.err.println("cats : " + cats.get(0));
        System.err.println("cats.get(0) list : ");
        for (Obracun_FS_PerfDetaljno xAxis : (List<Obracun_FS_PerfDetaljno>) data.get(cats.get(0))) {
            System.err.println(xAxis.getDan() + " - " + xAxis.getOstvarenje());
        }

        Map<ReportDetails, List> data1 = DS.getMD_FSPerformanceDetailed_C(oD, dO, fs).getTree();
        List categories = data1.keySet().stream().collect(Collectors.toList());
        System.err.println("data1 : " + categories);

        List reportNames = DS.getAS_ReportDetails_C().get(new NameIDLogicSearch(null, false, -1));
        System.err.println(reportNames);
    }

}
