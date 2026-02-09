CREATE DATABASE IF NOT EXISTS mydatabase;

USE mydatabase;

CREATE TABLE `users` {
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL
};

INSERT INTO users (name) VALUES ('Alice1'), ('Bob1');
