package org.zenoda.ztrader.data.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.zenoda.ztrader.common.DefaultMapper;
import org.zenoda.ztrader.data.domain.Company;

@Mapper
public interface CompanyMapper extends DefaultMapper<Company> {
}