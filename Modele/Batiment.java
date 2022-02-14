package Modele;

import java.awt.*;

import java.util.ArrayList;

public abstract class Batiment extends Entitee {

    private final ArrayList<Point> structure = new ArrayList<Point>(); //les déplacement necessaire pour
    private ArrayList<Point> action;

    /**
     * Inutile pour l'instant
     * @param array
     * @param value
     * @return
     */
    private boolean check(ArrayList<Point> array, Point value)
    {
        for (int i = 0; i < structure.size(); i++)
        {
            if (structure.get(i) == value)
                return true;
        }
        return false;
    }

    public ArrayList<Point> getStructure() {
        return structure;
    }

}
