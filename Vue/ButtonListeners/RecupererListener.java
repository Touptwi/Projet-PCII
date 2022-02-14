package Vue.ButtonListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Modele.Dwarf;
import Modele.Etat;

public class RecupererListener implements ActionListener
{
	Etat etat;
	Dwarf dwarf;
	
	public
	RecupererListener(Etat _e, Dwarf _d)
	{
		this.etat = _e;
		this.dwarf = _d;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		// TODO Auto-generated method stub
		etat.getGrille().recupererRessources(dwarf);
	}

}