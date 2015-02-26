/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CustomBeans;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Table;
import java.util.List;
import org.superb.apps.utilities.vaadin.Tables.IRefreshVisualContainer;

/**
 *
 * @author root
 * @param <T>
 */
public class HSE_GENTable<T> extends Table implements IRefreshVisualContainer {

    protected final BeanItemContainer<T> beanContainer;
    protected final HSE_SysNotifController controller;

    public HSE_GENTable(BeanItemContainer<T> beanContainer, HSE_SysNotifController controller) {
        this.beanContainer = beanContainer;
        this.controller = controller;

        setContainerDataSource(beanContainer);
        updateBeanItemContainer(controller.getSysNotifBoard_Report1());

        setSizeFull();

        setPageLength(20);
        setCacheRate(4);
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
        updateBeanItemContainer(controller.getSysNotifBoard_Report1());
        markAsDirtyRecursive();
    }
}
