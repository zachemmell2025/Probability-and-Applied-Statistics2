import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * @author Zach Emmell
 * CSV Smoother class smooths salted/noisy data taken from another csv file,
 *  and outputs another csv file
 */
public class CSVSmoother {

	public CSVSmoother() {
		
	}
	/**Recieves a csv file with salted data, generates a
	 * new y-value using a rolling average, and returns a
	 * new csv file of smoothed data
	 * @param fileName
	 * @param newfileName
	 * @param windowValue
	 * @return outputFile (CSV File with smoothed data)
	 * @throws IOException
	 */
	public File smooth(String fileName, String newfileName, int windowValue) throws IOException {
		File inputFile = new File(fileName);
		ArrayList<String> input = new ArrayList<String>();
		File outputFile = new File(newfileName);
		BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile));
		Scanner scanner = new Scanner(inputFile); 
		for(int i = 0; scanner.hasNext(); i++) {  // Add the lines from the file into an ArrayList
				input.add(i, scanner.nextLine());
		}
		scanner.close();
		
		for(int i = 0; i < input.size(); i++) {
			ArrayList<Double> averages = new ArrayList<Double>();
			int j = i + windowValue;
			while(j >= 0 && j >= i - windowValue) { //Retrieve y values from data points within window value of index i
				if(j >= input.size()) j = input.size() - 1;
				String line = input.get(j);
				String[] firstY = line.split(",");
				double y = Double.parseDouble(firstY[1]);
				averages.add(y); // Put y vaules into an array to be averaged together
				j--;
			}
			int averageY = 0;
			for(int k = 0; k < averages.size(); k++) { 
				averageY += averages.get(k); //Sum y the values
			}
			String line = input.get(i);
			String[] newLine = line.split(",");
			String yValue = String.valueOf(averageY / windowValue + 1); //Reinsert new averaged y value into the line, and back into the ArrayList
			newLine[1] = yValue;
			line = newLine[0] + "," + newLine[1];
			input.add(i, line);
			input.remove(i + 1);
		}
		for(String i: input) {    //Write new lines with smoothed data into a new file
			bw.write(i);
			bw.newLine();
		}
		
		bw.close();
		
		return outputFile;
	}
	
	

}
