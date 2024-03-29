package Modele;

import Modele.Entites.EntiteAvecInventaire.EntitesDeplacable.EntiteDeplacable;

import java.awt.Point;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Deplacement extends Thread
{
	protected EntiteDeplacable e;
	protected Grille grille;
	protected Point from;
	protected Point to;
	
	protected Point current_p;
	
	protected ArrayList<Point> a_star_path;
	
	boolean shouldQuit = false;
	
	boolean parcourRetour = true;
	
	public 
	Deplacement(EntiteDeplacable _e, Point _from, Point _to, Grille _grille)
	{
		this.grille = _grille;
		this.e = _e;
		this.from = _from;
		this.to = _to;
	}
	
	//Ajout de pR (pour goblin) indicant la presence d'un retour
	public 
	Deplacement(EntiteDeplacable _e, Point _from, Point _to, Grille _grille, boolean pR)
	{
		this.grille = _grille;
		this.e = _e;
		this.from = _from;
		this.to = _to;
		this.parcourRetour = pR;
	}
	
	
	/**
	 * Procedure run d'un deplacement : 
	 * lance le parcour d'un chemin dans un sens 
	 * verifie ensuite si l'on doit faire un retour et le lance si c'est le cas
	 */
	@Override
	public void
	run()
	{
		lance_chemin();
		if(!this.parcourRetour)
		{
			//update des positions pour le retour
			to = from;
			from = current_p;
			this.shouldQuit = false;
			lance_chemin();
		}
	}
	
	public Point
	stop_thread()
	{
		this.shouldQuit = true;
		return this.current_p;
	}
	
	/**
	 * Procedure lance_chemin de d�placement d'un entit�e
	 * Lance A* et d�place l'entit�e le long du chemin qu'elle a � parcourir
	 * V�rifie que les mouvements sont corrects
	 */
	public void 
	lance_chemin()
	{
		current_p = from;
		if(!algorithmA_star()) System.out.print("Aucun chemin trouv�");
		else {
			//System.out.print("A* : "); System.out.println(a_star_path);
			int idx = 0;

			while (idx < a_star_path.size() && !shouldQuit) {
				Point v = a_star_path.get(idx);
				if (this.move(current_p, v)) {
					current_p = v; //changing our position locally
					idx++; //going to read the next step
					if (e.check_deplacement(current_p, grille.getVoisins(current_p))) {//si le check déplacement a réussi (cas goblein: si le gobelin a croisé un ennemis)
						System.out.println("sortie demandé par check déplacement");
						this.stop_thread();
						//System.out.print("Move : ");
						//System.out.println(v); //debug
					}
				} else { //si le mouvement a echoué
					if (e.check_deplacement(current_p, grille.getVoisins(current_p))) {//si le check déplacement a réussi (cas goblein: si le gobelin a croisé un ennemis)
						System.out.println("sortie demandé par check déplacement");
						this.stop_thread();
					} else {
						System.out.print("Erreur de d�placement : ");
						//System.out.println(v);  //debug
						from = current_p;
						if (algorithmA_star()) idx = 0;
						else {
							System.out.print("Aucun chemin trouv�");
							break;
							//TODO : Possible d�bat ici, est-ce vraiment bon de break dans ce cas ou faut-il attendre ?
							// -> V�rification de la distance entre la position et la destination ?
						}
					}
				}
				try {
					sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			//e.check_deplacement(current_p, grille.getVoisins(current_p));

			System.out.println("fin déplacement");
		}
		e.fin_deplacement(current_p, grille.getVoisins(current_p));
	}
	/**
	 * Fonction utilitaire permettant le deplacement de l'entite e de f vers t avec verification
	 * @param f case "from" de depart
	 * @param t case "to" d'arrivée
	 * @return vrai si le move a put être effectué (aucune des case n'était occupées), faux sinon
	 */
	
	public synchronized boolean
	move(Point f, Point t)
	{
		return grille.move(f,t);
	}

	/**
	 * Algorithme A* (sans heuristiques : cases carrées de même dimension)
	 * Trace un chemin du point "from" au point "to" s'il existe et le stocke dans l'objet "a_star_path"
	 * @return true si et seulement si un chemin est trouvé
	 */
	public boolean parcours_profondeur()
	{
//		System.out.print("A* start (from, to) :("); //debug
//			System.out.print("["); System.out.print(from.x); System.out.print(","); System.out.print(from.y); System.out.print("]");//debug
//			System.out.print(", "); //debug
//			System.out.print("["); System.out.print(to.x); System.out.print(","); System.out.print(to.y); System.out.print("]"); //debug
//		System.out.println(")"); //debug
		ArrayList<Point> a_voir = new ArrayList<Point>();
		int max_priorite = Integer.MAX_VALUE;


		a_voir.add(from);
		boolean deja_vu[][] = new boolean[grille.getLongueur()][grille.getLargeur()];
		Point precedents[][] = new Point[grille.getLongueur()][grille.getLargeur()];

		int nb_noeud_dev = 0;

		while(!a_voir.isEmpty())
		{
			Point u = a_voir.get(0); a_voir.remove(u); //d�piler
			nb_noeud_dev++;
			if(u.x == to.x && u.y == to.y) //Si on est sur la case destination, retracer le chemin
			{
				this.a_star_path = retrace_chemin(u, precedents);
//				for(Point[] precs: precedents) System.out.println(Arrays.toString(precs)); //debug
				System.out.println("pfiou après "+nb_noeud_dev+" noeud j'ai trouvé");
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
								System.out.println("pfiou après "+nb_noeud_dev+" noeud j'ai trouvé");
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
	 * Algorithme A* (avec heuristique) sans coût
	 * Trace un chemin du point "from" au point "to" s'il existe et le stocke dans l'objet "a_star_path"
	 * @return true si et seulement si un chemin est trouvé
	 */
	public boolean algorithmA_star()
	{
//		System.out.print("A* start (from, to) :("); //debug
//			System.out.print("["); System.out.print(from.x); System.out.print(","); System.out.print(from.y); System.out.print("]");//debug
//			System.out.print(", "); //debug
//			System.out.print("["); System.out.print(to.x); System.out.print(","); System.out.print(to.y); System.out.print("]"); //debug
//		System.out.println(")"); //debug

		/**
		 * une coordonnée possédant un objet Point et une heurisitique
		 */
		class D_point implements Comparable<D_point>
		{
			final public Point p;
			final int heuristic;

			public D_point(Point p)
			{
				this.p = p;
				heuristic = (int) Math.sqrt((to.x - p.x)*(to.x - p.x) + (to.y - p.y)*(to.y - p.y));
			}


			/**
			 * utilisé pour classer les elements par heurisitque lors du parcours de A*
			 * Compare un D_point avec this
			 * @param o un D_point
			 * @return 0 si leur heurisitques sont les même, -1 si celle de o est supérieure et 1 si elle est inférieure
			 */
			@Override
			public int compareTo(D_point o) {
				return heuristic - o.heuristic;
			}

		}



		//ArrayList<Point> a_voir = new ArrayList<>();
		PriorityQueue<D_point> a_voir = new PriorityQueue<>(); //contient les cases à explorer classée par heuristique croissante
		a_voir.add(new D_point(from));
		boolean deja_vu[][] = new boolean[grille.getLongueur()][grille.getLargeur()]; //les cases déjà visités
		Point precedents[][] = new Point[grille.getLongueur()][grille.getLargeur()];

		int nb_noeud_dev = 0;//utile pour du debuguage en indiquant le nombre de noeud développés
		while(!a_voir.isEmpty())
		{
			D_point u = a_voir.poll(); //depiler
			nb_noeud_dev++;
			if(u.p.x == to.x && u.p.y == to.y) //Si on est sur la case destination, retracer le chemin
			{
				this.a_star_path = retrace_chemin(u.p, precedents);
//				for(Point[] precs: precedents) System.out.println(Arrays.toString(precs)); //debug
				//System.out.println("pfiou après "+nb_noeud_dev+" noeud j'ai trouvé");
				return true;
			}
			else //Sinon explorer les voisins en leur sauvegardant leur p�re (le point courrant)
			{
//				System.out.print("Current :"); //debug
//				System.out.print(u); //debug
//				System.out.print("Voisins :"); //debug
//				System.out.println(grille.getVoisins(u)); //debug
				for(Point v : grille.getVoisins(u.p))
				{
					if(!deja_vu[v.x][v.y])
					{
						if(grille.estOccupee(v)) // Modification locale dans le cas ou la case destination soit occupee
						{
							if(v.x == to.x && v.y == to.y)
							{
								this.a_star_path = retrace_chemin(u.p, precedents);
//								for(Point[] precs: precedents) System.out.println(Arrays.toString(precs)); //debug
								//System.out.println("pfiou après "+nb_noeud_dev+" noeud j'ai trouvé");
								return true;
							}
						}
						else
						{
							a_voir.add(new D_point(v));
							precedents[v.x][v.y] = new Point(u.p);
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