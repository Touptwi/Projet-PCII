package Vue.AffichageDwarf;

import java.awt.Rectangle;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

import Modele.Etat;
import Modele.Entitees.EntiteeAvecInventaire.EntieesDeplacable.Dwarf;
import Controleur.ButtonListeners.MoveListener;
import Controleur.ButtonListeners.RecupererListener;
import Vue.InterfaceEntitee;

public class IE_Dwarf implements InterfaceEntitee
{
	Dwarf dwarf;
	JPanel interface_graphique;
	JButton move_button = new JButton("DEPLACEMENT");
	JButton recup_button = new JButton("RECUPERER");
	JButton deliver_button = new JButton("LIVRER");
	JTextField inventaire;
	
	public 
	IE_Dwarf(Etat _etat, Dwarf _dwarf)
	{
		this.dwarf = _dwarf;
		this.interface_graphique = new JPanel();
		
		//"Deplacement" : mouvement du nain
		MoveListener move_action = new MoveListener(_etat);
		move_button.addActionListener(move_action);
		move_button.setBounds(new Rectangle(0, 0, 100, 100));
		move_button.setVisible(true);
		
		//"Recuperer" : recuperation de ressources (Possible liaison au batiment ?)
		RecupererListener recup_action = new RecupererListener(_etat, _dwarf);
		recup_button.addActionListener(recup_action);
		recup_button.setBounds(new Rectangle(100, 0, 100, 100));
		recup_button.setVisible(true);
				
		//"Livrer" : livraison de materiaux aux batiments
		// TODO : Action listener
		deliver_button.setBounds(new Rectangle(100, 0, 100, 100));
		deliver_button.setVisible(true);
		
		//Inventaire : Affichage de l'inventaire du nain
		inventaire = new JTextField(Arrays.toString(dwarf.getInventaire().getInventaire()), 20);
		inventaire.setEditable(false);

		JSplitPane separateurH1 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, move_button, recup_button);
		JSplitPane separateurH2 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, recup_button, deliver_button);
        JSplitPane separateurV = new JSplitPane(JSplitPane.VERTICAL_SPLIT, separateurH2, inventaire);
        
        interface_graphique.add(separateurH1);
        interface_graphique.add(separateurH2);
        interface_graphique.add(separateurV);
	}

	@Override
	public JPanel getJPanel() 
	{
		return this.interface_graphique;
	}

	@Override
	public void mise_a_jour()
	{
//		System.out.print(Arrays.toString(dwarf.getInventaire())); //debug
		inventaire.setText(Arrays.toString(dwarf.getInventaire().getInventaire()));
	}
}