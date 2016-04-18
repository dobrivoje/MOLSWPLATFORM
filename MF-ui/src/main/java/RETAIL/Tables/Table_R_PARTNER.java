package RETAIL.Tables;

import org.superbapps.utils.vaadin.Tables.Table_GEN;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.filter.Or;
import com.vaadin.data.util.filter.SimpleStringFilter;
import db.retail.ent.Partner;
import java.util.List;
import static Main.MyUI.DS_RETAIL;

/**
 *
 * @author Dobri
 */
public class Table_R_PARTNER extends Table_GEN<Partner> {

    public Table_R_PARTNER() {
        this(new BeanItemContainer<>(Partner.class), DS_RETAIL.getASC_Partner_C().getAll(true));
    }

    public Table_R_PARTNER(BeanItemContainer<Partner> beanContainer, List list) {
        super(beanContainer, list);

        setVisibleColumns("naziv", "kompanija");
        setColumnHeaders("Partner", "Partner's Firm");
    }

    public void setFilter(String filterString) {
        beanContainer.removeAllContainerFilters();

        if (filterString.length() > 0) {
            SimpleStringFilter nazivFilter = new SimpleStringFilter(
                    "naziv", filterString, true, false);

            beanContainer.addContainerFilter(new Or(nazivFilter));
        }
    }
}
