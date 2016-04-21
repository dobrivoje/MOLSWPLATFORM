package org.superbapps.auth;

public class IntermolADAccessControl extends ShiroAccessControl {

    public IntermolADAccessControl() {
        super("classpath:IntermolAD.ini");
    }
}
