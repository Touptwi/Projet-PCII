package Modele;

public class Grille {

    /// lien objets externe ///
    private static Etat e;
    ///////////////////////////
    private final int largeur;
    private final int longueur;

    // coordonnées de la case selectionnée
    private int selectionX;
    private int selectionY;

    private Entitee[][] grille;

    public Grille(Etat etat, int longu, int larg)
    {
        e = etat;
        longueur = longu;
        largeur = larg;
        grille = new Entitee[longueur][largeur];

        init_grille();

    }

    /**
     * initialise toutes les cases de la grille à null
     */
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

    /**
     * bouge l'objet sur la case sélectionnée vers la case de coordonnée x y (si celle-ci n'est pas occupée)
     * @param x coordonnée X de la case d'arrivée
     * @param y coordonnée Y de la case d'arrivée
     * @return true si le mouvement a pu etre effectué, false sinon
     */
    public boolean mouvement(int x, int y)
    {
        if (estOccupee(getSelectionX(),getSelectionY()) && !estOccupee(x,y))
        {
            grille[x][y] = getSelectedEntitee();
            grille[selectionX][selectionY] = null;
            return true;
        }
        return false;

    }

    /**
     * verifie si une entitée se trouve sur la case
     * @param x la coordonnée X de la case étudiée
     * @param y la coordonnée Y de la case étudiée
     * @return true si elle est occupée et false sinon
     */
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

    public int getSelectionX()
    {
        return selectionX;
    }

    public int getSelectionY()
    {
        return selectionY;
    }

    /**
     * change la valeur d'une case du tableau
     * @param x la coordonnée X de la case
     * @param y la coordonnée Y de la case
     * @param val la nouvelle valeur de la case
     */
    public void setCase(int x, int y, Entitee val)
    {
        grille[x][y] = val;
    }

    /**
     * va marque la case de coordonnée x y comme selectionnée
     * @param x coord X
     * @param y coord Y
     */
    public void selectionne(int x, int y)
    {
        selectionX = x;
        selectionY = y;
    }

    /**
     * rend l'entitée situé sur la case de coordonnée X Y
     * @param x la coord X
     * @param y la coord Y
     * @return rend L'entitée située sur la case et null si il n'y a rien à ces coordonnées
     */
    public Entitee getEntitee(int x, int y)
    {
        return grille[x][y];
    }

    /**
     * rend l'entitée située sur la case selectionnée
     * @return Une entitée si celle-ci se trouve sur la case selectionnée et null sinon
     */
    public Entitee getSelectedEntitee()
    {
        return getEntitee(selectionX, selectionY);
    }
    
}
