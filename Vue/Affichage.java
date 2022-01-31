package Vue;

import Controleur.Control;
import Modele.Entitee;
import Modele.Etat;

import javax.swing.*;
import java.awt.*;

public class Affichage extends JPanel
{
    private final Etat etat;
    final int taille_case = 100;
    
    
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
    
    public void 
    selectionnerEntitee(Entitee e)
    {
    	this.add( e.getInterfaceEntitee().getJFrame() );
    }
    
    //** ONLY CALL IF SOMETHING IS SELECTED */
    public void 
    deselectionnerEntitee()
    {
    	this.remove(this.getComponent(this.getComponentCount()-1));
    }
    
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
        
        JButton test = new JButton("test");
        test.setVisible(true);
        test.setBounds(0, 0, 10, 10);
        this.add(test);
        this.remove(this.getComponentCount()-1);
        
        
        
        
//        g.setColor(new Color(250, 0, 0, 255));
//        g.fillOval(10,10,80,80);
    }
}
