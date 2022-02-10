package Modele;

import Vue.IE_Fleur;
import Vue.VueFlower;

import java.awt.*;

public class Flower extends Entitee implements Runnable
{
    private int durability = 10;
    private Color color = Color.red;

    public Flower(Etat _e) 
    {
    	this.interface_e = new IE_Fleur(_e, this);
    	this.affichable = new VueFlower(this);
    }

    public void run() 
    {
        while(durability > 0)
        {
            durability--;
            if(durability < 7 && color == Color.red)
            {
                color = Color.orange;
            } 
            else if (durability<4 && color == Color.orange)
            {
                color = Color.yellow;
            }
            
            try { Thread.sleep(2000); }
            catch (Exception e) { e.printStackTrace(); }
        }
        affichable = null;
    }

    public Color getColor() { return color; }

    public int getDurability() { return durability; }
}
