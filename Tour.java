package dataStructure_TSP;

import java.util.LinkedList;

import dataStructure_TSP.Point;

/**
 * 
 * @author Thomas
 *
 */
public class Tour {
	/**
	 * a Tour consists of a LinkedList of Points
	 */
	LinkedList<Point> pointList = new LinkedList<Point>();
	double distance;
	
	/**
	 * 
	 */
	public Tour () {
	}
	
	/**
	 * 
	 * @return Returns the Size of the tour
	 */
	public double getDistance() {
		return distance;
	}
	
	/**
	 * 
	 * @param d
	 */
	public void increaseDistance(double d) {
		distance = distance + d;
	}
	
	/**
	 * 
	 * @return Returns the Tour
	 */
	public LinkedList<Point> getTour(){
		return pointList;
	}
	
	/**
	 * 
	 * @param i inserts the Point at index i
	 * @param p 
	 */
    public void insertPoint(int i, Point p){
        pointList.add(i, p);
    }
    
    /**
     * 
     * @param p
     */
    public void addPoint(Point p) {
    	pointList.add(p);
    }
    
	/**
	 * 
	 * @return the Size of the tour (N Points: The Tour consists of N+1 Points. Start and End Point is the same)
	 */
    public int getTourSize(){
    	return pointList.size();
    }
    
    /**
     * 
     * @return
     */
    public Point getLastPoint() {
    	return pointList.getLast();
    }
    /**
     * 
     * @return
     */
    public Point getFirstPoint() {
    	return pointList.getFirst();
    }
    
    /**
     * 
     * @param i
     * @return
     */
    public Point getPoint(int i){
        return pointList.get(i);
    }
    
    public boolean containsPoint(Point p) {
    	boolean returnvalue = pointList.contains(p);
    	return returnvalue;
    }
    
	
}
