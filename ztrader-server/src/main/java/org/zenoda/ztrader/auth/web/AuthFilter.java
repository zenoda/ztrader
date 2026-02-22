package org.zenoda.ztrader.auth.web;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@Order(1)
public class AuthFilter extends OncePerRequestFilter {
    private SessionContext sessionContext;

    public AuthFilter(SessionContext sessionContext) {
        this.sessionContext = sessionContext;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        sessionContext.setSession(request.getSession(true));
        if (!request.getRequestURI().matches("/api/auth/login|/api/auth/logout|/api/auth/status|/api/swagger-ui.*|/api/v3/api-docs.*") && sessionContext.getUserId() == null) {
            //未登录
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        }
        filterChain.doFilter(request, response);
    }
}
