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

        addGeneratedColumn("name1", (final Table source, final Object row, Object column) -> {
            return ((CompositeSellReport) row).getFkIdfs().getNaziv();
        });

        addGeneratedColumn("code1", (final Table source, final Object row, Object column) -> {
            return ((CompositeSellReport) row).getFkIdfs().getCode();
        });

        addGeneratedColumn("cocaModel1", (final Table source, final Object row, Object column) -> {
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

        setVisibleColumns("name1", "code1", "cocaModel1", "timeCode1", "quantity",
                "cogs", "revenue", "vat", "netto", "sellValue", "datumImporta1");
        setColumnHeaders("FS", "Code", "FS in COCA Model?", "Transaction Date", "Qty", "Cogs", "Revenue",
                "VAT", "Netto", "Sell Value", "Report Date Generated");

        setColumnAlignment("cocaModel1", Align.CENTER);
        setColumnWidth("cocaModel1", 50);
    }

    public void setFilter(String filterString) {
        beanContainer.removeAllContainerFilters();

        if (filterString.length() > 0) {
            SimpleStringFilter name1Filter = new SimpleStringFilter(
                    "name1", filterString, true, false);
            SimpleStringFilter code1Filter = new SimpleStringFilter(
                    "code1", filterString, true, false);
            SimpleStringFilter timeCode1Filter = new SimpleStringFilter(
                    "timeCode1", filterString, true, false);

            beanContainer.addContainerFilter(
                    new Or(name1Filter, code1Filter, timeCode1Filter)
            );
        }
    }
}
