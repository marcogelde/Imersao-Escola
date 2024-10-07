package org.senai;

import java.util.ArrayList;


public class Turma {
    private int id;
    private String nome;
    private ArrayList<Aluno> listaAlunos;
    private Professor professor;

    public Turma(int id, String nome) {
        this.id = id;
        this.nome = nome;
        this.listaAlunos = new ArrayList<>();
    }

    // Métodos
    public void adicionarAluno(Aluno a) {
        listaAlunos.add(a);
    }

    public void adicionarProfessor(Professor p) {
        this.professor = p;
    }

    public void visualizarTurma() {
        System.out.println("Turma: " + nome);
        System.out.println("Professor: " + (professor != null ? professor.toString() : "Nenhum professor cadastrado"));
        System.out.println("Alunos:");
        for (Aluno a : listaAlunos) {
            System.out.println(" - " + a.toString());
        }
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

    public ArrayList<Aluno> getListaAlunos() {
        return listaAlunos;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }
}
