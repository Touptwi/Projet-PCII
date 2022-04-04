package Modele.Entites.Ressources;

import Modele.Entites.Entite;
import Modele.Etat;
import Vue.AffichageRessource.IE_Ressource;
import Vue.AffichageRessource.VueRessource;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Ressource extends Entite
{
	public enum Type { GOLD, SILVER, COPPER, ALUMINIUM, FLEUR, COUNT }
	private Type type = Type.GOLD;
	private int quantitee = 0;
	private ArrayList<BufferedImage> images = new ArrayList<>();

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
	{	/*
		String ressource_type = "";
		if(this.type == Type.GOLD) ressource_type = "Gold";
		else if(this.type == Type.SILVER) ressource_type = "Silver";
		else if(this.type == Type.COPPER) ressource_type = "Copper";
		else if(this.type == Type.ALUMINIUM) ressource_type = "Aluminium";
		else if(this.type == Type.FLEUR) ressource_type = "Fleur";
		*/
		
        return (type.name() + " " + this.quantitee);
	}

	public BufferedImage getImage() {
		BufferedImage img = new BufferedImage(10,10,0);
		try {
			img = ImageIO.read(new File("Images/Ressources/"+type.name()+"/Ingot.png"));
		} catch (IOException E) { E.printStackTrace();}
		return img;
	}
}