package org.senai;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try (Connection conn = MySQLConnection.conexao()) {
            while (true) {
                System.out.println("Menu:");
                System.out.println("1. Cadastrar Turma");
                System.out.println("2. Ler Turmas");
                System.out.println("3. Atualizar Turma");
                System.out.println("4. Deletar Turma");
                System.out.println("5. Sair");
                System.out.print("Escolha uma opção: ");
                int escolha = scanner.nextInt();
                scanner.nextLine(); // Consumir a nova linha

                switch (escolha) {
                    case 1:
                        cadastrarTurmaMenu(conn, scanner);
                        break;
                    case 2:
                        lerTurmas(conn);
                        break;
                    case 3:
                        atualizarTurmaMenu(conn, scanner);
                        break;
                    case 4:
                        deletarTurmaMenu(conn, scanner);
                        break;
                    case 5:
                        System.out.println("Saindo...");
                        return;
                    default:
                        System.out.println("Opção inválida.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void cadastrarTurmaMenu(Connection conn, Scanner scanner) throws SQLException {
        System.out.print("Digite o ID do Professor: ");
        int professorId = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha

        System.out.print("Digite o nome do Professor: ");
        String professorNome = scanner.nextLine();

        System.out.print("Digite a idade do Professor: ");
        int professorIdade = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha

        Professor professor = new Professor(professorId, professorNome, professorIdade);

        List<Aluno> alunos = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            System.out.print("Digite o ID do Aluno " + i + ": ");
            int alunoId = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha

            System.out.print("Digite o nome do Aluno " + i + ": ");
            String alunoNome = scanner.nextLine();

            System.out.print("Digite a idade do Aluno " + i + ": ");
            int alunoIdade = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha

            alunos.add(new Aluno(alunoId, alunoNome, alunoIdade));
        }

        System.out.print("Digite o ID da Turma: ");
        int turmaId = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha

        System.out.print("Digite o nome da Turma: ");
        String turmaNome = scanner.nextLine();

        cadastrarTurma(conn, turmaId, turmaNome, professor, alunos);
        System.out.println("Cadastro realizado com sucesso!");
    }

    public static void lerTurmas(Connection conn) throws SQLException {
        String query = "SELECT * FROM Turma";
        try (PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                int professorId = rs.getInt("professor_id");
                System.out.println("ID: " + id + ", Nome: " + nome + ", Professor ID: " + professorId);
            }
        }
    }

    public static void atualizarTurmaMenu(Connection conn, Scanner scanner) throws SQLException {
        System.out.print("Digite o ID da Turma a ser atualizada: ");
        int turmaId = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha

        System.out.print("Digite o novo nome da Turma: ");
        String turmaNome = scanner.nextLine();

        System.out.print("Digite o novo ID do Professor: ");
        int professorId = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha

        String updateSQL = "UPDATE Turma SET nome = ?, professor_id = ? WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(updateSQL)) {
            pstmt.setString(1, turmaNome);
            pstmt.setInt(2, professorId);
            pstmt.setInt(3, turmaId);
            pstmt.executeUpdate();
        }
        System.out.println("Turma atualizada com sucesso!");
    }

    public static void deletarTurmaMenu(Connection conn, Scanner scanner) throws SQLException {
        System.out.print("Digite o ID da Turma a ser deletada: ");
        int turmaId = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha

        String deleteSQL = "DELETE FROM Turma WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(deleteSQL)) {
            pstmt.setInt(1, turmaId);
            pstmt.executeUpdate();
        }
        System.out.println("Turma deletada com sucesso!");
    }

    public static boolean idExists(Connection conn, String tableName, int id) throws SQLException {
        String query = "SELECT COUNT(*) FROM " + tableName + " WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }

    public static void cadastrarTurma(Connection conn, int turmaId, String turmaNome, Professor professor, List<Aluno> alunos) throws SQLException {
        // Verificar se IDs existem
        if (idExists(conn, "Pessoa", professor.getId())) {
            System.out.println("ID " + professor.getId() + " já existe na tabela Pessoa.");
            return;
        }
        for (Aluno aluno : alunos) {
            if (idExists(conn, "Pessoa", aluno.getId())) {
                System.out.println("ID " + aluno.getId() + " já existe na tabela Pessoa.");
                return;
            }
        }

        // Inserir Pessoa para Professor
        String insertPessoaSQL = "INSERT INTO Pessoa (id, nome, idade) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(insertPessoaSQL)) {
            pstmt.setInt(1, professor.getId());
            pstmt.setString(2, professor.getNome());
            pstmt.setInt(3, professor.getIdade());
            pstmt.executeUpdate();
        }

        // Inserir Professor
        String insertProfessorSQL = "INSERT INTO Professor (id, nome, idade) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(insertProfessorSQL)) {
            pstmt.setInt(1, professor.getId());
            pstmt.setString(2, professor.getNome());
            pstmt.setInt(3, professor.getIdade());
            pstmt.executeUpdate();
        }

        // Inserir Pessoas para Alunos
        for (Aluno aluno : alunos) {
            try (PreparedStatement pstmt = conn.prepareStatement(insertPessoaSQL)) {
                pstmt.setInt(1, aluno.getId());
                pstmt.setString(2, aluno.getNome());
                pstmt.setInt(3, aluno.getIdade());
                pstmt.executeUpdate();
            }
        }

        // Inserir Alunos
        String insertAlunoSQL = "INSERT INTO Aluno (id, nome, idade) VALUES (?, ?, ?)";
        for (Aluno aluno : alunos) {
            try (PreparedStatement pstmt = conn.prepareStatement(insertAlunoSQL)) {
                pstmt.setInt(1, aluno.getId());
                pstmt.setString(2, aluno.getNome());
                pstmt.setInt(3, aluno.getIdade());
                pstmt.executeUpdate();
            }
        }

        // Inserir Turma
        String insertTurmaSQL = "INSERT INTO Turma (id, nome, professor_id) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(insertTurmaSQL)) {
            pstmt.setInt(1, turmaId);
            pstmt.setString(2, turmaNome);
            pstmt.setInt(3, professor.getId());
            pstmt.executeUpdate();
        }

        // Inserir relação entre Turma e Alunos
        String insertTurmaAlunoSQL = "INSERT INTO Turma_Aluno (turma_id, aluno_id) VALUES (?, ?)";
        for (Aluno aluno : alunos) {
            try (PreparedStatement pstmt = conn.prepareStatement(insertTurmaAlunoSQL)) {
                pstmt.setInt(1, turmaId);
                pstmt.setInt(2, aluno.getId());
                pstmt.executeUpdate();
            }
        }

        System.out.println("Turma, professor e alunos cadastrados com sucesso!");
    }
}
