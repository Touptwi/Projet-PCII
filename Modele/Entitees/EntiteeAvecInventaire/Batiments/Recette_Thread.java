package Modele.Entitees.EntiteeAvecInventaire.Batiments;

import Vue.AffichageForge.IE_Forge;

public class Recette_Thread extends Thread{

    Forge origine;
    IE_Forge ie;
    private int duree;
    private int valMax;
    private String nom;
    private int score;
    public boolean enCours = true;

    public Recette_Thread(Forge f, Recette r)
    {
        origine = f;
        ie = (IE_Forge) f.interface_e;
        duree = r.getTemps();
        valMax = r.getTemps();
        nom = r.getNom();
        score = r.getScore();
    }

    @Override
    public void run() {
        super.run();
        while(enCours)
        {
            try { Thread.sleep(200); }
            catch (Exception e) { e.printStackTrace();}

            duree --;
            if (duree <= 0)
                enCours = false;

        }
        origine.getEtat().addScore(score);
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
    }
}