package RETAIL.Forms;

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
import com.vaadin.ui.TextField;
import db.retail.ent.GrupniNaziv;
import db.retail.ent.Kategorija;
import db.retail.ent.Mapping;
import static Main.MyUI.DS_RETAIL;
import org.superb.apps.utilities.vaadin.Tables.IRefreshVisualContainer;
import static org.superb.apps.utilities.Enums.CrudOperations.BUTTON_CAPTION_UPDATE;
import org.superb.apps.utilities.vaadin.Forms.Form_CRUD2;

public class Form_R_Mapping extends Form_CRUD2<Mapping> {

    //<editor-fold defaultstate="collapsed" desc="Form Fields">
    @PropertyId("naziv")
    private final TextField naziv = new TextField("Mapping Name");

    @PropertyId("code")
    private final TextField code = new TextField("Mapping Code");

    @PropertyId("aktivan")
    private final CheckBox active = new CheckBox("Mapping active ? ");

    @PropertyId("report")
    private final CheckBox report = new CheckBox("Mapping on report ? ");

    @PropertyId("datumUnosa")
    private final DateField datumUnosa = new DateField("Defined on");

    @PropertyId("fkIdk")
    private final ComboBox kategorija = new ComboBox("Calc. Report",
            new BeanItemContainer(Kategorija.class, DS_RETAIL.getASC_KATEG_C().getAll(false)));

    @PropertyId("fkIdgn")
    private final ComboBox grupniNaziv = new ComboBox("Spec. Report",
            new BeanItemContainer(GrupniNaziv.class, DS_RETAIL.getASC_GN_C().getAll(false)));
    //</editor-fold>

    public Form_R_Mapping() {
        super(new BeanFieldGroup(Mapping.class));

        fieldGroup.bindMemberFields(this);
        setFormFieldsWidths(250, Unit.PIXELS);

        initFields();
    }

    public Form_R_Mapping(Item existingItem, boolean defaultCRUDButtonOnForm, final IRefreshVisualContainer visualContainer) {
        this();

        this.defaultCRUDButtonOnForm = defaultCRUDButtonOnForm;

        fieldGroup.setItemDataSource(existingItem);
        beanItem = (BeanItem<Mapping>) fieldGroup.getItemDataSource();

        btnCaption = BUTTON_CAPTION_UPDATE.toString();
        clickListener = (Button.ClickEvent event) -> {
            Mapping m = beanItem.getBean();
            // setBeanFromFields(m);

            try {
                fieldGroup.commit();

                DS_RETAIL.getASC_MAPPING_C().update(m);

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
        };

        addBeansToForm();
    }

    //<editor-fold defaultstate="collapsed" desc="overided methods,...">
    @Override
    protected final void setBeanFromFields(Mapping item) {
        item.setNaziv(naziv.getValue());
        item.setCode(code.getValue());
        item.setAktivan(active.getValue());
        item.setReport(report.getValue());
        item.setDatumUnosa(datumUnosa.getValue());
        item.setFkIdgn((GrupniNaziv) grupniNaziv.getValue());
        item.setFkIdk((Kategorija) kategorija.getValue());
    }

    @Override
    protected final void initFields() {
        crudButton.setWidth(250, Unit.PIXELS);
        setRequiredFields();
    }

    @Override
    protected void setRequiredFields() {
        naziv.setRequired(true);
        naziv.setRequiredError("Must be entered !");

        code.setRequired(true);
        code.setRequiredError("Must be entered !");

        grupniNaziv.setRequired(true);
        grupniNaziv.setRequiredError("Must be entered !");

        kategorija.setRequired(true);
        kategorija.setRequiredError("Must be entered !");
    }

    @Override
    protected void updateDynamicFields() {
    }

    @Override
    public void setFieldsFromBean(Mapping item) {
        try {
            item.setNaziv(naziv.getValue());
        } catch (Exception e) {
        }

        try {
            item.setCode(code.getValue());
        } catch (Exception e) {
        }

        try {
            item.setAktivan(active.getValue());
        } catch (Exception e) {
        }

        try {
            item.setReport(report.getValue());
        } catch (Exception e) {
        }

        try {
            item.setDatumUnosa(datumUnosa.getValue());
        } catch (Exception e) {
        }

        try {
            item.setFkIdgn((GrupniNaziv) grupniNaziv.getValue());
        } catch (Exception e) {
        }

        try {
            item.setFkIdgn((GrupniNaziv) grupniNaziv.getValue());
        } catch (Exception e) {
        }

        try {
            item.setFkIdk((Kategorija) kategorija.getValue());
        } catch (Exception e) {
        }

    }
    //</editor-fold>

}
