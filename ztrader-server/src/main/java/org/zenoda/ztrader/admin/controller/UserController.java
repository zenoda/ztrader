package org.zenoda.ztrader.admin.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
}
