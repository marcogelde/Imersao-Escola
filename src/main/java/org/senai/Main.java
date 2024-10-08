import org.senai.Aluno;
import org.senai.EscolaDAO;
import org.senai.Professor;
import org.senai.Turma;

import java.sql.SQLException;

public static void main(String[] args) throws SQLException {
    EscolaDAO dao = new EscolaDAO();

    // Create
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

    // Read
    for(Aluno a : dao.listarAluno()){
        System.out.println("ID: "+a.getId() + " - Aluno " + a.getNome() + ", " + a.getIdade() + " anos e nota: " + a.getNota());
    }

    for(Professor p : dao.listarProfessor()){
        System.out.println("ID: "+p.getId() + " - Professor " + p.getNome() + ", " + p.getIdade() + " anos e salário: R$" + p.getSalario());
    }

    // Update
    Professor p2 = new Professor(4,"João", 30, 6000);
    dao.atualizarProfessor(p2, p2);

    for(Professor p : dao.listarProfessor()){
        System.out.println("ID: "+p.getId() + " - Professor " + p.getNome() + ", " + p.getIdade() + " anos e salário: R$" + p.getSalario());
    }

    Aluno a4 = new Aluno(3,"Rafa", 18, 7);
    dao.atualizarAluno(a4, a4);

    for(Aluno a : dao.listarAluno()){
        System.out.println("ID: "+a.getId() + " - Aluno " + a.getNome() + ", " + a.getIdade() + " anos e nota: " + a.getNota());
    }

    // Delete
    dao.deletarProfessor(4);
    System.out.println("Professor deletado com sucesso!");

    dao.deletarAluno(1);
    dao.deletarAluno(2);
    dao.deletarAluno(3);
    System.out.println("Alunos deletados com sucesso!");

    dao.deletarTurma(1);
    System.out.println("Turma deletada com sucesso!");

}

