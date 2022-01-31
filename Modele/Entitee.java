package Modele;
import Vue.Affichable;
import Vue.InterfaceEntitee;

public abstract class Entitee 
{
	Affichable affichable;
	InterfaceEntitee interface_e;
	
	public Affichable getSprite() { return affichable; }
	public InterfaceEntitee getInterfaceEntitee() { return interface_e; }
	
}
