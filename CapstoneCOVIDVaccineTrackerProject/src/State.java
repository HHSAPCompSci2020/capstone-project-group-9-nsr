/**
 * 
 * @author roopa
 * date: 05/07/2021
 * @revision 1
 *
 */
public class State extends Frame{
	StatesGraphics graph;
	
	/*
	 * calls the no-args StatesGraphic constructor
	 */
	public State() {
		super("US_MAP");
		graph = new StatesGraphics();
	}
	
	/*
	 * specifies the state for which we are drawing the graphs and obtaining statistics, also sets the picture of the state
	 * @param String stateName full name of the state
	 */
	public State(String stateName) {
		super(stateName+".png");
		graph = new StatesGraphics(stateName);
	}
	
	
	
	/*
	 * draws the graph generated from the StatesGraphics class and writes all the numerical statistics
	 */
	public void draw() {
		graph.drawGraph(500, 100, 100, 100);
	}
}
