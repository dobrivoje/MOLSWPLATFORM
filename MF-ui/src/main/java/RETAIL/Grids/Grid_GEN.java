package RETAIL.Grids;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Grid;
import java.util.List;
import org.superbapps.utils.vaadin.Tables.IRefreshVisualContainer;

public class Grid_GEN<T> extends Grid implements IRefreshVisualContainer {

    protected final BeanItemContainer<T> beanContainer;
    protected List list;

    public Grid_GEN(BeanItemContainer<T> beanContainer, List list) {
        this.beanContainer = beanContainer;
        this.list = list;

        setContainerDataSource(beanContainer);
        updateBeanItemContainer(list);

        setSizeFull();

        setImmediate(true);
    }

    protected final void updateBeanItemContainer(List list) {
        this.beanContainer.removeAllItems();
        this.beanContainer.addAll(list);
    }

    @Override
    public void refreshVisualContainer() {
        updateBeanItemContainer(this.list);
        markAsDirtyRecursive();
    }

}
