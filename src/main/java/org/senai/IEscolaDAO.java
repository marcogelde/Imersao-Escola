package org.senai;

import java.util.List;

public interface IEscolaDAO {

    void adicionarProfessor(Pessoa pessoa, Professor professor);
    void adicionarAluno(Pessoa pessoa, Aluno aluno);
    void adicionarTurma(Turma turma, Professor professor);

    List<Professor> listarProfessor();
    List<Aluno> listarAluno();
    List<Turma> listarTurma();

    void atualizarProfessor(Pessoa pessoa, Professor professor);
    void atualizarAluno(Pessoa pessoa, Aluno aluno);

    void deletarProfessor(int id);
    void deletarAluno(int id);
    void deletarTurma(int id);
}
