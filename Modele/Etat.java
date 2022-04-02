package Modele;

import java.awt.Point;

import Modele.Entites.*;
import Modele.Entites.EntiteAvecInventaire.Batiments.Forge;
import Modele.Entites.EntiteAvecInventaire.EntitesDeplacable.Dwarf;
import Modele.Entites.EntiteAvecInventaire.EntitesDeplacable.EntiteDeplacable;
import Modele.Entites.Ressources.Ressource;
import Modele.Entites.Ressources.Ressource.Type;

public class Etat
{
	public enum Modes { SELECTION, DEPLACEMENT }
	
	Modes mode_courant = Modes.SELECTION;
    private final int largeur;
    private final int hauteur;
    private final int duree;
    
    private final int largeur_grille = 10;
    private final int hauteur_grille = 10;

    private int score = 0;

    private Grille grille;

    TerrainGenerationThread dynamic_spawn_thread;

	public Countdown getCountdown() {
		return countdown;
	}

	Countdown countdown;

    public Etat(int l, int h,int d)
    {
        largeur = l;
        hauteur = h;
        duree = d;
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
        
        countdown = new Countdown(this, duree);
        System.out.println("le jeu se terminera dans 5 minutes");
    }

    public int getLargeur() {
        return largeur;
    }

    public int getHauteur() {
        return hauteur;
    }

    public int getDuree() {return duree; }
    
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
    	switch(mode_courant) //recupère le mode actuel
    	{
    		case SELECTION:  //on demande à la grille de selectionner la case à l'emplacement du click
    		{
    			this.grille.selectionne(x, y); 
    		} break;
    		
    		case DEPLACEMENT: //lorsque l'utilisateur appuie
			{
				EntiteDeplacable selected = (EntiteDeplacable) this.grille.getSelectedEntitee();
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

    public Entite getEntitee(int i, int j)
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

    /**
     * Arrête la génération d'ennemis en arrêtant dynamic_spawn_thread
     */
	public void stop()
	{
		System.out.print("Stopping the game loop now");
		this.dynamic_spawn_thread.stop_thread();
		
	}
}