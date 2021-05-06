/**
	 * 
	 */
import java.io.IOException;

import processing.core.PApplet;
public class Main {
	
	public static void main(String[] args) throws IOException {
		State state = new State();
		state.downloadData();
		state.readData();
	}

}
