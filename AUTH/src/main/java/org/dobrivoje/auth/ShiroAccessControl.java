package org.dobrivoje.auth;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.util.Factory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SimpleSession;
import org.apache.shiro.subject.Subject;

public class ShiroAccessControl implements IAccessAuthControl {

    //<editor-fold defaultstate="collapsed" desc="Infrastructure">
    // atribut pod navodnicima je id sesije koja se odnosi na username ulogovanog korisnika
    // private static final String UN_SESSION_KEY = "UR8450-XC88xoiuf-iow889s-HG786hjgghH11H50HH8911-mNNmn558wuuuw768x8c7";
    private String UN_SESSION_KEY;
    private static int loggedInUsers = 0;
    private static final Set<Serializable> usersSessions = new HashSet<>();

    private final Factory<SecurityManager> factory;
    private final SecurityManager securityManager;
    private final Subject subject;

    public ShiroAccessControl(String initFile) {
        factory = new IniSecurityManagerFactory(initFile);
        securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

        // izmena koda ispod, kodom ispod njega :
        // subject = ThreadContext.getSubject();
        subject = SecurityUtils.getSubject();
    }
    //</editor-fold>

    @Override
    public synchronized boolean login(String username, String password) {
        Logger.getLogger("shiro access control").log(Level.INFO, "SHIRO ACCESS CONTROL LOGIN. NO. OF LOGGEDIN USERS : {0}", loggedInUsers);
        Logger.getLogger("shiro access control sessions").log(Level.INFO, "SHIRO ACCESS CONTROL ACTIVE SESSIONS : {0}", usersSessions.toArray());

        // dodata provera pre logovanja
        ensureUserIsLoggedOut();

        UsernamePasswordToken token = new UsernamePasswordToken(username, password);

        // dodat kod prema instrukciji sa : http://stackoverflow.com/questions/14516851/shiro-complaining-there-is-no-session-with-id-xxx-with-defaultsecuritymanager
        token.setRememberMe(true);

        if (subject.getSession().getAttribute(UN_SESSION_KEY) == null) {
            try {
                subject.login(token);

                subject.getSession().setAttribute(UN_SESSION_KEY = (String) getSubjectSessionID(), getPrincipal());
                incLoggedUsers();

                return true;

            } catch (AuthenticationException ae) {
                return false;
            }

        } else {
            return true;
        }
    }

    @Override
    public synchronized void logout() {
        decLoggedUsers();

        try {
            // subject.logout();
            ensureUserIsLoggedOut();
        } catch (Exception e) {
        }

        Logger.getLogger("shiro access control").log(Level.INFO, "SHIRO ACCESS CONTROL LOGOUT. NO. OF LOGGEDIN USERS : {0}", getLoggedUsers());
        Logger.getLogger("shiro access control sessions").log(Level.INFO, "SHIRO ACCESS CONTROL ACTIVE SESSIONS : {0}", usersSessions.toString());
    }

    //<editor-fold defaultstate="collapsed" desc="Permissions/Auths...">
    @Override
    public boolean authenticated() {
        try {
            return subject.isAuthenticated();
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean hasRole(String role) {
        boolean o = false;
        try {
            o = subject.hasRole(role);
        } catch (Exception e) {
        }
        return o;
    }

    @Override
    public boolean isPermitted(String permission) {
        boolean o = false;
        try {
            o = subject.isPermitted(permission);
        } catch (Exception e) {
        }
        return o;
    }

    @Override
    public String getPrincipal() {
        try {
            return (String) subject.getPrincipal();
        } catch (Exception e) {
            return "";
        }
    }

    @Override
    public Session getSubjectSession() {
        try {
            return subject.getSession();
        } catch (Exception e) {
            return new SimpleSession("");
        }
    }

    @Override
    public Serializable getSubjectSessionID() {
        try {
            return subject.getSession().getId();
        } catch (Exception e) {
            return "";
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Inf Sys users,...">
    @Override
    public String getInfSysUserSession() {
        // atribut pod navodnicima je id sesije koja se odnosi na username ulogovanog korisnika
        try {
            return (String) subject.getSession().getAttribute(UN_SESSION_KEY);
        } catch (Exception e) {
            return "";
        }
    }

    @Override
    public synchronized int getLoggedUsers() {
        return loggedInUsers;
    }

    @Override
    public synchronized void incLoggedUsers() {
        if (!usersSessions.contains(getSubjectSessionID())) {
            usersSessions.add(getSubjectSessionID());
            loggedInUsers++;
        }
    }

    @Override
    public synchronized void decLoggedUsers() {
        if (usersSessions.contains(getSubjectSessionID())) {
            usersSessions.remove(getSubjectSessionID());
        }

        if (--loggedInUsers < 0) {
            loggedInUsers = 0;
        }

    }
    //</editor-fold>

    public static Set<Serializable> getUsersSessions() {
        return usersSessions;
    }

    private void ensureUserIsLoggedOut() {
        try {
            Subject currentUser = SecurityUtils.getSubject();

            if (currentUser != null) {
                // Log the user out and kill their session if possible.
                currentUser.logout();
                decLoggedUsers();

                Session session = currentUser.getSession(false);
                if (session != null) {
                    session.stop();
                }
            }
        } catch (Exception e) {
        }
    }
}
