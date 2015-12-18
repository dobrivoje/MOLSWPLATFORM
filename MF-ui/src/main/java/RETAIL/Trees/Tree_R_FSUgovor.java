/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RETAIL.Trees;

import db.Exceptions.CustomTreeNodesEmptyException;
import db.retail.ent.FS;
import java.util.Arrays;
import java.util.List;
import static mf.MyUI.DS_RETAIL;
import org.superb.apps.utilities.vaadin.Trees.CustomObjectTree;

/**
 *
 * @author Dobri
 */
public class Tree_R_FSUgovor extends CustomObjectTree<FS> {
    
    public Tree_R_FSUgovor(String caption, List treeItems) throws CustomTreeNodesEmptyException {
        super(caption, treeItems);
        expandItemsRecursively(treeItems);
    }
    
    public Tree_R_FSUgovor(String caption, FS fs) throws CustomTreeNodesEmptyException {
        super(caption, Arrays.asList(fs));
        expandItemsRecursively(fs);
    }
    
    @Override
    protected void createSubNodes(FS fs) {
        super.createSingleRootChildNodes(fs, DS_RETAIL.getMD_Ugovor_C().getDetails(fs));
    }
    
}
