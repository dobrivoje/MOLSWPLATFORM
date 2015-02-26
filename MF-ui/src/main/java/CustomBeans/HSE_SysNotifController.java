/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CustomBeans;

import db.controllers.HSE.FSController;
import db.controllers.HSE.WPController;
import db.ent.HSE.FuelStation;
import db.ent.HSE.WorkPlan;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dprtenjak
 */
public class HSE_SysNotifController {

    private final WPController wpc = new WPController();
    private final FSController fsc = new FSController();

    public List<HSE_SysNotif_Bean> getNewestWorkPlans() {
        return null;
    }

    public List<HSE_SysNotif_Bean> getSysNotifBoard_Report1() {
        List<HSE_SysNotif_Bean> L = new ArrayList<>();

        for (FuelStation fs : fsc.getAll()) {
            HSE_SysNotif_Bean hse = new HSE_SysNotif_Bean();
            hse.setFs(fs);

            int sumFinishedWorkPlans = 0;

            for (WorkPlan wp : wpc.getFSWorkPlans(fs)) {
                if (wp.getFinished()) {
                    sumFinishedWorkPlans++;
                }
            }

            hse.setSumFinishedWorkPlans(sumFinishedWorkPlans);
            hse.setSumUnfinishedWorkPlans(wpc.getFSWorkPlans(fs).size() - sumFinishedWorkPlans);

            L.add(hse);
        }

        return L;
    }

}
