package RETAIL.Trees;

import com.vaadin.event.ItemClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.VerticalLayout;
import db.Exceptions.CustomTreeNodesEmptyException;
import db.retail.ent.FS;
import db.retail.ent.ReportDetails;
import db.retail.ent.criteria.DateIntervalSearch;
import db.retail.ent.criteria.FSSearch;
import db.retail.ent.criteria.OS_Search;
import db.retail.ent.reports.Obracun_FS_PerfDetaljno;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import static Main.MyUI.DS_RETAIL;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Panel;
import org.dobrivoje.utils.colors.PastelColorGenerator;
import org.superb.apps.utilities.vaadin.MyWindows.MyWindow;
import org.superb.apps.utilities.vaadin.Trees.CustomObjectTree;
import org.vaadin.highcharts.ChartType;
import org.vaadin.highcharts.HighChartGen;

/**
 *
 * @author Dobri
 */
public class Tree_R_FSPerformance extends CustomObjectTree<ReportDetails> {

    public Tree_R_FSPerformance(OS_Search ossevent) throws CustomTreeNodesEmptyException, NullPointerException {
        this(ossevent, false);
    }

    public Tree_R_FSPerformance(OS_Search ossevent, boolean expandRootNodes) throws CustomTreeNodesEmptyException, NullPointerException {
        super(new ArrayList(DS_RETAIL.getMD_FS_Performace_C2(ossevent).getTree().keySet()), expandRootNodes);

        //<editor-fold defaultstate="collapsed" desc="addItemClickListener">
        addItemClickListener((ItemClickEvent event) -> {
            OS_Search criteria = new OS_Search(new DateIntervalSearch(ossevent.getDateFrom(), ossevent.getDateTo()), ossevent.getFsCode());
            FS f = DS_RETAIL.getASC_FS_C().getByID(new FSSearch("", ossevent.getFsCode()));

            List<ReportDetails> rd = null;

            if (event.isDoubleClick()) {
                if (event.getItemId() instanceof ReportDetails) {
                    if (event.isCtrlKey()) {
                        rd = DS_RETAIL.getASC_ReportDetails_C().getAll(false);
                    } else {
                        rd = Arrays.asList((ReportDetails) event.getItemId());
                    }
                }
            }

            if (rd != null) {
                getUI().addWindow(
                        new MyWindow(
                                "FS Daily Performace",
                                createReport_FSDailyPerformance(
                                        ChartType.AREA_SPLINE,
                                        f.getNaziv() + ", " + f.getCode() + ", " + ossevent.getDateFrom() + " - " + ossevent.getDateTo(),
                                        criteria,
                                        rd
                                ),
                                68, 55, Unit.PERCENTAGE
                        )
                );
            }
        }
        );
        //</editor-fold>
    }

    @Override
    protected void createSubNodes(ReportDetails rootNode) {
        createChildNodesForTheRoot(rootNode, DS_RETAIL.getMD_FS_Performace_C2().getDetails(rootNode), expandRootNodes);
    }

    //<editor-fold defaultstate="collapsed" desc="report methods">
    private Layout createReport_FSDailyPerformance(ChartType chartType, String title, OS_Search criteria, List<ReportDetails> categories) {
        Map<ReportDetails, List> data = DS_RETAIL.getMD_FSPerformanceDetailed_C(criteria.getDateFrom(), criteria.getDateTo(), criteria.getFsCode()).getTree();
        Map<ReportDetails, List> d = new LinkedHashMap<>();

        List xAxisValues = ((List<Obracun_FS_PerfDetaljno>) data.get(categories.get(0)))
                .stream().map(Obracun_FS_PerfDetaljno::getDan).collect(Collectors.toList());

        for (ReportDetails cc : categories) {
            if (data.containsKey(cc)) {
                d.put(cc, data.get(cc));
            }
        }

        Component c;
        VerticalLayout vle = new VerticalLayout();
        vle.setSizeFull();
        vle.setMargin(true);

        try {
            c = new HighChartGen().generateHighChart(
                    chartType,
                    title,
                    xAxisValues,
                    createYAxis_KategorijaDanOstvarenje(d),
                    new PastelColorGenerator(0.85f)
            );

        } catch (Exception ex) {
            c = new Panel("No results for the selected period !");
        }

        vle.addComponent(c);
        // vle.setExpandRatio(c, 1.0f);

        return vle;
    }

    private Map<Object, List> createYAxis_KategorijaDanOstvarenje(Map<ReportDetails, List> MM) {
        Map<Object, List> M = new HashMap<>();

        for (Map.Entry<ReportDetails, List> E : MM.entrySet()) {
            ReportDetails category = E.getKey();
            List<Obracun_FS_PerfDetaljno> categoryValues = E.getValue();

            M.put(category, categoryValues.stream().map(Obracun_FS_PerfDetaljno::getOstvarenje).collect(Collectors.toList()));
        }

        return M;
    }
    //</editor-fold>
}
