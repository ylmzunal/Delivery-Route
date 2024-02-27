import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class BruteForceTSP {

    private static int[][] distances; // Matrix of distances between cities
    private static int[] currentTour;  // Current tour being considered
    private static int[] bestTour;     // Best tour found so far
    private static int bestTourLength = Integer.MAX_VALUE; // Length of the best tour

    public static void main(String[] args) throws IOException {

//		Reading the file
		String delete = "Migros";
		String fileName = "/Users/yilmazunal/eclipse-workspace/SWE501/input01.txt";
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
		
		
// 		Creating Objects from Building class		
		
		Building[] build = new Building[count];
		
		int k =0;
		for (int i=0; i<count; i++) {
			build[i] = new Building(i,coordinatesNumber.get(k),coordinatesNumber.get(k+1));
			k += 2;
		}
		br.close();
		
		
//		Calculation of distances between houses
		int dimension = count;
		double[][] distances = new double[dimension+1][dimension+1];
		for (int i = 1; i<=dimension; i++) {
			for (int j=1; j<=dimension; j++) {
				distances[i][j] = Math.pow(Math.pow((build[i-1].getX()-build[j-1].getX()),2) + Math.pow((build[i-1].getY() - build[j-1].getY()),2),0.5);
				System.out.print( distances[i][j] + " ");
			}
			System.out.print( "\n");
		}

        int numCities = distances.length;

        currentTour = new int[numCities];
        bestTour = new int[numCities];

        // Initialize the current tour with city indices
        for (int i = 0; i < numCities; i++) {
            currentTour[i] = i;
        }

        permute(1, numCities);

        // Print the best tour and its length
        System.out.println("Best Tour: " + Arrays.toString(bestTour));
        System.out.println("Best Tour Length: " + bestTourLength);
    }

    private static void permute(int start, int end) {
        if (start == end - 1) {
            // Calculate the length of the current tour
            int currentTourLength = calculateTourLength(currentTour);

            // Update the best tour if the current tour is shorter
            if (currentTourLength < bestTourLength) {
                bestTourLength = currentTourLength;
                System.arraycopy(currentTour, 0, bestTour, 0, currentTour.length);
            }
        } else {
            for (int i = start; i < end; i++) {
                // Swap elements at positions start and i
                swap(start, i);

                // Recursively permute the rest of the array
                permute(start + 1, end);

                // Undo the swap to backtrack
                swap(start, i);
            }
        }
    }

    private static void swap(int i, int j) {
        int temp = currentTour[i];
        currentTour[i] = currentTour[j];
        currentTour[j] = temp;
    }

    private static int calculateTourLength(int[] tour) {
        int length = 0;
        for (int i = 0; i < tour.length - 1; i++) {
            length += distances[tour[i]][tour[i + 1]];
        }
        // Add the distance back to the starting city to complete the tour
        length += distances[tour[tour.length - 1]][tour[0]];
        return length;
    }
}
