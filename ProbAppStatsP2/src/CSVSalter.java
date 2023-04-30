

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


/**
 * @author Zach Emmell
 *CSV Salter class takes a file with (x,y) values of a function,
 * generates a random, configurable number to add or subtract to the y-values
 * and returns a new csv file with salted y-values. 
 */
public class CSVSalter {
	
	public CSVSalter() {
		
	}

	/**Recieves a CSV filename as an input, salts the y-values in the function,
	 *  and returns another file with salted data
	 * @param fileName
	 * @param saltRange
	 * @return outputFile (salted data)
	 * @throws IOException
	 */
	public File salt(String fileName, int saltRange) throws IOException{
		File inputFile = new File(fileName);
		ArrayList<String> input = new ArrayList<String>();
		File outputFile = new File("SaltedFunction.csv");
		BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile));
		Scanner scanner = new Scanner(inputFile);   //Add each line from the file as a string into an ArrayList
		for(int i = 0; scanner.hasNext(); i++) {
				input.add(i, scanner.nextLine());
		}
		scanner.close();
		Random rand = new Random();
		for(int i = 0; i < input.size(); i++) {   // Extract the y values in each array entry
			String line = input.get(i);
			String[] newLine = line.split(",");
			double intyValue = Double.parseDouble(newLine[1]);
			int random = -saltRange + rand.nextInt(saltRange*2);  //Add a random value to the y-value within the range 
			intyValue += random; 
			String yValue = String.valueOf(intyValue);
			newLine[1] = yValue;         //Reinsert new y-value
			line = newLine[0] + "," + newLine[1];    //Comma separated
			input.add(i, line);
			input.remove(i + 1);
		}
		for(String i: input) {      //Write new data values into a csv file
			bw.write(i);
			bw.newLine();
		}
		
		bw.close();
		return outputFile;
	}
	

}
