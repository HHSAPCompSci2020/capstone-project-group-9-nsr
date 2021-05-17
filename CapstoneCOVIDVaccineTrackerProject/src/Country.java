/**This class sets the map of the US and the dropDown that allow the user to move to a state page. 
 *  @author roopa
* date: 5/6/21
* @revision 1
*/
import java.util.ArrayList;
import java.util.TreeMap;

import javax.swing.JOptionPane;

import processing.core.PApplet;
import processing.core.PImage;

public class Country extends Frame{
	TreeMap<String, State> states = new TreeMap<String, State>();
	PImage map;
	MoreInfo moreInfo;
	private int screenHeight, screenWidth;
	boolean openDropDown, clickVaxName, clickAvailableVax, clickPeopleVaxed, clickFullyVaxed;
	boolean statePageOpen;
	String stateInput;
	String[] allStateNames;
	Stats stats = new Stats();
	float buttonDistance;
	int buttonWidth, buttonHeight;
	int buttonX, buttonY;
	ArrayList<String> list = stats.getCountryData();
	String names = "";
	
	/**
	 * constructor that initializes fields:
	 * openDropDown, statePageOpen
	 */
	public Country() {
		super("US_MAP.png");
		initializeStates();
		openDropDown = false;
		statePageOpen = false;
		clickVaxName = false;
		clickAvailableVax = false;
		clickPeopleVaxed = false;
		clickFullyVaxed = false;
		for(int i = 0; i < list.size() - 6; i++) {
			names += list.get(2 + i) + " ";
		}
	}
	
	/**
	 * initializes the TreeMap allStates to contain all the states and their statistical info
	 */
	private void initializeStates() {
		allStateNames = new String[] {"alabama", "alaska", "arizona", "arkansas", "california", "colorado", "connecticut", "delaware", "florida", "georgia",
				"hawaii", "idaho", "illinois", "indiana", "iowa", "kansas", "kentucky", "louisiana", "maine", "maryland", 
				"massachusetts", "michigan", "minnesota", "mississippi", "missouri", "montana", "nebraska", "nevada", "new hampshire", "new jersey",
				"new mexico", "new york", "north carolina", "north dakota", "ohio", "oklahoma", "oregon", "pennsylvania", "rhode island", "south carolina", 
				"south dakota", "tennessee", "texas", "utah", "vermont", "virginia", "washington", "west virginia", "wisconsin", "wyoming"};
		for (String a : allStateNames) {
			states.put(a, new State(a));
		}
	}
	
	/**
	 * creates the drop down menu for user to choose any state
	 * @param surface papplet parameter
	 */
	private void createDropDown(PApplet surface) {
		openDropDown = false;
		String input = (String)JOptionPane.showInputDialog(null, "Which state?",
		        "Which state?", JOptionPane.QUESTION_MESSAGE, null,
		       states.keySet().toArray(), // Array of choices
		        states.keySet().toArray()[0]); // Initial choice
		    
		if (input == null)
			return;
		stateInput = input;
		statePageOpen = true;
	}
	
	/**
	 * opens the state page that the user chooses
	 * @param surface papplet parameter
	 * @param stateName name of state chosen by user
	 */
	private void goToStatePage(PApplet surface, String stateName) {
		State nextState = new State(stateName);
		surface.fill(255);
		surface.rect(0, 0, surface.width, surface.height);
		nextState.draw(surface);
	}
	
	/**
	 * takes you back to the home page of program
	 * @param surface papplet parameter
	 */
	public void goToHome(PApplet surface) {
		surface.fill(255);
		surface.rect(0, 0, surface.width, surface.height);
		moreInfo.draw(surface);
	}
	
	/**
	 * draws all aspects of country class
	 * @param surface papplet parameter
	 */
	public void draw(PApplet surface) {
		map = surface.loadImage("maps/US_MAP.png");
		screenHeight = surface.height;
		screenWidth = surface.width;
		buttonDistance = screenHeight/30;
		buttonWidth = screenWidth/5;
		buttonHeight = screenHeight/35;
		if (screenHeight < screenWidth) {
			map.resize(0, (screenHeight/3)*2);
		} else {
			map.resize(screenWidth/2, 0);
		}
		surface.image(map, screenWidth/100, screenHeight/100);
		//draw button for drop down
		drawDropDownButton(surface, screenWidth);
		writeInfo(surface, (6*screenWidth/9), screenHeight* 5 /20, screenHeight/45, screenHeight/60, screenHeight/50);
		drawBackButton(surface, screenWidth, screenHeight);
		if(openDropDown) {
			createDropDown(surface);
		}
		if(statePageOpen) {
			goToStatePage(surface, stateInput);
		}
		
		if (clickVaxName) {
			drawButton(surface, (int)buttonX, (int)(buttonY+buttonDistance), buttonWidth, buttonHeight, names, 218);
		}
		if (clickAvailableVax) {
			drawButton(surface, (int)buttonX, (int)(buttonY+(2*buttonDistance)), buttonWidth, buttonHeight, list.get(list.size()-3), 218);
		}
		if (clickPeopleVaxed) {
			drawButton(surface, (int)buttonX, (int)(buttonY+(3*buttonDistance)), buttonWidth, buttonHeight, list.get(list.size()-2), 218);
		}
		if (clickFullyVaxed) {
			drawButton(surface, (int)buttonX, (int)(buttonY+(4*buttonDistance)), buttonWidth, buttonHeight, list.get(list.size()-1), 218);
		}
		
		//add if you go back, make statePage = false
		
	}
	
	/**
	 * writes recent information of the country vaccination situation including
	 * the name of the vaccines, number of doses availbale, population vaccinated and
	 * people fully vaccinated.
	 * 
	 * @param p PApplet to draw on
	 * @param x x coordinates of center of all texts
	 * @param y y coordinates of the top of where the text starts
	 */
	public void writeInfo(PApplet p, double x, double y, float titleSize, float writingSize, float leading) {
		buttonX = (int)x;
		buttonY = (int)y;
		p.textAlign(LEFT);
		p.fill(0);
		p.textSize(titleSize);
		
		p.text("updated as of " + list.get(1), (float)x, (float)(y));
		
		p.textSize(writingSize);
		
		
		p.textLeading(leading);
		drawButton(p, (int)x, (int)(y+buttonDistance), buttonWidth, buttonHeight, "names of vaccines used today", 218);
		drawButton(p, (int)x, (int)(y+(2*buttonDistance)), buttonWidth, buttonHeight, "total vaccinations available", 218);
		drawButton(p, (int)x, (int)(y+(3*buttonDistance)), buttonWidth, buttonHeight, "people vaccinated", 218);
		drawButton(p, (int)x, (int)(y+(4*buttonDistance)), buttonWidth, buttonHeight, "people fully vaccinated", 218);
		
		
//		p.text("names of vaccine used today : " + names + "\n" +
//		       "total vaccinations available : " + list.get(list.size()-3) + "\n" +
//		       "people vaccinated : " + list.get(list.size()-2) + "\n" +
//		       "people fully vaccinated : " + list.get(list.size()-1), (float)x, (float)(y + 60));

	}
	
	public void drawButton(PApplet surface, int x, int y, int w, int h, String text, int fillColor) {
		surface.fill(fillColor);
		surface.rect(x, y, w, h, 10);
    	surface.textAlign(surface.CENTER, surface.CENTER);
    	surface.fill(0);
    	surface.text(text, x + w/2, y + h/2);
    	surface.noFill();
	}
	
	public boolean overButton(int x, int y, int w, int h) {
	  if (mouseX > x && mouseX < (x + w) && mouseY > y && mouseY < (y + h)) {
	    return true;
	  }
	  return false;
	}
	
	public void mouseClicked() {
		if (overButton((int)buttonX, (int)(buttonY+buttonDistance), buttonWidth, buttonHeight)) {
			clickVaxName = true;
		}
		if (overButton((int)buttonX, (int)(buttonY+(2*buttonDistance)), buttonWidth, buttonHeight)) {
			clickAvailableVax = true;
		}
		if (overButton((int)buttonX, (int)(buttonY+(3*buttonDistance)), buttonWidth, buttonHeight)) {
			clickPeopleVaxed = true;
		}
		if (overButton((int)buttonX, (int)(buttonY+(4*buttonDistance)), buttonWidth, buttonHeight)) {
			clickFullyVaxed = true;
		}
	}
	
	/**
	 * @return width of the screen(drawing window)
	 */
	public int getScreenW() {
		return screenWidth;
	}
	
}
