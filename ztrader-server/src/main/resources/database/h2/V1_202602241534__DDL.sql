create table zt_data_category
(
    id                 bigint primary key comment '数据类别ID',
    data_category_code varchar(30) not null comment '数据类别编码',
    data_category_name varchar(50) not null comment '数据类别名称',
    data_rows          bigint      not null comment '数据行数',
    data_updated_at    bigint comment '最后更新时间',
    data_updated_by    bigint comment '最后更新的操作人',
    created_by         bigint comment '创建人',
    created_at         bigint comment '创建时间',
    updated_by         bigint comment '修改人',
    updated_at         bigint comment '修改时间',
    data_version       int comment '数据版本'
);
comment on table zt_data_category is '数据类别';

create table zt_company
(
    id           bigint primary key comment '公司ID',
    code         varchar(20)  not null comment '公司代码',
    name         varchar(50)  not null comment '公司名称',
    area         varchar(20)  not null comment '地域',
    industry     varchar(30)  not null comment '所属行业',
    cn_spell     varchar(100) not null comment '拼音缩写',
    market       varchar(15)  not null comment '市场类型（主板/创业板/科创板/CDR）',
    list_date    varchar(10)  not null comment '上市日期',
    created_by   bigint comment '创建人',
    created_at   bigint comment '创建时间',
    updated_by   bigint comment '修改人',
    updated_at   bigint comment '修改时间',
    data_version int comment '数据版本'
);
comment on table zt_company is '上市公司';
create unique index idx_zt_company_1 on zt_company (code);
create table zt_trade
(
    id           bigint primary key comment '交易ID',
    company_code varchar(20) not null comment '公司代码',
    trade_date   varchar(20) not null comment '交易日期',
    open         bigint comment '开盘价',
    close        bigint comment '收盘价',
    high         bigint comment '最高价',
    low          bigint comment '最低价',
    vol          bigint comment '成交量',
    created_by   bigint comment '创建人',
    created_at   bigint comment '创建时间',
    updated_by   bigint comment '修改人',
    updated_at   bigint comment '修改时间',
    data_version int comment '数据版本'
);
comment on table zt_trade is '交易数据';
create unique index idx_zt_trade_1 on zt_trade (company_code, trade_date);