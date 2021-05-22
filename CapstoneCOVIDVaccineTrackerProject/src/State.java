import java.util.ArrayList;

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
	private StatesGraphics graph;
	private String name;
	private PImage map;
	private int mapWidth, mapHeight;
	private int screenHeight, screenWidth;
//	private double graphWidth, graphHeight, graphX, graphY;
//	
//	private int stateButtonX = 0;
//	private int stateButtonY = 0;
//	private float stateButtonDist = 0;
//	private int stateButtonWidth = 0;
//	private int stateButtonHeight = 0;
	
	private float buttonDistance;
	private int buttonWidth, buttonHeight;
	private int buttonX, buttonY;
	
	private ArrayList<String> vaccine;
	
	private boolean clickVaxAvailable, clickVaxDistributed, clickDistPercent, clickPeopleVaxed, clickTotalVaxPercent, clickFullyVaxed, clickFullyVaxedPercent;
	private String vaxAvailable, vaxDist, distPercent, peopleVaxed, vaxedPercent, peopleFullyVaxed, fullyVaxedPercent;
	private String vaxAvailableString, vaxDistString, distPercentString, peopleVaxedString, vaxedPercentString, peopleFullyVaxedString, fullyVaxedPercentString;
	private String vaxAvailableDisplay, vaxDistDisplay, distPercentDisplay, peopleVaxedDisplay, vaxedPercentDisplay, peopleFullyVaxedDisplay, fullyVaxedPercentDisplay;
	
	private int r, g, b, r1, g1, b1;
	
	/**
	 * calls the no-args StatesGraphic constructor
	 */
	public State() {
		super("US_MAP.png");
		graph = new StatesGraphics();
		r = 99;
		g = 207;
		b = 248;
		r1= 214;
		g1= 244;
		b1= 255;
	}
	
	/**
	 * specifies the state for which we are drawing the graphs and obtaining statistics, also sets the picture of the state
	 * @param stateName full name of the state
	 */
	public State(String stateName) {
		super(stateName+".png");
		graph = new StatesGraphics(stateName);
		name = stateName;
		r = 99;
		g = 207;
		b = 248;
		r1= 214;
		g1= 244;
		b1= 255;
		clickVaxAvailable = false;
		clickVaxDistributed = false;
		clickDistPercent = false;
		clickPeopleVaxed = false;
		clickTotalVaxPercent = false;
		clickFullyVaxed = false;
		clickFullyVaxedPercent = false;
		vaxAvailable = " " ;
		vaxDist = " " ;
		distPercent = " " ;
		peopleVaxed = " " ;
		vaxedPercent = " " ;
		peopleFullyVaxed = " " ;
		fullyVaxedPercent = " " ;
		vaxAvailableString = "total vaccinations available";
		vaxDistString = "total vaccinations distributed";
		distPercentString = "total distribution percentage";
		peopleVaxedString = "people vaccinated";
		vaxedPercentString = "total vaccinations percentage";
		peopleFullyVaxedString = "people fully vaccinated";
		fullyVaxedPercentString = "fully vaccinated percentage";
		vaxAvailableDisplay = " " ;
		vaxDistDisplay = " " ;
		distPercentDisplay = " " ;
		peopleVaxedDisplay = " " ;
		vaxedPercentDisplay = " " ;
		peopleFullyVaxedDisplay = " " ;
		fullyVaxedPercentDisplay = " " ;
	}
	
	
	/**
	 * draws the graph generated from the StatesGraphics class and writes all the numerical statistics
	 * @param surface
	 */
	public void draw(PApplet surface) {
		vaccine = graph.getVaccineInfo();
		map = surface.loadImage("maps/" + name +".png");
		mapWidth = map.width;
		mapHeight = map.height;
		screenHeight = surface.height;
		screenWidth = surface.width;
//		stateButtonX = graph.getButtonX();
//		stateButtonY = graph.getButtonY();
//		stateButtonDist = graph.getButtonDistance();
//		stateButtonWidth = graph.getButtonWidth();
//		stateButtonHeight = graph.getButtonHeight();
		buttonDistance = surface.height/20;
		buttonWidth = surface.width/3;
		buttonHeight = surface.height/25;
		buttonX = (surface.width/20);
		buttonY =  surface.height* 11 /20;
		vaxAvailable = vaccine.get(2);
		vaxDist = vaccine.get(3);
		distPercent = vaccine.get(9);
		peopleVaxed = vaccine.get(4);
		vaxedPercent = vaccine.get(6) + "% of the state population";
		peopleFullyVaxed = vaccine.get(7);
		fullyVaxedPercent = vaccine.get(5) + "% of the state population";
		
		if (screenHeight < screenWidth) {
			if (mapWidth > screenWidth/2) {
				map.resize(screenWidth/2, 0);
			} 
			if (mapHeight > screenHeight/2) {
				map.resize(0, (screenHeight/2));
			}
		} else {
			map.resize(screenWidth/2, 0);
		}
		
		graph.draw(surface);
		
		surface.image(map, screenWidth/100, screenHeight/100);

		drawDropDownButton(surface, screenWidth);
		
		if (clickVaxAvailable) {
			vaxAvailableDisplay = (vaxAvailable);
		} else {
			vaxAvailableDisplay = (vaxAvailableString);
		}
		System.out.println(clickVaxAvailable);
		System.out.println("Top left: " + buttonX + ", " + buttonY);
		System.out.println("Top right: " + buttonX+buttonWidth + ", " + buttonY);
		System.out.println("Bottom left: " + buttonX + ", " + buttonY+buttonHeight);
		System.out.println("Bottom right: " + buttonX+buttonWidth + ", " + buttonY+buttonHeight);
		System.out.println(surface.mouseX + ", " + surface.mouseY);
//		if (clickVaxDistributed) {
//			vaxDistDisplay = vaxDist;
//		} else {
//			vaxDistDisplay = vaxDistString;
//		}
//		if (clickDistPercent) {
//			distPercentDisplay = distPercent;
//		} else {
//			distPercentDisplay = distPercentString;
//		}
//		if (clickPeopleVaxed) {
//			peopleVaxedDisplay = peopleVaxed;
//		} else {
//			peopleVaxedDisplay = peopleVaxedString;
//		}
//		if (clickTotalVaxPercent) {
//			vaxedPercentDisplay = vaxedPercent;
//		} else {
//			vaxedPercentDisplay = vaxedPercentString;
//		}
//		if (clickFullyVaxed) {
//			peopleFullyVaxedDisplay = peopleFullyVaxed;
//		} else {
//			peopleFullyVaxedDisplay = peopleFullyVaxedString;
//		}
//		if (clickFullyVaxedPercent) {
//			fullyVaxedPercentDisplay = fullyVaxedPercent;
//		} else {
//			fullyVaxedPercentDisplay = fullyVaxedPercentString;
//		}
		
		if (graph.getInfoAvailable()) {
			writeStats(surface, (float)surface.height/45, (float)surface.height/60, (float)surface.height/50);
		}
//		buttons(surface);
	}
	
	public void writeStats(PApplet p, float titleSize, float writingSize, float leading) {
		p.textSize(writingSize);
		p.textLeading(leading);
		
		drawButton(p, buttonX, (int)(buttonY+buttonDistance), buttonWidth, buttonHeight,vaxAvailableDisplay, 218);

//		drawButton(p, buttonX, (int)(buttonY+buttonDistance), buttonWidth, buttonHeight,vaxAvailableDisplay, r1, g1, b1);
//		drawButton(p, buttonX, (int)(buttonY+(2*buttonDistance)), buttonWidth, buttonHeight, vaxDist, r1, g1, b1);
//		drawButton(p, buttonX, (int)(buttonY+(3*buttonDistance)), buttonWidth, buttonHeight, distPercent, r1, g1, b1);
//		drawButton(p, buttonX, (int)(buttonY+(4*buttonDistance)), buttonWidth, buttonHeight, peopleVaxed, r1, g1, b1);
//		drawButton(p, buttonX, (int)(buttonY+(5*buttonDistance)), buttonWidth, buttonHeight, vaxedPercent, r1, g1, b1);
//		drawButton(p, buttonX, (int)(buttonY+(6*buttonDistance)), buttonWidth, buttonHeight, peopleFullyVaxed, r1, g1, b1);
//		drawButton(p, buttonX, (int)(buttonY+(7*buttonDistance)), buttonWidth, buttonHeight, fullyVaxedPercent, r1, g1, b1);
	}

//	public boolean overButton(PApplet p, int x, int y, int w, int h) {
//	  if (p.mouseX > x && p.mouseX < (x + w) && p.mouseY > y && p.mouseY < (y + h)) {
//	    return true;
//	  }
//	  return false;
//	}
	
	public void drawButton(PApplet surface, int x, int y, int w, int h, String text, int fillColor) {
		surface.fill(fillColor);
		surface.rect(x, y, w, h, 10);
    	surface.textAlign(surface.CENTER, surface.CENTER);
    	surface.fill(0);
    	surface.text(text, x + w/2, y + h/2);
    	surface.noFill();
	}
	//get methods
	/**
	 * @return name of the state
	 */
	public String getName() {
		return name;
	}
	
	public StatesGraphics getGraph() {
		return graph;
	}
	
	public int getButtonX() {
		return graph.getButtonX();
	}
	
	public int getButtonY() {
		return graph.getButtonY();
	}
	
	public int getButtonWidth() {
		return graph.getButtonWidth();
	}
	
	public int getButtonHeight() {
		return graph.getButtonHeight();
	}
	
	public float getButtonDistance() {
		return graph.getButtonDistance();
	}
		
	public boolean getClickVaxAvailable() {
		return clickVaxAvailable;
	}
	
//	public boolean getClickVaxDistributed() {
//		return graph.getClickVaxDistributed();
//	}
//	
//	public boolean getClickDistPercent() {
//		return graph.getClickDistPercent();
//	}
//	
//	public boolean getClickPeopleVaxed() {
//		return graph.getClickPeopleVaxed();
//	}
//	
//	public boolean getClickTotalVaxPercent() {
//		return graph.getClickTotalVaxPercent();
//	}
//	
//	public boolean getClickFullyVaxed() {
//		return graph.getClickFullyVaxed();
//	}
//	
//	public boolean getClickFullyVaxedPercent() {
//		return graph.getClickFullyVaxedPercent();
//	}
	
	public String getVaxAvailable() {
		return vaxAvailable;
	}
	
	public String getVaxAvailableDisplay() {
		return vaxAvailableDisplay;
	}
	
	public String getVaxAvailableString() {
		return vaxAvailableString;
	}
	
//	public String getVaxDist() {
//		return graph.getVaxDist();
//	}
//	
//	public String getDistPercent() {
//		return graph.getDistPercent();
//	}
//	
//	public String getPeopleVaxed() {
//		return graph.getPeopleVaxed();
//	}
//	
//	public String getVaxedPercent() {
//		return graph.getVaxedPercent();
//	}
//	
//	public String getPeopleFullyVaxed() {
//		return graph.getPeopleFullyVaxed();
//	}
//	
//	public String getFullyVaxedPercent() {
//		return graph.getFullyVaxedPercent();
//	}
	
	
	//set methods
	public void setClickVaxAvailable(boolean state) {
		clickVaxAvailable = (state);
	}
	
//	public void setClickVaxDistributed(boolean state) {
//		clickVaxDistributed = (state);
//	}
//	
//	public void setClickDistPercent(boolean state) {
//		clickDistPercent = (state);
//	}
//	
//	public void setClickPeopleVaxed(boolean state) {
//		graph.setClickPeopleVaxed(state);
//	}
//	
//	public void setClickTotalVaxPercent(boolean state) {
//		graph.setClickTotalVaxPercent(state);
//	}
//	
//	public void setClickFullyVaxed(boolean state) {
//		graph.setClickFullyVaxed(state);
//	}
//	
//	public void setClickFullyVaxedPercent(boolean state) {
//		graph.setClickFullyVaxedPercent(state);
//	}
}
