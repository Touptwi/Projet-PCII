package Modele;

import Vue.IE_Fleur;
import Vue.VueDwarf;

import java.util.ArrayList;

public class Dwarf extends Entitee {

    private boolean vivant = true;
    private ArrayList<Object> inventaire = new ArrayList<>();

    public Dwarf(Etat _e) {
        this.interface_e = new IE_Fleur(_e, this);
        this.affichable = new VueDwarf(this);
    }

    public void pickup(Object o){ inventaire.add(o); }

    public void drop(Object o){ inventaire.remove(o); }

    public ArrayList<Object> getInventaire() { return inventaire; }

    public void kill() { vivant = false; }
}
