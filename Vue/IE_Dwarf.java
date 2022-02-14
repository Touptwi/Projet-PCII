package Vue;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

import Modele.Etat;
import Modele.Dwarf;
import Vue.ButtonListeners.MoveListener;
import Vue.ButtonListeners.RecupererListener;

public class IE_Dwarf implements InterfaceEntitee
{
	Dwarf dwarf;
	JPanel interface_graphique;
	JButton move_button = new JButton("DEPLACEMENT");
	JButton recup_button = new JButton("RECUPERER");
	JTextField inventaire;
	
	public 
	IE_Dwarf(Etat _etat, Dwarf _dwarf)
	{
		this.dwarf = _dwarf;
		this.interface_graphique = new JPanel();
		
		MoveListener move_action = new MoveListener(_etat);
		move_button.addActionListener(move_action);
		move_button.setBounds(new Rectangle(0, 0, 100, 100));
		move_button.setVisible(true);
		
		//"Recuperer"
		RecupererListener recup_action = new RecupererListener(_etat, _dwarf);
		recup_button.addActionListener(recup_action);
		recup_button.setBounds(new Rectangle(100, 0, 100, 100));
		recup_button.setVisible(true);
		
		JSplitPane separateurH1 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, move_button, recup_button);
		
		//"Livrer"
		//TODO : bouton et actionlistener correspondants
		
		//Inventaire
		inventaire = new JTextField(Arrays.toString(dwarf.getInventaire()), 20);
//		inventaire.setEditable(false);
        JSplitPane separateurV = new JSplitPane(JSplitPane.VERTICAL_SPLIT, separateurH1, inventaire);
        interface_graphique.add(separateurV);
        //
		
		
		this.interface_graphique.add(move_button);
	}

	@Override
	public JPanel getJPanel() 
	{
		return this.interface_graphique;
	}

	@Override
	public void mise_a_jour()
	{
		//TODO : Call this and update other stuff if needed.
		inventaire.setText(Arrays.toString(dwarf.getInventaire()));
	}
}
