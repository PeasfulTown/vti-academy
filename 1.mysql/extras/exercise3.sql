CREATE DATABASE IF NOT EXISTS db_ql_doan;
USE db_ql_doan;

-- QUESTION 1
DROP TABLE IF EXISTS huongdan;
DROP TABLE IF EXISTS giangvien;
DROP TABLE IF EXISTS sinhvien; 
DROP TABLE IF EXISTS detai;

CREATE TABLE giangvien (
	id_gv		SMALLINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    ten_gv		VARCHAR(40) NOT NULL,
    tuoi		TINYINT UNSIGNED NOT NULL, 
    hocvi		ENUM(
					'Ths'
					, 'Ts'
					, 'PGS'
                    , 'GS'
				)
);

CREATE TABLE sinhvien (
	id_sv		SMALLINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    ten_sv		VARCHAR(40) NOT NULL,
    namsinh		SMALLINT NOT NULL,
    quequan		VARCHAR(40) NOT NULL
);

CREATE TABLE detai (
	id_detai	SMALLINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    ten_detai	VARCHAR(40) NOT NULL
);

CREATE TABLE huongdan (
	id			SMALLINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    id_sv		SMALLINT UNSIGNED NOT NULL,
    id_detai	SMALLINT UNSIGNED NOT NULL,
    id_gv		SMALLINT UNSIGNED NOT NULL,
    diem		TINYINT UNSIGNED,
    
    CONSTRAINT fk_huongdan_id_sv 
		FOREIGN KEY (id_sv)
		REFERENCES sinhvien (id_sv),
	CONSTRAINT fk_huongdan_id_detai
		FOREIGN KEY (id_detai)
        REFERENCES detai (id_detai),
	CONSTRAINT fk_huongdan_id_gv
		FOREIGN KEY (id_gv)
		REFERENCES giangvien (id_gv)
);

INSERT INTO giangvien 	(ten_gv						, tuoi	, hocvi		)
VALUES					('Isobel Moakson'			, 43	, 'Ths'		),
						('Rafaela Lardnar'			, 29	, 'Ths'		),
						('Gerik Jury'				, 35	, 'GS'		),
						('Christabella Blakiston'	, 27	, 'Ths'		),
						('Markus Hilhouse'			, 33	, 'PGS'		),
						('Angela Schroeder'			, 46	, 'PGS'		),
						('Coletta Tomson'			, 45	, 'Ths'		),
						('Duff Metcalf'				, 43	, 'Ts'		),
						('Ellette Watford'			, 43	, 'Ts'		),
						('Sofie Longmead'			, 36	, 'Ts'		),
						('Briney Zink'				, 49	, 'Ths'		),
						('Myrlene Derricoat'		, 34	, 'GS'		),
						('Esme Finder'				, 26	, 'Ts'		),
						('Viole Sirey'				, 31	, 'GS'		);

INSERT INTO sinhvien	(ten_sv						, namsinh		, quequan			)
VALUES					('Richy Trussman'			, 1993			, 'Łobżenica'		),
						('Jarrod Fairbourne'		, 1993			, 'Los Arcos'		),
						('Montague Harness'			, 2002			, 'Kálamos'			),
						('Celle Woodstock'			, 1999			, 'Lipa City'		),
						('Regine Grollmann'			, 2000			, 'Beregovoy'		),
						('Laverne Gowland'			, 1997			, 'Wufeng'			),
						('Car Neal'					, 1994			, 'Ninghai'			),
						('Urban Truman'				, 2001			, 'Xinqiaohe'		),
						('Brewer Craigs'			, 2001			, 'Talayan'			),
						('Pepi Pettyfar'			, 1993			, 'Porto Alegre'	),
						('Othello Wintour'			, 1999			, 'Paraguaçu'		),
						('Atlanta Arckoll'			, 1997			, 'Liperi'			),
						('Boycie Jevon'				, 1996			, 'Mabalacat'		),
						('Amalee Pennick'			, 1990			, 'Boisbriand'		);
                        
INSERT INTO detai		(ten_detai					)
VALUES					('morbi a ipsum'			),
						('neque aenean'				),
						('felis donec'				),
						('ligula nec sem'			),
						('cum sociis'				),
						('faucibus orci luctus'		),
						('ut mauris'				),
						('luctus cum'				),
						('a ipsum'					),
						('id consequat in'			),
						('nisi volutpat'			),
						('integer non velit'		),
						('at dolor'					),
						('adipiscing elit proin'	);
                        
INSERT INTO huongdan	(id_sv	, id_detai	, id_gv	, diem	)
VALUES					(6		, 8			, 12	, 10	),
						(12		, 5			, 3		, 2		),
						(3		, 3			, 4		, 3		),
						(9		, 8			, 2		, 7		),
						(12		, 6			, 9		, 10	),
						(3		, 7			, 12	, 9		),
						(8		, 1			, 3		, 10	),
						(3		, 10		, 2		, 7		),
						(6		, 2			, 8		, 2		),
						(5		, 11		, 10	, 2		),
						(7		, 12		, 3		, 8		),
						(8		, 12		, 12	, 4		),
						(11		, 10		, 7		, 9		),
						(4		, 10		, 8		, 4		);
                        
-- QUESTION 2
	-- a)
SELECT sv.id_sv, sv.ten_sv
FROM sinhvien sv
LEFT JOIN huongdan hd
ON sv.id_sv = hd.id_sv
WHERE hd.id_detai IS NULL;

	-- b)
SELECT COUNT(id_sv)
FROM huongdan
WHERE id_detai = 6;

-- QUESTION 3
CREATE OR REPLACE VIEW vw_sinhvien_info AS 
	SELECT sv.id_sv, sv.ten_sv, IFNULL(GROUP_CONCAT(dt.ten_detai), 'Chua co')
    FROM sinhvien sv
    LEFT JOIN huongdan hd
    ON sv.id_sv = hd.id_sv
    LEFT JOIN detai dt
    ON hd.id_detai = dt.id_detai
    GROUP BY sv.id_sv;
    
SELECT * FROM vw_sinhvien_info;

-- QUESTION 4
DROP TRIGGER IF EXISTS trg_before_sinhvien_insert;
DELIMITER $$
CREATE TRIGGER trg_before_sinhvien_insert
	BEFORE INSERT ON sinhvien
    FOR EACH ROW
    BEGIN
		IF (NEW.namsinh <= 1950) THEN 
			SIGNAL SQLSTATE '45001'
            SET MESSAGE_TEXT = 'Moi ban kiem tra lai nam sinh';
		END IF;
    END $$
DELIMITER ;

-- QUESTION 5
ALTER TABLE huongdan DROP CONSTRAINT fk_huongdan_id_sv;
ALTER TABLE huongdan 
	ADD CONSTRAINT fk_huongdan_id_sv 
	FOREIGN KEY (id_sv)
    REFERENCES sinhvien (id_sv)
    ON DELETE CASCADE;

-- QUESTION 6 
DROP PROCEDURE IF EXISTS sp_delete_sinhvien_by_name;
DELIMITER $$
CREATE PROCEDURE sp_delete_sinhvien_by_name(IN in_sinhvien_name VARCHAR(30))
	BEGIN
		DECLARE v_sinhvien_id SMALLINT;
        SET v_sinhvien_id = (
			SELECT id_sv FROM sinhvien WHERE ten_sv = in_sinhvien_name
		);
        
        DELETE FROM huongdan WHERE id_sv = v_sinhvien_id;
        DELETE FROM sinhvien WHERE id_sv = v_sinhvien_id;
    END $$
DELIMITER ;