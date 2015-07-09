/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.dobrivoje.auth.IntermolADAccessControl;
import org.dobrivoje.auth.IAccessAuthControl;
import org.dobrivoje.auth.roles.Roles;

/**
 *
 * @author root
 */
public class testKlasa {

    //<editor-fold defaultstate="collapsed" desc="decorated">
    @RequiresAuthentication()
    private static void testAutentifikacije(Subject subject) {
        System.err.println("testAutentifikacije, Subjekat " + subject.getPrincipal() + ", autentifikovan !");
    }

    @RequiresPermissions(Roles.ROLE_ROOT_PRIVILEGES)
    private static void testDozovle1(Subject subject) {
        System.err.println("testDozovle1 Subjekat " + subject.getPrincipal() + ", ima dozovlu za pravo : " + Roles.ROLE_ROOT_PRIVILEGES);
    }

    @RequiresRoles(value = Roles.ROLE_ROOT_PRIVILEGES)
    private static void testDozovle2(Subject subject) {
        System.err.println("testDozovle2 Subjekat " + subject.getPrincipal() + ", ima ROLE : " + Roles.ROLE_ROOT_PRIVILEGES);
    }
    //</editor-fold>

    public static void main(String[] args) {
        IAccessAuthControl intermolAD = new IntermolADAccessControl();

        try {
            intermolAD.login("intermol\\dprtenjak", "dedaMocika2000");
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

        System.err.println(" *** " + intermolAD.getPrincipal() + " -> " + intermolAD.hasRole("R_appFSUser"));
    }
}
