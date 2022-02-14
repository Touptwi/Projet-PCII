package Vue;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

import Modele.Etat;
import Modele.Ressource;
import Modele.Ressource.Type;

public class IE_Ressource implements InterfaceEntitee
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
        
        String ressource_type = "";
		if(r.getType() == Type.OR) ressource_type = "or";
		else if(r.getType() == Type.RUBIS) ressource_type = "rubis";
		else if(r.getType() == Type.EMERAUDE) ressource_type = "emeraude";
		else if(r.getType() == Type.SAPHIR) ressource_type = "saphir";
		
        JTextField nameAndQuantity = new JTextField(ressource_type + " " + r.getQuantitee(), 20);
        
        //mise en page via un SPLITSPANE
        separateur = new JSplitPane(JSplitPane.VERTICAL_SPLIT, icone, nameAndQuantity);
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

