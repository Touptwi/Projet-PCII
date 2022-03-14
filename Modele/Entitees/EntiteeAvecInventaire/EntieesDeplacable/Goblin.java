package Modele.Entitees.EntiteeAvecInventaire.EntieesDeplacable;

import Modele.Deplacement;
import Modele.Entitees.Ressources.Ressource;
import Modele.Etat;
import Vue.AffichageGoblin.IE_Goblin;
import Vue.AffichageGoblin.VueGoblin;

import java.awt.*;
import java.util.ArrayList;

public class Goblin extends EntiteeDeplacable implements Runnable
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
            etat.getGrille().recupererRessources(this);
        }
        fuir(pos);
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

	@Override
	public boolean check_deplacement(Point position, ArrayList<Point> voisins)
	{
        for(Point voisin : voisins) {
            if(etat.getGrille().getEntitee(voisin) != null) {
                if (etat.getGrille().getEntitee(voisin).isDwarf) {
                    this.isFeared= true;
                    fuir(position);
                    return true;
                }
            }
        }
        return false;
	}

	@Override
	public void fin_deplacement(Point position, ArrayList<Point> voisins) 
	{
        if(!isFeared) {
            takeRessource(position, voisins);
        }
	}

    /** Rentre d'où il vient */
	@Override
	public void fuir(Point pos)
	{
        new Deplacement(this, pos, position, etat.getGrille()).start();
	}
}