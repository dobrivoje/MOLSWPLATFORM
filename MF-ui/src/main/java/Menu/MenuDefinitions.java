/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Menu;

import java.util.ArrayList;
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
    HSE_SYS_NOTIF_BOARD("HSE Notification Board"),
    HSE_WORKPLAN("Work Plans"),
    //
    RETAIL_SYS_NOTIF_BOARD("Retail Notification Board"),
    RETAIL_COCACALC("Coca calculator");
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
        return new ArrayList<>(Arrays.asList(
                MOL_SECTOR_HSE, MOL_SECTOR_RETAIL
        ));
    }

    public static List<MenuDefinitions> get_HSE_SubItems() {
        return new ArrayList<>(Arrays.asList(
                HSE_SYS_NOTIF_BOARD,
                HSE_WORKPLAN
        ));
    }

    public static List<MenuDefinitions> get_RETAIL_SubItems() {
        return new ArrayList<>(Arrays.asList(
                RETAIL_SYS_NOTIF_BOARD,
                RETAIL_COCACALC
        ));
    }
}
