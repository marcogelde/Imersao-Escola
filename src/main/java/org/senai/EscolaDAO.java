package org.senai;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EscolaDAO {

    // Create
    public void adicionarProfessor(Pessoa pessoa, Professor professor) {
        String sqlPessoa = "INSERT INTO Pessoa (nome, idade) VALUES (?, ?)";
        String sqlProfessor = "INSERT INTO Professor (id, salario) VALUES (?, ?)";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmtPessoa = conn.prepareStatement(sqlPessoa);
             PreparedStatement stmtProfessor = conn.prepareStatement(sqlProfessor)) {

            stmtPessoa.setString(1, pessoa.getNome());
            stmtPessoa.setInt(2, pessoa.getIdade());
            stmtPessoa.executeUpdate();

            stmtProfessor.setInt(1, pessoa.getId());
            stmtProfessor.setDouble(2, professor.getSalario());
            stmtProfessor.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao adicionar professor.", e);
        }
    }

    public void adicionarAluno(Pessoa pessoa, Aluno aluno) {
        String sqlPessoa = "INSERT INTO Pessoa (nome, idade) VALUES (?, ?)";
        String sqlAluno = "INSERT INTO Aluno (id, nota) VALUES (?, ?)";


        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmtPessoa = conn.prepareStatement(sqlPessoa);
             PreparedStatement stmtAluno = conn.prepareStatement(sqlAluno)) {

            stmtPessoa.setString(1, pessoa.getNome());
            stmtPessoa.setInt(2, pessoa.getIdade());
            stmtPessoa.executeUpdate();

            stmtAluno.setInt(1, pessoa.getId());
            stmtAluno.setDouble(2, aluno.getNota());
            stmtAluno.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao adicionar aluno.", e);
        }
    }

    public void adicionarTurma(Turma turma, Professor professor) {
        String sqlTurma = "INSERT INTO Turma (nome, professor_id) VALUES (?, ?)";


        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmtTurma = conn.prepareStatement(sqlTurma)) {

            stmtTurma.setString(1, turma.getNome());
            stmtTurma.setInt(2, professor.getId());

            stmtTurma.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao adicionar turma.", e);
        }
    }

    // Read
    public List<Professor> listarProfessor(Professor p1) throws SQLException {
        String sqlProf = "SELECT p.id, p.nome, p.idade, prof.salario " +
                         "FROM Pessoa p " +
                         "JOIN Professor prof ON p.id = prof.id";

        List<Professor> professores = new ArrayList<>();

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sqlProf);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                int idade = rs.getInt("idade");
                double salario = rs.getDouble("salario");

                Professor professor = new Professor(id, nome, idade, salario);
                professores.add(professor);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar professores.", e);
        }
        return professores;
    }

    public List<Aluno> listarAluno(Aluno a1) throws SQLException {
        String sqlAluno = "SELECT p.id, p.nome, p.idade, aluno.nota " +
                          "FROM Pessoa p " +
                          "JOIN Aluno ON p.id = Aluno.id";

        List<Aluno> alunos = new ArrayList<>();

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sqlAluno);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                int idade = rs.getInt("idade");
                double nota = rs.getDouble("nota");

                Aluno aluno = new Aluno(id, nome, idade, nota);
                alunos.add(aluno);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar alunos.", e);
        }
        return alunos;
    }

    // Update

    // Delete


}
