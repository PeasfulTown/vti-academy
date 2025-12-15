CREATE DATABASE IF NOT EXISTS db_javacore_exam;
USE db_javacore_exam;

DROP TABLE IF EXISTS `account`;
DROP TABLE IF EXISTS major;

CREATE TABLE major (
	major_id		SMALLINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    `name`			VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE `account` (
	account_id		SMALLINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    fullname		VARCHAR(50) NOT NULL,
    email			VARCHAR(50) UNIQUE NOT NULL,
    `password`		CHAR(50) NOT NULL,
    dob				DATE DEFAULT (CURRENT_DATE()) NOT NULL,
    major_id		SMALLINT UNSIGNED NOT NULL,
    `type`			ENUM("LECTURER", "STUDENT") DEFAULT ("STUDENT"),
    
    FOREIGN KEY (major_id) 
		REFERENCES major (major_id)
);

-- password are all "password"
INSERT INTO `account` (fullname, email, `password`, dob, major_id, `type`)
VALUES 	("Lecturer 1", "lecturer1@gmail.com", "MxBpm698B6o5oglYHyTcrfS0AldYlGPRoa8C0XCs-0c=", CURRENT_DATE(), 1, "LECTURER"),
		("Lecturer 2", "lecturer2@gmail.com", "PA4idn0ndR8Z22FQytZNYKyht5qULbeSjXDrENnwRUA=", CURRENT_DATE(), 2, "LECTURER");
