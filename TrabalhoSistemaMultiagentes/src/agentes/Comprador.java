package agentes;
import jade.core.Agent;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author eduardobento
 */
public class Comprador extends Agent {
    @Override
    protected void setup(){
        System.out.println("agentes.Comprador.setup()" + getAID().getName());
        //adicionando comportamento
        addBehaviour(new MeusComportamentos());
    }
}
