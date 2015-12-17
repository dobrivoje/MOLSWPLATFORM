package MainMenu;

import com.vaadin.event.ItemClickEvent;
import java.util.HashMap;
import java.util.Map;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.server.Resource;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.Tree;
import com.vaadin.ui.themes.ValoTheme;
import Uni.Menu.Menu;
import static MainMenu.MenuDefinitions.HSE_SYS_NOTIF_BOARD;
import static MainMenu.MenuDefinitions.HSE_WORKPLAN;
import static MainMenu.MenuDefinitions.MOL_SECTOR_HSE;
import static MainMenu.MenuDefinitions.MOL_SECTOR_RETAIL;
import static MainMenu.MenuDefinitions.RETAIL_COCACALC;
import static MainMenu.MenuDefinitions.RETAIL_COCACALC_DATA_MAINTENENCE;
import static MainMenu.MenuDefinitions.RETAIL_COCACALC_DATA_MAINTENENCE_CATEGORIES;
import static MainMenu.MenuDefinitions.RETAIL_COCACALC_DATA_MAINTENENCE_CSR;
import static MainMenu.MenuDefinitions.RETAIL_COCACALC_PARTNERS_FS;
import static MainMenu.MenuDefinitions.RETAIL_COCACALC_DATA_MAINTENENCE_KEY_DISTRIBUTION;
import static MainMenu.MenuDefinitions.RETAIL_COCACALC_DATA_MAINTENENCE_MAPPING;
import static MainMenu.MenuDefinitions.RETAIL_COCACALC_PARTNERS;
import static MainMenu.MenuDefinitions.RETAIL_COCACALC_PARTNERS_CONTRACTS;
import static MainMenu.MenuDefinitions.RETAIL_SYS_NOTIF_BOARD;
import HSE.Views.View_HSE_SysNotifBoard;
import HSE.Views.View_HSE_WorkPlan;
import RETAIL.Views.View_RETAIL;
import RETAIL.Views.View_RETAIL_CocaCalc;
import RETAIL.Views.View_RETAIL_CocaCalc_DM_CSR;
import RETAIL.Views.View_RETAIL_CocaCalc_DM_MAPPING;
import RETAIL.Views.View_RETAIL_CocaCalc_PARTNERS;
import RETAIL.Views.View_RETAIL_CocaCalc_PA_CONTRACTS;
import RETAIL.Views.View_RETAIL_SysNotifBoard;

/**
 * Responsive navigation menu presenting a list of available views to the user.
 */
public class MainMenu extends CssLayout {

    private static final String VALO_MENUITEMS = "valo-menuitems";
    private static final String VALO_MENU_VISIBLE = "valo-menu-visible";
    private final Navigator navigator;
    private final Map<String, Button> viewButtons = new HashMap<>();
    private final Map<String, Tree> viewTrees = new HashMap<>();

    private final CssLayout menuItemsLayout;
    private final CssLayout menuPart;

    public MainMenu(Navigator navigator) {
        this.navigator = navigator;
        setPrimaryStyleName(ValoTheme.MENU_ROOT);
        menuPart = new CssLayout();
        menuPart.addStyleName(ValoTheme.MENU_PART);

        // header of the menu
        final HorizontalLayout top = new HorizontalLayout();
        top.setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);
        top.addStyleName(ValoTheme.MENU_TITLE);
        top.setSpacing(true);
        Label title = new Label("Company Sectors");
        title.addStyleName(ValoTheme.LABEL_H3);
        title.setSizeUndefined();
        //Image image = new Image(null, new ThemeResource("img/table-logo.png"));
        //image.setStyleName("logo");
        //top.addComponent(image);
        top.addComponent(title);
        menuPart.addComponent(top);

        // logout menu item
        MenuBar logoutMenu = new MenuBar();
        logoutMenu.addItem("Logout", FontAwesome.SIGN_OUT, (MenuItem selectedItem) -> {
            VaadinSession.getCurrent().getSession().invalidate();
            Page.getCurrent().reload();
        });

        logoutMenu.addStyleName("user-menu");
        menuPart.addComponent(logoutMenu);

        // container for the navigation buttons, which are added by addView()
        menuItemsLayout = new CssLayout();
        menuItemsLayout.setPrimaryStyleName(VALO_MENUITEMS);
        menuPart.addComponent(menuItemsLayout);

        addComponent(menuPart);
    }

    // Register a pre-created view instance in the navigation menu and in the
    // Navigator}.
    //<editor-fold defaultstate="collapsed" desc="Pre-created views registration !">
    public void addView(View view, final String name, String caption, Resource icon) {
        navigator.addView(name, view);
        createViewButton(name, caption, icon);
    }

    public void addViewButton(View view, final String name, String caption, Resource icon) {
        navigator.addView(name, view);
        createViewButton(name, caption, icon);
    }

    public void addViewTree(View view, final String name, String caption) {
        navigator.addView(name, view);
        createViewTree(name);
    }

    private void createViewButton(final String name, String caption, Resource icon) {
        Button button = new Button(caption, (ClickEvent event) -> {
            navigator.navigateTo(name);
        });
        button.setPrimaryStyleName(ValoTheme.MENU_ITEM);
        button.setIcon(icon);
        menuItemsLayout.addComponent(button);
        viewButtons.put(name, button);
    }
    //</editor-fold>

    private void createViewTree(final String name) {
        Tree mainMenuTree = new Tree();

        //<editor-fold defaultstate="collapsed" desc="Menu UI Defs">
        mainMenuTree.addItems(Menu.getDefault().getAllMenuItems());

        // --HSE
        // --RETAIL
        // mainMenuTree.expandItemsRecursively(MOL_SECTOR_HSE);
        mainMenuTree.expandItemsRecursively(MOL_SECTOR_RETAIL);

        // --HSE
        // --HSE
        // ------HSE_SYS_NOTIF_BOARD
        // ------HSE_WORKPLAN
        //
        mainMenuTree.setParent(HSE_SYS_NOTIF_BOARD, MOL_SECTOR_HSE);
        mainMenuTree.setParent(HSE_WORKPLAN, MOL_SECTOR_HSE);
        mainMenuTree.setChildrenAllowed(HSE_SYS_NOTIF_BOARD, false);
        mainMenuTree.setChildrenAllowed(HSE_WORKPLAN, false);

        // --RETAIL
        // ------RETAIL_SYS_NOTIF_BOARD
        // ------RETAIL_COCACALC
        //
        mainMenuTree.setParent(RETAIL_SYS_NOTIF_BOARD, MOL_SECTOR_RETAIL);
        mainMenuTree.setParent(RETAIL_COCACALC, MOL_SECTOR_RETAIL);
        mainMenuTree.setChildrenAllowed(RETAIL_SYS_NOTIF_BOARD, false);

        // --RETAIL
        // ------RETAIL_SYS_NOTIF_BOARD
        // ----------RETAIL_COCACALC_PARTNERS
        // ----------RETAIL_COCACALC_DATA_MAINTENENCE
        //
        mainMenuTree.expandItemsRecursively(RETAIL_COCACALC);
        mainMenuTree.setParent(RETAIL_COCACALC_PARTNERS, RETAIL_COCACALC);
        mainMenuTree.setParent(RETAIL_COCACALC_DATA_MAINTENENCE, RETAIL_COCACALC);
        // --RETAIL
        // ------RETAIL_SYS_NOTIF_BOARD
        // ----------RETAIL_COCACALC_PARTNERS
        // --------------RETAIL_COCACALC_PARTNERS_CONTRACTS
        // --------------RETAIL_COCACALC_PARTNERS_FS
        //
        mainMenuTree.expandItemsRecursively(RETAIL_COCACALC_PARTNERS);
        mainMenuTree.setParent(RETAIL_COCACALC_PARTNERS_CONTRACTS, RETAIL_COCACALC_PARTNERS);
        mainMenuTree.setParent(RETAIL_COCACALC_PARTNERS_FS, RETAIL_COCACALC_PARTNERS);
        mainMenuTree.setChildrenAllowed(RETAIL_COCACALC_PARTNERS_FS, false);
        mainMenuTree.setChildrenAllowed(RETAIL_COCACALC_PARTNERS_CONTRACTS, false);

        // --RETAIL
        // ------RETAIL_SYS_NOTIF_BOARD
        // ----------RETAIL_COCACALC_DATA_MAINTENENCE
        // --------------RETAIL_COCACALC_DATA_MAINTENENCE_MAPPING
        // --------------RETAIL_COCACALC_DATA_MAINTENENCE_KEY_DISTRIBUTION
        // --------------RETAIL_COCACALC_DATA_MAINTENENCE_CATEGORIES
        //
        mainMenuTree.expandItemsRecursively(RETAIL_COCACALC_DATA_MAINTENENCE);
        mainMenuTree.setParent(RETAIL_COCACALC_DATA_MAINTENENCE_CSR, RETAIL_COCACALC_DATA_MAINTENENCE);
        mainMenuTree.setParent(RETAIL_COCACALC_DATA_MAINTENENCE_MAPPING, RETAIL_COCACALC_DATA_MAINTENENCE);
        mainMenuTree.setParent(RETAIL_COCACALC_DATA_MAINTENENCE_KEY_DISTRIBUTION, RETAIL_COCACALC_DATA_MAINTENENCE);
        mainMenuTree.setParent(RETAIL_COCACALC_DATA_MAINTENENCE_CATEGORIES, RETAIL_COCACALC_DATA_MAINTENENCE);
        mainMenuTree.setChildrenAllowed(RETAIL_COCACALC_DATA_MAINTENENCE_CSR, false);
        mainMenuTree.setChildrenAllowed(RETAIL_COCACALC_DATA_MAINTENENCE_MAPPING, false);
        mainMenuTree.setChildrenAllowed(RETAIL_COCACALC_DATA_MAINTENENCE_KEY_DISTRIBUTION, false);
        mainMenuTree.setChildrenAllowed(RETAIL_COCACALC_DATA_MAINTENENCE_CATEGORIES, false);
        //</editor-fold>

        mainMenuTree.addItemClickListener((ItemClickEvent event) -> {
            switch ((MenuDefinitions) (event.getItemId())) {
                case MOL_SECTOR_HSE:
                case HSE_SYS_NOTIF_BOARD:
                    navigator.navigateTo(View_HSE_SysNotifBoard.class.getSimpleName());
                    break;
                case HSE_WORKPLAN:
                    navigator.navigateTo(View_HSE_WorkPlan.class.getSimpleName());
                    break;

                case MOL_SECTOR_RETAIL:
                case RETAIL_SYS_NOTIF_BOARD:
                    navigator.navigateTo(View_RETAIL_SysNotifBoard.class.getSimpleName());
                    break;

                case RETAIL_COCACALC:
                    navigator.navigateTo(View_RETAIL.class.getSimpleName());
                    break;

                case RETAIL_COCACALC_PARTNERS_FS:
                    navigator.navigateTo(View_RETAIL_CocaCalc.class.getSimpleName());
                    break;

                case RETAIL_COCACALC_DATA_MAINTENENCE_CSR:
                    navigator.navigateTo(View_RETAIL_CocaCalc_DM_CSR.class.getSimpleName());
                    break;

                case RETAIL_COCACALC_DATA_MAINTENENCE_MAPPING:
                    navigator.navigateTo(View_RETAIL_CocaCalc_DM_MAPPING.class.getSimpleName());
                    break;
                case RETAIL_COCACALC_PARTNERS_CONTRACTS:
                    navigator.navigateTo(View_RETAIL_CocaCalc_PA_CONTRACTS.class.getSimpleName());
                    break;
                    
                case RETAIL_COCACALC_PARTNERS:
                    navigator.navigateTo(View_RETAIL_CocaCalc_PARTNERS.class.getSimpleName());
                    break;
            }
        });

        mainMenuTree.setPrimaryStyleName(ValoTheme.MENU_ITEM);
        menuItemsLayout.addComponent(mainMenuTree);
        viewTrees.put(name, mainMenuTree);
    }

    /**
     * Highlights a view navigation button as the currently active view in the
     * menu. This method does not perform the actual navigation.
     *
     * @param viewName the name of the view to show as active
     */
    public void setActiveViewButton(String viewName) {
        for (Button button : viewButtons.values()) {
            button.removeStyleName("selected");
        }
        Button selected = viewButtons.get(viewName);
        if (selected != null) {
            selected.addStyleName("selected");
        }
        menuPart.removeStyleName(VALO_MENU_VISIBLE);
    }

    public void setActiveTreeView(String viewName) {
        for (Tree subTree : viewTrees.values()) {
            subTree.removeStyleName("selected");
        }
        Tree selected = viewTrees.get(viewName);
        if (selected != null) {
            selected.addStyleName("selected");
        }
        menuPart.removeStyleName(VALO_MENU_VISIBLE);
    }
}
