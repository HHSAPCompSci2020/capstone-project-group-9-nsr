import java.awt.Desktop;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import processing.core.PApplet;

/**
 * 
 * @author roopa
 *
 */
public class MoreInfo extends PApplet{
	
	private int x, y;
	private int buttonWidth, buttonHeight;
	private double pfizerX, pfizerY;
	private double johnsonX, johnsonY;
	private double modernaX, modernaY;
	private boolean pfizerButton, johnsonButton, modernaButton;
	private int screenHeight, screenWidth;
	
	/**
	 * constructor that initializes all fields:
	 * x, y
	 * buttonWidth, buttonHeight
	 * x and y coordinates of each of the vaccine company buttons
	 */
	public MoreInfo() {
		this.x = 100;
		this.y = 100;
		buttonWidth = width*2;
		buttonHeight = 20;
		pfizerX = this.x+10;
		pfizerY = this.y+10;
		johnsonX = pfizerX;
		johnsonY = pfizerY + buttonHeight + 10;
		modernaX = johnsonX;
		modernaY = johnsonY + buttonHeight + 10;
	}
	
	/**
	 * 
	 * @param surface papplet parameter
	 * @param x x coordinate of button
	 * @param y y coordinate of button
	 * @param w width of button
	 * @param h height of button
	 * @param text text to be displayed inside button
	 */
	public void drawButton(PApplet surface, int x, int y, int w, int h, String text) {
		surface.rect(x, y, w, h, 10);
    	surface.textAlign(surface.CENTER, surface.CENTER);
    	surface.fill(0);
    	surface.text(text, x + w/2, y + h/2);
    	surface.noFill();
	}
	
	/**
	 * draws all aspects of moreInfo class
	 * @param surface papplet parameter
	 */
	public void draw(PApplet surface) {
		screenHeight = surface.height;
		screenWidth = surface.width;
		johnsonY = this.y+screenHeight/3;
		modernaY = this.y+ 2*(screenHeight/3);
		buttonWidth = screenWidth/3;
		buttonHeight = screenHeight/7;
		drawVaccineButtons(surface);
		drawInfo(surface);
		if (pfizerButton) {
			URL url;
			try {
				url = new URL("https://www.cdc.gov/coronavirus/2019-ncov/vaccines/different-vaccines/Pfizer-BioNTech.html");
				openWebpage(url);
				pfizerButton = false;
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}	
		} if (johnsonButton) {
			URL url;
			try {
				url = new URL("https://www.cdc.gov/coronavirus/2019-ncov/vaccines/different-vaccines/janssen.html");
				openWebpage(url);
				johnsonButton = false;
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}	
		} if (modernaButton) {
			URL url;
			try {
				url = new URL("https://www.cdc.gov/coronavirus/2019-ncov/vaccines/different-vaccines/Moderna.html");
				openWebpage(url);
				modernaButton = false;
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}	
		}
	}
	
	/**
	 * draws the vaccine buttons
	 * @param surface papplet parameter
	 * @post textSize screen height/50
	 */
	private void drawVaccineButtons(PApplet surface) {
		surface.textSize(screenHeight/50);
		drawButton(surface, (int)x, (int)y, (int)buttonWidth, (int)buttonHeight, "Pfizer-BioNTech");
		drawButton(surface, (int)x, (int)y+surface.height/3, (int)buttonWidth, (int)buttonHeight, "Johnson & Johnson");
		drawButton(surface, (int)x, (int)y+2*surface.height/3, (int)buttonWidth, (int)buttonHeight, "Moderna");
	}
	
	/**
	 * draws the actual info
	 * @param surface papplet parameter
	 * @post textSize screenHeight/50
	 * @post textAlign left
	 */
	private void drawInfo(PApplet surface) {
		int startY = screenHeight/20 + 60;
		int yDifference = screenHeight/40;
		float textSize1 = screenHeight/25;
		float textSize2 = screenHeight/35;
		float textSize3 = screenHeight/50;
		float textSize4 = screenHeight/75;
		surface.textSize(textSize1);
		surface.text("ALL INFO GATHERED DIRECTLY FROM CDC WEBSITE (as of 05/13/2021)", screenWidth/2, screenHeight/20);
		surface.textAlign(LEFT);
		surface.textSize(textSize3);
		surface.text("eligibility: ", 6*screenWidth/11, startY+(yDifference));
		surface.textSize(textSize4);
		surface.text("     - 12 and older (in all vaccination sites) (only for Pfizer-BioNTech) ", 6*screenWidth/11, startY+(yDifference*2));
		surface.textSize(textSize3);
		surface.text("types: ", 6*screenWidth/11, startY+(yDifference*3));
		surface.textSize(textSize4);
		surface.text("     - Pfizer-BioNTech (must take 2 shots for full effectiveness) ", 6*screenWidth/11, startY+(yDifference*4));
		surface.text("     - Johnson & Johnson (must take 1 shot for full effectiveness)", 6*screenWidth/11, startY+(yDifference*5));
		surface.text("     - Moderna (must take 2 shots for full effectiveness)", 6*screenWidth/11, startY+(yDifference*6));
		surface.text("     *use buttons on left for more information*", 6*screenWidth/11, startY+(yDifference*7));
		surface.textSize(textSize3);
		surface.text("potential side effects: ", 6*screenWidth/11, startY+(yDifference*8));
		surface.textSize(textSize4);
		surface.text("     - on arm where you got the shot: pain, redness, swelling", 6*screenWidth/11, startY+(yDifference*9));
		surface.text("     - throughout body: tiredness, headache, muscle pain, chils, fever, nausea", 6*screenWidth/11, startY+(yDifference*10));
		surface.text("     - to reduce discomfort where you got the shot: apply clean, cool, wet washcloth over the area, use or exercise your arm ", 6*screenWidth/11, startY+(yDifference*11));
		surface.text("     - to reduce discomfort throughout your whole body: drink lots of fluids, dress lightly ", 6*screenWidth/11, startY+(yDifference*12));
		surface.text("     - side effects of second shot is typically more intense", 6*screenWidth/11, startY+(yDifference*13));
		surface.textSize(textSize3);
		surface.text("after vaccination: ", 6*screenWidth/11, startY+(yDifference*14));
		surface.textSize(textSize4);
		surface.text("     - should continue to wear your mask and follow social distancing guidelines \n"
				   + "       (but not required if others around you are fully vaccinated) as best you can to help protect others who have \n"
				   + "       not yet recieved the vaccine, as you could be a carrier", 6*screenWidth/11, startY+(yDifference*15));
		surface.text("     - outdoor activities are safer than indoor activities (but indoor activities are still allowed)", 6*screenWidth/11, startY+(yDifference*18));
		surface.text("     - if in US, don't need to get tested before or after travel or even self-quarantine\n"
				   + "       (if traveling outside US, make sure to check the situation of your destination)", 6*screenWidth/11, startY+(yDifference*19));
		surface.text("     - should still watch out for covid symptoms if you've been around someone who was/is sick", 6*screenWidth/11, startY+(yDifference*21));
		surface.textSize(textSize3);
		surface.text("effectiveness: ", 6*screenWidth/11, startY+(yDifference*22));
		surface.textSize(textSize4);
		surface.text("     - to recieve most protection, you should recieve all recommended doses of vaccination", 6*screenWidth/11, startY+(yDifference*23));
		surface.text("     - no vaccine is 100% effective, so some people can still get sick", 6*screenWidth/11, startY+(yDifference*24));
		surface.text("     - CDC recommends to get vaccine as soon as one is available", 6*screenWidth/11, startY+(yDifference*25));
		surface.text("     - you will be fully protected 2 weeks after your second shot", 6*screenWidth/11, startY+(yDifference*26));
		surface.textSize(textSize3);
		surface.text("things we know about the vaccine: ", 6*screenWidth/11, startY+(yDifference*27));
		surface.textSize(textSize4);
		surface.text("     - effective in preventing death and severe illness", 6*screenWidth/11, startY+(yDifference*28));
		surface.text("     - regular prevention methods(masks and social distancing) are still vital to protect others", 6*screenWidth/11, startY+(yDifference*29));
		surface.textSize(textSize3);
		surface.text("things we don't know yet about the vaccine: ", 6*screenWidth/11, startY+(yDifference*30));
		surface.textSize(textSize4);
		surface.text("     - how effective it is against variants", 6*screenWidth/11, startY+(yDifference*31));
		surface.text("     - how effective it is in people with weakened immune systems (includes those who take immunosuppressive medications)", 6*screenWidth/11, startY+(yDifference*32));
		surface.text("     - how long the vaccination can protect people", 6*screenWidth/11, startY+(yDifference*33));
	}
	

	/**
	 * opens the web page in a browser
	 * @param uri URI type parameter-one that gets opened in webpage
	 * @return true or false(if opened webpage successfully or not)
	 */

	public static boolean openWebpage(URI uri) {
	    Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
	    if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
	        try {
	            desktop.browse(uri);
	            return true;
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    return false;
	}
	
	/**
	 * calls URI version of openWebPage method
	 * @param url URL type parameter-the actual url from the web browser
	 * @return true or false(if called URL version of method successfully or not)
	 */
	public static boolean openWebpage(URL url) {
	    try {
	        return openWebpage(url.toURI());
	    } catch (URISyntaxException e) {
	        e.printStackTrace();
	    }
	    return false;
	}
	
	/**
	 * @return x coordinate of this class
	 */
	public int getX() {
		return this.x;
	}
	
	/**
	 * @return y coordinate of this class
	 */
	public int getY() {
		return this.y;
	}
	
	/**
	 * @return width of buttons on page
	 */
	public int getButtonWidth() {
		return this.buttonWidth;
	}
	
	/**
	 * @return height of buttons on page
	 */
	public int getButtonHeight() {
		return this.buttonHeight;
	}
	
	/**
	 * sets the state of pfizer button to true or false
	 * @param state new state of button
	 */
	public void setPfizer(boolean state) {
		pfizerButton = state;
	}
	
	/**
	 * sets the state of johnson button to true or false
	 * @param state new state of button
	 */
	public void setJohnson(boolean state) {
		johnsonButton = state;
	}
	
	/**
	 * sets the state of moderna button to true or false
	 * @param state new state of button
	 */
	public void setModerna(boolean state) {
		modernaButton = state;
	}

}
