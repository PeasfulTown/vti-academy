USE db_testing_system;

-- QUESTION 1 
SELECT a.email, a.username, a.fullname, d.department_name, a.create_date
FROM `account` a
INNER JOIN `department` d
ON a.department_id = d.department_id;

-- QUESTION 2
SELECT email, username, fullname, create_date
FROM `account`
WHERE create_date > "2010-12-20 00:00:00"; 

-- QUESTION 3 
SELECT a.email, a.username, a.fullname
FROM `account` a
INNER JOIN `position` p
ON a.position_id = p.position_id
WHERE p.position_name = "dev";

-- QUESTION 4
SELECT department_name
FROM `account` a
INNER JOIN `department` d
ON a.department_id = d.department_id
GROUP BY d.department_id
HAVING COUNT(*) >= 3;

-- QUESTION 5

WITH tbl
AS (
	SELECT q.question_id, q.content, q.create_date, COUNT(*) as count
	FROM question q
	INNER JOIN exam_question eq 
	ON q.question_id = eq.question_id 
    GROUP BY eq.question_id
)
SELECT content AS question, count
FROM tbl
WHERE count = (
	SELECT MAX(count) FROM tbl
);

-- QUESTION 6
SELECT c.category_name, COUNT(q.category_id) AS count
FROM category_question c
LEFT JOIN question q
ON c.category_id = q.category_id
GROUP BY c.category_id;

-- QUESTION 7
SELECT q.content AS question, COUNT(eq.question_id) AS times_used_in_exams
FROM question q
LEFT JOIN exam_question eq
ON q.question_id = eq.question_id
GROUP BY q.question_id, eq.question_id;
    
-- QUESTION 8
WITH cte_question_answer
AS (
	SELECT q.content AS question, COUNT(a.answer_id) AS answer_count
	FROM `question` q
	LEFT JOIN `answer` a
	ON q.question_id = a.question_id
	GROUP BY q.question_id, a.question_id
)
SELECT question, answer_count
FROM cte_question_answer
WHERE answer_count = (
	SELECT MAX(answer_count) FROM cte_question_answer
);

-- QUESTION 9
SELECT g.group_name, COUNT(ga.account_id) as no_of_accounts
FROM `group` g
LEFT JOIN group_account ga
ON g.group_id = ga.group_id
GROUP BY g.group_id, ga.group_id;

-- QUESTION 10
WITH cte_position_account
AS (
	SELECT p.position_name, COUNT(a.account_id) AS no_of_people
    FROM `position` p
    LEFT JOIN `account` a
    ON p.position_id = a.position_id
    GROUP BY p.position_id, a.position_id
)
SELECT position_name, no_of_people
FROM cte_position_account
WHERE no_of_people = (
	SELECT MIN(no_of_people) FROM cte_position_account
);

-- QUESTION 11 
SELECT d.department_name, a.account_id, p.position_name
FROM department d
LEFT JOIN `account` a
ON d.department_id = a.department_id
LEFT JOIN position p
ON a.position_id = p.position_id
WHERE p.position_name IN ("pm", "dev", "scrum master");

WITH cte_department_position
AS (
	SELECT d.department_name, a.account_id, p.position_name
    FROM department d
    LEFT JOIN `account` a
    ON d.department_id = a.department_id
    LEFT JOIN position p
    ON a.position_id = p.position_id
    WHERE p.position_name IN ("dev", "pm", "scrum master")
)
SELECT DISTINCT cte.department_name, dc.no_of_devs, pmc.no_of_pms, smc.no_of_scrum_masters
FROM cte_department_position cte
LEFT JOIN (
	SELECT department_name, COUNT(account_id) AS no_of_devs
    FROM cte_department_position
    GROUP BY department_name, position_name
    HAVING position_name = "dev"
) AS dc
ON cte.department_name = dc.department_name
LEFT JOIN (
	SELECT department_name, COUNT(account_id) AS no_of_pms
    FROM cte_department_position
    GROUP BY department_name, position_name
    HAVING position_name = "pm"
) AS pmc
ON cte.department_name = pmc.department_name
LEFT JOIN (
	SELECT department_name, COUNT(account_id) AS no_of_scrum_masters
    FROM cte_department_position
    GROUP BY department_name, position_name
    HAVING position_name = "scrum master"
) AS smc
ON cte.department_name = smc.department_name;

-- QUESTION 12 
SELECT q.content, t.type_name, c.category_name, a.fullname AS creator, q.create_date, an.content AS correct_answer
FROM question q
LEFT JOIN type_question t
ON q.type_id = t.type_id
LEFT JOIN category_question c
ON q.category_id = c.category_id
LEFT JOIN `account` a
ON q.creator_id = a.account_id
LEFT JOIN answer an
ON q.question_id = an.question_id AND an.is_correct = "true";

-- QUESTION 13
SELECT t.type_name, COUNT(q.question_id) AS no_of_questions
FROM question q
LEFT JOIN type_question t
ON q.type_id = t.type_id
GROUP BY t.type_id;

-- QUESTION 14-15
SELECT g.group_name AS empty_groups
FROM `group` g
LEFT JOIN group_account ga 
ON g.group_id = ga.group_id
WHERE ga.account_id IS NULL;

-- QUESTION 16
SELECT q.content AS questions_w_no_answers
FROM question q
LEFT JOIN answer a
ON q.question_id = a.question_id
WHERE a.answer_id IS NULL;

-- QUESTION 17
SELECT a.email, a.username, a.fullname
FROM `account` a
INNER JOIN group_account ga
ON a.account_id = ga.account_id
WHERE ga.group_id = 1

UNION 

SELECT a.email, a.username, a.fullname
FROM `account` a
INNER JOIN group_account ga
ON a.account_id = ga.account_id 
WHERE ga.group_id = 2;

-- QUESTION 18
SELECT g.group_name, COUNT(ga.account_id) AS members
FROM `group` g
INNER JOIN group_account ga
ON g.group_id = ga.group_id
GROUP BY g.group_id
HAVING members > 5

INTERSECT

SELECT g.group_name, COUNT(ga.account_id) AS members
FROM `group` g
INNER JOIN group_account ga
ON g.group_id = ga.group_id
GROUP BY g.group_id
HAVING members < 7;