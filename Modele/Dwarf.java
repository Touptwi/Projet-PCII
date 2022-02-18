package Modele;

import java.awt.Color;

import Vue.IE_Dwarf;
import Vue.VueDwarf;

public class Dwarf extends EntiteeAvecInventaire implements Runnable
{
    public Dwarf(Etat _e) 
    {
        this.interface_e = new IE_Dwarf(_e, this);
        this.affichable = new VueDwarf(this);
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
}
