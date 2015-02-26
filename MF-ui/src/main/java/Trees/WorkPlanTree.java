/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Trees;

import db.ent.HSE.WorkPlan;
import java.util.List;
import org.superb.apps.utilities.vaadin.Trees.CustomTree;

/**
 *
 * @author root
 */
public class WorkPlanTree extends CustomTree<WorkPlan> {

    public WorkPlanTree(String caption, List treeItems) {
        super(caption, treeItems);
    }

}
