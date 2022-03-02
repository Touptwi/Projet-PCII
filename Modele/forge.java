package Modele;

import Vue.IE_Forge;
import Vue.VueForge;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class forge extends Batiment{

    private ArrayList<Recette> recettes;
    private Recette_Thread[] fourneaux;


    public forge(Etat _e, int nb_fourneaux)
    {
        super(new ArrayList<Point>(Arrays.asList(new Point(0,0), new Point(1,0), new Point(-1,0))));
        affichable = new VueForge();
        init_inventaire();//creation de l'inventaire;
        fourneaux = new Recette_Thread[nb_fourneaux];
        recettes = new ArrayList<Recette>();
        System.out.println(nb_fourneaux);
        Arrays.fill(fourneaux, null);

        //creation et mise a jour de l'interface
        interface_e = new IE_Forge(_e, this);
        interface_e.mise_a_jour();

    }

    /**
     * lance la production d'une recette de la liste recettes
     * @param r la recette a produire
     * @return true si la recette a put être lancée
     */
    public boolean lancer_recette(Recette r)
    {
        if (recettes.contains(r) && r.check(inventaire))
        {
            int i = 0;
            while (i < fourneaux.length) {
                if (fourneaux[i] == null || !fourneaux[i].enCours) {
                    fourneaux[i] = new Recette_Thread(this,r);
                    fourneaux[i].start();
                    r.produire(inventaire);
                    recettes.remove(r);
                    interface_e.mise_a_jour();
                    return true;
                }
                i++;
            }
        }
        return false;
    }


    public void add_recettes(Recette r)
    {
        recettes.add(r);
        interface_e.mise_a_jour();
    }

    public ArrayList<Recette> get_recettes()
    {
        return recettes;
    }

    /**
     * renvoie le nombre d'emplacement libre dans le tableau fourneau
     * @return int le nombre
     */
    public int get_nb_fourneaux_libre()
    {
        int result = 0;
        for (int i = 0; i < fourneaux.length; i ++)
        {
            if (fourneaux[i] == null ||!fourneaux[i].enCours)
            {
                result ++;
                fourneaux[i] = null; //permet de remettre
            }
        }
        return result;
    }

    public int get_nb_fourneaux()
    {
        return fourneaux.length;
    }


    /**
     * renvoit la liste des emplacements libres dans fourneaux
     * @return la liste des indices des cases mise à null dans la liste fourneaux
     */
    public ArrayList<Integer> get_liste_fourneaux_actif()
    {
        ArrayList<Integer> result = new ArrayList<Integer>();
        for (int i = 0; i < fourneaux.length; i ++)
        {
            if (fourneaux[i] == null ||!fourneaux[i].enCours)
            {
                fourneaux[i] = null; //si un thread a terminé son execution alors il est retiré
            }else{
                result.add(i);
            }
        }
        return result;
    }

    /**
     * verifie que
     * @param i un indice de fourneaux
     * @return false si fourneaux[i] = null et true sinon
     * /!\ ne verifie PAS que i un indice valide
     */
    public boolean fourneau_actif(int i)
    {
        if (fourneaux[i] != null && fourneaux[i].enCours)
        {
            return true;
        }else{
            fourneaux[i] = null;
            return false;
        }
    }

    public int get_fourneau_avancee(int i)
    {
        if (i < fourneaux.length && i >= 0)
            return fourneaux[i].getDuree();
        return -1;
    }

    public String get_fourneau_nom(int i)
    {
        if (i >= 0 && i < fourneaux.length)
            return fourneaux[i].getNom();
        return "*";
    }

    public int get_fourneau_val_max(int i)
    {
        if(i >= 0 && i < fourneaux.length)
        {
            return fourneaux[i].getValMax();
        }
        return -1;

    }
}
