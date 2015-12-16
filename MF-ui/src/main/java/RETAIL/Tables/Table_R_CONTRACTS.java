package RETAIL.Tables;

import org.superb.apps.utilities.vaadin.Tables.Table_GEN;
import com.vaadin.data.util.BeanItemContainer;
import db.retail.ent.Ugovor;
import java.util.List;
import static mf.MyUI.DS_RETAIL;

/**
 *
 * @author Dobri
 */
public class Table_R_CONTRACTS extends Table_GEN<Ugovor> {

    public Table_R_CONTRACTS() {
        this(new BeanItemContainer<>(Ugovor.class), DS_RETAIL.getASC_Ugovor_C().getAll());
    }

    public Table_R_CONTRACTS(BeanItemContainer<Ugovor> beanContainer, List list) {
        super(beanContainer, list);

        //<editor-fold defaultstate="collapsed" desc="definisanje kolona">
        /*
         addGeneratedColumn("active1", (final Table source, final Object row, Object column) -> {
         CheckBox cb = new CheckBox("", ((Mapping) row).getAktivan());
         cb.setEnabled(false);
        
         return cb;
         });
        
         addGeneratedColumn("report1", (final Table source, final Object row, Object column) -> {
         CheckBox cb = new CheckBox("", ((Mapping) row).getReport());
         cb.setEnabled(false);
        
         return cb;
         });
         */
        //</editor-fold>
        
        /*
        setVisibleColumns("naziv", "code", "kategorija1", "active1", "report1",
                "izvObracun1", "izvSpecif1", "datumUnosa1");
        setColumnHeaders("Mapping", "Code", "Category", "Active ?", "Report ?",
                "Calc. Report", "Spec. Report", "Define Date");

        setColumnAlignment("code", Align.CENTER);
        setColumnWidth("code", 80);

        setColumnAlignment("active1", Align.CENTER);
        setColumnWidth("active1", 80);

        setColumnAlignment("report1", Align.CENTER);
        setColumnWidth("report1", 80);
        */
    }

    public void setFilter(String filterString) {
        beanContainer.removeAllContainerFilters();

        /*
        if (filterString.length() > 0) {
            SimpleStringFilter nazivFilter = new SimpleStringFilter(
                    "naziv", filterString, true, false);
            SimpleStringFilter codeFilter = new SimpleStringFilter(
                    "code", filterString, true, false);
            SimpleStringFilter kategFilter = new SimpleStringFilter(
                    "kategorija1", filterString, true, false);
            SimpleStringFilter calcFilter = new SimpleStringFilter(
                    "izvObracun1", filterString, true, false);
            SimpleStringFilter specFilter = new SimpleStringFilter(
                    "izvObracun1", filterString, true, false);

            beanContainer.addContainerFilter(
                    new Or(nazivFilter, codeFilter, kategFilter, calcFilter, specFilter)
            );
        }
        */
    }
}