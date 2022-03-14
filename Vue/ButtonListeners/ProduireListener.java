package Vue.ButtonListeners;

import Modele.Entitees.EntiteeAvecInventaire.Batiments.Recette;
import Modele.Entitees.EntiteeAvecInventaire.Batiments.Forge;
import Vue.InterfaceEntitee;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProduireListener implements ActionListener {

    InterfaceEntitee ie_forge;
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