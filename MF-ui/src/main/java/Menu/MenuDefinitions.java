/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Menu;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author root
 */
public enum MenuDefinitions {

    // Main menu options
    MOL_SECTOR_HSE("HSE"),
    MOL_SECTOR_RETAIL("RETAIL"),
    //
    // Submenus
    //
    HSE_SYS_NOTIF_BOARD("HSE Dashboard"),
    HSE_WORKPLAN("Work Plans"),
    //
    // Retail submenus
    //
    RETAIL_SYS_NOTIF_BOARD("Retail Dashboard"),
    RETAIL_COCACALC("Coca Calculus"),
    RETAIL_COCACALC_PARTNERS("Partners"),
    RETAIL_COCACALC_PARTNERS_DATA("Partner Data"),
    RETAIL_COCACALC_PARTNERS_FS("Fuelstations"),
    RETAIL_COCACALC_PARTNERS_CONTRACTS("Contracts"),
    RETAIL_COCACALC_DATA_MAINTENENCE("Data Maintenance"),
    RETAIL_COCACALC_DATA_MAINTENENCE_CSR("Composite Sell Report"),
    RETAIL_COCACALC_DATA_MAINTENENCE_MAPPING("Mapping"),
    RETAIL_COCACALC_DATA_MAINTENENCE_KEY_DISTRIBUTION("Key Distribution"),
    RETAIL_COCACALC_DATA_MAINTENENCE_CATEGORIES("CATEGORIES");
    //

    private final String menuItem;

    private MenuDefinitions(String menuItem) {
        this.menuItem = menuItem;
    }

    @Override
    public String toString() {
        return menuItem;
    }

    public static List<MenuDefinitions> get_MainMenuItems() {
        return Arrays.asList(
                MOL_SECTOR_HSE, MOL_SECTOR_RETAIL
        );
    }

    public static List<MenuDefinitions> get_HSE_SubItems() {
        return Arrays.asList(
                HSE_SYS_NOTIF_BOARD,
                HSE_WORKPLAN
        );
    }

    public static List<MenuDefinitions> get_RETAIL_SubItems() {
        return Arrays.asList(
                RETAIL_SYS_NOTIF_BOARD,
                RETAIL_SYS_NOTIF_BOARD,
                RETAIL_COCACALC,
                RETAIL_COCACALC,
                RETAIL_COCACALC_PARTNERS,
                RETAIL_COCACALC_PARTNERS_DATA,
                RETAIL_COCACALC_PARTNERS_FS,
                RETAIL_COCACALC_PARTNERS_CONTRACTS,
                RETAIL_COCACALC_DATA_MAINTENENCE,
                RETAIL_COCACALC_DATA_MAINTENENCE_CSR,
                RETAIL_COCACALC_DATA_MAINTENENCE_MAPPING,
                RETAIL_COCACALC_DATA_MAINTENENCE_KEY_DISTRIBUTION,
                RETAIL_COCACALC_DATA_MAINTENENCE_CATEGORIES
        );
    }

    public static List<MenuDefinitions> get_RETAIL_COCA_SubItems() {
        return Arrays.asList(
                RETAIL_COCACALC_PARTNERS,
                RETAIL_COCACALC_PARTNERS_DATA,
                RETAIL_COCACALC_PARTNERS_FS,
                RETAIL_COCACALC_PARTNERS_CONTRACTS,
                RETAIL_COCACALC_DATA_MAINTENENCE,
                RETAIL_COCACALC_DATA_MAINTENENCE_CSR,
                RETAIL_COCACALC_DATA_MAINTENENCE_MAPPING,
                RETAIL_COCACALC_DATA_MAINTENENCE_KEY_DISTRIBUTION,
                RETAIL_COCACALC_DATA_MAINTENENCE_CATEGORIES
        );
    }
}
