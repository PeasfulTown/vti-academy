CREATE DATABASE IF NOT EXISTS db_extra_assignments;
USE db_extra_assignments;

CREATE TABLE tbl (
	id 				SMALLINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    name			VARCHAR(40) NOT NULL, 
    code			CHAR(5) NOT NULL,
    modified_date	DATETIME DEFAULT NOW() NOT NULL
);

SELECT * FROM tbl;