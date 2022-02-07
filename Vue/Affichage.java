package Vue;

import Controleur.Control;
import Modele.Entitee;
import Modele.Etat;

import javax.swing.*;
import java.awt.*;

public class Affichage extends JPanel
{
    /** Java est maintenant tres content. */
	private static final long serialVersionUID = 1L;
	private Etat etat;
    final int taille_case = 100;
    
    /** Initialise une fenetre qui se rafraichi, avec un controller et un etat qu'on utilisera comme modele a afficher.
     * @param Etat e : Etat sur lequel se baser pour l'affichage.
     */
    public Affichage(Etat e) 
    {
        etat = e;        

        JFrame fenetre = new JFrame("FlowerCraft");
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setPreferredSize(new Dimension(etat.getLargeur() , etat.getHauteur()));
        this.addMouseListener(new Control(etat, this));
        this.setFocusable(true);
        
        fenetre.add(this);
        fenetre.pack();
        fenetre.setVisible(true);
    }
    
    public int getTailleCase() { return this.taille_case; }
    
    /** Ajoute l'affichage de l'entitee donee
     * @note Ne pas appeler cette methode quand elle a deja etee appelee et que la deselection n'est pas faite.
     * @param Entitee e : Entitee a afficher
     * */
    public void 
    selectionnerEntitee(Entitee e)
    {
    	this.add( e.getInterfaceEntitee().getJFrame() );
    }
    
    /** Enleve l'affichage de la derniere entitee
     * @note ATTENTION : APPELER CETTE FONCTION SEULEMENT SI UNE ENTITEE EST SELECTIONEE
     * Possible changement : donner une entitee dont on prendra l'affichage que l'on supprime
     * -> Besoin d'avoir l'entitee selectionnee.
     * */
    public void 
    deselectionnerEntitee()
    {
    	this.remove(this.getComponent(this.getComponentCount()-1));
    }
    
    /** Affichage a l'ecran, divise en plusieurs sections :
     * - Affichage du terrain (grille)
     * - Affichage des Entitees (points, sprites plus tard)
     * @param Graphics g : Graphic sur lequel afficher.
     */
    @Override
    public void paint(Graphics g) 
    {
        super.paint(g);
        for(int i = 0; i <= 10; i++) 
        {
            g.drawLine( 0, taille_case*i, taille_case*10, taille_case*i );
            g.drawLine( taille_case*i, 0, taille_case*i, taille_case*10);
        }
        
        for(int j = 0; j < etat.getHauteurGrille(); ++j)
        {
        	for(int i = 0; i < etat.getLargeurGrille(); ++i)
        	{
        		Entitee entitee = etat.getEntitee(i, j);
        		if(entitee == null) continue; 
        		((Graphics2D) g).translate(i*taille_case, j*taille_case);
        		entitee.getSprite().draw(g);
        		((Graphics2D) g).translate(-i*taille_case, -j*taille_case);
        	}
        }
        
//        g.setColor(new Color(250, 0, 0, 255));
//        g.fillOval(10,10,80,80);
    }
}
