import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.FileSystemNotFoundException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;

public class State {
	
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
	 */
	public void readData() {
		String fileName = "data/vaccineNumber.csv";
		File file = new File(fileName);
		double average = 0;
		int count = 0;
		try {
			Scanner inputStream = new Scanner(file);
			inputStream.next();
			while(inputStream.hasNext()) {
<<<<<<< Updated upstream
				String data = inputStream.next(); 
=======
				String data = inputStream.next(); //gets the whole thing????
>>>>>>> Stashed changes
				String[] values = data.split(",");
				if(values[3] != "") {
					double numOfVaccines = Double.parseDouble(values[3]);
					average += numOfVaccines;
					count++;
				}
<<<<<<< Updated upstream

=======
>>>>>>> Stashed changes
			}	
			inputStream.close();
			System.out.println("average: " + average / count);
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
