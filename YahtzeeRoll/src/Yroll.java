import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;

/**
 * Yroll implements ActionListener and initalizes the frame, panel, and calling of a thread.
 * @author mcaird22
 *
 */
public class Yroll implements ActionListener {

	private JFrame frame;
	newLabels[] dice = new newLabels[5];  // number of dices
	JButton button;
	int Total;
	JLabel sum;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Yroll window = new Yroll();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * 
	 */
	public Yroll() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1200, 300);
		frame.getContentPane();
		frame.getContentPane().setLayout(new FlowLayout());
		
		button = new JButton("Push");  // Push button to roll dice
		button.setBounds(180, 5, 89, 23);
		button.addActionListener(this);
		frame.getContentPane().add(button);
		frame.add(button);
		
		sum = new JLabel(); // a new label is printed to display total
		
		for (int i =0; i< dice.length; i++) { // for amount of dice, make new jLabels
			JLabel l = new JLabel();		  // Pass through the new Labels constructor
			dice[i] = new newLabels(l);
			frame.getContentPane().add(l);
			frame.add(l);
		}
		
		frame.setVisible(true);
		
	}

	/**
	 * Action gets called any time the button is hit. This calls the Thread in newLabels.java
	 * @Override
	 * @param e
	 */
	public void actionPerformed(ActionEvent e) { // if the button gets hit, make a new thread
		if( e.getSource() == button) {
			Total = 0;
			for (int i = 0; i < dice.length; i++) {
				Thread d = new Thread(dice[i]);
				d.start();
				
				try {   // makes sure the thread is done
					d.join();
				} catch (InterruptedException e1) {
					System.out.println("Its not done!");
					e1.printStackTrace();
				}
				Total += newLabels.getSum(); // adds the sum from dice
			}
			frame.getContentPane().add(sum);
			sum.setText("Sum Total: " + Total); // displays the dice total
			frame.add(sum);
			
		}
	}
}
