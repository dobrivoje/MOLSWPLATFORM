/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dobrivoje.auth.roles;

import java.io.Serializable;

/**
 *
 * @author dobri
 */
public class Roles1 implements Serializable {

    public static final String R_MF_ROOTPRIVILEGES = "R_MF_RootPrivileges";
    public static final String R_MF_BACKOFFICE_MANAGER = "R_MF_BackOffice_Manager";
    public static final String R_MF_FSUSER = "R_MF_FSUser";

    public static final String P_MF_FS_USER_LOGIN = "R_MF_FSUser:login";
    public static final String P_MF_FS_USER_WORKPLAN_SEARCH_OWN = "R_MF_FSUser:search:OwnWorkplan";
    public static final String P_MF_FS_USER_WORKPLANS_EDIT_ALL = "R_MF_FSUser:edit:AllWorkplans";
    public static final String P_MF_FS_USER_WORKPLANS_EDIT_OWN = "R_MF_FSUser:edit:OwnWorkplans";

    public static String[] getAllRoles() {
        return new String[]{R_MF_ROOTPRIVILEGES, R_MF_BACKOFFICE_MANAGER, R_MF_FSUSER};
    }

    public static String[] getAllPermissions() {
        return new String[]{
            P_MF_FS_USER_LOGIN,
            P_MF_FS_USER_WORKPLAN_SEARCH_OWN,
            P_MF_FS_USER_WORKPLANS_EDIT_ALL,
            P_MF_FS_USER_WORKPLANS_EDIT_OWN
        };
    }
}
