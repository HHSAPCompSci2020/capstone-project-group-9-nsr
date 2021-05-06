import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class fetches data and stores them in arraylists
 * @author 
 *
 */
public class Stats {
	
	String filename;
	
//	public Stats(String filename) {
//		this.filename = filename;
//		downloadData
//	}
	
	public ArrayList<Double> getData(String filename, int column){
		return null;
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
	public void readData() {
		
		String fileName = "data/vaccineNumber.csv";
		String line = "";
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			while((line = br.readLine()) != null) {
				String[] values = line.split(",");
				System.out.println(values[0]);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
