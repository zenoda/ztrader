insert into zt_data_category(id, data_category_code, data_category_name, data_rows, data_updated_at, data_updated_by,
                             created_by, created_at, updated_by, updated_at, data_version)
values (0, 'company', '上市公司', 0, null, null, 0, extract(epoch from current_timestamp()) * 1000, 0,
        extract(epoch from current_timestamp()) * 1000, 0);
insert into zt_data_category(id, data_category_code, data_category_name, data_rows, data_updated_at, data_updated_by,
                             created_by, created_at, updated_by, updated_at, data_version)
values (1, 'trade', '交易数据', 0, null, null, 0, extract(epoch from current_timestamp()) * 1000, 0,
        extract(epoch from current_timestamp()) * 1000, 0);