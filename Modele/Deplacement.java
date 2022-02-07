package Modele;

import java.awt.Point;

public class Deplacement extends Thread
{
	protected Entitee e;
	protected Point from;
	protected Point to;
	protected Grille grille;
	
	
	public 
	Deplacement(Entitee _e, Point _from, Point _to)
	{
		this.e = _e;
		this.from = _from;
		this.to = _to;
	}
	
	@Override
	public void
	run()
	{
		if(grille.estOccupee(from) && !grille.estOccupee(to))
		{
			grille.setCase(from, null);
			grille.setCase(to, e);
		}
	}
}
