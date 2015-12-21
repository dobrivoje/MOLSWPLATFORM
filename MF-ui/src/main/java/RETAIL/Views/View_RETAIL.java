package RETAIL.Views;

import MainMenu.MenuDefinitions;
import static MainMenu.MenuDefinitions.RETAIL_COCACALC;
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

    /*
     private void createCocaCalcOptions() {
     List<Component> C = new ArrayList();

     try {
     List<Panel> LP = new ArrayList<>();
     Panel p1 = new Panel(new Tree_R_PartnerUgovor("", MyUI.DS_RETAIL.getASC_Partner_C().getAll(false)));
     p1.setHeight(500, Unit.PIXELS);
     p1.setWidth(300, Unit.PIXELS);

     LP.add(p1);

     C.add(createPanelComponent(RETAIL_COCACALC_PARTNERS.toString(), LP, true));
     } catch (Exception e) {
     }

     try {
     List<Panel> LFS = new ArrayList<>();
     Panel pf = new Panel();
     pf.setHeight(500, Unit.PIXELS);
     pf.setWidth(300, Unit.PIXELS);
     pf.setContent(new Tree_R_FSUgovor("FS Ugovor", MyUI.DS_RETAIL.getASC_FS_C().getAll(false)));
     LFS.add(pf);
     C.add(createPanelComponent(RETAIL_COCACALC_PARTNERS_FS.toString(), LFS, true));
     } catch (Exception e) {
     }

     C.add(createPanelComponent(RETAIL_COCACALC_PARTNERS_CONTRACTS.toString(), subPanels, true));
     C.add(createPanelComponent(RETAIL_COCACALC_DATA_MAINTENENCE_MAPPING.toString(), subPanels, true));

     buildContentWithComponents(C);
     }
     */
}
