package Vue.ButtonListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Modele.Etat;

/**
 * Listener lier au bouton "déplacement" de l'interface des entitées mobiles tel que le nain
 */
public class MoveListener implements ActionListener
{
	Etat etat;
	
	public
	MoveListener(Etat _etat)
	{
		this.etat = _etat;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		etat.setMode(Etat.Modes.DEPLACEMENT);
	}
}