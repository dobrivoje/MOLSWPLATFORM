package RETAIL.Views;

import RETAIL.Forms.Form_R_UGOVOR;
import RETAIL.Tables.Table_R_UGOVOR;
import com.vaadin.event.FieldEvents;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItem;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalSplitPanel;
import db.retail.ent.Ugovor;
import mf.MyUI;
import org.dobrivoje.auth.roles.Roles;

public class View_RETAIL_CocaCalc_PA_Contracts extends VerticalLayout implements View {

    private final VerticalLayout VL = new VerticalLayout();
    private final VerticalLayout propVL = new VerticalLayout();
    private final HorizontalSplitPanel HL = new HorizontalSplitPanel();
    
    private final Table_R_UGOVOR table = new Table_R_UGOVOR();
    private final Form_R_UGOVOR form;


    private Button newBtn;

    public View_RETAIL_CocaCalc_PA_Contracts() {
        //<editor-fold defaultstate="collapsed" desc="UI setup">
        setSizeFull();
        addStyleName("crud-view");
        VL.setSizeFull();
        VL.setMargin(true);
        VL.setSpacing(true);
        HL.setSizeFull();
        HL.setSplitPosition(100, Sizeable.Unit.PERCENTAGE);
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

        form = new Form_R_UGOVOR(new BeanItem(new Ugovor()), true, () -> {
            table.refreshVisualContainer();
        });

        form.setEnabled(false);

        propVL.addComponent(form);

        table.addValueChangeListener((Property.ValueChangeEvent event) -> {
            openProperties((Ugovor) table.getValue());
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

        newBtn = new Button("New Contract");
        newBtn.setWidth(200, Unit.PIXELS);
        newBtn.addClickListener((Button.ClickEvent event) -> {
            openProperties(new Ugovor());
        });

        HorizontalLayout topLayout = new HorizontalLayout();
        topLayout.setSpacing(true);
        topLayout.setWidth(100, Sizeable.Unit.PERCENTAGE);
        topLayout.addComponents(filter, newBtn);
        topLayout.setComponentAlignment(filter, Alignment.MIDDLE_LEFT);
        topLayout.setExpandRatio(filter, 1);
        topLayout.setStyleName("top-bar");

        return topLayout;
    }
    //</editor-fold>

    private void openProperties(Ugovor item) {
        if (item != null) {
            HL.setSplitPosition(50, Sizeable.Unit.PERCENTAGE);
            form.setEnabled(MyUI.get().isPermitted(Roles.PERMISSION_APP_FS_USER_EDIT_OWN_WORKPLANS));
            form.setBeanItem(new BeanItem(item));
        } else {
            form.setEnabled(false);
            form.setBeanItem(new BeanItem(new Ugovor()));
            HL.setSplitPosition(100, Sizeable.Unit.PERCENTAGE);
        }
    }
}
