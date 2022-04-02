package Modele.Entites;

import Vue.Affichable;
import Vue.InterfaceEntite;

import java.awt.*;

public class Entite
{
	/** Sprite de l'Entitee : image a afficher ou l'entitee est sur le terrain */
	public Affichable affichable;
	/** Interface de l'Entitee : HUD a droite de l'ecran de jeu */
	public InterfaceEntite interface_e;
	/** Point de position de l'Entitee dans la grille */
	public Point position;
	/** Vérifie si l'entitée est un dwarf */
	public Boolean isDwarf = false;
	/** Vérifie si l'entitée est apeuré */
	public Boolean isFeared = false;

	public Affichable getSprite() { return affichable; }
	public InterfaceEntite getInterfaceEntitee() { return interface_e; }
	public Point getPosition() { return position; }

}