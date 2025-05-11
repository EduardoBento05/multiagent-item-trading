/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package agentes;
import jade.core.behaviours.Behaviour;
/**
 *
 * @author eduardobento
 */
public class MeusComportamentos extends Behaviour{
    
    private int acao = 0;
    
    @Override    
    public void action(){
            
        switch(acao) {
            case 1:
                System.out.println("Acao " + acao);break;
            case 2:
                System.out.println("Acao " + acao);break;
            case 3:
                System.out.println("Acao " + acao);break;
            default:
                System.out.println("Sem Acao definida" + acao);break;
        }
        
        this.acao = this.acao + 1 ;
    
    }

    @Override
    public boolean done() {
        return acao == 3; // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
}
