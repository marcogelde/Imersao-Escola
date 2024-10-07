package org.senai;

public class Aluno extends Pessoa {
        private double nota;

    public Aluno(int id, String nome, int idade, double nota) {
        super(id, nome, idade);
       this.nota = nota;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    @Override
    public String toString() {
        return STR."\{this.getNome()}, Idade: \{this.getIdade()}, Nota: \{this.getNota()}";
    }
}
