/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.apache.shiro.mgt.SecurityManager;

/**
 *
 * @author root
 */
public class testKlasa {

    public static void main(String[] args) {
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiroAD.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

        try {
            SecurityUtils.getSubject().login(new UsernamePasswordToken("intermol\\dprtenjak", ""));

            Subject subject = SecurityUtils.getSubject();

            System.err.println(subject.getPrincipal());
            System.err.println("isAuthenticated :" + subject.isAuthenticated());
            System.err.println("isPermitted IS :" + subject.isPermitted("IS"));
            System.err.println("isPermitted Localadmin :" + subject.isPermitted("Localadmin"));
        } catch (AuthenticationException ae) {
            System.err.println(ae.getMessage());
        } catch(NullPointerException npe) {
            System.err.println("nullllsssss");
        }
    }
}
