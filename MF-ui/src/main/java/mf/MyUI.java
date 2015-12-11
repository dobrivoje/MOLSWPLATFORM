package mf;

import Views.General.LoginScreen;
import Views.General.MainScreen;
import Views.MainMenu.HSE.View_HSE_WorkPlan;
import com.vaadin.annotations.PreserveOnRefresh;
import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.Responsive;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;
import dataservice.DataService;
import db.HSE.dataservice.DataService_HSE;
import org.dobrivoje.auth.IAccessAuthControl;
import org.dobrivoje.auth.IntermolADAccessControl;
import org.dobrivoje.utils.date.formats.DateFormat;

@Theme("mytheme")
@Widgetset("mf.MyAppWidgetset")
@PreserveOnRefresh
public class MyUI extends UI {

    private final IAccessAuthControl accessControl = new IntermolADAccessControl();
    public static final DataService_HSE DS = DataService.getDataService_HSE();
    public static final String SYSTEM_DATE_FORMAT = DateFormat.DATE_FORMAT_SRB.toString();

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        Responsive.makeResponsive(this);
        setLocale(vaadinRequest.getLocale());
        getPage().setTitle("MOL Serbia SW Platform");

        if (!accessControl.authenticated()) {
            setContent(new LoginScreen(accessControl, this::showMainView));
        }
    }

    protected void showMainView() {
        setContent(new MainScreen(MyUI.this));
        getNavigator().navigateTo(View_HSE_WorkPlan.class.getSimpleName());
    }

    public static MyUI get() {
        return (MyUI) UI.getCurrent();
    }

    //<editor-fold defaultstate="collapsed" desc="Interfaces">
    public IAccessAuthControl getAccessControl() {
        return accessControl;
    }

    public boolean isPermitted(String permission) {
        return accessControl.isPermitted(permission);
    }
    //</editor-fold>

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = true)
    public static class MyUIServlet extends VaadinServlet {
    }
}
