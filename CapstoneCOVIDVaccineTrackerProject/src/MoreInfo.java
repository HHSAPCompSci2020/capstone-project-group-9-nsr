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
	
	public void drawButton(PApplet surface, int x, int y, int w, int h, String text) {
		surface.rect(x, y, w, h, 10);
    	surface.textAlign(surface.CENTER, surface.CENTER);
    	surface.fill(0);
    	surface.text(text, x + w/2, y + h/2);
    	surface.noFill();
	}
	
	/*
	 * ALL INFO GATHERED DIRECTLY FROM CDC WEBSITE
	 * general info about COVID and vaccines: 
	 * vaccines:
	 * 
		 * eligibility and types:
		 * - 16 and older are eligible (in all vaccination sites)
		 * - 12-15 eligible (in some vaccination sites)
		 * - types available: Pfizer-BioNTech(2 shots), Moderna(2 shots), Johnson & Johnson/Janssen (1 shot)
		 * - Pfizer-BioNTech info: https://www.cdc.gov/coronavirus/2019-ncov/vaccines/different-vaccines/Pfizer-BioNTech.html
		 * - Moderna info: https://www.cdc.gov/coronavirus/2019-ncov/vaccines/different-vaccines/Moderna.html
		 * - Johnson & Johnson info: https://www.cdc.gov/coronavirus/2019-ncov/vaccines/different-vaccines/janssen.html
		 * - as of feb 27, 2021, astra zeneca and novavax are in phase 3 clinical trials
		 * - you will be fully protected 2 weeks after your second shot
		 * 
		 * potential side effects
		 * - on arm where you got the shot: pain, redness, swelling
		 * - throughout body: tiredness, headache, muscle pain, chils, fever, nausea
		 * - to reduce discomfort where you got the shot: apply clean, cool, wet washcloth over the area, use or exercise your arm
		 * - to reduce discomfort throughout your whole body: drink lots of fluids, dress lightly
		 * - side effects of second shot is typically more intense
		 * 
		 * after vaccination:
		 * - should continue to wear your mask and follow social distancing guidelines (but not required if others around you are fully vaccinated) as best you can to help protect others who have not yer recieved the vaccine, as you could be a carrier
		 * - outdoor activities are safer than indoor activities (but indoor activities are still allowed)
		 * - if in US, don't need to get tested before or after travel or even self-quarantine (if traveling outside US, make sure to check the situation of your destination)
		 * - should still watch out for covid symptoms if you've been around someone who was/is sick
		 * 
		 * effectiveness: 
		 * - to recieve most protection, you should recieve all recommended doses of vaccination
		 * - no vaccine is 100% effective, so some people can still get sick
		 * - CDC recommends to get vaccine as soon as one is available
		 * 
		 * known:
		 * - effective in preventing death and severe illness
		 * - regular prevention methods(masks and social distancing) are still vital to protect others
		 * 
		 * unkown:
		 * - how effective it is against variants
		 * - how effective it is in people with weakened immune systems (includes who take immunosuppressive medications)
		 * - how long the vaccination can protect people
		 * 
	 */
	public void draw(PApplet surface) {
		screenHeight = surface.height;
		screenWidth = surface.width;
		johnsonY = this.y+screenHeight/3;
		modernaY = this.y+ 2*(screenHeight/3);
		buttonWidth = screenWidth/3;
		buttonHeight = screenHeight/7;
		drawButton(surface, (int)pfizerX, (int)pfizerY, (int)buttonWidth, (int)buttonHeight, "Pfizer-BioNTech");
		drawButton(surface, (int)johnsonX, (int)johnsonY, (int)buttonWidth, (int)buttonHeight, "Johnson & Johnson");
		drawButton(surface, (int)modernaX, (int)modernaY, (int)buttonWidth, (int)buttonHeight, "Moderna");
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

	public static boolean openWebpage(URL url) {
	    try {
	        return openWebpage(url.toURI());
	    } catch (URISyntaxException e) {
	        e.printStackTrace();
	    }
	    return false;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public int getButtonWidth() {
		return this.buttonWidth;
	}
	
	public int getButtonHeight() {
		return this.buttonHeight;
	}
	
	public void setPfizer(boolean state) {
		pfizerButton = state;
	}
	
	public void setJohnson(boolean state) {
		johnsonButton = state;
	}
	
	public void setModerna(boolean state) {
		modernaButton = state;
	}

}
