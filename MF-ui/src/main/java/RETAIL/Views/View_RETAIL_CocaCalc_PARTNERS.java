package RETAIL.Views;

import RETAIL.Grids.Grid_R_Partner;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class View_RETAIL_CocaCalc_PARTNERS extends VerticalLayout implements View {

    private final Grid_R_Partner table = new Grid_R_Partner();

    public View_RETAIL_CocaCalc_PARTNERS() {
        //<editor-fold defaultstate="collapsed" desc="UI setup">
        setMargin(true);
        //addStyleName("crud-view");
        HorizontalLayout topLayout = createTopBar();

        addComponent(topLayout);
        table.setSizeFull();

        //VL.setStyleName("crud-main-layout");

        addComponent(table);
    }

    @Override
    public void enter(ViewChangeEvent event) {
    }

    //<editor-fold defaultstate="collapsed" desc="createTopBar">
    public final HorizontalLayout createTopBar() {
        TextField filter = new TextField();
        filter.setStyleName("filter-textfield");
        filter.setInputPrompt("search data...");
        ResetButtonForTextField.extend(filter);
        filter.setImmediate(false);

        HorizontalLayout topLayout = new HorizontalLayout();
        topLayout.setSpacing(true);
        topLayout.setWidth(100, Unit.PERCENTAGE);
        topLayout.addComponent(filter);
        topLayout.setComponentAlignment(filter, Alignment.MIDDLE_LEFT);
        topLayout.setExpandRatio(filter, 1);
        topLayout.setStyleName("top-bar");

        return topLayout;
    }
    //</editor-fold>
}
