/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tables.HSE.WorkPlan;

import reports.ent.HSE.HSE_SysNotif_Bean;
import Tables.GENTable;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.Align;
import java.util.List;
import org.superb.apps.utilities.Enums.WorkingPlansStatuses;
import org.superb.apps.utilities.vaadin.FancyLabels.WPSLabel;
import static ws.MyUI.DS;

/**
 *
 * @author root
 */
public class HSE_SysNotifTable extends GENTable<HSE_SysNotif_Bean> {

    public HSE_SysNotifTable() {
        this(new BeanItemContainer<>(HSE_SysNotif_Bean.class),
                DS.getHSESysNotifController().getSysNotifBoard_Report1());
    }

    public HSE_SysNotifTable(BeanItemContainer<HSE_SysNotif_Bean> beanContainer, List list) {
        super(beanContainer, list);

        addGeneratedColumn("finished", new Table.ColumnGenerator() {
            @Override
            public Object generateCell(final Table source, final Object row, Object column) {
                HSE_SysNotif_Bean hse = (HSE_SysNotif_Bean) row;
                WPSLabel label;

                switch (hse.getTotal() - hse.getSumFinishedWorkPlans()) {
                    case 0:
                        label = new WPSLabel(WorkingPlansStatuses.FINISHED, "OK");
                        break;
                    default:
                        label = new WPSLabel(WorkingPlansStatuses.IN_PROGRESS, WorkingPlansStatuses.IN_PROGRESS.toString());
                }

                return label;
            }
        });
        addGeneratedColumn("fs1", new Table.ColumnGenerator() {
            @Override
            public Object generateCell(final Table source, final Object row, Object column) {
                return ((HSE_SysNotif_Bean) row).getFs().getName();
            }
        });

        setVisibleColumns("fs1", "finished", "total");
        setColumnCollapsible("fs1", true);
        setColumnCollapsible("finished", true);
        setColumnHeaders("Fuel Station", "Finished ? ", "Total plans");

        //setColumnWidth("finished", 82);
        //setColumnWidth("total", 82);
        setColumnAlignment("fs1", Align.CENTER);
        setColumnAlignment("finished", Align.CENTER);
        setColumnAlignment("total", Align.CENTER);
    }

}
