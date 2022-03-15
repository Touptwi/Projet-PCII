package Modele.Entitees.EntiteeAvecInventaire.Batiments;

import Modele.Entitees.EntiteeAvecInventaire.EntiteeAvecInventaire;
import Modele.Etat;

import java.awt.*;

import java.util.ArrayList;

public abstract class Batiment extends EntiteeAvecInventaire 
{

    protected final ArrayList<Point> structure; //les déplacement necessaire pour creer le bâtiment

    private Etat etat;

    public Batiment(Etat _e, ArrayList<Point> s)
    {
        structure = s;
        etat = _e;
    }

    public Etat getEtat()
    {
        return etat;
    }

    public ArrayList<Point> getStructure() 
    {
        return structure;
    }
}