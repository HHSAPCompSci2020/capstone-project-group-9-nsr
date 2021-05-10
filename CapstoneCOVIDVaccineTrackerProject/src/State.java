import java.text.ParseException;

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
	private int heightDiff;
	
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
		graph.drawGraph(surface, 500.0, 10.0, 400.0, 400.0);
		
		surface.image(map, 10, heightDiff/4);
		surface.circle(0, screenHeight/2, 100);
//		System.out.println("HeightDiff: " + heightDiff);

		drawDropDownButton(surface, screenWidth);
	}
	
	/**
	 * 
	 * @return name of the state
	 */
	public String getName() {
		return name;
	}
}
