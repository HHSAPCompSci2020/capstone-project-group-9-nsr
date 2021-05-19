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
	
	/**
	 * calls the no-args StatesGraphic constructor
	 */
	public State() {
		super("US_MAP.png");
		graph = new StatesGraphics();
	}
	
	/**
	 * specifies the state for which we are drawing the graphs and obtaining statistics, also sets the picture of the state
	 * @param stateName full name of the state
	 */
	public State(String stateName) {
		super(stateName+".png");
		graph = new StatesGraphics(stateName);
		name = stateName;
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
