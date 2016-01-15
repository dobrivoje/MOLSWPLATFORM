package RETAIL.Trees;

import db.Exceptions.CustomTreeNodesEmptyException;
import db.retail.ent.Partner;
import java.util.Arrays;
import static Main.MyUI.DS_RETAIL;
import org.superb.apps.utilities.vaadin.Trees.CustomObjectTree;

/**
 *
 * @author Dobri
 */
public class Tree_R_PartnerUgovor extends CustomObjectTree<Partner> {

    public Tree_R_PartnerUgovor(Partner rootItem) throws CustomTreeNodesEmptyException {
        this(rootItem, false);
    }

    public Tree_R_PartnerUgovor(Partner rootItem, boolean expandRootNodes) throws CustomTreeNodesEmptyException {
        super("", Arrays.asList(rootItem), expandRootNodes);
    }

    @Override
    protected void createSubNodes(Partner rootItem) {
        createChildNodesForTheRoot(rootItem, DS_RETAIL.getMD_Partner_C().getDetails(rootItem), expandRootNodes);
    }

}
