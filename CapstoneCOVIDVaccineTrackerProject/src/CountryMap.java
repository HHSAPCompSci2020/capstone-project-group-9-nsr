/**This class sets the map of the US and the dropDown that allow the user to move to a state page. 
 *  @author Sophie
* date: 5/6/21
* @revision 1
*/
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import processing.awt.PSurfaceAWT;
import processing.core.PApplet;
import processing.core.PImage;
public class CountryMap extends Frame{
	ArrayList<State> states;
	ArrayList<String> stateNames = new ArrayList<String>();
	PImage map;
	
	/**constructor
	 * 
	 */	
	public CountryMap() {
		super("US_MAP.png");
		states = setAllStates();
		for (int i = 0; i < states.size(); i++) {
			String name = states.get(i).getName();
			stateNames.add(name);
		}
	}
	/**set all the states to their name
	 * 
	 * @return ArrayList<State> arraylist of the states with all the information in the fields
	 */
	private ArrayList<State> setAllStates(){
		ArrayList<State> setState = new ArrayList<State>();
	    setState.add(new State("alabama"));
	    setState.add(new State("alaska"));
	    setState.add(new State("arizona"));
	    setState.add(new State("arkansas"));
	    setState.add(new State("california"));
	    setState.add(new State("colorado"));
	    setState.add(new State("connecticut"));
	    setState.add(new State("delaware"));
	    setState.add(new State("florida"));
	    setState.add(new State("georgia"));
	    setState.add(new State("hawaii"));
	    setState.add(new State("idaho"));
	    setState.add(new State("illinois"));
	    setState.add(new State("indiana"));
	    setState.add(new State("iowa"));
	    setState.add(new State("kansas"));
	    setState.add(new State("kentucky"));
	    setState.add(new State("louisiana"));
	    setState.add(new State("maine"));
	    setState.add(new State("maryland"));
	    setState.add(new State("massachusetts"));
	    setState.add(new State("michigan"));
	    setState.add(new State("minnesota"));
	    setState.add(new State("mississippi"));
	    setState.add(new State("missouri"));
	    setState.add(new State("montana"));
	    setState.add(new State("nebraska"));
	    setState.add(new State("nevada"));
	    setState.add(new State("new hampshire"));
	    setState.add(new State("new jersey"));
	    setState.add(new State("new mexico"));
	    setState.add(new State("new york"));
	    setState.add(new State("north carolina"));
	    setState.add(new State("north dakota"));
	    setState.add(new State("ohio"));
	    setState.add(new State("oklahoma"));
	    setState.add(new State("oregon"));
	    setState.add(new State("pennsylvania"));
	    setState.add(new State("rhode Island"));
	    setState.add(new State("south carolina"));
	    setState.add(new State("south dakota"));
	    setState.add(new State("tennessee"));
	    setState.add(new State("texas"));
	    setState.add(new State("utah"));
	    setState.add(new State("vermont"));
	    setState.add(new State("virginia"));
	    setState.add(new State("washington"));
	    setState.add(new State("west virginia"));
	    setState.add(new State("wisconsin"));
	    setState.add(new State("wyoming"));
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
	/**opens the state page 
	 * @param state
	 */
	
	private void goToStatePage(PApplet surface) {
		State input = (State)JOptionPane.showInputDialog(null, "Which state?",
		        "Which state?", JOptionPane.QUESTION_MESSAGE, null,
		        stateNames.toArray(), // Array of choices
		        stateNames.toArray()[0]); // Initial choice
		    
		if (input == null)
			return;
		
		String stateName = input.getName(); 
		
		State nextState = new State(stateName);
		nextState.draw(surface);
	}	
	
	public void draw(PApplet surface) {
		map = surface.loadImage("maps/US_MAP.png");
		surface.image(map, 10, 10);
		goToStatePage(surface);
	}
}
