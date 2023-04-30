import java.io.IOException;

public class CSVTester {
	/**
	 * main method used to test Plotter, Salter, and Smoother Objects
	 * @param args
	 */

	public static void main(String[] args) {
		CSVPlotter plotter = new CSVPlotter();
		CSVSalter salter = new CSVSalter();
		CSVSmoother smoother = new CSVSmoother();
		
		
		try {
			plotter.plot(-100, 100, 2, 200);
		} catch (IOException e1) {
			
			e1.printStackTrace();
		}
		
		try {
			salter.salt("SimpleFunction.csv", 100);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		try {
			smoother.smooth("SaltedFunction.csv", "SmoothedFunction.csv", 6);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		try {
			smoother.smooth("SmoothedFunction.csv", "Smoothed2Function.csv", 6);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		try {
			smoother.smooth("Smoothed2Function.csv","Smoothed3Function.csv", 6);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}

}
