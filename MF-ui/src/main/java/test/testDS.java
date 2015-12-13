package test;

import db.retail.beans.criteria.DateIntervalSearch;
import db.retail.beans.criteria.FSSearch;
import db.retail.beans.criteria.OS_Search;
import db.retail.beans.reports.ObracunFinal;
import db.retail.beans.reports.Specifikacija;
import db.retail.dataservice.DataService_RETAIL;
import db.retail.ent.FS;

public class testDS {

    private static final DataService_RETAIL DSR = DataService_RETAIL.getDefault();

    public static void main(String[] args) {

        System.err.println("Final Obračun");
        for (ObracunFinal o : DSR.getASC_FinalObracun_C().get(new OS_Search(new DateIntervalSearch("2015-11-1", "2015-11-30"), null))) {
            System.err.println(o);
        }

        FS fs = DSR.getASC_FS_C().getByID(new FSSearch(null, "90431"));
        System.err.println("Specifikacija za stanicu : " + fs.getNaziv());
        for (Specifikacija o : DSR.getASC_Specifikacija_C().get(new OS_Search(new DateIntervalSearch("2015-11-1", "2015-11-30"), fs.getCode()))) {
            System.err.println(o);
        }
    }
}
