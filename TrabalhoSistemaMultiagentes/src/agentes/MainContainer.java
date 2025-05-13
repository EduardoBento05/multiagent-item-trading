
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package agentes;


/**
 *
 * @author eduardobento
 */

import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;

public class MainContainer {
    public static void main(String[] args) {
        Runtime rt = Runtime.instance();
        Profile profile = new ProfileImpl();
        profile.setParameter(Profile.GUI, "true");

        AgentContainer container = rt.createMainContainer(profile);

        try {
            //adiciona compradores e vendedores estaticamente, e para percorrero todos vendedores tem que mudar na lista dos comportamentos do comprador
            
            // Comprador quer comprar 5 maçãs por até 3.5
            Object[] argsComprador = new Object[] { "abacate", 5, 3.5 };
            AgentController comprador = container.createNewAgent("Comprador", "agentes.Comprador", argsComprador);
            comprador.start();

            // Vendedor1 tem maçã e banana
            Object[] argsVendedor1 = new Object[] { "maca,3.0,10", "banana,2.0,15" };
            AgentController vendedor1 = container.createNewAgent("Vendedor1", "agentes.Vendedor", argsVendedor1);
            vendedor1.start();

            // Vendedor2 tem só maçã, mas mais cara
            Object[] argsVendedor2 = new Object[] { "maca,3.4,10" };
            AgentController vendedor2 = container.createNewAgent("Vendedor2", "agentes.Vendedor", argsVendedor2);
            vendedor2.start();
            
            Object[] argsVendedor3 = new Object[] { "banana,3.4,10", "abacate,3.4,10" };
            AgentController vendedor3 = container.createNewAgent("Vendedor3", "agentes.Vendedor", argsVendedor3);
            vendedor3.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
