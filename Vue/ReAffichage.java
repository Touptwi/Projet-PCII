package Vue;

/**
 * ce thread assure le réaffichage régulier de la fenetre
 */
public class ReAffichage extends Thread {

    Affichage affichage;
    InterfaceEntitee ie;

    public ReAffichage(Affichage a) {
        affichage = a;
    }

    public void run() {

        while(true) {
            ie = affichage.getInterface_entitee_courante();
            //System.out.println(Thread.activeCount());
            if(ie != null)
            {
                ie.mise_a_jour();
            }
            affichage.revalidate();
            affichage.repaint();
            try { Thread.sleep(20); }
            catch (Exception e) { e.printStackTrace(); }
        }
    }
}