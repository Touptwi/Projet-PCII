package Modele.Entitees.EntiteeAvecInventaire.Batiments;

import Modele.Entitees.EntiteeAvecInventaire.Inventaire;
import Modele.Entitees.Ressources.Ressource;
import Vue.IE_Recette;

import java.util.*;

public class Recette {

	
	//les ingredients de la recette sont stockés dans un dictionnaire de la forme
	//Type de l'ingredient -> nb necessaire
//    private Dictionary<Ressource.Type,Integer> ingredients = new Hashtable<Ressource.Type, Integer>();

	private Inventaire ingredients;

	private String nom;

    private int temps = 0; //indique le temps de réalisation d'une recette (utilise par les threads des bâtiments)

    IE_Recette ie =null; //new IE_Recette(this); //l'interface (cette interface est utilisé comme sous interface de IE_Forge)

    public Recette(String nom, Inventaire ingredients, int t)
    {
        this.nom = nom;
        this.ingredients = ingredients;
        this.temps = t;
    }

    //Guetters et Setters ///////////////////////////////////////////////////

    protected void add_ingredient(Ressource r)
    {
        ingredients.add(r);
    }

    /**
     * renvoie la liste des objets necessaires pour réaliser la recette sous la forme d'une chaine de caractère
     * @return String la liste des objets necessaires
     */
    public String get_ingredients_string()
    {
        String result = "";
        for(Ressource r : ingredients)
        {
        	if(r != null) result += r.toString();
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
    public boolean check(Inventaire inventaire)
    {
    	for(int i = 0; i < Ressource.Type.COUNT.ordinal(); i++)
    	{
	          if (ingredients.get(i) != null 
	        		  && ( inventaire.get(i) == null || ingredients.get(i).getQuantitee() > inventaire.get(i).getQuantitee()))
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
    public void produire(Inventaire inventaire)
    {
        for(int i = 0; i < Ressource.Type.COUNT.ordinal(); i++)
    	{
	          inventaire.remove(ingredients.get(i));
    	}
    }

    @Override
    public boolean equals(Object o) 
    {
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