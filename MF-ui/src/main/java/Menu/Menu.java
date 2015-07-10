/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Menu;

import com.vaadin.ui.Tree;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author root
 */
public class Menu {

    private static Menu instance = null;

    private static List<MenuDefinitions> mainMenuItems;
    private static Map<MenuDefinitions, List<MenuDefinitions>> mainMenuSubOptions;

    private static final List<MenuDefinitions> allMenuItems = new ArrayList<>();

    private static Tree mainMenuTree;

    private Menu() {
        mainMenuItems = new ArrayList<>(MenuDefinitions.get_MainMenuItems());
        mainMenuSubOptions = new HashMap<>();
        mainMenuTree = new Tree();

        init();
        createTreeMenu();
    }

    public static Menu getDefault() {
        return instance == null ? instance = new Menu() : instance;
    }

    private void init() {
        //<editor-fold defaultstate="collapsed" desc="construct menu!">
        // Create submenu
        mainMenuSubOptions.put(MenuDefinitions.HSE_SYS_NOTIF_BOARD, MenuDefinitions.get_HSE_SubItems());
        mainMenuSubOptions.put(MenuDefinitions.HSE_WORKPLAN, MenuDefinitions.get_HSE_SubItems());

        mainMenuSubOptions.put(MenuDefinitions.RETAIL_SYS_NOTIF_BOARD, MenuDefinitions.get_HSE_SubItems());
        mainMenuSubOptions.put(MenuDefinitions.RETAIL_COCACALC, MenuDefinitions.get_HSE_SubItems());

        allMenuItems.addAll(mainMenuItems);
        allMenuItems.addAll(MenuDefinitions.get_HSE_SubItems());
        allMenuItems.addAll(MenuDefinitions.get_RETAIL_SubItems());
        //</editor-fold>
    }

    private void createTreeMenu() {
        mainMenuTree.addItems(mainMenuItems, mainMenuSubOptions.values());

        for (MenuDefinitions mainMenuItem : mainMenuItems) {
            mainMenuTree.setChildrenAllowed(mainMenuItem, true);

            if (mainMenuSubOptions.containsKey(mainMenuItem)) {
                List<MenuDefinitions> subMenuItems = mainMenuSubOptions.get(mainMenuItem);

                for (MenuDefinitions subMenuItem : subMenuItems) {
                    mainMenuTree.setParent(subMenuItem, mainMenuItem);
                    mainMenuTree.setChildrenAllowed(subMenuItem, false);
                    mainMenuTree.expandItemsRecursively(mainMenuItem);
                }
            }
        }
    }

    public List<MenuDefinitions> getMainMenuItems() {
        return mainMenuItems;
    }

    public List<MenuDefinitions> getMAIN_MENU_SUB_OPTIONS(MenuDefinitions key) {
        return mainMenuSubOptions.get(key);
    }

    public Tree getMainMenuTree() {
        return mainMenuTree;
    }

    public List<MenuDefinitions> getAllMenuItems() {
        return allMenuItems;
    }
}
