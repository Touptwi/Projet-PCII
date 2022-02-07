package Vue;

import Modele.Flower;

import java.awt.*;

public class VueFlower implements Affichable {

    private final Flower flower;

    public VueFlower(Flower f){
        flower = f;
    }

    public void draw(Graphics g){
        g.setColor(flower.getColor());
        g.fillOval(10,10, 80, 80);
    }
}
