package org.zenoda.ztrader.admin.controller;

import org.springframework.web.bind.annotation.*;
import org.zenoda.ztrader.admin.domain.PasswordChangeRequest;
import org.zenoda.ztrader.admin.domain.User;
import org.zenoda.ztrader.admin.mapper.UserMapper;
import org.zenoda.ztrader.admin.service.UserService;
import org.zenoda.ztrader.common.DefaultController;

@RestController
@RequestMapping("/admin/user")
public class UserController extends DefaultController<UserService, User, UserMapper> {

    public UserController(UserService service) {
        super(service);
    }

    @GetMapping(value = "/profile")
    public User getUserProfile() {
        return getDefaultService().getUserProfile();
    }

    @PostMapping(value = "/profile")
    public User saveUserProfile(@RequestBody User user) {
        return getDefaultService().saveUserProfile(user);
    }

    @PostMapping(value = "/password/change")
    public User changePassword(@RequestBody PasswordChangeRequest passwordChangeRequest) {
        return getDefaultService().changePassword(passwordChangeRequest);
    }
}
