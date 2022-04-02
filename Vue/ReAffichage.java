package Vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

/**
 * ce thread assure le réaffichage régulier de la fenetre
 */
public class ReAffichage extends Thread {

    Affichage affichage;
    InterfaceEntite ie;

    public ReAffichage(Affichage a) {
        affichage = a;
    }

    public void run() {

        while(true) {
            ie = affichage.getInterface_entitee_courante();
            //System.out.println(Thread.activeCount());


            if(ie != null)
            {
                ie.mise_a_jour();
            }
            affichage.mise_a_jour_score();
            affichage.revalidate();
            affichage.repaint();

            if (affichage.getEtat().getCountdown().isOver()) //a la fin du temps imparti
            {
                affichage.fenetre.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //assure que le programme ne se termine pas lors de
                //la fermeture de la JFRAME contenant le jeu
                Window test = affichage.fenetre; //recupération de la JFrame dans l'affichage
                test.dispatchEvent(new WindowEvent(test,WindowEvent.WINDOW_CLOSING));//envoie du signal de fermeture à la fenêtre
                return; //fin du trhead
            }
            try { Thread.sleep(20); }
            catch (Exception e) { e.printStackTrace(); }
        }
    }
}