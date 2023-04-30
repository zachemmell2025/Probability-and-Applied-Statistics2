import java.util.ArrayList;
/**
 * StatsLibrary...methods include:
 * -------Project 1-------
 * Mean
 * Median
 * Mode
 * Standard Deviation
 * Intersect
 * Union
 * Compliment
 * Factorial
 * Permutations
 * Combinations
 * Binomial Distribution
 * Geometric Distribution
 * ---------Project 2-------at bottom
 * Poisson Distribution
 * Hyper Geometric Distribution
 * Tchebysheffs
 * Uniform Distribution
 */

/**
 * @author Zach Emmell
 *
 */
public class StatsLibrary {

	public double mean(ArrayList<Integer> array) {
		double total = 0;
		for (int singleElement : array) {
			total = total + singleElement;
		}
		double mean = total / array.size();
		return mean;
	}

	public int median(ArrayList<Integer> array) {
		int medianPlace = (array.size() - 1) / 2;
		int median = array.get(medianPlace);
		return median;
	}

	public int mode(ArrayList<Integer> array) {
		int maxValue = 0, maxCount = 0;
		for (int i = 0; i < array.size(); i++) {
			int count = 0;
			for (int j = 0; j < array.size(); j++) {
				if (array.get(j) == array.get(i))
					count++;
			}
			if (count > maxCount) {
				maxCount = count;
				maxValue = array.get(i);
			}
		}
		return maxValue;

	}

	public double standardDeviation(ArrayList<Integer> array) {
		double total = 0;
		for (int singleElement : array) {
			total = total + singleElement;
		}
		double mean = total / array.size();

		double td = 0;
		for (int i = 0; i < array.size(); i++) {
			double fd = array.get(i) - mean;
			for (int j = 0; j < array.size(); j++) {
				double sd = Math.pow(fd, 2);
				for (int k = 0; k < array.size(); k++) {
					td += sd;
				}

			}
		}
		double fourd = td / (array.size() - 1);
		double stanDev = Math.sqrt(fourd);
		return stanDev;
	}
	
	public ArrayList<Integer> intersect(ArrayList<Integer> set1, ArrayList<Integer> set2) {
		ArrayList<Integer> intersection = new ArrayList<Integer>(set1);
		intersection.retainAll(set2);
		return intersection;
	}

	public ArrayList<Integer> union(ArrayList<Integer> set1, ArrayList<Integer> set2) {
		ArrayList<Integer> union = new ArrayList<Integer>(set1);
		 union.addAll(set2);
		 return union;
	}

	public ArrayList<Integer> compliment(ArrayList<Integer> set1, ArrayList<Integer> superset) {
		ArrayList<Integer> compliment = new ArrayList<Integer>(superset);
		compliment.removeAll(set1);
		return compliment;
	}

	public static Integer factorial(Integer n) {
		Integer factorial = n;
		for(int i = n - 1; i > 0; i--) {
			factorial = factorial * i;
		}
		return factorial;
	}

	public static double permutations(Integer n, Integer r) {
		double permutations = factorial(n) / factorial(n - r);
		return permutations;
	}

	public static double combinations(Integer n, Integer r) { 
		double combinations = factorial(n) / (factorial(r) * factorial(n - r));
		return combinations;
	}
	
	public double binomialDistribution(double p, double q, int n, int y) {
		double binDis = (factorial(n) / factorial(y) * factorial(n - y)) * Math.pow(p, y) * Math.pow(q, n-y);
		return binDis;
	}
	
	public double geometricDistribution(double p, double q, double y) {
		double geoDis = Math.pow(q, y - 1) * p;
		return geoDis;
	}
	
	
	// Project 2 methods ---------------------------------------------------------------------------------------------------------------
	
	
	public double hyperGeometricDistribution(int N, int n, int r, int y) {
		double hypGeo = combinations(r, y) * combinations(N - r, n - y) / combinations(N, n);
		return hypGeo;
	}
	
	public double poissonDistribution(double lambda, int y) {
		double e = 2.718281828;
		double poisDis = (Math.pow(lambda, y)) * Math.pow(e, -lambda) / factorial(y);
		return poisDis;
	}
	
	public double tchebysheffs(double mean, double stanDev, int high) {
		double k = (high - mean) / stanDev;
		double tcheby = 1 - (1/Math.pow(k, 2.0));
		return tcheby;
	}
	
	public double uniformDistribution(int a, int b, int upperBound, int lowerBound) {
		double uniDis = (upperBound - lowerBound) / (b - a);
		return uniDis;
	}
}

