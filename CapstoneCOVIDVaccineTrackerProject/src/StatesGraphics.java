
import java.awt.Point;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


import javax.swing.JButton;

import processing.core.PApplet;

/**
 * This class gets data from stats and creates data graphics.
 * 
 * @author 
 *
 */
public class StatesGraphics {
	private String name;
	private ArrayList<Double> numberOfVaccinesAvailable;
	private ArrayList<Double> numberOfVaccinesGiven;
	private ArrayList<Double> percentGiven;
	private ArrayList<String> dates;
	private JButton returnToHome;
	
	/**constructor
	 * if no parameter is inputted, the name is set to null
	 */
	public StatesGraphics() {
		name = null;
	}
	
	/**constructor
	 * sets the name to the parameter String state
	 * @param String state
	 */
	public StatesGraphics(String state) {
		name = state;
	}
	
	/**returns the name of the state
	 * @return name String
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * draws graph by figuring out all points of where the graph needs to be plotted.
	 * 
	 * @param x x coordinate of the left up corner of where the graph is drawn
	 * @param y y coordinate of the left up corner of where the graph is drawn
	 * @param width width of the rectangle the graph is in
	 * @param height height of the rectangle the graph is in
	 * @throws ParseException 
	 */
	@SuppressWarnings("unchecked")
	public void drawGraph(PApplet p, double x, double y, double width, double height) throws ParseException {
		
		Stats stat = new Stats();
		ArrayList<Double> cases = (ArrayList<Double>) stat.getCasesData(name, 2);
		ArrayList<String> dates = (ArrayList<String>) stat.getCasesData(name, 1);
		
		//figure out the biggest number of the arraylist to scale y
		double b = cases.get(0); //write this as a text on top of the yaxis
		
		for(int i = 1; i < cases.size(); i++) {
			if(b < cases.get(i)) {
				b = cases.get(i);
			}
		}
		
		//draw the frame of the graph
		p.line((float)x + 10, (float)y, (float)x + 10, (float)(y + height - 10));
		p.line((float)x+10, (float)(y + height - 10), (float)(x + width - 10), (float)(y + height - 10));
		
		p.text(b + "", (float)x, (float)y);
		
		DateTimeFormatter DATEFORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		LocalDate firstDate = LocalDate.parse(dates.get(0), DATEFORMATTER);
		LocalDate lastDate = LocalDate.parse(dates.get(dates.size()-1), DATEFORMATTER);
		
		//find diff in last and first date
		Period period = Period.between(firstDate, lastDate);
		int diff = Math.abs(period.getDays());
		
		//number in each pixel
		final double PIXEL_PER_X = (width - 10) / diff;
		final double PIXEL_PER_Y = (height - 10) / b;
		
		//add one day to first date and check if index of that date is existing
		//keep track of how many days added
		//if it is existing access the value at the same index in case data
		//figure out the coordinates
		ArrayList<Point> points = new ArrayList<Point>();
		
		for(int i = 0; i <= diff; i++) {
			if(dates.indexOf(firstDate.toString()) != -1) {
				double px = PIXEL_PER_X * dates.indexOf(firstDate.toString());
				double py = PIXEL_PER_Y * cases.get(dates.indexOf(firstDate.toString()));
				Point po = new Point();
				po.setLocation(px, py);
				points.add(po);
			}
			firstDate.plusDays(1);
		}
		
		for(int i = 0; i < points.size() - 1; i++) {
			p.line((float)points.get(i).getX(), (float)points.get(i).getY(), (float)points.get(i+1).getX(), (float)points.get(i+1).getY());
		}
		
	}

}
