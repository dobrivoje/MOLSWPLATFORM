package Views.MainMenu;

import com.vaadin.event.ItemClickEvent;
import java.util.HashMap;
import java.util.Map;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.server.Resource;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.Tree;
import com.vaadin.ui.themes.ValoTheme;
import Menu.Menu;
import Menu.MenuDefinitions;
import static Menu.MenuDefinitions.HSE_SYS_NOTIF_BOARD;
import static Menu.MenuDefinitions.HSE_WORKPLAN;
import static Menu.MenuDefinitions.MOL_SECTOR_HSE;
import static Menu.MenuDefinitions.MOL_SECTOR_RETAIL;
import static Menu.MenuDefinitions.RETAIL_COCACALC;
import static Menu.MenuDefinitions.RETAIL_SYS_NOTIF_BOARD;
import Views.MainMenu.HSE.HSE_SysNotifBoardView;
import Views.MainMenu.HSE.HSE_WorkPlanView;
import Views.MainMenu.RETAIL.RETAIL_SysNotifBoardView;

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
        Label title = new Label("MOL Serbia Sectors");
        title.addStyleName(ValoTheme.LABEL_H3);
        title.setSizeUndefined();
        Image image = new Image(null, new ThemeResource("img/table-logo.png"));
        image.setStyleName("logo");
        top.addComponent(image);
        top.addComponent(title);
        menuPart.addComponent(top);

        // logout menu item
        MenuBar logoutMenu = new MenuBar();
        logoutMenu.addItem("Logout", FontAwesome.SIGN_OUT, new Command() {
            @Override
            public void menuSelected(MenuItem selectedItem) {
                VaadinSession.getCurrent().getSession().invalidate();
                Page.getCurrent().reload();
            }
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
        Button button = new Button(caption, new ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                navigator.navigateTo(name);
            }
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

        mainMenuTree.expandItemsRecursively(MOL_SECTOR_HSE);
        mainMenuTree.expandItemsRecursively(MOL_SECTOR_RETAIL);

        // mainMenuTree.setChildrenAllowed(HSE_SYS_NOTIF_BOARD, true);
        mainMenuTree.setParent(HSE_SYS_NOTIF_BOARD, MOL_SECTOR_HSE);
        mainMenuTree.setParent(HSE_WORKPLAN, MOL_SECTOR_HSE);
        mainMenuTree.setChildrenAllowed(HSE_SYS_NOTIF_BOARD, false);
        mainMenuTree.setChildrenAllowed(HSE_WORKPLAN, false);

        mainMenuTree.setParent(RETAIL_SYS_NOTIF_BOARD, MOL_SECTOR_RETAIL);
        mainMenuTree.setParent(RETAIL_COCACALC, MOL_SECTOR_RETAIL);
        mainMenuTree.setChildrenAllowed(RETAIL_SYS_NOTIF_BOARD, false);
        mainMenuTree.setChildrenAllowed(RETAIL_COCACALC, false);

        //</editor-fold>
        mainMenuTree.addItemClickListener(new ItemClickEvent.ItemClickListener() {
            @Override
            public void itemClick(ItemClickEvent event) {
                switch ((MenuDefinitions) (event.getItemId())) {
                    case MOL_SECTOR_HSE:
                    case HSE_SYS_NOTIF_BOARD:
                        navigator.navigateTo(HSE_SysNotifBoardView.class.getSimpleName());
                        break;
                    case HSE_WORKPLAN:
                        navigator.navigateTo(HSE_WorkPlanView.class.getSimpleName());
                        break;

                    case MOL_SECTOR_RETAIL:
                    case RETAIL_SYS_NOTIF_BOARD:
                        navigator.navigateTo(RETAIL_SysNotifBoardView.class.getSimpleName());
                        break;
                }
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
