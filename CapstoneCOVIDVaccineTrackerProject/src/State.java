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
		
		
		if (screenHeight < screenWidth) {
			if (mapWidth > screenWidth/2) {
				map.resize(screenWidth/2, 0);
			} else {
				map.resize(0, (screenHeight-screenHeight/60));
			}
		} else {
			map.resize(screenWidth/2, 0);
		}
//		
//		graphX = 5*(screenWidth/9);
//		graphY = screenHeight/20;
//		surface.textAlign(LEFT);
//		graph.drawGraph(surface, graphX, graphY, graphWidth, graphHeight);
		
		graph.draw(surface);
		
		surface.image(map, screenWidth/100, screenHeight/100);

		drawDropDownButton(surface, screenWidth);
	}
	
	/**
	 * @return name of the state
	 */
	public String getName() {
		return name;
	}
}
