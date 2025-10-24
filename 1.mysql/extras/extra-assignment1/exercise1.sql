DROP DATABASE IF EXISTS fresher_training_management;
CREATE DATABASE IF NOT EXISTS fresher_training_management;
USE fresher_training_management;

DROP TABLE IF EXISTS trainee;

CREATE TABLE IF NOT EXISTS trainee (
	trainee_id			SMALLINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    full_name			VARCHAR(50) NOT NULL,
    birth_date 			DATE DEFAULT (CURRENT_DATE()) NOT NULL,
    gender				ENUM('male', 'female', 'unknown') NOT NULL, 
    et_iq				TINYINT UNSIGNED CHECK (0 <= et_iq AND et_iq <= 20),
    et_gmath			TINYINT UNSIGNED CHECK (0 <= et_gmath AND et_gmath <= 20),
    et_english			TINYINT UNSIGNED CHECK (0 <= et_english AND et_english <= 50),
    training_class		CHAR(3),
    evaluation_notes	MEDIUMTEXT
);

ALTER TABLE `trainee`
ADD COLUMN vti_account VARCHAR(50) NOT NULL UNIQUE KEY;

INSERT INTO trainee (full_name, birth_date, gender, et_iq, et_gmath, et_english, training_class, evaluation_notes, vti_account)
VALUES	("Alice Wong", "2000-03-28", "female", 15, 13, 40, "DTN", "No Comment", "alicew"),
		("John Smith", "1989-08-09", "male", 15, 12, 40, "DTN", "No Comment", "johns"),
		("Adrian Smith", "1997-03-14", "unknown", 15, 12, 50, "DTN", "No Comment", "adrians"),
		("Shane Alexander", "1990-01-27", "female", 15, 10, 40, "DTN", "No Comment", "shanea"),
        ("Cariane Doe", "1996-06-28", "female", 20, 14, 39, "DTN", "No Comment", "carianed"),
        ("Alicia Davis", "2001-02-17", "female", 19, 18, 47, "DTN", "No Comment", "aliciad"),
        ("William Bill", "1998-07-21", "male", 2, 10, 38, "DTN", "No Comment", "williamb"),
        ("Ali Ince", "1993-09-27", "male", 9, 18, 37, "DTN", "No Comment", "alii"),
        ("Bayley Miller", "1989-12-16", "male", 15, 10, 40, "DTN", "No Comment", "bayleym"),
        ("Nicole Palmer", "1990-09-20", "female", 15, 10, 40, "DTN", "No Comment", "nicolep");
        
SELECT * FROM trainee;

-- QUESTION 2
SELECT COUNT(*) as passing_students_count, GROUP_CONCAT(full_name) AS student_names, MONTH(birth_date) as birth_month
FROM (
	SELECT * 
    FROM `trainee`
    WHERE et_iq + et_gmath>=20
    AND et_iq>=8
    AND et_gmath>=8
    AND et_english>=18
    ) as t
GROUP BY MONTH(birth_date)
ORDER BY MONTH(birth_date);

-- QUESTION 3
 SELECT trainee_id, full_name, TIMESTAMPDIFF(YEAR, birth_date, CURDATE()) AS age, gender, et_iq, et_gmath, et_english, training_class, evaluation_notes, vti_account 
 FROM `trainee` 
 WHERE	LENGTH(full_name)=(SELECT MAX(LENGTH(full_name))
		FROM `trainee`);

-- QUESTION 4
SELECT * 
FROM `trainee`
WHERE et_iq + et_gmath>=20
AND et_iq>=8
AND et_gmath>=8
AND et_english>=18;

-- QUESTION 5
DELETE FROM `trainee`
WHERE trainee_id = (
	SELECT t.trainee_id FROM (
		SELECT trainee_id FROM `trainee` WHERE trainee_id=3
	) as t
);

-- QUESTION 6
UPDATE `trainee`
SET training_class="2"
WHERE trainee_id=5;