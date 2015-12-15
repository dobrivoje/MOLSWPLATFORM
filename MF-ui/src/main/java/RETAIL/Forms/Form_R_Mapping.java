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
import static mf.MyUI.DS_RETAIL;
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

    @PropertyId("fkIdgn")
    private final ComboBox grupniNaziv = new ComboBox("Mapping group",
            new BeanItemContainer(GrupniNaziv.class, DS_RETAIL.getASC_GN_C().getAll()));

    @PropertyId("fkIdk")
    private final ComboBox kategorija = new ComboBox("Category",
            new BeanItemContainer(Kategorija.class, DS_RETAIL.getASC_KATEG_C().getAll()));
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
            setBeanFromFields(m);

            try {
                fieldGroup.commit();

                DS_RETAIL.getASC_MAPPING_C().update(m);

                if (visualContainer != null) {
                    visualContainer.refreshVisualContainer();
                }

                Notification n = new Notification("Mapping Updated.", Notification.Type.TRAY_NOTIFICATION);

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
    protected final void setBeanFromFields(Mapping m) {
        m.setNaziv(naziv.getValue());
        m.setCode(code.getValue());
        m.setAktivan(active.getValue());
        m.setReport(report.getValue());
        m.setDatumUnosa(datumUnosa.getValue());
        m.setFkIdgn((GrupniNaziv) grupniNaziv.getValue());
        m.setFkIdk((Kategorija) kategorija.getValue());
    }

    @Override
    protected final void initFields() {
        crudButton.setWidth(250, Unit.PIXELS);
        datumUnosa.setEnabled(false);

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
    public void setFieldsFromBean(Mapping m) {
        try {
            m.setNaziv(naziv.getValue());
        } catch (Exception e) {
        }

        try {
            m.setCode(code.getValue());
        } catch (Exception e) {
        }

        try {
            m.setAktivan(active.getValue());
        } catch (Exception e) {
        }

        try {
            m.setReport(report.getValue());
        } catch (Exception e) {
        }

        try {
            m.setDatumUnosa(datumUnosa.getValue());
        } catch (Exception e) {
        }

        try {
            m.setFkIdgn((GrupniNaziv) grupniNaziv.getValue());
        } catch (Exception e) {
        }

        try {
            m.setFkIdgn((GrupniNaziv) grupniNaziv.getValue());
        } catch (Exception e) {
        }

        try {
            m.setFkIdk((Kategorija) kategorija.getValue());
        } catch (Exception e) {
        }

    }
    //</editor-fold>

}
