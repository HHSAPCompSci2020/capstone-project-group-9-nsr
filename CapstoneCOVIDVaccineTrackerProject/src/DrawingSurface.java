
import java.awt.Color;

import javax.swing.JButton;
import processing.core.PApplet;
import processing.core.PFont;

/**draws the graphics and
 * 
 * @author sophie
 *	@revision 5/13
 */
public class DrawingSurface extends PApplet{
	private PFont titles, body;
	
	private Country map;
	private MoreInfo moreInfo;
	private int animation=0;
	
	private int buttonW, buttonH;
	private int mapX, mapY;   	 //coordinates and width/height  of the map button
	private boolean mapClicked = false;		//if map button has been clicked
	
	private int infoX, infoY;   //coordinates and width/height  of the map button
	private boolean infoClicked = false;		//if INFO button has been clicked
	
	private int insX, insY;    //coordinates and width/height  of the map button
	private boolean insClicked = false;		//if map button has been clicked
	
	private int backButtonWidth, backButtonHeight;
	private int backButtonX, backButtonY;
	private int backButtonTextSize;
	
	private boolean mainPage;
	private boolean mapPage;
	private boolean infoPage;
	private boolean insPage;
	private int x=0;
	public DrawingSurface() {
		map = new Country();
		moreInfo = new MoreInfo();
		mainPage = true;
		mapPage = false;
		infoPage = false;
	}
	
	public void settings() {
		fullScreen();
	}
	
	public void setup() {
		titles = createFont("fonts/LondrinaOutline-Regular.ttf", 10);
		body = createFont("fonts/Montserrat-Regular.ttf", 10);
		textSize(width/60);
		fill(252);
		textSize(12);
	}
	
	/*
	 * draws all aspects of the project
	 */
	public void draw() {
		background(255);
		textSize(width/60);
		fill(252);
		  //update fields
		buttonW= width/3;
		buttonH = height/10;
		insX = width/3;
		insY = 3*(int)(height/10.0);
		mapX = width/3;
		mapY = 5*(int)(height/10.0);
		infoX = width/3;
		infoY = 7*(int)(height/10.0);
		backButtonWidth = width/25;
		backButtonHeight = height/25;
		backButtonX = width-backButtonWidth-width/100;
		backButtonY = height-backButtonHeight-height/100;
		backButtonTextSize = height/50;
		  //checks what page the window is on
		  if(mainPage) {
			  fill(252);
			  rect(0, 0, width, height);
			  fill(0);
			  callFont(titles, 0);
			  textSize(width/18);
				text("Covid Vaccine Tracker", width/2, height/10);
				textSize(width/60);
				 fill(252);
			  buttons();
			  if(mapClicked) {
				  mapPage = true;
				  mainPage = false;
				  infoPage = false;
				  insPage = false;
			  }
			  if(infoClicked) {
				  mapPage = false;
				  mainPage = false;
				  infoPage = true;
				  insPage = false;
		  	}
			  if(insClicked) {
				  mapPage = false;
				  mainPage = false;
				  infoPage = false;
				  insPage = true;
			  }
		  }
		  if(mapPage) {
			  if(x<150) {
				  fill(0);
				  callFont(titles, 0);
				  textSize(width/18);
				  text("Covid Vaccine Tracker", width/2, height/10);
				  textSize(width/60);
				  callFont(body, 0);
				  fill(252);
				  animation("going to map", mapX, mapY, buttonW, buttonH);
				  fill(218);
				  	drawButton(infoX, infoY, buttonW, buttonH, "more information");
				  	drawButton(insX, insY, buttonW, buttonH, "instructions");
				  x++;
			  }
			  else
				  goToMap();
				  
		  }
		  if(infoPage) {
			  if(x<150) {
				  fill(0);
				  callFont(titles, 0);
				  textSize(width/18);
				  text("Covid Vaccine Tracker", width/2, height/10);
				  textSize(width/60);
				  callFont(body, 0);
				  fill(252);
				  animation("going to more information", infoX, infoY, buttonW, buttonH);
				  fill(218);
				  	drawButton(mapX, mapY, buttonW, buttonH, "map");
				  	drawButton(insX, insY, buttonW, buttonH, "instructions");
				  x++;
			  }
			  else
				  goToInfo();
			  
		  }
		  if(insPage) {
			  if(x<150) {
				  fill(0);
				  callFont(titles, 0);
				  textSize(width/18);
				  text("Covid Vaccine Tracker", width/2, height/10);
				  callFont(body, 0);
				  textSize(width/60);
				  fill(252);
				  animation("going to instructions", insX, insY, buttonW, buttonH);
				  fill(218);
				  drawButton(mapX, mapY, buttonW, buttonH, "map");
				  drawButton(infoX, infoY, buttonW, buttonH, "more information");
				  x++;
			  }
			  else
				  goToIns();
		  }
		
	}
	/**checks if the mouse is over the button
	 * 
	 * @param x x coordinate of the button
	 * @param y y coordinate of the button
	 * @param w width of the button
	 * @param h height of the button
	 * @return boolean if the mouse is over the button
	 */
	public boolean overButton(int x, int y, int w, int h) {
		    if (mouseX > x && mouseX < (x + w) && mouseY > y && mouseY < (y + h)) {
		      return true;
		    }
		    return false;
		  }
	/**detects if the mouse is clicked
	 * and changes booleans to change pages
	 */
	public void mouseClicked() {
		if(mainPage) {
			if (overButton(mapX, mapY, buttonW, buttonH)) {
				mapClicked = true;
				infoClicked = false;
				insClicked = false;
				x=0;
			} 
			if (overButton(infoX, infoY, buttonW, buttonH)) {
				infoClicked = true;
				mapClicked = false;
				insClicked = false;
				x= 0;
		  } 
			if (overButton(insX, insY, buttonW, buttonH)) {
				insClicked = true;
				mapClicked = false;
				infoClicked = false;
				x= 0;
		  } 
		}
		if(mapPage) {
			map.openDropDown = overButton(map.getScreenW()-45, 0, 45, 40);
			if(overButton(backButtonX, backButtonY, backButtonWidth, backButtonHeight)) {
				mapPage = false;
				mainPage = true;
				reset();
			}
			if(overButton(map.buttonX,(int)(map.buttonY+ map.buttonDistance), map.buttonWidth, map.buttonHeight)) {
				map.clickAvailableVax = true;
			}
			if(overButton(backButtonX, (int)(backButtonY+ map.buttonDistance *2), backButtonWidth, backButtonHeight)) {
				map.clickFullyVaxed = true;
				reset();
			}
			if(overButton(backButtonX, (int)(backButtonY+ map.buttonDistance *3), backButtonWidth, backButtonHeight)) {
				map.clickPeopleVaxed = true; 
			}
			if(overButton(backButtonX, (int)(backButtonY+ map.buttonDistance *4), backButtonWidth, backButtonHeight)) {
				map.clickVaxName = true;
			}
		}
		if(infoPage) {
			if(overButton(backButtonX, backButtonY, backButtonWidth, backButtonHeight)) {
				infoPage = false;
				mainPage = true;
				reset();
			}
			if(overButton((int)moreInfo.getButtonX(), (int)moreInfo.getButtonY(), moreInfo.getButtonWidth(), moreInfo.getButtonHeight())) {
				moreInfo.setPfizer(true);
				moreInfo.setJohnson(false);
				moreInfo.setModerna(false);
			}
			if(overButton((int)moreInfo.getButtonX(),(int)(2* moreInfo.getButtonY()), moreInfo.getButtonWidth(), moreInfo.getButtonHeight())) {
				moreInfo.setPfizer(false);
				moreInfo.setJohnson(true);
				moreInfo.setModerna(false);
			}
			if(overButton((int)moreInfo.getButtonX(), (int)(3*moreInfo.getButtonY()), moreInfo.getButtonWidth(), moreInfo.getButtonHeight())) {
				moreInfo.setPfizer(false);
				moreInfo.setJohnson(false);
				moreInfo.setModerna(true);
			}
		}
		if(insPage) {
			if(overButton(backButtonX, backButtonY, backButtonWidth, backButtonHeight)) {
				insPage = false;
				mainPage = true;
				reset();
			}
		}
	}
	/**
	 * draws the animation for the buttons
	 * @param text text of what the animation is going to look like
	 * @param x x coordinate of the button
	 * @param y y coordinate of the button
	 * @param w width of the butotn
	 * @param h height of the button]
	 * @post fill(252)
	 */
	private void animation(String text, int x, int y, int w, int h) {
		callFont(body, 0);
		textSize(width/60);
		 	fill(218);
		  rect(x, y, w, h, 10);
		  fill(0, 102, 153);
		  if(animation<=50) 
			  text(text+ ".",x+ (w / 2), y + (h / 2));
		  else if(animation>50 && animation<=100) {
			  text(text+ "..",x + (w / 2), y + (h / 2));
		  }
		  else if(animation>100 && animation<=150) 
			  text(text+ "...", x + (w / 2), y + (h / 2));
		  else
			  animation = 0;
   	 	animation++;
   	 	fill(252);
	}
	/**checks if the mouse is over the map and info button
	 * makes the button darker when you hover over it and draws the button
	 * @post textAlign center, center
	 * @post fill
	 */
	public void buttons() {
		callFont(body, 0);
		textSize(width/60);
		if(overButton(mapX, mapY, buttonW,buttonH)) 
			  fill(150);
		else {
			  fill(218);
			}
	    drawButton(mapX, mapY, buttonW, buttonH, "map");
	
	    if(overButton(infoX, infoY, buttonW, buttonH)) 
			  fill(150);
		else {
			  fill(218);
			}
	    drawButton(infoX, infoY, buttonW, buttonH, "more information");
	    if(overButton(insX, insY, buttonW, buttonH)) 
				fill(150);
		else {
				  fill(218);
		}
		 drawButton(insX, insY, buttonW, buttonH, "instructions");
	    
	}
	/**
	 * draws a button 
	 * @param x x coordinate of the button
	 * @param y y coordinate of the button
	 * @param w width of the button
	 * @param h height of the button
	 * @param text text on the button
	 */
	public void drawButton(int x, int y, int w, int h, String text) {
		rect(x, y, w, h, 10);
    	textAlign(CENTER, CENTER);
    	fill(0);
    	text(text, x + w/2, y + h/2);
    	fill(218);
	}
	/**resets all the variables to default so when someone decides to go back, it will be all default
	 * 
	 */
	public void reset() {
		animation=0;
		mapClicked = false;
		infoClicked= false;
		insClicked = false;
		moreInfo.setPfizer(false);
		moreInfo.setJohnson(false);
		moreInfo.setModerna(false);
		map.statePageOpen = false;
		map.clickAvailableVax = false;
		map.clickFullyVaxed = false;
		map.clickPeopleVaxed = false; 
		map.clickVaxName = false;
		x = 0;
	}
	/**goes to map page
	 * draws a back button
	 * @post fill(255)
	 */
	public void goToMap() {
		fill(255);
		rect(0, 0, width, height);
		map.draw(this);
	    	if(overButton(backButtonX, backButtonY, backButtonWidth, backButtonHeight)) 
	    		fill(150);
			else {
				  fill(218);
				}
			textSize(backButtonTextSize);
	    	drawButton(backButtonX, backButtonY, backButtonWidth, backButtonHeight, "back");
	    	
	    	if(overButton(map.buttonX,(int)(map.buttonY+ map.buttonDistance), map.buttonWidth, map.buttonHeight)) 
	    		fill(150);
			else {
				  fill(218);
				}
	    	drawButton(map.buttonX,(int)(map.buttonY+ map.buttonDistance), map.buttonWidth, map.buttonHeight, "names of vaccines used today");
	    	
	    	if(overButton(map.buttonX,(int)(map.buttonY+ map.buttonDistance*2), map.buttonWidth, map.buttonHeight)) 
	    		fill(150);
			else {
				  fill(218);
				}
	    	drawButton(map.buttonX,(int)(map.buttonY+ map.buttonDistance*2), map.buttonWidth, map.buttonHeight, "total vaccinations available");
	    	if(overButton(map.buttonX,(int)(map.buttonY+ map.buttonDistance*3), map.buttonWidth, map.buttonHeight)) 
	    		fill(150);
			else {
				  fill(218);
				}
	    	drawButton(map.buttonX,(int)(map.buttonY+ map.buttonDistance*3), map.buttonWidth, map.buttonHeight, "people vaccinated");
	    	if(overButton(map.buttonX,(int)(map.buttonY+ map.buttonDistance*4), map.buttonWidth, map.buttonHeight)) 
	    		fill(150);
			else {
				  fill(218);
				}
	    	drawButton(map.buttonX,(int)(map.buttonY+ map.buttonDistance*4), map.buttonWidth, map.buttonHeight, "people fully vaccinated");
	    	
	}
	/**
	 * goes to info page 
	 * also makes the buttons dark when hovering over it
	 * draws a back button
	 * @post fill
	 */
	public void goToInfo() {
		callFont(body, 0);
		fill(255);
		rect(0, 0, width, height);
		moreInfo.draw(this);
		if(overButton(backButtonX, backButtonY, backButtonWidth, backButtonHeight)) 
    		fill(150);
		else {
			  fill(218);
			}
		textSize(backButtonTextSize);
    	drawButton(backButtonX, backButtonY, backButtonWidth, backButtonHeight, "back");
    	if(overButton((int)moreInfo.getButtonX(), (int)moreInfo.getButtonY(), moreInfo.getButtonWidth(), moreInfo.getButtonHeight())) {
    		textSize(height/50);
    		fill(150);
    		drawButton((int) moreInfo.getButtonX(), (int)moreInfo.getButtonY(), moreInfo.getButtonWidth(),moreInfo.getButtonHeight(), "Pfizer-BioNTech");
		}
    	fill(218);
		if(overButton((int)moreInfo.getButtonX(), (int)(2* moreInfo.getButtonY()), moreInfo.getButtonWidth(), moreInfo.getButtonHeight())) {
			textSize(height/50);
			fill(150);
			drawButton( (int)moreInfo.getButtonX(), (int)(2* moreInfo.getButtonY()), moreInfo.getButtonWidth(),moreInfo.getButtonHeight(), "Johnson & Johnson");
		}
		fill(218);
		if(overButton((int)moreInfo.getButtonX(), (int)(3*moreInfo.getButtonY()), moreInfo.getButtonWidth(), moreInfo.getButtonHeight())) {
			textSize(height/50);
			fill(150);
			drawButton( (int)moreInfo.getButtonX(),  (int)(3*moreInfo.getButtonY()), moreInfo.getButtonWidth(),moreInfo.getButtonHeight(), "Moderna");
		}
		fill(218);
	}
	public void goToIns() {
		callFont(titles, 0);
		fill(255);
		rect(0, 0, width, height);
		fill(0);
		textSize(width/60);
		textSize(width/27);
		text("Instructions", width/2, height/10);
		callFont(body, 0);
		textSize(width/60);
		textAlign(LEFT);
		
		text("- press the map button to go to the country map\n"
				+ "- the country map page shows a map of the country with countrywide statistics about the vaccines\n"
				+ "- on the map page, click the three lines in the corner to open a drop down\n"
				+ "- from there, there is an option to choose a state, which leads to the map of that state with its corresponding statistics\n\n\n"
				+ "- press the more info button to get more information\n"
				+ "- the more information page will give you more information about the different types of vaccine\n\n\n"
				+ "- press the back button to go back to the main page", 2*width/25, 3*height/10);
	    	if(overButton(backButtonX, backButtonY, backButtonWidth, backButtonHeight)) 
	    		fill(150);
			else {
				  fill(218);
				}
	    textSize(backButtonTextSize);	
	    drawButton(backButtonX, backButtonY, backButtonWidth, backButtonHeight, "back");
	    	
	}
	
	private void callFont(PFont font, int fontColor) {
		fill(fontColor);
		textFont(font);
	}


}
