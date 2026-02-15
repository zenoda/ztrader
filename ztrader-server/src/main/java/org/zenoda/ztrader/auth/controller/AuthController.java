package org.zenoda.ztrader.auth.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
}
