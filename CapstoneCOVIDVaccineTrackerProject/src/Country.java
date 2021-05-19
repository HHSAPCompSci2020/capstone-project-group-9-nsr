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
	private TreeMap<String, State> states = new TreeMap<String, State>();
	private PImage map;
	private MoreInfo moreInfo;
	private int screenHeight, screenWidth;
	private boolean openDropDown, clickVaxName, clickAvailableVax, clickPeopleVaxed, clickFullyVaxed;
	private boolean statePageOpen;
	private String stateInput;
	private String[] allStateNames;
	private Stats stats = new Stats();
	private float buttonDistance;
	private int buttonWidth, buttonHeight;
	private int buttonX, buttonY;
	private ArrayList<String> list = stats.getCountryData();
	private String names = "";
	private String vaxNames, vaxAvailable, peopleVaxed, peopleFullyVaxed;
	
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
		vaxNames = "names of vaccines used today";
		vaxAvailable = "total vaccinations available";
		peopleVaxed = "people vaccinated";
		peopleFullyVaxed = "people fully vaccinated";
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
		if(!statePageOpen) {
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
		}
		if(openDropDown) {
			createDropDown(surface);
		}
		if(statePageOpen) {
			goToStatePage(surface, stateInput);
		}
		
		if (clickVaxName) {
			vaxNames = names;
		} else {
			vaxNames = "names of vaccines used today";
		}
		if (clickAvailableVax) {
			vaxAvailable = list.get(list.size()-3);
		} else {
			vaxAvailable = "total vaccinations available";
		}
		if (clickPeopleVaxed) {
			peopleVaxed = list.get(list.size()-2);
		} else {
			peopleVaxed = "people vaccinated";
		}
		if (clickFullyVaxed) {
			peopleFullyVaxed = list.get(list.size()-1);
		} else {
			peopleFullyVaxed = "people fully vaccinated";
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
		drawButton(p, (int)x, (int)(y+buttonDistance), buttonWidth, buttonHeight, vaxNames, 218);
		drawButton(p, (int)x, (int)(y+(2*buttonDistance)), buttonWidth, buttonHeight, vaxAvailable, 218);
		drawButton(p, (int)x, (int)(y+(3*buttonDistance)), buttonWidth, buttonHeight, peopleVaxed, 218);
		drawButton(p, (int)x, (int)(y+(4*buttonDistance)), buttonWidth, buttonHeight, peopleFullyVaxed, 218);

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
	
	
	/**
	 * @return width of the screen(drawing window)
	 */
	public int getScreenW() {
		return screenWidth;
	}
	//	boolean openDropDown, clickVaxName, clickAvailableVax, clickPeopleVaxed, clickFullyVaxed;

	public void setClickVaxName(boolean state) {
		clickVaxName = state;
	}
	
	public void setClickAvailableVax(boolean state) {
		clickAvailableVax = state;
	}
	
	public void setClickPeopleVaxed(boolean state) {
		clickPeopleVaxed = state;
	}
	
	public void setClickFullyVaxed(boolean state) {
		clickFullyVaxed = state;
	}
	
	public void setStatePageOpen(boolean state) {
		statePageOpen = state;
	}
	
	public void setOpenDropDown(boolean state) {
		openDropDown = state;
	}
	
	public boolean getStatePageOpen() {
		return statePageOpen;
	}
	
	public int getButtonX() {
		return buttonX;
	}
	
	public int getButtonY() {
		return buttonY;
	}
	
	public float getButtonDistance() {
		return buttonDistance;
	}
	
	public int getButtonWidth() {
		return buttonWidth;
	}
	
	public int getButtonHeight() {
		return buttonHeight;
	}
	
	public String getVaxNames() {
		return vaxNames;
	}
	
	public String getVaxAvailable() {
		return vaxAvailable;
	}
	
	public String getPeopleVaxed() {
		return peopleVaxed;
	}
	
	public String getPeopleFullyVaxed() {
		return peopleFullyVaxed;
	}
	
	public boolean getClickVaxName() {
		return clickVaxName;
	}
	
	public boolean getClickAvailableVax() {
		return clickAvailableVax;
	}
	
	public boolean getClickPeopleVaxed() {
		return clickPeopleVaxed;
	}
	
	public boolean getClickFullyVaxed() {
		return clickFullyVaxed;
	}
	
	public TreeMap<String, State> getStates(){
		return states;
	}
}
