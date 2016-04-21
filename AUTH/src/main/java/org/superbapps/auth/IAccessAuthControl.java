package org.superbapps.auth;

import java.io.Serializable;
import java.util.Set;
import org.apache.shiro.session.Session;

/**
 * Simple interface for authentication and authorization checks.
 */
public interface IAccessAuthControl {

    boolean login(String username, String password);

    void logout();

    boolean authenticated();

    boolean hasRole(String role);

    boolean hasRole(Enum role);

    boolean isPermitted(String permission);

    boolean isPermitted(Enum permission);

    String getPrincipal();

    String getInfSysUserSession();

    int getNoOfSessions();

    Set<Serializable> getUsersSessions();

    void removeUserSession();

    Session getSubjectSession();

}
