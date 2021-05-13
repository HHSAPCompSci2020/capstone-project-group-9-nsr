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
	public void drawDropDownButton(PApplet surface, int screenWidth) {
		surface.strokeWeight(5);
		surface.line(screenWidth-40, 10, screenWidth-10, 10);
		surface.line(screenWidth-40, 20, screenWidth-10, 20);
		surface.line(screenWidth-40, 30, screenWidth-10, 30);
		surface.strokeWeight(1);
	}
	public void drawBackButton(PApplet surface, int screenWidth, int screenHeight) {
		surface.rect(height, height, height, width);
	}
}
