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
	int widthDiff, heightDiff;
	
	/*
	 * calls the no-args StatesGraphic constructor
	 */
	public State() {
		super("US_MAP.png");
		graph = new StatesGraphics();
	}
	
	/*
	 * specifies the state for which we are drawing the graphs and obtaining statistics, also sets the picture of the state
	 * @param String stateName full name of the state
	 */
	public State(String stateName) {
		super(stateName+".png");
		graph = new StatesGraphics(stateName);
		name = stateName;
	}
	
	
	
	/*
	 * draws the graph generated from the StatesGraphics class and writes all the numerical statistics
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
		widthDiff = screenWidth - mapWidth;
		heightDiff = screenHeight - mapHeight;
		surface.image(map, 10, heightDiff/2);
		
	}
	
	public String getName() {
		return name;
	}
}
