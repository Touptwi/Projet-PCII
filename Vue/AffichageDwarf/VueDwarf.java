package Vue.AffichageDwarf;

import Modele.Entitees.EntiteeAvecInventaire.EntieesDeplacable.Dwarf;
import Vue.Affichable;
import Vue.Affichage;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class VueDwarf implements Affichable
{

    private final Dwarf dwarf;

    private BufferedImage img;

    public VueDwarf(Dwarf dwarf)
    {
        this.dwarf = dwarf;
        try { img = ImageIO.read(new File("Images/Dwarf.png"));}
        catch (IOException E) { E.printStackTrace(); }
    }

    public void draw(Graphics g, Affichage a){
        //g.setColor(Color.BLACK);
        //g.fillOval(a.fitX(10),a.fitY(10), a.fitX(80), a.fitY(80));
        g.drawImage(img,0, 0, a.fitX(100), a.fitY(100),null);
    }
}