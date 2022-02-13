package Modele;

import Vue.IE_Fleur;
import Vue.IE_Fleur2;
import Vue.VueFlower;
import Vue.VueGardener;

import java.util.ArrayList;

public class Gardener extends Entitee {

    private boolean vivant = true;
    private ArrayList<Object> inventaire = new ArrayList<>();

    public Gardener(Etat _e) {
        this.interface_e = new IE_Fleur(_e, this);
        this.affichable = new VueGardener(this);
    }

    public void pickup(Object o){ inventaire.add(o); }

    public void drop(Object o){ inventaire.remove(o); }

    public ArrayList<Object> getInventaire() { return inventaire; }

    public void kill() { vivant = false; }
}
