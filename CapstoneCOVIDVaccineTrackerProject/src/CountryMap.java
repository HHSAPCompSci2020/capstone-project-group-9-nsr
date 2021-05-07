/**This class sets the map of the US and the dropDown that allow the user to move to a state page. 
 *  @author Sophie
* date: 5/6/21
* @revision 1
*/
import java.util.ArrayList;

import javax.swing.JComboBox;

import processing.core.PApplet;
public class CountryMap extends Frame{
	ArrayList<State> states;
	JComboBox dropDown;
	/**constructor
	 * 
	 * @param fileName
	 */	
	public CountryMap(String fileName) {
		super(fileName);
		states = setAllStates();
	}
	/**set all the states to their name
	 * 
	 * @return ArrayList<State> arraylist of the states with all the information in the fields
	 */
	private ArrayList<State> setAllStates(){
		ArrayList<State> setState = new ArrayList<State>();
	    states.add(new State("alabama"));
	    states.add(new State("alaska"));
	    states.add(new State("arizona"));
	    states.add(new State("arkansas"));
	    states.add(new State("california"));
	    states.add(new State("colorado"));
	    states.add(new State("connecticut"));
	    states.add(new State("delaware"));
	    states.add(new State("florida"));
	    states.add(new State("georgia"));
	    states.add(new State("hawaii"));
	    states.add(new State("idaho"));
	    states.add(new State("illinois"));
	    states.add(new State("indiana"));
	    states.add(new State("iowa"));
	    states.add(new State("kansas"));
	    states.add(new State("kentucky"));
	    states.add(new State("louisiana"));
	    states.add(new State("maine"));
	    states.add(new State("maryland"));
	    states.add(new State("massachusetts"));
	    states.add(new State("michigan"));
	    states.add(new State("minnesota"));
	    states.add(new State("mississippi"));
	    states.add(new State("missouri"));
	    states.add(new State("montana"));
	    states.add(new State("nebraska"));
	    states.add(new State("nevada"));
	    states.add(new State("new hampshire"));
	    states.add(new State("new jersey"));
	    states.add(new State("new mexico"));
	    states.add(new State("new york"));
	    states.add(new State("north carolina"));
	    states.add(new State("north dakota"));
	    states.add(new State("ohio"));
	    states.add(new State("oklahoma"));
	    states.add(new State("oregon"));
	    states.add(new State("pennsylvania"));
	    states.add(new State("rhode Island"));
	    states.add(new State("south carolina"));
	    states.add(new State("south dakota"));
	    states.add(new State("tennessee"));
	    states.add(new State("texas"));
	    states.add(new State("utah"));
	    states.add(new State("vermont"));
	    states.add(new State("virginia"));
	    states.add(new State("washington"));
	    states.add(new State("west virginia"));
	    states.add(new State("wisconsin"));
	    states.add(new State("wyoming"));
		return setState;
	}
	/**find and returns the index of state that matches the name
	 * returns -1 if none are found
	 * 
	 * @param String name of state
	 * @return int index of state
	 */
	private int findIndexOfState(String name) {
		for(int i = 0; i < 50; i++) {
			if(states.get(i).graph.getName() == name) {
				return i;
			}
		}
		return -1;
	}
	/**opens the dropdown menu
	 * 
	 */
	private void openDropDown() {
		
	}
	/**opens the state page 
	 * @param state
	 */
	
	private void openStatePage(StatesGraphics state) {
		
	}
}
