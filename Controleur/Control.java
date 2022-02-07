package Controleur;

import Modele.Etat;
import Vue.Affichage;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Control implements MouseListener {

    private final Etat etat;
    private final Affichage affichage;

    public Control(Etat e, Affichage a) {

        etat = e;
        affichage = a;
    }

    @Override
    public void mousePressed(MouseEvent e) 
    {
    	int mouseX = e.getX();
    	int mouseY = e.getY();
    	
    	if(mouseX > 0 && mouseX < etat.getLargeurGrille()*affichage.getTailleCase()
    		&& mouseY > 0 && mouseY < etat.getHauteurGrille()*affichage.getTailleCase())
    		etat.getGrille().selectionne(mouseX/affichage.getTailleCase(), mouseY/affichage.getTailleCase());
    }
    
    @Override
    public void mouseClicked(MouseEvent e) { }

    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
}
