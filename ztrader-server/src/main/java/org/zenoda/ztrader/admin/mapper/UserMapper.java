package org.zenoda.ztrader.admin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.zenoda.ztrader.admin.domain.User;
import org.zenoda.ztrader.common.DefaultMapper;

@Mapper
public interface UserMapper extends DefaultMapper<User> {
    User selectByUsername(String username);
}