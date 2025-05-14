/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package comportamentos;

import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.core.Agent;
import modulos.Produtos;

import java.util.List;

/**
 * Agende vendedor, recebe as mensagens dos compradores e responde com base no
 * estoque
 *
 * @author eduardobento
 */
public class VendedorComportamentos extends CyclicBehaviour {

    private final List<Produtos> estoque;

    public VendedorComportamentos(Agent agente, List<Produtos> estoque) {//recebendo o agente e o estoque disopnivel
        super(agente);
        this.estoque = estoque;
    }

    @Override
    public void action() {
        ACLMessage msg = myAgent.receive();
        if (msg != null) {
            switch (msg.getPerformative()) {
                case ACLMessage.CFP: //avalia se a mensagem é uma solicitação de proposta (cfp)
                    tratarCFP(msg);
                    break;
                case ACLMessage.ACCEPT_PROPOSAL:
                    tratarAceite(msg); //avalia caso a proposta foi aceita
                    break;
            }
        } else {
            block();
        }
    }

    private void tratarCFP(ACLMessage msg) {
        String[] partes = msg.getContent().split(",");
        String nomeProduto = partes[0];
        int qtdDesejada = Integer.parseInt(partes[1]);
        double precoMax = Double.parseDouble(partes[2]);

        for (Produtos p : estoque) { // for para avaliar se tem o produto solicitado no estoque
            if (p.getNome().equalsIgnoreCase(nomeProduto) && p.getQuantidade() >= qtdDesejada && p.getPreco() <= precoMax) {
                ACLMessage proposta = msg.createReply(); // resposta da proposta 
                proposta.setPerformative(ACLMessage.PROPOSE);
                proposta.setContent(p.getPreco() + "");
                myAgent.send(proposta);
                return;
            }
        }

        ACLMessage recusa = msg.createReply();
        recusa.setPerformative(ACLMessage.REFUSE);
        recusa.setContent("Produto não disponível");
        myAgent.send(recusa);
    }

    private void tratarAceite(ACLMessage msg) {
        String[] partes = msg.getContent().split(",");
        String nomeProduto = partes[0];
        int qtd = Integer.parseInt(partes[1]);

        for (Produtos p : estoque) {// valida se tem o produto no estoque
            if (p.getNome().equalsIgnoreCase(nomeProduto) && p.getQuantidade() >= qtd) {
                p.reduzirQuantidade(qtd); //reduz a quantidade do produto no estoque 
                ACLMessage inform = msg.createReply();
                inform.setPerformative(ACLMessage.INFORM);
                inform.setContent("Venda concluída de " + qtd + " " + nomeProduto);// passa as informações da venda 
                myAgent.send(inform);
                System.out.println("Venda realizada por " + myAgent.getLocalName());
                return;
            }
        }
    }
}
