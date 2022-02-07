package Modele;

import java.util.ArrayList;

public class Gardener extends Entitee {

    private boolean vivant = true;
    private ArrayList<Object> inventaire = new ArrayList<>();

    public Gardener() {
    }

    public void pickup(Object o){ inventaire.add(o); }

    public void drop(Object o){ inventaire.remove(o); }

    public ArrayList<Object> getInventaire() { return inventaire; }

    public void kill() { vivant = false; }
}
