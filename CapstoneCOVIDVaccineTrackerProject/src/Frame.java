/**The frame of the classes with pictures, CountryMap and State
* @author sophie
*/
import javax.swing.JButton;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * creates the frame
 * @author 
 *
 */
public class Frame extends PApplet{
	
	private String fileName;
	
	//Construcotr
	public Frame(String name) {
		fileName = name;
	}
	/**refreshes the page so that the information is up to date
	 * The information refreshes automatically, but this is just in case theres a bug and we need to refresh.
	 * 
	 */
	private void refresh() {
		
	}

}
