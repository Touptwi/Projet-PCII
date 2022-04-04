package Modele;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Countdown 
{
	private int countdownValue;
	private Etat etat;
	
	public Countdown(Etat e, int duration)
	{
		this.etat = e;
		this.countdownValue = duration;
		this.start();
	}
	
	public int getTimeLeft() { return this.countdownValue; }
	public boolean isOver() { return this.countdownValue <= 0; }
	
	public void addTime(int seconds) { this.countdownValue += seconds; }
	public void setTime(int duration) { this.countdownValue = duration; }
	
	public void start()
	{
		//scheduler nous permet de lancer un runnable a un intervalle de temps donne
		final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
		
		
	    final Runnable runnable = new Runnable() 
	    {
	        public void run() 
	        {
	        	countdownValue--; //Update du decompte (on decrois jusqu'a 0)
	            if (countdownValue < 0) 
	            {
	            	scheduler.shutdown(); //on arrete la programmation du run
	            	etat.stop(); //on arrete la boucle de jeu
	            	
	            	
		            //Fenetre de dialogue
		            JFrame jFrame = new JFrame();
		            JDialog jd = new JDialog(jFrame);
		            jd.setLayout(new FlowLayout());
		            jd.setBounds(500, 300, 400, 300);

		            //Affichage texte
		            JLabel jLabel = new JLabel("Your score :" + etat.getScore());
		            JButton jButton = new JButton("Exit game");
		            
		            // quand on clique sur "exit game", on termine le programme :
		            jButton.addActionListener( (ActionListener) new ActionListener()  
		            {
						@Override
						public void actionPerformed(ActionEvent e) { System.exit(1);}//jd.setVisible(false); }
		            });

		            jd.add(jLabel);
		            jd.add(jButton);
		            jd.setVisible(true); //On affiche finalement la fenetre
	            }
	        }
	    };
	    scheduler.scheduleAtFixedRate(runnable, 0, 1, TimeUnit.SECONDS); // on lance le scheduler avec une intervalle de temps de 1 seconde.
	}
}
