/**
 * @author nodoka
 */
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class fetches data and stores them in arraylists
 * @author nodoka shibasaki
 *
 */
public class Stats {
	
	private double mortalityRate = 0.0;

	/**
	 * this method uses java.io api to copy the data on cvc approved vaccine dataset to the csv file in the data folder.
	 * @throws IOException
	 */
	public final void downloadVaccineData() throws IOException{
		InputStream inputStream = new URL("https://raw.githubusercontent.com/owid/covid-19-data/master/public/data/vaccinations/us_state_vaccinations.csv").openStream();
		Files.copy(inputStream, Paths.get("data/vaccinationNumber.csv"), StandardCopyOption.REPLACE_EXISTING);
	}
	
	/**
	 * this method uses java.io api to copy the data on cvc approved covid cases dataset to the csv file in the data folder.
	 * @throws IOException
	 */
	public final void downloadCasesData() throws IOException{
		InputStream inputStream = new URL("https://raw.githubusercontent.com/nytimes/covid-19-data/master/us-states.csv").openStream();
		Files.copy(inputStream, Paths.get("data/cases.csv"), StandardCopyOption.REPLACE_EXISTING);
	}
	
	/**
	 * this method uses java.io api to copy the data on cvc approved covid cases dataset to the csv file in the data folder.
	 * @throws IOException
	 */
	public final void downloadCountryVaccinesData() throws IOException{
		InputStream inputStream = new URL("https://raw.githubusercontent.com/owid/covid-19-data/master/public/data/vaccinations/country_data/United%20States.csv").openStream();
		Files.copy(inputStream, Paths.get("data/countryVaccinationNumber.csv"), StandardCopyOption.REPLACE_EXISTING);
	}
	
	/**
	 * this method uses java.io api to copy the data on cvc approved covid cases dataset to the csv file in the data folder.
	 * @throws IOException
	 */
	public final void downloadCountryCases() throws IOException{
		InputStream inputStream = new URL("https://raw.githubusercontent.com/owid/covid-19-data/master/public/data/owid-covid-data.csv").openStream();
		Files.copy(inputStream, Paths.get("data/countryCaseNumber.csv"), StandardCopyOption.REPLACE_EXISTING);
	}
	
	/**
	 * reads the covid info from the file and gets the recent data.
	 * @param country name of the country
	 * @return recent data of cases and deaths info for that country
	 */
	public ArrayList<String> getCountryCases(String country) {

		List<String> lines = new ArrayList<String>();
		
		try {
			lines = Files.readAllLines(Paths.get("data/countryCaseNumber.csv"), Charset.defaultCharset());
		} catch (IOException e) {
            System.out.format("I/O error: %s%n", e);
		}
		
		String latest = lines.get(lines.size()-1);
		
		List<String> list = Arrays.asList(latest.split("\\s*,\\s*"));
		ArrayList<String> values = new ArrayList<String>(list);
		
		return values;
		
	}
	
	/**
	 * reads the vaccination info from the file and gets the recent data.
	 * @param state name of the state
	 * @return recent data of vaccination info for that state
	 */
	public ArrayList<String> getLatestVaccineInfo(String state) {
				
		try {
			
			List<String> lines = Files.readAllLines(Paths.get("data/vaccineNumber.csv"), Charset.defaultCharset());
			String latest = "";
			
			for(int i = 0; i < lines.size(); i++) {
				String[] val = lines.get(i).split(",");
				if(val[1].equalsIgnoreCase(state)) {
					latest += lines.get(i) + ",";
				}
			}
			
			List<String> values = new ArrayList<>(Arrays.asList(latest.split(",")));
			System.out.println(values.toString());
						
			return (ArrayList<String>) values;
			
		} catch (IOException e) {
            System.out.format("I/O error: %s%n", e);
		}
		
		return null;
		
	}
	
	
	/**
	 * returns data on number of cases of covid, death of covid cased on state name
	 * 
	 * @param state name of the state of desire
	 * @param index counted from 0 from right on cases.csv file
	 * @return arraylist of string with all desired data from the state inputted
	 */
	public ArrayList<String> getStringData(String state, int index, String filename) {
		
		
		try {
			
			List<String> lines = Files.readAllLines(Paths.get(filename), Charset.defaultCharset());
			ArrayList<String> data = new ArrayList<String >();
			
			for(int i = 0; i < lines.size(); i++) {
				String[] values = lines.get(i).split(",");
				if(values[1].equalsIgnoreCase(state)) {
					data.add(values[index]);
				}
			}
			
			return data;
			
		} catch (IOException e) {
            System.out.format("I/O error: %s%n", e);
		}
		
		return null;
		
	}
	
	/**
	 * returns data on number of cases of covid, death of covid cased on state name
	 * 
	 * @param state name of the state of desire
	 * @param index counted from 0 from right on cases.csv file
	 * @return arraylist of string with all desired data from the state inputted
	 */
	public ArrayList<Double> getDoubleData(String state, int index, String filename) {

		try {
			
			List<String> lines = Files.readAllLines(Paths.get(filename), Charset.defaultCharset());
			ArrayList<Double> data = new ArrayList<Double>();
			
			for(int i = 0; i < lines.size(); i++) {
				String[] values = lines.get(i).split(",");
				if(values[1].equalsIgnoreCase(state)) {
					if(!values[index].equals("")) {
						double d = Double.parseDouble(values[index]);
						data.add(d);
					}else {
						data.add(data.get(data.size()-1));
					}
				}
			}
			
			return data;
			
		} catch (IOException e) {
            System.out.format("I/O error: %s%n", e);
		}
		
		return null;
		
	}
	
	/**
	 * get vaccination data on united states as a whole.
	 * 
	 * @return string arraylist of united states' most recent dataset
	 */
	public ArrayList<String> getCountryData(){
		
		try {
			
			List<String> lines = Files.readAllLines(Paths.get("data/countryVaccinationNumber.csv"), Charset.defaultCharset());
			String latest = "";
			
			for(int i = 0; i < lines.size(); i++) {
				latest = lines.get(i);
			}
			
			List<String> values = new ArrayList<>(Arrays.asList(latest.split(",")));
						
			return (ArrayList<String>) values;
			
		} catch (IOException e) {
            System.out.format("I/O error: %s%n", e);
		}
		
		return null;
	}
	
	/**
	 * parse the inputted string arraylist into double arraylist
	 * 
	 * @param str string arraylist 
	 * @return parsed to double version of str arraylist
	 */
	public ArrayList<Double> parseDouble(ArrayList<String> str){
		
		ArrayList<Double> parsed = new ArrayList<Double>();
		
		for(int i = 0; i < str.size(); i++) {
			double d = Double.parseDouble(str.get(i));
			parsed.add(d);
		}
		
		return parsed;
		
	}

}
