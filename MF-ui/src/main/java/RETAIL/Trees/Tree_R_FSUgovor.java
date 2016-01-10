/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RETAIL.Trees;

import db.Exceptions.CustomTreeNodesEmptyException;
import db.retail.ent.FS;
import java.util.Arrays;
import static mf.MyUI.DS_RETAIL;
import org.superb.apps.utilities.vaadin.Trees.CustomObjectTree;

/**
 *
 * @author Dobri
 */
public class Tree_R_FSUgovor extends CustomObjectTree<FS> {

    public Tree_R_FSUgovor(FS fs) throws CustomTreeNodesEmptyException {
        super("", Arrays.asList(fs));
    }

    @Override
    protected void createSubNodes(FS fs) {
        createChildNodesForTheRoot(fs, DS_RETAIL.getMD_Ugovor_C().getDetails(fs));
    }

}
