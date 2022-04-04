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
	
	/**
	 * Methode check deplacement appelee pour chaque entitee deplacable apres un deplacement
	 * @param position : la position courante
	 * @param voisins : les voisins de la positions
	 * @return vrai : si et seulement si l'entitee veut finir son deplacement courant
	 */
	public abstract boolean check_deplacement(Point position, ArrayList<Point> voisins);

	/**
	 * Methode fin deplacement appelee pour chaque entitee deplacable a la fin d'un deplacement
	 * @param position : la position courante
	 * @param voisins : les voisins de la positions
	 */
	public abstract void fin_deplacement(Point position, ArrayList<Point> voisins);
}