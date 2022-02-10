package Controleur;

import Modele.Etat;
import Vue.Affichage;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Control implements MouseListener {

    private final Etat etat;
    private final Affichage affichage;

    public Control(Etat e, Affichage a) 
    {
        etat = e;
        affichage = a;
    }

    @Override
    public void mousePressed(MouseEvent e) 
    {
    	int mouseX = e.getX();
    	int mouseY = e.getY();
    	
    	if(mouseX > 0 && mouseX < affichage.fitX(etat.getLargeurGrille()*affichage.getTailleCase())
    		&& mouseY > 0 && mouseY < affichage.fitY(etat.getHauteurGrille()*affichage.getTailleCase()))
    	{
    		etat.click(mouseX/affichage.fitX(affichage.getTailleCase()), mouseY/affichage.fitY(affichage.getTailleCase()));
    		affichage.selectionnerEntitee();
    	}
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
}
