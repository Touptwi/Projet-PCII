package Vue;

import java.awt.Rectangle;
import javax.swing.JButton;
import javax.swing.JPanel;

import Modele.Etat;
import Modele.Dwarf;
import Vue.ButtonListeners.MoveListener;

public class IE_Fleur implements InterfaceEntitee
{
	Dwarf fleur;
	JPanel interface_graphique;
	
	public 
	IE_Fleur(Etat _etat, Dwarf _fleur)
	{
		this.fleur = _fleur;
		this.interface_graphique = new JPanel();
		JButton move_button = new JButton("DEPLACEMENT");
		MoveListener move_action = new MoveListener(_etat);
		move_button.addActionListener(move_action);
		move_button.setBounds(new Rectangle(0, 0, 100, 100));
		move_button.setVisible(true);
		this.interface_graphique.setBounds(new Rectangle(100*_etat.getLargeurGrille(), 0, 1400, 100*_etat.getHauteurGrille()));
		this.interface_graphique.add(move_button);
	}

	@Override
	public JPanel getJPanel() 
	{
		return this.interface_graphique;
	}

	@Override
	public void mise_a_jour()
	{}
}
