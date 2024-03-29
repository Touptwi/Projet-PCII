package Modele.Entites.EntiteAvecInventaire.EntitesDeplacable;

import java.awt.*;
import java.util.ArrayList;

import Modele.Etat;
import Vue.AffichageDwarf.IE_Dwarf;
import Vue.AffichageDwarf.VueDwarf;

public class Dwarf extends EntiteDeplacable
{
    public Dwarf(Etat _e, Point pos)
    {
        this.interface_e = new IE_Dwarf(_e, this);
        this.affichable = new VueDwarf(this);
		this.position = pos;
		_e.getGrille().setCase(pos, this);
		this.isDwarf = true;
    }

    //On update ici simplement la position de notre nain
	@Override
	public boolean check_deplacement(Point position, ArrayList<Point> voisins)
	{
		this.position = position;
		return false;
	}

	@Override
	public void fin_deplacement(Point position, ArrayList<Point> voisins) { }
}