package RETAIL.Grids;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.filter.Or;
import com.vaadin.data.util.filter.SimpleStringFilter;
import db.retail.ent.Mapping;
import java.util.List;
import static mf.MyUI.DS_RETAIL;

/**
 *
 * @author Dobri
 */
public class Grid_R_MAPPING extends Grid_GEN<Mapping> {

    public Grid_R_MAPPING() {
        this(new BeanItemContainer<>(Mapping.class), DS_RETAIL.getASC_MAPPING_C().getAll());
    }

    public Grid_R_MAPPING(BeanItemContainer<Mapping> beanContainer, List list) {
        super(beanContainer, list);

        setSelectionMode(SelectionMode.MULTI);
    }

    public void setFilter(String filterString) {
        beanContainer.removeAllContainerFilters();

        if (filterString.length() > 0) {
            SimpleStringFilter nazivFilter = new SimpleStringFilter(
                    "naziv", filterString, true, false);
            SimpleStringFilter codeFilter = new SimpleStringFilter(
                    "code", filterString, true, false);
            SimpleStringFilter kategFilter = new SimpleStringFilter(
                    "kategorija1", filterString, true, false);
            SimpleStringFilter calcFilter = new SimpleStringFilter(
                    "izvObracun1", filterString, true, false);
            SimpleStringFilter specFilter = new SimpleStringFilter(
                    "izvObracun1", filterString, true, false);

            beanContainer.addContainerFilter(
                    new Or(nazivFilter, codeFilter, kategFilter, calcFilter, specFilter)
            );
        }
    }
}
