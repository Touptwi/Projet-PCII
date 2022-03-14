package Modele.Entitees.EntiteeAvecInventaire;

import Modele.Entitees.Entitee;
import Modele.Entitees.Ressources.Ressource;

public abstract class EntiteeAvecInventaire extends Entitee
{
	/** Inventaire : Liste de Ressources de chaque type avec un compteur 
	 * incremente ou decremente lors de la recuperation ou la supression de ressources
	 */
	protected Inventaire inventaire = new Inventaire();
	
	public Inventaire getInventaire() { return this.inventaire; }	
}