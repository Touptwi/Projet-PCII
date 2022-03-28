package Modele;

import java.awt.Point;
import java.util.Random;

import Modele.Entitees.EntiteeAvecInventaire.EntieesDeplacable.Goblin;
import Modele.Entitees.Ressources.Ressource;
import Modele.Entitees.Ressources.Ressource.Type;

public class TerrainGenerationThread extends Thread 
{
	private Etat etat;
	private boolean running;
	
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
			int largeur_grille = etat.getLargeurGrille();
			int hauteur_grille = etat.getHauteurGrille();
			//Cr�ation de l'objet random pour g�n�ration de nombre al�atoire
			Random rand = new Random();

			//Coordonn�es de la ressources choisies al�atoirement
			int x = rand.nextInt()%largeur_grille; x = (x < 0 ? x + largeur_grille : x);
			int y = rand.nextInt()%hauteur_grille; y = (y < 0 ? y + largeur_grille : y);

			//Si la case est valide on continue
			if(!etat.getGrille().estOccupee(x,y))
			{

				//On choisi le type de ressource de fa�on al�atoire
				int type_ord = rand.nextInt()%Type.COUNT.ordinal(); type_ord = (type_ord < 0 ? type_ord + Type.COUNT.ordinal() : type_ord);
				Type type = Type.values()[type_ord];

				//Cr�ation de la ressource
				Ressource r = new Ressource(etat, 5, type, new Point(x, y));

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
				}while (etat.getGrille().estOccupee(x,y)); // Nouvelle g�neration si case non valide

				//Intervalle de temps a attendre avant de lancer le goblin pour recuperer la ressource
				int min_t = 3;
				int max_t = 8;

				//Generation de timer al�atoire
				int t = rand.nextInt()%max_t; t = (x < 0 ? x + max_t + min_t : x + min_t);
				try { sleep(1000*t); }
    			catch (InterruptedException e) { e.printStackTrace(); }

				//Lancement du goblin
				Goblin gob = new Goblin(etat, new Point(x, y), r);
				//new Thread(gob).start();
			}

			try { sleep(1000*1); }
			catch (InterruptedException e) { e.printStackTrace(); }
		}
	}
}
