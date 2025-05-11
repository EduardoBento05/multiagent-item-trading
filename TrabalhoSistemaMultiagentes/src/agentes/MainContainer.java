package agentes;

import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;

public class MainContainer {
    public static void main(String[] args) {
        // Inicializa o ambiente do JADE
        Runtime rt = Runtime.instance();
        Profile profile = new ProfileImpl();
        profile.setParameter(Profile.GUI, "true"); // Abre a interface gráfica do JADE

        AgentContainer mainContainer = rt.createMainContainer(profile);

        try {
            // Cria e inicia o agente Comprador
            AgentController comprador = mainContainer.createNewAgent("Comprador", "agentes.Comprador", null);
            comprador.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
