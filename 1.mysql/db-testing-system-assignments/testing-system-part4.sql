USE db_testing_system;

-- QUESTION 1 
SELECT account_id, email, username, fullname, a.department_id, department_name
FROM `account` a
LEFT JOIN `department` d
ON a.department_id = d.department_id;

-- QUESTION 2
SELECT * 
FROM `account`
WHERE create_date > "2010-12-20 00:00:00"; 

-- QUESTION 3 
SELECT * 
FROM `account` a
LEFT JOIN `position` p
ON a.position_id=p.position_id
WHERE p.position_name = "developer";

-- QUESTION 4
SELECT department_name, COUNT(*) as no_of_members
FROM `account` A
RIGHT JOIN `department` D
ON A.department_id = D.department_id
GROUP BY D.department_id
HAVING no_of_members >= 3;

-- QUESTION 5
WITH tbl
AS (
	SELECT q.content AS question, COUNT(*) AS times_used
	FROM question q
	RIGHT JOIN exam_question eq 
	ON q.question_id = eq.question_id
	GROUP BY eq.question_id
)
SELECT question AS most_used_question
FROM tbl 
WHERE times_used = (
	SELECT MAX(times_used) FROM tbl
);

-- QUESTION 6
SELECT C.category_name, COUNT(*) as no_of_questions
FROM `category_question` C
LEFT JOIN `question` Q
ON C.category_id = Q.category_id
GROUP BY C.category_name;

-- QUESTION 7
SELECT q.content AS question, COUNT(*) AS times_used_in_exams
FROM question q
LEFT JOIN exam_question eq
ON q.question_id = eq.question_id
GROUP BY q.question_id, eq.question_id;
    
-- QUESTION 8
WITH count_table
AS (
	SELECT Q.content, COUNT(*) AS answers_count
	FROM `question` Q
	LEFT JOIN `answer` A
	ON Q.question_id = A.question_id
	GROUP BY Q.question_id
)
SELECT content AS questions, answers_count
FROM count_table
WHERE answers_count = (
	SELECT MAX(answers_count) FROM count_table
);

-- QUESTION 9
SELECT g.group_name, COUNT(*) as no_of_accounts
FROM `group` g
LEFT JOIN group_account ga
ON g.group_id = ga.group_id
GROUP BY group_id;

-- QUESTION 10
WITH count_table
AS (
	SELECT p.position_name, COUNT(*) AS no_of_people
    FROM `position` p
    LEFT JOIN `account` a
    ON p.position_id = a.position_id
    GROUP BY p.position_id
)
SELECT position_name, no_of_people
FROM count_table
WHERE no_of_people = (
	SELECT MIN(no_of_people) FROM count_table
);

-- QUESTION 11 
WITH tbl
AS (
	SELECT d.department_name, p.position_name
    FROM department d
    LEFT JOIN `account` a
    ON d.department_id = a.department_id
    LEFT JOIN position p
    ON a.position_id = p.position_id
    WHERE p.position_name IN ("pm", "dev", "pm")
)
SELECT DISTINCT t.department_name, dc.no_of_devs, pmc.no_of_pm, sc.no_of_scrum
FROM tbl t
LEFT JOIN (
	SELECT department_name, COUNT(*) AS no_of_devs
    FROM tbl
    GROUP BY department_name, position_name
    HAVING position_name = "dev"
) AS dc
ON t.department_name = dc.department_name
LEFT JOIN (
	SELECT department_name, COUNT(*) AS no_of_pm
    FROM tbl
    GROUP BY department_name, position_name
    HAVING position_name = "pm"
) AS pmc
ON t.department_name = pmc.department_name
LEFT JOIN (
	SELECT department_name, COUNT(*) AS no_of_scrum_masters
    FROM tbl
    GROUP BY department_name, position_name
    HAVING position_name = "scrum master"
) AS smc
ON t.department_name = smc.department_name;

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
SELECT t.type_name, COUNT(*) AS no_of_questions
FROM question q
LEFT JOIN type_question t
ON q.type_id = t.type_id
GROUP BY t.type_name;

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
WHERE answer_id IS NULL;

-- QUESTION 17
SELECT a.email, a.username, a.fullname
FROM `account` a
LEFT JOIN group_account ga
ON a.account_id = ga.account_id
WHERE ga.group_id = 1

UNION 

SELECT a.email, a.username, a.fullname
FROM `account` a
LEFT JOIN group_account ga
ON a.account_id = ga.account_id 
WHERE ga.group_id = 2;

-- QUESTION 18
SELECT g.group_name, COUNT(*) AS members
FROM `group` g
LEFT JOIN group_account ga
ON g.group_id = ga.group_id
GROUP BY g.group_id
HAVING members > 5

INTERSECT

SELECT g.group_name, COUNT(*) AS members
FROM `group` g
LEFT JOIN group_account ga
ON g.group_id = ga.group_id
GROUP BY g.group_id
HAVING members < 7;