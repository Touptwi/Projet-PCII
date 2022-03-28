package Vue;

import Modele.Entitees.Entitee;
import Modele.Entitees.EntiteeAvecInventaire.Batiments.Batiment;
import Modele.Entitees.EntiteeAvecInventaire.EntieesDeplacable.Dwarf;
import Modele.Entitees.EntiteeAvecInventaire.EntieesDeplacable.Goblin;
import Modele.Entitees.Ressources.Ressource;
import Modele.Grille;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

/**
 * ce thread assure le réaffichage régulier de la fenetre
 */
public class ReAffichage extends Thread {

    Affichage affichage;
    InterfaceEntitee ie;

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
            affichage.revalidate();
            affichage.repaint();

            if (affichage.getEtat().getCountdown().isOver())
            {
                Window test = SwingUtilities.getWindowAncestor(affichage);
                test.dispatchEvent(new WindowEvent(test,WindowEvent.WINDOW_CLOSING));
                return;
            }
            try { Thread.sleep(20); }
            catch (Exception e) { e.printStackTrace(); }
        }
    }
}