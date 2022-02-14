package Modele;

import Vue.Affichable;
import Vue.IE_Ressource;
import Vue.VueRessource;

public class Ressource extends Entitee
{
	public enum Type { OR, RUBIS, EMERAUDE, SAPHIR, COUNT }
	private Type type = Type.OR;
	private int quantitee = 0;
	
	
	
	public Ressource(Etat _e, int q, Type t) 
	{ 
		quantitee = q; 
		type = t; 
		this.interface_e = new IE_Ressource(_e, this);
		this.affichable = new VueRessource(this);
	}
	
	public Ressource(Etat _e, int q) 
	{ 
		quantitee = q;
		this.interface_e = new IE_Ressource(_e, this);
	}
	
	public Ressource(Etat _e, Type t) 
	{ 
		type = t; 
		this.interface_e = new IE_Ressource(_e, this);
	}
	
	public Type getType() { return this.type; }
	public int getQuantitee() { return this.quantitee; }
	
	public void setType(Type t) { this.type = t; }
	public void setQuantitee(int q) { this.quantitee = q; }
}
