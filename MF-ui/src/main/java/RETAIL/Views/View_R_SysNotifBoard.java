package RETAIL.Views;

import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import db.retail.ent.FS;
import db.retail.ent.ReportDetails;
import db.retail.ent.criteria.DateIntervalSearch;
import db.retail.ent.criteria.NameIDLogicSearch;
import db.retail.ent.criteria.OS_Search;
import db.retail.ent.reports.Obracun_FS_PerfDetaljno;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.vaadin.highcharts.HighChartGen;
import org.vaadin.highcharts.ChartType;
import java.util.stream.Collectors;
import static Main.MyUI.DS_RETAIL;
import com.vaadin.ui.Alignment;
import java.util.LinkedHashMap;
import org.dobrivoje.utils.colors.PastelColorGenerator;
import org.superb.apps.utilities.datum.Dates;
import org.superb.apps.utilities.vaadin.Views.View_Dashboard;

public class View_R_SysNotifBoard extends View_Dashboard {

    public View_R_SysNotifBoard() {
        super("Retail Dashboard");

        List reportNames = DS_RETAIL.getAS_ReportDetails_C().get(new NameIDLogicSearch(null, true, -1));
        List<String> fsNames = DS_RETAIL.getASC_FS_C().getAll(false).subList(0, 4)
                .stream().map(FS::getNaziv).collect(Collectors.toList());

        Dates dates = new Dates(-3, true);
        OS_Search criteria = new OS_Search(new DateIntervalSearch(dates.getFromStr(), dates.getToStr()), "90431");

        buildContentWithComponents(
                createReport_FSDailyPerformance(ChartType.AREA_SPLINE, "FS Daily Performances", criteria),
                createReport(ChartType.STACKED_BAR, "Report", reportNames, fsNames),
                createReport(ChartType.SPLINE, "Report", reportNames, fsNames),
                createReport(ChartType.BAR, "Fuel Consumption", reportNames, Arrays.asList("Premium", "EVO")),
                createReport(ChartType.AREA_SPLINE, "Report", reportNames, fsNames)
        );
    }

    @Override
    public void enter(ViewChangeEvent event) {
    }

    //<editor-fold defaultstate="collapsed" desc="createTopBar">
    public final HorizontalLayout createTopBar() {

        HorizontalLayout topLayout = new HorizontalLayout();
        topLayout.setSpacing(true);
        topLayout.setWidth(100, Unit.PERCENTAGE);
        topLayout.setStyleName("top-bar");

        return topLayout;
    }
    //</editor-fold>

    private Map<Object, List> createYAxisValues(List xAxisValues, List categories) {
        Map<Object, List> M = new HashMap<>();

        List categoryValues;

        for (Object category : categories) {
            categoryValues = new ArrayList<>();

            for (Object xV : xAxisValues) {
                categoryValues.add((float) (10 + 17000 * Math.random()));
            }
            M.put(category, categoryValues);
        }

        return M;
    }

    private Component createReport(ChartType chartType, String title, List categories, List xAxisValues) {
        Component c1;
        String panelMessage;
        Panel p;

        try {
            panelMessage = title;

            c1 = new HighChartGen().generateHighChart(
                    chartType,
                    title,
                    xAxisValues,
                    createYAxisValues(xAxisValues, categories),
                    new PastelColorGenerator(0.8f)
            );

            p = new Panel(panelMessage, c1);

        } catch (Exception ex) {
            panelMessage = "No results for the " + title + ", for selected period !";
            p = new Panel(panelMessage);
        }

        VerticalLayout vle = new VerticalLayout(p);
        vle.setMargin(true);
        vle.setSpacing(true);
        vle.setComponentAlignment(p, Alignment.MIDDLE_CENTER);

        return vle;
    }

    private Component createReport(ChartType chartType, String title, List xAxisValues, Map<Object, List> yAxisValues) {
        Component c1;
        String panelMessage;
        Panel p;

        try {
            panelMessage = title;

            c1 = new HighChartGen().generateHighChart(
                    chartType,
                    title,
                    xAxisValues,
                    yAxisValues,
                    new PastelColorGenerator(0.8f)
            );

            p = new Panel(panelMessage, c1);

        } catch (Exception ex) {
            panelMessage = "No results for the " + title + ", for selected period !";
            p = new Panel(panelMessage);
        }

        VerticalLayout vle = new VerticalLayout(p);
        vle.setMargin(true);
        vle.setSpacing(true);
        vle.setComponentAlignment(p, Alignment.MIDDLE_CENTER);

        return vle;
    }

    private Component createReport_FSDailyPerformance(ChartType chartType, String title, OS_Search criteria) {
        Map<ReportDetails, List> data = DS_RETAIL.getMD_FSPerformanceDetailed_C(criteria.getDateFrom(), criteria.getDateTo(), criteria.getFsCode()).getTree();
        Map<Object, List> YAxisValues = new LinkedHashMap<>();

        // Set of ReportDetails -> Set of Object :
        List keys = data.keySet().stream().collect(Collectors.toList());

        // List of days :
        List xAxisValues = ((List<Obracun_FS_PerfDetaljno>) data.get((ReportDetails) keys.get(0)))
                .stream().map(Obracun_FS_PerfDetaljno::getDan).collect(Collectors.toList());

        for (Map.Entry<ReportDetails, List> E : data.entrySet()) {
            ReportDetails key = E.getKey();
            List value = E.getValue();

            YAxisValues.put(key, value);
        }

        return createReport(chartType, title, xAxisValues, YAxisValues);

    }

}
