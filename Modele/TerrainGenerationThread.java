package Modele;

import java.awt.Point;
import java.util.Random;

import Modele.Entites.EntiteAvecInventaire.EntitesDeplacable.Goblin;
import Modele.Entites.Ressources.Flower;
import Modele.Entites.Ressources.Ressource;
import Modele.Entites.Ressources.Ressource.Type;

public class TerrainGenerationThread extends Thread 
{
	private Etat etat;
	private boolean running;
	
	//Intervalle de temps a attendre avant de lancer le goblin pour recuperer la ressource
	private final int time_wait_min = 3;
	private final int time_wait_max = 8;
	
	//Temps a attendre entre deux generations de terrain (ajoutee au temps des goblins)
	private final int spawn_cooldown = 1;
	
	//Cr�ation de l'objet random pour g�n�ration de nombre al�atoire
	Random rand = new Random();
	
	public TerrainGenerationThread(Etat e)
	{
		etat = e;
		running = true;
	}
	
	public void stop_thread() { this.running = false; }
	
	@Override
	public void run()
	{
		//Boucle de cr�ation de ressources
		while(running)
		{
			
			Point ressource_pos = getRandomPos();
			//Si la case est valide on continue
			if(!etat.getGrille().estOccupee(ressource_pos))
			{
				//On choisi le type de ressource de fa�on al�atoire
				int type_ord = rand.nextInt()%Type.COUNT.ordinal(); type_ord = (type_ord < 0 ? type_ord + Type.COUNT.ordinal() : type_ord);
				Type type = Type.values()[type_ord];

				//Cr�ation de la ressource
				Ressource r;
				if(type != Type.FLEUR) r = new Ressource(etat, 5, type, ressource_pos);
				else r = new Flower(etat, ressource_pos);
				

				//Generation de timer al�atoire
				int t = rand.nextInt()%time_wait_max; t = (t < 0 ? t + time_wait_max + time_wait_min : t + time_wait_min);
				try { sleep(1000*t); }
    			catch (InterruptedException e) { e.printStackTrace(); }

				//Lancement du goblin depuis une des bordures de l ecran
				Goblin gob = new Goblin(etat, getRandomFreeBorderPos(), r);
			}
			
			try { sleep(1000*spawn_cooldown); }
			catch (InterruptedException e) { e.printStackTrace(); }
		}
	}
	
	public Point
	getRandomPos()
	{
		int largeur_grille = etat.getLargeurGrille();
		int hauteur_grille = etat.getHauteurGrille();
		
		//Cr�ation de l'objet random pour g�n�ration de nombre al�atoire

		//Coordonn�es de la ressources choisies al�atoirement
		int x = rand.nextInt()%largeur_grille; x = (x < 0 ? x + largeur_grille : x);
		int y = rand.nextInt()%hauteur_grille; y = (y < 0 ? y + largeur_grille : y);
		
		return new Point(x,y);
	}
	
	public Point
	getRandomFreeBorderPos()
	{
		int largeur_grille = etat.getLargeurGrille();
		int hauteur_grille = etat.getHauteurGrille();
		
		int x,y;
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
		}while (etat.getGrille().estOccupee(x, y)); // Nouvelle g�neration si case non valide
		return new Point(x,y);
	}
}
