import processing.core.PApplet;
import processing.core.PImage;

/**
 * 
 * @author roopa
 * date: 05/07/2021
 * @revision 1
 *
 */
public class State extends Frame{
	private StatesGraphics graph;
	private String name;
	private PImage map;
	private int mapWidth, mapHeight;
	private int screenHeight, screenWidth;
	private double graphWidth, graphHeight, graphX, graphY;
	
	private int stateButtonX = 0;
	private int stateButtonY = 0;
	private float stateButtonDist = 0;
	private int stateButtonWidth = 0;
	private int stateButtonHeight = 0;
	
	private int r, g, b, r1, g1, b1;
	
	/**
	 * calls the no-args StatesGraphic constructor
	 */
	public State() {
		super("US_MAP.png");
		graph = new StatesGraphics();
		r = 99;
		g = 207;
		b = 248;
		r1= 214;
		g1= 244;
		b1= 255;
	}
	
	/**
	 * specifies the state for which we are drawing the graphs and obtaining statistics, also sets the picture of the state
	 * @param stateName full name of the state
	 */
	public State(String stateName) {
		super(stateName+".png");
		graph = new StatesGraphics(stateName);
		name = stateName;
		r = 99;
		g = 207;
		b = 248;
		r1= 214;
		g1= 244;
		b1= 255;
	}
	
	
	/**
	 * draws the graph generated from the StatesGraphics class and writes all the numerical statistics
	 * @param surface
	 */
	public void draw(PApplet surface) {
		map = surface.loadImage("maps/" + name +".png");
		mapWidth = map.width;
		mapHeight = map.height;
		screenHeight = surface.height;
		screenWidth = surface.width;
		stateButtonX = graph.getButtonX();
		stateButtonY = graph.getButtonY();
		stateButtonDist = graph.getButtonDistance();
		stateButtonWidth = graph.getButtonWidth();
		stateButtonHeight = graph.getButtonHeight();
		
		
		if (screenHeight < screenWidth) {
			if (mapWidth > screenWidth/2) {
				map.resize(screenWidth/2, 0);
			} 
			if (mapHeight > screenHeight/2) {
				map.resize(0, (screenHeight/2));
			}
		} else {
			map.resize(screenWidth/2, 0);
		}
		
		graph.draw(surface);
		
		surface.image(map, screenWidth/100, screenHeight/100);

		drawDropDownButton(surface, screenWidth);
		buttons(surface);
	}
	
	public void buttons(PApplet surface) {
		r = 99;
		g = 207;
		b = 248;
		r1= 214;
		g1= 244;
		b1= 255;
		if(overButton(stateButtonX, (int)(stateButtonY+stateButtonDist), stateButtonWidth,stateButtonHeight)) 
				surface.fill(r, g, b);
			else 
				surface.fill(r1,g1,b1);
		drawButton(surface, stateButtonX, (int)(stateButtonY+stateButtonDist), stateButtonWidth,stateButtonHeight, graph.getVaxAvailable(), 218);
		if(overButton(stateButtonX, (int)(stateButtonY+stateButtonDist*2), stateButtonWidth,stateButtonHeight)) 
			surface.fill(r, g, b);
			else 
				surface.fill(r1,g1,b1);
		drawButton(surface, stateButtonX, (int)(stateButtonY+stateButtonDist*2), stateButtonWidth,stateButtonHeight, graph.getVaxDistDisplay(), 218);
		if(overButton(stateButtonX, (int)(stateButtonY+stateButtonDist*3), stateButtonWidth,stateButtonHeight)) 
				surface.fill(r, g, b);
			else 
				surface.fill(r1,g1,b1);
		drawButton(surface, stateButtonX, (int)(stateButtonY+stateButtonDist*3), stateButtonWidth,stateButtonHeight, graph.getDistPercent(), 218);
		if(overButton(stateButtonX, (int)(stateButtonY+stateButtonDist*4), stateButtonWidth,stateButtonHeight)) 
				surface.fill(r, g, b);
			else 
				surface.fill(r1,g1,b1);
		drawButton(surface, stateButtonX, (int)(stateButtonY+stateButtonDist*4), stateButtonWidth,stateButtonHeight, graph.getPeopleVaxedDisplay(), 218);
		if(overButton(stateButtonX, (int)(stateButtonY+stateButtonDist*5), stateButtonWidth,stateButtonHeight)) 
				surface.fill(r, g, b);
			else 
				surface.fill(r1,g1,b1);
		drawButton(surface, stateButtonX, (int)(stateButtonY+stateButtonDist*5), stateButtonWidth,stateButtonHeight, graph.getVaxedPercentDisplay(), 218);
		if(overButton(stateButtonX, (int)(stateButtonY+stateButtonDist*6), stateButtonWidth,stateButtonHeight)) 
				surface.fill(r, g, b);
			else 
				surface.fill(r1,g1,b1);
		drawButton(surface, stateButtonX, (int)(stateButtonY+stateButtonDist*6), stateButtonWidth,stateButtonHeight, graph.getPeopleFullyVaxedDisplay(), 218);
		if(overButton(stateButtonX, (int)(stateButtonY+stateButtonDist*7), stateButtonWidth,stateButtonHeight)) 
				surface.fill(r, g, b);
			else 
				surface.fill(r1,g1,b1);
		drawButton(surface, stateButtonX, (int)(stateButtonY+stateButtonDist*7), stateButtonWidth,stateButtonHeight, graph.getFullyVaxedPercentDisplay(), 218);
		
	}
	
	public void mouseClicked() {
		if(overButton(stateButtonX, (int)(stateButtonY+stateButtonDist), stateButtonWidth, stateButtonHeight)) {
			graph.setClickVaxAvailable(!getClickVaxAvailable());
		}
		if(overButton(stateButtonX, (int)(stateButtonY+stateButtonDist*2), stateButtonWidth, stateButtonHeight)) {
			graph.setClickVaxDistributed(!getClickVaxDistributed());
		}
		if(overButton(stateButtonX, (int)(stateButtonY+stateButtonDist*3), stateButtonWidth, stateButtonHeight)) {
			graph.setClickDistPercent(!getClickDistPercent());
		}
		if(overButton(stateButtonX, (int)(stateButtonY+stateButtonDist*4), stateButtonWidth, stateButtonHeight)) {
			graph.setClickPeopleVaxed(!getClickPeopleVaxed());
		}
		if(overButton(stateButtonX, (int)(stateButtonY+stateButtonDist*5), stateButtonWidth, stateButtonHeight)) {
			graph.setClickTotalVaxPercent(!getClickTotalVaxPercent());
		} 
		if(overButton(stateButtonX, (int)(stateButtonY+stateButtonDist*6), stateButtonWidth, stateButtonHeight)) {
			graph.setClickFullyVaxed(!getClickFullyVaxed());
		}
		if(overButton(stateButtonX, (int)(stateButtonY+stateButtonDist*7), stateButtonWidth, stateButtonHeight)) {
			graph.setClickFullyVaxedPercent(!getClickFullyVaxedPercent());
		}
	}
	

	public boolean overButton(int x, int y, int w, int h) {
	  if (mouseX > x && mouseX < (x + w) && mouseY > y && mouseY < (y + h)) {
	    return true;
	  }
	  return false;
	}
	
	public void drawButton(PApplet surface, int x, int y, int w, int h, String text, int fillColor) {
		surface.fill(fillColor);
		surface.rect(x, y, w, h, 10);
    	surface.textAlign(surface.CENTER, surface.CENTER);
    	surface.fill(0);
    	surface.text(text, x + w/2, y + h/2);
    	surface.noFill();
	}
	//get methods
	/**
	 * @return name of the state
	 */
	public String getName() {
		return name;
	}
	
	public StatesGraphics getGraph() {
		return graph;
	}
	
	public int getButtonX() {
		return graph.getButtonX();
	}
	
	public int getButtonY() {
		return graph.getButtonY();
	}
	
	public int getButtonWidth() {
		return graph.getButtonWidth();
	}
	
	public int getButtonHeight() {
		return graph.getButtonHeight();
	}
	
	public float getButtonDistance() {
		return graph.getButtonDistance();
	}
		
	public boolean getClickVaxAvailable() {
		return graph.getClickVaxAvailable();
	}
	
	public boolean getClickVaxDistributed() {
		return graph.getClickVaxDistributed();
	}
	
	public boolean getClickDistPercent() {
		return graph.getClickDistPercent();
	}
	
	public boolean getClickPeopleVaxed() {
		return graph.getClickPeopleVaxed();
	}
	
	public boolean getClickTotalVaxPercent() {
		return graph.getClickTotalVaxPercent();
	}
	
	public boolean getClickFullyVaxed() {
		return graph.getClickFullyVaxed();
	}
	
	public boolean getClickFullyVaxedPercent() {
		return graph.getClickFullyVaxedPercent();
	}
	
	public String getVaxAvailable() {
		return graph.getVaxAvailable();
	}
	
	public String getVaxDist() {
		return graph.getVaxDistDisplay();
	}
	
	public String getDistPercent() {
		return graph.getDistPercent();
	}
	
	public String getPeopleVaxed() {
		return graph.getPeopleVaxedDisplay();
	}
	
	public String getVaxedPercent() {
		return graph.getVaxedPercentDisplay();
	}
	
	public String getPeopleFullyVaxed() {
		return graph.getPeopleFullyVaxedDisplay();
	}
	
	public String getFullyVaxedPercent() {
		return graph.getFullyVaxedPercentDisplay();
	}
	
	
	//set methods
	public void setClickVaxAvailable(boolean state) {
		graph.setClickVaxAvailable(state);
	}
	
	public void setClickVaxDistributed(boolean state) {
		graph.setClickVaxDistributed(state);
	}
	
	public void setClickDistPercent(boolean state) {
		graph.setClickDistPercent(state);
	}
	
	public void setClickPeopleVaxed(boolean state) {
		graph.setClickPeopleVaxed(state);
	}
	
	public void setClickTotalVaxPercent(boolean state) {
		graph.setClickTotalVaxPercent(state);
	}
	
	public void setClickFullyVaxed(boolean state) {
		graph.setClickFullyVaxed(state);
	}
	
	public void setClickFullyVaxedPercent(boolean state) {
		graph.setClickFullyVaxedPercent(state);
	}
}
