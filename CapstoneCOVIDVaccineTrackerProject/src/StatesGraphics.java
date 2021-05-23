
import java.awt.Point;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
	
	private ArrayList<Double> cases, deaths, vaccineList, prediction;
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
		
		boolean graphingVaccine = true;
		
		if(vaccineList.size() <= 0) {
			graphingVaccine = false;
		}
		
		double b = cases.get(0);
		
		for(int i = 1; i < cases.size(); i++) {
			if(b < cases.get(i)) {
				b = cases.get(i);
			}
			
			if(i < vaccineList.size()) {
				if(b < vaccineList.get(i)) {
					b = vaccineList.get(i);
				}
			}

		}
		
		DateTimeFormatter DATEFORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		LocalDate firstDate = LocalDate.parse(covidDates.get(0), DATEFORMATTER);
		LocalDate lastDate = LocalDate.parse(covidDates.get(covidDates.size()-1), DATEFORMATTER);

		p.line((float)x + 10, (float)y, (float)x + 10, (float)(y + height - 10));
		p.line((float)x+10, (float)(y + height - 10), (float)(x + width - 10), (float)(y + height - 10));
		
		p.fill(0);
		p.textSize(10);
		p.text((int)b + "", (float)x - 30, (float)y);

		for(int i = 1; i < 7; i++) {
			p.text((int)(b * (7 - i) / 7)  + "", (float)x - 30, (float)(y + ((height - 10) * i / 7)));
		}
		
		p.text(covidDates.get(0), (float)(x), (float)(y + height - 5));
		p.text(covidDates.get(covidDates.size()-1), (float)(x - 10 + width), (float)(y + height - 5));

		p.textSize(12);
		p.text("# of covid cases in " + name, (float)(x + (width - 10)/2), (float)((y - 10)));
		p.text("date", (float)(x + ( width - 10)/2), (float)((y + height)));
		p.text("population", (float)((x - 70)), (float)(y + (height - 10)/2));
		p.fill(0, 0, 255);
		p.text("population of covid-19 cases in " + name, (float)(x + 10), (float)((y + height + 10)));
		 
		p.fill(255, 0, 0);
		p.text("population of covid-19 deaths in " + name, (float)(x + 10), (float)((y + height + 25)));
		
		p.fill(0, 255, 0);
		p.text("population of fully vaccinated in " + name, (float)(x + 10), (float)((y + height + 40)));

		double PIXEL_PER_X = (width - 10) / (covidDates.size());
		
		if(graphingVaccine) {
			int diff = 0;
			
			for(int i = 0; i < covidDates.size(); i++) {
				if(covidDates.get(i).equals(vaccineDates.get(0))) {
					diff = i;
				}
			}
			
			PIXEL_PER_X = (width - 10) / (vaccineDates.size() + diff);

		}
		final double PIXEL_PER_Y = (height - 10) / b;
		
		//coordinate of the base of the lines
		double xAxis = x + 10;
		double yAxis = y + height - 10;
		ArrayList<Point> points = new ArrayList<Point>();
		ArrayList<Point> points2 = new ArrayList<Point>();
		ArrayList<Point> points3 = new ArrayList<Point>();
		
		double initial = 0.0;
		
		if(graphingVaccine) {
			initial = PIXEL_PER_X * covidDates.indexOf(vaccineDates.get(0));
		}

		while(!firstDate.equals(lastDate)) {
				
				if(covidDates.indexOf(firstDate.toString()) != -1) {
					double px = xAxis + PIXEL_PER_X * covidDates.indexOf(firstDate.toString());
					double py = yAxis - PIXEL_PER_Y * cases.get(covidDates.indexOf(firstDate.toString())) ;
					Point po = new Point();
					po.setLocation(px, py);
					points.add(po);
				}
				
				if(covidDates.indexOf(firstDate.toString()) != -1) {
					double py = yAxis - PIXEL_PER_Y * deaths.get(covidDates.indexOf(firstDate.toString())) ;
					double px = xAxis + PIXEL_PER_X * covidDates.indexOf(firstDate.toString());
					Point po = new Point();
					po.setLocation(px, py);
					points2.add(po);
				}
				
				if(graphingVaccine) {
					if(vaccineDates.indexOf(firstDate.toString()) != -1) {
						double py = yAxis - PIXEL_PER_Y * vaccineList.get(vaccineDates.indexOf(firstDate.toString())) ;
						double px = xAxis + initial + PIXEL_PER_X * vaccineDates.indexOf(firstDate.toString());
						Point po = new Point();
						po.setLocation(px, py);
						points3.add(po);
					}
				}
				
				
			firstDate = firstDate.plusDays(1);
		}
				
		for(int i = 0; i < points.size()-1; i++) {	
			
			p.stroke(255, 0, 0);
			p.line((float)points2.get(i).getX(), (float)points2.get(i).getY(), (float)points2.get(i+1).getX(), (float)points2.get(i+1).getY());
			
			p.stroke(0, 0, 255);
			p.line((float)points.get(i).getX(), (float)points.get(i).getY(), (float)points.get(i+1).getX(), (float)points.get(i+1).getY());
			
			if(i < points3.size()-2) {
				p.stroke(0, 255, 0);
				p.line((float)points3.get(i).getX(), (float)points3.get(i).getY(), (float)points3.get(i+1).getX(), (float)points3.get(i+1).getY());
			}
						
		}
		
		p.stroke(0,0,0);
		p.noFill();

	}
	
	
	public void draw (PApplet surface) {
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
		p.textSize(titleSize);
		p.textAlign(p.LEFT);
		
		if(vaccine.size() > 13){
			infoAvailable = true;
			p.text("updated as of " + covidDates.get(covidDates.size()-1), (float)x + 50, (float)(y + (p.height * 3 / 4)));


		} else {
			infoAvailable = false;
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
