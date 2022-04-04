package Vue;

import Controleur.Control;
import Modele.Entites.Entite;
import Modele.Etat;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serial;

public class Affichage extends JSplitPane
{
    /** Java est maintenant tres content. */
	@Serial
    private static final long serialVersionUID = 1L;

    public Etat getEtat() {
        return etat;
    }

    private Etat etat;
    private final int taille_case = 100;

    public final JFrame fenetre;
    
    private JPanel interface_entitee_courante = null;
    private JSplitPane interfaces = new JSplitPane(JSplitPane.VERTICAL_SPLIT); //la zone droite contenant l'interface et le score
    private JPanel score = new JPanel(); //le jpanel contenant les informations a propos de la partie
    private JProgressBar temps = new JProgressBar(); //progress bar indiquant le temps restant

    private Thread thread_rafraichissement;

    /** Initialise une fenetre qui se rafraichi, avec un controller et un etat qu'on utilisera comme modele a afficher.
     * @param e : Etat sur lequel se baser pour l'affichage.
     */
    public Affichage(Etat e) 
    {
        etat = e;        

        fenetre = new JFrame("FlowerCraft"); //la fenêtre principale du jeu
        fenetre.setResizable(false);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setPreferredSize(new Dimension(etat.getLargeur() , etat.getHauteur())); //la fenêtre princ

        this.setFocusable(true);

        this.setLeftComponent(new JPanel()); //la partie grille
        this.setDividerSize(0);
        this.setDividerLocation(1/1.4);
        this.setRightComponent(null); //doit être initialisé a null sinon le split spane recouvre la grille et empeche le controleur de fonctionner

        score.setLayout(new BoxLayout(score,BoxLayout.Y_AXIS)); //assure que les elements s'affiche les uns en dessous des autres
        score.add(new JLabel("score: " + etat.getScore()));
        score.add(new JLabel("temps restant:"));
        score.add(temps);

        temps.setMaximum(etat.getDuree());
        //temps.setSize(80,10);

        //interfaces.setDividerSize(0);
        interfaces.setTopComponent(interface_entitee_courante);
        interfaces.setBottomComponent(score);
        interfaces.setResizeWeight(0.9); //assure que l'element du haut (ici l'interface de l'entité sélectionnée) prennent
                                         //90% de l'espace disponible


        fenetre.add(this);
        fenetre.pack();
        fenetre.setVisible(true);
        thread_rafraichissement = new ReAffichage(this);
        thread_rafraichissement.start();

        this.addMouseListener(new Control(etat, this));
    }

    
    public int getTailleCase() { return this.taille_case; }
    
    /** Ajoute l'affichage de l'entitee selectionnee dans la grille au split pane  interfaces*/
    public void 
    selectionnerEntitee()
    {
    	if(interface_entitee_courante != null)
        {
            interfaces.remove(interface_entitee_courante);
        }
    	Entite selected = etat.getGrille().getSelectedEntitee(); //on récupère l'entité sélectionnée
    	if(selected == null) interface_entitee_courante = null;
    	else
    	{
    		interface_entitee_courante = selected.getInterfaceEntitee().getJPanel(); //on récupère son interface
            this.setDividerLocation(1/1.4);
            interfaces.setTopComponent(interface_entitee_courante);
            interfaces.setDividerSize(0);
            this.setRightComponent(interfaces);
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

    /** Affichage de la grille */
    private void drawGrille(Graphics g){
        try {
            BufferedImage img = ImageIO.read(new File("Images/Gazon/Gazon.png"));
            BufferedImage img_ = ImageIO.read(new File("Images/Gazon/Gazon_.png"));
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

    /** Affichage des Entitee */
    private void drawEntitee(Graphics g) {
        for(int j = 0; j < etat.getHauteurGrille(); ++j)
        {
            for(int i = 0; i < etat.getLargeurGrille(); ++i)
            {
                Entite entitee = etat.getEntitee(i, j);
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

    /**
     * Renvoie l'interface de l'entitée selectionnée si elle existe
     * @return l'entitée de l'entité selectionnée et null si il n'y en a pas
     */
    public InterfaceEntite getInterface_entitee_courante() {
        Entite entitee = etat.getGrille().getSelectedEntitee();
        if (entitee == null)
            return null;
        else
            return entitee.interface_e;
    }

    /**
     * Cette fonction est appellée par la classe Reaffichage pour mettre a jour le JPanel contenant le score et
     * le temps de jeu restant.
     */
    public void mise_a_jour_score()
    {
        JLabel val_score = (JLabel) score.getComponent(0);
        val_score.setText("Score: "+ etat.getScore());
        temps.setValue(etat.getDuree()-etat.getCountdown().getTimeLeft());
    }
}