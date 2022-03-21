package Modele.Entitees;

import Modele.Etat;
import Vue.VueStone;

import java.awt.*;

public class Stone extends Entitee{
    
    public Stone(Etat _e, Point pos) {
        this.affichable = new VueStone(this);
        this.position = pos;
        _e.getGrille().setCase(pos, this);
    }
}
