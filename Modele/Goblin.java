package Modele;

import Vue.IE_Goblin;
import Vue.VueGoblin;

import java.awt.*;

public class Goblin extends EntiteeAvecInventaire implements Runnable
{

    public Goblin(Etat e, Point pos, Ressource r)
    {
        this.interface_e = new IE_Goblin(e, this);
        this.affichable = new VueGoblin(this);
        this.position = pos;
        e.getGrille().setCase(pos, this);
        new Deplacement(this, pos, r.getPosition(),e.getGrille()).start();
    }

    @Override
    public void run()
    {
        while(true)
        {
            interface_e.mise_a_jour();
            try { Thread.sleep(100); }
            catch (Exception e) { e.printStackTrace(); }
        }
    }
}
