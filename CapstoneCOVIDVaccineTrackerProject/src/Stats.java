import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;

/**
 * This class fetches data and stores them in arraylists
 * @author nodoka shibasaki
 *
 */
public class Stats {
	
	/**
	 * 
	 * @param dataName the name of the data
	 * @param state the name of the state
	 * @pre to get available vaccines input "VaccineAvailable", to get vaccines given input "VaccineGiven", to get percent given input "PercentGiven" for dataName.
	 */
	public Stats() {
		try {
			downloadData();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * this method uses java.io api to copy the data on cvc approved dataset to the csv file in the data folder.
	 * @return inputStream the data fetched from the link
	 * @throws IOException
	 */
	public final void downloadData() throws IOException{
		InputStream inputStream = new URL("https://raw.githubusercontent.com/owid/covid-19-data/master/public/data/vaccinations/us_state_vaccinations.csv").openStream();
		Files.copy(inputStream, Paths.get("data/vaccineNumber.csv"), StandardCopyOption.REPLACE_EXISTING);
		
		InputStream inputStream2 = new URL("https://raw.githubusercontent.com/datasets/covid-19/main/data/us_confirmed.csv").openStream();
		Files.copy(inputStream2, Paths.get("data/cases.csv"), StandardCopyOption.REPLACE_EXISTING);
	}
	
	public ArrayList<String> getVaccinationInfo(String state) {
		
		ArrayList<String> data = new ArrayList<String>();
		
		String fileName = "data/vaccineNumber.csv";
		String line = "";
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			while((line = br.readLine()) != null) {
				String[] values = line.split(",");
					if(values[1].equals(state)) {
						
						data.set(0, values[0]);//date
						data.set(1, values[2]);//total_vaccinations
						data.set(2, values[3]);//total_distributed
						data.set(3, values[9]);//distributed_per_hundred
						data.set(4, values[4]);//people_vaccinated
						data.set(5, values[6]);//total_vaccinations_per_hundred
						data.set(6, values[7]);//people_fully_vaccinated
						data.set(7, values[5]);//people_fully_vaccinated_per_hundred
					}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		
		return data;
		
	}
	
	
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
