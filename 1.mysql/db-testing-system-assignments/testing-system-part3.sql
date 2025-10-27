USE db_testing_system;

-- QUESTION 2
SELECT department_name FROM department;

-- QUESTION 3
SELECT department_id FROM department WHERE department_name="sales";

-- QUESTION 4
WITH cte_longest_name
AS (
	SELECT MAX(LENGTH(fullname)) AS max_count FROM `account`
) 
SELECT email, username, fullname
FROM `account`
WHERE LENGTH(fullname) = (
	SELECT max_count FROM cte_longest_name
);

-- QUESTION 5
WITH cte_longest_name
AS (
	SELECT MAX(LENGTH(fullname)) AS max_count 
    FROM `account`
    WHERE department_id = 3
) 
SELECT email, username, fullname
FROM `account`
WHERE LENGTH(fullname) = (
	SELECT max_count FROM cte_longest_name
);

-- QUESTION 6
SELECT group_name
FROM `group`
WHERE create_date < '2019-12-20 00:00:00';

-- QUESTION 7 
SELECT question_id 
FROM `answer`
GROUP BY question_id
HAVING COUNT(answer_id) >= 4;

-- QUESTION 8
SELECT exam_id 
FROM exam 
WHERE duration > 3600 
AND create_date < '2019-12-20 00:00:00';

-- QUESTION 9
SELECT * FROM `group` ORDER BY create_date DESC LIMIT 5;

-- QUESTION 10
SELECT COUNT(*) FROM `account` WHERE department_id = 2;

-- QUESTION 11
SELECT * FROM `account` WHERE fullname LIKE "D%o";

-- QUESTION 12
	-- Safe delete
DELETE FROM `exam` 
WHERE exam_id IN (
	SELECT e.exam_id FROM (
		SELECT exam_id FROM `exam` WHERE create_date < '2019-12-20 00:00:00'
	) AS e
);

-- QUESTION 13
SELECT * FROM `question` WHERE content LIKE "Cau_Hoi%";

DELETE FROM `question` 
WHERE question_id IN (
	SELECT q.question_id FROM (
		SELECT question_id FROM `question` WHERE content LIKE "Cau_Hoi%"
	) AS q
);

-- QUESTION 14
SELECT * FROM `account` WHERE account_id=5;

UPDATE `account` 
SET fullname="Nguyen Ba Loc"
WHERE account_id = 5;

-- QUESTION 15
SELECT * FROM `group_account` WHERE account_id=5;

UPDATE `group_account`
SET group_id = 4 
WHERE account_id = 5;