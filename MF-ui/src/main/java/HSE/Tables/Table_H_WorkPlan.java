/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HSE.Tables;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.filter.Or;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.ui.Table;
import com.vaadin.ui.CheckBox;
import db.HSE.ent.WorkPlan;
import java.util.List;
import static Main.MyUI.DS_HSE;
import com.vaadin.ui.Table.Align;
import org.superbapps.utils.vaadin.Tables.Table_GEN;

/**
 *
 * @author Dobri
 */
public class Table_H_WorkPlan extends Table_GEN<WorkPlan> {

    public Table_H_WorkPlan() {
        this(new BeanItemContainer<>(WorkPlan.class), DS_HSE.getWPController().getAll());
    }

    public Table_H_WorkPlan(BeanItemContainer<WorkPlan> beanContainer, List list) {
        super(beanContainer, list);

        addGeneratedColumn("finished1", (final Table source, final Object row, Object column) -> {
            CheckBox cb = new CheckBox("", ((WorkPlan) row).getFinished());
            cb.setEnabled(false);

            return cb;
        });

        setVisibleColumns("startDate1", "FK_FuelStation", "contractor", /*"subContractor", */
                /* "worktype", "termin", "duration", */ "finished1", "endDate1" /* ",comment" */);
        setColumnHeaders("Start date", "Fuel Station", "Contractor", /* "Subcontractor", */
                /* "Worktype", "Termin", "Duration", */ "Finished ?", "End Date" /*, "Comment" */);

        setColumnWidth("finished1", 80);
        setColumnAlignment("finished1", Align.CENTER);
        setColumnWidth("startDate1", 110);
    }

    public void setFilter(String filterString) {
        beanContainer.removeAllContainerFilters();

        if (filterString.length() > 0) {
            SimpleStringFilter fsFilter = new SimpleStringFilter(
                    "FK_FuelStation", filterString, true, false);
            SimpleStringFilter contractorFilter = new SimpleStringFilter(
                    "contractor", filterString, true, false);
            SimpleStringFilter startDateFilter = new SimpleStringFilter(
                    "startDate1", filterString, true, false);
            SimpleStringFilter endDateFilter = new SimpleStringFilter(
                    "endDate1", filterString, true, false);

            beanContainer.addContainerFilter(
                    new Or(
                            fsFilter, contractorFilter,
                            startDateFilter, startDateFilter,
                            endDateFilter
                    ));
        }
    }
}
