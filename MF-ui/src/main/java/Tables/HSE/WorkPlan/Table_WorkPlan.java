/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tables.HSE.WorkPlan;

import Tables.Table_GEN;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.filter.Or;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.ui.Table;
import com.vaadin.ui.CheckBox;
import db.ent.HSE.WorkPlan;
import java.util.List;
import static mf.MyUI.DS;

/**
 *
 * @author root
 */
public class Table_WorkPlan extends Table_GEN<WorkPlan> {

    public Table_WorkPlan() {
        this(new BeanItemContainer<>(WorkPlan.class), DS.getWPController().getAll());
    }

    public Table_WorkPlan(BeanItemContainer<WorkPlan> beanContainer, List list) {
        super(beanContainer, list);

        addGeneratedColumn("finished1", new Table.ColumnGenerator() {
            @Override
            public Object generateCell(final Table source, final Object row, Object column) {
                CheckBox cb = new CheckBox("", ((WorkPlan) row).getFinished());
                cb.setEnabled(false);

                return cb;
            }
        });
        setVisibleColumns("startDate1", "FK_FuelStation", "contractor", /*"subContractor", */
                /* "worktype", "termin", "duration", */ "finished1", "endDate" /* ",comment" */);
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
                    "endDate", filterString, true, false);

            beanContainer.addContainerFilter(
                    new Or(fsFilter, contractorFilter,
                            startDateFilter, startDateFilter,
                            endDateFilter
                    ));
        }
    }
}
