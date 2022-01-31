package Modele;

import java.util.ArrayList;

public class Gardener extends Thread {

    private boolean vivant = true;
    private ArrayList<Object> inventaire = new ArrayList<>();

    public Gardener() {
    }

    public void run() {

        while(vivant) {

            try { Thread.sleep(100); } catch (Exception e) { e.printStackTrace(); }
        }
    }

    public void pickup(Object o){ inventaire.add(o); }

    public void drop(Object o){ inventaire.remove(o); }

    public ArrayList<Object> getInventaire() { return inventaire; }

    public void kill() { vivant = false; }
}
