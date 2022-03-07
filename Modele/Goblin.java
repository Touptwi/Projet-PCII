package Modele;

import Vue.IE_Goblin;
import Vue.VueGoblin;

import java.awt.*;
import java.util.ArrayList;

public class Goblin extends EntiteeAvecInventaire implements Runnable
{

    Ressource ressource;
    Etat etat;

    public Goblin(Etat e, Point pos, Ressource r)
    {
        this.interface_e = new IE_Goblin(e, this);
        this.affichable = new VueGoblin(this);
        this.position = pos;
        this.ressource = r;
        this.etat = e;
        e.getGrille().setCase(pos, this);
        new Deplacement(this, pos, r.getPosition(),e.getGrille()).start();
    }

    /** Prendre la ressource si atteint puis fuit*/
    public void takeRessource(Point pos, ArrayList<Point> voisins) {
        if(voisins.contains(ressource.getPosition())) {
            this.addToInventaire(ressource);
            doEscape(pos, voisins);
        }
    }

    /** Rentre d'où il vient */
    public void doEscape(Point pos, ArrayList<Point> voisins) {
            new Deplacement(this, pos, position, etat.getGrille()).start();
    }

    @Override
    public void run()
    {
        while(true)
        {
            try { Thread.sleep(100); }
            catch (Exception e) { e.printStackTrace(); }
        }
    }
}
