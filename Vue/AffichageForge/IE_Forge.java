package Vue.AffichageForge;

import Controleur.ButtonListeners.ProduireListener;
import Modele.Etat;
import Modele.Entites.EntiteAvecInventaire.Batiments.Recette;
import Modele.Entites.Ressources.Ressource;
import Modele.Entites.EntiteAvecInventaire.Inventaire;
import Modele.Entites.EntiteAvecInventaire.Batiments.Forge;
import Vue.IE_inventaire;
import Vue.InterfaceEntite;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class IE_Forge implements InterfaceEntite {

    //Objet représenté
    private Forge forge;

    //composant de la page
    private JPanel fenetre_princ = new JPanel();

    private JPanel zone_info = new JPanel();

    private JPanel zone_fourneaux = new JPanel(); //l'interface dans laquelle s'affiche la liste des fourneaux
    private ArrayList<JPanel> liste_fourneaux = new ArrayList<JPanel>();// la liste des interfaces des fourneaux

    private JPanel zone_inventaire = new JPanel();//new JTextArea("*");
    private IE_inventaire inventaire = new IE_inventaire();

    private JTabbedPane tabbed_pane = new JTabbedPane();

    //liste des JPanel externes ajoutés
    private JPanel zone_recette = new JPanel();
    private HashMap<Recette,JPanel> liste_recette = new HashMap<Recette,JPanel>();

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
        for(Recette r:forge.get_recettes()) {
            System.out.println(r.getNom());
            generate_ie_recette(r, new IE_inventaire());
            zone_recette.add(liste_recette.get(r));
        }

        //parametrage de l'interface de l'inventaire
        //zone_inventaire.setEditable(false);
        //zone_inventaire.setBackground(fenetre_princ.getBackground());
        zone_inventaire.add(inventaire.getInterface());

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
        maj_zone_recettes();
        //zone_recette.setBorder(BorderFactory.createTitledBorder("il y a actuellement "+ forge.get_recettes().size() + " en attente"));
        zone_recette.setBorder(BorderFactory.createTitledBorder("score: "+ forge.getEtat().getScore()));
    }

    /**
     * va mettre a jour la sous fenêtre montrant le contenu des fourneaux de la forge
     */
    public void maj_zone_fourneau()
    {
        zone_fourneaux.setBorder(BorderFactory.createTitledBorder("peux encore lancer jusqu'à " + forge.get_nb_fourneaux_libre() + " recettes"));
        for (int i = 0; i < forge.get_nb_fourneaux(); i++)
        {
            if (forge.fourneau_actif(i))
            {
                liste_fourneaux.get(i).setVisible(true);
                maj_ie_fourneau(liste_fourneaux.get(i), forge.get_fourneau_nom(i), forge.get_fourneau_avancee(i), forge.get_fourneau_val_max(i));
            }else{
                liste_fourneaux.get(i).setVisible(false); //si le fourneau n'est pas actif, on rend son interface invisible
            }
        }
    }

    /**
     * met a jour l'interface indiquant l'inventaire de l'inventaire de la forge
     */
    public void maj_zone_inventaire()
    {
        zone_inventaire.remove(inventaire.getInterface());
        inventaire.mise_a_jour_inventaire(forge);
        zone_inventaire.add(inventaire.getInterface());
    }

    /**
     * met a jour l'interface indiquant la liste des recettes faisable par la forge
     */
    private void maj_zone_recettes()
    {
        for(Recette i:forge.get_recettes())
        {
            /*if (!liste_recette.containsKey(i)) // si elle n'est pas déjà ajouté
            {

            }else*/{
                maj_ie_recette(liste_recette.get(i),i);
            }
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

        liste_fourneaux.add(result);//on ajoute cette interface dans la liste des interface crée
        zone_fourneaux.add(result); //on ajoute le jpanel dans la zone correspondante
    }

    /**
     * va mettre a jour la barre de progression d'un fourneau
     * @param panel le JPanel de liste_fourneaux a mettre a jour
     * @param nom le nom de l'objet entrain d'etre produit
     * @param avancee l'avancée actuelle du fourneau
     * @param val_max la valeur max de l'avancé
     * Remarque, toutes ces données sont accessible dans le thread représentant le fourneau
     */
    private void maj_ie_fourneau(JPanel panel, String nom, int avancee, int val_max)
    {
        //on récupère tous les éléments de l'interface en les castant dans le bon type
        //et on met a jour leur valeur
        JTextPane ie_nom = (JTextPane) panel.getComponent(0);
        ie_nom.setText(nom);

        JProgressBar barre = (JProgressBar) panel.getComponent(1);
        barre.setMaximum(val_max);
        barre.setValue(val_max - avancee);
    }

    /**
     * va créer une interface représentant une recette avec son nom, les ingrédients nécessaires
     * et un bouton lançant la production.
     * @param r la recette liée à cette interface
     */
    private void generate_ie_recette(Recette r, IE_inventaire i)
    {
        JPanel recette_ie = new JPanel();
        recette_ie.setBorder(BorderFactory.createTitledBorder(r.getNom()));

        JButton produire = new JButton();

        recette_ie.setLayout(new BorderLayout());
        recette_ie.add(produire,BorderLayout.EAST);

        //l'inventaire indiquant la liste des ingrédients nécessaires
        i.mise_a_jour_inventaire(r.get_ingredients());
        System.out.println("recette " + r.getNom() + ": " + r.get_ingredients_string());
        recette_ie.add(i.getInterface());

        // LE bouton qui permettera de selectionner la recette
        produire.setEnabled(r.check(forge.getInventaire()));
        produire.setText("lancer production");
        produire.addActionListener(new ProduireListener(forge,r));


        liste_recette.put(r,recette_ie); //on ajoute le duo recette interface
        System.out.println(liste_recette.size());
    }

    /**
     * va changer l'etat du bouton "produire" de
     * @param recette l'interface de la recette a modifier
     * @param r la recette liée à l'interface
     */
    private void maj_ie_recette(JPanel recette, Recette r)
    {
        JButton produire = (JButton) recette.getComponent(0);
        boolean can_produce = r.check(forge.getInventaire());
        produire.setEnabled(can_produce);
//        produire.setBackground((can_produce ? Color.GREEN : Color.RED));
    }
}