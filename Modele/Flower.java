package Modele;

import java.awt.*;

public class Flower extends Entitee implements Runnable
{
    private int durability = 10;
    private Color color = Color.red;

    public Flower() 
    {
    	
    }

    public void run() 
    {
        while(durability > 0)
        {
            durability--;
            if(durability < 5 && color == Color.red) 
            {
                color = Color.orange;
            } 
            else if (durability<3 && color == Color.orange)
            {
                color = Color.yellow;
            }
            
            try { Thread.sleep(100); } 
            catch (Exception e) { e.printStackTrace(); }
        }
    }

    public Color getColor() { return color; }

    public int getDurability() { return durability; }
}
