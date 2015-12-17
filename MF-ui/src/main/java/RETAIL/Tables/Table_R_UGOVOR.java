package RETAIL.Tables;

import org.superb.apps.utilities.vaadin.Tables.Table_GEN;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.filter.Or;
import com.vaadin.data.util.filter.SimpleStringFilter;
import db.retail.ent.Ugovor;
import java.util.List;
import static mf.MyUI.DS_RETAIL;

/**
 *
 * @author Dobri
 */
public class Table_R_UGOVOR extends Table_GEN<Ugovor> {

    public Table_R_UGOVOR() {
        this(new BeanItemContainer<>(Ugovor.class), DS_RETAIL.getASC_Ugovor_C().getAll());
    }

    public Table_R_UGOVOR(BeanItemContainer<Ugovor> beanContainer, List list) {
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
        setVisibleColumns("brojUgovora", "fkIdp", "fkIdfs", "datumPotpisivanja1", "datumPreuzimanja1", "fiksniIznos");
        setColumnHeaders("Br. Ugovora", "Partner", "FS", "Datum Potpisivanja", "Datum Preuzimanja", "Fiksni Iznos");

        /*
         setColumnAlignment("fkIdp", Align.CENTER);
         setColumnWidth("fkIdp", 80);
         */
    }

    public void setFilter(String filterString) {
        beanContainer.removeAllContainerFilters();

        if (filterString.length() > 0) {
            SimpleStringFilter nazivFilter = new SimpleStringFilter(
                    "brojUgovora", filterString, true, false);
            SimpleStringFilter codeFilter = new SimpleStringFilter(
                    "fkIdp", filterString, true, false);
            SimpleStringFilter kategFilter = new SimpleStringFilter(
                    "fkIdfs", filterString, true, false);

            beanContainer.addContainerFilter(
                    new Or(nazivFilter, codeFilter, kategFilter)
            );
        }

    }
}
