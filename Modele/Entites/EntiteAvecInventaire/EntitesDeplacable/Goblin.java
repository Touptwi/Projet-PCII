package Modele.Entites.EntiteAvecInventaire.EntitesDeplacable;

import Modele.Deplacement;
import Modele.Entites.Ressources.Ressource;
import Modele.Etat;
import Vue.AffichageGoblin.IE_Goblin;
import Vue.AffichageGoblin.VueGoblin;

import java.awt.*;
import java.util.ArrayList;

public class Goblin extends EntiteDeplacable
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
        this.deplacement = new Deplacement(this, pos, r.getPosition(),e.getGrille(), false);
        this.deplacement.start();
    }

    /** Prendre la ressource si atteint puis fuit*/
    public void takeRessource(Point pos, ArrayList<Point> voisins) {
    	if (voisins.contains(ressource.getPosition())) {
    		etat.getGrille().recupererRessources(this);
    	}
    	this.isFeared = true;
    }

	@Override
	public boolean check_deplacement(Point position, ArrayList<Point> voisins)
	{
        if(!isFeared) {
            for (Point voisin : voisins) {
                if (etat.getGrille().getEntitee(voisin) != null) {
                    if (etat.getGrille().getEntitee(voisin).isDwarf) {
                        //takeRessource(position, voisins);
                        return true;
                    }
                }
            }
        }
        return false;
	}

	@Override
	public void fin_deplacement(Point position, ArrayList<Point> voisins) 
	{
        if(!isFeared) 
        {
            takeRessource(position, voisins);
        }
        else if(etat.getGrille().getEntitee(position) == this)
        {
            etat.getGrille().setCase(position.x, position.y, null);
        }else{
            //System.out.println("la position " + position + "n'est pas la bonne");
        }
	}
}