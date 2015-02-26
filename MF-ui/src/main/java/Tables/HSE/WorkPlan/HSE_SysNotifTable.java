/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tables.HSE.WorkPlan;

import CustomBeans.HSE_GENTable;
import CustomBeans.HSE_SysNotifController;
import CustomBeans.HSE_SysNotif_Bean;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.Align;
import org.superb.apps.utilities.Enums.WorkingPlansStatuses;
import org.superb.apps.utilities.vaadin.FancyLabels.WPSLabel;

/**
 *
 * @author root
 */
public class HSE_SysNotifTable extends HSE_GENTable<HSE_SysNotif_Bean> {

    public HSE_SysNotifTable() {
        this(new BeanItemContainer<>(HSE_SysNotif_Bean.class), new HSE_SysNotifController());
    }

    public HSE_SysNotifTable(BeanItemContainer<HSE_SysNotif_Bean> beanContainer, HSE_SysNotifController hseController) {
        super(beanContainer, hseController);

        addGeneratedColumn("finished", new Table.ColumnGenerator() {
            @Override
            public Object generateCell(final Table source, final Object row, Object column) {
                HSE_SysNotif_Bean hse = (HSE_SysNotif_Bean) row;
                WPSLabel label;

                switch (hse.getSumFinishedWorkPlans()) {
                    case 0:
                        label = new WPSLabel(WorkingPlansStatuses.IN_PROGRESS, WorkingPlansStatuses.IN_PROGRESS.toString());
                        break;
                    default:
                        label = new WPSLabel(WorkingPlansStatuses.UNKNOWN, WorkingPlansStatuses.UNKNOWN.toString());
                }

                return label;
            }
        });
        addGeneratedColumn("total", new Table.ColumnGenerator() {
            @Override
            public Object generateCell(final Table source, final Object row, Object column) {
                return "---";
            }
        });

        setVisibleColumns("fs", "finished", "total");
        setColumnHeaders("Fuel Station", "Finished ? ", "Total");

        //setColumnWidth("finished", 82);
        //setColumnWidth("total", 82);
        setColumnAlignment("fs", Align.CENTER);
        setColumnAlignment("finished", Align.CENTER);
        setColumnAlignment("total", Align.CENTER);
    }

}
