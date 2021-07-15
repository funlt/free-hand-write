package com.funlt.freehandwrite.shiro;

import com.funlt.freehandwrite.domain.Member;
import com.funlt.freehandwrite.domain.Permission;
import com.funlt.freehandwrite.domain.Role;
import com.funlt.freehandwrite.service.MemberService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomRealm extends AuthorizingRealm {

    @Autowired
    private MemberService memberService;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取登录用户名
        String email = (String) principalCollection.getPrimaryPrincipal();
        //查询用户名称
        Member member = memberService.getMemberByEmail(email);
        //添加角色和权限
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        for (Role role : member.getRoles()) {
            //添加角色
            simpleAuthorizationInfo.addRole(role.getRoleName());
            //添加权限
            for (Permission permission : role.getPermissions()) {
                simpleAuthorizationInfo.addStringPermission(permission.getPermName());
            }
        }
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //获取用户信息
        String email = authenticationToken.getPrincipal().toString();
        Member member = memberService.getMemberByEmail(email);
        if (member == null) {
            //这里返回后会报出对应异常
            return null;
        } else {
            //这里验证authenticationToken和simpleAuthenticationInfo的信息
            return new SimpleAuthenticationInfo(email, member.getPassword(), getName());
        }
    }
}
