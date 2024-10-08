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
        String sqlPessoa = "INSERT INTO Pessoa (id, nome, idade) VALUES (?, ?, ?)";
        String sqlProfessor = "INSERT INTO Professor (id, salario) VALUES (?, ?)";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmtPessoa = conn.prepareStatement(sqlPessoa);
             PreparedStatement stmtProfessor = conn.prepareStatement(sqlProfessor)) {

            // Inserir na tabela Pessoa
            stmtPessoa.setInt(1, professor.getId());
            stmtPessoa.setString(2, professor.getNome());
            stmtPessoa.setInt(3, professor.getIdade());
            stmtPessoa.executeUpdate();

            // Inserir na tabela Professor
            stmtProfessor.setInt(1, professor.getId());
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
    public List<Professor> listarProfessor() {
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
    public List<Aluno> listarAluno() {
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

    public List<Turma> listarTurma() {
        String sqlTurma = "SELECT t.id, t.nome AS nome_turma, p.id AS professor_id, p.nome AS professor_nome, p.idade AS professor_idade, prof.salario " +
                "FROM Turma t " +
                "JOIN Professor prof ON t.professor_id = prof.id " +
                "JOIN Pessoa p ON prof.id = p.id";

        List<Turma> turmas = new ArrayList<>();

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sqlTurma);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int turmaId = rs.getInt("id");
                String nomeTurma = rs.getString("nome_turma");

                // Dados do professor
                int professorId = rs.getInt("professor_id");
                String professorNome = rs.getString("professor_nome");
                int professorIdade = rs.getInt("professor_idade");
                double salarioProfessor = rs.getDouble("salario");

                Professor professor = new Professor(professorId, professorNome, professorIdade, salarioProfessor);
                Turma turma = new Turma(turmaId, nomeTurma);
                turma.setProfessor(professor);

                turmas.add(turma);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar turmas.", e);
        }
        return turmas;
    }


    // Update
    public void atualizarProfessor(Pessoa pessoa, Professor professor) {
            String sqlPessoa = "UPDATE Pessoa SET nome = ?, idade = ? WHERE id = ?";
            String sqlProfessor = "UPDATE Professor SET salario = ? WHERE id = ?";

            try (Connection conn = Conexao.getConnection();
                PreparedStatement stmtPessoa = conn.prepareStatement(sqlPessoa);
                PreparedStatement stmtProfessor = conn.prepareStatement(sqlProfessor)){

                stmtPessoa.setString(1,pessoa.getNome());
                stmtPessoa.setInt(2,pessoa.getIdade());
                stmtPessoa.setInt(3,professor.getId());
                stmtPessoa.executeUpdate();

                stmtProfessor.setDouble(1, professor.getSalario());
                stmtProfessor.setInt(2,professor.getId());
                stmtProfessor.executeUpdate();

            } catch (SQLException e) {
                throw new RuntimeException("Erro ao atualizar professor.", e);
            }
    }

    public void atualizarAluno(Pessoa pessoa, Aluno aluno) {
        String sqlPessoa = "UPDATE Pessoa SET nome = ?, idade = ? WHERE id = ?";
        String sqlAluno = "UPDATE Aluno SET nota = ? WHERE id = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmtPessoa = conn.prepareStatement(sqlPessoa);
             PreparedStatement stmtAluno = conn.prepareStatement(sqlAluno)){

            stmtPessoa.setString(1,pessoa.getNome());
            stmtPessoa.setInt(2,pessoa.getIdade());
            stmtPessoa.setInt(3,aluno.getId());
            stmtPessoa.executeUpdate();

            stmtAluno.setDouble(1, aluno.getNota());
            stmtAluno.setInt(2,aluno.getId());
            stmtAluno.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar aluno.", e);
        }
    }

    // Delete
    public void deletarProfessor(int id) {
        String sqlProfessor = "DELETE FROM Professor WHERE id = ?";
        String sqlPessoa = "DELETE FROM Pessoa WHERE id = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmtProfessor = conn.prepareStatement(sqlProfessor);
             PreparedStatement stmtPessoa = conn.prepareStatement(sqlPessoa)) {

            // Deleta o professor
            stmtProfessor.setInt(1, id);
            stmtProfessor.executeUpdate();

            // Deleta a pessoa associada ao professor
            stmtPessoa.setInt(1, id);
            stmtPessoa.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar professor.", e);
        }
    }


    public void deletarAluno(int id) {
        String sqlAluno = "DELETE FROM Aluno WHERE id = ?";
        String sqlPessoa = "DELETE FROM Pessoa WHERE id = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmtAluno = conn.prepareStatement(sqlAluno);
             PreparedStatement stmtPessoa = conn.prepareStatement(sqlPessoa)) {

            stmtAluno.setInt(1, id);
            stmtAluno.executeUpdate();

            stmtPessoa.setInt(1, id);
            stmtPessoa.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar aluno.", e);
        }
    }

    public void deletarTurma(int id) {
        String sqlTurmaAluno = "DELETE FROM Turma_Aluno WHERE id_turma = ?";
        String sqlTurma = "DELETE FROM Turma WHERE id = ?";


        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmtTurmaAluno = conn.prepareStatement(sqlTurmaAluno);
             PreparedStatement stmtTurma = conn.prepareStatement(sqlTurma)) {

            // Deleta o relacionamento da turma com os alunos
            stmtTurmaAluno.setInt(1, id);
            stmtTurmaAluno.executeUpdate();

            // Deleta a turma
            stmtTurma.setInt(1, id);
            stmtTurma.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar turma.", e);
        }
    }

}
