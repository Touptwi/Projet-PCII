package Vue;

import java.awt.Color;
import java.awt.Graphics;


import Modele.Ressource;

public class VueRessource implements Affichable
{
	//TODO : 
	// 	- Charger la bonne texture a la construction
	// 	- Probablement pas besoin de pointeur sur la ressource ? (A voir)
	private final Ressource ressource;

    public VueRessource(Ressource r)
    {
        this.ressource = r;
    }

    public void draw(Graphics g, Affichage a){
        g.setColor(Color.YELLOW);
        g.fillOval(a.fitX(10),a.fitY(10), a.fitX(80), a.fitY(80));
        //g.drawImage(img,0, 0, a.fitX(100), a.fitY(100),null);
    }
}
