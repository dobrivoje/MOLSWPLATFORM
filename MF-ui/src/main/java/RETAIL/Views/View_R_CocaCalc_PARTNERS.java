package RETAIL.Views;

import RETAIL.Forms.Form_R_PARTNER;
import RETAIL.Tables.Table_R_PARTNER;
import RETAIL.Trees.Tree_R_PartnerUgovor;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItem;
import com.vaadin.event.FieldEvents;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import db.Exceptions.CustomTreeNodesEmptyException;
import db.retail.ent.Partner;
import Main.MyUI;
import org.dobrivoje.auth.roles.Roles;

public class View_R_CocaCalc_PARTNERS extends VerticalLayout implements View {

    private final VerticalLayout VL = new VerticalLayout();
    private final VerticalLayout propVL = new VerticalLayout();
    private final HorizontalSplitPanel HL = new HorizontalSplitPanel();

    private final Table_R_PARTNER table = new Table_R_PARTNER();
    private final Form_R_PARTNER form;

    VerticalLayout vp = new VerticalLayout();

    public View_R_CocaCalc_PARTNERS() {
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

        form = new Form_R_PARTNER(new BeanItem(new Partner()), true, () -> {
            table.refreshVisualContainer();
        });

        form.setEnabled(false);

        vp.setSpacing(true);
        vp.setSizeFull();

        table.addValueChangeListener((Property.ValueChangeEvent event) -> {
            openProperties((Partner) table.getValue());
        });

        addComponent(VL);
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

    private void openProperties(Partner item) {
        vp.removeAllComponents();
        propVL.removeAllComponents();

        if (item != null) {
            HL.setSplitPosition(50, Sizeable.Unit.PERCENTAGE);

            boolean readOnly = !MyUI.get().isPermitted(Roles.PERMISSION_APP_FS_USER_EDIT_OWN_WORKPLANS);

            form.setEnabled(readOnly);
            form.setBeanItem(new BeanItem(item));

            try {
                vp.addComponent(new Panel("Contracts", new Tree_R_PartnerUgovor(item, true, readOnly)));
            } catch (CustomTreeNodesEmptyException ex) {
            }

            // vp.addComponents(new Panel("Update Form", form));
            //
            propVL.addComponent(vp);

        } else {
            form.setEnabled(false);
            form.setBeanItem(new BeanItem(new Partner()));
            HL.setSplitPosition(100, Sizeable.Unit.PERCENTAGE);
        }
    }
}
