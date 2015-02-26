package Views.MainMenu.HSE;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

public class HSE_SysNotifBoardView extends VerticalLayout implements View {

    public static final String VIEW_NAME = "HSE System Notifications";

    private final VerticalLayout VL = new VerticalLayout();

    public HSE_SysNotifBoardView() {
        //<editor-fold defaultstate="collapsed" desc="UI setup">
        setSizeFull();
        addStyleName("crud-view");
        VL.setSizeFull();
        VL.setMargin(true);
        VL.setSpacing(true);

        HorizontalLayout topLayout = createTopBar();

        VL.addComponent(topLayout);
        // VL.addComponent(HL);
        VL.setSizeFull();
        // VL.setExpandRatio(HL, 1);
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
