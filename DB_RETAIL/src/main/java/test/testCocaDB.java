package test;

import db.retail.ent.criteria.DateIntervalSearch;
import db.retail.ent.criteria.OS_Search;
import db.retail.ent.reports.ObracunFinal;
import db.retail.ent.reports.Specifikacija;
import db.retail.dataservice.DataService_RETAIL;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author DPrtenjak
 */
public class testCocaDB {

    private static final DataService_RETAIL DS = DataService_RETAIL.getDefault();

    public static void main(String[] args) {
        /*
         System.err.println("FS test - All FS's:");
         for (FS f : DS.getASC_FS_C().getAll()) {
         System.err.println(f);
         }
        
         System.err.println("FS test - FS by ID : " + fss.getCode());
         System.err.println(DS.getASC_FS_C().get(fss));
        
         System.err.println("Mapping test - All mappings:");
         for (Mapping m : DS.getASC_Mapping().getAll()) {
         System.err.println(m);
         }
        
         System.err.println("Mapping test - By ID: ");
         System.err.println(DS.getASC_Mapping().get(ms));

         System.err.println("finalni obračun test.");

         */
        int br = 0;
        String Od = "2015-11-1";
        String Do = "2015-11-30";
        String fsCode = "90431";

        Date Od1;
        Date Do1;

        try {
            Od1 = new SimpleDateFormat("yyyy-MM-dd").parse("2015-11-1");
            Do1 = new SimpleDateFormat("yyyy-MM-dd").parse("2015-11-30");

            System.err.println("datumi test : od: " + Od1 + ", do: " + Do1);
        } catch (Exception e) {
        }

        for (ObracunFinal o : DS.getASC_FinalObracun_C().get(new OS_Search(new DateIntervalSearch(Od, Do), fsCode))) {
            System.err.println((++br) + " " + o.toString());
        }

        System.err.println("finalni obračun test.");

        int br1 = 0;

        for (Specifikacija o : DS.getASC_Specifikacija_C().get(new OS_Search(new DateIntervalSearch(Od, Do), fsCode))) {
            System.err.println((++br1) + " " + o.toString());
        }

    }
}
