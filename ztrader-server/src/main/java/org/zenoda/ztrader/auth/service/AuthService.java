package org.zenoda.ztrader.auth.service;

import org.springframework.stereotype.Service;
import org.zenoda.ztrader.admin.domain.User;
import org.zenoda.ztrader.admin.service.UserService;
import org.zenoda.ztrader.auth.domain.Credential;
import org.zenoda.ztrader.auth.domain.SessionInfo;
import org.zenoda.ztrader.auth.web.SessionContext;

@Service
public class AuthService {
    private SessionContext sessionContext;
    private UserService userService;

    public AuthService(SessionContext sessionContext, UserService userService) {
        this.sessionContext = sessionContext;
        this.userService = userService;
    }

    public SessionInfo login(Credential credential) {
        SessionInfo sessionInfo = null;
        if (credential instanceof Credential.PasswordCredential) {
            sessionInfo = loginWithPassword((Credential.PasswordCredential) credential);
        }
        return sessionInfo;
    }

    private SessionInfo loginWithPassword(Credential.PasswordCredential credential) {
        User user = userService.getUserByUsername(credential.getUsername());
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        if (!user.getPassword().equals(credential.getPassword())) {
            throw new RuntimeException("Invalid password");
        }
        sessionContext.setUserId(user.getId());
        sessionContext.setUsername(user.getUsername());
        return getSessionInfo();
    }

    public SessionInfo getSessionInfo() {
        SessionInfo sessionInfo = new SessionInfo();
        sessionInfo.setSessionId(sessionContext.getSessionId());
        sessionInfo.setUsername(sessionContext.getUsername());
        sessionInfo.setUserId(sessionContext.getUserId());
        return sessionInfo;
    }
}
