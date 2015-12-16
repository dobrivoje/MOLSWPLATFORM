package test;

import db.retail.dataservice.DataService_RETAIL;
import db.retail.ent.Partner;

public class testCocaDB {

    private static final DataService_RETAIL DS = DataService_RETAIL.getDefault();

    public static void main(String[] args) {

        Partner p = DS.getASC_Partner_C().getAll().get(1);

        System.err.println("p: " + p);
        System.err.println(DS.getMD_Partner_C().getDetails(p));
    }
}
