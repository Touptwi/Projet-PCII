package Vue;

import java.awt.Rectangle;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import Modele.Etat;
import Modele.Dwarf;
import Vue.ButtonListeners.MoveListener;
import Vue.ButtonListeners.RecupererListener;

public class IE_Dwarf implements InterfaceEntitee
{
	Dwarf dwarf;
	JPanel interface_graphique;
	private JSplitPane separateur;
	
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
		JButton recup_button = new JButton("RECUPERER");
		RecupererListener recup_action = new RecupererListener(_etat, _dwarf);
		recup_button.addActionListener(recup_action);
		recup_button.setBounds(new Rectangle(100, 0, 100, 100));
		recup_button.setVisible(true);
		
        separateur = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, move_button, recup_button);
        interface_graphique.add(separateur);
		
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
