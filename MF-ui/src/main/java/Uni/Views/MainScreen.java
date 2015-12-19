package Uni.Views;

import HSE.Views.View_HSE_SysNotifBoard;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import HSE.Views.View_HSE_WorkPlan;
import MainMenu.MainMenu;
import RETAIL.Views.View_RETAIL;
import RETAIL.Views.View_RETAIL_CocaCalc;
import RETAIL.Views.View_RETAIL_CocaCalc_DM_CSR;
import RETAIL.Views.View_RETAIL_CocaCalc_DM_MAPPING;
import RETAIL.Views.View_RETAIL_CocaCalc_PARTNERS;
import RETAIL.Views.View_RETAIL_CocaCalc_PA_Contracts;
import RETAIL.Views.View_RETAIL_SysNotifBoard;
import mf.MyUI;

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
        menu.addViewTree(new View_HSE_WorkPlan(), View_HSE_WorkPlan.class.getSimpleName(), View_HSE_WorkPlan.class.getSimpleName());
        menu.addViewButton(new AboutView(), AboutView.class.getSimpleName(), AboutView.class.getSimpleName(), FontAwesome.INFO_CIRCLE);

        // DODAVANJE VIEW-ova NOVIH AKCIJA GL. MENIJA :
        navigator.addView(EmptyView.class.getSimpleName(), EmptyView.class);
        navigator.addView(View_HSE_WorkPlan.class.getSimpleName(), View_HSE_WorkPlan.class);
        navigator.addView(View_HSE_SysNotifBoard.class.getSimpleName(), View_HSE_SysNotifBoard.class);
        navigator.addView(View_RETAIL_SysNotifBoard.class.getSimpleName(), View_RETAIL_SysNotifBoard.class);
        navigator.addView(View_RETAIL.class.getSimpleName(), View_RETAIL.class);
        navigator.addView(View_RETAIL_CocaCalc.class.getSimpleName(), View_RETAIL_CocaCalc.class);
        navigator.addView(View_RETAIL_CocaCalc_DM_CSR.class.getSimpleName(), View_RETAIL_CocaCalc_DM_CSR.class);
        navigator.addView(View_RETAIL_CocaCalc_DM_MAPPING.class.getSimpleName(), View_RETAIL_CocaCalc_DM_MAPPING.class);
        navigator.addView(View_RETAIL_CocaCalc_PA_Contracts.class.getSimpleName(), View_RETAIL_CocaCalc_PA_Contracts.class);
        navigator.addView(View_RETAIL_CocaCalc_PARTNERS.class.getSimpleName(), View_RETAIL_CocaCalc_PARTNERS.class);

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
