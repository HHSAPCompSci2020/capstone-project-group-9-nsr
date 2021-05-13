
import javax.swing.JButton;
import processing.core.PApplet;

/**
 * 
 * @author sophie
 *
 */
public class DrawingSurface extends PApplet{
	private Country map;
	private MoreInfo moreInfo;
	private int animation=0;
	
	private int mapX, mapY, mapW, mapH;    
	private boolean mapClicked = false;
	
	private int infoX, infoY, infoW, infoH;    
	private boolean infoClicked = false;
	
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
		  mapX = width/3;
		  mapY = 5*(int)(height/10.0);
		  mapW = width/3;
		  mapH = height/10;
		  infoX = width/3;
		  infoY = 7*(int)(height/10.0);
		  infoW = width/3;
		  infoH = height/10;
		  
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

	public boolean overButton(int x, int y, int w, int h) {
		    if (mouseX > x && mouseX < (x + w) && mouseY > y && mouseY < (y + h)) {
		      return true;
		    }
		    return false;
		  }
	 
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
	/**
	 * @post textAlign center, center
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
	public void drawButton(int x, int y, int w, int h, String text) {
		rect(x, y, w, h, 10);
    	textAlign(CENTER, CENTER);
    	fill(0);
    	text(text, x + w/2, y + h/2);
	}
	public void reset() {
		animation=0;
		mapClicked = false;
		infoClicked= false;
		map.statePageOpen = false;
		x = 0;
	}
	public void goToMain() {
		reset();
	}
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
