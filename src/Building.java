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
public class Building {
	
//	private double[][] coordinates ;
	private  double x,y;
	private int ID;
	
	Building(int ID, double x, double y) {
		this.x = x;
		this.y = y;
		this.ID = ID;
	}
	
//	public double[][] getCoordinates() {
//		return this.coordinates;
//	}
	
	public int getID() {
		return this.ID;
	}
	
	public  double getY() {
		return this.y;
	}
	
	public  double getX() {
		return this.x;
	}
	

}
