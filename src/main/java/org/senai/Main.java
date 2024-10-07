import org.senai.Aluno;
import org.senai.EscolaDAO;
import org.senai.Professor;
import org.senai.Turma;

import java.sql.SQLException;

public static void main(String[] args) throws SQLException {
    EscolaDAO dao = new EscolaDAO();

    // Criando instâncias
    Aluno a1 = new Aluno(1,"João", 20, 8.5);
    dao.adicionarAluno(a1, a1);

    Aluno a2 = new Aluno(2,"Pedro", 21, 9.0);
    dao.adicionarAluno(a2, a2);

    Aluno a3 = new Aluno(3,"Marco", 29, 10.0);
    dao.adicionarAluno(a3, a3);

    Professor p1 = new Professor(4,"Honório", 35, 5000);
    dao.adicionarProfessor(p1, p1);

    Turma t1 = new Turma(1, "Programação");
    dao.adicionarTurma(t1, p1);


    t1.adicionarProfessor(p1);
    t1.adicionarAluno(a1);
    t1.adicionarAluno(a2);
    t1.adicionarAluno(a3);

    t1.visualizarTurma();

    //dao.listarAluno(a1);
}