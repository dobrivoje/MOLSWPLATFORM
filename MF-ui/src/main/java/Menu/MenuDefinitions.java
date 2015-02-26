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
    //
    // Submenus
    //
    HSE_SYS_NOTIF_BOARD("System Notification Board"),
    HSE_WORKPLAN("Work Plan");

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
                MOL_SECTOR_HSE
        ));
    }

    public static List<MenuDefinitions> get_HSE_SubItems() {
        return new ArrayList<>(Arrays.asList(
                HSE_SYS_NOTIF_BOARD,
                HSE_WORKPLAN
        ));
    }
}
