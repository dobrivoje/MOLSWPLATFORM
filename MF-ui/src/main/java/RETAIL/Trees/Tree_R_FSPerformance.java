package RETAIL.Trees;

import db.Exceptions.CustomTreeNodesEmptyException;
import db.retail.ent.FS;
import db.retail.ent.criteria.DateIntervalSearch;
import db.retail.ent.criteria.OS_Search;
import java.util.Arrays;
import java.util.List;
import static mf.MyUI.DS_RETAIL;
import org.superb.apps.utilities.vaadin.Trees.CustomObjectTree;

/**
 *
 * @author Dobri
 */
public class Tree_R_FSPerformance extends CustomObjectTree<FS> {

    public Tree_R_FSPerformance(String caption, List treeItems) throws CustomTreeNodesEmptyException {
        super(caption, treeItems);
        expandItemsRecursively(treeItems);
    }

    public Tree_R_FSPerformance(String caption, FS rootitem) throws CustomTreeNodesEmptyException {
        super(caption, Arrays.asList(rootitem));
        expandItemsRecursively(rootitem);
    }

    @Override
    protected void createSubNodes(FS rootItem) {
        super.createSingleRootChildNodes(rootItem, (List) DS_RETAIL.getMD_FS_Performace_C().getMasterDetail(
                new OS_Search(new DateIntervalSearch("", ""), rootItem.getCode())
        ));

    }

}
