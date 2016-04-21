package RETAIL.Views;

import RETAIL.Forms.Form_R_FS;
import com.vaadin.event.FieldEvents;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import RETAIL.Tables.Table_R_FS;
import RETAIL.Trees.Tree_R_FSPerformance;
import RETAIL.Trees.Tree_R_FSUgovor;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItem;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.DateField;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Tree;
import db.retail.ent.FS;
import db.retail.ent.criteria.DateIntervalSearch;
import db.retail.ent.criteria.OS_Search;
import java.util.Date;
import Main.MyUI;
import static Main.MyUI.SYSTEM_DATE_FORMAT;
import org.superbapps.auth.roles.Roles;
import org.superbapps.utils.common.dates.Dates;
import org.superbapps.utils.vaadin.Exceptions.CustomTreeNodesEmptyException;

public class View_R_CocaCalc extends VerticalLayout implements View {

    private final VerticalLayout VL = new VerticalLayout();
    private final VerticalLayout vp = new VerticalLayout();
    private final HorizontalSplitPanel HL = new HorizontalSplitPanel();

    private final Table_R_FS table = new Table_R_FS();
    private final Form_R_FS form;

    private Tree tree_R_FSPerformance;
    private final OS_Search ossEvent;

    // dinamcki panel koji brisemo i kreiramo svaki put kada dodje do promene u ossEvent-u
    private Panel lastPerformacePanel;

    private final DateField viewDateFrom = new DateField();
    private final DateField viewDateTo = new DateField();
    private final Dates dates = new Dates(-1, true);

    private final VerticalLayout propVL = new VerticalLayout();

    public View_R_CocaCalc() {
        ossEvent = new OS_Search(new DateIntervalSearch(dates.getFromStr(), dates.getToStr()), null);

        viewDateFrom.setDateFormat(SYSTEM_DATE_FORMAT);
        viewDateTo.setDateFormat(SYSTEM_DATE_FORMAT);
        viewDateFrom.setValue(dates.getFrom());
        viewDateTo.setValue(dates.getTo());

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

        form = new Form_R_FS(new BeanItem(new FS()), true, () -> {
            table.refreshVisualContainer();
        });

        form.setEnabled(false);

        vp.setSpacing(true);
        vp.setSizeFull();

        table.addValueChangeListener((Property.ValueChangeEvent event) -> {
            openProperties((FS) table.getValue());
        });

        addComponent(VL);
    }
    //<editor-fold defaultstate="collapsed" desc="Customer Table - Double click - Customer Form">

    private void refreshFSPerformancePanel(OS_Search ossevent) {
        //tree_R_FSPerformance.update(ossEvent);

        try {
            tree_R_FSPerformance = new Tree_R_FSPerformance(ossevent, true);
        } catch (CustomTreeNodesEmptyException | NullPointerException ex) {
        }

        // update-uj dinamicki posle izmene datuma/fs
        vp.replaceComponent(lastPerformacePanel, lastPerformacePanel = new Panel("Performace", tree_R_FSPerformance));
    }

    @Override
    public void enter(ViewChangeEvent event) {
    }

    //<editor-fold defaultstate="collapsed" desc="createTopBar">
    public final HorizontalLayout createTopBar() {
        //<editor-fold defaultstate="collapsed" desc="DateField listeners">
        viewDateFrom.addValueChangeListener((Property.ValueChangeEvent event) -> {
            dates.setFrom((Date) event.getProperty().getValue());
            ossEvent.setDateFrom(dates.getFromStr());

            if (ossEvent.getFsCode() != null) {
                refreshFSPerformancePanel(ossEvent);
            }
        });

        viewDateTo.addValueChangeListener((Property.ValueChangeEvent event) -> {
            dates.setTo((Date) event.getProperty().getValue());
            ossEvent.setDateTo(dates.getToStr());

            if (ossEvent.getFsCode() != null) {
                refreshFSPerformancePanel(ossEvent);
            }
        });
        //</editor-fold>    

        //<editor-fold defaultstate="collapsed" desc="Topbar visual">
        TextField filter = new TextField();
        filter.setStyleName("filter-textfield");
        filter.setInputPrompt("search data...");
        ResetButtonForTextField.extend(filter);
        filter.setImmediate(false);
        filter.addTextChangeListener((FieldEvents.TextChangeEvent event) -> {
            table.setFilter(event.getText());
        });

        HorizontalLayout topLayout = new HorizontalLayout();
        topLayout.setSpacing(true);
        topLayout.setWidth(100, Unit.PERCENTAGE);

        topLayout.addComponents(filter, viewDateFrom, viewDateTo);

        topLayout.setComponentAlignment(filter, Alignment.MIDDLE_LEFT);
        topLayout.setComponentAlignment(viewDateFrom, Alignment.MIDDLE_RIGHT);
        topLayout.setComponentAlignment(viewDateTo, Alignment.MIDDLE_RIGHT);

        topLayout.setComponentAlignment(filter, Alignment.MIDDLE_LEFT);
        topLayout.setExpandRatio(filter, 1);
        topLayout.setStyleName("top-bar");
        //</editor-fold>

        return topLayout;
    }
    //</editor-fold>

    private void openProperties(FS item) {
        vp.removeAllComponents();
        propVL.removeAllComponents();

        if (item != null) {
            HL.setSplitPosition(60, Sizeable.Unit.PERCENTAGE);

            ossEvent.setFsCode(item.getCode());
            form.setEnabled(MyUI.get().isPermitted(Roles.APP_COCACALC_OWNREPORT_READ));
            form.setBeanItem(new BeanItem(item));

            //<editor-fold defaultstate="collapsed" desc="Create Panels With Trees">
            try {
                tree_R_FSPerformance = new Tree_R_FSPerformance(ossEvent, true);

                vp.addComponent(lastPerformacePanel = new Panel("Performace", (Tree_R_FSPerformance) tree_R_FSPerformance));

            } catch (NullPointerException | CustomTreeNodesEmptyException e) {
            }

            try {
                vp.addComponents(new Panel("Contracts", new Tree_R_FSUgovor(item, false, !MyUI.get().isPermitted(Roles.APP_COCACALC_FS_UGOVOR_MAINTENANCE))));
            } catch (CustomTreeNodesEmptyException ex) {
            }
            // vp.addComponents(new Panel("Update Form", form));
            //</editor-fold> 

            propVL.addComponent(vp);
        } else {
            ossEvent.setFsCode(null);
            HL.setSplitPosition(100, Unit.PERCENTAGE);
        }
    }
}
