import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
/**
 * 
 * @author Zach Emmell
 *CSV Plotter class creates x and y values of a simple y=mx+b function and puts them into a csv file to later be plotted in excel
 */

public class CSVPlotter {

	public CSVPlotter() {
		
	}
	/**plot is responsible for creating the x, y values 
	 * based off the function y = (1/2)x + 9, range, increment, and number of points.
	 * Writes each ArrayList entry into a csv file
	 * @param lowXRange
	 * @param highXRange
	 * @param xIncrement
	 * @param numPoints
	 * @return ArrayList with Strings of x and y values
	 * @throws IOException 
	 */
	public File plot(int lowerBound, int upperBound, int xIncrement, int numPoints) throws IOException {
		ArrayList<String> output = new ArrayList<String>();
		
		for(int x = lowerBound ; x <= upperBound && numPoints >= 0; x += xIncrement) {   //creates x,y points based off of the simple function separated by commas, and placed into the ArrayList
			double y = (0.5 * x) + 9;
			String newX = String.valueOf(x);
			String newY = String.valueOf(y);
			output.add(newX +"," + newY);
			numPoints = numPoints - xIncrement;
			
		}
		File csv = new File("SimpleFunction.csv");
		FileWriter fw = new FileWriter(csv);
		BufferedWriter bw = new BufferedWriter(fw);
		for(int i = 0; i < output.size(); i++) {      //Writes each entry of (x,y) values in ArrayList, and writes them to a csv file
			bw.write(output.get(i));
			bw.newLine();
		}
		bw.close();
		return csv;
		
	}	
		
}
