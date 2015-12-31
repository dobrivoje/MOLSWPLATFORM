package RETAIL.Trees;

import db.Exceptions.CustomTreeNodesEmptyException;
import java.util.List;
import java.util.Map;
import org.superb.apps.utilities.vaadin.Trees.CustomObjectTree;

/**
 *
 * @author Dobri
 */
public class Tree_R_FSPerformance extends CustomObjectTree<String> {

    public Tree_R_FSPerformance(String caption, Map<String, List> tree) throws CustomTreeNodesEmptyException, NullPointerException {
        super(caption, tree);
    }
    
    // pozivanjem konstruktora sa mapom, donji metod NIJE POTREBAN !
    @Override
    protected void createSubNodes(String rootNode) {
    }

}
