package Vue;

public class ReAffichage extends Thread {

    Affichage affichage;

    public ReAffichage(Affichage a) {
        affichage = a;
    }

    public void run() {

        while(true) {
            affichage.revalidate();
            affichage.repaint();
            try { Thread.sleep(20); }
            catch (Exception e) { e.printStackTrace(); }
        }
    }
}
