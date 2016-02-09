package test;

import db.retail.DBHandler_RETAIL;
import db.retail.dataservice.DataService_RETAIL;
import db.retail.ent.FS;
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
public class testCocaDB {

    private static final DataService_RETAIL DS = DataService_RETAIL.getDefault();
    private static final DBHandler_RETAIL DBH = DBHandler_RETAIL.getDefault();

    private static final Map<String, List<String>> M = new LinkedHashMap<>();

    public static void main(String[] args) {

        System.err.println("finalni obračun test.");

        String Od = "2015-11-1";
        String Do = "2015-11-30";
        //String fsCode = "90111";

        /*
         IMDSearch<String, OS_Search> ic = DS.getMD_FS_Performace_C();
         ic.setCriteria(new OS_Search(new DateIntervalSearch(Od, Do), fsCode));
        
         System.err.println("test2 : getTree metod test");
         System.err.println(ic.getTree());
         System.err.println(ic.getTree().keySet());
         */
        for (FS f : DS.getASC_FS_C().getAll(false).stream().filter(p -> p.getCocaModel()).collect(Collectors.toList())) {
            System.err.println("FS: " + f);
            System.err.println("");

            IMDSearch<Object, OS_Search> ic2 = DS.getMD_FS_Performace_C3(Od, Do, f.getCode());

            for (Map.Entry<Object, List> E : ic2.getTree().entrySet()) {
                ReportDetails key = (ReportDetails) E.getKey();
                List value = E.getValue();

                System.err.println(key + " - " + value);
            }

            System.err.println("");
            System.err.println("");
        }
    }
}
