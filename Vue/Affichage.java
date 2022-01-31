package Vue;

import Controleur.Control;
import Modele.Entitee;
import Modele.Etat;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Affichage extends JPanel 
{

    private final Etat etat;
    final int taille_case = 10;
    
    public Affichage(Etat e) 
    {
        etat = e;        

        JFrame frame = new JFrame("FlowerCraft");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setPreferredSize(new Dimension(etat.getHauteur() , etat.getLargeur()));
        this.addMouseListener(new Control(etat, this));
        this.setFocusable(true);

        frame.add(this);
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void paint(Graphics g) 
    {
    	Entitee[][] grille;
    	
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
        		entitee.getAffichable().draw(g);
        		((Graphics2D) g).translate(-i*taille_case, -j*taille_case);
        	}
        }
        
//        g.setColor(new Color(250, 0, 0, 255));
//        g.fillOval(10,10,80,80);
    }
}
