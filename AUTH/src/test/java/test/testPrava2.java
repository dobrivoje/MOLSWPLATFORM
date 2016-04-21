/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.util.Arrays;
import org.superbapps.auth.IAccessAuthControl;
import org.superbapps.auth.ShiroAccessControl;
import org.superbapps.auth.roles.Roles1;

/**
 *
 * @author Dobri
 */
public class testPrava2 {

    public static void main(String[] args) {
        IAccessAuthControl IAC = new ShiroAccessControl("classpath:IntermolAD1.ini");

        try {
            IAC.login("ws", "");
            System.err.println(IAC.getPrincipal() + " isAuthenticated ? " + IAC.authenticated());

            System.err.println("Test 1: ROLES MF :");
            System.err.println(Roles1.getApp_MF_Roles());
            System.err.println(Roles1.getApp_MF_Permissions());

            System.err.println("Test 2: ROLES WS :");
            System.err.println(Roles1.getApp_WS_Roles());
            System.err.println(Roles1.getApp_WS_Permissions());

            System.err.println("Test 3: Roles1.values() :");
            System.err.println(Arrays.toString(Roles1.values()));

            System.err.println("-----------------Roles--------------------------");

            for (Roles1 r : Roles1.values()) {
                if (IAC.hasRole(r.toString())) {
                    System.err.println(IAC.getPrincipal() + " HAS ROLE : " + r);
                }
            }

            System.err.println("--------------Permissions------------------------");

            System.err.println("P: " + Roles1.getApp_WS_Permissions());

            if (IAC.isPermitted(Roles1.P_WS_CARDS_READ.toString())) {
                System.err.println("HAS Role : " + Roles1.P_WS_CARDS_READ);
            } else {
                System.err.println("Has NOT Role : " + Roles1.P_WS_CARDS_READ.name());
                System.err.println("Has NOT Role : " + Roles1.P_WS_CARDS_READ.toString());
            }

        } catch (Exception e) {
        }
    }

}
