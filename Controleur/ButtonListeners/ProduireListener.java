package Controleur.ButtonListeners;

import Modele.Entites.EntiteAvecInventaire.Batiments.Recette;
import Modele.Entites.EntiteAvecInventaire.Batiments.Forge;
import Vue.InterfaceEntite;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProduireListener implements ActionListener {

    InterfaceEntite ie_forge;
    Forge forge;
    Recette recette;
    public ProduireListener(Forge f, Recette r)
    {
        ie_forge = f.interface_e;
        forge = f;
        recette = r ;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        forge.lancer_recette(recette);
    }
}