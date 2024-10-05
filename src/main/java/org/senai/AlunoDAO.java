package org.senai;

import java.util.ArrayList;
import java.util.List;

public class AlunoDAO implements IAlunoDAO {
    private List<Aluno> alunos = new ArrayList<>();

    @Override
    public Aluno findById(int alunoId) {
        for (Aluno aluno : alunos) {
            if (aluno.getId() == alunoId) {
                return aluno;
            }
        }
        return null;
    }

    @Override
    public List<Aluno> findAll() {
        return alunos;
    }

    @Override
    public void save(Aluno aluno) {
        alunos.add(aluno);
    }

    @Override
    public void update(Aluno aluno) {
        int index = -1;
        for (int i = 0; i < alunos.size(); i++) {
            if (alunos.get(i).getId() == aluno.getId()) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            alunos.set(index, aluno);
        }
    }

    @Override
    public void delete(Aluno aluno) {
        alunos.remove(aluno);
    }
}
