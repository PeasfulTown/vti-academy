CREATE DATABASE IF NOT EXISTS vti_mark_management;
USE vti_mark_management;

-- QUESTION 1

DROP TABLE IF EXISTS trainee;
DROP TABLE IF EXISTS `subject`;
DROP TABLE IF EXISTS trainee_subject;

CREATE TABLE trainee (
	trainee_id		SMALLINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    first_name		VARCHAR(15) NOT NULL,
    last_name		VARCHAR(15) NOT NULL,
    age				TINYINT UNSIGNED NOT NULL,
    gender			ENUM('male', 'female', 'unknown') NOT NULL DEFAULT 'unknown'
);

CREATE TABLE `subject` (
	subject_id		SMALLINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    subject_name	ENUM(
					'MySQL'
                    , 'JavaCore'
                    , 'Frontend Basic'
                    , 'Spring Framework'
                    , 'Frontend Advanced'
                    , 'Mock Project' )
);

CREATE TABLE trainee_subject (
	id				SMALLINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	trainee_id		SMALLINT UNSIGNED NOT NULL,
    subject_id		SMALLINT UNSIGNED NOT NULL,
    mark			TINYINT UNSIGNED,
    exam_day		DATETIME,
    
    CONSTRAINT fk_trainee_subject_trainee_id 
		FOREIGN KEY (trainee_id)
        REFERENCES trainee (trainee_id),
	CONSTRAINT fk_trainee_subject_subject_id
		FOREIGN KEY (subject_id)
		REFERENCES subject (subject_id)
);


INSERT INTO trainee ( first_name		, last_name		, age	, gender	)
VALUES 				( 'Elly'			, 'Lisle'		, 29	, 'female'	),
					( 'Lion'			, 'Bernardez'	, 42	, 'male'	),
					( 'Nevin'			, 'Sleicht'		, 26	, 'unknown'	),
					( 'Johanna'			, 'Goadsby'		, 41	, 'female'	),
					( 'Caitrin'			, 'Goreisr'		, 41	, 'female'	),
					( 'Karole'			, 'Neilan'		, 51	, 'unknown'	),
					( 'Eugenio'			, 'Lohden'		, 52	, 'male'	),
					( 'Egbert'			, 'Terzza'		, 19	, 'female'	),
					( 'Willey'			, 'Kayser'		, 56	, 'male'	),
					( 'Westbrook'		, 'Cowden'		, 18	, 'female'	),
					( 'Kacy'			, 'Hyam'		, 53	, 'female'	),
					( 'Linda'			, 'MacAlroy'	, 32	, 'female'	);

INSERT INTO `subject`	( subject_name			)
VALUES					( 'MySQL'				),
						( 'JavaCore'			),
						( 'Frontend Basic'		),
						( 'Spring Framework'	),
						( 'Frontend Advanced'	),
						( 'Mock Project' 		);
                        
INSERT INTO trainee_subject ( trainee_id	, subject_id	, mark	, exam_day				)
VALUES						( 4				, 5				, 3		, '2005-03-14 22:08:59'	),
							( 10			, 6				, 2		, '2001-02-05 02:59:13'	),
							( 2				, 2				, 10	, '2023-10-07 16:56:19'	),
							( 8				, 3				, 3		, '2020-03-22 08:53:26'	),
							( 7				, 6				, 2		, '2005-11-17 15:25:30'	),
							( 11			, 1				, 4		, '2020-12-07 05:26:25'	),
							( 6				, 3				, 5		, '2024-03-16 17:25:33'	),
							( 1				, 6				, 6		, '2022-04-18 23:34:48'	),
							( 6				, 2				, 9		, '2017-02-21 12:44:40'	),
							( 2				, 1				, 10	, '2022-07-07 12:16:36'	),
							( 7				, 6				, 4		, '2022-10-09 14:40:41'	),
							( 9				, 1				, 9		, '2007-11-27 14:32:43'	);


-- QUESTION 2
	-- a)
SELECT s.subject_name
FROM `subject` s
LEFT JOIN trainee_subject ts
ON s.subject_id = ts.subject_id
WHERE mark IS NULL;
	-- b)
SELECT s.subject_name, COUNT(mark) AS mark_count
FROM `subject` s
INNER JOIN trainee_subject ts
ON s.subject_id = ts.subject_id
GROUP BY s.subject_name
HAVING mark_count >= 2;

-- QUESTION 3
CREATE OR REPLACE VIEW vw_trainee_info AS
	SELECT t.trainee_id
			, CONCAT(t.first_name, ' ', t.last_name) AS fullname
            , t.age
            , t.gender
            , s.subject_id
            , s.subject_name
            , ts.mark
            , ts.exam_day
	FROM trainee t
    LEFT JOIN trainee_subject ts
    ON t.trainee_id = ts.trainee_id
    LEFT JOIN `subject` s 
    ON ts.subject_id = s.subject_id;
    
SELECT * FROM vw_trainee_info;

-- QUESTION 4
	-- a)
ALTER TABLE trainee_subject DROP FOREIGN KEY fk_trainee_subject_trainee_id;
ALTER TABLE trainee_subject DROP FOREIGN KEY fk_trainee_subject_subject_id;

DESCRIBE trainee_subject;
DROP TRIGGER IF EXISTS trg_subject_update_id;
DELIMITER $$
CREATE TRIGGER trg_subject_update_id
	BEFORE UPDATE ON `subject`
    FOR EACH ROW
    BEGIN
		IF (NEW.subject_id <> OLD.subject_id) THEN 
            UPDATE trainee_subject 
			SET subject_id = NEW.subject_id
            WHERE subject_id = OLD.subject_id;
		END IF;
    END $$
DELIMITER ;

	-- b)
DROP TRIGGER IF EXISTS trg_student_delete_id;
DELIMITER $$
CREATE TRIGGER trg_student_delete_id
	BEFORE DELETE ON trainee
    FOR EACH ROW
    BEGIN
		DELETE FROM trainee_subject WHERE trainee_id = OLD.trainee_id;
    END $$
DELIMITER ;

-- QUESTION 5
SET SQL_SAFE_UPDATE = 0;
DROP PROCEDURE IF EXISTS sp_delete_trainee_by_name;
DELIMITER $$
CREATE PROCEDURE sp_delete_trainee_by_name(IN in_trainee_name VARCHAR(15))
	BEGIN 
		IF (in_trainee_name = '*') THEN
			TRUNCATE trainee;
		ELSE
			DELETE FROM trainee 
            WHERE trainee_id LIKE (
				SELECT trainee_id FROM (
					SELECT trainee_id FROM trainee WHERE first_name = in_trainee_name
				) AS temp
			);
        END IF;
    END $$
DELIMITER ;