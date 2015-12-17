package RETAIL.Tables;

import org.superb.apps.utilities.vaadin.Tables.Table_GEN;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.filter.Or;
import com.vaadin.data.util.filter.SimpleStringFilter;
import db.retail.ent.Partner;
import java.util.List;
import static mf.MyUI.DS_RETAIL;

/**
 *
 * @author Dobri
 */
public class Table_R_PARTNER extends Table_GEN<Partner> {

    public Table_R_PARTNER() {
        this(new BeanItemContainer<>(Partner.class), DS_RETAIL.getASC_Partner_C().getAll());
    }

    public Table_R_PARTNER(BeanItemContainer<Partner> beanContainer, List list) {
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
        setVisibleColumns("naziv", "kompanija");
        setColumnHeaders("Partner", "Firma");

        setColumnAlignment("naziv", Align.CENTER);
    }

    public void setFilter(String filterString) {
        beanContainer.removeAllContainerFilters();

        if (filterString.length() > 0) {
            SimpleStringFilter nazivFilter = new SimpleStringFilter(
                    "naziv", filterString, true, false);

            beanContainer.addContainerFilter(
                    new Or(nazivFilter)
            );
        }
    }
}
