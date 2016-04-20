package RETAIL.Forms;

import com.vaadin.data.Item;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.data.util.BeanItem;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import db.retail.ent.FS;
import db.retail.ent.Partner;
import db.retail.ent.Ugovor;
import static Main.MyUI.DS_RETAIL;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import static org.superbapps.utils.common.Enums.CrudOperations.BUTTON_CAPTION_UPDATE;
import org.superbapps.utils.vaadin.Forms.Form_CRUD2;
import org.superbapps.utils.vaadin.Tables.IRefreshVisualContainer;
import org.superbapps.utils.vaadin.Trees.ILayoutLockable;

public class Form_R_UGOVOR extends Form_CRUD2<Ugovor> implements ILayoutLockable {

    //<editor-fold defaultstate="collapsed" desc="Form Fields">
    @PropertyId("brojUgovora")
    private final TextField brojUgovora = new TextField("Contract No.");

    @PropertyId("fkIdp")
    private final ComboBox partner = new ComboBox("Partner", DS_RETAIL.getMD_Partner_C().getAllDetails());

    @PropertyId("fkIdfs")
    private final ComboBox fs = new ComboBox("FS", DS_RETAIL.getASC_FS_C().getAll(true));

    @PropertyId("datumPotpisivanja")
    private final DateField datumPotpisivanja = new DateField("Contract Sign Date");

    @PropertyId("datumPreuzimanja")
    private final DateField datumPreuzimanja = new DateField("Teakover Date");

    @PropertyId("datumPrekidaUgovora")
    private final DateField datumPrekidaUgovora = new DateField("Contract Cancel Date");

    @PropertyId("ugovorAktivan")
    private final CheckBox ugovorAktivan = new CheckBox("Contract Active ?");

    @PropertyId("fiksniIznos")
    private final TextField fiksniIznos = new TextField("Fixed Ammount");
    //</editor-fold>

    public Form_R_UGOVOR() {
        super(new BeanFieldGroup(Ugovor.class));

        fieldGroup.bindMemberFields(this);
        setFormFieldsWidths(250, Unit.PIXELS);

        initFields();
    }

    public Form_R_UGOVOR(Item existingItem, boolean defaultCRUDButtonOnForm, final IRefreshVisualContainer visualContainer) {
        this();

        this.defaultCRUDButtonOnForm = defaultCRUDButtonOnForm;

        fieldGroup.setItemDataSource(existingItem);
        beanItem = (BeanItem<Ugovor>) fieldGroup.getItemDataSource();

        btnCaption = BUTTON_CAPTION_UPDATE.toString();
        clickListener = (Button.ClickEvent event) -> {
            Ugovor itemToUpdate = beanItem.getBean();
            // setBeanFromFields(itemToUpdate);

            try {
                fieldGroup.commit();

                DS_RETAIL.getASC_Ugovor_C().update(itemToUpdate);
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

    public Form_R_UGOVOR(Item existingItem, boolean defaultCRUDButtonOnForm, final IRefreshVisualContainer visualContainer, boolean readOnly) {
        this(existingItem, defaultCRUDButtonOnForm, visualContainer);

        setLayoutFieldsLocked(readOnly);
    }

    //<editor-fold defaultstate="collapsed" desc="overided methods,...">
    @Override
    protected final void setBeanFromFields(Ugovor item) {
        item.setBrojUgovora(brojUgovora.getValue());
        item.setFkIdp((Partner) partner.getValue());
        item.setFkIdfs((FS) fs.getValue());

        try {
            item.setDatumPotpisivanja(datumPotpisivanja.getValue());
        } catch (Exception e) {
        }

        try {
            item.setDatumPreuzimanja(datumPreuzimanja.getValue());
        } catch (Exception e) {
        }

        try {
            item.setDatumPrekidaUgovora(datumPrekidaUgovora.getValue());
        } catch (Exception e) {
        }

        try {
            item.setUgovorAktivan(ugovorAktivan.getValue());
        } catch (Exception e) {
        }

        try {
            item.setFiksniIznos(Double.parseDouble(fiksniIznos.getValue()));
        } catch (Exception nfe) {
        }
    }

    @Override
    protected final void initFields() {
        crudButton.setWidth(250, Unit.PIXELS);

        setRequiredFields();
    }

    @Override
    protected void setRequiredFields() {
        brojUgovora.setRequired(true);
        brojUgovora.setRequiredError("Must be entered !");

        partner.setRequired(true);
        partner.setRequiredError("Must be entered !");

        fs.setRequired(true);
        fs.setRequiredError("Must be entered !");

        datumPotpisivanja.setRequired(true);
        datumPotpisivanja.setRequiredError("Must be entered !");

        datumPreuzimanja.setRequired(true);
        datumPreuzimanja.setRequiredError("Must be entered !");
    }

    @Override
    protected void updateDynamicFields() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setFieldsFromBean(Ugovor item) {
        try {
            item.setBrojUgovora(brojUgovora.getValue());
        } catch (Exception e) {
        }

        try {
            item.setFkIdp((Partner) partner.getValue());
        } catch (Exception e) {
        }

        try {
            item.setFkIdfs((FS) fs.getValue());
        } catch (Exception e) {
        }

        try {
            item.setDatumPotpisivanja(datumPotpisivanja.getValue());
        } catch (Exception e) {
        }

        try {
            item.setDatumPreuzimanja(datumPreuzimanja.getValue());
        } catch (Exception e) {
        }

        try {
            item.setDatumPrekidaUgovora(datumPrekidaUgovora.getValue());
        } catch (Exception e) {
        }

        try {
            item.setUgovorAktivan(ugovorAktivan.getValue());
        } catch (Exception e) {
        }

        try {
            item.setFiksniIznos(Double.valueOf(fiksniIznos.getValue()));
        } catch (Exception e) {
        }
    }
    //</editor-fold>

    @Override
    public final void setLayoutFieldsLocked(final boolean readOnly) {

        fieldGroup.getFields().stream().forEach(f -> {
            f.setEnabled(!readOnly);
        });
    }

}
