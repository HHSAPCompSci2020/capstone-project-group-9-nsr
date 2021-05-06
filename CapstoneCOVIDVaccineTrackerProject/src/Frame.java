/**The frame of the classes with pictures, CountryMpa and State
* @author 
*/
import javax.swing.JButton;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * creates the frame
 * @author 
 *
 */
public class Frame {
	private PImage map;
	private JButton refresh;
	//Construcotr
	public Frame(PImage img) {
		map = img;
	}
	/**refreshes the page so that the information is up to date
	 * The information refreshes automatically, but this is just in case theres a bug and we need to refresh.
	 * 
	 */
	private void refresh() {
		
	}

}
