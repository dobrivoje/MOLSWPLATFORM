package test;

import db.retail.dataservice.DataService_RETAIL;
import db.retail.ent.criteria.DateIntervalSearch;
import db.retail.ent.criteria.FSSearch;
import db.retail.ent.criteria.OS_Search;
import db.retail.ent.reports.ObracunFinal;
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

        int br = 0;
        String Od = "2015-11-1";
        String Do = "2015-11-30";
        String fsCode = "90111";
        //String fsCode = null;

        for (ObracunFinal o : DS.getASC_FinalObracun_C().get(new OS_Search(new DateIntervalSearch(Od, Do), fsCode))) {
            System.err.println((++br) + " " + o.getCalcToString());
        }

        /*
         for (ObracunFinal o : DS.getASC_FinalObracun_C().get(new OS_Search(new DateIntervalSearch(Od, Do), fsCode))) {
         if (!M.containsKey(o.getReportName())) {
         M.put(
         o.getReportName(),
         new LinkedList<>(Arrays.asList(o.getKoefNaziv() + ", " + new DecimalFormat("0.000").format(o.getOstvarenje())))
         );
         } else {
         M.get(o.getReportName()).add(
         o.getKoefNaziv()
         + ", " + new DecimalFormat("0.000").format(o.getOstvarenje())
         );
         }
         }
         */
        System.err.println("test3 : map");
        System.err.println("fs : " + DS.getASC_FS_C().getByID(new FSSearch(null, fsCode)));

        for (Map.Entry<String, List<String>> entrySet : ObracunFinal.generate(fsCode, Do, Do, DS.getASC_FinalObracun_C()).entrySet()) {
            String key = entrySet.getKey();
            List<String> value = entrySet.getValue();

            System.err.println("* " + key);
            System.err.println("   |___" + (value));
        }

        /*
         System.err.println("test4 : map");
        
         for (Map.Entry<String, Double> entrySet : ObracunFinal.generate(fsCode, Do, Do).entrySet()) {
         String key = entrySet.getKey();
         Double value = entrySet.getValue();
        
         System.err.println("* " + key);
         System.err.println("   |___" + new DecimalFormat("0.000").format(value));
         }
         */
    }
}
