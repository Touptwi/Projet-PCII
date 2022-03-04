package Vue;

import Modele.Etat;
import Modele.Goblin;

import javax.swing.*;
import java.util.Arrays;

public class IE_Goblin implements InterfaceEntitee {

    Goblin goblin;
    JPanel interface_graphique;
    JTextField inventaire;

    public IE_Goblin(Etat e, Goblin gob)
    {
        this.goblin = gob;
        this.interface_graphique = new JPanel();

        //Inventaire : Affichage de l'inventaire du gobelin
        inventaire = new JTextField(Arrays.toString(goblin.getInventaire()), 20);
        inventaire.setEditable(false);

        interface_graphique.add(inventaire);
    }

    @Override
    public JPanel getJPanel()
    {
        return this.interface_graphique;
    }

    @Override
    public void mise_a_jour() {
        inventaire.setText(Arrays.toString(goblin.getInventaire()));
    }

}
