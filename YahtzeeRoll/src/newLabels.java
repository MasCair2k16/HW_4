
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * NewLabels implements Runnable. this is where the thread takes place and produces the JLabels dice.
 * @author mcaird22
 *
 */
public class newLabels implements Runnable {
	private JLabel _label;
	final String dir = System.getProperty("user.dir"); // user dir
	static int sum = 0;
	public newLabels(JLabel label) {  // Constructor
		_label = label;
		System.out.println(dir);
	}
	
	/**
	 * Returns the total sum of dice faces to the Yroll class when called
	 * @return sum
	 */
	static int getSum() {return sum;} // to get the sum
	
	/**
	 * This runs the thread and produces the Dice JLabels. Each time the button is hit, the rice are randomized. More
	 * in the comments in the function.
	 */
	public void run() {
		int rolls = (int) (Math.random() * 10) + 5; // randomizes the number of rolls
		sum = 0;
		int d = 0;
		for (int i = 0; i < rolls; i++) { // for the amount of rolls,rand numbers will then be used to pick dice
			d = ((int) (Math.random()*5)) + 1;
			
			_label.setIcon(new ImageIcon(dir + "\\dice"+ d +".jpg")); // picture of dice
			_label.setVisible(true);
		}
		sum=+d;  // sums the numbers
	}

}
