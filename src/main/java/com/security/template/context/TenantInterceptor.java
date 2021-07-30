package com.security.template.context;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TenantInterceptor extends HandlerInterceptorAdapter {

    private final AuthenticationHolder holder;

    public TenantInterceptor(AuthenticationHolder holder) {
        this.holder = holder;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        holder.setTenantId(getTenant(request));
        holder.setUserAcess(getUserAccess(request));

        return true;
    }

    private Integer getUserAccess(HttpServletRequest request) {
        return (Integer) request.getAttribute("user_access");
    }

    private Integer getTenant(HttpServletRequest request) {
        return (Integer) request.getAttribute("tenant");
    }
}
