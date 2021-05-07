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
	    states.add(new State("Alaska"));
	    states.add(new State("Arizona"));
	    states.add(new State("Arkansas"));
	    states.add(new State("California"));
	    states.add(new State("Colorado"));
	    states.add(new State("Connecticut"));
	    states.add(new State("Delaware"));
	    states.add(new State("Florida"));
	    states.add(new State("Georgia"));
	    states.add(new State("Hawaii"));
	    states.add(new State("Idaho"));
	    states.add(new State("Illinois"));
	    states.add(new State("Indiana"));
	    states.add(new State("Iowa"));
	    states.add(new State("Kansas"));
	    states.add(new State("Kentucky"));
	    states.add(new State("Louisiana"));
	    states.add(new State("Maine"));
	    states.add(new State("Maryland"));
	    states.add(new State("Massachusetts"));
	    states.add(new State("Michigan"));
	    states.add(new State("Minnesota"));
	    states.add(new State("Mississippi"));
	    states.add(new State("Missouri"));
	    states.add(new State("Montana"));
	    states.add(new State("Nebraska"));
	    states.add(new State("Nevada"));
	    states.add(new State("New Hampshire"));
	    states.add(new State("New Jersey"));
	    states.add(new State("New Mexico"));
	    states.add(new State("New York"));
	    states.add(new State("North Carolina"));
	    states.add(new State("North Dakota"));
	    states.add(new State("Ohio"));
	    states.add(new State("Oklahoma"));
	    states.add(new State("Oregon"));
	    states.add(new State("Pennsylvania"));
	    states.add(new State("Rhode Island"));
	    states.add(new State("South Carolina"));
	    states.add(new State("South Dakota"));
	    states.add(new State("Tennessee"));
	    states.add(new State("Texas"));
	    states.add(new State("Utah"));
	    states.add(new State("Vermont"));
	    states.add(new State("Virginia"));
	    states.add(new State("Washington"));
	    states.add(new State("West Virginia"));
	    states.add(new State("Wisconsin"));
	    states.add(new State("Wyoming"));

		for(int i = 0; i < 50; i++) {
			
		}
		return setState;
	}
	/**find and returns the index of state that matches the name
	 * returns -1 if none are found
	 * 
	 * @param name
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
