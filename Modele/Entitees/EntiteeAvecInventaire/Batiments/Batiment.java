package Modele.Entitees.EntiteeAvecInventaire.Batiments;

import Modele.Entitees.EntiteeAvecInventaire.EntiteeAvecInventaire;

import java.awt.*;

import java.util.ArrayList;

public abstract class Batiment extends EntiteeAvecInventaire 
{

    protected final ArrayList<Point> structure; //les déplacement necessaire pour creer le bâtiment

    public Batiment(ArrayList<Point> s)
    {
        structure = s;
    }

    public ArrayList<Point> getStructure() 
    {
        return structure;
    }
}