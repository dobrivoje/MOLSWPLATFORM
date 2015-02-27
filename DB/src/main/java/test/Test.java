/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import db.controllers.HSE.FSController;
import db.controllers.HSE.WPController;
import db.ent.HSE.FuelStation;
import db.ent.HSE.WorkPlan;

/**
 *
 * @author dprtenjak
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        WPController wpc = new WPController();
        int i = 0;
        for (WorkPlan fs : wpc.getNewestWorkPlans()) {
            System.err.println(
                    ++i + ". "
                    + fs.getStartDate() + ", "
                    + fs.getContractor() + ", "
                    + fs.getSubContractor() + ", "
                    + fs.getWorktype() + ", "
                    + fs.getFK_FuelStation().toString()
            );
        }

        System.err.println("How meny finished WPs ?");
        for (FuelStation fs : new FSController().getAll()) {
            System.err.println(fs.getName()+" - "+ wpc.getWorkPlansCountByStation(fs, true));
            System.err.println(fs.getName()+" - "+ wpc.getWorkPlansCountByStation(fs, false));
        }

    }

}
