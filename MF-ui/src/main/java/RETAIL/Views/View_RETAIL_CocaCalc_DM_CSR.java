package RETAIL.Views;

import RETAIL.Tables.Table_R_CSR;
import com.vaadin.event.FieldEvents;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import Views.ResetButtonForTextField;
import com.vaadin.data.Property;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Panel;
import db.retail.ent.CompositeSellReport;
import java.util.ArrayList;
import java.util.List;

public class View_RETAIL_CocaCalc_DM_CSR extends VerticalLayout implements View {

    private final VerticalLayout VL = new VerticalLayout();

    private final HorizontalSplitPanel HL = new HorizontalSplitPanel();
    private final Table_R_CSR table = new Table_R_CSR();
    private final VerticalLayout propVL = new VerticalLayout();

    private final List<Panel> propPanels = new ArrayList<>();

    public View_RETAIL_CocaCalc_DM_CSR() {
        //<editor-fold defaultstate="collapsed" desc="UI setup">
        setSizeFull();
        addStyleName("crud-view");
        VL.setSizeFull();
        VL.setMargin(true);
        VL.setSpacing(true);
        HL.setSizeFull();
        HL.setSplitPosition(100, Unit.PERCENTAGE);
        HorizontalLayout topLayout = createTopBar();

        // kreiraj panel za tabelu i properies tabele :
        VerticalLayout vl1 = new VerticalLayout(table);

        propVL.setMargin(true);
        propVL.setSpacing(true);

        vl1.setMargin(true);
        vl1.setSizeFull();
        HL.setFirstComponent(vl1);
        HL.setSecondComponent(propVL);
        VL.addComponent(topLayout);
        VL.addComponent(HL);
        VL.setSizeFull();
        VL.setExpandRatio(HL, 1);
        VL.setStyleName("crud-main-layout");
        addComponent(VL);
        //</editor-fold>

        table.addValueChangeListener((Property.ValueChangeEvent event) -> {
            CompositeSellReport csr = (CompositeSellReport) table.getValue();
            openProperties(csr);
        });

        addComponent(VL);
    }
    //<editor-fold defaultstate="collapsed" desc="Customer Table - Double click - Customer Form">

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
        filter.addTextChangeListener((FieldEvents.TextChangeEvent event) -> {
            table.setFilter(event.getText());
        });

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

    private void openProperties(CompositeSellReport csr) {
        if (csr != null) {
            HL.setSplitPosition(50, Unit.PERCENTAGE);

            if (propVL.getComponentCount() > 0) {
                propVL.removeAllComponents();
            }

            /*            
             Form_R_FS fsForm = new Form_R_FS(new BeanItem(fs), true, () -> {
             fsTable.refreshVisualContainer();
             });
             fsForm.setEnabled(MyUI.get().isPermitted(Roles.PERMISSION_APP_FS_USER_EDIT_OWN_WORKPLANS));

             propVL.addComponent(fsForm);
             */
        } else {
            HL.setSplitPosition(100, Unit.PERCENTAGE);
        }
    }
}
