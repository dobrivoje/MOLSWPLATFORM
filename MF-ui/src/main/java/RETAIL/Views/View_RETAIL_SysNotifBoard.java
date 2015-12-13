package RETAIL.Views;

import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
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
import org.superb.apps.utilities.vaadin.Views.View_Dashboard;

public class View_RETAIL_SysNotifBoard extends View_Dashboard {

    public View_RETAIL_SysNotifBoard() {
        super("Retail Dashboard");

        List<Object> L = new ArrayList<>();
        int K = 1000;
        for (int i = 0; i < K; i++) {
            L.add(i);
        }
        buildContentWithComponents(
                createReport(ChartType.BAR, "t1", Arrays.asList("Stanko", "Dobri", "Nenad")),
                createReport(ChartType.AREA, "t3", Arrays.asList("Stanko", "Dobri", "Nenad")),
                createReport(ChartType.AREA_RANGE, "xxx", Arrays.asList("Stanko", "Dobri", "Nenad", "Stanko", "Dobri", "Nenad", "Stanko", "Dobri", "Nenad", "Stanko", "Dobri", "Nenad", "Stanko", "Dobri", "Nenad", "Stanko", "Dobri", "Nenad", "Stanko", "Dobri", "Nenad", "Stanko", "Dobri", "Nenad", "Stanko", "Dobri", "Nenad", "Stanko", "Dobri", "Nenad", "Stanko", "Dobri", "Nenad", "Stanko", "Dobri", "Nenad", "Stanko", "Dobri", "Nenad", "Stanko", "Dobri", "Nenad", "Stanko", "Dobri", "Nenad", "Stanko", "Dobri", "Nenad", "Stanko", "Dobri", "Nenad", "Stanko", "Dobri", "Nenad", "Stanko", "Dobri", "Nenad", "Stanko", "Dobri", "Nenad", "Stanko", "Dobri", "Nenad", "Stanko", "Dobri", "Nenad", "Stanko", "Dobri", "Nenad", "Stanko", "Dobri", "Nenad", "Stanko", "Dobri", "Nenad", "Stanko", "Dobri", "Nenad", "Stanko", "Dobri", "Nenad", "Stanko", "Dobri", "Nenad", "Stanko", "Dobri", "Nenad")),
                createZoomableReport(ChartType.LINE_TIMESERIES_ZUMABLE, "", L),
                createReport(ChartType.STACKED_BAR, "AJTI", Arrays.asList("Иштван", "Арпад", "Добривоје"))
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

    private Map<Object, List<Object>> initData(List<Object> categories) {
        Map<Object, List<Object>> M = new HashMap<>();

        List<Object> tmpRandomList;

        for (Object G : Arrays.asList("Радица", "Violeta", "Живка", "Стамена", "Nikolina")) {
            tmpRandomList = new ArrayList<>();

            for (int j = 0; j < categories.size(); j++) {
                tmpRandomList.add((int) (1 + 3 * Math.random()));
            }

            M.put(G, tmpRandomList);
        }

        return M;
    }

    private Map<Object, List<Object>> initZoomableData(List<Object> categories) {
        Map<Object, List<Object>> M = new HashMap<>();

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

    private Component createReport(ChartType chartType, String title, List<Object> procurators) {
        Component c1 = new HighChartGen().generateHighChart(
                chartType,
                title,
                initData(procurators),
                procurators
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

    private Component createZoomableReport(ChartType chartType, String title, List<Object> data) {
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
