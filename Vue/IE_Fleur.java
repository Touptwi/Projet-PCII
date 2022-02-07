package Vue;

import java.awt.Rectangle;
import javax.swing.JButton;
import javax.swing.JFrame;

import Modele.Etat;
import Modele.Flower;
import Vue.ButtonListeners.MoveListener;

public class IE_Fleur implements InterfaceEntitee
{
	Flower fleur;
	JFrame interface_graphique;
	
	public 
	IE_Fleur(Etat _etat, Flower _fleur)
	{
		this.fleur = _fleur;
		this.interface_graphique = new JFrame();
		JButton move_button = new JButton();
		MoveListener move_action = new MoveListener(_etat);
		move_button.addActionListener(move_action);
		move_button.setBounds(new Rectangle(0,0,10,10));
		this.interface_graphique.add(move_button);
	}

	@Override
	public JFrame getJFrame() 
	{
		return this.interface_graphique;
	}
}
