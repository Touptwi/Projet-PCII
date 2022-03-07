package Vue;

import Controleur.Control;
import Modele.Entitee;
import Modele.Etat;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;

public class Affichage extends JSplitPane
{
    /** Java est maintenant tres content. */
	private static final long serialVersionUID = 1L;
	private Etat etat;
    private final int taille_case = 100;
    
    private JPanel interface_entitee_courante = null;
    
    /** Initialise une fenetre qui se rafraichi, avec un controller et un etat qu'on utilisera comme modele a afficher.
     * @param e : Etat sur lequel se baser pour l'affichage.
     */
    public Affichage(Etat e) 
    {
        etat = e;        

        JFrame fenetre = new JFrame("FlowerCraft");
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setPreferredSize(new Dimension(etat.getLargeur() , etat.getHauteur()));
        this.addMouseListener(new Control(etat, this));
        this.setFocusable(true);

        this.setLeftComponent(new JPanel());
        this.setRightComponent(interface_entitee_courante);
        this.setDividerSize(0);
        
        fenetre.add(this);
        fenetre.pack();
        fenetre.setVisible(true);
    }
    
    public int getTailleCase() { return this.taille_case; }
    
    /** Ajoute l'affichage de l'entitee selectionnee dans la grille */
    public void 
    selectionnerEntitee()
    {
    	if(interface_entitee_courante != null) this.remove(interface_entitee_courante);
    	Entitee selected = etat.getGrille().getSelectedEntitee();
    	if(selected == null) interface_entitee_courante = null;
    	else
    	{
    		interface_entitee_courante = selected.getInterfaceEntitee().getJPanel();
            this.setDividerLocation(1/1.4);
            this.setRightComponent(interface_entitee_courante);
		}
    }

    /**
     * va adapter la coordonnée x transmise à la taille acutelle de la fenetre
     * @param x la coordonnée initiale
     * @return la coordonnée x adaptée
     */
    public int fitX(int x) { return (int)((double)x/etat.getLargeur()*this.getWidth()); }

    /**
     * va adapter la coordonnée y  transmise à la taille acutelle de la fenetre
     * @param y la coordonnée initiale
     * @return la coordonnée x adaptée
     */
    public int fitY(int y) {
        return (int)((double)y/etat.getHauteur()*this.getHeight());
    }


    private void drawGrille(Graphics g){

        try {
            BufferedImage img = ImageIO.read(new File("Images/Gazon.png"));
            BufferedImage img_ = ImageIO.read(new File("Images/Gazon_.png"));
            for (int i = 0; i < etat.getLargeurGrille(); i++) {
                for (int j = 0; j < etat.getHauteurGrille(); j++) {
                    if((i+j)%2==0) {
                        g.drawImage(img, fitX(j*taille_case), fitY(i*taille_case), fitX(taille_case), fitY(taille_case), null);
                    } else {
                        g.drawImage(img_, fitX(j*taille_case), fitY(i*taille_case), fitX(taille_case), fitY(taille_case), null);
                    }
                }
            }
        }
        catch (IOException E) { E.printStackTrace(); }

        for(int i = 0; i <= etat.getLargeurGrille(); i++) {
            g.drawLine(0, fitY(taille_case*i), fitX(taille_case*etat.getLargeurGrille()), fitY(taille_case * i));
        }
        for(int i = 0; i <= etat.getHauteurGrille(); i++) {
            g.drawLine( fitX(taille_case*i), 0, fitX(taille_case*i), fitY(taille_case*etat.getHauteurGrille()));
        }
    }


    private void drawEntitee(Graphics g) {
        for(int j = 0; j < etat.getHauteurGrille(); ++j)
        {
            for(int i = 0; i < etat.getLargeurGrille(); ++i)
            {
                Entitee entitee = etat.getEntitee(i, j);
                if(entitee == null) continue;
                ((Graphics2D) g).translate(fitX(i*taille_case), fitY(j*taille_case));
                entitee.getSprite().draw(g, this);
                ((Graphics2D) g).translate(fitX(-i*taille_case), fitY(-j*taille_case));
            }
        }
    }
    
    /** Affichage a l'ecran, divise en plusieurs sections :
     * - Affichage du terrain (grille)
     * - Affichage des Entitees (points, sprites plus tard)
     * @param g : Graphic sur lequel afficher.
     */
    @Override
    public void paint(Graphics g) 
    {
    	super.paint(g);

        drawGrille(g);
        drawEntitee(g);
    }
}
