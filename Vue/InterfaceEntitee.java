package Vue;

import javax.swing.JPanel;

/** Cette interface correspond aux interfaces graphiques a afficher 
 * a droite du terrain, correspondant a l'entitee selectionee.
 * Elle se traduit par un simple Component (JFrame) que l'on ajoutera 
 * simplement a l'affichage (ou supprimera) quand on selectionne (ou deselectionne) une Entitee. */
public interface InterfaceEntitee
{
	public JPanel getJPanel();

	/**
	 * a appeller pour demander la mise à jour de l'interface
	 */
	public void mise_a_jour();
}