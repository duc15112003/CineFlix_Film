-- Sử dụng cơ sở dữ liệu mặc định
USE master;

-- Tạo login mới với username và password
CREATE LOGIN duc WITH PASSWORD = '123';

-- Tạo user và gán vào cơ sở dữ liệu
CREATE USER duc FOR LOGIN duc;

-- Gán quyền admin cho user mới
ALTER SERVER ROLE sysadmin ADD MEMBER duc;


CREATE DATABASE CineFlix;
