import java.util.ArrayList;

import javax.swing.JButton;

/**
 * This class gets data from stats and creates data graphics.
 * 
 * @author 
 *
 */
public class StatesGraphics {
	private String name;
	private ArrayList<Double> numberOfVaccinesAvailable;
	private ArrayList<Double> numberOfVaccinesGiven;
	private ArrayList<Double> percentGiven;
	private JButton returnToHome;
	/**constructor
	 * if no parameter is inputted, the name is set to null
	 */
	public StatesGraphics() {
		name = null;
	}
	/**constructor
	 * sets the name to the parameter String state
	 * @param String state
	 */
	public StatesGraphics(String state) {
		name = state;
	}
	/**adds to the number of vaccines available
	 * @param int num of how many more vaccines avaliable 
	 */
	public void addAvailable(int num) {
		//numberOfVaccinesAvailable += num;
	}
	/**adds to the number of vaccines given
	 * @param int num of how many more vaccines given added
	 */
	public void addGiven(int num) {
		//numberOfVaccinesGiven += num;
	}
	/** set the percent of given vaccines
	 * @parameter double percent of given vaccines
	 */
	public void setPercent(double percent) {
		//percentGiven = percent;
	}
	/**returns the name of the state
	 * @return name String
	 */
	public String getName() {
		return name;
	}
	/**draws the graph of the state
	 * 
	 */
	public void drawGraph() {
		
		Stats stat = new Stats();
		numberOfVaccinesAvailable = stat.readData("VaccineAvailable", name);
		numberOfVaccinesGiven = stat.readData("VaccineGiven", name);
		percentGiven = stat.readData("PercentGiven", name);
		
		

	}

}
