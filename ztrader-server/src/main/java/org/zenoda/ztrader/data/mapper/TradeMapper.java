package org.zenoda.ztrader.data.mapper;

import java.util.List;
import org.zenoda.ztrader.data.domain.Trade;

public interface TradeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Trade record);

    Trade selectByPrimaryKey(Long id);

    List<Trade> selectAll();

    int updateByPrimaryKey(Trade record);
}