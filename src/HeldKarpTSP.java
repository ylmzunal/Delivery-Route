import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class HeldKarpTSP {

    public static double heldKarp(double[][] graph) {
        int n = graph.length;
        double[][] memo = new double[n][1 << n];
        for (double[] row : memo) {
            Arrays.fill(row, -1);
        }

        // Base case: starting from city 0
        memo[0][1] = 0;

        return heldKarpHelper(graph, 0, 1, memo);
    }

    private static double heldKarpHelper(double[][] graph, int current, int state, double[][] memo) {
        int n = graph.length;

        // Check if the subproblem has already been solved
        if (memo[current][state] != -1) {
            return memo[current][state];
        }

        // Initialize the minimum distance to maximum value
        double minDistance = Double.MAX_VALUE;

        // Iterate over all cities
        for (int next = 0; next < n; next++) {
            // Check if the next city is not visited
            if ((state & (1 << next)) == 0) {
                // Calculate the distance to the next city
                double distance = graph[current][next] + heldKarpHelper(graph, next, state | (1 << next), memo);

                // Update the minimum distance
                minDistance = Math.min(minDistance, distance);
            }
        }

        // Memoize the result
        memo[current][state] = minDistance;
        return minDistance;
    }

    public static void main(String[] args) throws IOException {
    	
//		Reading the file
		String delete = "Migros";
		String fileName = "/Users/yilmazunal/eclipse-workspace/SWE501/input02.txt";
		File file = new File(fileName);
		System.out.print(file + "\n");
		FileReader fReader = new FileReader(file);
		
		
//		String Line;
		BufferedReader br = new BufferedReader(fReader);
		String line;

		int initialPoint =0;
		ArrayList<Double> coordinatesNumber = new ArrayList<Double>();
		int count = 0;
		
		while((line = br.readLine()) != null){
		     //process the line
//			Determination of Migros coordinate
			if(line.indexOf(delete) != -1) {
				initialPoint = count+1;
	         }
			line = line.replace(delete, "");
		    String[] coordinatesString = line.split(",");
		    
//		    Converting data from string to double type
		     for (String str : coordinatesString) {
		    	  coordinatesNumber.add(Double.parseDouble(str));
		      }
		     count +=1;
		}
		br.close();
		
// 		Creating Objects from Building class		
		
		Building[] build = new Building[count];
		
		int k =0;
		for (int i=0; i<count; i++) {
			build[i] = new Building(i,coordinatesNumber.get(k),coordinatesNumber.get(k+1));
			k += 2;
		}
        double[][] graph = getDistanceMatrix(build, count);
        
        double result = heldKarp(graph);
        
        System.out.println("Minimum distance: " + result);
    }
    
    public static double[][] getDistanceMatrix(Building[] build, int count) {

//		Calculation of distances between houses
		int dimension = count;
		double[][] distanceMatrix = new double[dimension+1][dimension+1];
		for (int i = 1; i<=dimension; i++) {
			for (int j=1; j<=dimension; j++) {
				distanceMatrix[i][j] = Math.pow(Math.pow((build[i-1].getX()-build[j-1].getX()),2) + Math.pow((build[i-1].getY() - build[j-1].getY()),2),0.5);
//				System.out.print( distanceMatrix[i][j] + " ");
			}
//			System.out.print( "\n");
		}
		
//		
//		double path = distanceMatrix[10][9] + distanceMatrix[9][8] + distanceMatrix[8][7] + distanceMatrix[7][1] + distanceMatrix[1][2] +
//				distanceMatrix[2][4] + distanceMatrix[4][5] + distanceMatrix[5][3] + distanceMatrix[3][6] + 
//				distanceMatrix[6][12] + distanceMatrix[12][11] + distanceMatrix[11][10];
//		System.out.println(path);
		
// 		Calculating the solution and printing it to the screen
		return distanceMatrix;
	}
}
