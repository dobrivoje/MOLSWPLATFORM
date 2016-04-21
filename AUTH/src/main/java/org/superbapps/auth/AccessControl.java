package org.superbapps.auth;

public class AccessControl extends ShiroAccessControl {
    
    public AccessControl() {
        super("classpath:shiro.ini");
    }

}
