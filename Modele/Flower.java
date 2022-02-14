package Modele;

import Vue.IE_Dwarf;
import Vue.IE_Plante;
import Vue.VueFlower;

import javax.swing.event.ChangeEvent;
import java.awt.*;

public class Flower extends Entitee implements Runnable
{
    private int durability = 10;
    private Color color = Color.red;

    public Flower(Etat _e) 
    {
        this.interface_e = new IE_Plante(_e, this);
    	this.affichable = new VueFlower(this);
    }

    public void run() 
    {
        while(durability > 0)
        {
            durability--;
            interface_e.mise_a_jour();
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
