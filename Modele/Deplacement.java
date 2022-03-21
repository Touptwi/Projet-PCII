package Modele;

import Modele.Entitees.EntiteeAvecInventaire.EntieesDeplacable.EntiteeDeplacable;

import java.awt.Point;
import java.util.ArrayList;

public class Deplacement extends Thread
{
	protected EntiteeDeplacable e;
	protected Grille grille;
	protected Point from;
	protected Point to;
	
	protected Point current_p;
	
	protected ArrayList<Point> a_star_path;
	
	boolean shouldQuit = false;
	
	public 
	Deplacement(EntiteeDeplacable _e, Point _from, Point _to, Grille _grille)
	{
		this.grille = _grille;
		this.e = _e;
		this.from = _from;
		this.to = _to;
	}
	
	/**
	 * Procedure run de d�placement d'un entit�e
	 * Lance A* et d�place l'entit�e le long du chemin qu'elle a � parcourir
	 * V�rifie que les mouvements sont corrects
	 */
	@Override
	public void
	run()
	{
		current_p = from;
		if(!algorithmA_star()) System.out.print("Aucun chemin trouv�");
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
					e.check_deplacement(current_p,grille.getVoisins(current_p));
					//System.out.print("Move : "); System.out.println(v); //debug
				}
				else
				{
					if(e.check_deplacement(current_p,grille.getVoisins(current_p))) {
						break;
					}
//					System.out.print("Erreur de d�placement : "); System.out.println(v);  //debug
					from = current_p;
					if(algorithmA_star()) idx = 0;
					else
					{
						System.out.print("Aucun chemin trouv�");
						break;
						//TODO : Possible d�bat ici, est-ce vraiment bon de break dans ce cas ou faut-il attendre ?
						// -> V�rification de la distance entre la position et la destination ?
					}
				}
				try { sleep(500); } 
				catch (InterruptedException e) { e.printStackTrace(); }
				if(shouldQuit) break;
			}
			e.check_deplacement(current_p,grille.getVoisins(current_p));
			e.fin_deplacement(current_p,grille.getVoisins(current_p));
		}
	}
	
	public Point
	stop_thread()
	{
		this.shouldQuit = true;
		return this.current_p;
	}
	
	/**
	 * Fonction utilitaire permettant le deplacement de l'entit�e e de f � t avec v�rification
	 * @param f case "from" de d�part
	 * @param t case "to" d'arriv�e
	 * @return vrai si le move a �t� effectu� et �tait correct, faux sinon
	 * TODO : V�rifier que le "synchronized" fait bien ce qu'on veut.
	 */
	
	public synchronized boolean
	move(Point f, Point t)
	{
//		System.out.print(f); //debug
//		System.out.print("  "); //debug
//		System.out.println(t); //debug
		
		if(!grille.estOccupee(f))
		{
//			System.out.println("Pas d'entitee case de d�part"); //debug
			return false;
		}
		else if (grille.estOccupee(t)) 
		{
//			System.out.print("Entitee case de d�part : "); //debug
//			System.out.println(grille.getEntitee(f)); //debug
//			System.out.print("Entitee case d'arriv�e : "); //debug
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
	 * Algorithme A* (sans heuristiques : cases carr�es de m�me dimensions)
	 * Trace un chemin du point "from" au point "to" si il existe et le stocke dans le champs "a_star_path"
	 * @return true si et seulement si un chemin est trouv�
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
			Point u = a_voir.get(0); a_voir.remove(u); //d�piler
			if(u.x == to.x && u.y == to.y) //Si on est sur la case destination, retracer le chemin
			{
				this.a_star_path = retrace_chemin(u, precedents);
//				for(Point[] precs: precedents) System.out.println(Arrays.toString(precs)); //debug
				return true;
			}
			else //Sinon explorer les voisins en leur sauvegardant leur p�re (le point courrant)
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
		// Cas o� il n'y a pas de chemin possible
		return false;
	}
	
	/**
	 * Retrace le chemin a partir de notre dernier point et la liste des parents des points
	 * On utilise ici le dernier point pour pouvoir faire des appels recursifs :
	 * On obtiendra alors une liste dans le "bon sens", avec le d�but du chemin en indice 0
	 * (On estime donc ici que le cout en temps est plus important que celui en m�moire)
	 * @param u : le dernier point du chemin
	 * @param prec : la liste des parents de chaque points
	 * @return le chemin obtenu en reliant les points trouv�s
	 */
	public ArrayList<Point>
	retrace_chemin(Point u, Point[][] prec)
	{
		ArrayList<Point> chemin;
		if(u.x == from.x && u.y == from.y) return new ArrayList<Point>(); //Ne pas ajouter la premiere case (case de d�part)
		else chemin = retrace_chemin(prec[u.x][u.y], prec); //Retracer le reste du chemin inverse
		chemin.add(u); //Ajouter la case en cours a la fin
		return chemin;
	}
}