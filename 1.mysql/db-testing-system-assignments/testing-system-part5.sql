USE db_testing_system;

-- QUESTION 1
DROP VIEW IF EXISTS vw_sale_emp;

CREATE VIEW vw_sale_emp AS 
	SELECT username, email, fullname 
    FROM `account`
    WHERE department_id = (
		SELECT department_id FROM department WHERE department_name = 'sales'
	);
    
SELECT * FROM vw_sale_emp;

-- QUESTION 2
DROP VIEW IF EXISTS vw_account_most_group;

CREATE VIEW vw_account_most_group AS
	WITH cte_group_count
    AS (
			SELECT account_id, COUNT(group_id) as group_count
            FROM group_account 
            GROUP BY account_id
	),
    cte_account 
    AS (
			SELECT account_id
            FROM cte_group_count
            WHERE group_count = ( 
				SELECT MAX(group_count) FROM cte_group_count
			)
	)
    SELECT a.username, a.email, a.fullname, a.create_date
    FROM `account` a
    INNER JOIN cte_account c
    ON a.account_id = c.account_id;
    
SELECT * FROM vw_account_most_group;

-- QUESTION 3
DROP VIEW IF EXISTS vw_long_question;

CREATE OR REPLACE VIEW vw_long_question AS
	WITH cte_word_cnt
    AS (
			SELECT question_id, SUM(LENGTH(content) - LENGTH(REPLACE(content, ' ', '')) + 1) AS word_count
            FROM question
            GROUP BY question_id
	)
    SELECT q.question_id, q.content, wc.word_count
    FROM question q
    INNER JOIN cte_word_cnt wc
    ON q.question_id =  wc.question_id
    HAVING wc.word_count > 300;
    
SELECT * FROM vw_long_question;

-- SAFE DELETE ISSUE
DELETE FROM `question` 
WHERE question_id IN (
	SELECT temp.question_id FROM (
		SELECT question_id FROM vw_long_question WHERE question_id IS NOT NULL
	) as temp
);
	
-- QUESTION 4
