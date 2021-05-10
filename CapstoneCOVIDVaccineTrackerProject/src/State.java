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
		if (screenHeight<screenWidth) {
			graphWidth = 1*(screenHeight/3);
			graphHeight = 1*(screenHeight/3);
		} else {
			graphWidth = 1*(screenWidth/3);
			graphHeight = 1*(screenWidth/3);
		}
		graphX = 2*(screenWidth/3);
		graphY = screenHeight/20;
		
		graph.drawGraph(surface, graphX, graphY, graphWidth, graphHeight);
		
		surface.image(map, 10, heightDiff/4);
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
