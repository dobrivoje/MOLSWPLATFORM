package Views.MainMenu.HSE;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.vaadin.highcharts.HighChart;

public class HSE_SysNotifBoardView extends VerticalLayout implements View {

    public static final String VIEW_NAME = "HSE System Notifications";
    private final VerticalLayout VL = new VerticalLayout();
    private Map<String, List<Integer>> M = new HashMap<>();

    // private final HSE_SysNotifTable hseSysNotifTable = new HSE_SysNotifTable();
    public HSE_SysNotifBoardView() {
        initData();

        addStyleName("crud-view");

        VL.setWidth(66, Unit.PERCENTAGE);
        VL.setHeight(66, Unit.PERCENTAGE);
        VL.setMargin(true);
        VL.setSpacing(true);
        VL.setStyleName("crud-main-layout");

        VL.addComponent(createTopBar());
        Component c1 = generateHighChart("Upoređenje1", M);
        VL.addComponent(c1);
        initData();
        Component c2 = generateHighChart("Upoređenje2", M);
        VL.addComponent(c2);

        VL.setExpandRatio(c1, 1);
        VL.setExpandRatio(c2, 1);

        addComponent(VL);
        setExpandRatio(VL, 1);
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

    private void initData() {
        List<Integer> tmpRandomList;

        for (int i = 0; i < 5; i++) {
            tmpRandomList = new ArrayList<>();

            for (int j = 0; j < 5; j++) {
                tmpRandomList.add((int) (10 + 100 * Math.random()));
            }
            M.put("serija-" + i, tmpRandomList);
        }
    }

    private Component generateHighChart(String title, Map<String, List<Integer>> series) {
        HighChart chart = new HighChart();
        String serija = new String();

        /*
         chart.setHcjs("var options = { "
         + "title: {text: '" + title + "' }, "
         + "series: "
         + "[ "
         + " { name: '" + "s1" + "', data: " + Arrays.asList(1, 3, 2, 4, 3, 7) + "} , "
         + " { name: '" + "ss2" + "', data: " + Arrays.asList(4, 3, 7, 2, 5, 8) + "} , "
         + " { name: '" + "ss3" + "', data: " + Arrays.asList(2, 5, 8, 1, 3, 2) + "} , "
         + " ]};"
         );
         */
        String header = "var options = { "
                + "title: {text: '" + title + "' }, "
                + "chart: {type: 'spline'}, "
                + "tooltip: { headerFormat: '<b>{series.name}</b><br>', "
                + "pointFormat: '{point.x:%e. %b}: {point.y:.2f} m'}, "
                + "plotOptions: { spline: { marker: { enabled: true } } }, "
                + "series: "
                + "[ ";

        for (Map.Entry<String, List<Integer>> es : series.entrySet()) {
            String key = es.getKey();
            List<Integer> value = es.getValue();

            serija += " { name: '" + key + "', data: " + value + "} , ";
        }

        String tail = " ]};";

        chart.setHcjs(header + serija + tail);
        return chart;
    }

}
