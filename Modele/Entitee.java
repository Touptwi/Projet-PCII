package Modele;
import Vue.Affichable;
import Vue.InterfaceEntitee;

public abstract class Entitee
{
	/** Sprite de l'Entitee : image a afficher ou l'entitee est sur le terrain */
	public Affichable affichable;
	/** Interface de l'Entitee : HUD a droite de l'ecran de jeu */
	public InterfaceEntitee interface_e;
	
	public Affichable getSprite() { return affichable; }
	public InterfaceEntitee getInterfaceEntitee() { return interface_e; }

}
