package Vue;

import Modele.Entites.EntiteAvecInventaire.EntiteAvecInventaire;
import Modele.Entites.Ressources.Ressource;

import javax.swing.*;

public class IE_inventaire {

    JPanel inventaire = new JPanel();

    public JPanel getInterface()
    {
        return inventaire;
    }

    public void mise_a_jour_inventaire(EntiteAvecInventaire e)
    {
        inventaire = new JPanel();
        for(Ressource r:e.getInventaire().getInventaire())
        {
            if(r!= null && r.getQuantitee() > 0)
            {
                inventaire.add(new JLabel(new ImageIcon("Images/Ressources/" + r.getType().name() + "/Ingot.png","description")));
                //System.out.println(r.toString());
                inventaire.add(new JLabel(r.toString()));
            }
        }
    }
}
