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
    department_name	VARCHAR(30) NOT NULL UNIQUE KEY
);

CREATE TABLE `position`(
	position_id		SMALLINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    position_name	VARCHAR(35) NOT NULL UNIQUE KEY
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
    group_name		VARCHAR(40) NOT NULL,
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
    type_name	VARCHAR(30) NOT NULL
);

CREATE TABLE `category_question`(
	category_id		SMALLINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    category_name	VARCHAR(25) NOT NULL UNIQUE KEY
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
    is_correct		ENUM("true", "false") NOT NULL,
    
    FOREIGN KEY(question_id)
		REFERENCES `question`(question_id)
);

CREATE TABLE `exam`(
	exam_id			SMALLINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    code			CHAR(8) NOT NULL,
    title			VARCHAR(50) NOT NULL,
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
VALUES	("administration"),
        ("security"),
        ("human resources"),
        ("financial accounting"),
		("sales"),
		("marketing"),
        ("research and development"),
        ("education"),
        ("information technology");

-- position (position_name)
INSERT INTO `position`(position_name)
VALUES	("president"),
		("vice president"),
        ("administrative assistant"),
		("chief operation officer"),
		("accountant"),
		("marketing manager"),
        ("sales representative"),
		("project manager"),
        ("software developer"),
        ("teacher"),
        ("teacher's assistant"),
        ("student");

-- account (email, username, fullname, department_id, position_id, create_date)
INSERT INTO `account`(email, username, fullname, department_id, position_id)
VALUES	("williamd@example.com", "willd", "William Doe", 1, 1),
		("joes@example.com", "joes", "Joe Schmoe", 1, 2),
		("johns@example.com", "johns", "John Smith", 7, 8),
		("janes@example.com", "janes", "Jane Smith", 7, 9),
        ("alicew@example.com", "alicew", "Alice Wong", 7, 9), 
		("hanhn@example.com", "hanhn", "Hanh Nguyen", 9, 9),
        ("emilyp@example.com", "emilyp", "Emily Parisien", 8, 10),
        ("nicoleb@example.com", "nicoleb", "Nicole Bayley", 8, 11),
        ("clayj@example.com", "clayj", "Clayton John", 8, 10),
        ("jaredm@example.com", "jaredm", "Jared Miller", 8, 12),
        ("sheilab@example.com", "sheilab", "Sheila Brown", 8, 12),
        ("bayleya@example.com", "bayleym", "Bayley Anderson", 8, 12),
        ("aliciad@example.com", "aliciad", "Alicia Davis", 8, 12);

-- group (group_id, group_name, creator_id, create_date)
INSERT INTO `group`(group_name, creator_id)
VALUES	("General Meetings", 1),
		("Quarterly Financials Review", 4),
        ("Cyber Security Awareness Meeting", 3),
        ("Administration", 1),
		("Project Brainstorming Session", 3),
        ("Teachers", 7),
        ("Student Resources", 10), 
        ("Study Group", 11),
        ("Computer Science Group", 11),
        ("English Club", 13), 
        ("Extracurricular Activity Org", 12);

-- group_account (group_id, account_id, join_date)
INSERT INTO `group_account`(group_id, account_id)
VALUES	(1, 1),
		(1, 2),
        (1, 3),
        (1, 4),
        (2, 1),
        (2, 2),
        (2, 3),
        (2, 4),
        (9, 11), 
        (9, 12),
        (10, 10),
        (10, 11),
        (11, 10),
        (11, 13);

-- type_question (type_id, type_name)
INSERT INTO `type_question`(type_name)
VALUES	("multiple choice"),
		("short answer"),
        ("true or false"),
        ("match answers"),
        ("fill in the blank"),
		("essay"),
        ("open question"),
        ("oral"),
        ("long answer"),
        ("practical");

-- category_question (category_id, category_name)
INSERT INTO `category_question`(category_name)
VALUES	("java"),
		(".net"),
		("go"),
        ("mongodb"),
        ("linux"),
        ("git"),
        ("mysql"),
        ("javascript"),
        ("english"),
        ("rust"),
        ("artifical intelligence"),
        ("cloud computing"),
        ("game development");

-- question (question_id, content, category_id, type_id, creator_id, create_date)
INSERT INTO `question`(content, category_id, type_id, creator_id)
VALUES	("What is the correct syntax to output \"Hello, World!\" in Java?", 1, 1, 7),
		("Is Java short for JavaScript?", 1, 3, 7),
        ("What kind of database is MongoDB?", 4, 1, 8),
        ("What is a Table called in MongoDB?", 4, 1, 7),
        ("Are Primary Keys nullable?", 7, 3, 8),
        ("Describe a challenging cloud project you've worked on. What were the key challenges, and how did you overcome them?", 12, 6, 8),
        ("What is cloud computing?", 12, 2, 8),
        ("How to push a local git repository to remote?", 6, 2, 7),
        ("Which of the commands below will create a new git branch?", 6, 1, 8),
        ("Which Linux command creates a new file?", 5, 1, 8);

-- answer (answer_id, content, question_id, is_correct)
INSERT INTO `answer`(content, question_id, is_correct)
VALUES	("System.out.println(\"Hello, World!\");", 1, "true"),
		("echo(\"Hello, World!\");", 1, "false"),
        ("Schema", 4, "false"),
        ("Collection", 4, "true"),
        ("No", 5, "true"),
        ("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras eget lacus et risus varius gravida. 
        Nullam at fermentum leo. Aliquam erat volutpat. Vivamus blandit et lorem ac efficitur. 
        Vestibulum a urna risus. Vivamus tincidunt felis id magna fringilla tristique. 
        Suspendisse non elementum mi. Quisque eget libero eu ligula placerat finibus. 
        Vivamus urna sapien, faucibus non semper sed, feugiat et nunc. 
        Quisque sit amet nibh fringilla, gravida arcu ac, aliquet diam. 
        Aenean a dui laoreet, condimentum urna at, sollicitudin ante. 
        Nulla imperdiet varius interdum. Integer gravida felis a turpis rhoncus, eu pretium metus consequat. 
        Aenean vestibulum arcu orci, in bibendum orci efficitur a. Nunc sit amet pulvinar orci. 
        Pellentesque magna ante, ultricies sed mattis ut, varius ut erat.", 6, "true"),
		("Cloud computing is when we use on-premise servers.", 7, "false"),
        ("git push origin main", 8, "true"),
        ("git create new branch \"new_branch\" ", 8, "false"),
        ("touch new_file", 9, "true");
        
-- exam (exam_id, code, title, category_id, duration, creator_id, create_date)
INSERT INTO `exam`(code, title, category_id, duration, creator_id)
VALUES	("COMP1011", "Java Programming Basics Midterm", 1, 3600, 7),
		("COMP1022", "Linux Operating System 101", 5, 1200, 8),
        ("COMP0101", "Serverless Databases 101", 4, 2400, 8),
        ("COMP2012", "Relational Databases", 7, 3600, 7),
        ("COMP2211", "Java Concurrency in Practice", 1, 3600, 7), 
        ("COMP1302", "Cloud Computing Basics", 6, 3600, 8),
        ("COMP3030", "Vertical and Horizontal Scaling in the Cloud", 6, 3600, 8),
        ("COMP2240", "Version Control with Git", 7, 3600, 7),
        ("COMP1091", "Collaborative Development and CI/CD", 7, 3600, 8),
        ("GAME1010", "Game Development Basics in Unity", 13, 3600, 7);

-- exam_question (exam_id, question_id)
INSERT INTO `exam_question`(exam_id, question_id)
VALUES	(1, 1),
		(1, 2),
        (3, 3),
        (3, 4),
        (4, 5),
        (6, 6),
        (6, 7),
        (8, 8),
        (9, 9),
        (2, 10);
        
        