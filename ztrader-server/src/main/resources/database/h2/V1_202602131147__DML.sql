-- 预置root用户
insert into adm_user(id, username, password, nickname, avatar_url, email, phone, email_verified, phone_verified,
                     is_root, status, last_login_at, created_at, created_by, updated_at, updated_by, data_version)
VALUES (0, 'root', '', 'root', '', 'root@sample.com', '10000000000', false, false, true, 0, null,
        extract(epoch from current_timestamp()) * 1000, 0, extract(epoch from current_timestamp()) * 1000, 0,
        0);