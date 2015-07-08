/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dobrivoje.auth.primer;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 *
 * @author root
 */
public class MyRealm extends AuthorizingRealm {

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        /* Set<String> roles = new HashSet<>(Arrays.asList("Manager", "User"));
         Set<String> permissions = new HashSet<>(
         Arrays.asList("p1", "p2", "p2:read", "p2:write"));
         Collection<String> principalsList = principals.byType(String.class);
        
         if (principalsList.isEmpty()) {
         throw new AuthorizationException("Empty principal list !");
         }
        
         for (String pl : principalsList) {
         if (pl.contains("ws")) {
         HashSet<String> userRoles = new HashSet<>(Arrays.asList("User"));
         userRoles.add(pl);
         }
         if (pl.contains("root")) {
         HashSet<String> userRoles = new HashSet<>(Arrays.asList("User"));
         userRoles.add(pl);
         }
         }
        
         SimpleAuthenticationInfo sai = new SimpleAuthenticationInfo(principals, roles);*/
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken at) throws AuthenticationException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
