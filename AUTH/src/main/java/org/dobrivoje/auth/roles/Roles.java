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
public class Roles implements Serializable {

    public static final String R_MF_ROOT_PRIVILEGES = "R_MF_RootPrivileges";
    public static final String R_MF_OFFICE_MANAGER = "R_MF_OfficeManager";
    public static final String R_MF_FS_USER = "R_MF_FS_User";

    public static final String P_MF_FS_USER_LOGIN = "R_MF_FSUser:login";
    public static final String P_MF_FS_USER_WORKPLAN_SEARCH_OWN = "R_MF_FSUser:search:OwnWorkplan";
    public static final String P_MF_FS_USER_WORKPLANS_EDIT_ALL = "R_MF_FSUser:edit:AllWorkplans";
    public static final String P_MF_FS_USER_WORKPLANS_EDIT_OWN = "R_MF_FSUser:edit:OwnWorkplans";

    public static String[] getAllRoles() {
        return new String[]{R_MF_ROOT_PRIVILEGES, R_MF_OFFICE_MANAGER, R_MF_FS_USER};
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
