package org.zenoda.ztrader.common;

import cn.hutool.core.util.IdUtil;
import org.springframework.transaction.annotation.Transactional;
import org.zenoda.ztrader.auth.web.SessionContext;

public class DefaultService<T extends DefaultEntity, M extends DefaultMapper<T>> {
    private SessionContext sessionContext;
    private M repository;

    public DefaultService(SessionContext sessionContext, M repository) {
        this.sessionContext = sessionContext;
        this.repository = repository;
    }

    public SessionContext getSessionContext() {
        return sessionContext;
    }

    public M getDefaultMapper() {
        return repository;
    }

    @Transactional
    public T save(T entity) {
        if (entity.getId() == null) {
            entity.setId(IdUtil.getSnowflakeNextId());
            entity.setDataVersion(0);
            entity.setCreatedAt(System.currentTimeMillis());
            entity.setCreatedBy(sessionContext.getUserId());
            if (entity instanceof DefaultMutableEntity) {
                DefaultMutableEntity mutableEntity = ((DefaultMutableEntity) entity);
                mutableEntity.setUpdatedAt(System.currentTimeMillis());
                mutableEntity.setUpdatedBy(sessionContext.getUserId());
            }
            repository.insert(entity);
        } else if (entity instanceof DefaultMutableEntity) {
            DefaultMutableEntity mutableEntity = ((DefaultMutableEntity) entity);
            mutableEntity.setUpdatedAt(System.currentTimeMillis());
            mutableEntity.setUpdatedBy(sessionContext.getUserId());
            mutableEntity.setDataVersion(mutableEntity.getDataVersion() + 1);
            repository.updateById(entity);
        } else {
            throw new RuntimeException("非可变实体不允许执行更新操作");
        }
        return entity;
    }

    @Transactional
    public Integer removeById(Long id, Integer dataVersion) {
        return repository.deleteById(id, dataVersion);
    }

    public T getById(Long id) {
        return repository.selectById(id);
    }
}
