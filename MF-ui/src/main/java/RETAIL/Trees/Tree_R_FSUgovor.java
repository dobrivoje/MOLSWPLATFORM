/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RETAIL.Trees;

import db.Exceptions.CustomTreeNodesEmptyException;
import db.retail.ent.FS;
import java.util.Arrays;
import static Main.MyUI.DS_RETAIL;
import java.util.stream.Collectors;
import org.superb.apps.utilities.vaadin.Trees.CustomObjectTree;

/**
 *
 * @author Dobri
 */
public class Tree_R_FSUgovor extends CustomObjectTree<FS> {
    
    public Tree_R_FSUgovor(FS fs) throws CustomTreeNodesEmptyException {
        this(fs, false);
    }
    
    public Tree_R_FSUgovor(FS fs, boolean expandRootNodes) throws CustomTreeNodesEmptyException {
        super("", Arrays.asList(fs), expandRootNodes);
    }
    
    public Tree_R_FSUgovor(boolean expandRootNodes, boolean justCoca) throws CustomTreeNodesEmptyException {
        super("",
                DS_RETAIL.getASC_FS_C().getAll(false).stream().filter(p -> p.getCocaModel().equals(true)).collect(Collectors.toList()),
                expandRootNodes
        );
    }
    
    @Override
    protected void createSubNodes(FS fs) {
        createChildNodesForTheRoot(fs, DS_RETAIL.getMD_Ugovor_C().getDetails(fs), expandRootNodes);
    }
    
}
