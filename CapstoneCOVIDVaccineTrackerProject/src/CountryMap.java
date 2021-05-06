/** @author Sophie
* date: 5/6/21
* @revision 1
*/
import java.util.ArrayList;

import javax.swing.JComboBox;

import processing.core.PApplet;
public class CountryMap extends Frame{
	ArrayList<StatesGraphics> states;
	JComboBox dropDown;
 
	public CountryMap() {
		states = setAllStates();
	}
	private ArrayList<StatesGraphics> setAllStates(){
		ArrayList<StatesGraphics> setState = new ArrayList<StatesGraphics>();
	//	setState.add(new State() )
		for(int i = 0; i < 50; i++) {
			
		}
		return setState;
	}
	private void openDropDown() {
		
	}
	private void openStatePage(StatesGraphics state) {
		
	}
}
