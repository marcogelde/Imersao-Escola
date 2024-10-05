package org.senai;

import java.util.List;

public interface IProfessorDAO {
    Professor findById(int professorId);
    List<Professor> findAll();
    void save(Professor professor);
    void update(Professor professor);
    void delete(Professor professor);
}
