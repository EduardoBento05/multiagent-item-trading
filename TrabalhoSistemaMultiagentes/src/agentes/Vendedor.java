/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package agentes;

import comportamentos.VendedorComportamentos;
import jade.core.Agent;
import modulos.Produtos;



import java.util.ArrayList;
import java.util.List;



/**
 *
 * @author eduardobento
 */

public class Vendedor extends Agent {
    private final List<Produtos> estoque = new ArrayList<>();

    @Override
    protected void setup() {
        System.out.println("Agente Vendedor iniciado: " + getLocalName());

        Object[] args = getArguments();
        if (args != null) {
            for (Object arg : args) {
                String[] partes = ((String) arg).split(",");
                String nome = partes[0];
                double preco = Double.parseDouble(partes[1]);
                int qtd = Integer.parseInt(partes[2]);
                estoque.add(new Produtos(nome, preco, qtd));
            }
        }

        addBehaviour(new VendedorComportamentos(this, estoque));
    }

    @Override
    protected void takeDown() {
        System.out.println("Vendedor finalizado: " + getAID().getName());
    }
}
