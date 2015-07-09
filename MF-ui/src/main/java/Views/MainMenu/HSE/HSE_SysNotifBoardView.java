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
import org.vaadin.highcharts.HighChartGen;
import org.vaadin.highcharts.ChartType;

public class HSE_SysNotifBoardView extends VerticalLayout implements View {

    private final VerticalLayout VL = new VerticalLayout();
    private final Map<String, List<Float>> M = new HashMap<>();
    private final HighChartGen hcg;

    // private final HSE_SysNotifTable hseSysNotifTable = new HSE_SysNotifTable();
    public HSE_SysNotifBoardView() {
        hcg = new HighChartGen();

        addStyleName("crud-view");

        VL.setSizeUndefined();
        VL.setMargin(true);
        VL.setSpacing(true);
        VL.setStyleName("crud-main-layout");

        VL.addComponent(createTopBar());
        initData();
        Component c1 = hcg.generateHighChart(ChartType.LINE, "Upoređenje1", M);
        VL.addComponent(c1);
        VL.addComponent(hcg.generateTEST222());
        
        VL.setExpandRatio(c1, 1);

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
        List<Float> tmpRandomList;

        for (int i = 0; i < 5; i++) {
            tmpRandomList = new ArrayList<>();

            for (int j = 0; j < 5; j++) {
                tmpRandomList.add((float) (10 + 100 * Math.random()));
            }
            M.put("serija-" + i, tmpRandomList);
        }
    }

}
