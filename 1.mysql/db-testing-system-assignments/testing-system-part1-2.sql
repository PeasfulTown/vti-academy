DROP DATABASE IF EXISTS db_testing_system;
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
    department_name	ENUM(
		'sales', 
        'marketing', 
        'human resources', 
        'finance', 
        'information technology', 
        'customer service',
        'maintenance',
        'security',
        'operations',
        'administration',
        'waiting room') NOT NULL UNIQUE KEY
);

CREATE TABLE `position`(
	position_id		SMALLINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    position_name	ENUM(
		'vice president',
        'director',
        'manager',
        'assistant manager',
        'tech lead',
        'pm',
        'scrum master',
        'dev',
        'test',
        'intern',
        'trainee') NOT NULL UNIQUE KEY
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
		REFERENCES `department`(department_id) ON DELETE CASCADE,
	FOREIGN KEY (position_id)
		REFERENCES `position`(position_id) ON DELETE CASCADE
);

CREATE TABLE `group`(
	group_id		SMALLINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    group_name		VARCHAR(40) NOT NULL,
    creator_id		SMALLINT UNSIGNED NOT NULL,
    create_date		DATETIME DEFAULT NOW() NOT NULL,
    
    FOREIGN KEY (creator_id)
		REFERENCES `account`(account_id) ON DELETE CASCADE
);

CREATE TABLE `group_account`(
	id 				SMALLINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	group_id		SMALLINT UNSIGNED NOT NULL,
    account_id		SMALLINT UNSIGNED NOT NULL,
    join_date		DATETIME DEFAULT NOW() NOT NULL,
    
    FOREIGN KEY(group_id)
		REFERENCES `group`(group_id) ON DELETE CASCADE,
    FOREIGN KEY(account_id)
		REFERENCES `account`(account_id) ON DELETE CASCADE
);

CREATE TABLE `type_question`(
	type_id		SMALLINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    type_name	ENUM(
		'multiple choice',
        'essay',
        'short answer',
        'long answer',
        'practical',
        'project',
        'oral',
        'matching',
        'fill in the blank', 
        'true or false') NOT NULL
);

CREATE TABLE `category_question`(
	category_id		SMALLINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    category_name	ENUM(
		'java',
        '.net',
        'sql',
        'postman',
        'ruby',
        'linux',
        'cloud',
        'ai',
        'game',
        'javascript') NOT NULL UNIQUE KEY
);

CREATE TABLE `question` (
    question_id 		SMALLINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    content 			TEXT NOT NULL,
    category_id 		SMALLINT UNSIGNED,
    type_id 			SMALLINT UNSIGNED,
    creator_id 			SMALLINT UNSIGNED NOT NULL,
    create_date 		DATETIME DEFAULT NOW() NOT NULL,
    
    FOREIGN KEY (category_id)
        REFERENCES `category_question` (category_id) ON DELETE CASCADE,
    FOREIGN KEY (type_id)
        REFERENCES `type_question` (type_id) ON DELETE CASCADE,
    FOREIGN KEY (creator_id)
        REFERENCES `account` (account_id) ON DELETE CASCADE
);

CREATE TABLE `answer`(
	answer_id		SMALLINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    content			TEXT NOT NULL,
    question_id		SMALLINT UNSIGNED NOT NULL,
    is_correct		ENUM("true", "false") NOT NULL,
    
    FOREIGN KEY(question_id)
		REFERENCES `question`(question_id) ON DELETE CASCADE
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
		REFERENCES `category_question`(category_id) ON DELETE CASCADE,
	FOREIGN KEY(creator_id)
		REFERENCES `account`(account_id) ON DELETE CASCADE
);

CREATE TABLE `exam_question`(
	id 			SMALLINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	exam_id		SMALLINT UNSIGNED NOT NULL,
    question_id	SMALLINT UNSIGNED NOT NULL,
    
    FOREIGN KEY(exam_id)
		REFERENCES `exam`(exam_id),
    FOREIGN KEY(question_id)
		REFERENCES `question`(question_id) ON DELETE CASCADE
);

-- TABLE RECORDS

-- department (department_name) 
INSERT INTO `department`(department_name)
VALUES 	('sales'),
        ('marketing'), 
        ('human resources'), 
        ('finance'), 
        ('information technology'), 
        ('customer service'),
        ('maintenance'),
        ('security'),
        ('operations'),
        ('administration'),
        ('waiting room');

-- position (position_name)
INSERT INTO `position`(position_name)
VALUES	('vice president'),
		('director'),
        ('manager'),
        ('assistant manager'),
        ('tech lead'),
        ('pm'),
        ('scrum master'),
        ('dev'),
        ('test'),
        ('intern'),
        ('trainee');

-- account (email, username, fullname, department_id, position_id, create_date)
INSERT INTO `account` 	(email						, username		, fullname			, department_id	, position_id	, create_date)
VALUES					("williamd@example.com"		, "willd"		, "William Doe"		, 2				, 1				, '2009-02-09 00:00:00'),
						("joes@example.com"			, "joes"		, "Joe Schmoe"		, 8				, 1				, '2009-03-02 00:00:00'),
						("johns@example.com"		, "johns"		, "John Smith"		, 7				, 2				, '2019-12-30 00:00:00'),
						("janes@example.com"		, "janes"		, "Jane Smith"		, 3				, 2				, '2020-01-01 00:00:00'),
						("alicew@example.com"		, "alicew"		, "Alice Wong"		, 5				, 3				, '2021-04-05 00:00:00'), 
						("hanhn@example.com"		, "hanhn"		, "Hanh Nguyen"		, 8				, 3				, '2023-08-19 00:00:00'),
						("emilyp@example.com"		, "emilyp"		, "Emily Parisien"	, 6				, 3				, '2018-11-12 00:00:00'),
						("nicoleb@example.com"		, "nicoleb"		, "Nicole Bayley"	, 4				, 4				, '2017-06-27 00:00:00'),
						("clayj@example.com"		, "clayj"		, "Clayton John"	, 5				, 8				, '2021-01-30 00:00:00'),
						("jaredm@example.com"		, "jaredm"		, "Jared Miller"	, 4				, 4				, '2024-08-14 00:00:00'),
						("sheilab@exadmple.com"		, "sheilab"		, "Sheila Brown"	, 5				, 5				, '2015-02-17 00:00:00'),
						("bayleya@example.com"		, "bayleym"		, "Bayley Anderson"	, 1				, 7				, '2023-07-11 00:00:00'),
						("aliciad@example.com"		, "aliciad"		, "Alicia Davis"	, 2				, 6				, '2011-09-28 00:00:00'),
						("dinhd@example.com"		, "dinhd"		, "Dinh Do"			, 5				, 7				, '2013-07-19 00:00:00'),
						("angelp@example.com"		, "angelp"		, "Angel Phillips"	, 3				, 9				, '2019-05-27 00:00:00'),
						("josephm@example.com"		, "josephm"		, "Joseph Martin"	, 10			, 9				, '2020-11-08 00:00:00'),
						("theob@example.com"		, "theob"		, "Theodore Bailey"	, 9				, 8				, '2021-01-03 00:00:00'),
						("andrewa@example.com"		, "andrewa"		, "Andrew Adams"	, 4				, 8				, '2011-02-18 00:00:00'),
						("ryanc@example.com"		, "ryanc"		, "Ryan Collins"	, 5				, 8 			, '2019-09-12 00:00:00'),
						("romanc@example.com"		, "romanc"		, "Roman Cook"		, 5				, 8				, '2017-07-19 00:00:00'),
						("loganj@example.com"		, "loganj"		, "Logan Jimenez"	, 5				, 8				, '2021-04-10 00:00:00'),
						("danielc@example.com"		, "danielc"		, "Daniel Cruz"		, 5 			, 8				, '2021-09-10 00:00:00');

-- group (group_id, group_name, creator_id, create_date)
INSERT INTO `group`	(group_name					, creator_id	, create_date)
VALUES				('Project Development'		, 1				, '2010-12-20 00:00:00'	),
					('Brand Building'			, 3				, '2012-03-04 00:00:00' ),
					('Inspiration'				, 2				, '2020-11-09 00:00:00'	),
                    ('Office Avengers'			, 4				, '2021-04-19 00:00:00' ),
                    ('Agile Alliance'			, 5				, '2017-07-27 00:00:00' ),
                    ('Breakout Room'			, 5				, '2014-05-29 00:00:00' ),
                    ('Spreadsheet Shredders'	, 6				, '2024-01-30 00:00:00' ), 
                    ('Scrummin\' it'			, 7				, '2014-08-11 00:00:00' ),
                    ('Breakroom Bears'			, 2				, '2018-03-21 00:00:00' ),
                    ('Office Ninjas'			, 3				, '2000-12-30 00:00:00' );

-- group_account (group_id, account_id, join_date)
INSERT INTO `group_account` (group_id, account_id)
VALUES	(1, 5),
		(1, 3),
        (1, 6),
        (1, 4),
        (1, 9),
        (1, 6),
        (1, 7),
        (1, 8),
        (2, 1),
        (2, 2),
        (2, 3),
        (2, 4),
        (2, 5),
        (2, 10),
        (3, 5),
        (3, 8),
		(3, 9),
        (3, 6),
        (3, 2),
        (4, 2),
        (4, 3),
        (4, 4),
		(4, 7),
        (4, 10),
        (5, 2),
        (5, 3),
        (6, 4),
		(6, 5),
		(7, 6),
        (8, 7),
        (8, 8),
        (8, 9),
        (9, 10),
        (9, 1),
		(9, 2),
        (10, 3),
        (10, 4),
        (10, 5),
        (10, 6);
        
-- type_question (type_id, type_name)
INSERT INTO `type_question`(type_name)
VALUES	('multiple choice'),
		('essay'),
        ('short answer'),
        ('long answer'),
        ('practical'),
        ('project'),
        ('oral'),
        ('matching'),
        ('fill in the blank'),
        ('true or false');

-- category_question (category_id, category_name)
INSERT INTO `category_question`(category_name)
VALUES	('java'),
		('.net'),
        ('sql'),
        ('postman'),
        ('ruby'),
        ('linux'),
        ('cloud'),
        ('ai'),
        ('game'),
        ('javascript');
		
-- question (question_id, content, category_id, type_id, creator_id, create_date)
INSERT INTO `question`	(content																			, category_id	, type_id	, creator_id	, create_date)
VALUES					("Lorem ipsum dolor sit amet, consectetur adipiscing elit."							, 1				, 10		, 2				, '2025-01-23 00:00:00'),
						("Praesent et purus vitae enim pellentesque placerat at ac libero."					, 1				, 10		, 3				, '2023-02-04 00:00:00'),
						("Vivamus ut felis interdum, fermentum leo vel, finibus lectus."					, 1				, 1			, 7				, '2024-03-09 00:00:00'),
						("Duis convallis tortor a tellus rutrum semper."									, 1				, 2			, 6				, '2021-04-20 00:00:00'),
						("Integer lobortis nibh condimentum sapien fringilla laoreet."						, 1 			, 3			, 5				, '2020-05-01 00:00:00'),
						("Mauris vel ipsum a sem dignissim malesuada quis nec sapien."						, 1				, 1			, 9				, '2019-06-08 00:00:00'),
						("Nam tempus eros vitae nisi imperdiet, quis laoreet elit hendrerit."				, 2				, 1			, 8				, '2024-07-13 00:00:00'),
						("Proin condimentum lorem in est feugiat, vitae convallis dolor consequat."			, 2				, 1			, 7				, '2025-08-15 00:00:00'),
						("Phasellus egestas turpis ut felis dictum maximus."								, 2				, 1			, 10			, '2025-09-04 00:00:00'),
						("Fusce ornare leo vel porta fermentum."											, 2				, 2			, 2				, '2025-09-08 00:00:00'),
                        ("Suspendisse convallis velit vitae augue cursus, eu blandit orci blandit."			, 3				, 2			, 3				, '2025-11-30 00:00:00'),
						("Phasellus nec erat et nibh venenatis ultricies."									, 3				, 1			, 1				, '2025-12-28 00:00:00'),
                        ("Mauris eu dui finibus, euismod mi eget, facilisis nunc."							, 4				, 1			, 1				, '2025-02-24 00:00:00'),
                        ("Donec vitae libero sagittis, iaculis diam et, consequat ligula."					, 4				, 8			, 9				, '2025-03-17 00:00:00'),
                        ("Phasellus nec erat et nibh venenatis ultricies."									, 5				, 6			, 8				, '2025-04-19 00:00:00'),
                        ("Etiam molestie nulla eu interdum volutpat."										, 5				, 2			, 5				, '2025-05-20 00:00:00'),
                        ("Vivamus viverra massa in ligula mollis elementum."								, 6				, 5			, 6				, '2025-06-28 00:00:00'),
                        ("Nam at massa vel erat viverra malesuada."											, 6				, 9			, 1				, '2025-07-30 00:00:00'),
                        ("Donec tempor metus sed orci convallis commodo."									, 7				, 8			, 1				, '2025-03-10 00:00:00'),
                        ("Sed cursus libero ut elit dignissim, id gravida sem pharetra."					, 8				, 4			, 2				, '2025-09-22 00:00:00'),
                        ("Mauris sodales lorem at felis tincidunt, quis tempus ex porta."					, 9				, 5			, 3				, '2025-02-19 00:00:00'),
                        ("Question 1"																		, 9 			, 1			, 1				, '2025-09-07 00:00:00');

INSERT INTO `question` 	(content, category_id	, type_id	, creator_id) 		
VALUE					("Nunc et consequat dolor. Duis tortor tellus, euismod sed purus sit amet, condimentum finibus ante. Aenean nec tellus in quam dignissim placerat eget non nulla. Sed aliquam nulla sem, ut malesuada justo varius eget. Vestibulum tempor purus ac purus sodales faucibus. Suspendisse potenti. Suspendisse et massa sed purus blandit tincidunt. Sed at laoreet nisi. Quisque molestie felis tellus, sed lobortis risus imperdiet sit amet. Sed eget mi mauris. Aenean accumsan volutpat velit, tempor ullamcorper lorem lobortis quis. Aenean nec aliquet purus. Praesent nec dui id dui pellentesque tempor sit amet sit amet dui. Duis porttitor egestas dui.
Nunc quis commodo orci. Aenean placerat suscipit libero quis mollis. Donec auctor posuere posuere. Donec semper mattis lorem, non ultrices orci fringilla in. Nulla molestie quam dapibus dignissim pulvinar. Pellentesque arcu augue, ultricies porta massa quis, sodales rutrum diam. Integer non condimentum mauris. Cras ut nunc ut nunc dictum tempus a volutpat diam. Integer efficitur, metus a varius faucibus, nulla augue auctor augue, sed porta urna orci ut ipsum. Nullam vel tristique augue. Pellentesque eu turpis eu velit molestie aliquet eget ornare nisi. In non sapien aliquam, dapibus sem ac, sollicitudin quam. Quisque tristique dignissim sapien. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Curabitur cursus malesuada odio vel pulvinar. Sed sed est maximus, faucibus justo eu, molestie lectus.
Quisque vitae lacus iaculis, ornare elit non, feugiat nunc. Proin cursus ipsum eu tellus lacinia, nec condimentum tortor semper. Nulla lobortis bibendum nisl. Cras ultricies convallis eros, quis pulvinar diam pellentesque at. Nulla finibus dui vitae metus ullamcorper commodo. Duis laoreet augue tellus, vel lacinia urna feugiat sed. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae; Ut et rhoncus mi. Maecenas auctor ornare eros, sed maximus libero semper eget. Pellentesque mattis, augue vitae cursus posuere, tellus ex egestas dolor, sit amet lobortis elit lorem porta."
								, 3				, 1			, 2);

INSERT INTO `question` 	(content, category_id	, type_id	, creator_id) 		
VALUE					("Nunc et asfjirw dolor. Duis tortor tellus, euismod sed purus sit amet, condimentum finibus ante. Aenean nec tellus in quam dignissim placerat eget non nulla. Sed aliquam nulla sem, ut malesuada justo varius eget. Vestibulum tempor purus ac purus sodales faucibus. Suspendisse potenti. Suspendisse et massa sed purus blandit tincidunt. Sed at laoreet nisi. Quisque molestie felis tellus, sed lobortis risus imperdiet sit amet. Sed eget mi mauris. Aenean accumsan volutpat velit, tempor ullamcorper lorem lobortis quis. Aenean nec aliquet purus. Praesent nec dui id dui pellentesque tempor sit amet sit amet dui. Duis porttitor egestas dui.
Nunc quis commodo orci. Aenean placerat suscipit libero quis mollis. Donec auctor posuere posuere. Donec semper mattis lorem, non ultrices orci fringilla in. Nulla molestie quam dapibus dignissim pulvinar. Pellentesque arcu augue, ultricies porta massa quis, sodales rutrum diam. Integer non condimentum mauris. Cras ut nunc ut nunc dictum tempus a volutpat diam. Integer efficitur, metus a varius faucibus, nulla augue auctor augue, sed porta urna orci ut ipsum. Nullam vel tristique augue. Pellentesque eu turpis eu velit molestie aliquet eget ornare nisi. In non sapien aliquam, dapibus sem ac, sollicitudin quam. Quisque tristique dignissim sapien. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Curabitur cursus malesuada odio vel pulvinar. Sed sed est maximus, faucibus justo eu, molestie lectus.
Quisque vitae lacus iaculis, ornare elit non, feugiat nunc. Proin cursus ipsum eu tellus lacinia, nec condimentum tortor semper. Nulla lobortis bibendum nisl. Cras ultricies convallis eros, quis pulvinar diam pellentesque at. Nulla finibus dui vitae metus ullamcorper commodo. Duis laoreet augue tellus, vel lacinia urna feugiat sed. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae; Ut et rhoncus mi. Maecenas auctor ornare eros, sed maximus libero semper eget. Pellentesque mattis, augue vitae cursus posuere, tellus ex egestas dolor, sit amet lobortis elit lorem porta."
								, 6				, 3			, 9);
                                
                                
-- answer (answer_id, content, question_id, is_correct)
INSERT INTO `answer` 	(content														, question_id	, is_correct)
VALUES					('sed sagittis nam congue risus semper porta volutpat quam'		, 12			, 'false'	),
						('nonummy integer non velit donec diam neque'					, 15			, 'false'	),
						('ridiculus mus etiam vel augue vestibulum'						, 14			, 'true'	),
						('ultrices aliquet maecenas leo odio condimentum id'			, 20			, 'true'	),
						('morbi non lectus aliquam sit amet diam in'					, 8				, 'true'	),
						('adipiscing elit proin interdum mauris non'					, 14			, 'true'	),
						('urna pretium nisl ut volutpat sapien arcu'					, 12			, 'true'	),
						('congue vivamus metus arcu adipiscing molestie hendrerit'		, 19			, 'false'	),
						('purus sit amet nulla quisque arcu libero rutrum ac'			, 16			, 'true'	),
						('hac habitasse platea dictumst maecenas ut massa'				, 9				, 'true'	),
						('suspendisse ornare consequat lectus in'						, 8				, 'false'	),
						('augue a suscipit nulla elit ac'								, 16			, 'false'	),
						('vulputate luctus cum sociis natoque penatibus et'				, 6				, 'true'	),
						('diam neque vestibulum eget vulputate'							, 15			, 'false'	),
						('sed ante vivamus tortor duis mattis'							, 13			, 'true'	),
						('pellentesque ultrices mattis odio donec'						, 7				, 'true'	),
						('morbi odio odio elementum eu interdum'						, 19			, 'true'	),
						('ultrices mattis odio donec vitae'								, 13			, 'false'	),
						('odio curabitur convallis duis consequat dui nec nisi volutpat', 13			, 'false'	),
						('ipsum ac tellus semper interdum mauris ullamcorper purus'		, 4				, 'true'	),
						('ut erat id mauris vulputate elementum'						, 21			, 'true'	),
						('in est risus auctor sed tristique in tempus'					, 9				, 'false'	),
						('parturient montes nascetur ridiculus mus etiam vel'			, 19			, 'true'	),
						('sit amet justo morbi ut odio'									, 13			, 'true'	);

                        
-- exam (exam_id, code, title, category_id, duration, creator_id, create_date)
INSERT INTO `exam`	(code		, title												, category_id	, duration	, creator_id, create_date)
VALUES				("COMP1011"	, "Java Programming Basics Midterm"					, 1				, 3600		, 7			, '2022-10-29 00:00:00'),
					("COMP1022"	, "Linux Operating System 101"						, 5				, 1200		, 8			, '2019-02-10 00:00:00'),
					("COMP0101"	, "Serverless Databases 101"						, 4				, 2400		, 8			, '2010-12-11 00:00:00'),
					("COMP2012"	, "Relational Databases"							, 7				, 3600		, 7			, '2021-03-23 00:00:00'),
					("COMP2211"	, "Java Concurrency in Practice"					, 1				, 3600		, 7			, '2024-01-18 00:00:00'), 
					("COMP1302"	, "Cloud Computing Basics"							, 6				, 3600		, 8			, '2017-08-02 00:00:00'),
					("COMP3030"	, "Scaling in the Cloud"							, 6				, 3600		, 8			, '2019-09-07 00:00:00'),
					("COMP2240"	, "Version Control with Git"						, 7				, 3600		, 7			, '2023-04-30 00:00:00'),
					("COMP1091"	, "Collaborative Development and CI/CD"				, 7				, 3600		, 8			, '2012-12-10 00:00:00'),
					("GAME1010"	, "Game Development Basics in Unity"				, 10			, 3600		, 7			, '2015-03-18 00:00:00'),
					("ENGL1001"	, "English For Everyday Conversations"				, 9				, 4800		, 8			, '2022-08-29 00:00:00');

-- exam_question (exam_id, question_id)
INSERT INTO `exam_question`(exam_id, question_id)
VALUES 	(10, 8),
		(2, 17),
		(2, 17),
		(6, 21),
		(7, 22),
		(10, 18),
		(9, 17),
		(2, 2),
		(10, 8),
		(11, 18),
		(2, 20),
		(3, 7),
		(10, 3),
		(3, 13),
		(1, 2),
		(3, 14),
		(7, 9),
		(3, 19),
		(5, 18),
		(6, 8),
		(6, 11),
		(5, 1),
		(5, 15),
		(1, 21);

