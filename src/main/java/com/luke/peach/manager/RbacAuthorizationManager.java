package com.luke.peach.manager;

import com.luke.peach.config.RbacAuthorityService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;

/**
 * @author ：luke
 * @date ：Created in 2024/5/24 10:24 AM
 * @description：
 * @modified By：
 */


@Component
public class RbacAuthorizationManager implements AuthorizationManager<RequestAuthorizationContext> {

    @Autowired
    private RbacAuthorityService rbacAuthorityService;



    @Override
    public AuthorizationDecision check(Supplier<Authentication> authentication, RequestAuthorizationContext context) {
        HttpServletRequest request = context.getRequest();
        boolean hasPermission = rbacAuthorityService.hasPermission(request, authentication.get());

        if (hasPermission) {
            return new AuthorizationDecision(true);
        } else {
            throw new AccessDeniedException("Access is denied");
        }
    }
}
