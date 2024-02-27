import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TSPBruteForce {

    private static int[][] graph; // Adjacency matrix representing distances between cities
    private static int numCities;

    public static void main(String[] args) throws IOException {
        // Example usage:
//    	/		Reading the file
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
		double[][] graph = new double[dimension+1][dimension+1];
		for (int i = 1; i<=dimension; i++) {
			for (int j=1; j<=dimension; j++) {
				graph[i][j] = Math.pow(Math.pow((build[i-1].getX()-build[j-1].getX()),2) + Math.pow((build[i-1].getY() - build[j-1].getY()),2),0.5);
//				System.out.print( distances[i][j] + " ");
			}
//			System.out.print( "\n");
		}

        numCities = graph.length;


        int[] tour = solveTSP();
        System.out.println("Optimal Tour: " + Arrays.toString(tour));
        System.out.println("Total Distance: " + calculateTotalDistance(tour));
    }

    public static int[] solveTSP() {
        int[] tour = new int[numCities];
        int[] minTour = new int[numCities];
        boolean[] visited = new boolean[numCities];
        int[] minDistance = {Integer.MAX_VALUE};

        solveTSPRecursive(0, tour, visited, minTour, minDistance);

        return minTour;
    }

    private static void solveTSPRecursive(int depth, int[] tour, boolean[] visited, int[] minTour, int[] minDistance) {
        if (depth == numCities) {
            int currentDistance = calculateTotalDistance(tour);
            if (currentDistance < minDistance[0]) {
                minDistance[0] = currentDistance;
                System.arraycopy(tour, 0, minTour, 0, numCities);
            }
            return;
        }

        for (int i = 0; i < numCities; i++) {
            if (!visited[i]) {
                tour[depth] = i;
                visited[i] = true;
                solveTSPRecursive(depth + 1, tour, visited, minTour, minDistance);
                visited[i] = false;
            }
        }
    }

    private static int calculateTotalDistance(int[] tour) {
        int totalDistance = 0;
        for (int i = 0; i < numCities - 1; i++) {
            totalDistance += graph[tour[i]][tour[i + 1]];
        }
        // Return to the starting city
        totalDistance += graph[tour[numCities - 1]][tour[0]];
        return totalDistance;
    }
}
