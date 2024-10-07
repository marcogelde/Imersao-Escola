package org.senai;

public class Professor extends Pessoa {
    private double salario;

    public Professor(int id, String nome, int idade, double salario) {
        super(id, nome, idade);
        this.salario = salario;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    @Override
    public String toString() {
        return STR."\{this.getNome()}, Idade: \{this.getIdade()}, Salário: R$\{this.getSalario()}";
    }
}
