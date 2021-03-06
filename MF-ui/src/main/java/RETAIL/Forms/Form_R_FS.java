package RETAIL.Forms;

import com.vaadin.data.Item;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.data.util.BeanItem;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import db.retail.ent.FS;
import static Main.MyUI.DS_RETAIL;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import static org.superbapps.utils.common.Enums.CrudOperations.BUTTON_CAPTION_UPDATE;
import org.superbapps.utils.vaadin.Forms.Form_CRUD2;
import org.superbapps.utils.vaadin.Tables.IRefreshVisualContainer;
import org.superbapps.utils.vaadin.Trees.ILayoutLockable;

public class Form_R_FS extends Form_CRUD2<FS> implements ILayoutLockable {

    //<editor-fold defaultstate="collapsed" desc="Form Fields">
    @PropertyId("naziv")
    private final TextField naziv = new TextField("FS Name");

    @PropertyId("code")
    private final TextField code = new TextField("FS Code");

    @PropertyId("model")
    private final TextField model = new TextField("Model");

    @PropertyId("cocaModel")
    private final CheckBox cocaModel = new CheckBox("Coca model ? ");
    //</editor-fold>

    public Form_R_FS() {
        super(new BeanFieldGroup(FS.class));

        fieldGroup.bindMemberFields(this);
        setFormFieldsWidths(250, Unit.PIXELS);

        initFields();
    }

    public Form_R_FS(Item existingItem, boolean defaultCRUDButtonOnForm, final IRefreshVisualContainer visualContainer) {
        this();

        this.defaultCRUDButtonOnForm = defaultCRUDButtonOnForm;

        fieldGroup.setItemDataSource(existingItem);
        beanItem = (BeanItem<FS>) fieldGroup.getItemDataSource();

        btnCaption = BUTTON_CAPTION_UPDATE.toString();
        clickListener = (Button.ClickEvent event) -> {
            FS fsToUpdate = beanItem.getBean();
            // setBeanFromFields(fsToUpdate);

            try {
                fieldGroup.commit();

                DS_RETAIL.getFS_C().update(fsToUpdate);
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

    
    public Form_R_FS(Item existingItem, boolean defaultCRUDButtonOnForm, final IRefreshVisualContainer visualContainer, boolean readOnly) {
        this(existingItem, defaultCRUDButtonOnForm, visualContainer);
        
        setLayoutFieldsLocked(readOnly);
    }

    
    //<editor-fold defaultstate="collapsed" desc="overided methods,...">
    @Override
    protected final void setBeanFromFields(FS item) {
        item.setNaziv(naziv.getValue());
        item.setCode(code.getValue());
        item.setModel(model.getValue());
        item.setCocaModel(cocaModel.getValue());
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
    }

    @Override
    protected void updateDynamicFields() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setFieldsFromBean(FS item) {
        try {
            item.setNaziv(naziv.getValue());
        } catch (Exception e) {
        }

        try {
            item.setCode(code.getValue());
        } catch (Exception e) {
        }

        try {
            item.setModel(model.getValue());
        } catch (Exception e) {
        }

        try {
            item.setCocaModel(cocaModel.getValue());
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
