CREATE DATABASE IF NOT EXISTS db_practice_exam_3;
USE db_practice_exam_3;

DROP PROCEDURE IF EXISTS sp_get_email_pass;
DROP TABLE IF EXISTS `admin`;
DROP TABLE IF EXISTS employee;
DROP TABLE IF EXISTS skill;
DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
	id 			SMALLINT UNSIGNED AUTO_INCREMENT,
    fullname 	VARCHAR(45) NOT NULL,
    email		VARCHAR(50) NOT NULL,
    pass		CHAR(50) NOT NULL,
    `type`		ENUM('admin', 'employee') NOT NULL, 
    
    CONSTRAINT pk_user_id PRIMARY KEY (id),
    CONSTRAINT uq_email UNIQUE KEY (email)
);

CREATE TABLE `admin` (
	admin_id				SMALLINT UNSIGNED NOT NULL,
    years_of_experience		TINYINT UNSIGNED NOT NULL DEFAULT(0),
    
    CONSTRAINT pk_admin_id PRIMARY KEY (admin_id),
    CONSTRAINT fk_admin_id FOREIGN KEY (admin_id)
		REFERENCES `user` (id) ON DELETE CASCADE
);

CREATE TABLE skill (
	id		TINYINT UNSIGNED AUTO_INCREMENT,
    `name`	VARCHAR(20) NOT NULL,
    
    CONSTRAINT pk_skill_id PRIMARY KEY (id),
    CONSTRAINT uq_name UNIQUE KEY (`name`)
);

CREATE TABLE employee (
	employee_id SMALLINT UNSIGNED NOT NULL,
    skill_id	TINYINT UNSIGNED NOT NULL,
    
    CONSTRAINT pk_employee_id PRIMARY KEY (employee_id),
    CONSTRAINT fk_employee_id FOREIGN KEY (employee_id)
		REFERENCES `user` (id) ON DELETE CASCADE,
	CONSTRAINT fk_skill_id FOREIGN KEY (skill_id)
		REFERENCES skill (id) 
);

DELIMITER $$
CREATE PROCEDURE sp_get_email_pass(IN in_email VARCHAR(50), OUT out_pass CHAR(50))
	BEGIN
		SELECT pass INTO out_pass
        FROM `user` WHERE email = in_email; 
    END; $$
DELIMITER ;

DELIMITER $$
CREATE TRIGGER trg_insert_user_admin BEFORE INSERT ON `user` 
FOR EACH ROW
	BEGIN
		
    END;$$
DELIMITER ;

INSERT INTO `user` (fullname, email, pass, `type`) 
VALUES 	("admin1"		, "admin1@example.com"		, 'NdlqTSCEvBLlKe-aQKhO-1fSzRaJZND0gfOzBoF0LnY='	, "admin"),
		("empl1"		, "empl1@example.com.vn"	, 'M26MqDTUj_WHbv583ZsZe6DXA8lnvi12UEKOYwn15mA='		, "employee"),
        ("empl2"		, "empl2@example.com.vn"	, 'M26MqDTUj_WHbv583ZsZe6DXA8lnvi12UEKOYwn15mA='		, "employee"),
        ("admin2"		, "admin2@example.com"		, 'NdlqTSCEvBLlKe-aQKhO-1fSzRaJZND0gfOzBoF0LnY='			, "admin");

INSERT INTO `admin` (admin_id, years_of_experience)
VALUES 	(1 , 3), (4 , 2);

INSERT INTO skill (`name`)
VALUES 	("sql"), ("java");

INSERT INTO employee (employee_id, skill_id)
VALUES	(2, 1), (3, 2);

SET @v_out_pass = '';
CALL sp_get_email_pass("empl2@example.com.vn", @v_out_pass);
SELECT @v_out_pass; 

SELECT u.id, u.fullname, u.email, u.pass, u.`type`, a.years_of_experience 
FROM `user` u
JOIN `admin` a
ON u.id = a.admin_id;

SELECT u.id, u.fullname, u.email, u.pass, u.`type`, s.`name`
FROM `user` u
JOIN employee e
ON u.id = e.employee_id
JOIN skill s
ON e.skill_id = s.id;

SELECT u.id, u.fullname, u.email, u.pass, u.`type`, 
IF(u.`type` = "admin", a.years_of_experience, null) AS years_of_experience, 
IF (u.`type` = "employee", s.id, null) AS skill_id, 
IF (u.`type` = "employee", s.`name`, null) AS skill_name
FROM `user` u
LEFT JOIN `admin` a ON u.id = a.admin_id AND u.`type` = "admin"
LEFT JOIN employee e ON u.id = e.employee_id AND u.`type` = "employee"
LEFT JOIN skill s ON e.skill_id = s.id AND u.`type` = "employee" WHERE u.email = "";
