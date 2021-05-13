
import javax.swing.JButton;
import processing.core.PApplet;

/**draws the graphics and
 * 
 * @author sophie
 *	@revision 5/13
 */
public class DrawingSurface extends PApplet{
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
	public void setup() {
		textSize(width/60);
		fill(252);
		textSize(12);
	}
	
	/*
	 * draws all aspects of the project
	 */
	public void draw() {
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
		  //checks what page the window is on
		  if(mainPage) {
			  fill(252);
			  rect(0, 0, width, height);
			  fill(0);
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
			if(overButton(width-60, height-50, 50, 25)) {
				mapPage = false;
				mainPage = true;
				reset();
			}
		}
		if(infoPage) {
			if(overButton(width-60, height-50, 50, 25)) {
				infoPage = false;
				mainPage = true;
				reset();
			}
			if(overButton(moreInfo.getX(), moreInfo.getY(), moreInfo.getButtonWidth(), moreInfo.getButtonHeight())) {
				moreInfo.setPfizer(true);
				moreInfo.setJohnson(false);
				moreInfo.setModerna(false);
			}
			if(overButton(moreInfo.getX(), moreInfo.getY()+ height/3, moreInfo.getButtonWidth(), moreInfo.getButtonHeight())) {
				moreInfo.setPfizer(false);
				moreInfo.setJohnson(true);
				moreInfo.setModerna(false);
			}
			if(overButton(moreInfo.getX(), moreInfo.getY()+ 2*height/3, moreInfo.getButtonWidth(), moreInfo.getButtonHeight())) {
				moreInfo.setPfizer(false);
				moreInfo.setJohnson(false);
				moreInfo.setModerna(true);
			}
		}
		if(insPage) {
			if(overButton(width-60, height-50, 50, 25)) {
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
	 * @param w width of the btuton
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
		map.statePageOpen = false;
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
	    	if(overButton(width-60, height -50, 50, 25)) 
	    		fill(150);
			else {
				  fill(218);
				}
	    	drawButton(width-60, height-50, 50, 25, "back");
	}
	/**
	 * goes to info page 
	 * also makes the buttons dark when hovering over it
	 * draws a back button
	 * @post fill
	 */
	public void goToInfo() {
		fill(255);
		rect(0, 0, width, height);
		moreInfo.draw(this);
		if(overButton(width-60, height -50, 50, 25)) 
    		fill(150);
		else {
			  fill(218);
			}
    	drawButton(width-60, height-50, 50, 25, "back");
    	if(overButton(moreInfo.getX(), moreInfo.getY(), moreInfo.getButtonWidth(), moreInfo.getButtonHeight())) {
    		fill(150);
    		drawButton( moreInfo.getX(),  moreInfo.getY(), moreInfo.getButtonWidth(),moreInfo.getButtonHeight(), "Pfizer-BioNTech");
		}
    	fill(218);
		if(overButton(moreInfo.getX(), moreInfo.getY()+ height/3, moreInfo.getButtonWidth(), moreInfo.getButtonHeight())) {
			fill(150);
			drawButton( moreInfo.getX(),  moreInfo.getY()+ height/3, moreInfo.getButtonWidth(),moreInfo.getButtonHeight(), "Johnson & Johnson");
		}
		fill(218);
		if(overButton(moreInfo.getX(), moreInfo.getY()+ 2*height/3, moreInfo.getButtonWidth(), moreInfo.getButtonHeight())) {
			fill(150);
			drawButton( moreInfo.getX(),  moreInfo.getY()+ 2*height/3, moreInfo.getButtonWidth(),moreInfo.getButtonHeight(), "Moderna");
		}
		fill(218);
	}
	public void goToIns() {
		fill(255);
		rect(0, 0, width, height);
		fill(0);
		textSize(width/27);
		text("Instructions", width/2, height/10);
		textSize(width/60);
		text("press the map button to go to the country map\n"
				+ "the country map page shows a map of the country with countrywide statistics about the vaccines\n "
				+ "on the map page, click the three lines in the corner to open a drop down\n"
				+ "from there, there is an option to choose a state, which leads to the map of that state with its corresponding statistics\n\n\n"
				+ "press the more info button to get more information\n"
				+ "the more information page will give you more information about the different types of vaccine\n\n\n"
				+ "press the back button to go back to the main page", width/2, 5*height/10);
	    	if(overButton(width-60, height -50, 50, 25)) 
	    		fill(150);
			else {
				  fill(218);
				}
	    	drawButton(width-60, height-50, 50, 25, "back");
	    	
	}


}
