package Vue.AffichageFlower;

import Modele.Entites.Ressources.Flower;
import Vue.Affichable;
import Vue.Affichage;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


/**
 * interface liée aux fleur
 */
public class VueFlower implements Affichable {

    private final Flower flower;

    private BufferedImage img1;
    private BufferedImage img2;
    private BufferedImage img3;

    public VueFlower(Flower f){
        flower = f;
        try {
            img1 = ImageIO.read(new File("Images/Ressources/FLEUR/Ore.png"));
            img2 = ImageIO.read(new File("Images/Ressources/FLEUR/Ore2.png"));
            img3 = ImageIO.read(new File("Images/Ressources/FLEUR/Ore3.png"));
        }
        catch (IOException E) { E.printStackTrace(); }
    }

    public void draw(Graphics g, Affichage a){
        //g.setColor(flower.getColor());
        //g.fillOval(a.fitX(10),a.fitY(10), a.fitX(80), a.fitY(80));
        if(flower.getColor()==Color.red) {
            g.drawImage(img1, 0, 0, a.fitX(100), a.fitY(100), null);
        } else if(flower.getColor()==Color.orange) {
            g.drawImage(img2, 0, 0, a.fitX(100), a.fitY(100), null);
        } else if(flower.getColor()==Color.yellow) {
            g.drawImage(img3, 0, 0, a.fitX(100), a.fitY(100), null);
        }
    }
}