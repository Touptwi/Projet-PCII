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
	 * TODO : Vérifier que le "synchronized" fait bien ce qu'on veut.
	 */
	
	public synchronized boolean
	move(Point f, Point t)
	{
//		System.out.print(f); //debug
//		System.out.print("  "); //debug
//		System.out.println(t); //debug
		
		if(!grille.estOccupee(f))
		{
//			System.out.println("Pas d'entitee case de départ"); //debug
			return false;
		}
		else if (grille.estOccupee(t)) 
		{
//			System.out.print("Entitee case de départ : "); //debug
//			System.out.println(grille.getEntitee(f)); //debug
//			System.out.print("Entitee case d'arrivée : "); //debug
//			System.out.println(grille.getEntitee(t)); //debug
			return false;
		}
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
	 */
	@Override
	public void
	run()
	{
		Point current_p = from;
		if(!algorithmA_star()) System.out.print("Aucun chemin trouvé");
		else
		{
			//System.out.print("A* : "); System.out.println(a_star_path);
			int idx = 0;
			
			while(idx < a_star_path.size())
			{
				Point v = a_star_path.get(idx);
				if(this.move(current_p, v)) 
				{ 
					current_p = v; //changing our position locally
					idx++; //going to read the next step
					//System.out.print("Move : "); System.out.println(v); //debug
				}
				else 
				{ 
//					System.out.print("Erreur de déplacement : "); System.out.println(v);  //debug
					from = current_p;
					if(algorithmA_star()) idx = 0;
					else 
					{
						System.out.print("Aucun chemin trouvé");
						break; 
						//TODO : Possible débat ici, est-ce vraiment bon de break dans ce cas ou faut-il attendre ?
						// -> Vérification de la distance entre la position et la destination ?
					}
				}
				try { sleep(500); } 
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
//		System.out.print("A* start (from, to) :("); //debug
//			System.out.print("["); System.out.print(from.x); System.out.print(","); System.out.print(from.y); System.out.print("]");//debug
//			System.out.print(", "); //debug
//			System.out.print("["); System.out.print(to.x); System.out.print(","); System.out.print(to.y); System.out.print("]"); //debug
//		System.out.println(")"); //debug
		
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
						if(grille.estOccupee(v)) // Modification locale dans le cas ou la case destination soit occupee
						{
							if(v.x == to.x && v.y == to.y)
							{
								this.a_star_path = retrace_chemin(u, precedents);
//								for(Point[] precs: precedents) System.out.println(Arrays.toString(precs)); //debug
								return true;
							}
						}
						else
						{
							a_voir.add(v);
							precedents[v.x][v.y] = new Point(u);							
						}
						
					}
					deja_vu[v.x][v.y] = true;
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
		if(u.x == from.x && u.y == from.y) return new ArrayList<Point>(); //Ne pas ajouter la premiere case (case de départ)
		else chemin = retrace_chemin(prec[u.x][u.y], prec); //Retracer le reste du chemin inverse
		chemin.add(u); //Ajouter la case en cours a la fin
		return chemin;
	}
}
