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
 
	public CountryMap(String fileName) {
		super(fileName);
		states = setAllStates();
	}
	private ArrayList<State> setAllStates(){
		ArrayList<State> setState = new ArrayList<State>();
	//	setState.add(new State() )
		for(int i = 0; i < 50; i++) {
			
		}
		return setState;
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
