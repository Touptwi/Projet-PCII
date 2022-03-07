package Modele;

import java.awt.*;
import java.util.ArrayList;

import Vue.IE_Dwarf;
import Vue.VueDwarf;

public class Dwarf extends EntiteeDeplacable implements Runnable
{
    public Dwarf(Etat _e, Point pos)
    {
        this.interface_e = new IE_Dwarf(_e, this);
        this.affichable = new VueDwarf(this);
		this.position = pos;
		_e.getGrille().setCase(pos, this);
    }

	@Override
	public void run() 
	{
		while(true)
		{
			interface_e.mise_a_jour();
			try { Thread.sleep(100); }
			catch (Exception e) { e.printStackTrace(); }			
		}
	}

	@Override
	public void check_deplacement(Point position, ArrayList<Point> voisins) 
	{
		// TODO Auto-generated method stub	
	}

	@Override
	public void fin_deplacement(Point position, ArrayList<Point> voisins) 
	{
		// TODO Auto-generated method stub
	}

	@Override
	public void fuir(Point position, ArrayList<Point> voisins) 
	{
		// TODO Auto-generated method stub	
	}
}
