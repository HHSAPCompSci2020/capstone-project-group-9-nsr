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
	private TreeMap<String, State> states;
	private PImage map;
	private int screenHeight, screenWidth;
	private boolean openDropDown, clickVaxName, clickAvailableVax, clickPeopleVaxed, clickFullyVaxed;
	private boolean statePageOpen;
	private String stateInput;
	private String[] allStateNames;
	private Stats stats;
	private float buttonDistance;
	private int buttonWidth, buttonHeight;
	private int buttonX, buttonY;
	private ArrayList<String> list;
	private String vaxNames, vaxAvailable, peopleVaxed, peopleFullyVaxed;
	private String vaxNamesString, vaxAvailableString, peopleVaxedString, peopleFullyVaxedString;
	private String vaxNamesDisplay, vaxAvailableDisplay, peopleVaxedDisplay, peopleFullyVaxedDisplay;
	
	/**
	 * constructor that initializes fields:
	 * openDropDown, statePageOpen
	 */
	public Country() {
		super("US_MAP.png");
		states = new TreeMap<String, State>();
		initializeStates();
		stats = new Stats();
		list = stats.getCountryData();
		stateInput = "";
		openDropDown = false;
		statePageOpen = false;
		clickVaxName = false;
		clickAvailableVax = false;
		clickPeopleVaxed = false;
		clickFullyVaxed = false;
		vaxNamesDisplay = " ";
		vaxAvailableDisplay = " ";
		peopleVaxedDisplay = " ";
		peopleFullyVaxedDisplay = " ";
		vaxNamesString = "names of vaccines used today";
		vaxAvailableString = "total vaccinations available";
		peopleVaxedString = "people vaccinated";
		peopleFullyVaxedString = "people fully vaccinated";
		vaxNames = "";
		vaxAvailable = "";
		peopleVaxed = ""; 
		peopleFullyVaxed = "";
		for(int i = 0; i < list.size() - 6; i++) {
			vaxNames += list.get(2 + i) + " ";
		}
		setDisplayInfo();
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
		surface.fill(255);
		surface.rect(0, 0, surface.width, surface.height);
		State nextState = states.get(stateName);
		
		
		nextState.draw(surface);
	}
	
	
	
	/**
	 * draws all aspects of country class
	 * @param surface papplet parameter
	 */
	public void draw(PApplet surface) {
		map = surface.loadImage("maps/US_MAP.png");
		screenHeight = surface.height;
		screenWidth = surface.width;
		buttonDistance = screenWidth/4;
		buttonWidth = (int) (screenWidth/4.5);
		buttonHeight = screenHeight/15;
		if(!statePageOpen) {
			if (screenHeight < screenWidth) {
				map.resize(0, (screenHeight/3)*2);
			} else {
				map.resize(screenWidth/2, 0);
			}
			surface.image(map, screenWidth/100, screenHeight/100);
			//draw button for drop down
			drawDropDownButton(surface, screenWidth);
			writeInfo(surface, (screenWidth/75), screenHeight* 17 /20, screenHeight/35, screenHeight/70);
		}
		if(openDropDown) {
			createDropDown(surface);
			State nextState = states.get(stateInput);
			nextState.reset();
		}
		if(statePageOpen) {
			goToStatePage(surface, stateInput);
		}
		if (clickVaxName) {
			vaxNamesDisplay = vaxNames;
		} else {
			vaxNamesDisplay = vaxNamesString;
		}
		if (clickAvailableVax) {
			vaxAvailableDisplay = vaxAvailable;
		} else {
			vaxAvailableDisplay = vaxAvailableString;
		}
		if (clickPeopleVaxed) {
			peopleVaxedDisplay = peopleVaxed;
		} else {
			peopleVaxedDisplay = peopleVaxedString;
		}
		if (clickFullyVaxed) {
			peopleFullyVaxedDisplay = peopleFullyVaxed;
		} else {
			peopleFullyVaxedDisplay = peopleFullyVaxedString;
		}
		
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
	public void writeInfo(PApplet p, double x, double y, float titleSize, float writingSize) {
		buttonX = (int)x;
		buttonY = (int)(y-buttonDistance/2);
		p.textAlign(p.LEFT);
		p.fill(0);
		p.textSize(titleSize);
		
		p.text("updated as of " + list.get(1), (float)buttonX, (float)(buttonY+buttonDistance/4));
		
		p.textSize(writingSize);
		drawButton(p, (int)buttonX, (int)(buttonY+buttonDistance/3), buttonWidth, buttonHeight, vaxNamesDisplay, 218);
		drawButton(p, (int)(buttonX+buttonDistance), (int)(buttonY+buttonDistance/3), buttonWidth, buttonHeight, vaxAvailableDisplay, 218);
		drawButton(p, (int)(buttonX+(2*buttonDistance)), (int)(buttonY+buttonDistance/3), buttonWidth, buttonHeight, peopleVaxedDisplay, 218);
		drawButton(p, (int)(buttonX+(3*buttonDistance)), (int)(buttonY+buttonDistance/3), buttonWidth, buttonHeight, peopleFullyVaxedDisplay, 218);

	}
	
	public void drawButton(PApplet surface, int x, int y, int w, int h, String text, int fillColor) {
		surface.fill(fillColor);
		surface.rect(x, y, w, h, 10);
    	surface.textAlign(surface.CENTER, surface.CENTER);
    	surface.fill(0);
    	surface.text(text, x + w/2, y + h/2);
    	surface.noFill();
	}
	
	public boolean overButton(PApplet p, int x, int y, int w, int h) {
	  if (p.mouseX > x && p.mouseX < (x + w) && p.mouseY > y && p.mouseY < (y + h)) {
	    return true;
	  }
	  return false;
	}
	
	private void setDisplayInfo() {
		
		String[] vaxNamesTemp = vaxNames.split("\\s+");
		vaxNames = " ";
		for (int i = 0; i < vaxNamesTemp.length; i++) {
			vaxNames += vaxNamesTemp[i];
			if (i < vaxNamesTemp.length-1) {
				vaxNames += ", ";
			}
		}
		vaxNames = vaxNames.replace("\"", "");
		
		
		String vaxAvailableTemp = list.get(list.size()-3);
		for (int i = vaxAvailableTemp.length()-1; i >= 0; i--) {
			vaxAvailable = vaxAvailableTemp.substring(i, i+1) + vaxAvailable;
			if (i % 3 == 0 && i!=0) {
				vaxAvailable = "," + vaxAvailable;
			} 
		}
		
		String peopleVaxedTemp = list.get(list.size()-2);
		for (int i = peopleVaxedTemp.length()-1; i >= 0; i--) {
			peopleVaxed = peopleVaxedTemp.substring(i, i+1) + peopleVaxed;
			if (i % 3 == 0 && i!=0) {
				peopleVaxed = "," + peopleVaxed;
			} 
		}
		
		String peopleFullyVaxedTemp = list.get(list.size()-1);
		for (int i = peopleFullyVaxedTemp.length()-1; i >= 0; i--) {
			peopleFullyVaxed = peopleFullyVaxedTemp.substring(i, i+1) + peopleFullyVaxed;
			if (i % 3 == 0 && i!=0) {
				peopleFullyVaxed = "," + peopleFullyVaxed;
			} 
		}
	}
	
	//get methods
	/**
	 * @return width of the screen(drawing window)
	 */
	public int getScreenW() {
		return screenWidth;
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
		return vaxNamesDisplay;
	}
	
	public String getVaxAvailable() {
		return vaxAvailableDisplay;
	}
	
	public String getPeopleVaxed() {
		return peopleVaxedDisplay;
	}
	
	public String getPeopleFullyVaxed() {
		return peopleFullyVaxedDisplay;
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
	
	public String getStateInput() {
		return stateInput;
	}
	
	//set methods
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

}
