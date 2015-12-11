package test;

import db.retail.beans.criteria.DateIntervalSearch;
import db.retail.beans.criteria.OS_Search;
import db.retail.beans.reports.ObracunFinal;
import db.retail.beans.reports.Specifikacija;
import db.retail.dataservice.DataService_RETAIL;

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
         for (FS f : DS.getASC_FS().getAll()) {
         System.err.println(f);
         }
        
         System.err.println("FS test - FS by ID : " + fss.getCode());
         System.err.println(DS.getASC_FS().get(fss));
        
         System.err.println("Mapping test - All mappings:");
         for (Mapping m : DS.getASC_Mapping().getAll()) {
         System.err.println(m);
         }
        
         System.err.println("Mapping test - By ID: ");
         System.err.println(DS.getASC_Mapping().get(ms));
         */

        System.err.println("finalni obračun test.");

        int br = 0;
        String Od = "2015-11-1";
        String Do = "2015-11-30";
        // String fsCode = "90431";
        String fsCode = null;

        for (ObracunFinal o : DS.getASC_FinalObracun().get(new OS_Search(new DateIntervalSearch(Od, Do), fsCode))) {
            System.err.println((++br) + " " + o.toString());
        }

        System.err.println("finalni obračun test.");

        int br1 = 0;

        for (Specifikacija o : DS.getASC_Specifikacija().get(new OS_Search(new DateIntervalSearch(Od, Do), fsCode))) {
            System.err.println((++br1) + " " + o.toString());
        }

    }
}
