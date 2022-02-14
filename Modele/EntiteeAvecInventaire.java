package Modele;

import java.util.Arrays;

public abstract class EntiteeAvecInventaire extends Entitee
{
	private Ressource[] inventaire = new Ressource[Ressource.Type.COUNT.ordinal()];
	
	public Ressource[] getInventaire() { return this.inventaire; }
	
	public void addToInventaire(Ressource r) 
	{ 
		Ressource inInvRess = this.inventaire[r.getType().ordinal()];
		if(inInvRess == null) this.inventaire[r.getType().ordinal()] = new Ressource(null, r.getQuantitee(), r.getType());
		else inInvRess.setQuantitee(inInvRess.getQuantitee() + r.getQuantitee());
//		System.out.print(Arrays.toString(inventaire));
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
