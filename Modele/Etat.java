package Modele;

import java.awt.Point;
import java.util.Random;

import Modele.Entitees.*;
import Modele.Entitees.EntiteeAvecInventaire.Batiments.Forge;
import Modele.Entitees.EntiteeAvecInventaire.EntieesDeplacable.Dwarf;
import Modele.Entitees.EntiteeAvecInventaire.EntieesDeplacable.EntiteeDeplacable;
import Modele.Entitees.EntiteeAvecInventaire.EntieesDeplacable.Goblin;
import Modele.Entitees.Ressources.Ressource;
import Modele.Entitees.Ressources.Ressource.Type;
import Modele.Entitees.Ressources.Flower;

public class Etat
{
	public enum Modes { SELECTION, DEPLACEMENT }
	
	Modes mode_courant = Modes.SELECTION;
    private final int largeur;
    private final int hauteur;
    
    private final int largeur_grille = 10;
    private final int hauteur_grille = 10;

    private int score = 0;

    private Grille grille;

    TerrainGenerationThread dynamic_spawn_thread;

	public Countdown getCountdown() {
		return countdown;
	}

	Countdown countdown;

    public Etat(int l, int h) 
    {
        largeur = l;
        hauteur = h;
        grille = new Grille(this, largeur_grille, hauteur_grille);
        //Flower f = new Flower(this, new Point(0,0));
        Dwarf d1 = new Dwarf(this, new Point(1, 0));
        Dwarf d2 = new Dwarf(this, new Point(2, 0));

		//Generation terrain
		Stone s1 = new Stone(this, new Point(7,6));
		Stone s2 = new Stone(this, new Point(8,6));
		Stone s3 = new Stone(this, new Point(6,7));

        //initiation du test de la forge
        Forge forge = new Forge(this,3, new Point(3,3));
        forge.getInventaire().add(new Ressource(this, 5, Type.GOLD));

        //new Thread(f).start();
        dynamic_spawn_thread = new TerrainGenerationThread(this);
        dynamic_spawn_thread.start();
        
        countdown = new Countdown(this, 300);
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
				EntiteeDeplacable selected = (EntiteeDeplacable) this.grille.getSelectedEntitee();
				Deplacement d = selected.getDeplacement();
				Point depart = this.grille.getSelectionPosition();
				if(d != null) depart = d.stop_thread();
				d = new Deplacement(selected, depart, new Point(x,y), grille);
				selected.setDeplacement(d);
				d.start();
				this.mode_courant = Modes.SELECTION;
			} break;
    	}
    }

    public Entitee getEntitee(int i, int j)
    {
        return grille.getEntitee(i,j);
    }

    public void addScore(int n_score)
    {
        score = score + n_score;
        System.out.println("score:" + score);
    }

	public int getScore()
	{
		return score;
	}
	
	public void stop()
	{
		System.out.print("Stopping the game loop now");
		this.dynamic_spawn_thread.stop_thread();
		
	}
}