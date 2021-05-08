/**@author 
 * 
	 * 
	 */

import javax.swing.JButton;

import processing.core.PApplet;
public class DrawingSurface extends PApplet{
	JButton map;
	JButton moreInfo;
	
	int animation=0;
	
	int mapX, mapY, mapW, mapH;    
	boolean mapClicked = false;
	
	int infoX, infoY, infoW, infoH;    
	boolean infoClicked = false;
	
	boolean mainPage = true;
	boolean mapPage = false;
	boolean statePage = false;
	boolean infoPage = false;
	
	
	public DrawingSurface() {
	map = new JButton("Map");
	moreInfo = new JButton("More Information");
	}
	
	
	public void setup() {
	  ellipseMode(CENTER);
	}
	/*
	 * draws all aspects of the project
	 */
	public void draw() {
		
		  fill(218);
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
			  buttons();
			  if(mapClicked) {
				  mapPage = true;
				  mainPage = false;
				  statePage = false;
				  infoPage = false;
			  }
			  if(infoClicked) {
			  
		  	}
		  }
		  if(mapPage) {
			  reset();
			  fill(255);
			  rect(0, 0, width, height);
		  }
	}

	 boolean overButton(int x, int y, int w, int h) {
		    if (mouseX > x && mouseX < (x + w) && mouseY > y && mouseY < (y + h)) {
		      return true;
		    }
		    return false;
		  }
	 
	public void mouseClicked() {
		if (mouseX > mapX && mouseX < (mapX + mapW) && mouseY > mapY && mouseY < (mapY + mapH)) {
		  mapClicked = true;
		  infoClicked = false;
	  } 
		if (mouseX > infoX && mouseX < (infoX + infoW) && mouseY > infoY && mouseY < (infoY + infoH)) {
			  infoClicked = true;
			  mapClicked = false;
		  } 
	}
	private void animation(String text, int x, int y, int w, int h) {
		 fill(218);
		  rect(x, y, w, h, 10);
		  fill(0, 102, 153);
		  if(animation<=100) 
			  text(text+ ".",x+ (w / 2), y + (h / 2));
		  else if(animation>100 && animation<=200) {
			  text(text+ "..",x + (w / 2), y + (h / 2));
		  }
		  else if(animation>200 && animation<=300) 
			  text(text+ "...", x + (w / 2), y + (h / 2));
		  else
			  animation = 0;
   	 	animation++;
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
	    if(!mapClicked) {
	    	rect(mapX, mapY, mapW, mapH, 10);
	    	textAlign(CENTER, CENTER);
	    	fill(0);
	    	text("map", mapX + mapW/2, mapY + mapH/2);
	    }
	    else if(mapClicked) {
	    	animation("going to map", mapX, mapY, mapW, mapH);
	    	//bring to map page
	    }
	    if(overButton(infoX, infoY, infoW, infoH)) 
			  fill(150);
			else {
			  fill(218);
			}
	    if(!infoClicked) {
	    	rect(infoX, infoY, infoW, infoH, 10);
	    	textAlign(CENTER, CENTER);
	    	fill(0);
	    	text("more information", infoX + infoW/2, infoY + infoH/2);
	    }
	    else if(infoClicked) {
	    	animation("going to more information", infoX, infoY, infoW, infoH);
	    	//bring to map page
	    }
	    
	}
	public void reset() {
		animation=0;
		mapClicked = false;
	}
	public void goToMain() {
		
	}
	public void goToMap() {
		
	}
	public void goToState(String state) {
		
	}
	public void goToInfo() {
	
	}


}
