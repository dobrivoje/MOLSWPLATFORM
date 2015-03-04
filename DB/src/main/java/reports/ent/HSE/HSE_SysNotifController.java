/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reports.ent.HSE;

import db.DBHandler;
import db.ent.HSE.FuelStation;
import db.ent.HSE.WorkPlan;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dprtenjak
 */
public class HSE_SysNotifController {

    private static DBHandler DBH;

    public HSE_SysNotifController(DBHandler DBH) {
        HSE_SysNotifController.DBH = DBH;
    }

    public List<HSE_SysNotif_Bean> getNewestWorkPlans() {
        return null;
    }

    /**
     * Izveštaj 1 : Za svaku stanicu vrati bean HSE_SysNotif_Bean odn. ID
     * stanice, uk. završenih poslova, i uk. broj poslova.
     *
     * @return List<HSE_SysNotif_Bean>
     */
    public List<HSE_SysNotif_Bean> getSysNotifBoard_Report1() {
        List<HSE_SysNotif_Bean> L = new ArrayList<>();

        for (FuelStation fs : DBH.getAllFuelStations()) {
            HSE_SysNotif_Bean hse = new HSE_SysNotif_Bean();
            hse.setFs(fs);

            int sumFinishedWorkPlans = 0;

            for (WorkPlan wp : DBH.getFSWorkPlans(fs)) {
                if (wp.getFinished()) {
                    sumFinishedWorkPlans++;
                }
            }

            hse.setSumFinishedWorkPlans(sumFinishedWorkPlans);
            hse.setTotal(DBH.getFSWorkPlans(fs).size());

            L.add(hse);
        }

        return L;
    }

}
