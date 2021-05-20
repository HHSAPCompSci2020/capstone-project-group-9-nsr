
import java.awt.Point;
import java.io.IOException;
import java.text.ParseException;
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
public class StatesGraphics extends PApplet{
	
	private Stats stat = new Stats();	
	private String name;
	
	private double graphWidth, graphHeight;
	
	private ArrayList<Double> cases, deaths;
	private ArrayList<String> dates;
	
	ArrayList<String> list = stat.getVaccinationInfo(name);
	
	private float buttonDistance;
	private int buttonWidth, buttonHeight;
	private int buttonX, buttonY;
	private boolean clickVaxAvailable, clickVaxDistributed, clickDistPercent, clickPeopleVaxed, clickTotalVaxPercent, clickFullyVaxed, clickFullyVaxedPercent;
	private String vaxAvailable, vaxDist, distPercent, peopleVaxed, vaxedPercent, peopleFullyVaxed, fullyVaxedPercent;
	private String vaxAvailableString, vaxDistString, distPercentString, peopleVaxedString, vaxedPercentString, peopleFullyVaxedString, fullyVaxedPercentString;
	private String vaxAvailableDisplay, vaxDistDisplay, distPercentDisplay, peopleVaxedDisplay, vaxedPercentDisplay, peopleFullyVaxedDisplay, fullyVaxedPercentDisplay;
	
	/**constructor
	 * if no parameter is inputted, the name is set to null
	 */
	public StatesGraphics() {
		name = null;
		buttonX = 0;
		buttonY = 0;
		clickVaxAvailable = false;
		clickVaxDistributed = false;
		clickDistPercent = false;
		clickPeopleVaxed = false;
		clickTotalVaxPercent = false;
		clickFullyVaxed = false;
		clickFullyVaxedPercent = false;
		vaxAvailable = " " ;
		vaxDist = " " ;
		distPercent = " " ;
		peopleVaxed = " " ;
		vaxedPercent = " " ;
		peopleFullyVaxed = " " ;
		fullyVaxedPercent = " " ;
		vaxAvailableString = "total vaccinations available";
		vaxDistString = "total vaccinations distributed";
		distPercentString = "total distribution percentage";
		peopleVaxedString = "people vaccinated";
		vaxedPercentString = "total vaccinations percentage";
		peopleFullyVaxedString = "people fully vaccinated";
		fullyVaxedPercentString = "fully vaccinated percentage";
		vaxAvailableDisplay = " " ;
		vaxDistDisplay = " " ;
		distPercentDisplay = " " ;
		peopleVaxedDisplay = " " ;
		vaxedPercentDisplay = " " ;
		peopleFullyVaxedDisplay = " " ;
		fullyVaxedPercentDisplay = " " ;
		setDisplayInfo();
	}
	
	/**constructor
	 * sets the name to the parameter String state
	 * @param String state
	 */
	public StatesGraphics(String state) {
		name = state;
		buttonX = 0;
		buttonY = 0;
		clickVaxAvailable = false;
		clickVaxDistributed = false;
		clickDistPercent = false;
		clickPeopleVaxed = false;
		clickTotalVaxPercent = false;
		clickFullyVaxed = false;
		clickFullyVaxedPercent = false;
		vaxAvailable = " " ;
		vaxDist = " " ;
		distPercent = " " ;
		peopleVaxed = " " ;
		vaxedPercent = " " ;
		peopleFullyVaxed = " " ;
		fullyVaxedPercent = " " ;
		vaxAvailableString = "total vaccinations available";
		vaxDistString = "total vaccinations distributed";
		distPercentString = "total distribution percentage";
		peopleVaxedString = "people vaccinated";
		vaxedPercentString = "total vaccinations percentage";
		peopleFullyVaxedString = "people fully vaccinated";
		fullyVaxedPercentString = "fully vaccinated percentage";
		vaxAvailableDisplay = " " ;
		vaxDistDisplay = " " ;
		distPercentDisplay = " " ;
		peopleVaxedDisplay = " " ;
		vaxedPercentDisplay = " " ;
		peopleFullyVaxedDisplay = " " ;
		fullyVaxedPercentDisplay = " " ;
//		setDisplayInfo();
	}
	
	

	/**returns the name of the state
	 * @return name String
	 */
	public String getName() {
		return name;
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
		
		cases = stat.getDoubleCovidData(name, 3);
		deaths = stat.getDoubleCovidData(name, 4);
		dates = stat.getStringCovidData(name, 0);
//		ArrayList<Double> pre = predictData();
		
//		ArrayList<Double> cases = new ArrayList<Double>();
//		
//		//528784.0
//		cases.add(12.0);
//		cases.add(947.0);
//		cases.add(38962.0);
//		cases.add(70358.0);
//		cases.add(101334.0);
//		cases.add(530988.0);
		
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
		
		DateTimeFormatter DATEFORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		LocalDate firstDate = LocalDate.parse(dates.get(0), DATEFORMATTER);
		LocalDate lastDate = LocalDate.parse(dates.get(dates.size()-1), DATEFORMATTER);
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
		
		p.text(dates.get(0), (float)(x), (float)(y + height - 5));
		p.text(dates.get(dates.size()-1), (float)(x - 10 + width), (float)(y + height - 5));

		p.textSize(12);
		p.text("# of covid cases in " + name, (float)(x + (width - 10)/2), (float)((y - 10)));
		p.text("date", (float)(x + ( width - 10)/2), (float)((y + height)));
		p.text("population", (float)((x - 70)), (float)(y + (height - 10)/2));
		p.text("number of cases", (float)(x + 10), (float)((y + height + 10)));
		
		p.fill(255, 0, 0);
		p.text("number of deaths", (float)(x + 10), (float)((y + height + 20)));

		//find diff in last and first date
		Period period = Period.between(firstDate, lastDate);
		int diff = Math.abs(period.getDays());
		
		//number in each pixel
		final double PIXEL_PER_X = (width - 10) / (dates.size());
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
		
		while(!firstDate.equals(lastDate)) {
			if(dates.indexOf(firstDate.toString()) != -1) {
				
				if(dates.indexOf(firstDate.toString()) < cases.size()) {
					double px = xAxis + PIXEL_PER_X * dates.indexOf(firstDate.toString());
//					System.out.println(xAxis + " + " + PIXEL_PER_X + " * " + dates.indexOf(firstDate.toString()) + " = " + px);
					double py = yAxis - PIXEL_PER_Y * cases.get(dates.indexOf(firstDate.toString())) ;
//					System.out.println(yAxis + " + " + PIXEL_PER_Y + " * " + cases.get(dates.indexOf(firstDate.toString())) + " = " + py);
					Point po = new Point();
					po.setLocation(px, py);
					points.add(po);
//					System.out.println(px + ", " + py);
				}
				
				if(dates.indexOf(firstDate.toString()) < deaths.size()) {
					double py = yAxis - PIXEL_PER_Y * deaths.get(dates.indexOf(firstDate.toString())) ;
					double px = xAxis + PIXEL_PER_X * dates.indexOf(firstDate.toString());

//					System.out.println(yAxis + " + " + PIXEL_PER_Y + " * " + cases.get(dates.indexOf(firstDate.toString())) + " = " + py);
					Point po = new Point();
					po.setLocation(px, py);
					points2.add(po);
				}
			}
			firstDate = firstDate.plusDays(1);
		}
		
//		double rate = cases.get(cases.size()-2) / cases.get(cases.size()-1);

//		
//		for(int i = 0; i < pre.size(); i++) {
////			if(pre.get(i) > 0) {
//				double px = points.get(points.size()-1).getX() + PIXEL_PER_X;
//				//double py = yAxis - PIXEL_PER_Y * pre.get(i);
//				double py = points.get(points.size()-1).getY() + points.get(points.size()-1).getY() * rate;
//				Point po = new Point();
//				po.setLocation(px,py);
//				points.add(po);
////			}
//		}
		
		double px = xAxis + PIXEL_PER_X * dates.size()-1;
		double py = yAxis - PIXEL_PER_Y * cases.get(cases.size()-1) ;
		Point po = new Point();
		po.setLocation(px, py);
		points.add(po);
		
		py = yAxis - PIXEL_PER_Y * deaths.get(deaths.size()-1) ;
		po.setLocation(px, py);
		points2.add(po);
		
		
	
//		p.line(100, 100,200, 100);
		
		for(int i = 0; i < points.size() - 2; i++) {
//			System.out.println(points.get(i).getX() + ", " + points.get(i).getY() + ", " + points.get(i+1).getX() + ", " + points.get(i+1).getY());
	
			p.stroke(0, 0, 0);
			p.line((float)points.get(i).getX(), (float)points.get(i).getY(), (float)points.get(i+1).getX(), (float)points.get(i+1).getY());
			
			p.stroke(255, 0, 0);
			p.line((float)points2.get(i).getX(), (float)points2.get(i).getY(), (float)points2.get(i+1).getX(), (float)points2.get(i+1).getY());

		}		

	}
	
	
	public void draw (PApplet surface) {
		list = stat.getVaccinationInfo(name);
		buttonDistance = surface.height/20;
		buttonWidth = surface.width/3;
		buttonHeight = surface.height/25;
		if (surface.height<surface.width) {
			graphWidth = (surface.height/2);
			graphHeight = (surface.height/2);
		} else {
			graphWidth = (surface.width/2);
			graphHeight = (surface.width/2);
		}
		drawGraph(surface, 7*(surface.width/11), surface.height/20, graphWidth, graphHeight);
		writeInfo(surface, (surface.width/20) , surface.height* 11 /20, (float)surface.height/45, (float)surface.height/60, (float)surface.height/50);
		if (clickVaxAvailable) {
			vaxAvailableDisplay = vaxAvailable;
		} else {
			vaxAvailableDisplay = vaxAvailableString;
		}
		if (clickVaxDistributed) {
			vaxDistDisplay = vaxDist;
		} else {
			vaxDistDisplay = vaxDistString;
		}
		if (clickDistPercent) {
			distPercentDisplay = distPercent;
		} else {
			distPercentDisplay = distPercentString;
		}
		if (clickPeopleVaxed) {
			peopleVaxedDisplay = peopleVaxed;
		} else {
			peopleVaxedDisplay = peopleVaxedString;
		}
		if (clickTotalVaxPercent) {
			vaxedPercentDisplay = vaxedPercent;
		} else {
			vaxedPercentDisplay = vaxedPercentString;
		}
		if (clickFullyVaxed) {
			peopleFullyVaxedDisplay = peopleFullyVaxed;
		} else {
			peopleFullyVaxedDisplay = peopleFullyVaxedString;
		}
		if (clickFullyVaxedPercent) {
			fullyVaxedPercentDisplay = fullyVaxedPercent;
		} else {
			fullyVaxedPercentDisplay = fullyVaxedPercent;
		}
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
		buttonX = (int)x;
		buttonY = (int)y;
		
		p.fill(0);
//		p.stroke(0);
		p.textSize(titleSize);
		p.textAlign(LEFT);
		
		if(list.size() > 0){
			p.text("updated as of " + dates.get(dates.size()-1), (float)x, (float)(y));
			
			p.textSize(writingSize);
			p.textLeading(leading);
			
			drawButton(p, buttonX, (int)(buttonY+buttonDistance), buttonWidth, buttonHeight, vaxAvailableDisplay, 218);
			drawButton(p, buttonX, (int)(buttonY+(2*buttonDistance)), buttonWidth, buttonHeight, vaxDistDisplay, 218);
			drawButton(p, buttonX, (int)(buttonY+(3*buttonDistance)), buttonWidth, buttonHeight, distPercentDisplay, 218);
			drawButton(p, buttonX, (int)(buttonY+(4*buttonDistance)), buttonWidth, buttonHeight, peopleVaxedDisplay, 218);
			drawButton(p, buttonX, (int)(buttonY+(5*buttonDistance)), buttonWidth, buttonHeight, vaxedPercentDisplay, 218);
			drawButton(p, buttonX, (int)(buttonY+(6*buttonDistance)), buttonWidth, buttonHeight, peopleFullyVaxedDisplay, 218);
			drawButton(p, buttonX, (int)(buttonY+(7*buttonDistance)), buttonWidth, buttonHeight, fullyVaxedPercentDisplay, 218);

//			p.text("total vaccinations available : " + list.get(2) + "\n" +
//			
//				   "total vaccinations distributed : " + list.get(3) + "\n" +
//				   "total distribution percentage : " + list.get(9) + "\n" +
//				   "people vaccinated : " + list.get(4) + "\n" +
//				   "total vaccinations percentage : " + list.get(6) + "% of the state population" + "\n" +
//				   "people fully vaccinated : " + list.get(7) + "\n" +
//				   "fully vaccinated percentage : " + list.get(5) + "% of the state population", (float)x+500, (float)(y + 60));
		} else {
			p.text("there is no numerical data available for " + name, (float)x, (float)(y + 30));
		}
		
	}
	
	public void drawButton(PApplet surface, int x, int y, int w, int h, String text, int fillColor) {
		surface.fill(fillColor);
		surface.rect(x, y, w, h, 10);
    	surface.textAlign(surface.CENTER, surface.CENTER);
    	surface.fill(0);
    	surface.text(text, x + w/2, y + h/2);
    	surface.noFill();
	}
	
	public boolean overButton(int x, int y, int w, int h) {
	  if (mouseX > x && mouseX < (x + w) && mouseY > y && mouseY < (y + h)) {
	    return true;
	  }
	  return false;
	}
	
	private void setDisplayInfo() {
		vaxAvailable = list.get(2);
//		for (int i = vaxAvailableTemp.length()-1; i >= 0; i--) {
//			vaxAvailable = vaxAvailableTemp.substring(i, i+1) + vaxAvailable;
//			if (i % 3 == 0 && i!=0) {
//				vaxAvailable = "," + vaxAvailable;
//			} 
//		}
		
		vaxDist = list.get(3);
//		for (int i = vaxDistTemp.length()-1; i >= 0; i--) {
//			vaxDist = vaxDistTemp.substring(i, i+1) + vaxDist;
//			if (i % 3 == 0 && i!=0) {
//				vaxDist = "," + vaxDist;
//			} 
//		}
		
		distPercent = list.get(9);
//		for (int i = distPercentTemp.length()-1; i >= 0; i--) {
//			distPercent = distPercentTemp.substring(i, i+1) + distPercent;
//			if (i % 3 == 0 && i!=0) {
//				distPercent = "," + distPercent;
//			} 
//		}
		
		peopleVaxed = list.get(4);
//		for (int i = peopleVaxedTemp.length()-1; i >= 0; i--) {
//			peopleVaxed = peopleVaxedTemp.substring(i, i+1) + peopleVaxed;
//			if (i % 3 == 0 && i!=0) {
//				peopleVaxed = "," + peopleVaxed;
//			} 
//		}
		
		vaxedPercent = list.get(6);
//		for (int i = vaxedPercentTemp.length()-1; i >= 0; i--) {
//			vaxedPercent = vaxedPercentTemp.substring(i, i+1) + vaxedPercent;
//			if (i % 3 == 0 && i!=0) {
//				vaxedPercent = "," + vaxedPercent;
//			} 
//		}
		vaxedPercent += "% of the state population";
		
		peopleFullyVaxed = list.get(7);
//		for (int i = peopleFullyVaxedTemp.length()-1; i >= 0; i--) {
//			peopleFullyVaxed = peopleFullyVaxedTemp.substring(i, i+1) + peopleFullyVaxed;
//			if (i % 3 == 0 && i!=0) {
//				peopleFullyVaxed = "," + peopleFullyVaxed;
//			} 
//		}
		
		fullyVaxedPercent = list.get(5);
//		for (int i = fullyVaxedPercentTemp.length()-1; i >= 0; i--) {
//			fullyVaxedPercent = fullyVaxedPercentTemp.substring(i, i+1) + fullyVaxedPercent;
//			if (i % 3 == 0 && i!=0) {
//				fullyVaxedPercent = "," + fullyVaxedPercent;
//			} 
//		}
		fullyVaxedPercent += "% of the state population";
	}
	
	//get methods
	public int getButtonX() {
		return buttonX;
	}
	
	public int getButtonY() {
		return buttonY;
	}
	
	public int getButtonWidth() {
		return buttonWidth;
	}
	
	public int getButtonHeight() {
		return buttonHeight;
	}
	
	public float getButtonDistance() {
		return buttonDistance;
	}
		
	public boolean getClickVaxAvailable() {
		return clickVaxAvailable;
	}
	
	public boolean getClickVaxDistributed() {
		return clickVaxDistributed;
	}
	
	public boolean getClickDistPercent() {
		return clickDistPercent;
	}
	
	public boolean getClickPeopleVaxed() {
		return clickPeopleVaxed;
	}
	
	public boolean getClickTotalVaxPercent() {
		return clickTotalVaxPercent;
	}
	
	public boolean getClickFullyVaxed() {
		return clickFullyVaxed;
	}
	
	public boolean getClickFullyVaxedPercent() {
		return clickFullyVaxedPercent;
	}
	
	//set methods	
	public void setClickVaxAvailable(boolean state) {
		clickVaxAvailable = state;
	}
	
	public void setClickVaxDistributed(boolean state) {
		clickVaxDistributed = state;
	}
	
	public void setClickDistPercent(boolean state) {
		clickDistPercent = state;
	}
	
	public void setClickPeopleVaxed(boolean state) {
		clickPeopleVaxed = state;
	}
	
	public void setClickTotalVaxPercent(boolean state) {
		clickTotalVaxPercent = state;
	}
	
	public void setClickFullyVaxed(boolean state) {
		clickFullyVaxed = state;
	}
	
	public void setClickFullyVaxedPercent(boolean state) {
		clickFullyVaxedPercent = state;
	}


}
