package Vue;

import Controleur.Control;
import Modele.Etat;

import javax.swing.*;
import java.awt.*;

public class Affichage extends JPanel {

    private final Etat etat;

    public Affichage(Etat e) {

        etat = e;

        JFrame frame = new JFrame("FlowerCraft");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setPreferredSize(new Dimension(etat.getHauteur() , etat.getLargeur()));
        this.addMouseListener(new Control(etat, this));
        this.setFocusable(true);

        frame.add(this);
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        for(int i = 0; i <= 10; i++) {
            g.drawLine( 0, 100*i, 100*10,100*i );
            g.drawLine( 100*i, 0, 100*i, 100*10);
        }

        g.setColor(new Color(250, 0, 0, 255));
        g.fillOval(10,10,80,80);
    }
}
