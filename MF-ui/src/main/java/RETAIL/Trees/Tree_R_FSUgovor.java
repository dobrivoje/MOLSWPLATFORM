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
import java.util.List;
import java.util.stream.Collectors;
import org.superb.apps.utilities.vaadin.Trees.CustomObjectTree;

/**
 *
 * @author Dobri
 */
public class Tree_R_FSUgovor extends CustomObjectTree<FS> {
    
    public Tree_R_FSUgovor(FS fs) throws CustomTreeNodesEmptyException {
        this(Arrays.asList(fs), false);
    }
    
    public Tree_R_FSUgovor(List<FS> fuelStations, boolean expandRootNodes) throws CustomTreeNodesEmptyException {
        super("", fuelStations, expandRootNodes);
    }
    
    public Tree_R_FSUgovor(boolean expandRootNodes, boolean justCoca) throws CustomTreeNodesEmptyException {
        super("",
                DS_RETAIL.getASC_FS_C().getAll(false).stream().filter(p -> p.getCocaModel().equals(justCoca)).collect(Collectors.toList()),
                expandRootNodes
        );
    }
    
    @Override
    protected void createSubNodes(FS fs) {
        createChildNodesForTheRoot(fs, DS_RETAIL.getMD_Ugovor_C().getDetails(fs), expandRootNodes);
    }
    
}
