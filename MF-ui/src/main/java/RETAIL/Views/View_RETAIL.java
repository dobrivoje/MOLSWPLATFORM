package RETAIL.Views;

import Menu.MenuDefinitions;
import static Menu.MenuDefinitions.RETAIL_COCACALC;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import java.util.ArrayList;
import java.util.List;
import org.superb.apps.utilities.vaadin.Views.View_Dashboard;

public class View_RETAIL extends View_Dashboard {

    public View_RETAIL() {
        super(RETAIL_COCACALC.toString());
        createCocaCalcOptions();
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

    private void createCocaCalcOptions() {
        List<Component> C = new ArrayList();

        MenuDefinitions.get_RETAIL_COCA_SubItems().stream().forEach((md) -> {
            C.add(createPanelComponent(md.toString(), subPanels, true));
        });

        buildContentWithComponents(C);
    }
}
