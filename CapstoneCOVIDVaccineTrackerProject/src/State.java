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
	int mapWidth, mapHeight;
	int screenHeight, screenWidth;
	int heightDiff;
	
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
	}
	
	
	/**
	 * draws the graph generated from the StatesGraphics class and writes all the numerical statistics
	 * 
	 * @param surface
	 */
	public void draw(PApplet surface) {
		map = surface.loadImage("maps/" + name +".png");
		mapWidth = map.width;
		mapHeight = map.height;
		screenHeight = surface.height;
		screenWidth = surface.width;
		if (screenHeight < screenWidth) {
			map.resize(0, (screenHeight/3)*2);
		} else {
			map.resize(screenWidth/2, 0);
		}
		heightDiff = screenHeight - mapHeight;
		surface.image(map, 10, screenHeight/3);
		surface.strokeWeight(5);
		surface.line(screenWidth-40, 10, screenWidth-10, 10);
		surface.line(screenWidth-40, 20, screenWidth-10, 20);
		surface.line(screenWidth-40, 30, screenWidth-10, 30);
		surface.strokeWeight(1);
	}
	
	/**
	 * 
	 * @return name of the state
	 */
	public String getName() {
		return name;
	}
}
