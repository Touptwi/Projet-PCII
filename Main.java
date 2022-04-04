import Modele.Etat;
import Vue.Affichage;

public class Main {

    //Modele
    public static final int largeur = 1400;
    public static final int hauteur = 1000;

    public static void main(String[] args) 
    {
        Etat etat = new Etat(largeur, hauteur,600);
        Affichage affichage = new Affichage(etat);
    }
}