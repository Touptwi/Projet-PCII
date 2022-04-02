package Vue;

import Modele.Entites.Stone;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class VueStone implements Affichable {

    private final Stone stone;

    private BufferedImage img;

    public VueStone(Stone stone){
        this.stone = stone;
        try { img = ImageIO.read(new File("Images/Stone.png"));}
        catch (IOException E) { E.printStackTrace(); }
    }

    public void draw(Graphics g, Affichage a){
            g.drawImage(img,a.fitX(10), a.fitY(10), a.fitX(80), a.fitY(80),null);
        }
}
