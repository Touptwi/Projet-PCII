package Vue;

import Modele.Flower;
import Modele.Gardener;

import java.awt.*;

public class VueGardener implements Affichable {

    private final Gardener gardener;

    public VueGardener(Gardener gardener)
    {
        this.gardener = gardener;
    }

    public void draw(Graphics g, Affichage a){
        g.setColor(Color.BLACK);
        g.fillOval(a.fitX(10),a.fitY(10), a.fitX(80), a.fitY(80));
    }
}