CREATE DATABASE biblioteca;

USE biblioteca;

CREATE TABLE livro (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(100),
    autor VARCHAR(100),
    ano INT
);
