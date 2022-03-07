package Vue.ButtonListeners;

import Modele.Recette;
import Modele.forge;
import Vue.IE_Forge;
import Vue.InterfaceEntitee;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProduireListener implements ActionListener {

    InterfaceEntitee ie_forge;
    forge forge;
    Recette recette;
    public ProduireListener(forge f, Recette r)
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
