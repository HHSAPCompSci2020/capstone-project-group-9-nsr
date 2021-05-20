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
		
//		try {
//			downloadCountryCases();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		
//		ArrayList<String> data = new ArrayList<String>();
//		
//		String fileName = "data/countryCaseNumber.csv";
//		String line = "";
//		
//		try {
//			BufferedReader br = new BufferedReader(new FileReader(fileName));
//			while((line = br.readLine()) != null) {
//				List<String> list = Arrays.asList(line.split("\\s*,\\s*"));
//				ArrayList<String> values = new ArrayList<String>(list);
//					if(values.get(2).equalsIgnoreCase(country)) {
//						data = values;
//					}
//			}
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}

		List<String> lines = new ArrayList<String>();
		
		try {
			lines = Files.readAllLines(Paths.get("countryCaseNumber.csv"), Charset.defaultCharset());
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
	public ArrayList<String> getVaccinationInfo(String state) {
		
//		try {
//			downloadVaccineData();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		
//		ArrayList<String> data = new ArrayList<String>();
//		
//		String fileName = "data/vaccineNumber.csv";
//		String line = "";
//		
//		try {
//			BufferedReader br = new BufferedReader(new FileReader(fileName));
//			while((line = br.readLine()) != null) {
//				List<String> list = Arrays.asList(line.split("\\s*,\\s*"));
//				ArrayList<String> values = new ArrayList<String>(list);
//					if(values.get(1).equalsIgnoreCase(state)) {
//						data = values;
//					}
//			}
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//		
//		return data;
				
		try {
			
			List<String> lines = Files.readAllLines(Paths.get("data/vaccineNumber.csv"), Charset.defaultCharset());
			String latest = "";
			
			for(int i = 0; i < lines.size(); i++) {
				String[] values = lines.get(i).split(",");
				if(values[1].equalsIgnoreCase(state)) {
					latest = lines.get(i);
				}
			}
			
			List<String> values = new ArrayList<>(Arrays.asList(latest.split(",")));
						
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
	public ArrayList<String> getStringCovidData(String state, int index) {
		
		
//		ArrayList<String> data = new ArrayList<String>();
//		
//		String fileName = "data/cases.csv";
//		String line = "";
//		
//		int totalDeath = 0;
//		int totalCases = 0;
//		
//		try {
//			BufferedReader br = new BufferedReader(new FileReader(fileName));
//			int n = 0;
//			while((line = br.readLine()) != null) {
//				String[] values = line.split(",");
//				if(values.length > 2) {
//					if(values[1].equalsIgnoreCase(state)) {
//						data.add(values[index]);
////						totalCases += d;
////						totalDeath += Integer.parseInt(values[4]);
//					}
//				}
//			}    
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		
////		mortalityRate = totalDeath / totalCases;
//			
//		return data;
		
		
		try {
			
			List<String> lines = Files.readAllLines(Paths.get("data/cases.csv"), Charset.defaultCharset());
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
	public ArrayList<Double> getDoubleCovidData(String state, int index) {
		
//		try {
//			downloadCasesData();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		
//		ArrayList<Double> data = new ArrayList<Double>();
//		
//		String fileName = "data/cases.csv";
//		String line = "";
//		
//		int totalDeath = 0;
//		int totalCases = 0;
//		
//		try {
//			BufferedReader br = new BufferedReader(new FileReader(fileName));
//			int n = 0;
//			while((line = br.readLine()) != null) {
//				String[] values = line.split(",");
//				if(values.length > 2) {
//					if(values[1].equalsIgnoreCase(state)) {
//						Double d = Double.parseDouble(values[index]);
//						data.add(d);
////						totalCases += d;
////						totalDeath += Integer.parseInt(values[4]);
//					}
//				}
//			}    
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		
////		mortalityRate = totalDeath / totalCases;
//			
//		return data;
		
		try {
			
			List<String> lines = Files.readAllLines(Paths.get("data/cases.csv"), Charset.defaultCharset());
			ArrayList<Double> data = new ArrayList<Double>();
			
			for(int i = 0; i < lines.size(); i++) {
				String[] values = lines.get(i).split(",");
				System.out.println(values.length);
				if(values[1].equalsIgnoreCase(state)) {
					double d = Double.parseDouble(values[index]);
					data.add(d);
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
		
//		try {
//			downloadCountryVaccinesData();
//		} catch (IOException e1) {
//			e1.printStackTrace();
//		}
//		
//		ArrayList<String> data = null;
//		
//		String fileName = "data/countryVaccinationNumber.csv";
//		String line = "";
//		
//		try {
//			BufferedReader br = new BufferedReader(new FileReader(fileName));
//			while((line = br.readLine()) != null) {
//				String[] values = line.split(",");
//				data = new ArrayList<String>();
//				for(int i = 0; i < values.length; i++) {
//					data.add(values[i]);
//				}
//					
//			}
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//		
//		return data;
		
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
//	
//	public ArrayList<Double> simplifyList(ArrayList<Double> list){
//		
//		ArrayList<Double> shortened = new ArrayList<Double>();
//	}

	
//	public ArrayList<?> getCasesData(String state, int index){
//		
//		try {
//			downloadCasesData();
//		} catch (IOException e1) {
//			e1.printStackTrace();
//		}
//		
//		ArrayList<Double> cases = new ArrayList<Double>();
//		ArrayList<String> string = new ArrayList<String>();
//		
//		String fileName = "data/cases.csv";
//		String line = "";
//		
//		try {
//			BufferedReader br = new BufferedReader(new FileReader(fileName));
//			while((line = br.readLine()) != null) {
//				System.out.println("lodaing... " + index);
//				String[] values = line.split(",");
//					if(values[4].equalsIgnoreCase(state)) {
//						if(index != 0 && index != 1) {
//							double d = Double.parseDouble(values[index]);
//							cases.add(d);
//						}else {
//							string.add(values[index]);
//						}
//					}
//			}
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		
//		if(index != 0 && index != 1) {
//			return cases;
//		}
//		
//		return string;
//		
//	}
	
	/**
	 * reads vaccineNumber.csv data
	 * future plan: add parameter with filename so it is used for all
	 */
//	public ArrayList<?> readData(String dataName, String state) {
//		
//		ArrayList<Double> data = new ArrayList<Double>();
//		ArrayList<String> date = new ArrayList<String>();
//		
//		String fileName = "data/vaccineNumber.csv";
//		String line = "";
//		int index = -1;
//		
//		if(dataName.equals("VaccineAvailable")) {
//			index = 2;
//		}else if(dataName.equals("VaccineGiven")){
//			index = 3;
//		}else if(dataName.equals("PercentGiven")){
//			index = 4;
//		}else if(dataName.equals("date")) {
//			index = 0;
//		}else {
//			System.out.println("Wrong name of the data");
//			return null;
//		}
//		
//		try {
//			BufferedReader br = new BufferedReader(new FileReader(fileName));
//			while((line = br.readLine()) != null) {
//				String[] values = line.split(",");
//					if(values[1].equals(state)) {
//						if(index == 0) {
//							date.add(values[0]);
//						}else {
//							double d = Double.parseDouble(values[index]);
//							data.add(d);
//							System.out.println(values[1] + ": " + d);
//						}
//					}
//			}
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		
//		if(index == 0)
//			return date;
//		
//		return data;
//		
//	}

}
