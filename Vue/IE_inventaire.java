package Vue;

import Modele.Entites.EntiteAvecInventaire.EntiteAvecInventaire;
import Modele.Entites.EntiteAvecInventaire.Inventaire;
import Modele.Entites.Ressources.Ressource;

import javax.swing.*;

public class IE_inventaire {

    JPanel inventaire = new JPanel(); //l'interface de l'inventaire

    public JPanel getInterface()
    {
        return inventaire;
    }

    /**
     * met a jour le jpanel inventaire en fonction de celui de l'entite transmise
     * @param e une EntiteAvecInventaire
     * /!\ le jpanel stocké dans inventaire avant l'appel à mise_a_jour_inventaire est détruit
     */
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

    public void mise_a_jour_inventaire(Inventaire i)
    {
        inventaire = new JPanel();
        for(Ressource r:i)
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
