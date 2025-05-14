/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package agentes;

/**
 *
 * @author eduardobento
 */
import jade.core.Agent;
import comportamentos.CompradorComportamentos;

public class Comprador extends Agent {

    @Override

    protected void setup() {

        System.out.println("Agente Comprador iniciado: " + getLocalName()); //mensagem de inicialização do agente
        Object[] args = getArguments();
        if (args != null && args.length == 3) {
            String produto = (String) args[0];
            int quantidade = Integer.parseInt(args[1].toString());
            double precoMax = Double.parseDouble(args[2].toString());
            addBehaviour(new CompradorComportamentos(this, produto, quantidade, precoMax));
        } else {
            System.out.println("Argumentos insuficientes. Encerrando agente..."); // mensagem de erro por falta de argumentos
            doDelete();
        }
    }

    @Override
    protected void takeDown() {
        System.out.println("Comprador finalizado: " + getAID().getName());
    }
}
