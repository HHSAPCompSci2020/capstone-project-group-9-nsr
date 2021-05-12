/**This class sets the map of the US and the dropDown that allow the user to move to a state page. 
 *  @author roopa
* date: 5/6/21
* @revision 1
*/
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

import javax.swing.JOptionPane;

import processing.core.PApplet;
import processing.core.PImage;

public class Country extends Frame{
	HashMap<String, State> allStates = new HashMap<String, State>();
	TreeMap states = new TreeMap();
	PImage map;
	private int mapWidth, mapHeight;
	private int screenHeight, screenWidth;
	private int heightDiff;
	boolean openDropDown;
	boolean statePageOpen;
	String stateInput;
	/**constructor
	 * 
	 */	
	public Country() {
		super("US_MAP.png");
		initializeStates();
		openDropDown = false;
		statePageOpen =false;
	}
	
	private void initializeStates() {
		String[] allStateNames = new String[] {"alabama", "alaska", "arizona", "arkansas", "california", "colorado", "connecticut", "delaware", "florida", "georgia",
				"hawaii", "idaho", "illinois", "indiana", "iowa", "kansas", "kentucky", "louisiana", "maine", "maryland", 
				"massachusetts", "michigan", "minnesota", "mississippi", "missouri", "montana", "nebraska", "nevada", "new hampshire", "new jersey",
				"new mexico", "new york", "north carolina", "north dakota", "ohio", "oklahoma", "oregon", "pennsylvania", "rhode island", "south carolina", 
				"south dakota", "tennessee", "texas", "utah", "vermont", "virginia", "washington", "west virginia", "wisconsin", "wyoming"};
		for (String a : allStateNames) {
			states.put(a, new State(a));
		}
	}
	
	/**find and returns the index of state that matches the name
	 * returns -1 if none are found
	 * 
	 * @param String name of state
	 * @return int index of state
	 */
	private int findIndexOfState(String name) {
//		for(int i = 0; i < 50; i++) {
//			if(states.get(i).graph.getName() == name) {
//				return i;
//			}
//		}
		return -1;
	}
	
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
	
	/**opens the state page 
	 * @param state
	 */
	
	private void goToStatePage(PApplet surface, String stateName) {
		State nextState = new State(stateName);
		surface.fill(255);
		surface.rect(0, 0, surface.width, surface.height);
		nextState.draw(surface);
	}	
	
	public void draw(PApplet surface) {
		map = surface.loadImage("maps/US_MAP.png");
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
		surface.image(map, 10, 10);
		//draw button for drop down
		drawDropDownButton(surface, screenWidth);
		if(openDropDown) {
			createDropDown(surface);
		}
		if(statePageOpen) {
			goToStatePage(surface, stateInput);
		}
		//add if you go back, make statePage = false
		
	}
	public int getScreenW() {
		return screenWidth;
	}
	
}
