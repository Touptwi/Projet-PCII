package Modele;

import java.awt.*;

import java.util.ArrayList;

public abstract class Batiment extends Entitee {

    private final ArrayList<Point> structure = new ArrayList<Point>(); //les déplacement necessaire pour
    private ArrayList<Point> action;
    private Grille grille;

    public Batiment(Grille g)
    {
        grille = g;
    }
    private boolean check(ArrayList<Point> array, Point value)
    {
        for (int i = 0; i < structure.size(); i++)
        {
            if (structure.get(i) == value)
                return true;
        }
        return false;
    }

    /**
     * indique à la grille quelle case remplir avec pour origine le point actuellement selectionné par la grille  et en
     * fonction de la structure du batiment
     */
    public void create_batiment()
    {
        Point origine = new Point(grille.getSelectionX(),grille.getSelectionY());
        ArrayList<Point> a_construire = getStructure();
        for (int i = 0; i< a_construire.size(); i++)
        {
            Point point = new Point(origine.x + a_construire.get(i).x, origine.y + a_construire.get(i).y);
            if (((point.x < 0 || point.y < 0) && (point.x > grille.getLargeur() || point.y > grille.getLongueur())) || grille.estOccupee(point) )
            {
                return;
            }
            grille.setCase(point, this);
        }
    }
    public ArrayList<Point> getStructure() {
        return structure;
    }

}
