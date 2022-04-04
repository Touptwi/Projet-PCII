package Modele.Entites;

import Modele.Etat;
import Vue.InterfaceEntite;
import Vue.VueStone;

import javax.swing.*;
import java.awt.*;

public class Stone extends Entite {
    
    public Stone(Etat _e, Point pos) {
        this.interface_e = new InterfaceEntite() {
            @Override
            public JPanel getJPanel() {
                return new JPanel();
            }

            @Override
            public void mise_a_jour() {

            }
        };
        this.affichable = new VueStone(this);
        this.position = pos;
        _e.getGrille().setCase(pos, this);
    }
}
