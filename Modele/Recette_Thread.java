package Modele;

import Vue.IE_Forge;

public class Recette_Thread extends Thread{

    Forge origine;
    IE_Forge ie;
    private int duree;
    private int valMax;
    private String nom;
    public boolean enCours = true;


    public Recette_Thread(Forge f, Recette r)
    {
        origine = f;
        ie = (IE_Forge) f.interface_e;
        duree = r.getTemps();
        valMax = r.getTemps();
        nom = r.getNom();
    }

    @Override
    public void run() {
        super.run();
        while(enCours)
        {
            try { Thread.sleep(200); }
            catch (Exception e) { e.printStackTrace();}

            duree --;
            ie.maj_zone_fourneau();
            if (duree <= 0)
                enCours = false;

        }
        System.out.println("fin de la recette");
        ie.maj_zone_fourneau();
    }

    public int getDuree()
    {
        return duree;
    }

    public String getNom()
    {
        return nom;
    }

    public int getValMax()
    {
        return valMax;
    }

    /**
     * Va stopper le thread et mettre a jour l'interface
     */
    public void stopThread()
    {
        enCours = false;
        ie.mise_a_jour();
    }
}
