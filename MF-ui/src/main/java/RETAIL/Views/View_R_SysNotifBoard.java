package RETAIL.Views;

import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import db.retail.ent.ReportDetails;
import db.retail.ent.criteria.DateIntervalSearch;
import db.retail.ent.criteria.NameIDLogicSearch;
import db.retail.ent.criteria.OS_Search;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.vaadin.highcharts.HighChartGen;
import org.vaadin.highcharts.ChartType;
import static java.lang.Math.sin;
import static java.lang.Math.PI;
import static java.lang.Math.exp;
import java.util.stream.Collectors;
import static mf.MyUI.DS_RETAIL;
import org.superb.apps.utilities.vaadin.Views.View_Dashboard;

public class View_R_SysNotifBoard extends View_Dashboard {

    public View_R_SysNotifBoard() {
        super("Retail Dashboard");

        List<Object> L = new ArrayList<>();
        int K = 1000;
        for (int i = 0; i < K; i++) {
            L.add(i);
        }

        List reportsList = DS_RETAIL.getAS_ReportDetails_C().get(new NameIDLogicSearch("", true, -1));
        // List cats = Arrays.asList("Радица", "Violeta", "Живка", "Стамена", "Nikolina");
        /*
         List cats = DS_RETAIL.getASC_FS_C().get(
         new FSSearch(null, null, null, true)
         ).subList(0, 3);
         */
        List cats = DS_RETAIL.getASC_FS_C().getAll(false)
                .stream().filter(p -> p.getCocaModel().equals(true)).collect(Collectors.toList())
                .subList(0, 4);

        buildContentWithComponents(
                //createReportFSPerformaceDetailed(ChartType.SPLINE, "FS Performace During Time", reportsList, "2015-11-1", "2015-11-30", "90431"),
                createReport(ChartType.BAR, "t1", Arrays.asList("Stanko", "Dobri", "Nenad"), cats),
                createReport(ChartType.BAR, "t2", reportsList, cats),
                createReport(ChartType.AREA, "t3", Arrays.asList("Stanko", "Dobri", "Nenad"), cats),
                createReport(ChartType.AREA_RANGE, "xxx", Arrays.asList("Stanko", "Dobri", "Nenad", "Stanko", "Dobri", "Nenad"), cats),
                createZoomableReport(ChartType.LINE_TIMESERIES_ZUMABLE, "", L),
                createReport(ChartType.STACKED_COLUMN, "AJTI", Arrays.asList("Иштван", "Арпад", "Добривоје"), cats)
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

    private Map<Object, List> generateData(List xAxisValues, List categories) {
        Map<Object, List> M = new HashMap<>();

        List tmpRandomList;

        for (Object G : categories) {
            tmpRandomList = new ArrayList<>();

            for (Object xV : xAxisValues) {
                tmpRandomList.add((1 + (int) (3 * Math.random())));
            }

            M.put(G, tmpRandomList);
        }

        return M;
    }

    private Component createReport(ChartType chartType, String title, List xAxisValues, List categories) {
        Component c1 = new HighChartGen().generateHighChart(
                chartType,
                title,
                generateData(xAxisValues, categories),
                xAxisValues
        );

        subPanels.add(new Panel(title, c1));

        VerticalLayout VL = new VerticalLayout();
        VL.setSizeUndefined();
        VL.setMargin(true);
        VL.setSpacing(true);

        for (Component c : subPanels) {
            VL.addComponents(c);
        }

        return VL;
    }

    private Component createReportFSPerformaceDetailed(ChartType chartType, String title, String from, String to, String fsCode) {
        Map<ReportDetails, List> M = DS_RETAIL.getMD_FSPerformanceDetailed_C(
                new OS_Search(new DateIntervalSearch(from, to), fsCode)
        ).getTree();

        List<ReportDetails> categories = Arrays.asList(DS_RETAIL.getAS_ReportDetails_C().getAll(false).get(0));
        List xAxisValues = M.get(0);

        for (Map.Entry<ReportDetails, List> E : M.entrySet()
                .stream()
                .filter((Map.Entry<ReportDetails, List> p) -> p.getKey().getIdrd().equals(1))
                .collect(Collectors.toList())) {
            Object cat = E.getKey();
            List values = E.getValue();
        }

        Component c1 = new HighChartGen().generateHighChart(
                chartType,
                title,
                generateData(xAxisValues, categories),
                xAxisValues
        );

        subPanels.add(new Panel(title, c1));

        VerticalLayout VL = new VerticalLayout();
        VL.setSizeUndefined();
        VL.setMargin(true);
        VL.setSpacing(true);

        for (Component c : subPanels) {
            VL.addComponents(c);
        }

        return VL;
    }

    private Map<Object, List> initZoomableData(List<Object> categories) {
        Map<Object, List> M = new HashMap<>();

        List<Object> tmpRandomList;
        int K = 1000;

        for (Object G : Arrays.asList("BMB95", "BMB EVO", "LPG", "DSL", "DSL EVO")) {
            tmpRandomList = new ArrayList<>();

            for (int j = 0; j < categories.size(); j++) {
                double D = (10000 * (j * sin(2 * (1000 - j) * PI) * exp((5 * j) / (j + 1) / K)) * Math.random());
                tmpRandomList.add(D);
            }

            M.put(G, tmpRandomList);
        }

        return M;
    }

    private Component createZoomableReport(ChartType chartType, String title, List data) {
        Component c1 = new HighChartGen().generateHighChart(
                chartType,
                title,
                initZoomableData(data),
                data
        );

        subPanels.add(new Panel(title, c1));

        VerticalLayout VL = new VerticalLayout();
        VL.setMargin(true);
        VL.setSpacing(true);

        for (Component c : subPanels) {
            VL.addComponents(c);
        }

        return VL;
    }

}
