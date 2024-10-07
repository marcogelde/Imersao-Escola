CREATE DATABASE escola;
USE escola;

CREATE TABLE Pessoa
(
    id    INT PRIMARY KEY auto_increment,
    nome  VARCHAR(100) NOT NULL,
    idade INT          NOT NULL
);

CREATE TABLE Professor
(
    id INT PRIMARY KEY auto_increment,
    salario DOUBLE NOT NULL,
    FOREIGN KEY (id) REFERENCES Pessoa (id)
);

CREATE TABLE Aluno
(
    id INT PRIMARY KEY auto_increment,
    nota DOUBLE NOT NULL,
    FOREIGN KEY (id) REFERENCES Pessoa (id)
);

CREATE TABLE Turma
(
    id           INT PRIMARY KEY auto_increment,
    nome         VARCHAR(100) NOT NULL,
    professor_id INT,
    FOREIGN KEY (professor_id) REFERENCES Professor (id)
);

CREATE TABLE Turma_Aluno
(
    turma_id INT,
    aluno_id INT,
    PRIMARY KEY (turma_id, aluno_id),
    FOREIGN KEY (turma_id) REFERENCES Turma (id),
    FOREIGN KEY (aluno_id) REFERENCES Aluno (id)
);