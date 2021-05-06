/*
 * @author: Roopa Srinivas
 * date: 05/06/2021
 * @revision: 1 
 */
public class State {
	StatesGraphics graph;
	
	/*
	 * calls the no-args StatesGraphic constructor
	 */
	public State() {
		graph = new StatesGraphics();
	}
	
	/*
	 * specifies the state for which we are drawing the graphs and obtaining statistics
	 */
	public State(String stateName) {
		graph = new StatesGraphics(stateName);
	}
	
	/*
	 * draws the graph generated from the StatesGraphics class and writes all the numerical statistics
	 */
	public void draw() {
		
	}
}
