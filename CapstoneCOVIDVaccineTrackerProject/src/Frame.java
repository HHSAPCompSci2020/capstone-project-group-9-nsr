/**The frame of the classes with pictures, CountryMap and State
* @author sophie
*/
import javax.swing.JButton;

import processing.core.PApplet;
import processing.core.PImage;

/**the frame of the state and the country page
 * creates the frame
 * @author Sophie
 *
 */
public class Frame extends PApplet{
	
	private String fileName;
	
	//Constructor
	public Frame(String name) {
		fileName = name;
	}
	/**
	 * draws the drop down button for each page in state or map
	 * @param surface PApplet
	 * @param screenWidth width of the window
	 */
	public void drawDropDownButton(PApplet surface, int screenWidth) {
		surface.strokeWeight(5);
		surface.line(screenWidth-40, 10, screenWidth-10, 10);
		surface.line(screenWidth-40, 20, screenWidth-10, 20);
		surface.line(screenWidth-40, 30, screenWidth-10, 30);
		surface.strokeWeight(1);
	}
	/**
	 * draws the back button for state and country
	 * @param surface PApplet 
	 * @param screenWidth  width of the window
	 * @param screenHeight height of the window
	 */
	public void drawBackButton(PApplet surface, int screenWidth, int screenHeight) {
		surface.rect(width-width/25-width/100, height-height/25-height/100, width/25, height/25);
	}
}
