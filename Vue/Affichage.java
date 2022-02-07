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
    
    JPanel interface_entitee_courante = null;
    
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
    
    /** Ajoute l'affichage de l'entitee selectionnee */
    public void 
    selectionnerEntitee()
    {
    	if(interface_entitee_courante != null) this.remove(interface_entitee_courante);
    	Entitee selected = etat.getGrille().getSelectedEntitee();
    	if(selected == null) interface_entitee_courante = null;
    	else
    	{
    		interface_entitee_courante = selected.getInterfaceEntitee().getJPanel();
    		this.add(selected.getInterfaceEntitee().getJPanel());
		}
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
