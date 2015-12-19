package RETAIL.Grids;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.SelectionMode;
import db.retail.ent.Partner;
import static mf.MyUI.DS_RETAIL;

/**
 *
 * @author Dobri
 */
public class Grid_R_Partner extends Grid {

    public Grid_R_Partner() {
        setContainerDataSource(new BeanItemContainer(Partner.class, 
                DS_RETAIL.getASC_Partner_C().getAll(true)));
        setSelectionMode(SelectionMode.MULTI);
    }
}
