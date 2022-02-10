package Vue;

import Modele.Flower;

import java.awt.*;

public class VueFlower implements Affichable {

    private final Flower flower;

    public VueFlower(Flower f){
        flower = f;
    }

    public void draw(Graphics g, Affichage a){
        g.setColor(flower.getColor());
        g.fillOval(a.fitX(10),a.fitY(10), a.fitX(80), a.fitY(80));
    }
}
