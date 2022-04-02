package Modele.Entites.EntiteAvecInventaire.EntitesDeplacable;

import Modele.Deplacement;
import Modele.Entites.EntiteAvecInventaire.EntiteAvecInventaire;

import java.awt.Point;
import java.util.ArrayList;

public abstract class EntiteDeplacable extends EntiteAvecInventaire
{
	Deplacement deplacement;
	
	public void setDeplacement(Deplacement depl) { this.deplacement = depl; }
	public Deplacement getDeplacement() { return this.deplacement; }
	
	public abstract boolean check_deplacement(Point position, ArrayList<Point> voisins);
	public abstract void fin_deplacement(Point position, ArrayList<Point> voisins);
	public abstract void fuir(Point pos);
}