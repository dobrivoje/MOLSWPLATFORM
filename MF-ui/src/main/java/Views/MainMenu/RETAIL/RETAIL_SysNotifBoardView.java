package Views.MainMenu.RETAIL;

import Views.DashboardView;
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

public class RETAIL_SysNotifBoardView extends DashboardView {

    public RETAIL_SysNotifBoardView() {
        super("Retail Notifications Board");

        buildContentWithComponents(
                createReport1(),
                createReport2()
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

    private Map<String, List<Object>> initData(List<String> categories) {
        Map<String, List<Object>> M = new HashMap<>();

        List<Object> tmpRandomList;

        for (String G : Arrays.asList("Радица", "Violeta", "Живка", "Стамена", "Nikolina")) {
            tmpRandomList = new ArrayList<>();

            for (int j = 0; j < categories.size(); j++) {
                tmpRandomList.add((int) (1 + 3 * Math.random()));
            }

            M.put(G, tmpRandomList);
        }

        return M;
    }

    private Component createReport1() {
        HighChartGen hcg = new HighChartGen();
        List<String> k = Arrays.asList("Radiša", "Ljubo", "Иштван");
        Component c1 = hcg.generateHighChart3(
                ChartType.STACKED_BAR,
                "Ukupan broj akcija u selu",
                initData(k),
                k
        );

        subPanels.add(new Panel("Total F..k Village Actions", c1));

        VerticalLayout VL = new VerticalLayout();
        VL.setSizeFull();
        VL.setMargin(true);
        VL.setSpacing(true);

        for (Component c : subPanels) {
            VL.addComponents(c);
        }

        return VL;
    }

    private Component createReport2() {
        HighChartGen hcg = new HighChartGen();
        List<String> k = Arrays.asList("Stanko", "Dobri", "Nenad");
        Component c1 = hcg.generateHighChart3(
                ChartType.STACKED_BAR,
                "Ukupan broj akcija u selu",
                initData(k),
                k
        );

        subPanels.add(new Panel("Total F..k Village Actions", c1));

        VerticalLayout VL = new VerticalLayout();
        VL.setSizeFull();
        VL.setMargin(true);
        VL.setSpacing(true);

        for (Component c : subPanels) {
            VL.addComponents(c);
        }

        return VL;
    }

}
