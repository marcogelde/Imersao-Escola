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
    id INT PRIMARY KEY,
    salario DOUBLE NOT NULL,
    FOREIGN KEY (id) REFERENCES Pessoa (id) ON DELETE CASCADE
);

CREATE TABLE Aluno
(
    id INT PRIMARY KEY,
    nota DOUBLE NOT NULL,
    FOREIGN KEY (id) REFERENCES Pessoa (id)
);

CREATE TABLE Turma
(
    id           INT PRIMARY KEY auto_increment,
    nome         VARCHAR(100) NOT NULL,
    professor_id INT,
    FOREIGN KEY (professor_id) REFERENCES Professor (id) ON DELETE CASCADE
);

CREATE TABLE Turma_Aluno
(
    id_turma INT,
    id_aluno INT,
    PRIMARY KEY (id_turma, id_aluno),
    FOREIGN KEY (id_turma) REFERENCES Turma (id),
    FOREIGN KEY (id_aluno) REFERENCES Aluno (id)
);