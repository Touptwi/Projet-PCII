package Vue;

import Modele.Entitees.EntiteeAvecInventaire.Batiments.Recette;
import Modele.Entitees.EntiteeAvecInventaire.Batiments.Forge;
import Controleur.ButtonListeners.ProduireListener;

import javax.swing.*;
import java.awt.*;

public class IE_Recette {

    Recette recette;

    private JPanel recette_ie = new JPanel();
    private JButton produire = new JButton();
    private JTextArea area = new JTextArea();

    public IE_Recette(Recette r)
    {
        recette = r;
        generate_ie_recette();
    }

    private void generate_ie_recette()
    {
        recette_ie.setLayout(new BorderLayout());
        recette_ie.add(produire,BorderLayout.EAST);
        recette_ie.add(area,BorderLayout.CENTER);

    }

    public void maj_ie_recette(Forge forge)
    {

        // LE bouton qui permettera de selectionner la recette
        produire.setEnabled(recette.check(forge.get_inventaire()));
        produire.addActionListener(new ProduireListener(forge,recette));
        produire.setText("lancer production");
        //Element necessaire a la creation de la recette
        area.setEditable(false);
        area.setText("\n" + recette.get_ingredients_string());
    }

    public JPanel get_ie()
    {
        return recette_ie;
    }
}