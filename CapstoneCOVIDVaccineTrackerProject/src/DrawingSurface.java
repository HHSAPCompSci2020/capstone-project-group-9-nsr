/**@author 
 * 
	 * 
	 */

import javax.swing.JButton;

import processing.core.PApplet;
public class DrawingSurface extends PApplet{
	JButton map;
	JButton moreInfo;
	int x, y, w, h;    //width and height and starting point
	int animation=0;
	boolean mapClicked = false;
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
		  //if map button clicked
		  x = width/3;
		  y = 5*(int)(height/10.0);
		  w = width/3;
		  h = height/10;
		  if(overButton()) {
			  fill(150);
		  }else {
			  fill(218);
		  }
		    if(!mapClicked) {
		    rect(x, y, w, h, 10);
		    textAlign(CENTER, CENTER);
		    fill(0);
		    text("map", x + w/2, y + h/2);
		    }
		    else if(mapClicked) {
		    	animation();
		    	//bring to map page
		    }
	}

	 boolean overButton() {
		    if (mouseX > x && mouseX < (x + w) && mouseY > y && mouseY < (y + h)) {
		      return true;
		    }
		    return false;
		  }
	 
	public void mouseClicked() {
		if (mouseX > x && mouseX < (x + w) && mouseY > y && mouseY < (y + h)) {
		  mapClicked = true;
	  } 
	}
	private void animation() {
		 fill(218);
		  rect(x, y, w, h, 10);
		  fill(0, 102, 153);
   	 if(animation<=100) 
			  text("going to map.", x+ (w / 2), y + (h / 2));
		  else if(animation>100 && animation<=200) {
			  text("going to map..", x + (w / 2), y + (h / 2));
		  }
		  else if(animation>200 && animation<=300) 
			  text("going to map...", x + (w / 2), y + (h / 2));
		  else
			  animation = 0;
   	 	animation++;
	}


}
