package Vue.AffichageFlower;

import Modele.Etat;
import Modele.Entitees.Ressources.Flower;
import Vue.InterfaceEntitee;

import javax.swing.*;
import java.awt.*;

public class IE_Plante implements InterfaceEntitee {
    private Flower flower;
    private JPanel interface_graphique_princ;
    private Canvas icone;
    private JSplitPane separateur;
    private JProgressBar barre;

    //Variable

    private int Durabilite_max;
    


    public IE_Plante(Etat _e, Flower f)
    {
        //recupération des variables utiles à l'interface
        flower = f;
        Durabilite_max = f.getDurability();

        //création de la fenetre générale de l'interface
        interface_graphique_princ = new JPanel();

        //generation de l'icone
        icone = new Canvas();
        icone.setSize(new Dimension(60,60));

        //initialisation de la barre
        barre = new JProgressBar();
        barre.setMinimum(0);
        barre.setMaximum(100);

        //mise en page via un SPLITSPANE
        separateur = new JSplitPane(JSplitPane.VERTICAL_SPLIT, icone, barre);
        interface_graphique_princ.add(separateur);
    }


    @Override
    public JPanel getJPanel() {
        return interface_graphique_princ;
    }


    /**
     * met a jour la barre de progression d'après l'avancée de la fleur
     */
    @Override
    public void mise_a_jour()
    {
        int n_valeur = 100 - (flower.getDurability()*100/Durabilite_max) ;
        barre.setValue(n_valeur);
        if (n_valeur >= 100)
        {
            interface_graphique_princ.setVisible(false);
        }
    }
}