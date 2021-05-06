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
	
	String dataName;
	String state;
	
	/**
	 * 
	 * @param dataName the name of the data
	 * @param state the name of the state
	 * @pre to get available vaccines input "VaccineAvailable", to get vaccines given input "VaccineGiven", to get percent given input "PercentGiven" for dataName.
	 */
	public Stats(String dataName, String state) {
		this.dataName = dataName;
		this.state = state;
	}
	
	/**
	 * 
	 * @return the data of the dataName in the state
	 */
	public ArrayList<String> getData(){
		try {
			downloadData();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return readData();
	}
	
	/**
	 * this method uses java.io api to copy the data on cvc approved dataset to the csv file in the data folder.
	 * @return inputStream the data fetched from the link
	 * @throws IOException
	 */
	public final void downloadData() throws IOException{
		InputStream inputStream = new URL("https://raw.githubusercontent.com/owid/covid-19-data/master/public/data/vaccinations/us_state_vaccinations.csv").openStream();
		Files.copy(inputStream, Paths.get("data/vaccineNumber.csv"), StandardCopyOption.REPLACE_EXISTING);
	}
	
	
	/**
	 * reads vaccineNumber.csv data
	 * future plan: add parameter with filename so it is used for all
	 */
	public ArrayList<String> readData() {
		
		ArrayList<String> data = new ArrayList<String>();
		
		String fileName = "data/vaccineNumber.csv";
		String line = "";
		int index = -1;
		
		if(dataName.equals("VaccineAvailable")) {
			index = 2;
		}else if(dataName.equals("VaccineGiven")){
			index = 3;
		}else if(dataName.equals("PercentGiven")){
			index = 4;
		}else {
			System.out.println("Wrong name of the data");
			return null;
		}
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			while((line = br.readLine()) != null) {
				String[] values = line.split(",");
					if(values[1].equals(state)) {
						data.add(values[index]);
						System.out.println(values[1] + ": " + values[index]);
					}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return data;
		
	}

}
