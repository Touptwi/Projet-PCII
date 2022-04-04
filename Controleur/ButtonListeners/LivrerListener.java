package Controleur.ButtonListeners;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Modele.Entites.Entite;
import Modele.Entites.EntiteAvecInventaire.Batiments.Forge;
import Modele.Entites.EntiteAvecInventaire.EntitesDeplacable.Dwarf;
import Modele.Etat;
import Modele.Grille;

public class LivrerListener implements ActionListener
{
    Etat etat;
    Dwarf dwarf;

    public
    LivrerListener(Etat _e, Dwarf _d)
    {
        this.etat = _e;
        this.dwarf = _d;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        System.out.println("livraison depuis la case: " + dwarf.position);
        Grille g = etat.getGrille();
        for (Point p:g.getVoisins(dwarf.position))
        {
            Entite entitee = g.getEntitee(p);
            if (entitee instanceof Forge)
            {
                System.out.println("forge trouvée");
                dwarf.getInventaire().transfert(((Forge) entitee).getInventaire());
                return;
            }
        }
    }
}