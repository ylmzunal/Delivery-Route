/* 
 *  Yılmaz Ünal
 *  2023719108
 *  18/12/2023
 *  This Java program finds the fastest delivery route of the Migros delivery vehicle. 
 *  I implemented the nearest neighbour algorithm to solve the problem. 
 *  The solution to the problem consists of 3 program files. 
 *  These are the main code (yılmaz\_unal.java), "Building" class 
 *  and "NearestNeighborSoltion" class, respectively. 
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;

public class yilmaz_unal {

	public static void main(String[] args) throws IOException {
		
//		Reading the file
		Instant start = Instant.now();
		String delete = "Migros";
		String fileName = "/Users/yilmazunal/eclipse-workspace/SWE501/input04.txt";
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

        NearestNeighbourSolution.migrosDeliveryRoute(getDistanceMatrix(build, count), initialPoint, build);
        
		Instant finish = Instant.now();
		Duration runTime = Duration.between(start,finish);
		long milisn = runTime.toMillis();
		System.out.println("\nProgram running time: " + milisn + "msn.");



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
