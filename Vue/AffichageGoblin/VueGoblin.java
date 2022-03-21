package Vue.AffichageGoblin;

import Modele.Entitees.EntiteeAvecInventaire.EntieesDeplacable.Goblin;
import Modele.Entitees.EntiteeAvecInventaire.Inventaire;
import Vue.Affichable;
import Vue.Affichage;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class VueGoblin implements Affichable {

    private Goblin goblin;

    private BufferedImage img;
    private BufferedImage img_;

    public VueGoblin(Goblin gob){
        this.goblin = gob;
        try {
            img_ = ImageIO.read(new File("Images/Goblin/Goblin_.png"));
            img = ImageIO.read(new File("Images/Goblin/Goblin.png"));
        }
        catch (IOException E) { E.printStackTrace(); }
    }

    public void draw(Graphics g, Affichage a){
        if(goblin.getInventaire().isVide()) {g.drawImage(img_, a.fitX(10), a.fitY(20), a.fitX(80), a.fitY(80), null);}
        else                                {g.drawImage(img, a.fitX(10), a.fitY(20), a.fitX(80), a.fitY(80), null);}
    }
}