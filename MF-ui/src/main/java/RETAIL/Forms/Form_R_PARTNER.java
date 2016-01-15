package RETAIL.Forms;

import com.vaadin.data.Item;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.data.util.BeanItem;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import db.retail.ent.Partner;
import db.retail.ent.Ugovor;
import static Main.MyUI.DS_RETAIL;
import org.superb.apps.utilities.vaadin.Tables.IRefreshVisualContainer;
import static org.superb.apps.utilities.Enums.CrudOperations.BUTTON_CAPTION_UPDATE;
import org.superb.apps.utilities.vaadin.Forms.Form_CRUD2;

public class Form_R_PARTNER extends Form_CRUD2<Partner> {

    //<editor-fold defaultstate="collapsed" desc="Form Fields">
    @PropertyId("naziv")
    private final TextField naziv = new TextField("Partner Rep.");

    @PropertyId("kompanija")
    private final TextField kompanija = new TextField("Partner's firm");
    //</editor-fold>

    public Form_R_PARTNER() {
        super(new BeanFieldGroup(Partner.class));

        fieldGroup.bindMemberFields(this);
        setFormFieldsWidths(250, Unit.PIXELS);

        initFields();
    }

    public Form_R_PARTNER(Item existingItem, boolean defaultCRUDButtonOnForm, final IRefreshVisualContainer visualContainer) {
        this();

        this.defaultCRUDButtonOnForm = defaultCRUDButtonOnForm;

        fieldGroup.setItemDataSource(existingItem);
        beanItem = (BeanItem<Partner>) fieldGroup.getItemDataSource();

        btnCaption = BUTTON_CAPTION_UPDATE.toString();
        clickListener = (Button.ClickEvent event) -> {
            Partner itemToUpdate = beanItem.getBean();
            setBeanFromFields(itemToUpdate);

            try {
                fieldGroup.commit();

                DS_RETAIL.getASC_Partner_C().update(itemToUpdate);
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
    protected final void setBeanFromFields(Partner item) {
        item.setNaziv(naziv.getValue());
        item.setKompanija(kompanija.getValue());
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
    }

    @Override
    protected void updateDynamicFields() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setFieldsFromBean(Partner item) {
        try {
            item.setNaziv(naziv.getValue());
        } catch (Exception e) {
        }

        try {
            item.setKompanija(kompanija.getValue());
        } catch (Exception e) {
        }
    }
    //</editor-fold>

}
