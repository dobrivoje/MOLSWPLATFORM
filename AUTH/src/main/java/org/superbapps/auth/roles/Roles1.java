/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.superbapps.auth.roles;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author dobri
 */
public enum Roles1 {

    //<editor-fold defaultstate="collapsed" desc="MF">
    //
    // GENERAL MF
    //
    R_MF_ROOTPRIVILEGES("R_MF_RootPrivileges"),
    R_MF_LOGIN("R_MF_LOGIN"),
    R_MF_RETAIL_BACKOFFICE_MANAGER("R_MF_RETAIL_BACKOFFICE_MANAGER"),
    R_MF_RETAIL_BACKOFFICE_USER("R_MF_RETAIL_BACKOFFICE_USER"),
    R_MF_FS_USER("R_MF_FS_USER"),
    R_MF_HSE_USER("R_MF_HSE_USER"),
    //
    // COCA CALC APP
    //
    APP_COCACALC_MAINTENENCE("app:cocacalc:maintenence"),
    APP_COCACALC_OWNREPORT_READ("app:cocacalc:ownreport:read"),
    //
    // PERMISSIONS RETAIL
    //
    P_MF_RETAIL_BACKOFFICE_USER_REPORT_READ("p_mf:retail:backoffice:user:report:read"),
    P_MF_RETAIL_BACKOFFICE_USER_REPORT_GENERATE("p_mf:retail:backoffice:user:report:generate"),
    P_MF_FS_REPORT_SPEC("p_mf:fs:user:reports:specifikacija"),
    P_MF_FS_REPORT_OBRACUN("p_mf:fs:user:reports:obracun"),
    //
    // PERMISSIONS HSE
    //
    P_MF_HSE_FS_USER_OWNWP_EDIT("p_mf:hse:user:wp:own:edit"),
    P_MF_HSE_FS_USER_OWNWP_SEARCH("p_mf:hse:user:wp:own:search"),
    P_MF_HSE_FS_USER_ALLWP_SEARCH("p_mf:hse:fs:user:wp:all:search"),
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="WS">
    //
    // GENERAL WS
    //
    R_WS_ROOTPRIVILEGES("R_WS_ROOTPRIVILEGES"),
    R_WS_LOGIN("R_WS_LOGIN"),
    R_WS_CARDS_MANAGER("R_WS_CARDS_MANAGER"),
    R_WS_CARDS_USER("R_WS_CARDS_USER"),
    R_WS_FS_MANAGER("R_WS_FS_MANAGER"),
    R_WS_FS_USER("R_WS_FS_USER"),
    R_WS_CRM_READ("R_WS_CRM_READ"),
    R_WS_CRM_MAINTENANCE("R_WS_CRM_MAINTENANCE"),
    //
    // EXCEL ROLES
    //
    R_WS_EXCEL_ALL("R_WS_EXCEL_ALL"),
    //
    // PERMISSIONS CUSTOMERS
    //
    P_WS_CUSTOMERS_READ("p_ws:customers:read"),
    P_WS_CUSTOMERS_MAINTENANCE("p_ws:customers:maintenance"),
    //
    // PERMISSIONS CRM
    //
    P_WS_CRM_READ("p_ws:crm:read"),
    P_WS_CRM_MAINTENANCE("p_ws:crm:maintenance"),
    //
    // PERMISSIONS SELL EXCEL
    //
    P_WS_EXCEL_IMPORT("p_ws:excel:import"),
    P_WS_EXCEL_EXPORT("p_ws:excel:export"),
    //
    // PERMISSIONS WS CARDS
    //
    P_WS_CARDS_ALL("p_ws:cards:*"),
    P_WS_CARDS_READ("p_ws:cards:read"),
    //
    // PERMISSIONS FUELSTATIONS MANAGEMENT
    //
    P_WS_FS_READ("p_ws:fs:read"),
    P_WS_FS_MAINTENANCE("p_ws:fs:maintenance");
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Support..">
    //<editor-fold defaultstate="collapsed" desc="System Support">
    private final String value;

    private Roles1(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

    public static List<Roles1> getApp_MF_Roles() {
        return filterByName("R_MF");
    }

    public static List<Roles1> getApp_MF_Permissions() {
        return filterByName("P_MF");
    }

    public static List<Roles1> getApp_WS_Roles() {
        return filterByName("R_WS");
    }

    public static List<Roles1> getApp_WS_Permissions() {
        return filterByName("P_WS");
    }

    private static List<Roles1> filterByName(String what) {
        return Arrays.asList(Roles1.values())
                .stream()
                .filter((Roles1 r) -> r.name().startsWith(what))
                .collect(Collectors.toList());

    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Test">
    public static void main(String[] args) {
        System.err.println("test 1");
        System.err.println(Roles1.getApp_MF_Roles());

        System.err.println("test 2");
        System.err.println(Roles1.getApp_MF_Permissions());
        
        System.err.println("test 3");
        System.err.println(Roles1.getApp_WS_Roles());

        System.err.println("test 4");
        System.err.println(Roles1.getApp_WS_Permissions());
        
    }
    //</editor-fold>
    //</editor-fold>

}
