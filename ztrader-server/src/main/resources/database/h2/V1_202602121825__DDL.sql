create table adm_user
(
    id             bigint primary key comment '用户ID',
    username       varchar(64)  not null comment '用户名',
    password       varchar(128) not null comment '密码',
    nickname       varchar(64)  not null comment '昵称',
    avatar_url     varchar(128) not null comment '头像URL',
    email          varchar(128) not null comment '电子邮件',
    phone          varchar(32)  null comment '手机号',
    email_verified boolean      not null comment '邮箱验证状态',
    phone_verified boolean      not null comment '手机验证状态',
    is_root        boolean      not null comment '是否根用户',
    status         varchar(10)  not null comment '用户状态',
    last_login_at  bigint       null comment '最后一次登录时间',
    created_at     bigint       not null comment '创建时间',
    created_by     bigint       not null comment '创建人ID',
    updated_at     bigint       not null comment '修改时间',
    updated_by     bigint       not null comment '修改人ID',
    data_version   int          not null comment '数据版本'
);
comment on table adm_user is '用户';