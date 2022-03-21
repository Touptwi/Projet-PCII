package Vue.AffichageRessource;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import Modele.Entitees.Ressources.Ressource;
import Vue.Affichable;
import Vue.Affichage;

import javax.imageio.ImageIO;

public class VueRessource implements Affichable
{
	private final Ressource ressource;

    private BufferedImage img;

    public VueRessource(Ressource r)
    {
        this.ressource = r;
        try { img = ImageIO.read(new File("Images/Ressources/"+ressource.getType()+"/Ore.png"));}
        catch (IOException E) { E.printStackTrace(); }
    }

    public void draw(Graphics g, Affichage a){
        g.drawImage(img,a.fitX(10), a.fitY(10), a.fitX(80), a.fitY(80),null);
    }
}