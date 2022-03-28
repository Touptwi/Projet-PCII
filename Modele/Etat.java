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

    Thread dynamic_spawn_thread;

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


        Etat _e = this;
        dynamic_spawn_thread =
    		new Thread()
		    {
		    	@Override
		    	public void run()
		    	{
		    		//Boucle de cr�ation de ressources
		    		while(true)
		    		{
		    			//Cr�ation de l'objet random pour g�n�ration de nombre al�atoire
		    			Random rand = new Random();

		    			//Coordonn�es de la ressources choisies al�atoirement
		    			int x = rand.nextInt()%largeur_grille; x = (x < 0 ? x + largeur_grille : x);
		    			int y = rand.nextInt()%hauteur_grille; y = (y < 0 ? y + largeur_grille : y);

		    			//Si la case est valide on continue
		    			if(!_e.getGrille().estOccupee(x,y))
		    			{

		    				//On choisi le type de ressource de fa�on al�atoire
		    				int type_ord = rand.nextInt()%Type.COUNT.ordinal(); type_ord = (type_ord < 0 ? type_ord + Type.COUNT.ordinal() : type_ord);
		    				Type type = Type.values()[type_ord];

		    				//Cr�ation de la ressource
		    				Ressource r = new Ressource(_e, 5, type, new Point(x, y));

		    				do
		    				{
		    					//Choix de coordonn�es : les goblins apparaissent a un des bords du terrain, en hauteur ou largeur al�atoirement
		    					if(rand.nextBoolean())
		    					{
		    						//Choix de coordonn�es al�atoires
		    						x = rand.nextInt()%largeur_grille; x = (x < 0 ? x + largeur_grille : x);
		    						y = (rand.nextBoolean() ? 0 : hauteur_grille-1);
		    					}
		    					else
		    					{
		    						//Choix de coordonn�es al�atoires
		    						x = (rand.nextBoolean() ? 0 : largeur_grille-1);
		    						y = rand.nextInt()%hauteur_grille; y = (y < 0 ? y + largeur_grille : y);
		    					}
		    				}while (_e.getGrille().estOccupee(x,y)); // Nouvelle g�neration si case non valide

		    				//Intervalle de temps a attendre avant de lancer le goblin pour recuperer la ressource
		    				int min_t = 3;
		    				int max_t = 8;

		    				//Generation de timer al�atoire
		    				int t = rand.nextInt()%max_t; t = (x < 0 ? x + max_t + min_t : x + min_t);
		    				try { sleep(1000*t); }
			    			catch (InterruptedException e) { e.printStackTrace(); }

		    				//Lancement du goblin
		    				Goblin gob = new Goblin(_e, new Point(x, y), r);
		    				//new Thread(gob).start();
		    			}

		    			try { sleep(1000*1); }
		    			catch (InterruptedException e) { e.printStackTrace(); }
		    		}
		    	}
			};
        this.dynamic_spawn_thread.start();
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
}