package test;

import db.retail.dataservice.DataService_RETAIL;
import db.retail.ent.criteria.DateIntervalSearch;
import db.retail.ent.criteria.FSSearch;
import db.retail.ent.criteria.OS_Search;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Dobri
 */
public class testCocaDB {

    private static final DataService_RETAIL DS = DataService_RETAIL.getDefault();

    private static final Map<String, List<String>> M = new LinkedHashMap<>();

    public static void main(String[] args) {

        System.err.println("finalni obračun test.");

        String Od = "2015-11-1";
        String Do = "2015-11-30";
        String fsCode = "90111";
        //String fsCode = null;

        System.err.println("test3 : map");
        System.err.println("fs : " + DS.getASC_FS_C().getByID(new FSSearch(null, fsCode)));

        for (Map.Entry<String, List> entrySet : DataService_RETAIL.getDefault().getMD_FS_Performace_C2().getMasterDetail(
                new OS_Search(new DateIntervalSearch(Od, Do), fsCode)).entrySet()) {
            String key = entrySet.getKey();
            Object value = entrySet.getValue();

            System.err.println("key: " + key);
            System.err.println("value: " + value);
        }

    }
}
