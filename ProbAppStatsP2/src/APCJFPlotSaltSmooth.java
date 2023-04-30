
import org.apache.commons.math4.legacy.stat.descriptive.DescriptiveStatistics;
import org.apache.commons.rng.UniformRandomProvider;
import org.apache.commons.rng.simple.RandomSource;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
/**
 * 
 * @author Zach Emmell
 *Plotting, Salting, and Smoothing using Apache Commons Math and JFreeCharts libraries
 */
public class APCJFPlotSaltSmooth extends ApplicationFrame{
	public APCJFPlotSaltSmooth(String applicationTitle, String chartTitle) {
	      super(applicationTitle);
	      JFreeChart lineChart = ChartFactory.createXYLineChart(
	         chartTitle,
	         "X","Y",
	         DataSet(),
	         PlotOrientation.VERTICAL,
	         true,true,false);
	         
	      ChartPanel chartPanel = new ChartPanel(lineChart);
	      chartPanel.setPreferredSize(new java.awt.Dimension( 560 , 367 ) );
	      setContentPane(chartPanel);
	   }

		
		/**Creates basic (x,y) function as an XYSeries to be plotted
		 * @return an XYSeries
		 */
		public XYSeries data() {
			XYSeries series = new XYSeries("(1/2)x + 9");
		      for(double x = -100; x < 101; x++) {
		    	  series.add(x, (0.5*x) + 9);
		      }
		    return series;
		}

	  
	   
	   /**Salts a XYSeries and returns a new XYSeries with salted data
	 * @param data
	 * @return salted XYSeries
	 */
	public XYSeries saltedDataSet(XYSeries data, int saltRange) {
		   XYSeries series = new XYSeries("Salted Data");
		   UniformRandomProvider rng = RandomSource.XO_RO_SHI_RO_128_PP.create();    //UniformRandomProvider class creates a new random number to be added to the y-value in the series
		   int index = 0;
		   for(int x = (int) data.getMinX(); x < data.getMaxX(); x++) {
			   double rand = rng.nextDouble(-saltRange, saltRange); // UniformRandomProvider.nextDouble() method allows for a clear minimum and maximum bound to be set, unlike java.util.Random.nextDouble()
			   double yValue = (double) data.getY(index);
			   series.add(x, yValue + rand);
			   index++;
		   }
		   return series;
	   }
	   
	   /**Takes a salted dataset and smooths using a rolling mean, returns a new, smoothed XYSeries
	 * @param saltedData
	 * @return smoothed XYSeries
	 */
	public XYSeries smoothedDataSet(XYSeries saltedData, int windowSize) {
		   DescriptiveStatistics stats = new DescriptiveStatistics(windowSize);  //DescriptiveStatistics helps keep a rolling mean, has a parameter for windowValue
		   XYSeries series = new XYSeries("Smoothed Data");
		   int index = 0;
		   for(int x = (int) saltedData.getMinX(); x < saltedData.getMaxX(); x++) {
			   long nLines = 1;
			   while (nLines != 0) {
				   double y = (double) saltedData.getY(index);
		           nLines++;
		           stats.addValue(y);
		           if (nLines == windowSize + 1) {
		                nLines = 0;
		                y = stats.getMean();
		          }
		          series.add(x, y);
			   }
			   index++;
		   }
		   
		   return series;
		   
	   }
	   
	   /**Creates the XYDataset with all the normal, salted, and smoothed XYSeries. This will then be plotted by JFreeChart
	 * @return XYDataset
	 */
	public XYDataset DataSet() {
		   XYSeriesCollection Dataset = new XYSeriesCollection();
		   XYSeries dataset = data();
		   XYSeries saltedData = saltedDataSet(dataset, 100);
		   XYSeries smoothedData1 = smoothedDataSet(saltedData, 3);
		   XYSeries smoothedData2 = smoothedDataSet(smoothedData1, 10);
		   XYSeries smoothedData3 = smoothedDataSet(smoothedData2, 10);
		   Dataset.addSeries(dataset);
		   Dataset.addSeries(saltedData);
		   Dataset.addSeries(smoothedData3);
		   return Dataset;
	   }
	   
	   
	   
	   public static void main(String[] args) {
	      APCJFPlotSaltSmooth chart = new APCJFPlotSaltSmooth(
	         "Function" ,
	         "Simple Function");

	      chart.pack();
	      RefineryUtilities.centerFrameOnScreen(chart);
	      chart.setVisible(true);
	   }
}
