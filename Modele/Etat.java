package Modele;

public class Etat {

    private final int largeur;
    private final int hauteur;

    public Etat(int l, int h) {
        largeur = l;
        hauteur = h;
    }

    public int getLargeur() {
        return largeur;
    }

    public int getHauteur() {
        return hauteur;
    }
}
