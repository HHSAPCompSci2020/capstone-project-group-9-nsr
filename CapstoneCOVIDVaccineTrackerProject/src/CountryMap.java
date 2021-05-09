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
public class CountryMap extends Frame{
	HashMap<String, State> allStates = new HashMap<String, State>();
	TreeMap states = new TreeMap();
	PImage map;
	int mapWidth, mapHeight;
	
	/**constructor
	 * 
	 */	
	public CountryMap() {
		super("US_MAP.png");
		initializeStates();
		
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
		String input = (String)JOptionPane.showInputDialog(null, "Which state?",
		        "Which state?", JOptionPane.QUESTION_MESSAGE, null,
		       states.keySet().toArray(), // Array of choices
		        states.keySet().toArray()[0]); // Initial choice
		    
		if (input == null)
			return;
		
		goToStatePage(surface, input);
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
		map.resize((surface.width/500)*mapWidth, (surface.height/500)*mapHeight);
		surface.image(map, 10, 10);
		createDropDown(surface);
	}
}
