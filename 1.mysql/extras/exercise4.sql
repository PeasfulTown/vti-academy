CREATE DATABASE IF NOT EXISTS db_facebook;
USE db_facebook;

-- QUESTION 1
DROP TABLE IF EXISTS staff;
DROP TABLE IF EXISTS office;
DROP TABLE IF EXISTS `national`;

CREATE TABLE `national` (
	national_id		SMALLINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    national_name	VARCHAR(25) UNIQUE NOT NULL,
    language_main	VARCHAR(25) NOT NULL
);

CREATE TABLE office (
	office_id		SMALLINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    street_address	VARCHAR(30) NOT NULL,
    national_id		SMALLINT UNSIGNED,
    
    CONSTRAINT fk_office_national_id
		FOREIGN KEY (national_id)
        REFERENCES `national` (national_id)
);

CREATE TABLE staff (
	staff_id		SMALLINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    first_name		VARCHAR(15) NOT NULL,
    last_name		VARCHAR(20) NOT NULL,
    email			VARCHAR(40) NOT NULL,
    office_id		SMALLINT UNSIGNED NOT NULL,
    
    CONSTRAINT fk_staff_office_id
		FOREIGN KEY (office_id)
        REFERENCES office (office_id)
);

-- QUESTION 2
INSERT INTO `national` 	(national_name		, language_main			)
VALUES					('Vietnam'			, 'Vietnamese'			),
						('Australia'		, 'English'				),
                        ('Canada'			, 'English'				),
                        ('Philipines'		, 'Tagalog'				),
                        ('Russia'			, 'Russian'				),
                        ('Thailand'			, 'Thai'				),
                        ('Nepal'			, 'Nepali'				),
                        ('Mexico'			, 'Spanish'				),
                        ('Malaysia'			, 'Malay'				),
                        ('Laos'				, 'Lao'					),
                        ('England'			, 'English'				);
                        
INSERT INTO office 		(street_address					, national_id	)
VALUES					('088 Sachtjen Crossing'		, 5				),
						('38530 Sommers Street'			, 5				),
						('45045 Norway Maple Way'		, 4				),
						('82939 Grasskamp Center'		, 2				),
						('2729 Cottonwood Way'			, 8				),
						('31 Gerald Trail'				, 10			),
						('4077 Cottonwood Road'			, 8				),
						('629 Forest Lane'				, 2				),
						('29786 Butterfield Crossing'	, 1				),
						('300 Burning Wood Center'		, 7				);
                        
INSERT INTO staff 		(first_name		, last_name		, email							, office_id	)
VALUES					('Rodolphe'		, 'Nelius'		, 'rnelius0@walmart.com'		, 4			),
						('Catina'		, 'McEntegart'	, 'cmcentegart1@state.tx.us'	, 7			),
						('Lucky'		, 'Kiezler'		, 'lkiezler2@statcounter.com'	, 1			),
						('Hamlen'		, 'Kettlesing'	, 'hkettlesing3@amazon.com'		, 9			),
						('Nesta'		, 'Haucke'		, 'nhaucke4@uol.com.br'			, 9			),
						('Victor'		, 'Andren'		, 'vandren5@prlog.org'			, 7			),
						('Melonie'		, 'Tongue'		, 'mtongue6@webmd.com'			, 9			),
						('Nesta'		, 'Bellon'		, 'nbellon7@usgs.gov'			, 6			),
						('Virgie'		, 'Babon'		, 'vbabon8@vistaprint.com'		, 2			),
						('Tatum'		, 'Jeste'		, 'tjeste9@wufoo.com'			, 5			),
                        ('Dao'			, 'Nguyen'		, 'daonq@viettel.com.vn'		, 1			);

-- QUESTION 3
SELECT s.staff_id, s.first_name, s.last_name, s.email
FROM staff s
INNER JOIN office o
ON s.office_id = o.office_id
INNER JOIN `national` n
ON o.national_id = n.national_id
WHERE n.national_name = 'Vietnam';

-- QUESTION 4
SELECT s.staff_id, CONCAT(s.first_name, s.last_name) AS fullname, s.email, n.national_name
FROM staff s
LEFT JOIN office o
ON s.office_id = o.office_id
LEFT JOIN `national` n
ON o.national_id = n.national_id;

-- QUESTION 5
SELECT n.national_name
FROM `national` n
INNER JOIN office o
ON n.national_id = o.national_id
INNER JOIN staff s
ON o.office_id = s.office_id
WHERE s.email = 'daonq@viettel.com.vn';

-- QUESTION 6 
SELECT n.national_id, n.national_name 
FROM `national` n
LEFT JOIN office o
ON n.national_id = o.national_id
LEFT JOIN staff s
ON o.office_id = s.office_id 
WHERE s.staff_id IS NULL;

WITH cte_temp AS (
	SELECT DISTINCT o.national_id
    FROM office o 
    INNER JOIN (
		SELECT DISTINCT o.office_id
        FROM office o
        INNER JOIN staff s
        ON o.office_id = s.office_id
	) AS t
    ON o.office_id = t.office_id
)
SELECT n.national_name 
FROM `national` n
LEFT JOIN cte_temp o
ON o.national_id = n.national_id
WHERE o.national_id IS NULL;


-- QUESTION 7 
SELECT COUNT(*) 
FROM `national`
WHERE language_main = 'English';

-- QUESTION 8
SELECT * 
FROM staff 
WHERE first_name LIKE ('N________C');

-- QUESTION 9
SELECT * 
FROM staff 
WHERE office_id IS NULL;

-- QUESTION 10
DELETE FROM staff
WHERE staff_id = 9;

-- QUESTION 11
SET SQL_SAFE_UPDATES = 1;
DROP PROCEDURE IF EXISTS sp_delete_national_offices_by_name;
DELIMITER $$
CREATE PROCEDURE sp_delete_national_offices_by_name(IN in_national_name VARCHAR(30))
	BEGIN 
		DECLARE v_national_id SMALLINT;
        SET v_national_id = (
			SELECT national_id 
            FROM `national`
            WHERE national_name = in_national_name
		);
        
        DELETE FROM staff
        WHERE office_id IN (
			SELECT office_id FROM (
				SELECT office_id 
                FROM office WHERE national_id = v_national_id
			) AS temp
		);
        
        DELETE FROM office
        WHERE national_id = v_national_id;
        
        DELETE FROM `national`
        WHERE national_id = v_national_id;
    END $$
DELIMITER ;

CALL sp_delete_national_offices_by_name('Russia');

-- QUESTION 12
DROP FUNCTION IF EXISTS f_total_staff;
DELIMITER $$
CREATE FUNCTION f_total_staff() RETURNS INT
	BEGIN
		DECLARE v_total_staff INT;
        SET v_total_staff = (
			SELECT COUNT(*) FROM staff
		);
		RETURN v_total_staff;
    END $$
DELIMITER ;

SELECT f_total_staff();

-- QUESTION 13
DROP TRIGGER IF EXISTS trg_before_staff_insert;
DELIMITER $$
CREATE TRIGGER trg_before_staff_insert
	BEFORE INSERT ON staff
    FOR EACH ROW
    BEGIN
		IF (SELECT COUNT(*) >= 10000 FROM staff) THEN
			SIGNAL SQLSTATE '45002'
            SET MESSAGE_TEXT = 'Cannot insert, exceeding maximum allowed number of staff (10,000)';
		END IF;
    END $$
DELIMITER ;

-- QUESTION 14
WITH cte_staff_count AS (
	SELECT o.office_id, COUNT(*) AS count
    FROM office o 
    INNER JOIN staff s
    ON o.office_id = s.office_id
    GROUP BY o.office_id
),
cte_max_count AS (
	SELECT MAX(count) max_count
    FROM cte_staff_count
)
SELECT o.office_id, o.street_address, o.national_id, t.count
FROM office o
INNER JOIN cte_staff_count t
ON o.office_id = t.office_id
WHERE t.count = (
	SELECT max_count FROM cte_max_count
);

-- QUESTION 15
DROP FUNCTION IF EXISTS f_get_staff_by_email;
DELIMITER $$
CREATE FUNCTION f_get_staff_by_email(v_in_staff_email VARCHAR(40)) RETURNS VARCHAR(120)
	BEGIN
		DECLARE v_staff_info VARCHAR(120);
        
		SELECT CONCAT(s.staff_id
			, ' '
            , s.first_name
            , ' '
            , s.last_name
            , ' '
            , s.email
            , ' '
            , o.street_address
			, ' '
            , n.national_name
            , ' '
            , n.language_main
		) INTO v_staff_info
        FROM staff s
        INNER JOIN office o
        ON s.office_id = o.office_id
        INNER JOIN `national` n
        ON o.national_id = n.national_id
        WHERE email = v_in_staff_email;
        
        RETURN v_staff_info;
    END $$
DELIMITER ;

SELECT f_get_staff_by_email('nhaucke4@uol.com.br');

-- QUESTION 16
CREATE TABLE log_office (
	id						SMALLINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    staff_id				SMALLINT UNSIGNED NOT NULL,
    old_office_address		VARCHAR(30) NOT NULL,
    `timestamp`				DATETIME NOT NULL DEFAULT NOW()
);

DESCRIBE office;
DROP TRIGGER IF EXISTS trg_before_staff_update;
DELIMITER $$
CREATE TRIGGER trg_before_staff_update
	BEFORE UPDATE ON staff
    FOR EACH ROW
    BEGIN
		DECLARE v_old_street_address VARCHAR(30);
        
        SET v_old_street_address = (
			SELECT o.street_address
            FROM office o 
            WHERE o.office_id = OLD.office_id
		);
        
		IF (OLD.office_id <> NEW.office_id) THEN
			INSERT INTO log_office 	(staff_id		, old_office_address	)
            VALUE 					(OLD.staff_id	, v_old_street_address	);
		END IF;
    END $$
DELIMITER ;

-- QUESTION 17
DROP TRIGGER IF EXISTS trg_before_national_insert;
DELIMITER $$
CREATE TRIGGER trg_before_national_insert
	BEFORE INSERT ON `national`
    FOR EACH ROW 
    BEGIN
		IF (SELECT COUNT(*) >= 100 FROM `national`) THEN
			SIGNAL SQLSTATE '45003'
            SET MESSAGE_TEXT = 'Failed to insert national record, maximum 100 national records allowed.';
		END IF;
    END $$
DELIMITER ;

-- QUESTION 18 
WITH cte_empl_count AS (
	SELECT o.national_id, COUNT(s.staff_id) AS empl_count
    FROM office o
    INNER JOIN staff s
    ON o.office_id = s.staff_id
    GROUP BY o.national_id
)
SELECT n.national_name, c.empl_count
FROM `national` n
LEFT JOIN cte_empl_count c
ON n.national_id = c.national_id;

-- QUESTION 19 
DROP PROCEDURE IF EXISTS sp_number_of_staff_by_nation;
DELIMITER $$
CREATE PROCEDURE sp_number_of_staff_by_nation(IN in_nation_name VARCHAR(25))
	BEGIN 
		DECLARE v_national_id SMALLINT;
        SET v_national_id = (
			SELECT national_id
            FROM `national`
            WHERE national_name = in_nation_name
		);
        
        WITH cte_staff_count AS (
			SELECT o.national_id, COUNT(s.staff_id) AS empl_count
            FROM office o
            INNER JOIN staff s
            ON o.office_id = s.office_id
            WHERE o.national_id = v_national_id
            GROUP BY o.national_id
		)
        SELECT n.national_name, IFNULL(c.empl_count, 0) AS empl_count
        FROM cte_staff_count c
        RIGHT JOIN `national` n
        ON c.national_id = n.national_id
        WHERE n.national_id = v_national_id;
    END $$
DELIMITER ;

CALL sp_number_of_staff_by_nation('Thailand');

-- QUESTION 20
SELECT o.office_id, o.street_address, COUNT(s.staff_id) AS empl_count
FROM office o
LEFT JOIN staff s
ON o.office_id = s.office_id
GROUP BY o.office_id;

-- QUESTION 21
DROP PROCEDURE IF EXISTS sp_number_of_staff_by_office_id;
DELIMITER $$
CREATE PROCEDURE sp_number_of_staff_by_office_id(IN in_office_id SMALLINT)
	BEGIN
		SELECT office_id, COUNT(staff_id)
        FROM staff
        GROUP BY office_id
        HAVING office_id = in_office_id;
    END $$	
DELIMITER ;

CALL sp_number_of_staff_by_office_id(1);

-- QUESTION 22
DROP PROCEDURE IF EXISTS sp_nation_with_most_staff;
DELIMITER $$
CREATE PROCEDURE sp_nation_with_most_staff()
	BEGIN
		WITH cte_count AS (
			SELECT o.national_id, COUNT(s.staff_id) AS empl_count
			FROM staff s
			INNER JOIN office o
			ON s.office_id = o.office_id
			GROUP BY o.national_id
		)
		SELECT n.national_id, n.national_name, c.empl_count
		FROM `national` n
		INNER JOIN cte_count c
		ON n.national_id = c.national_id
		WHERE c.empl_count = (
			SELECT MAX(empl_count) FROM cte_count
		);		
    END $$
DELIMITER ;

CALL sp_nation_with_most_staff();

-- QUESTION 23
SELECT n.national_name, COUNT(s.staff_id) AS empl_count
FROM staff s
INNER JOIN office o
ON s.office_id = o.office_id
RIGHT JOIN `national` n
ON o.national_id = n.national_id
GROUP BY n.national_name;

-- QUESTION 24
ALTER TABLE office DROP FOREIGN KEY fk_office_national_id;
ALTER TABLE office ADD CONSTRAINT fk_office_national_id FOREIGN KEY (national_id) REFERENCES `national` (national_id) ON DELETE SET NULL;

-- QUESTION 25
ALTER TABLE office DROP FOREIGN KEY fk_office_national_id;
ALTER TABLE office ADD CONSTRAINT fk_office_national_id FOREIGN KEY (national_id) REFERENCES `national` (national_id) ON DELETE CASCADE;
