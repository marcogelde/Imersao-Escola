package org.senai;

import java.util.List;

public interface IAlunoDAO {
    Aluno findById(int alunoId);
    List<Aluno> findAll();
    void save(Aluno aluno);
    void update(Aluno aluno);
    void delete(Aluno aluno);
}
