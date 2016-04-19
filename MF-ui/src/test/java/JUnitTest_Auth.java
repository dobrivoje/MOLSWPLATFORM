/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.dobrivoje.auth.IAccessAuthControl;
import org.dobrivoje.auth.IntermolADAccessControl;
import org.dobrivoje.auth.roles.Roles;
import org.junit.Test;

/**
 *
 * @author root
 */
public class JUnitTest_Auth {

    @Test
    public void test_autentifikacija() {
        IAccessAuthControl intermolAD = new IntermolADAccessControl();

        try {
            intermolAD.login("intermol\\dprtenjak", "dedaMoca4");
            //intermolAD.login("ws", "");

            System.err.println(intermolAD.getPrincipal() + " isAuthenticated ? " + intermolAD.authenticated());

            System.err.println("-----------------Roles--------------------------");

            for (String r : Roles.getAllRoles()) {
                if (intermolAD.hasRole(r)) {
                    System.err.println(intermolAD.getPrincipal() + " HAS ROLE : " + r);
                } else {
                    System.err.println(intermolAD.getPrincipal() + " HAS NOT ROLE : " + r);
                }
            }

            System.err.println("--------------Permissions------------------------");

            for (String p : Roles.getAllPermissions()) {
                if (intermolAD.isPermitted(p)) {
                    System.err.println(intermolAD.getPrincipal() + " IS permitted : " + p);
                } else {
                    System.err.println(intermolAD.getPrincipal() + " is NOT permitted : " + p);
                }
            }

        } catch (UnknownAccountException e) {
            System.err.println("Nepoznati nalog !");
        } catch (IncorrectCredentialsException e) {
            System.err.println("IncorrectCredentials !");
        } catch (ExcessiveAttemptsException e) {
            System.err.println("ExcessiveAttempts !");
        }

        System.err.println("--------------Permissions------------------------");

        for (String s : Roles.getAllPermissions()) {
            System.err.println(intermolAD.getPrincipal() + ", " + s + " -> " + intermolAD.isPermitted(s));
        }

        System.err.println(" *** " + intermolAD.getPrincipal() + " -> " + intermolAD.hasRole(Roles.R_MF_FS_USER));

        System.err.println("--------------SESSIONS------------------------");
        intermolAD.login("ws", "");
        System.err.println("User sessions : " + intermolAD.getUsersSessions().toString());

        System.err.println(" Logout... ");
        intermolAD.logout();
        System.err.println(" Sessions : " + intermolAD.getUsersSessions().toString());

    }
}
