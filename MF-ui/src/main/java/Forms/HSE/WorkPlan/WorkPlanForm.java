package Forms.HSE.WorkPlan;

import com.vaadin.data.Item;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;
import date.formats.DateFormat;
import db.controllers.HSE.FSController;
import db.controllers.HSE.WPController;
import db.ent.HSE.FuelStation;
import db.ent.HSE.WorkPlan;
import org.superb.apps.utilities.Enums.CrudOperations;
import static org.superb.apps.utilities.Enums.CrudOperations.BUTTON_CAPTION_NEW;
import static org.superb.apps.utilities.Enums.CrudOperations.BUTTON_CAPTION_UPDATE;
import org.superb.apps.utilities.vaadin.Tables.IRefreshVisualContainer;

public class WorkPlanForm extends FormLayout {

    private final WPController wpController = new WPController();
    private final FSController fsController = new FSController();

    private final FieldGroup fieldGroup = new BeanFieldGroup(WorkPlan.class);
    private Button crudButton;
    private BeanItem<WorkPlan> beanItem;

    private Button.ClickListener clickListener;
    private String btnCaption;

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
            "Fuel Station", new BeanItemContainer(FuelStation.class, fsController.getAll()));
    //</editor-fold>

    public WorkPlanForm() {
        setSizeFull();

        fieldGroup.bindMemberFields(this);

        startDate.setWidth(210, Unit.PIXELS);
        fuelStation.setWidth(210, Unit.PIXELS);
        contractor.setWidth(90, Unit.PERCENTAGE);
        subContractor.setWidth(90, Unit.PERCENTAGE);

        worktype.setWidth(90, Unit.PERCENTAGE);
        worktype.setRows(2);

        termin.setWidth(210, Unit.PIXELS);
        duration.setWidth(80, Unit.PIXELS);
        endDate.setWidth(210, Unit.PIXELS);
        comment.setWidth(90, Unit.PERCENTAGE);
        comment.setRows(2);

        fuelStation.setNullSelectionAllowed(false);

        startDate.setDateFormat(DateFormat.DATE_FORMAT_SRB.toString());
        endDate.setDateFormat(DateFormat.DATE_FORMAT_SRB.toString());

        for (Component f : fieldGroup.getFields()) {
            if (f instanceof TextField) {
                TextField tf = (TextField) f;
                tf.setNullRepresentation("");
            }
            if (f instanceof TextArea) {
                TextArea tf = (TextArea) f;
                tf.setNullRepresentation("");
            }
        }

        startDate.setRequired(true);
        startDate.setRequiredError("Mora se uneti vrednost !");

        fuelStation.setRequired(true);
        fuelStation.setRequiredError("Mora se uneti vrednost !");

        contractor.setRequired(true);
        contractor.setRequiredError("Mora se uneti vrednost !");

        worktype.setRequired(true);
        worktype.setRequiredError("Mora se uneti vrednost !");
    }

    public WorkPlanForm(final CrudOperations crudOperation, final IRefreshVisualContainer visualContainer) {
        this();

        fieldGroup.setItemDataSource(new BeanItem(new WorkPlan()));
        beanItem = (BeanItem<WorkPlan>) fieldGroup.getItemDataSource();

        if (crudOperation.equals(CrudOperations.CREATE)) {
            btnCaption = BUTTON_CAPTION_NEW.toString();
            clickListener = new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent event) {
                    bindFieldsToBean(beanItem.getBean());

                    try {
                        wpController.addNew(beanItem.getBean());

                        if (visualContainer != null) {
                            visualContainer.refreshVisualContainer();
                        }

                        Notification n = new Notification("New Workplan Added.", Notification.Type.TRAY_NOTIFICATION);
                        n.setDelayMsec(500);
                        n.show(getUI().getPage());
                    } catch (Exception ex) {
                        Notification.show("Greška.", "MORATE UNETI POLJA OZNAČENA ZVEZDICOM !", Notification.Type.ERROR_MESSAGE);
                    }
                }
            };

            crudButton = new Button(btnCaption, clickListener);
            crudButton.setWidth(150, Unit.PIXELS);
            crudButton.setStyleName(ValoTheme.BUTTON_FRIENDLY);

            startDate.setDateFormat(DateFormat.DATE_FORMAT_SRB.toString());
            endDate.setDateFormat(DateFormat.DATE_FORMAT_SRB.toString());

            addComponents(startDate, fuelStation, contractor, subContractor, worktype, termin, duration, endDate, finished, comment, crudButton);

            startDate.focus();
        }
    }

    public WorkPlanForm(Item existingWorkPlan, final IRefreshVisualContainer visualContainer) {
        this();

        fieldGroup.setItemDataSource(existingWorkPlan);
        beanItem = (BeanItem<WorkPlan>) fieldGroup.getItemDataSource();

        btnCaption = BUTTON_CAPTION_UPDATE.toString();
        clickListener = new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                WorkPlan wpToUpdate = beanItem.getBean();
                bindFieldsToBean(wpToUpdate);

                try {
                    wpController.updateExisting(wpToUpdate);

                    if (visualContainer != null) {
                        visualContainer.refreshVisualContainer();
                    }

                    Notification n = new Notification("WorkPlan Updated.", Notification.Type.TRAY_NOTIFICATION);

                    n.setDelayMsec(500);
                    n.show(getUI().getPage());
                } catch (Exception ex) {
                    Notification.show("Error", "Description: " + ex.toString(), Notification.Type.ERROR_MESSAGE);
                }
            }
        };

        crudButton = new Button(btnCaption, clickListener);
        crudButton.setWidth(150, Unit.PIXELS);
        crudButton.setStyleName(ValoTheme.BUTTON_FRIENDLY);

        addComponents(startDate, fuelStation, contractor, subContractor, worktype, termin, duration, endDate, finished, comment, crudButton);
    }

    private void bindFieldsToBean(WorkPlan workPlanBean) {
        workPlanBean.setStartDate(startDate.getValue());
        workPlanBean.setEndDate(endDate.getValue());
        workPlanBean.setFK_FuelStation((FuelStation) fuelStation.getValue());
        workPlanBean.setContractor(contractor.getValue());
        workPlanBean.setSubContractor(subContractor.getValue());
        workPlanBean.setWorktype(worktype.getValue());
        workPlanBean.setTermin(termin.getValue());
        workPlanBean.setDuration(duration.getValue());
        workPlanBean.setFinished(finished.getValue());
        workPlanBean.setComment(comment.getValue());
    }
}
