package org.senai;

import java.util.List;

public interface ITurmaDAO {
    Turma findById(int turmaId);
    List<Turma> findAll();
    void save(Turma turma);
    void update(Turma turma);
    void delete(Turma turma);
}
