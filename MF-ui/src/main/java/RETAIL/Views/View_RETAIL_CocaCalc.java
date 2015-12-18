package RETAIL.Views;

import RETAIL.Forms.Form_R_FS;
import com.vaadin.event.FieldEvents;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import RETAIL.Tables.Table_R_FS;
import RETAIL.Trees.Tree_R_FSUgovor;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Panel;
import db.Exceptions.CustomTreeNodesEmptyException;
import db.retail.ent.FS;
import mf.MyUI;
import org.dobrivoje.auth.roles.Roles;

public class View_RETAIL_CocaCalc extends VerticalLayout implements View {

    private final VerticalLayout VL = new VerticalLayout();

    private final HorizontalSplitPanel HL = new HorizontalSplitPanel();
    private final Table_R_FS table = new Table_R_FS();
    private final VerticalLayout propVL = new VerticalLayout();

    public View_RETAIL_CocaCalc() {
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
            openProperties((FS) table.getValue());
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

    private void openProperties(FS item) {
        if (item != null) {
            HL.setSplitPosition(50, Unit.PERCENTAGE);

            if (propVL.getComponentCount() > 0) {
                propVL.removeAllComponents();
            }

            Form_R_FS form = new Form_R_FS(new BeanItem(item), true, () -> {
                table.refreshVisualContainer();
            });
            form.setEnabled(MyUI.get().isPermitted(Roles.PERMISSION_APP_FS_USER_EDIT_OWN_WORKPLANS));

            VerticalLayout vp = new VerticalLayout();
            vp.setSpacing(true);
            vp.setSizeFull();

            try {
                vp.addComponent(new Panel("Contracts", new Tree_R_FSUgovor("", item)));
            } catch (CustomTreeNodesEmptyException ex) {
            }

            vp.addComponents(
                    new Panel("Performace"),
                    new Panel("Update Form", form)
            );

            propVL.addComponent(vp);

        } else {
            HL.setSplitPosition(100, Unit.PERCENTAGE);
        }
    }
}
