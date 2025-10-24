CREATE DATABASE IF NOT EXISTS db_extra_assignments;
USE db_extra_assignments;

CREATE TABLE tbl2 (
	id				SMALLINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    name			VARCHAR(40) NOT NULL,
    birth_date		DATE DEFAULT (CURRENT_DATE()) NOT NULL,
    gender			ENUM('0', '1') DEFAULT NULL,
    is_deleted_flag ENUM('0', '1') NOT NULL
);

SELECT * FROM tbl2;