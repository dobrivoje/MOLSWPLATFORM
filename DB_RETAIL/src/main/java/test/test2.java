/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import db.retail.DBHandler_RETAIL;
import db.retail.dataservice.DataService_RETAIL;
import db.retail.ent.ReportDetails;
import db.retail.ent.reports.Obracun_FS_PerfDetaljno;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
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

        Map<Object, List<Object>> M_Report_FS_DanOst = new LinkedHashMap<>();

        long t0 = TimeUnit.MILLISECONDS.toMillis(new Date().getTime());
        for (ReportDetails rd : DS.getAS_ReportDetails_C().getAll(false)) {
            M_Report_FS_DanOst.put(
                    rd,
                    DBH.get_FS_Performance_Detailjno(oD, dO, fs)
                    .stream()
                    .filter(p -> p.getIdRd().equals(rd.getIdrd()))
                    .collect(Collectors.toList())
            );
        }
        long t1 = TimeUnit.MILLISECONDS.toMillis(new Date().getTime());
        System.err.println("time spent in milliseconds: " + TimeUnit.MILLISECONDS.toMillis(t1 - t0));

        for (Map.Entry<Object, List<Object>> E : M_Report_FS_DanOst.entrySet()) {
            System.err.println("report : " + E.getKey());
            for (Object fsp : E.getValue()) {
                System.err.println(((Obracun_FS_PerfDetaljno) fsp));
            }
        }

        System.err.println("test2 preko kontrolera: ");

        long t2 = TimeUnit.MILLISECONDS.toMillis(new Date().getTime());
        for (Map.Entry<ReportDetails, List> E : DS.getMD_FSPerformanceDetailed_C(oD, dO, fs).getTree().entrySet()) {
            System.err.println("report : " + (ReportDetails) E.getKey());
            for (Object fsp : E.getValue()) {
                System.err.println(((Obracun_FS_PerfDetaljno) fsp).getOstvarenje());
            }
        }
        long t3 = TimeUnit.MILLISECONDS.toMillis(new Date().getTime());
        System.err.println("time spent in milliseconds: " + TimeUnit.MILLISECONDS.toMillis(t3 - t2));

    }

}
