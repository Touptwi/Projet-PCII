package Modele;

import java.awt.Point;

import Modele.Ressource.Type;

public class Etat {

	public enum Modes { SELECTION, DEPLACEMENT }
	
	Modes mode_courant = Modes.SELECTION;
    private final int largeur;
    private final int hauteur;
    
    private final int largeur_grille = 10;
    private final int hauteur_grille = 10;

    private Grille grille;

    public Etat(int l, int h) {
        largeur = l;
        hauteur = h;
        grille = new Grille(this, largeur_grille, hauteur_grille);
        Flower f = new Flower(this);
        Dwarf d = new Dwarf(this);
        Ressource r = new Ressource(this, 5, Type.RUBIS);
        grille.setCase(0, 0, f);
        grille.setCase(1,0,d);
        grille.setCase(new Point(0, 1), r);
        new Thread(f).start();
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
    
    public void setMode(Modes mode) { this.mode_courant = mode; }

    /**
     * au clic va, en fonction du mode courant selectionner ou lancé le déplacement d'une entitée
     * @param x la coordonnée x de la case cliqué
     * @param y la coordonnée y de la case cliqué
     */
    public void
    click(int x, int y)
    {
    	switch(mode_courant)
    	{
    		case SELECTION: 
    		{
    			this.grille.selectionne(x, y); 
    		} break;
    		
    		case DEPLACEMENT: 
			{
				(new Deplacement(this.grille.getSelectedEntitee(), this.grille.getSelectionPosition(), new Point(x,y), grille)).start(); 
				this.mode_courant = Modes.SELECTION;
			} break;
    	}
    }
    
    
    public Entitee getEntitee(int i, int j)
    {
        return grille.getEntitee(i,j);
    }
    
    
}
