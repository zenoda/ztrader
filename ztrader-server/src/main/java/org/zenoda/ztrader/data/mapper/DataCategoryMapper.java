package org.zenoda.ztrader.data.mapper;

import java.util.List;
import org.zenoda.ztrader.data.domain.DataCategory;

public interface DataCategoryMapper {
    int deleteByPrimaryKey(String dataCategoryCode);

    int insert(DataCategory record);

    DataCategory selectByPrimaryKey(String dataCategoryCode);

    List<DataCategory> selectAll();

    int updateByPrimaryKey(DataCategory record);
}