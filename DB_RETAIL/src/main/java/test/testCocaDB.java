package test;

import db.retail.DBHandler_RETAIL;
import db.retail.dataservice.DataService_RETAIL;
import db.retail.ent.criteria.DateIntervalSearch;
import db.retail.ent.criteria.OS_Search;
import db.retail.interfaces.IMDSearch;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Dobri
 */
public class testCocaDB {

    private static final DataService_RETAIL DS = DataService_RETAIL.getDefault();
    private static final DBHandler_RETAIL DBH = DBHandler_RETAIL.getDefault();

    private static final Map<String, List<String>> M = new LinkedHashMap<>();

    public static void main(String[] args) {

        System.err.println("finalni obračun test.");

        String Od = "2015-11-1";
        String Do = "2015-11-30";
        //String fsCode = "90111";
        String fsCode = null;

        /*
         IMDSearch<String, OS_Search> ic = DS.getMD_FS_Performace_C();
         ic.setCriteria(new OS_Search(new DateIntervalSearch(Od, Do), fsCode));
        
         System.err.println("test2 : getTree metod test");
         System.err.println(ic.getTree());
         System.err.println(ic.getTree().keySet());
         */
        IMDSearch<String, OS_Search> ic = DS.getMD_FS_Performace_C();
        ic.setCriteria(new OS_Search(new DateIntervalSearch(Od, Do), fsCode));

        for (Map.Entry<String, List> E : ic.getTree().entrySet()) {
            System.err.println(E.getKey() + " -> " + E.getValue());
        }

    }
}
