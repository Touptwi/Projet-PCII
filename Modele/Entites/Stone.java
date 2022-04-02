package Modele.Entites;

import Modele.Etat;
import Vue.VueStone;

import java.awt.*;

public class Stone extends Entite {
    
    public Stone(Etat _e, Point pos) {
        this.affichable = new VueStone(this);
        this.position = pos;
        _e.getGrille().setCase(pos, this);
    }
}
