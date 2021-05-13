
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
	private int mapX, mapY, mapW, mapH;   	 //coordinates and width/height  of the map button
	private boolean mapClicked = false;		//if map button has been clicked
	
	private int infoX, infoY, infoW, infoH;   //coordinates and width/height  of the map button
	private boolean infoClicked = false;		//if INFO button has been clicked
	
	private int insX, insY, insW, insH;    //coordinates and width/height  of the map button
	private boolean insClicked = false;		//if map button has been clicked
	
	private boolean mainPage;
	private boolean mapPage;
	private boolean infoPage;
	private int x=0;
	public DrawingSurface() {
		map = new Country();
		moreInfo = new MoreInfo();
		mainPage = true;
		mapPage = false;
		infoPage = false;
	}
	
	
	/*
	 * draws all aspects of the project
	 */
	public void draw() {
		  //update fields
		buttonW= width/3;
		buttonH = height/10;
		insX = width/3;
		insY = 7*(int)(height/10.0);
		  insW = width/3;
		  insH = height/10;
		  mapX = width/3;
		  mapY = 5*(int)(height/10.0);
		  mapW = width/3;
		  mapH = height/10;
		  infoX = width/3;
		  infoY = 7*(int)(height/10.0);
		  infoW = width/3;
		  infoH = height/10;
		  //checks what page the window is on
		  if(mainPage) {
			  fill(252);
			  rect(0, 0, width, height);
			  buttons();
			  if(mapClicked) {
				  mapPage = true;
				  mainPage = false;
				  infoPage = false;
			  }
			  if(infoClicked) {
				  mapPage = false;
				  mainPage = false;
				  infoPage = true;
		  	}
		  }
		  if(mapPage) {
			  if(x<150) {
				  animation("going to map", mapX, mapY, mapW, mapH);
				  fill(218);
				  	drawButton(infoX, infoY, infoW, infoH, "more information");
				  x++;
			  }
			  else
				  goToMap();
				  
		  }
		  if(infoPage) {
			  if(x<150) {
				  animation("going to more information", infoX, infoY, infoW, infoH);
				  fill(218);
				  	drawButton(mapX, mapY, mapW, mapH, "map");
				  x++;
			  }
			  else
				  goToInfo();
			  
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
			if (overButton(mapX, mapY, mapW, mapH)) {
				mapClicked = true;
				infoClicked = false;
				x=0;
			} 
			if (overButton(infoX, infoY, infoW, infoH)) {
				infoClicked = true;
				mapClicked = false;
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
		if(overButton(mapX, mapY, mapW, mapH)) 
			  fill(150);
			else {
			  fill(218);
			}
	    	drawButton(mapX, mapY, mapW, mapH, "map");
	
	    if(overButton(infoX, infoY, infoW, infoH)) 
			  fill(150);
			else {
			  fill(218);
			}
	    	drawButton(infoX, infoY, infoW, infoH, "more information");
	    
	    
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
	}
	/**resets all the variables to default so when someone decides to go back, it will be all default
	 * 
	 */
	public void reset() {
		animation=0;
		mapClicked = false;
		infoClicked= false;
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


}
