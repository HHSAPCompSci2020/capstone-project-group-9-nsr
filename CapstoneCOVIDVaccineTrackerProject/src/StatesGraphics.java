
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
 * @author nodoka
 *
 */
public class StatesGraphics {
	
	private String name;
	
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
	public void drawGraph(PApplet p, double x, double y, double width, double height){
		
		Stats stat = new Stats();
		ArrayList<Double> cases = stat.getDoubleCovidData(name, 3);
//		ArrayList<Double> cases = new ArrayList<Double>();
//		
//		//528784.0
//		cases.add(12.0);
//		cases.add(947.0);
//		cases.add(38962.0);
//		cases.add(70358.0);
//		cases.add(101334.0);
//		cases.add(530988.0);
		
		ArrayList<String> dates = stat.getStringCovidData(name, 0);
//		ArrayList<String> dates = new ArrayList<String>();
//
//		dates.add("2020-12-20");
//		dates.add("2020-12-21");
//		dates.add("2020-12-22");
//		dates.add("2020-12-23");
//		dates.add("2020-12-24");
//		dates.add("2020-12-25");

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
		
		p.fill(0);
		p.textSize(10);
		p.text((int)b + "", (float)x - 15, (float)y);

		for(int i = 1; i < 7; i++) {
			p.text((int)(b * i / 7)  + "", (float)x - 15, (float)(y + ((height - 10) * i / 7)));
		}
		
		p.text(dates.get(0), (float)(x), (float)(y + height - 5));
		p.text(dates.get(dates.size()-1), (float)(x - 10 + width), (float)(y + height - 5));

		p.textSize(12);
		p.text("# of covid cases in " + name, (float)(x + (width - 10)/2), (float)((y - 10)));
		p.text("date", (float)(x + ( width - 10)/2), (float)((y + height)));
		p.text("# of cases", (float)((x - 70)), (float)(y + (height - 10)/2));
		
		DateTimeFormatter DATEFORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		LocalDate firstDate = LocalDate.parse(dates.get(0), DATEFORMATTER);
		LocalDate lastDate = LocalDate.parse(dates.get(dates.size()-1), DATEFORMATTER);

		
		//find diff in last and first date
		Period period = Period.between(firstDate, lastDate);
		int diff = Math.abs(period.getDays());
		
		//number in each pixel
		final double PIXEL_PER_X = (width - 10) / dates.size();
//		System.out.println(width + " - " + 10 + " / " + diff);
		final double PIXEL_PER_Y = (height - 10) / b;
		
		//coordinate of the base of the lines
		double xAxis = x + 10;
		double yAxis = y + height - 10;
		
		
		
		//add one day to first date and check if index of that date is existing
		//keep track of how many days added
		//if it is existing access the value at the same index in case data
		//figure out the coordinates
		ArrayList<Point> points = new ArrayList<Point>();
		
		while(!firstDate.equals(lastDate)) {
			if(dates.indexOf(firstDate.toString()) != -1) {
				double px = xAxis + PIXEL_PER_X * dates.indexOf(firstDate.toString());
//				System.out.println(xAxis + " + " + PIXEL_PER_X + " * " + dates.indexOf(firstDate.toString()) + " = " + px);
				double py = yAxis - PIXEL_PER_Y * cases.get(dates.indexOf(firstDate.toString())) ;
//				System.out.println(yAxis + " + " + PIXEL_PER_Y + " * " + cases.get(dates.indexOf(firstDate.toString())) + " = " + py);
				Point po = new Point();
				po.setLocation(px, py);
				points.add(po);
//				System.out.println(px + ", " + py);
			}
			firstDate = firstDate.plusDays(1);
		}
		
		double px = xAxis + PIXEL_PER_X * dates.size()-1;
		double py = yAxis - PIXEL_PER_Y * cases.get(cases.size()-1) ;
		Point po = new Point();
		po.setLocation(px, py);
		points.add(po);
		
		
	
//		p.line(100, 100,200, 100);
		
		for(int i = 0; i < points.size() - 1; i++) {
//			System.out.println(points.get(i).getX() + ", " + points.get(i).getY() + ", " + points.get(i+1).getX() + ", " + points.get(i+1).getY());
			p.line((float)points.get(i).getX(), (float)points.get(i).getY(), (float)points.get(i+1).getX(), (float)points.get(i+1).getY());
		}
		
		writeInfo(p, (x - 70 + x + width) / 2 , y + height + 50);
		
	}
	
	/**
	 * writes recent information of the state vaccination situation including
	 * the name of the vaccines, number of doses availbale, population vaccinated and
	 * people fully vaccinated.
	 * 
	 * @param p PApplet to draw on
	 * @param x x coordinates of center of all texts
	 * @param y y coordinates of the top of where the text starts
	 */
	public void writeInfo(PApplet p, double x, double y) {
		
		Stats stats = new Stats();
		ArrayList<String> list = stats.getVaccinationInfo(name);
		
		p.fill(0);
		p.textSize(20);
		
		p.text("updated as of " + list.get(0), (float)x, (float)(y + 30));
		
		p.textSize(10);
		p.text("total vaccinations available : " + list.get(2), (float)x, (float)(y + 60));
		p.text("total distributed : " + list.get(3), (float)x, (float)(y + 80));
		p.text("total distribution percentage : " + list.get(9), (float)x, (float)(y + 100));
		p.text("people vaccinated : " + list.get(4), (float)x, (float)(y + 120));
		p.text("total vaccinations percentage : " + list.get(6) + "% of the population", (float)x, (float)(y + 140));
		p.text("people fully vaccinated : " + list.get(7), (float)x, (float)(y + 160));
		p.text("fully vaccinated percentage : " + list.get(5) + "% of the population", (float)x, (float)(y + 180));
	}

}
