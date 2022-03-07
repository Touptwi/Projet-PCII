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
        Flower f = new Flower(this, new Point(0,0));
        Dwarf d = new Dwarf(this, new Point(1, 0));
        Ressource r = new Ressource(this, 5, Type.RUBIS, new Point(0, 1));
        Goblin gob = new Goblin(this, new Point(9,9), r);

        //initiation du test de la forge
        Forge forge = new Forge(this,3, new Point(3,3));
        forge.get_inventaire().put(Type.RUBIS,3);
        forge.add_recettes(new Rubis_poli());
        forge.add_recettes(new Rubis_poli());
        forge.add_recettes(new Rubis_poli());
        forge.lancer_recette(forge.get_recettes().get(0));
        forge.interface_e.mise_a_jour();
        //

        new Thread(f).start();
        new Thread(d).start();
        new Thread(gob).start();
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
