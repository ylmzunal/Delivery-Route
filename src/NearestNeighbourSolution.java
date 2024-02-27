/* 
 *  Yılmaz Ünal
 *  2023719108
 *  18/12/2023
 *  This Java program finds the fastest delivery route of the Migros delivery vehicle. 
 *  I implemented the nearest neighbor algorithm to solve the problem. 
 *  The solution to the problem consists of 3 program files. 
 *  These are the main code (yılmaz\_unal.java), "Building" class 
 *  and "NearestNeighborSoltion" class, respectively. 
 */

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

public class NearestNeighbourSolution {
	
 
    public static void migrosDeliveryRoute(double distanceMatrix[][], int initialPoint, Building[] build ) throws IOException
    {

    	Stack<Integer> stack = new Stack<Integer>();    // Creating a list of unvisited houses
        int numberOfNodes = distanceMatrix.length-1;
        
        File routePath = new File("RoutePath_Input04.txt");
		if (!routePath.exists()) {
			routePath.createNewFile();
		}
		
        FileWriter fWriter = new FileWriter(routePath ,false);
        BufferedWriter bWriter = new BufferedWriter(fWriter);
        bWriter.write(String.valueOf(initialPoint) + ","+ build[initialPoint-1].getX() + "," + build[initialPoint-1].getY() + "\n");
        
        int[] visitedHouse = new int[numberOfNodes+1];  // Creating an empty tour to store the sequence of houses.
        visitedHouse[initialPoint] = 1;  // Starting house (Migros)
        double distance = 0.0;
        stack.push((int) initialPoint);
        int route ;
		int dst = 0, i;
        double min;
        boolean minFlag = false;
        System.out.print("Shortest Route: [" +  initialPoint + ", ");
        
        while (!stack.isEmpty())
        {
        	route = stack.peek();
            //System.out.println(route + ", Route");
            i = 1;
            min = Double.MAX_VALUE;
            while (i <= numberOfNodes)
            {
                if (distanceMatrix[route][i] > 0.00000 && visitedHouse[i] == 0)
                {
                    if (min > distanceMatrix[route][i])
                    {
                        min = distanceMatrix[route][i];
                        dst = i;						// Finding the nearest unvisited house to the current house
                        minFlag = true;
                    }
                }
                i++;
                
            }
            
            if (minFlag)
            {
            	visitedHouse[dst] = 1;  // Moving to the nearest house and mark it as visited
                distance += distanceMatrix[route][dst];
                stack.push((int) dst);  // Updating the current house to the one just visited
                bWriter.write(String.valueOf(dst) + ","+ build[dst-1].getX() + ","+ build[dst-1].getY() +"\n");
                System.out.print(dst + ", ");
                minFlag = false;
                continue;
            }

            stack.pop();        // Removing the visited house from the list of unvisited houses.
        }
        distance += distanceMatrix[dst][initialPoint];
//        System.out.println("initialPoint: " + initialPoint + ", dst: " + dst);
        bWriter.write(String.valueOf(initialPoint) + ","+ build[initialPoint-1].getX() + ","+ build[initialPoint-1].getY());
        bWriter.close();
        System.out.print( initialPoint + "]");
        System.out.println("\nDistance: " + distance);
    }

}
