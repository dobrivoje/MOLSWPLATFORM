/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tables;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Table;
import db.controllers.HSE.Controller;
import java.util.List;
import org.superb.apps.utilities.vaadin.Tables.IRefreshVisualContainer;

/**
 *
 * @author root
 */
public class GENTable<T> extends Table implements IRefreshVisualContainer {

    protected final BeanItemContainer<T> beanContainer;
    protected final Controller controller;

    public GENTable(BeanItemContainer<T> beanContainer, Controller controller) {
        this.beanContainer = beanContainer;
        this.controller = controller;

        setContainerDataSource(beanContainer);
        updateBeanItemContainer(controller.getAll());

        setSizeFull();
        
        setPageLength(20);
        setCacheRate(10);
        setSelectable(true);
        setColumnCollapsingAllowed(true);
        //setImmediate(true);
    }

    protected final void updateBeanItemContainer(List listOfData) {
        this.beanContainer.removeAllItems();
        this.beanContainer.addAll(listOfData);
    }

    @Override
    public void refreshVisualContainer() {
        updateBeanItemContainer(controller.getAll());
        markAsDirtyRecursive();
    }
}
