package HSE.Views;

import HSE.Forms.Form_H_WorkPlan;
import com.vaadin.event.FieldEvents;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import HSE.Tables.Table_H_WorkPlan;
import RETAIL.Views.ResetButtonForTextField;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.HorizontalSplitPanel;
import db.HSE.ent.WorkPlan;
import org.dobrivoje.auth.roles.Roles;
import mf.MyUI;

public class View_HSE_WorkPlan extends VerticalLayout implements View {

    private final VerticalLayout VL = new VerticalLayout();
    private final HorizontalSplitPanel HL = new HorizontalSplitPanel();
    private final VerticalLayout propVL = new VerticalLayout();

    private final Table_H_WorkPlan table = new Table_H_WorkPlan();
    private final Form_H_WorkPlan form;

    private Button newBtn;

    public View_HSE_WorkPlan() {
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

        form = new Form_H_WorkPlan(new BeanItem(new WorkPlan()), true, () -> {
            table.refreshVisualContainer();
        });

        form.setEnabled(false);

        propVL.addComponent(form);

        table.addValueChangeListener((Property.ValueChangeEvent event) -> {
            WorkPlan wp = (WorkPlan) table.getValue();
            openProperties(wp);
        });
        //</editor-fold>

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

        newBtn = new Button("New WorkPlan");
        newBtn.setWidth(200, Unit.PIXELS);
        newBtn.addClickListener((Button.ClickEvent event) -> {
            openProperties(new WorkPlan());
        });

        HorizontalLayout topLayout = new HorizontalLayout();
        topLayout.setSpacing(true);
        topLayout.setWidth(100, Unit.PERCENTAGE);
        topLayout.addComponents(filter, newBtn);
        topLayout.setComponentAlignment(filter, Alignment.MIDDLE_LEFT);
        topLayout.setExpandRatio(filter, 1);
        topLayout.setStyleName("top-bar");

        return topLayout;
    }
    //</editor-fold>

    private void openProperties(WorkPlan item) {
        if (item != null) {
            HL.setSplitPosition(40, Unit.PERCENTAGE);
            form.setEnabled(MyUI.get().isPermitted(Roles.PERMISSION_APP_FS_USER_EDIT_OWN_WORKPLANS));
            form.setBeanItem(new BeanItem(item));
        } else {
            form.setEnabled(false);
            form.setBeanItem(new BeanItem(new WorkPlan()));
            HL.setSplitPosition(100, Unit.PERCENTAGE);
        }
    }
}
