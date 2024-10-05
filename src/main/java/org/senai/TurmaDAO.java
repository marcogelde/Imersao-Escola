package org.senai;

import java.util.ArrayList;
import java.util.List;

public class TurmaDAO implements ITurmaDAO {
    private List<Turma> turmas = new ArrayList<>();

    @Override
    public Turma findById(int turmaId) {
        for (Turma turma : turmas) {
            if (turma.getId() == turmaId) {
                return turma;
            }
        }
        return null;
    }

    @Override
    public List<Turma> findAll() {
        return turmas;
    }

    @Override
    public void save(Turma turma) {
        turmas.add(turma);
    }

    @Override
    public void update(Turma turma) {
        int index = -1;
        for (int i = 0; i < turmas.size(); i++) {
            if (turmas.get(i).getId() == turma.getId()) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            turmas.set(index, turma);
        }
    }

    @Override
    public void delete(Turma turma) {
        turmas.remove(turma);
    }
}
