package Modele;

import Modele.Entitees.EntiteeAvecInventaire.Batiments.Batiment;
import Modele.Entitees.Entitee;
import Modele.Entitees.EntiteeAvecInventaire.EntieesDeplacable.Dwarf;
import Modele.Entitees.EntiteeAvecInventaire.EntieesDeplacable.Goblin;
import Modele.Entitees.EntiteeAvecInventaire.EntiteeAvecInventaire;
import Modele.Entitees.Ressources.Ressource;

import java.awt.Point;
import java.util.ArrayList;

public class Grille 
{
    /// lien objets externe ///
    private static Etat e;
    ///////////////////////////
    private final int largeur;
    private final int longueur;

    // coordonnées de la case selectionnée
    private int selectionX;
    private int selectionY;
    
    private Entitee entitee_selectionee;

    private Entitee[][] grille;
    
    public Grille(Etat etat, int longu, int larg)
    {
        e = etat;
        longueur = longu;
        largeur = larg;
        grille = new Entitee[longueur][largeur];

        init_grille();

    }

    /**
     * initialise toutes les cases de la grille à null
     */
    public void init_grille()
    {
        for (int i = 0; i < grille.length ; i++)
        {
            for (int j = 0; j < grille[0].length; j++)
            {
                grille[i][j] = null;
            }
        }
    }

    /**
     * verifie si une entitée se trouve sur la case
     * @param x la coordonnée X de la case étudiée
     * @param y la coordonnée Y de la case étudiée
     * @return true si elle est occupée et false sinon
     */
    public boolean estOccupee(int x, int y)
    {
        return grille[x][y] != null;
    }
    
    /** Verifie si une case donnee est occupee par une entitee
     * @param p : placement de la case
     * @return vrai si une entitee est placee sur la case donnee, faux sinon
     */
    public boolean estOccupee(Point p)
    {
    	return grille[p.x][p.y] != null;
    }

/////// guetters et setters ////////////////////////////////////////////////////////////////////////////////////////////

    public int getLargeur()
    {
        return largeur;
    }

    public int getLongueur()
    {
        return longueur;
    }

    public int getSelectionX()
    {
        return selectionX;
    }

    public int getSelectionY()
    {
        return selectionY;
    }
    
    public Point getSelectionPosition()
    {
    	return new Point(selectionX, selectionY);
    }
    
    public ArrayList<Point> getSelectionVoisins()
    {
    	ArrayList<Point> voisins = new ArrayList<Point>();
		for(int i = -1; i <= 1; ++i)
			for(int j = -1; j <= 1; ++j)
				if(	j != i && j*i != -1
					&& selectionX + i >= 0 && selectionY + i < largeur 
					&& selectionY + j >= 0 && selectionY + j < longueur)
						voisins.add(new Point(selectionX + i, selectionY + j));
		return voisins;
    }
    
    public ArrayList<Point> getVoisins(Point p)
    {
    	ArrayList<Point> voisins = new ArrayList<Point>();
		for(int i = -1; i <= 1; ++i)
			for(int j = -1; j <= 1; ++j)
				if(	j != i && j != -1*i
					&& p.x + i >= 0 && p.x + i < largeur 
					&& p.y + j >= 0 && p.y + j < longueur)
						voisins.add(new Point(p.x + i, p.y + j));
		return voisins;
    }

    /**
     * change la valeur d'une case du tableau
     * @param x la coordonnée X de la case
     * @param y la coordonnée Y de la case
     * @param val la nouvelle valeur de la case
     */
    public synchronized void setCase(int x, int y, Entitee val)
    {
        grille[x][y] = val;
        //System.out.println(toString()+ "\n"); //debug
        //System.out.println(Thread.activeCount());
    }
    
    
    /** Place une entitee dans une case
     * @param to : case ou placer l'entitee
     * @param val : Entitee a placer 
     */
    public synchronized void setCase(Point to, Entitee val)
    {
    	grille[to.x][to.y] = val;
        //System.out.println(toString() + "\n");//debug
        //System.out.println(Thread.activeCount());
    }

    /**
     * va marque la case de coordonnée x y comme selectionnée
     * @param x coord X
     * @param y coord Y
     */
    public void selectionne(int x, int y)
    {
        selectionX = x;
        selectionY = y;
        entitee_selectionee = getEntitee(x,y);
        //System.out.print(String.format("x,y : %d,%d", x, y)); //debug
        
    }

    /**
     * rend l'entitée situé sur la case de coordonnée X Y ssi elle est affichable
     * @param x la coord X
     * @param y la coord Y
     * @return rend L'entitée située sur la case et null si il n'y a rien à ces coordonnées
     */
    public Entitee getEntitee(int x, int y)
    {
        if (grille[x][y] != null && grille[x][y].getSprite() == null) { grille[x][y] = null; }
        return grille[x][y];
    }
    
    /** Renvoie l'entitee situee a la case donee ssi elle est affichable
     * @param from : Case contenant l'entitee
     * @return Entitee a la case donee
     */
    public Entitee getEntitee(Point from)
    {
        if (grille[from.x][from.y] != null && grille[from.x][from.y].getSprite() == null) { grille[from.x][from.y] = null; }
        return grille[from.x][from.y];
    }

    /**
     * @return la denrniere entitee selectionne
     */
    public Entitee getSelectedEntitee()
    {
        return entitee_selectionee;
    }

    public void create_batiment_selection(Batiment bat)
    {
        Point origine = new Point(selectionX,selectionY);
        ArrayList<Point> a_construire = bat.getStructure();
        for (int i = 0; i< a_construire.size(); i++)
        {
            Point point = new Point(origine.x + a_construire.get(i).x, origine.y + a_construire.get(i).y);
            if (((point.x < 0 || point.y < 0) && (point.x > largeur || point.y > longueur)) || estOccupee(point) )
            {
                return;
            }
            setCase(point, bat);
        }
    }

    public void create_batiment(Point p, Batiment bat)
    {
        ArrayList<Point> a_construire = bat.getStructure();
        for (int i = 0; i< a_construire.size(); i++)
        {
            Point point = new Point(p.x + a_construire.get(i).x, p.y + a_construire.get(i).y);
            if (((point.x < 0 || point.y < 0) && (point.x > largeur || point.y > longueur)) || estOccupee(point) )
            {
                System.out.println("probleme lors de la creation du bâtiment " + bat.getClass());
                return;
            }
            this.setCase(point.x,point.y, bat);
        }
    }

	public void recupererRessources(EntiteeAvecInventaire EAI) {
        Point pos = new Point(-1, -1);
        for (int i = 0; i < getLargeur(); i++) {
            for (int j = 0; j < getLongueur(); j++) {
                if (getEntitee(i, j) == EAI) pos = new Point(i, j);
            }
        }
        if (pos != new Point(-1,-1)) {
            for (Point p : getVoisins(pos)) {
                Entitee e = this.getEntitee(p);
                if (e instanceof Ressource) {
                    EAI.getInventaire().add((Ressource) e);
                    this.setCase(p, null);
                }
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < getLongueur();i++) {
            for (int j = 0; j < getLargeur(); j++) {
                Entitee e = getEntitee(j,i);
                if( e instanceof Dwarf)
                    result.append("D").append(" ; ");
                else if (e instanceof Goblin)
                    result.append("G" + " ; ");
                else if(e instanceof Batiment)
                    result.append("B" + " ; ");
                else if(e instanceof Ressource)
                    result.append("R" + " ; ");
                else
                    result.append(" " + " ; ");
            }
            result.append("\n");
        }
        return result.toString();
    }
}