package Vue;

import java.awt.Rectangle;
import javax.swing.JButton;
import javax.swing.JPanel;

import Modele.Etat;
import Modele.Dwarf;
import Vue.ButtonListeners.MoveListener;

public class IE_Dwarf implements InterfaceEntitee
{
	Dwarf dwarf;
	JPanel interface_graphique;
	
	public 
	IE_Dwarf(Etat _etat, Dwarf _dwarf)
	{
		this.dwarf = _dwarf;
		this.interface_graphique = new JPanel();
		
		JButton move_button = new JButton("DEPLACEMENT");
		MoveListener move_action = new MoveListener(_etat);
		move_button.addActionListener(move_action);
		move_button.setBounds(new Rectangle(0, 0, 100, 100));
		move_button.setVisible(true);
		
		//"Recuperer"
		
		//"Livrer"
		
		
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
