package RETAIL.Trees;

import db.Exceptions.CustomTreeNodesEmptyException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import static mf.MyUI.DS_RETAIL;
import org.superb.apps.utilities.vaadin.Trees.CustomObjectTree;

/**
 *
 * @author Dobri
 */
public class Tree_R_FSPerformance extends CustomObjectTree<String> {

    public Tree_R_FSPerformance(Map<String, List> customTree) throws CustomTreeNodesEmptyException, NullPointerException {
        super(new ArrayList(customTree.keySet()));
    }

    @Override
    protected void createSubNodes(String rootNode) {
        createChildNodesForTheRoot(rootNode, DS_RETAIL.getMD_FS_Performace_C().getDetails(rootNode), true);
    }

}
