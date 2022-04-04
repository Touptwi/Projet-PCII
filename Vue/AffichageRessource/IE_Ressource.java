package Vue.AffichageRessource;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.*;

import Modele.Etat;
import Modele.Entites.Ressources.Ressource;
import Vue.InterfaceEntite;

public class IE_Ressource implements InterfaceEntite
{
    private Ressource ressource;
    private JPanel interface_graphique_princ;
    private Canvas icone;
    private JSplitPane separateur;

    public IE_Ressource(Etat _e, Ressource r)
    {
    	ressource = r;
        interface_graphique_princ = new JPanel();

        //generation de l'icone
        icone = new Canvas();
        icone.setSize(new Dimension(60,60));
        

        JLabel nameAndQuantity = new JLabel(ressource.toString(),new ImageIcon("Images/Ressources/" + ressource.getType().name() +"/Ingot.png"),SwingConstants.LEFT);
        //JTextField nameAndQuantity = new JTextField(ressource.toString(), 20);
        //nameAndQuantity.setEditable(false);
        
        //mise en page via un SPLITSPANE
        separateur = new JSplitPane(JSplitPane.VERTICAL_SPLIT, icone, nameAndQuantity);
        separateur.setDividerSize(0);
        interface_graphique_princ.add(separateur);
    }

    @Override
    public JPanel getJPanel() 
    {
        return interface_graphique_princ;
    }

    @Override
    public void mise_a_jour()
    {
        
    }
}