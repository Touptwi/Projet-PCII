package Modele.Entitees.EntiteeAvecInventaire.EntieesDeplacable;

import Modele.Deplacement;
import Modele.Entitees.EntiteeAvecInventaire.EntiteeAvecInventaire;

import java.awt.Point;
import java.util.ArrayList;

public abstract class EntiteeDeplacable extends EntiteeAvecInventaire
{
	Deplacement deplacement;
	
	public void setDeplacement(Deplacement depl) { this.deplacement = depl; }
	public Deplacement getDeplacement() { return this.deplacement; }
	
	public abstract boolean check_deplacement(Point position, ArrayList<Point> voisins);
	public abstract void fin_deplacement(Point position, ArrayList<Point> voisins);
	public abstract void fuir(Point pos);
}