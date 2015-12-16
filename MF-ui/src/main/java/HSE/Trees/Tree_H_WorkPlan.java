/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HSE.Trees;

import db.Exceptions.CustomTreeNodesEmptyException;
import db.HSE.ent.WorkPlan;
import java.util.List;
import org.superb.apps.utilities.vaadin.Trees.CustomTree;

/**
 *
 * @author Dobri
 */
public class Tree_H_WorkPlan extends CustomTree<WorkPlan> {

    public Tree_H_WorkPlan(String caption, List treeItems) throws CustomTreeNodesEmptyException {
        super(caption, treeItems);
    }

}
