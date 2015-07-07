package Views.MainMenu.HSE;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;
import org.vaadin.highcharts.HighChart;

public class HSE_SysNotifBoardView extends VerticalLayout implements View {

    public static final String VIEW_NAME = "HSE System Notifications";

    private final VerticalLayout VL = new VerticalLayout();

    // private final HSE_SysNotifTable hseSysNotifTable = new HSE_SysNotifTable();
    public HSE_SysNotifBoardView() {
        setSizeFull();
        // addStyleName("crud-view");

        VL.setSizeFull();
        VL.setMargin(true);
        VL.setSpacing(true);

        VL.addComponent(createTopBar());
        Component c = generateHighChart("neki naslov", "ser1", "ser2");
        VL.addComponent(c);
        VL.setStyleName("crud-main-layout");
        VL.setExpandRatio(c, 1);
        addComponent(VL);

        setExpandRatio(VL, 1);
    }
    //<editor-fold defaultstate="collapsed" desc="Customer Table - Double click - Customer Form">

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

    private Component generateHighChart() {
        HighChart chart = new HighChart();
        //chart.setHcjs("var options = { title: { text: 'test diagram' }, series: [{ name: 's1', data: [1, 3, 2]}] };");

        return chart;
    }

    private Component generateHighChart(String title, String... series) {
        HighChart chart = new HighChart();
        chart.setHcjs("var options = { title: { text: '" + title + "' }, series: [{ name: 's1', data: [1, 3, 2]}] };");

        String serija = "";
        for (String s : series) {
            serija += "{ name: '" + s + "', data: [" + 1 + ", " + 2 + ", " + 3 + "]}, ";
        }

        serija.substring(0, serija.length() - 2);

        return chart;
    }

}
