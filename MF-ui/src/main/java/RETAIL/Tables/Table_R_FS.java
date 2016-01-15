/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RETAIL.Tables;

import org.superb.apps.utilities.vaadin.Tables.Table_GEN;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.filter.Or;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.ui.Table;
import com.vaadin.ui.CheckBox;
import db.retail.ent.FS;
import java.util.List;
import static Main.MyUI.DS_RETAIL;

/**
 *
 * @author Dobri
 */
public class Table_R_FS extends Table_GEN<FS> {

    public Table_R_FS() {
        this(new BeanItemContainer<>(FS.class), DS_RETAIL.getASC_FS_C().getAll(true));
    }

    public Table_R_FS(BeanItemContainer<FS> beanContainer, List list) {
        super(beanContainer, list);

        addGeneratedColumn("cocaModel1", (final Table source, final Object row, Object column) -> {
            CheckBox cb = new CheckBox("", ((FS) row).getCocaModel());
            cb.setEnabled(false);

            return cb;
        });

        setVisibleColumns("naziv", "code", "model", "cocaModel1");
        setColumnHeaders("Fuelstation", "Code", "Model", "COCA ?");

        setColumnWidth("finished1", 80);
        setColumnAlignment("finished1", Align.CENTER);
        setColumnWidth("cocaModel", 50);
    }

    public void setFilter(String filterString) {
        beanContainer.removeAllContainerFilters();

        if (filterString.length() > 0) {
            SimpleStringFilter fsFilter = new SimpleStringFilter(
                    "naziv", filterString, true, false);
            SimpleStringFilter contractorFilter = new SimpleStringFilter(
                    "code", filterString, true, false);
            SimpleStringFilter startDateFilter = new SimpleStringFilter(
                    "model", filterString, true, false);
            SimpleStringFilter endDateFilter = new SimpleStringFilter(
                    "cocaModel", filterString, true, false);

            beanContainer.addContainerFilter(
                    new Or(fsFilter, contractorFilter,
                            startDateFilter, startDateFilter,
                            endDateFilter
                    ));
        }
    }
}
