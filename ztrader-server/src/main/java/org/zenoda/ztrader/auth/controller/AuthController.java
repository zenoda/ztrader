package org.zenoda.ztrader.auth.controller;

import org.springframework.web.bind.annotation.*;
import org.zenoda.ztrader.auth.domain.AuthStatus;
import org.zenoda.ztrader.auth.domain.Credential;
import org.zenoda.ztrader.auth.domain.SessionInfo;
import org.zenoda.ztrader.auth.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(value = "/login")
    public SessionInfo login(@RequestBody Credential credential) {
        return authService.login(credential);
    }

    @PostMapping(value = "/logout")
    public void logout() {
        authService.logout();
    }

    @GetMapping(value = "/status")
    public AuthStatus getAuthStatus() {
        return authService.getAuthStatus();
    }
}
