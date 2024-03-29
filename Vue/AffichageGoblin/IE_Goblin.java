package Vue.AffichageGoblin;

import Modele.Etat;
import Modele.Entites.EntiteAvecInventaire.EntitesDeplacable.Goblin;
import Vue.IE_inventaire;
import Vue.InterfaceEntite;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Arrays;

public class IE_Goblin implements InterfaceEntite {

    Goblin goblin;
    JPanel interface_graphique;
    IE_inventaire inventaire = new IE_inventaire();

    public IE_Goblin(Etat e, Goblin gob)
    {
        this.goblin = gob;
        this.interface_graphique = new JPanel();
        interface_graphique.setLayout(new BoxLayout(interface_graphique, BoxLayout.PAGE_AXIS));
        interface_graphique.add(new JLabel(new ImageIcon("Images/Goblin/Goblin.png")));

        //Inventaire : Affichage de l'inventaire du gobelin
        //inventaire = new JTextField(Arrays.toString(goblin.getInventaire().getInventaire()), 20);
        //inventaire.setEditable(false);

        interface_graphique.add(inventaire.getInterface());
    }

    @Override
    public JPanel getJPanel()
    {
        return this.interface_graphique;
    }

    @Override
    public void mise_a_jour() {

        //inventaire.setText(Arrays.toString(goblin.getInventaire().getInventaire()));
        interface_graphique.remove(inventaire.getInterface());
        inventaire.mise_a_jour_inventaire(goblin);
        interface_graphique.add(inventaire.getInterface());
    }
}