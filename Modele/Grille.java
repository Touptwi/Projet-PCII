package Modele;

import java.awt.Point;
import java.util.ArrayList;

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
    
    /** Verifie si une case donnee est occupee par une entitee
     * @param p : placement de la case
     * @return vrai si une entitee est placee sur la case donnee, faux sinon
     */
    public boolean estOccupee(Point p)
    {
    	return grille[p.x][p.y] != null;
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
    
    public Point getSelectionPosition()
    {
    	return new Point(selectionX, selectionY);
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
    
    
    /** Place une entitee dans une case
     * @param to : case ou placer l'entitee
     * @param val : Entitee a placer 
     */
    public void setCase(Point to, Entitee val)
    {
    	grille[to.x][to.y] = val;
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
        //System.out.print(String.format("x,y : %d,%d", x, y)); //debug
        
    }

    /**
     * rend l'entitée situé sur la case de coordonnée X Y ssi elle est affichable
     * @param x la coord X
     * @param y la coord Y
     * @return rend L'entitée située sur la case et null si il n'y a rien à ces coordonnées
     */
    public Entitee getEntitee(int x, int y)
    {
        if (grille[x][y] != null && grille[x][y].getSprite() == null) { grille[x][y] = null; }
        return grille[x][y];
    }
    
    /** Renvoie l'entitee situee a la case donee ssi elle est affichable
     * @param from : Case contenant l'entitee
     * @return Entitee a la case donee
     */
    public Entitee getEntitee(Point from)
    {
        if (grille[from.x][from.y] != null && grille[from.x][from.y].getSprite() == null) { grille[from.x][from.y] = null; }
        return grille[from.x][from.y];
    }

    /**
     * rend l'entitée située sur la case selectionnée
     * @return Une entitée si celle-ci se trouve sur la case selectionnée et null sinon
     */
    public Entitee getSelectedEntitee()
    {
        return getEntitee(selectionX, selectionY);
    }

    public void create_batiment(Batiment bat)
    {
        Point origine = new Point(selectionX,selectionY);
        ArrayList<Point> a_construire = bat.getStructure();
        for (int i = 0; i< a_construire.size(); i++)
        {
            Point point = new Point(origine.x + a_construire.get(i).x, origine.y + a_construire.get(i).y);
            if (((point.x < 0 || point.y < 0) && (point.x > largeur || point.y > longueur)) || estOccupee(point) )
            {
                return;
            }
            setCase(point, bat);
        }
    }
}
