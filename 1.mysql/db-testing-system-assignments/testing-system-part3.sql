USE db_testing_system;

-- QUESTION 2
SELECT * FROM department;

-- QUESTION 3
SELECT department_id FROM department WHERE department_name="sales";

-- QUESTION 4
SELECT * FROM `account` WHERE LENGTH(fullname)=(SELECT MAX(LENGTH(fullname)) from `account`);

-- QUESTION 5
SELECT *
FROM `account`	
WHERE	LENGTH(fullname)=(SELECT MAX(LENGTH(fullname)) 
		FROM `account` 
		WHERE department_id=3) 
AND department_id=3;

-- QUESTION 6
SELECT * 
FROM `group`
WHERE `create_date`<"2019-12-20 00:00:00";

-- QUESTION 7
-- TODO: add more records to `answer` table 
SELECT question_id 
FROM `answer`
GROUP BY question_id
HAVING COUNT(answer_id)>=4;

-- QUESTION 8
SELECT exam_id 
FROM exam 
WHERE duration>3600 
AND create_date<"2019-12-20 00:00:00";

-- QUESTION 9
SELECT *
FROM 	(SELECT *
		FROM `group` 
        ORDER BY create_date DESC LIMIT 5
        ) as x;

-- QUESTION 10
SELECT COUNT(*) FROM `account` WHERE department_id=2;

-- QUESTION 11
SELECT * 
FROM `account` 
WHERE fullname 
LIKE "D%o";

-- QUESTION 12
	-- Safe delete
DELETE FROM `exam` 
WHERE exam_id IN (
	SELECT e.exam_id FROM (
		SELECT exam_id FROM `exam` WHERE create_date<"2019-12-20 00:00:00"
	) AS e
);
	-- Unsafe delete
DELETE FROM `exam` WHERE create_date<"2019-12-20 00:00:00";

-- QUESTION 13
SELECT * FROM `question` WHERE content LIKE "Question%";

DELETE FROM `question` 
WHERE question_id IN (
	SELECT q.question_id FROM (
		SELECT question_id FROM `question` WHERE content LIKE "Question%"
	) AS q
);

-- QUESTION 14
SELECT * FROM `account` WHERE account_id=5;

UPDATE `account` 
SET fullname="Nguyen Ba Loc"
WHERE account_id=5;

-- QUESTION 15
SELECT * FROM `group_account` WHERE account_id=5;

UPDATE `group_account`
SET group_id=4 
WHERE account_id=5;