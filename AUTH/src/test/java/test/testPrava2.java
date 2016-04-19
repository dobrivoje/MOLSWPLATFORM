/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.dobrivoje.auth.roles.Roles;

/**
 *
 * @author DPrtenjak
 */
public class testPrava2 {

    public static Map<Object, String> getRoles() {
        Map<Object, String> M = new HashMap<>();

        M.put(Roles.R.R_MF_ROOTPRIVILEGES, "R_MF_RootPrivileges");
        M.put(Roles.R.R_MF_RETAIL_BACKOFFICE_MANAGER, "R_MF_Retail_BackOffice_Manager");
        M.put(Roles.R.R_MF_RETAIL_BACKOFFICE_USER, "R_MF_Retail_BackOffice_User");
        M.put(Roles.R.R_MF_FS_USER, "R_MF_FSUser");

        return M;
    }

    public static Map<Object, String> getPermissions() {
        Map<Object, String> M = new HashMap<>();

        M.put(Roles.P.P_MF_FS_USER_LOGIN, "R_MF_FSUser:login");
        M.put(Roles.P.P_MF_FS_USER_WORKPLAN_SEARCH_OWN, "R_MF_FSUser:login");
        M.put(Roles.P.P_MF_FS_USER_WORKPLANS_EDIT_ALL, "R_MF_FSUser:edit:AllWorkplans");
        M.put(Roles.P.P_MF_FS_USER_WORKPLANS_EDIT_OWN, "R_MF_FSUser:edit:OwnWorkplans");

        return M;
    }

    public static void main(String[] args) {
        System.err.println("Roles : " + getRoles());
        System.err.println("-----------------------------------");

        System.err.println("Permissions : " + getPermissions());
        System.err.println("-----------------------------------");

        List LR = Arrays.asList(getRoles().keySet().stream().filter(p -> p.toString().contains("R_")).toArray());
        List LP = Arrays.asList(getPermissions().keySet().stream().filter(p -> p.toString().contains("P_")).toArray());

        System.err.println("LR : " + LR);
        System.err.println("LP : " + LP);

    }

}
