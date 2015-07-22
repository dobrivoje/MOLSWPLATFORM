package Views.MainMenu.HSE;

import Views.View_Dashboard;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
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

public class View_HSE_SysNotifBoard extends View_Dashboard {

    // private final HSE_SysNotifTable hseSysNotifTable = new HSE_SysNotifTable();
    public View_HSE_SysNotifBoard() {
        super("HSE Notifications Board");

        createTopBar();
        buildContentWithComponents(
                createReport(ChartType.LINE, "Report", "Preševo", "Horgoš 1", "Požega"),
                createReport(ChartType.SPLINE, "Report", "Preševo", "Horgoš 1", "Požega"),
                createReport(ChartType.BAR, "Report", "Preševo", "Horgoš 1", "Požega"),
                createReport(ChartType.SPLINE, "Report", "Preševo", "Horgoš 1", "Požega"),
                createReport(ChartType.PIE_GRADIENT, "Report", "Preševo", "Horgoš 1", "Požega"),
                createReport(ChartType.AREA, "Report", "Preševo", "Horgoš 1", "Požega"),
                createReport(ChartType.AREA_SPLINE, "Report", "Preševo", "Horgoš 1", "Požega")
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

        for (Object G : Arrays.asList("BMB95", "BMB95 EVO", "ED", "EVOD", "LPG")) {
            tmpRandomList = new ArrayList<>();

            for (int j = 0; j < categories.size(); j++) {
                tmpRandomList.add((float) (10 + 17000 * Math.random()));
            }
            M.put(G, tmpRandomList);
        }

        return M;
    }

    private Component createReport(ChartType chartType, String title, Object... procurators) {
        List<Object> k = Arrays.asList(procurators);
        Component c1 = new HighChartGen().generateHighChart(
                chartType,
                title,
                initData(k),
                k
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

}
