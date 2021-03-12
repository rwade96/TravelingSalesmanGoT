import java.util.ArrayList;

public class CoordinateManager {

	// Holds our points
    private  ArrayList<Coordinate> pointArray = new ArrayList<Coordinate>();
    
    public CoordinateManager(int size) {
    	pointArray = new ArrayList<Coordinate>(size);
    }

    // Adds a point
    public  void addPoint(Coordinate point) {
        pointArray.add(point);
    }
    
    // Get a point
    public Coordinate getPoint(int i){
        return (Coordinate)pointArray.get(i);
    }
    
    // Get the number of points
    public  int size(){
        return pointArray.size();
    }
    
    public  ArrayList<Coordinate> getList() {
    	return pointArray;
    }
    public void printCoordinates() {
    	for (int i = 0; i < pointArray.size(); i++) {
    		System.out.println("Point #"+(i+1)+" X: "+pointArray.get(i).getX()+" Y: "+pointArray.get(i).getY());
    	}
    }
    
    public double getDistance() {
    	int distance = 0;
    	for(int i = 0; i < size()-1; i++) {
    		distance += distanceBetweenPoints(getPoint(i), getPoint(i+1));
		}
    	return distance;
    }
    
    public double distanceBetweenPoints(Coordinate point1, Coordinate point2) {
        double distX = Math.abs(point1.getX() - point2.getX());
        double distY = Math.abs(point1.getY() - point2.getY());
        return Math.sqrt(Math.pow(distX, 2) + Math.pow(distY, 2));
    }
}
