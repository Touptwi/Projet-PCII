package Vue;

import java.awt.Rectangle;
import javax.swing.JButton;
import javax.swing.JPanel;

import Modele.Etat;
import Modele.Flower;
import Vue.ButtonListeners.MoveListener;

public class IE_Fleur implements InterfaceEntitee
{
	Flower fleur;
	JPanel interface_graphique;
	
	public 
	IE_Fleur(Etat _etat, Flower _fleur)
	{
		this.fleur = _fleur;
		this.interface_graphique = new JPanel();
		JButton move_button = new JButton();
		MoveListener move_action = new MoveListener(_etat);
		move_button.addActionListener(move_action);
		move_button.setBounds(new Rectangle(0, 0, 100, 100));
		this.interface_graphique.add(move_button);
	}

	@Override
	public JPanel getJPanel() 
	{
		return this.interface_graphique;
	}
}
