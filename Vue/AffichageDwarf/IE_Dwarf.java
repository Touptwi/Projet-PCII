package Vue.AffichageDwarf;

import java.awt.*;

import javax.swing.*;

import Controleur.ButtonListeners.LivrerListener;
import Modele.Entites.Ressources.Ressource;
import Modele.Etat;
import Modele.Entites.EntiteAvecInventaire.EntitesDeplacable.Dwarf;
import Controleur.ButtonListeners.MoveListener;
import Controleur.ButtonListeners.RecupererListener;
import Vue.IE_inventaire;
import Vue.InterfaceEntite;

public class IE_Dwarf implements InterfaceEntite
{
	Dwarf dwarf;
	JPanel interface_graphique;
	JButton move_button = new JButton("DEPLACEMENT");
	JButton recup_button = new JButton("RECUPERER");
	JButton deliver_button = new JButton("LIVRER");
	//JTextField inventaire;
	IE_inventaire inventaire = new IE_inventaire();
	
	public 
	IE_Dwarf(Etat _etat, Dwarf _dwarf)
	{
		this.dwarf = _dwarf;
		this.interface_graphique = new JPanel();

		interface_graphique.setLayout(new BoxLayout(interface_graphique, BoxLayout.PAGE_AXIS));
		interface_graphique.add(new JLabel(new ImageIcon("Images/Dwarf.png")));
		
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
		LivrerListener livrer = new LivrerListener(_etat,_dwarf);
		deliver_button.addActionListener(livrer);
		deliver_button.setBounds(new Rectangle(100, 0, 100, 100));
		deliver_button.setVisible(true);
		
		//Inventaire : Affichage de l'inventaire du nain
		//inventaire = new JTextField(Arrays.toString(dwarf.getInventaire().getInventaire()), 20);
		//inventaire.setEditable(false);

        JPanel buttons = new JPanel();
		buttons.setLayout(new BoxLayout(buttons, BoxLayout.LINE_AXIS));
		buttons.add(move_button);
		buttons.add(recup_button);
		buttons.add(deliver_button);

		interface_graphique.add(buttons);
		interface_graphique.add(inventaire.getInterface());
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
		//inventaire.setText(Arrays.toString(dwarf.getInventaire().getInventaire()));
		interface_graphique.remove(inventaire.getInterface());
		inventaire.mise_a_jour_inventaire(dwarf);
		interface_graphique.add(inventaire.getInterface());

	}


}