package Controleur.ButtonListeners;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Modele.Entitees.Entitee;
import Modele.Entitees.EntiteeAvecInventaire.Batiments.Forge;
import Modele.Entitees.EntiteeAvecInventaire.EntieesDeplacable.Dwarf;
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
        Grille g = etat.getGrille();
        for (Point p:g.getVoisins(dwarf.position))
        {
            Entitee entitee = g.getEntitee(p);
            if (entitee instanceof Forge)
            {
                dwarf.getInventaire().transfert(((Forge) entitee).getInventaire());
                return;
            }
        }
    }
}