package Modele;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;

public class Deplacement extends Thread
{
	protected Entitee e;
	protected Grille grille;
	protected Point from;
	protected Point to;
	
	protected ArrayList<Point> a_star_path;
	
	public 
	Deplacement(Entitee _e, Point _from, Point _to, Grille _grille)
	{
		this.grille = _grille;
		this.e = _e;
		this.from = _from;
		this.to = _to;
	}
	
	/**
	 * Fonction utilitaire permettant le deplacement de l'entitée e de f à t avec vérification
	 * @param f case "from" de départ
	 * @param t case "to" d'arrivée
	 * @return vrai si le move a été effectué et était correct, faux sinon
	 */
	
	public boolean
	move(Point f, Point t)
	{
		if(!grille.estOccupee(f) || grille.estOccupee(t)) return false;
		else
		{
			grille.setCase(f, null);
			grille.setCase(t, e);
			return true;
		}
	}
	
	/**
	 * Procedure run de déplacement d'un entitée
	 * Lance A* et déplace l'entitée le long du chemin qu'elle a à parcourir
	 * Vérifie que les mouvements sont corrects
	 * TODO : relancer A* et synchroniser/section critique en cas d'erreur ?
	 * TODO : arrêt avant la dernière case si celle ci devient occupée
	 */
	@Override
	public void
	run()
	{
		Point current_p = from;
		if(!algorithmA_star()) System.out.print("Aucun chemin trouvé");
		else
		{
			System.out.print("A* : "); System.out.println(a_star_path);
			for(Point v : a_star_path)
			{
				if(this.move(current_p, v)) 
				{ 
					current_p = v; 
					//System.out.print("Move : "); System.out.println(v); //debug
				}
				else 
				{ 
					System.out.print("Erreur de déplacement : "); System.out.println(v); 
				}
				try { sleep(1000); } 
				catch (InterruptedException e) { e.printStackTrace(); }
			}			
		}
	}
	
	/**
	 * Algorithme A* (sans heuristiques : cases carrées de même dimensions)
	 * Trace un chemin du point "from" au point "to" si il existe et le stocke dans le champs "a_star_path"
	 * @return true si et seulement si un chemin est trouvé
	 */
	public boolean
	algorithmA_star() 
	{
		System.out.print("A* start (from, to) :("); //debug
			System.out.print("["); System.out.print(from.x); System.out.print(","); System.out.print(from.y); System.out.print("]");//debug
			System.out.print(", "); //debug
			System.out.print("["); System.out.print(to.x); System.out.print(","); System.out.print(to.y); System.out.print("]"); //debug
		System.out.println(")"); //debug
		
		ArrayList<Point> a_voir = new ArrayList<Point>();
		a_voir.add(from);

		boolean deja_vu[][] = new boolean[grille.getLongueur()][grille.getLargeur()];
		Point precedents[][] = new Point[grille.getLongueur()][grille.getLargeur()];
		
		while(!a_voir.isEmpty())
		{
			Point u = a_voir.get(0); a_voir.remove(u); //dépiler
			if(u.x == to.x && u.y == to.y) //Si on est sur la case destination, retracer le chemin
			{
				this.a_star_path = retrace_chemin(u, precedents);
//				for(Point[] precs: precedents) System.out.println(Arrays.toString(precs)); //debug
				return true;
			}
			else //Sinon explorer les voisins en leur sauvegardant leur père (le point courrant)
			{
//				System.out.print("Current :"); //debug
//				System.out.print(u); //debug
//				System.out.print("Voisins :"); //debug
//				System.out.println(grille.getVoisins(u)); //debug
				for(Point v : grille.getVoisins(u)) 
				{
					if(!deja_vu[v.x][v.y])
					{
						deja_vu[v.x][v.y] = true;
						a_voir.add(v);
						precedents[v.x][v.y] = new Point(u);
					}
				}
			}
		}
		// Cas où il n'y a pas de chemin possible
		return false;
	}
	
	/**
	 * Retrace le chemin a partir de notre dernier point et la liste des parents des points
	 * On utilise ici le dernier point pour pouvoir faire des appels recursifs :
	 * On obtiendra alors une liste dans le "bon sens", avec le début du chemin en indice 0
	 * (On estime donc ici que le cout en temps est plus important que celui en mémoire)
	 * @param u : le dernier point du chemin
	 * @param prec : la liste des parents de chaque points
	 * @return le chemin obtenu en reliant les points trouvés
	 */
	public ArrayList<Point>
	retrace_chemin(Point u, Point[][] prec)
	{
		ArrayList<Point> chemin;
		if(u.x != from.x || u.y != from.y) chemin = retrace_chemin(prec[u.x][u.y], prec);
		else return new ArrayList<Point>();
		chemin.add(u);
		return chemin;
	}
}
