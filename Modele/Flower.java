package Modele;

import Vue.IE_Plante;
import Vue.VueFlower;

import javax.swing.event.ChangeEvent;
import java.awt.*;

public class Flower extends Ressource implements Runnable
{
    private int durability = 100;
    private Color color = Color.red;

    public Flower(Etat _e, Point pos)
    {
    	super(_e, 1, Ressource.Type.FLEUR, pos);
        this.interface_e = new IE_Plante(_e, this);
    	this.affichable = new VueFlower(this);
        this.position = pos;
        _e.getGrille().setCase(pos, this);
    }

    public void run() 
    {
        while(durability > 0)
        {
            durability--;
            interface_e.mise_a_jour(); //utilisé pour assurer que la barre de progression de l'interfae suit la durabilité
            if(durability < 70 && color == Color.red)
            {
                color = Color.orange;
            } 
            else if (durability<40 && color == Color.orange)
            {
                color = Color.yellow;
            }
            
            try { Thread.sleep(200); }
            catch (Exception e) { e.printStackTrace(); }
        }
        affichable = null;
    }

    public Color getColor() { return color; }

    public int getDurability() { return durability; }

}
