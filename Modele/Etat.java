package Modele;

public class Etat {

    private final int largeur;
    private final int hauteur;
    
    private final int largeur_grille = 10;
    private final int hauteur_grille = 10;

    private Grille grille;

    public Etat(int l, int h) {
        largeur = l;
        hauteur = h;
        grille = new Grille(this, largeur_grille, hauteur_grille);
    }

    public int getLargeur() {
        return largeur;
    }

    public int getHauteur() {
        return hauteur;
    }
    
    public int getLargeurGrille() { return largeur_grille; }
    public int getHauteurGrille() { return hauteur_grille; }
    public Grille getGrille() { return this.grille; }
    
    
    public Entitee getEntitee(int i, int j)
    {
        return grille.getEntitee(i,j);
    }
}
