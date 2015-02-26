package Views.MainMenu.HSE;

import Tables.HSE.WorkPlan.HSE_SysNotifTable;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

public class HSE_SysNotifBoardView extends VerticalLayout implements View {

    public static final String VIEW_NAME = "HSE System Notifications";

    private final VerticalLayout VL = new VerticalLayout();
    private final GridLayout GL = new GridLayout();

    private final Panel p = new Panel();

    private final HSE_SysNotifTable hseSysNotifTable = new HSE_SysNotifTable();

    public HSE_SysNotifBoardView() {
        //<editor-fold defaultstate="collapsed" desc="UI setup">
        setSizeFull();
        addStyleName("crud-view");

        GL.setRows(3);
        GL.setColumns(3);

        VL.setSizeFull();
        VL.setMargin(true);

        HorizontalLayout topLayout = createTopBar();

        hseSysNotifTable.setSizeFull();
        p.setWidth(35, Unit.PERCENTAGE);
        p.setHeight(350, Unit.PIXELS);
        p.setCaption("Workplans Report");
        p.setContent(hseSysNotifTable);
        GL.addComponent(p, 0, 0);

        VL.addComponent(topLayout);
        VL.addComponent(GL);
        VL.setSizeFull();
        VL.setExpandRatio(GL, 1);
        VL.setStyleName("crud-main-layout");
        addComponent(VL);
        //</editor-fold>

        addComponent(VL);
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
        // topLayout.addComponents(filter, newBtn);
        // topLayout.setComponentAlignment(filter, Alignment.MIDDLE_LEFT);
        // topLayout.setExpandRatio(filter, 1);
        topLayout.setStyleName("top-bar");

        return topLayout;
    }
    //</editor-fold>

}
