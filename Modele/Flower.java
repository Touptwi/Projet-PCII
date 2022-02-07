package Modele;

import Vue.VueFlower;

import java.awt.*;

public class Flower extends Entitee implements Runnable
{
    private int durability = 10;
    private Color color = Color.red;

    public Flower() 
    {
    	affichable = new VueFlower(this);
    }

    public void run() 
    {
        while(durability > 0)
        {
            durability--;
            if(durability < 7 && color == Color.red)
            {
                color = Color.orange;
                System.out.printf("JE SUIS ORANGE !\n");
            } 
            else if (durability<4 && color == Color.orange)
            {
                color = Color.yellow;
                System.out.printf("JE SUIS JAUNE !!\n");
            }
            
            try { Thread.sleep(2000); }
            catch (Exception e) { e.printStackTrace(); }
        }
        color = null;
        System.out.printf("JE SUIS MORT ;-;\n");
    }

    public Color getColor() { return color; }

    public int getDurability() { return durability; }
}
