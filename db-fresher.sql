CREATE DATABASE fresher_training_management;
USE fresher_training_management;

DROP TABLE IF EXISTS trainee;

CREATE TABLE IF NOT EXISTS trainee (
	trainee_id			INT PRIMARY KEY AUTO_INCREMENT,
    full_name			VARCHAR(50),
    birth_date 			DATE,
    gender				ENUM('male', 'female', 'unknown'),
    et_iq				INT CHECK (0 < et_iq AND et_iq < 20),
    et_gmath			INT CHECK (0 < et_gmath AND et_gmath < 20),
    et_english			INT CHECK (0 < et_english AND et_english < 50),
    training_class		CHAR(3),
    evaluation_notes	TEXT,
    vti_account 		VARCHAR(50) NOT NULL UNIQUE
);

-- acceptable record
INSERT INTO trainee VALUES (0, "Alice Wong", current_date(), "female", 15, 10, 40, "DTN", "No Comment", "alicew");
-- gender out of bounds 
INSERT INTO trainee VALUES (1, "John Smith", current_date(), "n/a", 15, 10, 40, "DTN", "No Comment", "johns");
-- et_gmath out of bounds
INSERT INTO trainee VALUES (2, "Adrian Smith", current_date(), "unknown", 15, 10, 100, "DTN", "No Comment", "adrians");
-- vti_account not unique
INSERT INTO trainee VALUES (3, "Emily", current_date(), "female", 15, 10, 40, "DTN", "No Comment", "alicew");