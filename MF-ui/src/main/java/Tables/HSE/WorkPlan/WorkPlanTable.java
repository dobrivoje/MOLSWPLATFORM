/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tables.HSE.WorkPlan;

import Tables.GENTable;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.filter.Or;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.ui.Table;
import com.vaadin.ui.CheckBox;
import db.controllers.HSE.Controller;
import db.controllers.HSE.WPController;
import db.ent.HSE.WorkPlan;

/**
 *
 * @author root
 */
public class WorkPlanTable extends GENTable<WorkPlan> {

    public WorkPlanTable() {
        this(new BeanItemContainer<>(WorkPlan.class), new WPController());
    }

    public WorkPlanTable(BeanItemContainer<WorkPlan> beanContainer, Controller controller) {
        super(beanContainer, controller);

        addGeneratedColumn("finished1", new Table.ColumnGenerator() {
            @Override
            public Object generateCell(final Table source, final Object row, Object column) {
                CheckBox cb = new CheckBox("", ((WorkPlan) row).getFinished());
                cb.setEnabled(false);

                return cb;
            }
        });
        setVisibleColumns("startDate", "FK_FuelStation", "contractor", /*"subContractor", */
                /* "worktype", "termin", "duration", */ "finished1", "endDate" /* ",comment" */);
        setColumnHeaders("Start date", "Fuel Station", "Contractor", /* "Subcontractor", */
                /* "Worktype", "Termin", "Duration", */ "Finished ?", "End Date" /*, "Comment" */);

        setColumnWidth("finished1", 82);
        setColumnAlignment("finished1", Align.CENTER);
    }

    public void setFilter(String filterString) {
        beanContainer.removeAllContainerFilters();

        if (filterString.length() > 0) {
            SimpleStringFilter fsFilter = new SimpleStringFilter(
                    "FK_FuelStation", filterString, true, false);
            SimpleStringFilter contractorFilter = new SimpleStringFilter(
                    "contractor", filterString, true, false);
            SimpleStringFilter startDateFilter = new SimpleStringFilter(
                    "startDate", filterString, true, false);
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
