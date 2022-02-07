package Vue.ButtonListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Modele.Etat;

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
