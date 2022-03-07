package Vue;

import Modele.Goblin;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class VueGoblin implements Affichable {

    private final Goblin goblin;

    private BufferedImage img;

    public VueGoblin(Goblin gob){
        this.goblin = gob;
        try { img = ImageIO.read(new File("Images/Goblin.png"));}
        catch (IOException E) { E.printStackTrace(); }
    }

    public void draw(Graphics g, Affichage a){
        g.drawImage(img,10, 20, a.fitX(80), a.fitY(80),null);
    }
}