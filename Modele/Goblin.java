package Modele;

import Vue.IE_Goblin;
import Vue.VueGoblin;

import java.awt.*;
import java.util.ArrayList;

public class Goblin extends EntiteeAvecInventaire implements Runnable
{

    Ressource ressource;

    public Goblin(Etat e, Point pos, Ressource r)
    {
        this.interface_e = new IE_Goblin(e, this);
        this.affichable = new VueGoblin(this);
        this.position = pos;
        this.ressource = r;
        e.getGrille().setCase(pos, this);
        new Deplacement(this, pos, r.getPosition(),e.getGrille()).start();
    }

    /** Prendre la ressource si atteint */
    public void takeRessource(ArrayList<Point> voisins) {
        if(voisins.contains(ressource.getPosition())) {
            this.addToInventaire(ressource);
        }
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
