/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modulos;

import jade.util.leap.Serializable;

/**
 *
 * @author eduardobento
 */
public class Produtos implements Serializable {

    String nome;
    double preco;
    int quantidade;

    //inicializa o produto e suas informações
    public Produtos(String nome, double preco, Integer quantidade) {
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void reduzirQuantidade(int qtd) {
        this.quantidade -= qtd;
    }

    //ajustando a frase de forma mais legivel 
    @Override
    public String toString() {
        return "Produto{"
                + "nome='" + nome + '\''
                + ", preco=" + preco
                + ", quantidade=" + quantidade
                + '}';
    }

    //imprimindo os dados do produto na tela
    public void imprimir() {
        System.out.println("********************************");
        System.out.println("Nome: " + nome);
        System.out.println("Preco: " + preco);
        System.out.println("Disponibildiade: " + quantidade);
        System.out.println("********************************");
    }

}
