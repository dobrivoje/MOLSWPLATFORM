package Views.MainMenu.HSE;

import Forms.HSE.WorkPlan.WorkPlanForm;
import com.vaadin.event.FieldEvents;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import Tables.HSE.WorkPlan.WorkPlanTable;
import Views.ResetButtonForTextField;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.HorizontalSplitPanel;
import db.ent.HSE.WorkPlan;
import org.dobrivoje.auth.roles.Roles;
import org.superb.apps.utilities.vaadin.Tables.IRefreshVisualContainer;
import ws.MyUI;

public class HSE_WorkPlanView extends VerticalLayout implements View {

    private final VerticalLayout VL = new VerticalLayout();

    private final HorizontalSplitPanel HL = new HorizontalSplitPanel();
    private final WorkPlanTable wpTable = new WorkPlanTable();
    private final VerticalLayout propVL = new VerticalLayout();

    private Button newBtn;

    public HSE_WorkPlanView() {
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
        VerticalLayout vl1 = new VerticalLayout(wpTable);

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

        wpTable.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                WorkPlan wp = (WorkPlan) wpTable.getValue();
                openProperties(wp);
            }
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
        filter.addTextChangeListener(new FieldEvents.TextChangeListener() {
            @Override
            public void textChange(FieldEvents.TextChangeEvent event) {
                wpTable.setFilter(event.getText());
            }
        });

        newBtn = new Button("New WorkPlan");
        newBtn.addStyleName(ValoTheme.BUTTON_PRIMARY);
        newBtn.setIcon(FontAwesome.YOUTUBE_PLAY);
        newBtn.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                WorkPlan wp = new WorkPlan();
                openProperties(wp);
            }
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

    private void openProperties(WorkPlan wp) {
        if (wp != null) {
            HL.setSplitPosition(40, Unit.PERCENTAGE);

            if (propVL.getComponentCount() > 0) {
                propVL.removeAllComponents();
            }

            WorkPlanForm wpForm = new WorkPlanForm(new BeanItem(wp), new IRefreshVisualContainer() {
                @Override
                public void refreshVisualContainer() {
                    wpTable.refreshVisualContainer();
                }
            });

            wpForm.setEnabled(MyUI.get().isPermitted(Roles.PERMISSION_APP_FS_USER_EDIT_OWN_WORKPLANS));
            System.err.println();

            propVL.addComponent(wpForm);

        } else {
            HL.setSplitPosition(100, Unit.PERCENTAGE);
        }
    }
}
