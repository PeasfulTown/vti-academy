/*============================== INSERT DATABASE =======================================*/
/*======================================================================================*/
-- Add data Department
INSERT INTO Department(DepartmentName)
VALUES
						(N'Marketing'	),
						(N'Sale'		),
						(N'Bảo vệ'		),
						(N'Nhân sự'		),
						(N'Kỹ thuật'	),
						(N'Tài chính'	),
						(N'Phó giám đốc'),
						(N'Giám đốc'	),
						(N'Thư kí'		),
						(N'Bán hàng'	);

-- Add data position
INSERT INTO Position	(PositionName	)
VALUES 					('Dev'			),
						('Test'			),
						('Scrum_Master'	),
						('PM'			);


-- Add data Account
INSERT INTO `Account`
(Email, Username, FullName, DepartmentID, PositionID, CreateDate, `password`, Role)

VALUES
('Email1@gmail.com','Username1','Fullname1',5,1,'2020-03-05','$2a$10$W2neF9.6Agi6kAKVq8q3fec5dHW8KUA.b0VSIGdIZyUravfLpyIFi','ADMIN'),

('Email2@gmail.com','Username2','Fullname2',1,2,'2020-03-05','$2a$10$W2neF9.6Agi6kAKVq8q3fec5dHW8KUA.b0VSIGdIZyUravfLpyIFi','USER'),

('Email3@gmail.com','Username3','Fullname3',2,2,'2020-03-07','$2a$10$W2neF9.6Agi6kAKVq8q3fec5dHW8KUA.b0VSIGdIZyUravfLpyIFi','USER'),

('Email4@gmail.com','Username4','Fullname4',3,4,'2020-03-08','$2a$10$W2neF9.6Agi6kAKVq8q3fec5dHW8KUA.b0VSIGdIZyUravfLpyIFi','USER'),

('Email5@gmail.com','Username5','Fullname5',4,4,'2020-03-10','$2a$10$W2neF9.6Agi6kAKVq8q3fec5dHW8KUA.b0VSIGdIZyUravfLpyIFi','USER'),

('Email6@gmail.com','Username6','Fullname6',6,3,'2020-04-05','$2a$10$W2neF9.6Agi6kAKVq8q3fec5dHW8KUA.b0VSIGdIZyUravfLpyIFi','USER'),

('Email7@gmail.com','Username7','Fullname7',2,2,'2020-04-05','$2a$10$W2neF9.6Agi6kAKVq8q3fec5dHW8KUA.b0VSIGdIZyUravfLpyIFi','USER'),

('Email8@gmail.com','Username8','Fullname8',8,1,'2020-04-07','$2a$10$W2neF9.6Agi6kAKVq8q3fec5dHW8KUA.b0VSIGdIZyUravfLpyIFi','USER'),

('Email9@gmail.com','Username9','Fullname9',2,2,'2020-04-07','$2a$10$W2neF9.6Agi6kAKVq8q3fec5dHW8KUA.b0VSIGdIZyUravfLpyIFi','USER'),

('Email10@gmail.com','Username10','Fullname10',10,1,'2020-04-09','$2a$10$W2neF9.6Agi6kAKVq8q3fec5dHW8KUA.b0VSIGdIZyUravfLpyIFi','USER');




