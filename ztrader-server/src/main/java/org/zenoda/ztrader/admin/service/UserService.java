package org.zenoda.ztrader.admin.service;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.lang.generator.SnowflakeGenerator;
import cn.hutool.core.util.IdUtil;
import org.springframework.stereotype.Service;
import org.zenoda.ztrader.admin.domain.PasswordChangeRequest;
import org.zenoda.ztrader.admin.domain.User;
import org.zenoda.ztrader.admin.exception.BusinessException;
import org.zenoda.ztrader.admin.mapper.UserMapper;
import org.zenoda.ztrader.auth.web.SessionContext;
import org.zenoda.ztrader.common.DefaultService;

@Service
public class UserService extends DefaultService<User, UserMapper> {

    public UserService(SessionContext sessionContext, UserMapper repository) {
        super(sessionContext, repository);
    }

    public User getUserByUsername(String username) {
        return getDefaultMapper().selectByUsername(username);
    }

    public User getUserProfile() {
        Long userId = getSessionContext().getUserId();
        User user = getById(userId);
        user.setPassword(null);
        return user;
    }

    public User saveUserProfile(User user) {
        user.setPassword(null);
        return save(user);
    }

    public User changePassword(PasswordChangeRequest passwordChangeRequest) {
        Long userId = getSessionContext().getUserId();
        User user = getById(userId);
        if (user.getPassword().equals(passwordChangeRequest.getOldPassword())) {
            user.setPassword(passwordChangeRequest.getNewPassword());
            user = save(user);
            user.setPassword(null);
            return user;
        } else {
            throw new BusinessException("旧密码错误");
        }
    }
}
