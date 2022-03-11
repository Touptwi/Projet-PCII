package Modele.Entitees.Batiments;

import Modele.Entitees.Ressources.Ressource;
import Vue.IE_Recette;

import java.util.*;

public abstract class Recette {

    private Dictionary<Ressource.Type,Integer> ingredients = new Hashtable<Ressource.Type, Integer>();
    //les ingredients de la recette sont stockés dans un dictionnaire de la forme
    //Type de l'ingredient -> nb necessaire

    private String nom;

    private int temps = 0; //indique le temps de réalisation d'une recette (utilise par les threads des bâtiments)

    IE_Recette ie = new IE_Recette(this); //l'interface (cette interface est utilisé comme sous interface de IE_Forge)

    //Guetters et Setters ///////////////////////////////////////////////////

    protected void add_ingredient(Ressource.Type ing,int nb)
    {
        ingredients.put(ing,nb);
    }

    /**
     * renvoie la liste des objets necessaires pour réaliser la recette sous la forme d'une chaine de caractère
     * @return String la liste des objets necessaires
     */
    public String get_ingredients_string()
    {
        String result = "";
        Enumeration<Ressource.Type> clefs = ingredients.keys();
        while(clefs.hasMoreElements())
        {
            Ressource.Type ressource = clefs.nextElement();
            if (ingredients.get(ressource) != 0)
            {
                result = result + ressource.toString() + ": " + ingredients.get(ressource) + "\n";
            }
        }
        return result;
    }

    public int getTemps() {
        return temps;
    }

    protected void setTemps(int temps) {
        this.temps = temps;
    }

    public String getNom() {
        return nom;
    }

    protected void setNom(String nom) {
        this.nom = nom;
    }

    public IE_Recette getRecetteIE()
    {
        return ie;
    }

    /////////////////////////////////////////////////////////////////////////

    /**
     * verifie qu'un inventaire donné contient les élément necessaire pour réaliser la recette
     * @param inventaire un inventaire de bâtiment sous la forme d'un dictionnaire Ressource.type -> quantité
     * @return true si l'inventaire contient les ressources nécessaire et false sinon
     */
    public boolean check(Dictionary<Ressource.Type,Integer> inventaire)
    {
        Enumeration<Ressource.Type> liste_ingredients = ingredients.keys();

        while (liste_ingredients.hasMoreElements())
        {
            Ressource.Type ing = liste_ingredients.nextElement();
            if (ingredients.get(ing) > inventaire.get(ing))
            {
                return false;
            }
        }
        return true;
    }

    /**
     * retire les ingredients necessaire à la recette à un inventaire donné
     * @param inventaire l'inventaire dont on veut retirer les éléments
     * /!\ Cette fonction n'est pas sécurisé et ne verifie pas que l'inventaire possède assez d'élément pour la recette
     * si ce n'est pas le cas l'inventaire possèdera des quantité négatives
     */
    public void produire(Dictionary<Ressource.Type,Integer> inventaire)
    {
        Enumeration<Ressource.Type> liste_ingredients = ingredients.keys();
        while (liste_ingredients.hasMoreElements())
        {
            Ressource.Type ing = liste_ingredients.nextElement();
            inventaire.put(ing,inventaire.get(ing) - ingredients.get(ing));
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Recette)) return false;
        Recette recette = (Recette) o;
        return temps == recette.temps && ingredients.equals(recette.ingredients) && getNom().equals(recette.getNom());
    }

    @Override
    public int hashCode() {
        return Objects.hash(ingredients, temps, getNom());
    }
}