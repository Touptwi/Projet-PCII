package Vue;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;

public class VueForge implements Affichable{

    Image[] img = new Image[3];
    int appel = 0;

    public VueForge()
    {
        for (int i = 0; i < 3 ; i++)
        {
            try {
                img[i] = ImageIO.read(new File("Images/furnace"+ ((i%2) + 1) +".png"));
            } catch (IOException E) {
                E.printStackTrace();
            }
        }
    }

    @Override
    public void draw(Graphics g, Affichage a) {
        g.drawImage(img[appel],10, 20, a.fitX(80), a.fitY(80),null);
        appel = (appel + 1)%3;
    }
}