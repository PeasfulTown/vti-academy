USE db_testing_system;

-- QUESTION 1
DROP PROCEDURE IF EXISTS sp_find_dep_empl;
DELIMITER $$
CREATE PROCEDURE sp_find_dep_empl(IN in_department_name VARCHAR(25))
	BEGIN
		SELECT a.account_id, a.username, a.email, a.fullname, a.create_date
        FROM `account` a
        INNER JOIN department d
        ON a.department_id = d.department_id 
        WHERE d.department_name = in_department_name;
    END $$
DELIMITER ;

CALL sp_find_dep_empl('marketing');

-- QUESTION 2
DROP PROCEDURE IF EXISTS sp_group_usr_cnt

DELIMITER $$
CREATE PROCEDURE sp_group_usr_cnt()
	BEGIN
		SELECT ga.group_id, g.group_name, COUNT(ga.account_id) AS user_count
		FROM `group` g
		LEFT JOIN group_account ga
		ON g.group_id = ga.group_id
		GROUP BY ga.group_id, g.group_id;
	END $$
DELIMITER ;

CALL sp_group_usr_cnt();

-- QUESTION 3
	-- CURRENT MONTH
DROP PROCEDURE IF EXISTS sp_month_type_question_count;

DELIMITER $$
CREATE PROCEDURE sp_month_type_question_count()
	BEGIN
		WITH cte_cur_month_tbl
        AS (
			SELECT tq.type_name
			FROM type_question tq
			LEFT JOIN question q 
			ON tq.type_id = q.type_id
        )
		SELECT tq.type_name, COUNT(q.question_id) AS question_count
		FROM type_question tq
		LEFT JOIN question q 
		ON tq.type_id = q.type_id
		WHERE 
			q.question_id IS NULL OR -- show records with no questions
            MONTH(q.create_date) = MONTH(CURRENT_DATE())
		GROUP BY tq.type_id;
	END $$
DELIMITER ;

CALL sp_month_type_question_count();

-- QUESTION 4
DROP PROCEDURE IF EXISTS sp_type_w_most_question;

WITH cte_question_cnt
AS (
	SELECT tq.type_id, tq.type_name, COUNT(q.question_id) AS question_cnt
	FROM type_question tq
	INNER JOIN question q
	ON tq.type_id = q.type_id
	GROUP BY tq.type_id
),
cte_max_question_cnt
AS (
	SELECT MAX(question_cnt) AS highest_count FROM cte_question_cnt
)
SELECT type_id, question_cnt
FROM cte_question_cnt
WHERE question_cnt = (
	SELECT highest_count FROM cte_max_question_cnt
);

DELIMITER $$
CREATE PROCEDURE sp_type_w_most_question(OUT out_type_question_id INT)
	BEGIN
		WITH cte_question_cnt
		AS (
			SELECT tq.type_id, tq.type_name, COUNT(q.question_id) AS question_cnt
			FROM type_question tq
			INNER JOIN question q
			ON tq.type_id = q.type_id
			GROUP BY tq.type_id
		),
		cte_max_question_cnt
		AS (
			SELECT MAX(question_cnt) AS highest_count FROM cte_question_cnt
		)
		SELECT type_id INTO out_type_question_id
		FROM cte_question_cnt
		WHERE question_cnt = (
			SELECT highest_count FROM cte_max_question_cnt
		);
    END $$
DELIMITER ;

SET @v_id_type_question = 0;
CALL sp_type_w_most_question(@v_id_type_question);
SELECT @v_id_type_question;

-- QUESTION 5
SELECT type_name 
FROM type_question 
WHERE type_id = @v_id_type_question;

-- QUESTION 6
DROP PROCEDURE IF EXISTS sp_get_group_by_name;
DELIMITER $$
CREATE PROCEDURE sp_get_group_by_name(IN in_group_name VARCHAR(20))
	BEGIN
		SELECT g.group_id, g.group_name, a.username AS creator, g.create_date 
        FROM `group` g
        INNER JOIN `account` a
        ON g.creator_id = a.account_id
        WHERE g.group_name LIKE CONCAT('%', in_group_name, '%');
    END $$
DELIMITER ;

DROP PROCEDURE IF EXISTS sp_get_account_by_username;
DELIMITER $$
CREATE PROCEDURE sp_get_account_by_username(IN in_str VARCHAR(20))
	BEGIN
		SELECT a.account_id
			, a.username
            , a.fullname
            , a.email
            , d.department_name AS department
            , p.position_name AS position
            , a.create_date
        FROM `account` a
        INNER JOIN department d
        ON a.department_id = d.department_id
        INNER JOIN `position` p
        ON a.position_id = p.position_id
        WHERE a.username LIKE CONCAT('%', in_str, '%')
        LIMIT 1;
    END $$
DELIMITER ;

DROP PROCEDURE IF EXISTS sp_find_group_or_username_by_str;
DELIMITER $$
CREATE PROCEDURE sp_find_group_or_username_by_str(IN in_str VARCHAR(30))
	BEGIN
		IF (
			SELECT group_id 
            FROM `group`
            WHERE group_name LIKE CONCAT('%', in_str, '%')
		) IS NOT NULL
		THEN CALL sp_get_group_by_name(in_str);
		ELSE 
			CALL sp_get_account_by_username(in_str);
		END IF;
    END $$
DELIMITER ;

CALL sp_find_group_or_username_by_str('sheet');

-- QUESTION 7 
DROP FUNCTION IF EXISTS f_get_username_from_email;
SET GLOBAL log_bin_trust_function_creators = 1;
DELIMITER $$
CREATE FUNCTION f_get_username_from_email(p_email VARCHAR(50)) RETURNS VARCHAR(20)
	BEGIN 
		DECLARE v_username VARCHAR(20);
        DECLARE v_email VARCHAR(50);
        SET v_email = TRIM(p_email);
        
        
        SELECT SUBSTR(v_email, 1, REGEXP_INSTR(v_email, '@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$') - 1) INTO v_username;
        
        RETURN v_username;
	END $$
DELIMITER ;
SET GLOBAL log_bin_trust_function_creators = 0;

SELECT f_get_username_from_email('hanhn@gmail.com');

DROP PROCEDURE IF EXISTS sp_insert_account_from_input;
DELIMITER $$
CREATE PROCEDURE sp_insert_account_from_input(IN in_fullname VARCHAR(50), IN in_email VARCHAR(50))
	BEGIN
		DECLARE v_username VARCHAR(10);
        DECLARE v_dep_id TINYINT;
        DECLARE v_pos_id TINYINT;
        
        SET v_username = f_get_username_from_email(in_email);
        SET v_dep_id = (
			SELECT department_id 
            FROM department
            WHERE department_name = 'waiting room'
		);
        SET v_pos_id = (
			SELECT position_id
			FROM `position`
            WHERE position_name = 'dev'
		);
        
		INSERT INTO `account` (email, username, fullname, department_id, position_id)
        VALUE (in_email, v_username, in_fullname, v_dep_id, v_pos_id);
        
        SELECT * 
        FROM `account` 
        WHERE account_id = last_insert_id();

    END $$
DELIMITER ;

CALL sp_insert_account_from_input('Simon Jarret', 'simonj@gmail.com');

-- QUESTION 8
DROP PROCEDURE IF EXISTS sp_get_longest_question;
DELIMITER $$
CREATE PROCEDURE sp_get_longest_question(IN in_type_question ENUM('essay', 'multiple choice'))
	BEGIN
		WITH cte_biggest_length_count
		AS (
			SELECT MAX(LENGTH(q.content)) AS biggest_count
			FROM question q
			RIGHT JOIN type_question tq
			ON q.type_id = tq.type_id
			WHERE tq.type_name = in_type_question
		)
		SELECT *, LENGTH(content) AS char_count
		FROM question 
		WHERE LENGTH(content) = (
			SELECT biggest_count FROM cte_biggest_length_count
		);
    END $$
DELIMITER ;

CALL sp_get_longest_question('multiple choice');
CALL sp_get_longest_question('essay');

-- QUESTION 9
DROP PROCEDURE IF EXISTS sp_delete_exam_by_id;
DELIMITER $$
CREATE PROCEDURE sp_delete_exam_by_id(IN in_exam_id SMALLINT)
	BEGIN
		DELETE FROM exam WHERE exam_id = in_exam_id;
    END $$
DELIMITER ;

CALL sp_delete_exam_by_id(3);

-- QUESTION 10
-- TODO: finish 
DROP PROCEDURE IF EXISTS sp_remove_old_tests;

-- QUESTION 11
DROP TRIGGER IF EXISTS trigger_update_account_department_on_department_delete;
DELIMITER $$
CREATE TRIGGER trigger_update_account_department_on_department_delete
	BEFORE DELETE ON department
    FOR EACH ROW
    BEGIN 
		DECLARE v_new_dep_id TINYINT;
        SET v_new_dep_id = (
			SELECT department_id 
            FROM department
            WHERE department_name = 'waiting room'
		);
        
        UPDATE `account`
        SET department_id = v_new_dep_id
        WHERE department_id = OLD.department_id;
        
    END $$
DELIMITER ;

DROP PROCEDURE IF EXISTS sp_remove_department_by_name;
DELIMITER $$
CREATE PROCEDURE sp_remove_department_by_name(IN in_department_name VARCHAR(20))
	BEGIN
			DECLARE v_department_id TINYINT;
            SET v_department_id = (
				SELECT department_id
                FROM department
                WHERE department_name = in_department_name
			);
            
            DELETE FROM department
            WHERE department_id = v_department_id;
    END $$
DELIMITER ;

CALL sp_remove_department_by_name('marketing');

-- QUESTION 12
DROP PROCEDURE IF EXISTS sp_print_questions_created_current_year;
DELIMITER $$
CREATE PROCEDURE sp_print_questions_created_current_year()
	BEGIN
		SELECT MONTH(q.create_date) AS month_created, COUNT(q.question_id) AS question_count
		FROM question q
		WHERE YEAR(q.create_date) = YEAR(CURDATE())
		GROUP BY month_created
		ORDER BY month_created;	
    END $$
DELIMITER ;

CALL sp_print_questions_created_current_year;

SELECT YEAR(CURDATE());

-- QUESTION 13
DROP PROCEDURE IF EXISTS sp_print_questions_created_current_year_last_six_months;

DELIMITER $$
CREATE PROCEDURE sp_print_questions_created_current_year_last_six_months()
	BEGIN
		WITH cte_questions_last_six_months
		AS (
			SELECT MONTH(q.create_date) AS month_created, q.question_id, q.content, q.create_date
			FROM question q
			WHERE TIMESTAMPDIFF(MONTH, q.create_date, CURDATE()) <= 6
		)
		SELECT m.month_created AS month_created
				, IF(COUNT(content) = 0, 'No questions this month', COUNT(content)) AS question_count
		FROM (
			SELECT 1 AS month_created UNION ALL
			SELECT 2 AS month_created UNION ALL
			SELECT 3 AS month_created UNION ALL
			SELECT 4 AS month_created UNION ALL
			SELECT 5 AS month_created UNION ALL
			SELECT 6 AS month_created UNION ALL
			SELECT 7 AS month_created UNION ALL
			SELECT 8 AS month_created UNION ALL
			SELECT 9 AS month_created UNION ALL
			SELECT 10 AS month_created UNION ALL
			SELECT 11 AS month_created UNION ALL
			SELECT 12
		) as m
		LEFT JOIN cte_questions_last_six_months q
		ON m.month_created = q.month_created
		GROUP BY month_created;
	END $$
DELIMITER ;

CALL sp_print_questions_created_current_year_last_six_months;