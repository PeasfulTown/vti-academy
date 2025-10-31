DROP DATABASE IF EXISTS fresher_training_management;
CREATE DATABASE IF NOT EXISTS fresher_training_management;
USE fresher_training_management;

DROP TABLE IF EXISTS trainee;

-- QUESTION 1
CREATE TABLE IF NOT EXISTS trainee (
	trainee_id			SMALLINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    full_name			VARCHAR(50) NOT NULL,
    birth_date 			DATE DEFAULT (CURRENT_DATE()) NOT NULL,
    gender				ENUM('male', 'female', 'unknown') NOT NULL, 
    et_iq				TINYINT UNSIGNED CHECK (0 <= et_iq AND et_iq <= 20) NOT NULL,
    et_gmath			TINYINT UNSIGNED CHECK (0 <= et_gmath AND et_gmath <= 20) NOT NULL,
    et_english			TINYINT UNSIGNED CHECK (0 <= et_english AND et_english <= 50) NOT NULL,
    training_class		CHAR(6) NOT NULL,
    evaluation_notes	VARCHAR(15)
);

-- QUESTION 2
INSERT INTO trainee (full_name			, birth_date	, gender	, et_iq	, et_gmath	, et_english	, training_class, evaluation_notes 	)
VALUES				("Alice Wong"		, "2000-03-28"	, "female"	, 15	, 13		, 40			, "VTI001"		, "DHBKHN"			),
					("John Smith"		, "1989-08-09"	, "male"	, 15	, 12		, 40			, "VTI001"		, "DHBKHN"			),
					("Adrian Smith"		, "1997-03-14"	, "unknown"	, 15	, 12		, 50			, "VTI002"		, "DHBKHN"			),
					("Shane Alexander"	, "1990-01-27"	, "female"	, 15	, 10		, 40			, "VTI002"		, "DHQGHN"			),
					("Cariane Doe"		, "1996-06-28"	, "female"	, 20	, 14		, 39			, "VTI003"		, "DHQGHN"			),
					("Alicia Davis"		, "2001-02-17"	, "female"	, 19	, 18		, 47			, "VTI003"		, "DHQGHN"			),
					("William Bill"		, "1998-07-21"	, "male"	, 2		, 10		, 38			, "VTI003"		, "HVBCVT"			),
					("Ali Ince"			, "1993-09-27"	, "male"	, 9		, 18		, 37			, "VTI004"		, "HVBCVT"			),
					("Bayley Miller"	, "1989-12-16"	, "male"	, 15	, 10		, 40			, "VTI004"		, "HVBCVT"			),
					("Nicole Palmer"	, "1990-09-20"	, "female"	, 15	, 10		, 40			, "VTI004"		, "HVBCVT"			),
					("Nguyen Dac"		, "1999-03-28"	, "male"	, 20	, 13		, 40			, "VTI001"		, "DHBKHN"			);
-- QUESTION 3 
-- CONSTRAINT VIOLATION
INSERT INTO trainee (full_name			, birth_date	, gender	, et_iq	, et_gmath	, et_english	, training_class, evaluation_notes 	)
VALUES				("Nguyen Dac"		, "1999-03-28"	, "male"	, 30	, 13		, 40			, "VTI001"		, "DHBKHN"			);

-- QUESTION 4
SELECT DAY(birth_date), GROUP_CONCAT(full_name)
FROM trainee
WHERE
	et_iq >= 12
	AND	et_gmath >= 12
    AND et_english >= 20
GROUP BY DAY(birth_date);
    
-- QUESTION 5
SELECT *
FROM trainee 
WHERE TRIM(full_name) LIKE ('N%C');

-- QUESTION 6
SELECT * 
FROM trainee
WHERE TRIM(full_name) LIKE ('_g%');

-- QUESTION 7
SELECT *
FROM trainee
WHERE TRIM(full_name) LIKE ('_________C');

-- QUESTION 8
SELECT DISTINCT full_name
FROM trainee;

-- QUESTION 9
SELECT full_name
FROM trainee
ORDER BY full_name;

-- QUESTION 10
SELECT * 
FROM trainee
WHERE LENGTH(full_name) = (
	SELECT MAX(LENGTH(full_name)) FROM trainee
);

-- QUESTION 11
SELECT trainee_id, full_name, birth_date
FROM trainee
WHERE LENGTH(full_name) = (
	SELECT MAX(LENGTH(full_name)) FROM trainee
);

-- QUESTION 12
SELECT full_name, et_iq, et_gmath, et_english
FROM trainee
WHERE LENGTH(full_name) = (
	SELECT MAX(LENGTH(full_name)) FROM trainee
);

-- QUESTION 13
SELECT *
FROM trainee
ORDER BY birth_date DESC
LIMIT 5;

-- QUESTION 14
SELECT *
FROM trainee
WHERE 
	(et_iq + et_gmath) >= 20
    AND et_iq >= 8 
    AND et_gmath >= 8
    AND et_english >= 18;
    
-- QUESTION 15
DELETE FROM trainee WHERE trainee_id = 5;

-- QUESTION 16
DELETE FROM trainee 
WHERE trainee_id = (
	SELECT trainee_id FROM (
		SELECT trainee_id 
        FROM trainee
        WHERE (et_iq + et_gmath) <= 15
	) AS t
);

-- QUESTION 17
DELETE FROM trainee
WHERE (TIMESTAMPDIFF(YEAR, birth_date, CURDATE())) > 30;

-- QUESTION 18
UPDATE trainee
SET training_class = 'VTI003'
WHERE trainee_id = 3;

-- QUESTION 19
UPDATE trainee
SET 
	full_name = 'LeVanA',
    et_iq = 10,
    et_gmath = 15,
    et_english = 30
WHERE trainee_id = 10;

SELECT * FROM trainee WHERE trainee_id = 10;

-- QUESTION 20 
SELECT COUNT(trainee_id)
FROM trainee
WHERE training_class = 'VTI001';

-- QUESTION 21
SELECT COUNT(trainee_id)
FROM trainee
WHERE training_class = 'VTI001';

-- QUESTION 22
SELECT COUNT(trainee_id)
FROM trainee
WHERE 
	training_class = 'VTI001'
    OR training_class = 'VTI003';
    
-- QUESTION 23
SELECT gender, COUNT(trainee_id)
FROM trainee
GROUP BY gender;

-- QUESTION 24
SELECT training_class, COUNT(trainee_id) AS count
FROM trainee
GROUP BY training_class
HAVING count > 5;

-- QUESTION 25
SELECT training_class, COUNT(trainee_id) AS count
FROM trainee
GROUP BY training_class
HAVING count > 5;

-- QUESTION 26
SELECT evaluation_notes, COUNT(trainee_id) AS count
FROM trainee
GROUP BY evaluation_notes
HAVING count < 4;

-- QUESTION 27
SELECT * 
FROM trainee
WHERE training_class = 'VTI001'
UNION
SELECT * 
FROM trainee
WHERE training_class = 'VTI002';

-- QUESTION 2
ALTER TABLE `trainee`
ADD COLUMN vti_account VARCHAR(50) NOT NULL UNIQUE KEY;

-- QUESTION 3

SELECT * FROM trainee;

-- QUESTION 4
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

-- QUESTION 5
 SELECT trainee_id, 
		full_name, 
		TIMESTAMPDIFF(YEAR, birth_date, CURDATE()) AS age, 
		gender, 
        et_iq, 
        et_gmath, 
        et_english, 
        training_class, 
        evaluation_notes, 
        vti_account 
 FROM `trainee` 
 WHERE	LENGTH(full_name) = (
	SELECT MAX(LENGTH(full_name))
	FROM `trainee`
);

-- QUESTION 6
SELECT * 
FROM `trainee`
WHERE et_iq + et_gmath>=20
AND et_iq>=8
AND et_gmath>=8
AND et_english>=18;

-- QUESTION 6
DELETE FROM `trainee`
WHERE trainee_id = (
	SELECT t.trainee_id FROM (
		SELECT trainee_id FROM `trainee` WHERE trainee_id=3
	) as t
);

-- QUESTION 7
UPDATE `trainee`
SET training_class="2"
WHERE trainee_id=5;

-- QUESTION 8
CREATE OR REPLACE VIEW passed_trainees 
AS (
	SELECT * 
    FROM `trainee`
    WHERE et_iq + et_gmath>=20
    AND et_iq>=8
    AND et_gmath>=8
    AND et_english>=18
);

SELECT * FROM passed_trainees;
