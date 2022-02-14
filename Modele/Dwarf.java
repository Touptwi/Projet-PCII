package Modele;

import Vue.IE_Dwarf;
import Vue.VueDwarf;

public class Dwarf extends EntiteeAvecInventaire 
{
    public Dwarf(Etat _e) 
    {
        this.interface_e = new IE_Dwarf(_e, this);
        this.affichable = new VueDwarf(this);
    }
}
