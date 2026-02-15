package org.zenoda.ztrader.auth.web;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;

@Component
public class SessionContext {
    private static final String USER_ID = "userId";
    private static final String USERNAME = "username";
    private final ThreadLocal<HttpSession> sessionThreadLocal = new ThreadLocal<>();

    public void setSession(HttpSession session) {
        sessionThreadLocal.set(session);
    }

    public String getSessionId() {
        return sessionThreadLocal.get().getId();
    }

    public void setUserId(Long userId) {
        sessionThreadLocal.get().setAttribute(USER_ID, userId);
    }

    public Long getUserId() {
        return (Long) sessionThreadLocal.get().getAttribute(USER_ID);
    }

    public void setUsername(String username) {
        sessionThreadLocal.get().setAttribute(USERNAME, username);
    }

    public String getUsername() {
        return (String) sessionThreadLocal.get().getAttribute(USERNAME);
    }

}
