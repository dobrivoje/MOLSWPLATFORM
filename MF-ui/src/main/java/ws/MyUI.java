package ws;

import Views.General.LoginScreen;
import Views.General.LoginScreen.LoginListener;
import Views.General.MainScreen;
import Views.MainMenu.HSE.HSE_WorkPlanView;
import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.Responsive;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;
import dataservice.DataService;
import org.dobrivoje.auth.IAccessAuthControl;
import org.dobrivoje.auth.IntermolADAccessControl;
import org.dobrivoje.utils.date.formats.DateFormat;

/**
 *
 */
@Theme("mytheme")
@Widgetset("ws.MyAppWidgetset")
public class MyUI extends UI {

    private final IAccessAuthControl accessControl = new IntermolADAccessControl();
    public static final DataService DS = DataService.getDefault();
    public static final String SYSTEM_DATE_FORMAT = DateFormat.DATETIME_FORMAT_SRB.toString();

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        Responsive.makeResponsive(this);
        setLocale(vaadinRequest.getLocale());
        getPage().setTitle("MOL Serbia SW Platform");

        if (!accessControl.authenticated()) {
            setContent(new LoginScreen(accessControl, new LoginListener() {
                @Override
                public void doAfterLogin() {
                    showMainView();
                }
            }));
        }
    }

    protected void showMainView() {
        setContent(new MainScreen(MyUI.this));
        getNavigator().navigateTo(HSE_WorkPlanView.class.getSimpleName());
    }

    public static MyUI get() {
        return (MyUI) UI.getCurrent();
    }

    public IAccessAuthControl getAccessControl() {
        return accessControl;
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
