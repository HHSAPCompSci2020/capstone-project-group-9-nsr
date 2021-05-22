
import java.awt.Point;
import java.io.IOException;
import java.text.ParseException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JButton;

import processing.core.PApplet;

/**
 * This class gets creates graohics for the individual state classes including graphs and statistics.
 * 
 * @author nodoka
 *
 */
public class StatesGraphics{
	
	private Stats stat;	
	private String name;
	
	private double graphWidth, graphHeight;
	
	private ArrayList<Double> cases, deaths, vaccineList;
	private ArrayList<String> dates, vaccine, vaccineDates, covidDates;
	
	private boolean infoAvailable;

	
	/**constructor
	 * if no parameter is inputted, the name is set to null
	 */
	public StatesGraphics() {
		this(null);
	}
	
	/**constructor
	 * sets the name to the parameter String state
	 * @param String state
	 */
	public StatesGraphics(String state) {
		stat = new Stats();
		name = state;
		vaccine = stat.getLatestVaccineInfo(name);
		System.out.println("state: " + state + " vaccine:" + vaccine);
	}
	
	

	/**returns the name of the state
	 * @return name String
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * 
	 * @param lastDay
	 * @return
	 */
	private ArrayList<Double> predictData(LocalDate lastDay){
		
		ArrayList<Double> preCases = new ArrayList<Double>();
		vaccine = stat.getLatestVaccineInfo(name);
		
		double full = Double.parseDouble(vaccine.get(5));
		double once = Double.parseDouble(vaccine.get(4));

		preCases.add(cases.get(cases.size()-1));
		
		for(int i = 0; i < 14; i++) {
			
			double casesFifteen = cases.get(((dates.indexOf(dates.size()-15))));
			double casesFourteen = cases.get(((dates.indexOf(dates.size()-14))));
			double diff = casesFifteen - casesFourteen;
			
			if(diff < 0) {
				diff = 0;
			}
			
			preCases.add(preCases.get(i) * 2 - (full * 0.9) - (once * 0.8));
			
		}
		
		return preCases;
	}
	
	/**
	 * 
	 * @return
	 */
//	private ArrayList<Double> predictData(){
//		
//		ArrayList<Double> cases = stat.getDoubleCovidData(name, 3);
//		ArrayList<Double> deaths = stat.getDoubleCovidData(name, 4);
//		
//		String[] states  = {"california", "texas", "florida", "new york", "pennsylvania", 
//				"illinois", "ohio", "georgia", "north carolina", "michigan", "new jersey",
//				"virginia", "washington", "arizona", "massachusetts", "tennessee", "indiana",
//				"missouri", "maryland", "wisconsin", "colorado", "minnesota", "south carolina",
//				"alabama", "louisiana", "kentucky", "oregon", "oklahoma", "connecticut",
//				"utah", "iowa", "nevada", "arkansas", "mississippi", "kansas", "new mexico",
//				"nebraska", "idaho", "west virginia", "hawaii", "new hampshire", "maine",
//				"montana", "rhode island", "delaware", "south dakota", "north dakota", "alaska",
//				"vermont", "wyoming"};
//		
//		int[] population = {39512223, 28995881, 21477737, 19453561, 12801989, 12671821, 11689100,
//				10617423, 10488084, 9986857, 8882190, 8535519, 7614893, 7278717, 6949503, 6833174,
//				6732219, 6137428, 6045680, 5822434, 5758736, 5639632, 5148714, 4903185, 4648794, 
//				4467673, 4217737, 3956971, 3565287, 3205958, 3155070, 3080156, 3017825, 2976149,
//				2913314, 2096829, 1934408, 1787065, 1792147, 1415872, 1359711, 1344212, 1068778,
//				1059361, 973764, 884659, 762062, 731545, 623989, 578759};
//		
//		double totalCase = 0;
//		
//		for(int i = 0; i < cases.size(); i++) {
//			totalCase += cases.get(i);
//		}
//		
//		ArrayList<String> states2 = new ArrayList<String>(); 
//		
//		for(int i = 0; i < states.length; i++) {
//			states2.add(states[i]);
//		}
//		
//		int N = population[states2.indexOf(name)]; // Population
//		double I0 = cases.get(cases.size()-1); // Starting infected/exposed
//		double beta = 0.57; // Infection rate
//		double gamma = 0.0714; // recovery time (days to the -1)
//		double a = 0.142; // incubation period (days to the -1)
//		
//		ArrayList<Double> S = new ArrayList<>();
//		ArrayList<Double> E = new ArrayList<>();
//		ArrayList<Double> I = new ArrayList<>();
//		ArrayList<Double> R = new ArrayList<>();
//		
//		final int R0 = 0;
//		final int S0 = (int) (N - I0 - R0);
//		
//		S.add((double)S0);
//		E.add((double)I0);
//		I.add(0.0);
//		R.add(0.0);
//		
//
//	    for (int day = 0; day < 15; day++) {
//
//		    double dS = (beta * S.get(day) * I.get(day)) / N;
//		    double newS = S.get(day) - (dS);
//		    double newE = E.get(day) + (dS - (a * E.get(day)));
//		    double newI = I.get(day) + ((a * E.get(day)) - (gamma * I.get(day)));
//		    double newR = R.get(day) + (gamma * I.get(day));
//		    
//		    S.add(newS);
//	        E.add(newE);
//	        I.add(newI);
//	        R.add(newR);
//	    }
//		
//		return I;
//	}
	
	
	/**
	 * draws graph by figuring out all points of where the graph needs to be plotted.
	 * 
	 * @param x x coordinate of the left up corner of where the graph is drawn
	 * @param y y coordinate of the left up corner of where the graph is drawn
	 * @param width width of the rectangle the graph is in
	 * @param height height of the rectangle the graph is in
	 * @throws ParseException 
	 */
	private void drawGraph(PApplet p, double x, double y, double width, double height){
		
		cases = stat.getDoubleData(name, 3, "data/cases.csv");
		deaths = stat.getDoubleData(name, 4, "data/cases.csv");
		covidDates = stat.getStringData(name, 0, "data/cases.csv");
		vaccineDates = stat.getStringData(name, 0, "data/vaccineNumber.csv");
		vaccineList = stat.getDoubleData(name, 7, "data/vaccineNumber.csv");
		
		

		//figure out the biggest number of the arraylist to scale y
		double b = 0; //write this as a text on top of the yaxis
		
		for(int i = 0; i < cases.size(); i++) {
			if(b < cases.get(i)) {
				b = cases.get(i);
			}else if(i < vaccineList.size()) {
				if(i < vaccineList.get(i));
//					b = vaccineList.get(i);
			}
		}
		
		DateTimeFormatter DATEFORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		LocalDate firstDate = LocalDate.parse(covidDates.get(0), DATEFORMATTER);
		LocalDate covidLastDate = LocalDate.parse(covidDates.get(covidDates.size()-1), DATEFORMATTER);
		LocalDate vaccineLastDate = LocalDate.parse(vaccineDates.get(vaccineDates.size()-1), DATEFORMATTER);
		LocalDate lastDate = covidLastDate;
		
		if(covidLastDate.compareTo(vaccineLastDate) < 0) {
			lastDate = vaccineLastDate;
			dates = vaccineDates;
		}else {
			lastDate = covidLastDate;
			dates = covidDates;
		}
		
		LocalDate copyOfLastDate = lastDate;
		 
		//draw the frame of the graph
		p.line((float)x + 10, (float)y, (float)x + 10, (float)(y + height - 10));
		p.line((float)x+10, (float)(y + height - 10), (float)(x + width - 10), (float)(y + height - 10));
		
		p.fill(0);
		p.textSize(10);
		p.text((int)b + "", (float)x - 30, (float)y);

		for(int i = 1; i < 7; i++) {
			p.text((int)(b * (7 - i) / 7)  + "", (float)x - 30, (float)(y + ((height - 10) * i / 7)));
		}
		
		p.text(firstDate.toString(), (float)(x), (float)(y + height - 5));
		p.text(lastDate.toString(), (float)(x - 10 + width), (float)(y + height - 5));

		p.textSize(12);
		p.text("# of covid cases in " + name, (float)(x + (width - 10)/2), (float)((y - 10)));
		p.text("date", (float)(x + ( width - 10)/2), (float)((y + height)));
		p.text("population", (float)((x - 70)), (float)(y + (height - 10)/2));
		p.text("number of cases", (float)(x + 10), (float)((y + height + 10)));
		
		p.fill(255, 0, 0);
		p.text("number of deaths", (float)(x + 10), (float)((y + height + 20)));

		//find diff in last and first date
		
		Duration duration = Duration.between(firstDate.atStartOfDay(), lastDate.atStartOfDay());
		
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
		ArrayList<Point> points2 = new ArrayList<Point>();
		ArrayList<Point> points3 = new ArrayList<Point>();
		
		while(!firstDate.equals(lastDate)) {
				
				if(covidDates.indexOf(firstDate.toString()) != -1) {
					double px = xAxis + PIXEL_PER_X * covidDates.indexOf(firstDate.toString());
					Point po = new Point();
					double py = yAxis - PIXEL_PER_Y * deaths.get(covidDates.indexOf(firstDate.toString())) ;
					po.setLocation(px, py);
					points2.add(po);
				}
				
				if(covidDates.indexOf(firstDate.toString()) != -1) {
					double px = xAxis + PIXEL_PER_X * covidDates.indexOf(firstDate.toString());
					Point po = new Point();
					double py = yAxis - PIXEL_PER_Y * cases.get(covidDates.indexOf(firstDate.toString())) ;
					po.setLocation(px, py);
					points.add(po);
				}
				
				if(vaccineDates.indexOf(firstDate.toString()) != -1) {
					double px = xAxis + PIXEL_PER_X * dates.indexOf(firstDate.toString());
					Point po = new Point();
					double py = yAxis - PIXEL_PER_Y * vaccineList.get(vaccineDates.indexOf(firstDate.toString())) ;
					po.setLocation(px, py);
					points3.add(po);
				}
				
			firstDate = firstDate.plusDays(1);
		}
				
		for(int i = 0; i < dates.size()-1; i++) {
//			System.out.println(points.get(i).getX() + ", " + points.get(i).getY() + ", " + points.get(i+1).getX() + ", " + points.get(i+1).getY());
	
			if(i < points.size()-1) {
				
				p.stroke(255, 0, 0);
				p.line((float)points2.get(i).getX(), (float)points2.get(i).getY(), (float)points2.get(i+1).getX(), (float)points2.get(i+1).getY());
				
				p.stroke(0, 0, 0);
				p.line((float)points.get(i).getX(), (float)points.get(i).getY(), (float)points.get(i+1).getX(), (float)points.get(i+1).getY());
				
			}
			
			if(i < points3.size()-1) {
				p.stroke(0, 255, 0);
				p.line((float)points3.get(i).getX(), (float)points3.get(i).getY(), (float)points3.get(i+1).getX(), (float)points3.get(i+1).getY());
			}
			
		}
		
		p.stroke(0,0,0);
		p.noFill();

	}
	
	
	public void draw (PApplet surface) {
//		vaccine = stat.getLatestVaccineInfo(name);
		if (surface.height<surface.width) {
			graphWidth = (surface.height/2);
			graphHeight = (surface.height/2);
		} else {
			graphWidth = (surface.width/2);
			graphHeight = (surface.width/2);
		}

		
		drawGraph(surface, 7*(surface.width/11), surface.height/20, graphWidth, graphHeight);
		writeInfo(surface, (surface.width/20) , surface.height* 11 /20, (float)surface.height/45, (float)surface.height/60, (float)surface.height/50);
		
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
	private void writeInfo(PApplet p, double x, double y, float titleSize, float writingSize, float leading) {
		
		p.fill(0);
//		p.stroke(0);
		p.textSize(titleSize);
		p.textAlign(p.LEFT);
		
		if(vaccine.size() > 0){
			infoAvailable = true;
			p.text("updated as of " + dates.get(dates.size()-1), (float)x, (float)(y));


		} else {
			p.text("there is no numerical data available for " + name, (float)x, (float)(y + 30));
		}
		
	}
	
	
	//get methods
		
	
	public boolean getInfoAvailable() {
		return infoAvailable;
	}
	
	public ArrayList<String> getVaccineInfo(){
		return vaccine;
	}


}
