/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dobrivoje.auth.roles;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author dobri
 */
public class Roles implements Serializable {

    //<editor-fold defaultstate="collapsed" desc="enums defs">
    public enum R {

        R_MF_ROOTPRIVILEGES,
        R_MF_RETAIL_BACKOFFICE_MANAGER,
        R_MF_RETAIL_BACKOFFICE_USER,
        R_MF_FS_USER
    }

    public enum P {

        P_MF_FS_USER_LOGIN,
        P_MF_FS_USER_WORKPLAN_SEARCH_OWN,
        P_MF_FS_USER_WORKPLANS_EDIT_ALL,
        P_MF_FS_USER_WORKPLANS_EDIT_OWN
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="General MF Rights">
    public static final String R_MF_ROOTPRIVILEGES = "R_MF_RootPrivileges";
    public static final String R_MF_LOGIN = "R_MF_Login";
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Retail Rights">
    public static final String R_MF_RETAIL_BACKOFFICE_MANAGER = "R_MF_Retail_BackOffice_Manager";
    public static final String R_MF_RETAIL_BACKOFFICE_USER = "R_MF_Retail_BackOffice_User";
    public static final String R_MF_FS_USER = "R_MF_FS_User";
    public static final String R_MF_HSE_USER = "R_MF_HSE_FS_User";
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Application Rights">
    //<editor-fold defaultstate="collapsed" desc="COCA CALCULUS RIGHTS">
    public static final String APP_COCACALC_MAINTENENCE = "app:cocacalc:maintenence";
    public static final String APP_COCACALC_OWNREPORT_READ = "app:cocacalc:ownreport:read";
    //</editor-fold>
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="permissions">
    //<editor-fold defaultstate="collapsed" desc="retail">
    public static final String P_MF_RETAIL_BACKOFFICE_USER_REPORT_READ = "R_MF_Retail_BackOffice_User:report:read";
    public static final String P_MF_RETAIL_BACKOFFICE_USER_REPORT_GENERATE = "R_MF_Retail_BackOffice_User:report:generate";
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="hse">
    public static final String P_MF_HSE_FS_USER_OWNWP_EDIT = "R_MF_HSE_FS_User:wp:own:edit";
    public static final String P_MF_HSE_FS_USER_OWNWP_SEARCH = "R_MF_HSE_FS_User:wp:own:search";
    public static final String P_MF_HSE_FS_USER_ALLWP_SEARCH = "R_MF_HSE_FS_User:wp:all:search";
    //</editor-fold>
    //</editor-fold>

    public static String[] getAllRoles() {
        return new String[]{
            R_MF_ROOTPRIVILEGES,
            R_MF_LOGIN,
            R_MF_RETAIL_BACKOFFICE_MANAGER,
            R_MF_RETAIL_BACKOFFICE_USER,
            R_MF_FS_USER,
            R_MF_HSE_USER
        };
    }

    public static String[] getAllPermissions() {
        return new String[]{
            P_MF_RETAIL_BACKOFFICE_USER_REPORT_READ,
            P_MF_RETAIL_BACKOFFICE_USER_REPORT_GENERATE,
            APP_COCACALC_MAINTENENCE,
            APP_COCACALC_OWNREPORT_READ,
            P_MF_HSE_FS_USER_OWNWP_EDIT,
            P_MF_HSE_FS_USER_OWNWP_SEARCH,
            P_MF_HSE_FS_USER_ALLWP_SEARCH
        };
    }

    //<editor-fold defaultstate="collapsed" desc="enum getters">
    public static Map<Object, String> getRoles() {
        Map<Object, String> M = new HashMap<>();

        M.put(R.R_MF_ROOTPRIVILEGES, "R_MF_RootPrivileges");
        M.put(R.R_MF_RETAIL_BACKOFFICE_MANAGER, "R_MF_Retail_BackOffice_Manager");
        M.put(R.R_MF_RETAIL_BACKOFFICE_USER, "R_MF_Retail_BackOffice_User");
        M.put(R.R_MF_FS_USER, "R_MF_FSUser");

        return M;
    }

    public static Map<Object, String> getPermissions() {
        Map<Object, String> M = new HashMap<>();

        M.put(P.P_MF_FS_USER_LOGIN, "R_MF_FSUser:login");
        M.put(P.P_MF_FS_USER_WORKPLAN_SEARCH_OWN, "R_MF_FSUser:login");
        M.put(P.P_MF_FS_USER_WORKPLANS_EDIT_ALL, "R_MF_FSUser:edit:AllWorkplans");
        M.put(P.P_MF_FS_USER_WORKPLANS_EDIT_OWN, "R_MF_FSUser:edit:OwnWorkplans");

        return M;
    }
    //</editor-fold>

}
