package org.senai;

import java.util.ArrayList;
import java.util.List;

public class Turma {
    private int id;
    private String nome;
    private List<Aluno> alunos;
    private Professor professor;

    public Turma(int id, String nome) {
        this.id = id;
        this.nome = nome;
        this.alunos = new ArrayList<>();
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    // Métodos
    public void adicionarAluno(Aluno aluno) {
        alunos.add(aluno);
    }

    public void adicionarProfessor(Professor professor) {
        this.professor = professor;
    }

    public void visualizarTurma() {
        System.out.println("Turma: " + nome);
        System.out.println("Professor: " + (professor != null ? professor.getNome() : "Nenhum"));
        System.out.println("Alunos:");
        for (Aluno aluno : alunos) {
            System.out.println(" - " + aluno.getNome());
        }
    }
}
