/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import dataservice.DataService;
import db.ent.HSE.FuelStation;

/**
 *
 * @author dprtenjak
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DataService DS = DataService.getDefault();
        for (FuelStation r : DS.getFSController().getAll()) {
            System.err.println(r.getName() + ", how meny finished WPs : "
                    + DS.getWPController().getWorkPlansCountByStation(r, true)
            );
        }
    }
}
