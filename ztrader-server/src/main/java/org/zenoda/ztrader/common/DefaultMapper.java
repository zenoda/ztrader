package org.zenoda.ztrader.common;

public interface DefaultMapper<T extends DefaultEntity> {
    int insert(T t);

    T updateById(T t);

    int deleteById(Long id, Integer version);

    T selectById(Long id);
}
