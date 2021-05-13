import processing.core.PApplet;
import processing.core.PImage;

/**
 * 
 * @author roopa
 * date: 05/07/2021
 * @revision 1
 *
 */
public class State extends Frame{
	StatesGraphics graph;
	String name;
	PImage map;
	private int mapWidth, mapHeight;
	private int screenHeight, screenWidth;
	private double graphWidth, graphHeight, graphX, graphY;
	
	/**
	 * calls the no-args StatesGraphic constructor
	 */
	public State() {
		super("US_MAP.png");
		graph = new StatesGraphics();
	}
	
	/**
	 * specifies the state for which we are drawing the graphs and obtaining statistics, also sets the picture of the state
	 * @param stateName full name of the state
	 */
	public State(String stateName) {
		super(stateName+".png");
		graph = new StatesGraphics(stateName);
		name = stateName; 
//		stats = new Stats();
//		stateStats = stats.getVaccinationInfo(name);
//		System.out.println(stateStats.toString());
	}
	
	
	/**
	 * draws the graph generated from the StatesGraphics class and writes all the numerical statistics
	 * @param surface
	 */
	public void draw(PApplet surface) {
		map = surface.loadImage("maps/" + name +".png");
		mapWidth = map.width;
		mapHeight = map.height;
		screenHeight = surface.height;
		screenWidth = surface.width;
		if (mapWidth > screenWidth/2) {
			map.resize(screenWidth/2, 0);
		}
		if (screenHeight < screenWidth) {
			map.resize(0, (screenHeight/2));
		} else {
			map.resize(screenWidth/2, 0);
		}
		if (screenHeight<screenWidth) {
			graphWidth = (screenHeight/2);
			graphHeight = (screenHeight/2);
		} else {
			graphWidth = (screenWidth/2);
			graphHeight = (screenWidth/2);
		}
		graphX = 5*(screenWidth/9);
		graphY = screenHeight/20;
		
		graph.drawGraph(surface, graphX, graphY, graphWidth, graphHeight);
		
		surface.image(map, 10, 10);

		drawDropDownButton(surface, screenWidth);
	}
	
	/**
	 * @return name of the state
	 */
	public String getName() {
		return name;
	}
}
