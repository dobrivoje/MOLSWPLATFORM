package RETAIL.Trees;

import com.vaadin.event.ItemClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import db.Exceptions.CustomTreeNodesEmptyException;
import db.retail.ent.FS;
import db.retail.ent.ReportDetails;
import db.retail.ent.criteria.DateIntervalSearch;
import db.retail.ent.criteria.FSSearch;
import db.retail.ent.criteria.OS_Search;
import db.retail.ent.reports.Obracun_FS_PerfDetaljno;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import static mf.MyUI.DS_RETAIL;
import org.superb.apps.utilities.vaadin.MyWindows.MyWindow;
import org.superb.apps.utilities.vaadin.Trees.CustomObjectTree;
import org.vaadin.highcharts.ChartType;
import org.vaadin.highcharts.HighChartGen;

/**
 *
 * @author Dobri
 */
public class Tree_R_FSPerformance extends CustomObjectTree<String> {

    public Tree_R_FSPerformance(OS_Search ossevent) throws CustomTreeNodesEmptyException, NullPointerException {
        super(new ArrayList(DS_RETAIL.getMD_FS_Performace_C(ossevent).getTree().keySet()));

        addItemClickListener((ItemClickEvent event) -> {
            if (event.isDoubleClick()) {
                OS_Search criteria = new OS_Search(new DateIntervalSearch(ossevent.getDateFrom(), ossevent.getDateTo()), ossevent.getFsCode());

                FS f = DS_RETAIL.getASC_FS_C().getByID(new FSSearch("", ossevent.getFsCode()));

                VerticalLayout VL = new VerticalLayout(createReport_FSDailyPerformance(
                        ChartType.AREA_SPLINE,
                        f.getNaziv() + ", " + f.getCode() + ", " + ossevent.getDateFrom() + " - " + ossevent.getDateTo(),
                        criteria)
                );
                VL.setSizeFull();
                VL.setMargin(true);

                UI.getCurrent().addWindow(new MyWindow("FS Daily Performace", VL, 60, 80));
            }
        });

    }

    @Override
    protected void createSubNodes(String rootNode) {
        createChildNodesForTheRoot(rootNode, DS_RETAIL.getMD_FS_Performace_C().getDetails(rootNode), true);
    }

    private Component createReport_FSDailyPerformance(ChartType chartType, String title, OS_Search criteria) {
        Map<ReportDetails, List> data = DS_RETAIL.getMD_FSPerformanceDetailed_C(criteria.getDateFrom(), criteria.getDateTo(), criteria.getFsCode()).getTree();

        List categories = data.keySet().stream().collect(Collectors.toList());
        List xAxisValues = ((List<Obracun_FS_PerfDetaljno>) data.get((ReportDetails) categories.get(0)))
                .stream().map(Obracun_FS_PerfDetaljno::getDan).collect(Collectors.toList());

        return new HighChartGen().generateHighChart(
                chartType,
                title,
                xAxisValues,
                createYAxisValues2(data)
        );
    }

    private Map<Object, List> createYAxisValues2(Map<ReportDetails, List> MM) {
        Map<Object, List> M = new HashMap<>();

        for (Map.Entry<ReportDetails, List> E : MM.entrySet()) {
            ReportDetails category = E.getKey();
            List<Obracun_FS_PerfDetaljno> categoryValues = E.getValue();

            M.put(category, categoryValues.stream().map(Obracun_FS_PerfDetaljno::getOstvarenje).collect(Collectors.toList()));
        }

        return M;
    }

}
