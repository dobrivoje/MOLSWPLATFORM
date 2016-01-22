package Main;

import RETAIL.Views.View_R;
import Uni.Views.LoginScreen;
import Uni.Views.MainScreen;
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
import db.retail.dataservice.DataService_RETAIL;
import org.dobrivoje.auth.IAccessAuthControl;
import org.dobrivoje.utils.date.formats.DateFormat;
import org.superb.apps.utilities.Enums.ServletOperations;

@Theme("mytheme")
@Widgetset("mf.MyAppWidgetset")
@PreserveOnRefresh
public class MyUI extends UI {

    // private final IAccessAuthControl accessControl = new IntermolADAccessControl();
    private IAccessAuthControl accessControl;

    public static final DataService_HSE DS_HSE = DataService.getDS_HSE();
    public static final DataService_RETAIL DS_RETAIL = DataService.getDS_RETAIL();

    public static final String SYSTEM_DATE_FORMAT = DateFormat.DATE_FORMAT_SRB.toString();

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        accessControl = (IAccessAuthControl) vaadinRequest.getWrappedSession().getAttribute(ServletOperations.SERVLET_CREATION.toString());

        Responsive.makeResponsive(this);
        setLocale(vaadinRequest.getLocale());
        getPage().setTitle("MOL Serbia SW Platform");

        if (!accessControl.authenticated()) {
            setContent(new LoginScreen(accessControl, this::showMainView));
        }
    }

    protected void showMainView() {
        setContent(new MainScreen(MyUI.this));
        getNavigator().navigateTo(View_R.class.getSimpleName());
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
