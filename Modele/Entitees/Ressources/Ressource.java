package Modele.Entitees.Ressources;

import Modele.Entitees.Entitee;
import Modele.Etat;
import Vue.AffichageRessource.IE_Ressource;
import Vue.AffichageRessource.VueRessource;

import java.awt.*;

public class Ressource extends Entitee
{
	public enum Type { OR, RUBIS, EMERAUDE, SAPHIR, FLEUR, COUNT }
	private Type type = Type.OR;
	private int quantitee = 0;

	public Ressource(Etat _e, int q, Type t, Point pos)
	{
		quantitee = q;
		type = t;
		this.interface_e = new IE_Ressource(_e, this);
		this.affichable = new VueRessource(this);
		this.position = pos;
		_e.getGrille().setCase(pos, this);
	}

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
	
	@Override
	public String 
	toString()
	{	
		String ressource_type = "";
		if(this.type == Type.OR) ressource_type = "or";
		else if(this.type == Type.RUBIS) ressource_type = "rubis";
		else if(this.type == Type.EMERAUDE) ressource_type = "emeraude";
		else if(this.type == Type.SAPHIR) ressource_type = "saphir";
		else if(this.type == Type.FLEUR) ressource_type = "fleur";
		
        return (ressource_type + " " + this.quantitee);
	}
}