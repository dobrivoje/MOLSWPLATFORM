package HSE.Views;

import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import db.retail.ent.FS;
import db.retail.ent.criteria.NameIDLogicSearch;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static mf.MyUI.DS_RETAIL;
import org.superb.apps.utilities.vaadin.Views.View_Dashboard;
import org.vaadin.highcharts.HighChartGen;
import org.vaadin.highcharts.ChartType;

public class View_HSE_SysNotifBoard extends View_Dashboard {

    // private final HSE_SysNotifTable hseSysNotifTable = new HSE_SysNotifTable();
    public View_HSE_SysNotifBoard() {
        super("HSE Notifications Board");

        createTopBar();

        List reportNames = DS_RETAIL.getAS_ReportDetails_C().get(new NameIDLogicSearch(null, true, -1));
        
        List<FS> fs = DS_RETAIL.getASC_FS_C().getAll(false).subList(0, 4);
        List<String> fsNames = new ArrayList();
        fs.stream().forEach((f) -> {
            fsNames.add(f.getNaziv());
        });

        buildContentWithComponents(
                createReport(ChartType.STACKED_BAR, "Report", reportNames, fsNames),
                createReport(ChartType.SPLINE, "Report", reportNames, fsNames),
                createReport(ChartType.BAR, "Fuel Consumption", reportNames, Arrays.asList("Premium", "EVO")),
                createReport(ChartType.AREA_SPLINE, "Report", reportNames, fsNames),
                createReport(ChartType.DONUT_3D, "Report", reportNames, fsNames),
                createReport(ChartType.AREA, "Report", reportNames, fsNames)
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
        Component c1 = new HighChartGen().generateHighChart(
                chartType,
                title,
                createYAxisValues(xAxisValues, categories),
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

}
