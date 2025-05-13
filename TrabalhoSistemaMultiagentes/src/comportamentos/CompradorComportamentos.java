/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package comportamentos;

/**
 *
 * @author eduardobento
 */

import jade.core.behaviours.*;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.core.Agent;


import java.util.*;

public class CompradorComportamentos extends SequentialBehaviour {

    private final Agent agente;
    private final String produto;
    private final int quantidade;
    private final double precoMax;
    
    //seta a quantidade de vendedores que precisa
    private final String[] vendedores = { "Vendedor1", "Vendedor2","Vendedor3" };
    private int respostasRecebidas = 0;
    private String melhorVendedor = null;
    private double melhorPreco = Double.MAX_VALUE;

    public CompradorComportamentos(Agent agente, String produto, int quantidade, double precoMax) {
        this.agente = agente;
        this.produto = produto;
        this.quantidade = quantidade;
        this.precoMax = precoMax;

        addSubBehaviour(new EnviarCFP());
        addSubBehaviour(new ReceberRespostas());
        addSubBehaviour(new AceitarMelhorProposta());
    }

    private class EnviarCFP extends OneShotBehaviour {
        @Override
        public void action() {
            ACLMessage cfp = new ACLMessage(ACLMessage.CFP);
            cfp.setContent(produto + "," + quantidade + "," + precoMax);

            for (String vendedor : vendedores) {
                cfp.addReceiver(new jade.core.AID(vendedor, jade.core.AID.ISLOCALNAME));
            }

            agente.send(cfp);
            System.out.println("CFP enviado para vendedores.");
        }
    }

    private class ReceberRespostas extends Behaviour {
        
        @Override
        public void action() {
            ACLMessage msg = agente.receive(MessageTemplate.or(
                    MessageTemplate.MatchPerformative(ACLMessage.PROPOSE),
                    MessageTemplate.MatchPerformative(ACLMessage.REFUSE)
            ));

            if (msg != null) {
                respostasRecebidas++;
                if (msg.getPerformative() == ACLMessage.PROPOSE) {
                    String[] dados = msg.getContent().split(",");
                    double preco = Double.parseDouble(dados[0]);

                    if (preco <= precoMax && preco < melhorPreco) {
                        melhorPreco = preco;
                        melhorVendedor = msg.getSender().getLocalName();
                    }
                } else if (msg.getPerformative() == ACLMessage.REFUSE) {
                    System.out.println("So to de zoio com o " + msg.getSender().getLocalName());
                }
            } else {
                block(1000);
            }
        }

        @Override
        public boolean done() {
            return respostasRecebidas == vendedores.length;
        }
    }

    private class AceitarMelhorProposta extends OneShotBehaviour {
        @Override
        public void action() {
            if (melhorVendedor != null) {
                ACLMessage accept = new ACLMessage(ACLMessage.ACCEPT_PROPOSAL);
                accept.addReceiver(new jade.core.AID(melhorVendedor, jade.core.AID.ISLOCALNAME));
                accept.setContent(produto + "," + quantidade);
                agente.send(accept);
                System.out.println("Proposta aceita de: " + melhorVendedor + " por R$ " + melhorPreco);
            } else {
                System.out.println("Nenhuma proposta adequada foi recebida.");
            }
        }
    }
}
