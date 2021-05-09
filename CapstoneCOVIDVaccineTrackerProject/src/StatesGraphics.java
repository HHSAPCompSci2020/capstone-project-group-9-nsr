/**
 * @author roopa, sophie, nodoka
 */
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
	private ArrayList<String> dates;
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
	
	/**
	 * draws graph by figuring out all points of where the graph needs to be plotted.
	 * 
	 * @param x x coordinate of the left up corner of where the graph is drawn
	 * @param y y coordinate of the left up corner of where the graph is drawn
	 * @param width width of the rectangle the graph is in
	 * @param height height of the rectangle the graph is in
	 */
	@SuppressWarnings("unchecked")
	public void drawGraph(double x, double y, double width, double height) {
		
		Stats stat = new Stats();
		ArrayList<Double> cases = (ArrayList<Double>) stat.getCasesData(name, 2);
		ArrayList<String> dates = (ArrayList<String>) stat.getCasesData(name, 1);
		
		//figure out the biggest number of the arraylist to scale y
		double b = cases.get(0); //write this as a text on top of the yaxis
		
		for(int i = 1; i < cases.size(); i++) {
			if(b < cases.get(i)) {
				b = cases.get(i);
			}
		}
		
		//figure out the first and the last date to scale x
		String first = dates.get(0);
		
		
		//calculate the coordinates of the points and store them and draw graph
		
		

	}

}
