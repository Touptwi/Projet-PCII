package Modele.Entites.Ressources;

import Modele.Etat;
import Vue.AffichageFlower.IE_Plante;
import Vue.AffichageFlower.VueFlower;

import java.awt.*;

public class Flower extends Ressource implements Runnable
{
    private int durability = 100;
    private Color color = Color.red;

    public Flower(Etat _e, Point pos)
    {
    	super(_e, 5, Ressource.Type.FLEUR, pos);
        this.interface_e = new IE_Plante(_e, this);
    	this.affichable = new VueFlower(this);
        this.position = pos;
        _e.getGrille().setCase(pos, this);
        
        Thread th = new Thread(this);
        th.start();
    }

    /**
     * Methode run
     * Met a jour la durabilite de la fleur en fonction du temps, 
     * change aussi la couleur et la quantitee de la ressource en fonction du temps qui passe
     */
    public void run() 
    {
        while(durability > 0)
        {
            durability--;
            if(durability < 70 && color == Color.red)
            {
                color = Color.orange;
                this.setQuantitee(1);
            } 
            else if (durability<40 && color == Color.orange)
            {
                color = Color.yellow;
                this.setQuantitee(3);
            }
            
            try { Thread.sleep(200); }
            catch (Exception e) { e.printStackTrace(); }
        }
        affichable = null;
    }

    public Color getColor() { return color; }

    public int getDurability() { return durability; }
}