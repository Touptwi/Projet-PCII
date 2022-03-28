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
		final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
	    final Runnable runnable = new Runnable() 
	    {
	        public void run() 
	        {
	        	countdownValue--;
	            if (countdownValue < 0) 
	            {
	            	scheduler.shutdown();
	            	etat.stop();
		            
		            JFrame jFrame = new JFrame();

		            JDialog jd = new JDialog(jFrame);

		            jd.setLayout(new FlowLayout());

		            jd.setBounds(500, 300, 400, 300);

		            JLabel jLabel = new JLabel("Your score :" + etat.getScore());
		            
		            JButton jButton = new JButton("Exit game");
		            jButton.addActionListener( (ActionListener) new ActionListener() 
		            {
						@Override
						public void actionPerformed(ActionEvent e) { jd.setVisible(false); }
		            });

		            jd.add(jLabel);
		            jd.add(jButton);
		            jd.setVisible(true);
	            }
	        }
	    };
	    scheduler.scheduleAtFixedRate(runnable, 0, 1, TimeUnit.SECONDS);
	}
}
