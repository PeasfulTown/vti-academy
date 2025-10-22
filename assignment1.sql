CREATE DATABASE IF NOT EXISTS db_testing_system;
USE db_testing_system;


DROP TABLE IF EXISTS `exam_question`;
DROP TABLE IF EXISTS `exam`;

DROP TABLE IF EXISTS `answer`;
DROP TABLE IF EXISTS `question`;

DROP TABLE IF EXISTS `group_account`;
DROP TABLE IF EXISTS `group`;

DROP TABLE IF EXISTS `account`;
DROP TABLE IF EXISTS `department`;
DROP TABLE IF EXISTS `position`;

DROP TABLE IF EXISTS `category_question`;
DROP TABLE IF EXISTS `type_question`;


CREATE TABLE `department`(
	department_id	SMALLINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    department_name	ENUM("sales", "marketing", "information-technology", "english", "history") NOT NULL UNIQUE KEY
);

CREATE TABLE `position`(
	position_id		SMALLINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    position_name	ENUM("dev", "test", "scrum-master", "pm") NOT NULL UNIQUE KEY
);

CREATE TABLE `account`(
	account_id		SMALLINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    email			VARCHAR(40) NOT NULL UNIQUE KEY,
    username		VARCHAR(10) NOT NULL UNIQUE KEY,
    fullname		VARCHAR(50) NOT NULL,
    department_id	SMALLINT UNSIGNED,
    position_id		SMALLINT UNSIGNED,
    create_date		DATETIME DEFAULT NOW() NOT NULL,
    
	FOREIGN KEY (department_id)
		REFERENCES `department`(department_id),
	FOREIGN KEY (position_id)
		REFERENCES `position`(position_id)
);

CREATE TABLE `group`(
	group_id		SMALLINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    group_name		VARCHAR(30) NOT NULL,
    creator_id		SMALLINT UNSIGNED NOT NULL,
    create_date		DATETIME DEFAULT NOW() NOT NULL,
    
    FOREIGN KEY (creator_id)
		REFERENCES `account`(account_id)
);

CREATE TABLE `group_account`(
	id 				SMALLINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	group_id		SMALLINT UNSIGNED NOT NULL,
    account_id		SMALLINT UNSIGNED NOT NULL,
    join_date		DATETIME DEFAULT NOW() NOT NULL,
    
    FOREIGN KEY(group_id)
		REFERENCES `group`(group_id),
    FOREIGN KEY(account_id)
		REFERENCES `account`(account_id)
);

CREATE TABLE `type_question`(
	type_id		SMALLINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    type_name	ENUM('essay', 'multiple-choice') NOT NULL
);

CREATE TABLE `category_question`(
	category_id		SMALLINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    category_name	VARCHAR(15) NOT NULL UNIQUE KEY
);

CREATE TABLE `question` (
    question_id 		SMALLINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    content 			TEXT NOT NULL,
    category_id 		SMALLINT UNSIGNED,
    type_id 			SMALLINT UNSIGNED,
    creator_id 			SMALLINT UNSIGNED NOT NULL,
    create_date 		DATETIME DEFAULT NOW() NOT NULL,
    
    FOREIGN KEY (category_id)
        REFERENCES `category_question` (category_id),
    FOREIGN KEY (type_id)
        REFERENCES `type_question` (type_id),
    FOREIGN KEY (creator_id)
        REFERENCES `account` (account_id)
);

CREATE TABLE `answer`(
	answer_id		SMALLINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    content			TEXT NOT NULL,
    question_id		SMALLINT UNSIGNED NOT NULL,
    is_correct		ENUM("true", "false", "undecided") DEFAULT "undecided" NOT NULL,
    
    FOREIGN KEY(question_id)
		REFERENCES `question`(question_id)
);

CREATE TABLE `exam`(
	exam_id			SMALLINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    code			CHAR(8) NOT NULL,
    title			VARCHAR(40) NOT NULL,
    category_id		SMALLINT UNSIGNED NOT NULL,
    duration		MEDIUMINT UNSIGNED,
    creator_id		SMALLINT UNSIGNED NOT NULL,
    create_date		DATETIME DEFAULT NOW() NOT NULL,
    
    FOREIGN KEY(category_id)
		REFERENCES `category_question`(category_id),
	FOREIGN KEY(creator_id)
		REFERENCES `account`(account_id)
);

CREATE TABLE `exam_question`(
	id 			SMALLINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	exam_id		SMALLINT UNSIGNED NOT NULL,
    question_id	SMALLINT UNSIGNED NOT NULL,
    
    FOREIGN KEY(exam_id)
		REFERENCES `exam`(exam_id),
    FOREIGN KEY(question_id)
		REFERENCES `question`(question_id)
);

-- TABLE RECORDS

-- department (department_name) 
INSERT INTO `department`(department_name)
VALUES	("information-technology"),
		("sales"),
		("marketing");

-- position (position_name)
INSERT INTO `position`(position_name)
VALUES	("dev"),
		("test"),
		("scrum-master"),
        ("pm");

-- account (email, username, fullname, department_id, position_id, create_date)
INSERT INTO `account`(email, username, fullname, department_id, position_id)
VALUES	("johns@example.com", "johns", "John Smith", 3, 4),
		("janes@example.com", "janes", "Jane Smith", 2, 3),
		("hanhn@example.com", "hanhn", "Hanh Nguyen", 3, 1);

-- group (group_id, group_name, creator_id, create_date)
INSERT INTO `group`(group_name, creator_id)
VALUES	("Project Brainstorming Session", 1);

-- group_account (group_id, account_id, join_date)
INSERT INTO `group_account`(group_id, account_id)
VALUES	(1, 1),
		(1, 2),
        (1, 3);

-- type_question (type_id, type_name)
INSERT INTO `type_question`(type_name)
VALUES	("multiple-choice"),
		("essay");

-- category_question (category_id, category_name)
INSERT INTO `category_question`(category_name)
VALUES	("java"),
		(".net"),
		("go"),
        ("mongodb");

-- question (question_id, content, category_id, type_id, creator_id, create_date)
INSERT INTO `question`(content, category_id, type_id, creator_id)
VALUES	("What is the correct syntax to output \"Hello, World!\" in Java?", 1, 1, 1),
		("Is Java short for JavaScript?", 1, 1, 1),
        ("What kind of database is MongoDB?", 4, 1, 1),
        ("What is a Table called in MongoDB?", 4, 1, 1);

-- answer (answer_id, content, question_id, is_correct)
INSERT INTO `answer`(content, question_id, is_correct)
VALUES	("System.out.println(\"Hello, World!\");", 1, "true"),
		("echo(\"Hello, World!\");", 1, "false"),
        ("Schema", 4, "false"),
        ("Collection", 4, "true"); 

-- exam (exam_id, code, title, category_id, duration, creator_id, create_date)
INSERT INTO `exam`(code, title, category_id, duration, creator_id)
VALUES	("COMP1011", "Java Programming Basics", 1, 3600, 1);

-- exam_question (exam_id, question_id)
INSERT INTO `exam_question`(exam_id, question_id)
VALUES	(1, 1),
		(1, 2);