/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HSE.Tables;

import db.HSE.ent.reportbeans.HSE_SysNotif_Bean;
import org.superb.apps.utilities.vaadin.Tables.Table_GEN;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.Align;
import java.util.List;
import org.superb.apps.utilities.Enums.WorkingPlansStatuses;
import org.superb.apps.utilities.vaadin.FancyLabels.WPSLabel;
import static Main.MyUI.DS_HSE;

/**
 *
 * @author Dobri
 */
public class Table_H_SysNotif extends Table_GEN<HSE_SysNotif_Bean> {

    public Table_H_SysNotif() {
        this(new BeanItemContainer<>(HSE_SysNotif_Bean.class),
                DS_HSE.getHSESysNotifController().getSysNotifBoard_Report1());
    }

    public Table_H_SysNotif(BeanItemContainer<HSE_SysNotif_Bean> beanContainer, List list) {
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
