USE db_testing_system;

-- QUESTION 1
DROP FUNCTION IF EXISTS f_check_if_older_than_1_year;
DELIMITER $$
CREATE FUNCTION f_check_if_older_than_1_year (in_date DATETIME) RETURNS BIT 
	BEGIN
		RETURN in_date < DATE_SUB(NOW(), INTERVAL 1 YEAR);
    END $$
DELIMITER ;

DROP TRIGGER IF EXISTS trg_before_group_insert_create_date_not_more_than_1_year_old;
DELIMITER $$
CREATE TRIGGER trg_before_group_insert_create_date_not_more_than_1_year_old 
	BEFORE INSERT ON `group`
    FOR EACH ROW
    BEGIN
		IF (f_check_if_older_than_1_year(NEW.create_date)) THEN 
			SIGNAL SQLSTATE '45001'
            SET MESSAGE_TEXT = 'New group must not have create_date older than 1 year';
		END IF;
	END $$
DELIMITER ;

-- QUESTION 2
DROP TRIGGER IF EXISTS trg_before_account_insert_dont_allow_more_sales_dep_account;
DELIMITER $$
CREATE TRIGGER trg_before_account_insert_dont_allow_more_sales_dep_account
	BEFORE INSERT ON `account`
    FOR EACH ROW
    BEGIN
		DECLARE v_sales_dep_id SMALLINT;
        
        SET v_sales_dep_id = (
			SELECT department_id
			FROM department
			WHERE department_name = 'sales'
		);
		
        IF (NEW.department_id = v_sales_dep_id) THEN
			SIGNAL SQLSTATE '45002'
			SET MESSAGE_TEXT = 'Department \"Sales\" cannot add more user';
		END IF;
    END $$
DELIMITER ;

-- QUESTION 3
DROP TRIGGER IF EXISTS trg_before_groupaccount_insert_5_users_max;
DELIMITER $$
CREATE TRIGGER trg_before_groupaccount_insert_5_users_max 
	BEFORE INSERT ON group_account
    FOR EACH ROW
    BEGIN 
		DECLARE v_group_has_5_or_more_members BIT;
        
        SET v_group_has_5_or_more_members = (
			SELECT COUNT(account_id) >= 5 
			FROM group_account 
			WHERE group_id = NEW.group_id
		);
        
        IF (v_group_has_5_or_more_members) THEN 
			SIGNAL SQLSTATE '45003'
            SET MESSAGE_TEXT = 'Unable to insert account to group, groups must have no more than 5 members';
		END IF;
    END $$
DELIMITER ;

-- QUESTION 4
DROP TRIGGER IF EXISTS trg_before_examquestion_insert_10_questions_max;
DELIMITER $$
CREATE TRIGGER trg_before_examquestion_insert_10_questions_max
	BEFORE INSERT ON exam_question
    FOR EACH ROW
    BEGIN
		DECLARE v_exam_has_10_or_more_questions BIT;
        
        SET v_exam_has_10_or_more_questions = (
			SELECT COUNT(question_id) >= 10
			FROM exam_question
			WHERE exam_id = NEW.exam_id
		);
        
        IF (v_exam_has_10_or_more_questions) THEN
			SIGNAL SQLSTATE '45004'
            SET MESSAGE_TEXT = 'Exam already has 10 or more questions, cannot add more questions to exam';
		END IF;
    END $$
DELIMITER ;

-- QUESTION 5
DROP TRIGGER IF EXISTS trg_before_account_delete_dont_delete_admin;
DELIMITER $$
CREATE TRIGGER trg_before_account_delete_dont_delete_admin
	BEFORE DELETE ON `account`
    FOR EACH ROW
    BEGIN
        IF (OLD.email = 'admin@gmail.com') THEN 
			SIGNAL SQLSTATE '45005'
            SET MESSAGE_TEXT = 'Deleting admin account not permitted';
		END IF;
    END $$
DELIMITER ;

DROP TRIGGER IF EXISTS trg_before_account_update_dont_delete_admin;
DELIMITER $$
CREATE TRIGGER trg_before_account_update_dont_delete_admin
	BEFORE UPDATE ON `account`
    FOR EACH ROW
    BEGIN 
		IF (OLD.email = 'admin@gmail.com') THEN 
			SIGNAL SQLSTATE '45005'
            SET MESSAGE_TEXT = 'Deleting admin account not permitted';
		END IF;
    END $$
DELIMITER ;

-- QUESTION 6
DROP TRIGGER IF EXISTS trg_before_account_insert_auto_set_department_if_null;
DELIMITER $$
CREATE TRIGGER trg_before_account_insert_auto_set_department_if_null
	BEFORE INSERT ON `account`
    FOR EACH ROW
    BEGIN 
		DECLARE v_waiting_department_id SMALLINT;
        SET v_waiting_department_id = (
			SELECT department_id 
            FROM department
            WHERE department_name = 'waiting room'
		);
        
		IF (NEW.department_id IS NULL) THEN
			SET NEW.department_id = v_waiting_department_id;
		END IF;
    END $$
DELIMITER ;

-- QUESTION 7
DROP TRIGGER IF EXISTS trg_before_questionanswer_insert_max_answers;
DELIMITER $$
CREATE TRIGGER trg_before_questionanswer_insert_max_answers
	BEFORE INSERT ON answer
    FOR EACH ROW
    BEGIN
        IF (
			SELECT COUNT(answer_id) >= 4
            FROM answer
            WHERE question_id = NEW.question_id
		) THEN
			SIGNAL SQLSTATE '45007'
			SET MESSAGE_TEXT = 'Question already has maximum allowed number of answers (4)';
		ELSEIF (
			SELECT COUNT(IF(is_correct = 'true', 1, NULL)) >= 2
			FROM answer
			WHERE question_id = NEW.question_id
			AND
			NEW.is_correct = 'true'
		) THEN
			SIGNAL SQLSTATE '45007'
            SET MESSAGE_TEXT = 'Question already has maximum allowed number of correct answers (2)';
        END IF;
    END $$
DELIMITER ;

-- QUESTION 8
DROP TRIGGER IF EXISTS trg_before_account_insert_check_gender;
DELIMITER $$
CREATE TRIGGER trg_before_account_insert_check_gender
	BEFORE INSERT ON `account`
    FOR EACH ROW
    BEGIN
		CASE LOWER(NEW.gender)
			WHEN 'male' THEN 
				SET NEW.gender = 'M';
			WHEN 'female' THEN
				SET NEW.gender = 'F';
			WHEN 'unknown' THEN
				SET NEW.gender = 'U';
		END CASE;
    END $$
DELIMITER ;

DROP TRIGGER IF EXISTS trg_before_account_update_check_gender;
DELIMITER $$
CREATE TRIGGER trg_before_account_update_check_gender
	BEFORE UPDATE ON `account`
    FOR EACH ROW
    BEGIN
		CASE LOWER(NEW.gender)
			WHEN 'male' THEN 
				SET NEW.gender = 'M';
			WHEN 'female' THEN
				SET NEW.gender = 'F';
			WHEN 'unknown' THEN
				SET NEW.gender = 'U';
		END CASE;
    END $$
DELIMITER ;

-- QUESTION 9 
DROP TRIGGER IF EXISTS trg_before_question_delete_2_days_old;
DELIMITER $$
CREATE TRIGGER trg_before_question_delete_2_days_old
	BEFORE DELETE ON question
    FOR EACH ROW
    BEGIN
		IF (OLD.create_date >= DATE_SUB(CURDATE(), INTERVAL 2 DAY)) THEN
			SIGNAL SQLSTATE '45009'
            SET MESSAGE_TEXT = 'Not allowed to delete questions less than 2 days old';
		END IF;
    END $$
DELIMITER ;

-- QUESTION 10 
SELECT exam_id 
FROM exam_question
WHERE question_id = 4;

SELECT COUNT(*) > 0
FROM exam_question
WHERE question_id = 1;

DROP TRIGGER IF EXISTS trg_before_question_delete_not_in_exam;
DELIMITER $$
CREATE TRIGGER trg_before_question_delete_not_in_exam
	BEFORE DELETE ON question
    FOR EACH ROW
    BEGIN
		IF (
			SELECT COUNT(*) > 0
            FROM exam_question 
            WHERE question_id = OLD.question_id
		) THEN
			SIGNAL SQLSTATE '45010'
            SET MESSAGE_TEXT = 'Not allowed to delete or update questions that are being used on exams';
		END IF;
    END $$
DELIMITER ;

DROP TRIGGER IF EXISTS trg_before_question_update_not_in_exam;
DELIMITER $$
CREATE TRIGGER trg_before_question_update_not_in_exam
	BEFORE UPDATE ON question
    FOR EACH ROW
    BEGIN
		IF (
			SELECT COUNT(*) > 0
            FROM exam_question 
            WHERE question_id = OLD.question_id
		) THEN
			SIGNAL SQLSTATE '45010'
            SET MESSAGE_TEXT = 'Not allowed to delete or update questions that are being used in exams';
		END IF;
    END $$
DELIMITER ;

-- QUESTION 12
DESCRIBE exam;
SELECT 
	exam_id, 
    `code`,
    title,
    CASE
		WHEN duration <= 30 THEN 'Short time'
        WHEN 30 < duration AND duration <= 60 THEN 'Medium time'
        WHEN duration > 60 THEN 'Long time'
        ELSE 'Unknown'
	END as duration
FROM exam;

-- QUESTION 13 
SELECT 
	g.group_id
    , g.group_name
    , CASE
		WHEN COUNT(ga.account_id) <= 5 THEN 'few'
        WHEN 5 < COUNT(ga.account_id) AND COUNT(ga.account_id) <= 20 THEN 'normal'
        ELSE 'higher'
	  END AS the_number_user_amount
FROM `group` g
LEFT JOIN group_account ga
ON g.group_id = ga.group_id
GROUP BY group_id;

-- QUESTION 14
SELECT d.department_name
    , IF(COUNT(a.account_id) > 0, COUNT(a.account_id), 'Khong co user') AS user_count
FROM department d
LEFT JOIN `account` a
ON d.department_id = a.department_id
GROUP BY d.department_id;
