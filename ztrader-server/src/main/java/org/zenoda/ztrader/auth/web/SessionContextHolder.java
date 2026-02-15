package org.zenoda.ztrader.auth.web;

import org.springframework.stereotype.Component;

@Component
public class SessionContextHolder {
    private static SessionContext sessionContext;

    public static SessionContext getSessionContext() {
        return sessionContext;
    }

    public SessionContextHolder(SessionContext sessionContext) {
        SessionContextHolder.sessionContext = sessionContext;
    }
}
