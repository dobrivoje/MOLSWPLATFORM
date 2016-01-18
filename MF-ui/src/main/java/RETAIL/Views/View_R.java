package RETAIL.Views;

import RETAIL.Trees.Tree_R_FSUgovor;
import static Uni.MainMenu.MenuDefinitions.RETAIL_COCACALC;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import db.retail.ent.FS;
import java.util.ArrayList;
import java.util.List;
import static Main.MyUI.DS_RETAIL;
import db.Exceptions.CustomTreeNodesEmptyException;
import java.util.Arrays;
import org.superb.apps.utilities.vaadin.Views.View_Dashboard;

public class View_R extends View_Dashboard {

    public View_R() {
        super(RETAIL_COCACALC.toString());
        createFuelStations();
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

    private void createFuelStations() {
        List<Component> C = new ArrayList();

        for (FS fs : DS_RETAIL.getASC_FS_C().getAll(false)) {
            /*
             try {
             C.add(
             createPanelComponent(
             fs.toString(),
             Arrays.asList(
             new Panel(new Tree_R_FSPerformance(new OS_Search(new DateIntervalSearch("2015-11-1", "2015-11-30"), fs.getCode()), true))
             ), true
             )
             );
             } catch (CustomTreeNodesEmptyException ex) {
             }
             */

            try {
                C.add(
                        createPanelComponent(
                                fs.toString(),
                                Arrays.asList(
                                        new Panel(new Tree_R_FSUgovor(Arrays.asList(fs), true))
                                ), true
                        )
                );

            } catch (CustomTreeNodesEmptyException ex) {
            }

        }

        buildContentWithComponents(C);
    }

}
