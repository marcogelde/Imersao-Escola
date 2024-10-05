package org.senai;

import java.util.ArrayList;
import java.util.List;

public class ProfessorDAO implements IProfessorDAO {
    private List<Professor> professores = new ArrayList<>();

    @Override
    public Professor findById(int professorId) {
        for (Professor professor : professores) {
            if (professor.getId() == professorId) {
                return professor;
            }
        }
        return null;
    }

    @Override
    public List<Professor> findAll() {
        return professores;
    }

    @Override
    public void save(Professor professor) {
        professores.add(professor);
    }

    @Override
    public void update(Professor professor) {
        int index = -1;
        for (int i = 0; i < professores.size(); i++) {
            if (professores.get(i).getId() == professor.getId()) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            professores.set(index, professor);
        }
    }

    @Override
    public void delete(Professor professor) {
        professores.remove(professor);
    }
}
