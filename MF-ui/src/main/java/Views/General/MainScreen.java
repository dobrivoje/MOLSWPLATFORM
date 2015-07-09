package Views.General;

import Views.MainMenu.HSE.HSE_SysNotifBoardView;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import Views.MainMenu.HSE.HSE_WorkPlanView;
import Views.MainMenu.MainMenu;
import ws.MyUI;

/**
 * Content of the UI when the user is logged in.
 *
 *
 */
public class MainScreen extends HorizontalLayout {

    private MainMenu menu;

    public MainScreen(MyUI ui) {

        setStyleName("main-screen");

        CssLayout viewContainer = new CssLayout();
        viewContainer.addStyleName("valo-content");
        viewContainer.setSizeFull();

        final Navigator navigator = new Navigator(ui, viewContainer);
        navigator.setErrorView(ErrorView.class);
        menu = new MainMenu(navigator);
        menu.addViewTree(new HSE_WorkPlanView(), HSE_WorkPlanView.class.getSimpleName(), HSE_WorkPlanView.class.getSimpleName());
        menu.addViewButton(new AboutView(), AboutView.class.getSimpleName(), AboutView.class.getSimpleName(), FontAwesome.INFO_CIRCLE);

        // DODAVANJE VIEW-ova NOVIH AKCIJA GL. MENIJA :
        navigator.addView(EmptyView.class.getSimpleName(), EmptyView.class);
        navigator.addView(HSE_WorkPlanView.class.getSimpleName(), HSE_WorkPlanView.class);
        navigator.addView(HSE_SysNotifBoardView.class.getSimpleName(), HSE_SysNotifBoardView.class);

        navigator.addViewChangeListener(viewChangeListener);

        addComponent(menu);
        addComponent(viewContainer);
        setExpandRatio(viewContainer, 1);
        setSizeFull();
    }

    // notify the view menu about view changes so that it can display which view
    // is currently active
    ViewChangeListener viewChangeListener = new ViewChangeListener() {

        @Override
        public boolean beforeViewChange(ViewChangeEvent event) {
            return true;
        }

        @Override
        public void afterViewChange(ViewChangeEvent event) {
            menu.setActiveViewButton(event.getViewName());
        }

    };
}
