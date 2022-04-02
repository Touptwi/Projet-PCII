package Modele.Entites.EntiteAvecInventaire;

import Modele.Entites.Entite;

public abstract class EntiteAvecInventaire extends Entite
{
	/** Inventaire : Liste de Ressources de chaque type dont le dernier élément est un compteur
	 * incremente ou decremente lors de la recuperation ou la supression de ressources
	 */
	protected Inventaire inventaire = new Inventaire();
	
	public Inventaire getInventaire() { return this.inventaire; }	
}