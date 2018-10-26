package dataStructure_TSP;

/**
 * Class Point with Geocoordinates 
 * @author Thomas
 *
 */
public class Point {
	
	/**
	 * each Point consists of an index, x-Coordinate and y-Coordinate.
	 */
	int indexPointNumber;
	double xCoordinate;
	double yCoordinate;
	int degree;
	
	/**
	 * Constructor
	 * @param n
	 * @param x
	 * @param y
	 */
	public Point(int n, double x, double y) {
		this.indexPointNumber = n;
		this.xCoordinate = x;
		this.yCoordinate = y;
		this.degree = 0;
	}
	
	/**
	 * 
	 */
	public Point() {		
	}
	
	/**
	 * 
	 * @return
	 */
	public double getXCoordinate() {
		return this.xCoordinate;
	}
	
	/**
	 * 
	 * @return
	 */
	public double getYCoordinate() {
		return this.yCoordinate;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getIndexPointNumber() {
		return this.indexPointNumber;
	}
	
	public double getLongitudeValue() {
		return this.yCoordinate;
	}
	
	public double getLatitudeValue() {
		return this.xCoordinate;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getDegree() {
		return this.degree;
	}
	
	/**
	 * 
	 * @param i
	 */
	public void setDegree(int i) {
		this.degree = i;
	}
	
	/**
	 * 
	 */
	public void increaseDegree() {
		this.degree++;
	}
	
	/**
	 * 
	 * @param p
	 * @return
	 */
	public double eucDistanceTo(Point p) {
		double distance;
		distance = Math.sqrt(Math.pow(this.xCoordinate - p.getXCoordinate(), 2) + Math.pow(this.yCoordinate - p.getYCoordinate(), 2));
		return distance;
	}
	
	/**
	 * 
	 * @param Point p with longitude/latitude coordinates
	 * @return 
	 */
	public int geoDistanceTo(Point p) {
		//radius of the world
		final double r = 6378.388;
		//x Value is latitude; y value longitude
		//distance calculation
		
		double q1 = Math.cos(this.yCoordinate - p.getLongitudeValue());
		double q2 = Math.cos(this.xCoordinate - p.getLatitudeValue());
		double q3 = Math.cos(this.xCoordinate + p.getLatitudeValue());
		
		int distance = (int) (r * Math.acos(0.5 * ((1.0 + q1) * q2 - (1.0 - q1) * q3)) +1.0);
		
		return distance;
	}
	
	@Override
	public boolean equals (Object object) {
	    boolean result = false;
//	    if (object == null || object.getClass() != getClass()) {
//	        result = false;
//	    } else {
//	        Point point = (Point) object;
//	        if (this.indexPointNumber == point.indexPointNumber) {
//	            result = true;
//	        }
//	    }
	    Point point = (Point) object;
        if (this.indexPointNumber == point.indexPointNumber) {
            result = true;
        }
//        if (this == point) {
//            result = true;
//        }
	    return result;
	}
}
