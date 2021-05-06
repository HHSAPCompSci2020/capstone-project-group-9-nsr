/**
	 * 
	 */
import java.io.IOException;

import processing.core.PApplet;
public class Main {
	
	public static void main(String[] args) throws IOException {
		Stats state = new Stats();
		state.downloadData();
		state.readData();
	}

}
