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
	private PImage map, infoGraph;
	private int mapWidth, mapHeight;
	private int screenHeight, screenWidth;
	
	private float buttonDistance;
	private int buttonWidth, buttonHeight;
	private int buttonX, buttonY;
	
	private ArrayList<String> vaccine;
	
	private boolean clickVaxAvailable, clickVaxDistributed, clickDistPercent, clickPeopleVaxed, clickTotalVaxPercent, clickFullyVaxed, clickFullyVaxedPercent;
	private String vaxAvailable, vaxDist, distPercent, peopleVaxed, vaxedPercent, peopleFullyVaxed, fullyVaxedPercent;
	private String vaxAvailableString, vaxDistString, distPercentString, peopleVaxedString, vaxedPercentString, peopleFullyVaxedString, fullyVaxedPercentString;
	private String vaxAvailableDisplay, vaxDistDisplay, distPercentDisplay, peopleVaxedDisplay, vaxedPercentDisplay, peopleFullyVaxedDisplay, fullyVaxedPercentDisplay;
	
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
		vaxAvailableDisplay = "" ;
		vaxDistDisplay = "" ;
		distPercentDisplay = "" ;
		peopleVaxedDisplay = "" ;
		vaxedPercentDisplay = "" ;
		peopleFullyVaxedDisplay = "" ;
		fullyVaxedPercentDisplay = "" ;
	}
	
	/**
	 * draws the graph generated from the StatesGraphics class and writes all the numerical statistics
	 * @param surface
	 */
	public void draw(PApplet surface) {
		if (vaccine == null) {
			vaccine = graph.getVaccineInfo();
			if (graph.getInfoAvailable()) {
				vaxAvailable = vaccine.get(2);
				vaxDist = vaccine.get(3);
				distPercent = vaccine.get(9) + "% of the state population";
				peopleVaxed = vaccine.get(4);
				vaxedPercent = vaccine.get(6) + "% of the state population";
				peopleFullyVaxed = vaccine.get(7);
				fullyVaxedPercent = vaccine.get(5) + "% of the state population";
			}
			graph.createGraph(surface, 7*(surface.width/11), surface.height/20, name);
		}
		map = surface.loadImage("maps/" + name +".png");
		
		mapWidth = map.width;
		mapHeight = map.height;
		screenHeight = surface.height;
		screenWidth = surface.width;
		
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
		

		surface.image(map, screenWidth/100, screenHeight/100);
		
		infoGraph = surface.loadImage("maps/" + name + ".png");
		surface.image(infoGraph, (float)(7*(screenWidth/11)-(0.3*graph.getGraphWidth())), (float)(screenHeight/20-(0.1*graph.getGraphHeight())));

		drawDropDownButton(surface, screenWidth);
		
		
		
		
		graph.draw(surface);
		
		
		if (clickVaxAvailable) {
			vaxAvailableDisplay = (vaxAvailable);
		} else {
			vaxAvailableDisplay = (vaxAvailableString);
		}
		if (clickVaxDistributed) {
			vaxDistDisplay = vaxDist;
		} else {
			vaxDistDisplay = vaxDistString;
		}
		if (clickDistPercent) {
			distPercentDisplay = distPercent;
		} else {
			distPercentDisplay = distPercentString;
		}
		if (clickPeopleVaxed) {
			peopleVaxedDisplay = peopleVaxed;
		} else {
			peopleVaxedDisplay = peopleVaxedString;
		}
		if (clickTotalVaxPercent) {
			vaxedPercentDisplay = vaxedPercent;
		} else {
			vaxedPercentDisplay = vaxedPercentString;
		}
		if (clickFullyVaxed) {
			peopleFullyVaxedDisplay = peopleFullyVaxed;
		} else {
			peopleFullyVaxedDisplay = peopleFullyVaxedString;
		}
		if (clickFullyVaxedPercent) {
			fullyVaxedPercentDisplay = fullyVaxedPercent;
		} else {
			fullyVaxedPercentDisplay = fullyVaxedPercentString;
		}
		
	}

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
		return buttonX;
	}
	
	public int getButtonY() {
		return buttonY;
	}
	
	public int getButtonWidth() {
		return buttonWidth;
	}
	
	public int getButtonHeight() {
		return buttonHeight;
	}
	
	public float getButtonDistance() {
		return buttonDistance;
	}
		
	public boolean getClickVaxAvailable() {
		return clickVaxAvailable;
	}
	
	public boolean getClickVaxDistributed() {
		return clickVaxDistributed;
	}
	
	public boolean getClickDistPercent() {
		return clickDistPercent;
	}
	
	public boolean getClickPeopleVaxed() {
		return clickPeopleVaxed;
	}
	
	public boolean getClickTotalVaxPercent() {
		return clickTotalVaxPercent;
	}
	
	public boolean getClickFullyVaxed() {
		return clickFullyVaxed;
	}
	
	public boolean getClickFullyVaxedPercent() {
		return clickFullyVaxedPercent;
	}
	
	public String getVaxAvailable() {
		return vaxAvailable;
	}
	
	public String getVaxAvailableDisplay() {
		return vaxAvailableDisplay;
	}
	
	public String getVaxAvailableString() {
		return vaxAvailableString;
	}
	
	public String getVaxDist() {
		return vaxDist;
	}
	
	public String getVaxDistDisplay() {
		return vaxDistDisplay;
	}
	
	public String getVaxDistString() {
		return vaxDistString;
	}
	
	public String getDistPercent() {
		return distPercent;
	}
	
	public String getDistPercentDisplay() {
		return distPercentDisplay;
	}
	
	public String getDistPercentString() {
		return distPercentString;
	}
	
	public String getPeopleVaxed() {
		return peopleVaxed;
	}
	
	public String getPeopleVaxedDisplay() {
		return peopleVaxedDisplay;
	}
	
	public String getPeopleVaxedString() {
		return peopleVaxedString;
	}
	
	public String getVaxedPercent() {
		return vaxedPercent;
	}
	
	public String getVaxedPercentDisplay() {
		return vaxedPercentDisplay;
	}
	
	public String getVaxedPercentString() {
		return vaxedPercentString;
	}
	
	public String getPeopleFullyVaxed() {
		return peopleFullyVaxed;
	}
	
	public String getPeopleFullyVaxedDisplay() {
		return peopleFullyVaxedDisplay;
	}
	
	public String getPeopleFullyVaxedString() {
		return peopleFullyVaxedString;
	}
	
	public String getFullyVaxedPercent() {
		return fullyVaxedPercent;
	}
	
	public String getFullyVaxedPercentDisplay() {
		return fullyVaxedPercentDisplay;
	}
	
	public String getFullyVaxedPercentString() {
		return fullyVaxedPercentString;
	}
	
	//set methods
	
	public void setButtonInfo() {
		
	}
	
	public void setButtonX(int num) {
		buttonX = num;
	}
	
	public void setButtonY(int num) {
		buttonY = num;
	}
	
	public void setButtonWidth(int num) {
		buttonWidth = num;
	}
	
	public void setButtonHeight(int num) {
		buttonHeight = num;
	}
	
	public void setButtonDist(float num) {
		buttonDistance = num;
	}
	
	public void setClickVaxAvailable(boolean state) {
		clickVaxAvailable = (state);
	}
	
	public void setClickVaxDistributed(boolean state) {
		clickVaxDistributed = (state);
	}
	
	public void setClickDistPercent(boolean state) {
		clickDistPercent = (state);
	}
	
	public void setClickPeopleVaxed(boolean state) {
		clickPeopleVaxed = (state);
	}
	
	public void setClickTotalVaxPercent(boolean state) {
		clickTotalVaxPercent = (state);
	}
	
	public void setClickFullyVaxed(boolean state) {
		clickFullyVaxed = (state);
	}
	
	public void setClickFullyVaxedPercent(boolean state) {
		clickFullyVaxedPercent = (state);
	}
	
	public void setVaxAvailableDisplay(String txt) {
		vaxAvailableDisplay = txt;
	}
	
	public void setVaxDistDisplay(String txt) {
		vaxDistDisplay = txt;
	}
	
	public void setDistPercentDisplay(String txt) {
		distPercent = txt;
	}
	
	public void setPeopleVaxedDisplay(String txt) {
		peopleVaxedDisplay = txt;
	}
	
	public void setVaxedPercentDisplay(String txt) {
		vaxedPercentDisplay = txt;
	}
	
	public void setPeopleFullyVaxedDisplay(String txt) {
		peopleFullyVaxed = txt;
	}
	
	public void setFullyVaxedPercentDisplay(String txt) {
		fullyVaxedPercentDisplay = txt;
	}
}
