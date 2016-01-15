package HSE.Forms;

import com.vaadin.data.Item;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import db.HSE.ent.FuelStation;
import db.HSE.ent.WorkPlan;
import org.superb.apps.utilities.vaadin.Tables.IRefreshVisualContainer;
import static Main.MyUI.SYSTEM_DATE_FORMAT;
import static Main.MyUI.DS_HSE;
import static org.superb.apps.utilities.Enums.CrudOperations.BUTTON_CAPTION_UPDATE;
import org.superb.apps.utilities.vaadin.Forms.Form_CRUD2;

public class Form_H_WorkPlan extends Form_CRUD2<WorkPlan> {

    //<editor-fold defaultstate="collapsed" desc="Form Fields">
    @PropertyId("startDate")
    private final DateField startDate = new DateField("Start Date");

    @PropertyId("contractor")
    private final TextField contractor = new TextField("Contractor");

    @PropertyId("subContractor")
    private final TextField subContractor = new TextField("Sub Contractor");

    @PropertyId("worktype")
    private final TextArea worktype = new TextArea("Worktype");

    @PropertyId("termin")
    private final TextField termin = new TextField("Termin");

    @PropertyId("duration")
    private final TextField duration = new TextField("Duration");

    @PropertyId("endDate")
    private final DateField endDate = new DateField("End Date");

    @PropertyId("finished")
    private final CheckBox finished = new CheckBox("Finished ? ");

    @PropertyId("comment")
    private final TextArea comment = new TextArea("Comment");

    @PropertyId("FK_FuelStation")
    private final ComboBox fuelStation = new ComboBox(
            "Fuel Station", new BeanItemContainer(FuelStation.class,
                    DS_HSE.getFSController().getAll()));
    //</editor-fold>

    public Form_H_WorkPlan() {
        super(new BeanFieldGroup(WorkPlan.class));

        fieldGroup.bindMemberFields(this);
        setFormFieldsWidths(250, Unit.PIXELS);

        initFields();
    }

    public Form_H_WorkPlan(Item existingWorkPlan, boolean defaultCRUDButtonOnForm, final IRefreshVisualContainer visualContainer) {
        this();

        this.defaultCRUDButtonOnForm = defaultCRUDButtonOnForm;

        fieldGroup.setItemDataSource(existingWorkPlan);
        beanItem = (BeanItem<WorkPlan>) fieldGroup.getItemDataSource();

        btnCaption = BUTTON_CAPTION_UPDATE.toString();
        clickListener = new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                WorkPlan wpToUpdate = beanItem.getBean();
                setBeanFromFields(wpToUpdate);

                try {
                    fieldGroup.commit();

                    DS_HSE.getWPController().updateExisting(wpToUpdate);

                    if (visualContainer != null) {
                        visualContainer.refreshVisualContainer();
                    }

                    Notification n = new Notification("Item Updated.", Notification.Type.TRAY_NOTIFICATION);

                    n.setDelayMsec(500);
                    n.show(getUI().getPage());

                } catch (FieldGroup.CommitException ex) {
                    Notification.show("Error", "Fields indicated by red stars, must be provided.", Notification.Type.ERROR_MESSAGE);
                } catch (Exception ex) {
                    Notification.show("Error", ex.getMessage(), Notification.Type.ERROR_MESSAGE);
                }
            }
        };

        addBeansToForm();
    }

    //<editor-fold defaultstate="collapsed" desc="overided methods,...">
    @Override
    protected void setBeanFromFields(WorkPlan workPlan) {
        workPlan.setStartDate(startDate.getValue());
        workPlan.setEndDate(endDate.getValue());
        workPlan.setFK_FuelStation((FuelStation) fuelStation.getValue());
        workPlan.setContractor(contractor.getValue());
        workPlan.setSubContractor(subContractor.getValue());
        workPlan.setWorktype(worktype.getValue());
        workPlan.setTermin(termin.getValue());
        workPlan.setDuration(duration.getValue());
        workPlan.setFinished(finished.getValue());
        workPlan.setComment(comment.getValue());
    }

    @Override
    protected final void initFields() {
        fuelStation.setNullSelectionAllowed(false);

        startDate.setDateFormat(SYSTEM_DATE_FORMAT);
        endDate.setDateFormat(SYSTEM_DATE_FORMAT);

        crudButton.setWidth(250, Unit.PIXELS);
    }

    @Override
    protected void setRequiredFields() {
        startDate.setRequired(true);
        startDate.setRequiredError("Mora se uneti vrednost !");

        fuelStation.setRequired(true);
        fuelStation.setRequiredError("Mora se uneti vrednost !");

        contractor.setRequired(true);
        contractor.setRequiredError("Mora se uneti vrednost !");

        worktype.setRequired(true);
        worktype.setRequiredError("Mora se uneti vrednost !");
    }

    @Override
    protected void updateDynamicFields() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public void setFieldsFromBean(WorkPlan item) {
        try {
            item.setStartDate(startDate.getValue());
        } catch (Exception e) {
        }

        try {
            item.setEndDate(endDate.getValue());
        } catch (Exception e) {
        }

        try {
            item.setFK_FuelStation((FuelStation) fuelStation.getValue());
        } catch (Exception e) {
        }

        try {
            item.setContractor(contractor.getValue());
        } catch (Exception e) {
        }

        try {
            item.setSubContractor(subContractor.getValue());
        } catch (Exception e) {
        }

        try {
            item.setWorktype(worktype.getValue());
        } catch (Exception e) {
        }

        try {
            item.setTermin(termin.getValue());
        } catch (Exception e) {
        }

        try {
            item.setDuration(duration.getValue());
        } catch (Exception e) {
        }

        try {
            item.setFinished(finished.getValue());
        } catch (Exception e) {
        }

        try {
            item.setComment(comment.getValue());
        } catch (Exception e) {
        }
    }
    //</editor-fold>

}
