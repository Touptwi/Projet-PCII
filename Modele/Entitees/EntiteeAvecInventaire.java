package Modele.Entitees;

import Modele.Entitees.Ressources.Ressource;

public abstract class EntiteeAvecInventaire extends Entitee
{
	/** Inventaire : Liste de Ressources de chaque type avec un compteur 
	 * incremente ou decremente lors de la recuperation ou la supression de ressources
	 */
	private Ressource[] inventaire = new Ressource[Ressource.Type.COUNT.ordinal()];
	
	public Ressource[] getInventaire() { return this.inventaire; }

	/** Ajoute la ressource donnee a l'inventaire de l'entitee
	 * @param r : Ressource a mettre dans l'inventaire
	 */
	public void addToInventaire(Ressource r) 
	{ 
		Ressource inInvRess = this.inventaire[r.getType().ordinal()];
		if(inInvRess == null) this.inventaire[r.getType().ordinal()] = new Ressource(null, r.getQuantitee(), r.getType());
		else inInvRess.setQuantitee(inInvRess.getQuantitee() + r.getQuantitee());
//		System.out.print(Arrays.toString(inventaire)); //debug
	}
	
	/** Supprime la ressource donnee de l'inventaire
	 * @param r : Ressource a enlever
	 * @return : Reusite de l'operation (test de quantitee suffisante ?)
	 */
	public boolean removeFromInventaire(Ressource r) 
	{ 
		Ressource inInvRess = this.inventaire[r.getType().ordinal()];
		if(inInvRess == null) return false;
		int quantitee = inInvRess.getQuantitee() - r.getQuantitee();
		if(quantitee < 0) return false;		
		else inInvRess.setQuantitee(quantitee);
		return true;
	}
	
}