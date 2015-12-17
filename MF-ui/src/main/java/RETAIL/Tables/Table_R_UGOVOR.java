package RETAIL.Tables;

import org.superb.apps.utilities.vaadin.Tables.Table_GEN;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.filter.Or;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Table;
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
        addGeneratedColumn("ugovorAktivan1", (final Table source, final Object row, Object column) -> {
            CheckBox cb = new CheckBox("", ((Ugovor) row).isUgovorAktivan());
            cb.setEnabled(false);

            return cb;
        });
        //</editor-fold>
        setVisibleColumns("brojUgovora", "fkIdp", "fkIdfs", "datumPotpisivanja1", "datumPreuzimanja1", "ugovorAktivan1", "datumPrekidaUgovora1", "fiksniIznos");
        setColumnHeaders("Contract No.", "Partner", "FS", "Sign Date", "Takeover Date", "Contract Active?", "Cancelation Date", "Fixed Ammount");

        setColumnAlignment("ugovorAktivan1", Align.CENTER);
        setColumnWidth("ugovorAktivan1", 110);
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
