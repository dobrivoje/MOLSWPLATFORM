/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RETAIL.Tables;

import Tables.Table_GEN;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.filter.Or;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.ui.Table;
import com.vaadin.ui.CheckBox;
import db.retail.ent.CompositeSellReport;
import java.text.SimpleDateFormat;
import java.util.List;
import static mf.MyUI.DS_RETAIL;

/**
 *
 * @author root
 */
public class Table_R_CSR extends Table_GEN<CompositeSellReport> {

    public Table_R_CSR() {
        this(new BeanItemContainer<>(CompositeSellReport.class), DS_RETAIL.getASC_CS_C().getAll());
    }

    public Table_R_CSR(BeanItemContainer<CompositeSellReport> beanContainer, List list) {
        super(beanContainer, list);

        addGeneratedColumn("fsName", (final Table source, final Object row, Object column) -> {
            return ((CompositeSellReport) row).getFkIdfs().getNaziv();
        });

        addGeneratedColumn("fsCode", (final Table source, final Object row, Object column) -> {
            return ((CompositeSellReport) row).getFkIdfs().getCode();
        });

        addGeneratedColumn("fsCocaModel", (final Table source, final Object row, Object column) -> {
            CheckBox cb = new CheckBox("", ((CompositeSellReport) row).getFkIdfs().getCocaModel());
            cb.setEnabled(false);

            return cb;
        });

        addGeneratedColumn("timeCode1", (final Table source, final Object row, Object column) -> {
            try {
                return new SimpleDateFormat("d.M.yyyy").format(((CompositeSellReport) row).getTimeCode());
            } catch (Exception e) {
                return "";
            }
        });

        setVisibleColumns("fsName", "fsCode", "fsCocaModel", "timeCode1", "quantity",
                "cogs", "revenue", "vat", "netto", "sellValue", "datumImporta1");
        setColumnHeaders("FS", "Code", "FS in COCA Model?", "Transaction Date", "Qty", "Cogs", "Revenue",
                "VAT", "Netto", "Sell Value", "Report Date Generated");

        //setColumnWidth("finished1", 80);
        setColumnAlignment("fsCocaModel", Align.CENTER);
        setColumnWidth("fsCocaModel", 50);
    }

    public void setFilter(String filterString) {
        beanContainer.removeAllContainerFilters();

        if (filterString.length() > 0) {
            SimpleStringFilter fsFilter = new SimpleStringFilter(
                    "fsName", filterString, true, false);
            SimpleStringFilter fsCodeFilter = new SimpleStringFilter(
                    "fsCode", filterString, true, false);
            SimpleStringFilter timeCodeFilter = new SimpleStringFilter(
                    "timeCode1", filterString, true, false);

            beanContainer.addContainerFilter(
                    new Or(fsFilter, fsCodeFilter, timeCodeFilter)
            );
        }
    }
}
