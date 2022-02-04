package Vue;

import java.awt.Graphics;

/**
 * Interface d'un element affichable a l'ecran. Utilise notamment pour les images correspondant a une entitee a afficher
 * @author Nicolas
 */
public interface Affichable 
{
	/** Methode a appeler pour afficher notre affichable 
	 * @note : Cet affichage sera fait en 0 0 donc il faut bien penser a translatter cet affichage.
	 * @param g : Graphic sur lequel afficher
	 * */
	public void draw(Graphics g);
}
