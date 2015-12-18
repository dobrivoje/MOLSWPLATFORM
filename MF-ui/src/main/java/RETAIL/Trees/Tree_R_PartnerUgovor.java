package RETAIL.Trees;

import db.Exceptions.CustomTreeNodesEmptyException;
import db.retail.ent.Partner;
import java.util.Arrays;
import java.util.List;
import static mf.MyUI.DS_RETAIL;
import org.superb.apps.utilities.vaadin.Trees.CustomObjectTree;

/**
 *
 * @author Dobri
 */
public class Tree_R_PartnerUgovor extends CustomObjectTree<Partner> {

    public Tree_R_PartnerUgovor(String caption, List treeItems) throws CustomTreeNodesEmptyException {
        super(caption, treeItems);
        expandItemsRecursively(treeItems);
    }

    public Tree_R_PartnerUgovor(String caption, Partner rootitem) throws CustomTreeNodesEmptyException {
        super(caption, Arrays.asList(rootitem));
        expandItemsRecursively(rootitem);
    }

    @Override
    protected void createSubNodes(Partner rootItem) {
        super.createSingleRootChildNodes(rootItem, DS_RETAIL.getMD_Partner_C().getDetails(rootItem));
    }

}
