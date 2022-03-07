package Modele;

import java.awt.*;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;

public abstract class Batiment extends Entitee {

    protected final ArrayList<Point> structure; //les déplacement necessaire pour creer le bâtiment
    protected Dictionary<Ressource.Type,Integer> inventaire = new Hashtable<Ressource.Type,Integer>();

    public Batiment(ArrayList<Point> s)
    {
        structure = s;
    }

    /**
     * va initialiser toutes les clefs de l'inventaire
     * Cette fonction DOIT être appelée à la creation d'un bâtiment utilisant un inventaire
     */
    protected void init_inventaire()
    {
        for (Ressource.Type i: Ressource.Type.values())
        {
            inventaire.put(i,0);
        }
    }

    /**
     * cette fonction va récupérer tous les éléments d'un inventaire d'un nain, les ajouter à l'inventaire du bâtiments
     * et les supprimer de l'inventaire du nain
     * @param inventaire_externe l'inventaire à transvaser /!\Celui-ci est modifier à la fin de la fonction
     */
    public void add_inventaire(Ressource[] inventaire_externe)
    {
        for (int i = 0; i < inventaire_externe.length; i++)
        {
            Ressource element = inventaire_externe[i];
            inventaire.put(element.getType(),inventaire.get(element.getType()) + 1);
        }
    }

    public Dictionary<Ressource.Type,Integer> get_inventaire()
    {
        return inventaire;
    }

    public ArrayList<Point> getStructure() {
        return structure;
    }

}
