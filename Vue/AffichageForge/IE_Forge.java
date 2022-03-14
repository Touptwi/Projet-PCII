package Vue.AffichageForge;

import Modele.Etat;
import Modele.Entitees.EntiteeAvecInventaire.Batiments.Recette;
import Modele.Entitees.Ressources.Ressource;
import Modele.Entitees.EntiteeAvecInventaire.Inventaire;
import Modele.Entitees.EntiteeAvecInventaire.Batiments.Forge;
import Vue.InterfaceEntitee;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Dictionary;

public class IE_Forge implements InterfaceEntitee {

    //Objet représenté
    private Forge forge;

    //composant de la page
    private JPanel fenetre_princ = new JPanel();

    private JPanel zone_fourneaux = new JPanel();
    private ArrayList<JPanel> liste_fourneaux = new ArrayList<JPanel>();

    private JTextArea zone_inventaire = new JTextArea("*");

    private JTabbedPane tabbed_pane = new JTabbedPane();

    //liste des JPanel externes ajoutés
    JPanel zone_recette = new JPanel();
    ArrayList<Recette> liste_recette = new ArrayList<Recette>();

    public IE_Forge(Etat _e, Forge f) {
        forge = f;

        //parametrage de la liste des fourneaux
        zone_fourneaux.setBorder(BorderFactory.createTitledBorder("Peux lancer jusqu'à  * recettes"));
        zone_fourneaux.setLayout(new BoxLayout(zone_fourneaux,BoxLayout.Y_AXIS));
        for (int i = 0; i < forge.get_nb_fourneaux(); i++)
        {
            generate_ie_fourneau();
        }

        //parametrage de l'interface de recette
        zone_recette.setLayout(new BoxLayout(zone_recette,BoxLayout.Y_AXIS));
        zone_recette.setBorder(BorderFactory.createTitledBorder("il y a actuellement * en attente"));

        //parametrage de l'interface de l'inventaire
        zone_inventaire.setEditable(false);
        zone_inventaire.setBackground(fenetre_princ.getBackground());

        // paramétrage de la fenetre princ
        fenetre_princ.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        fenetre_princ.setLayout(new BorderLayout());

        // parametrage de la fenetre inferieur de l'interface
        tabbed_pane.add("commandes", zone_recette);
        tabbed_pane.add("inventaire", zone_inventaire);

        JSplitPane separation = new JSplitPane(JSplitPane.VERTICAL_SPLIT,zone_fourneaux,tabbed_pane);
        separation.setDividerSize(0);
        fenetre_princ.add(separation);
    }

    @Override
    public JPanel getJPanel() {
        return fenetre_princ;
    }

    @Override
    public void mise_a_jour()
    {
        maj_zone_fourneau();
        maj_zone_inventaire();
        zone_recette.setBorder(BorderFactory.createTitledBorder("il y a actuellement "+ forge.get_recettes().size() + " en attente"));
        for(Recette i:forge.get_recettes())
        {
            if (!liste_recette.contains(i)) // si elle n'est pas déjà ajouté
            {
                liste_recette.add(i);
                zone_recette.add(i.getRecetteIE().get_ie());
                i.getRecetteIE().maj_ie_recette(forge);
            }
        }
        ArrayList<Recette> test = new ArrayList<Recette>();
        for(Recette i : liste_recette)
        {
            if(!forge.get_recettes().contains(i))
            {
                test.add(i);
            }
        }

        for(Recette i : test)
        {
            liste_recette.remove(i);
            zone_recette.remove(i.getRecetteIE().get_ie());
        }
    }

    /**
     * va mettre a jour la sous fenêtre montrant le contenu des fourneaux de la forge
     */
    public void maj_zone_fourneau()
    {
        zone_fourneaux.setBorder(BorderFactory.createTitledBorder("peux encore lancer jusqu'à " + forge.get_nb_fourneaux_libre() + " recettes"));
        ArrayList<Integer> val = forge.get_liste_fourneaux_actif();
        for (int i = 0; i < forge.get_nb_fourneaux(); i++)
        {
            if (forge.fourneau_actif(i))
            {
                liste_fourneaux.get(i).setVisible(true);
                maj_ie_fourneau(liste_fourneaux.get(i), forge.get_fourneau_nom(i), forge.get_fourneau_avancee(i), forge.get_fourneau_val_max(i));
            }else{
                liste_fourneaux.get(i).setVisible(false);
            }
        }
    }

    /**
     * met a jour l'interface indiquant l'inventaire de l'inventaire de la forge
     */
    public void maj_zone_inventaire()
    {
        if (forge.getInventaire() != null)
        {
            String result = "";
            Inventaire i = forge.getInventaire();
            Ressource[] ressources = i.getInventaire();
            for (Ressource r : ressources) 
            {
            	result += r.toString();
            }
            zone_inventaire.setText(result);
        }
    }

    /**
     * Initialiser la sous fenêtre representant les fourneaux de la forge et tous les composants necessaires
     */
    private void generate_ie_fourneau()
    {
        JPanel result = new JPanel();
        result.setLayout(new BorderLayout());

        JTextPane ie_nom = new JTextPane();
        ie_nom.setText("[EN ATTENTE]");
        result.add(ie_nom, BorderLayout.WEST);

        JProgressBar barre = new JProgressBar();
        barre.setValue(0);
        result.add(barre);

        liste_fourneaux.add(result);
        zone_fourneaux.add(result);
    }

    private void maj_ie_fourneau(JPanel panel, String nom, int avancee, int val_max)
    {

        JTextPane ie_nom = (JTextPane) panel.getComponent(0);
        ie_nom.setText(nom);

        JProgressBar barre = (JProgressBar) panel.getComponent(1);
        barre.setMaximum(val_max);
        barre.setValue(val_max - avancee);

    }
}