USE db_testing_system;

-- QUESTION 1 
SELECT account_id, email, username, fullname, a.department_id, department_name
FROM `account` a
LEFT JOIN `department` d
ON a.department_id = d.department_id;

-- QUESTION 2
-- TODO: edit some creation dates since all current creation dates are NOW()
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
	-- TODO: MOST COMMONLY USED EXAM QUESTIONS (MAX COUNT)
    
-- QUESTION 6
SELECT C.category_name, COUNT(*) as no_of_questions
FROM `category_question` C
LEFT JOIN `question` Q
ON C.category_id = Q.category_id
GROUP BY C.category_name;

-- QUESTION 7
	-- TODO: HOW MANY TIMES EACH QUESTION SHOWS UP ON EXAMS (COUNT EXAMS)
    
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
-- department, account, position
-- OUT: number of dev, number of test, scrum master, pm for every department
SELECT d.department_id, d.department_name, a.account_id, p.position_id, p.position_name
FROM department d
LEFT JOIN `account` a
ON d.department_id = a.department_id
RIGHT JOIN `position` p
ON a.position_id = p.position_id
WHERE p.position_name IN ("accountant", "developer", "teacher");