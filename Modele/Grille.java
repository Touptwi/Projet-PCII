package Modele;

import java.util.ArrayList;

public class Grille {

    /// lien objets externe ///
    private static Etat e;
    ///////////////////////////
    private final int largeur;
    private final int longueur;
    private Entitee[][] grille;

    public Grille(Etat etat, int longu, int larg)
    {
        e = etat;
        longueur = longu;
        largeur = larg;
        grille = new Entitee[longueur][largeur];

        init_grille();

    }

    public void init_grille()
    {
        for (int i = 0; i < grille.length ; i++)
        {
            for (int j = 0; j < grille[0].length; j++)
            {
                grille[i][j] = null;
            }
        }
    }


    public boolean estOccupee(int x, int y)
    {
        return grille[x][y] != null;
    }

/////// guetters et setters ////////////////////////////////////////////////////////////////////////////////////////////
    public int getLargeur()
    {
        return largeur;
    }

    public int getLongueur()
    {
        return longueur;
    }

    public Entitee getEntitee(int x, int y)
    {
        return grille[x][y];
    }

    
}
