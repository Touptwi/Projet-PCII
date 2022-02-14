package Modele;

import java.util.ArrayList;

public abstract class EntiteeAvecInventaire extends Entitee
{
	private Ressource[] inventaire = new Ressource[Ressource.Type.COUNT.ordinal()];
	
	public Ressource[] getInventaire() { return this.inventaire; }

	public void addToInventaire(Ressource r) 
	{ 
		Ressource inInvRess = this.inventaire[r.getType().ordinal()];
		if(inInvRess == null) inInvRess = new Ressource(null, 0, r.getType());
		int quantitee = inInvRess.getQuantitee() + r.getQuantitee();
		inInvRess.setQuantitee(quantitee); 
	}
	
	public boolean removeFromInventaire(Ressource r) 
	{ 
		Ressource inInvRess = this.inventaire[r.getType().ordinal()];
		if(inInvRess == null) return false;
		int quantitee = inInvRess.getQuantitee() - r.getQuantitee();
		if(quantitee >= 0) inInvRess.setQuantitee(quantitee);
		else return false;		
		return true;
	}
	
}
