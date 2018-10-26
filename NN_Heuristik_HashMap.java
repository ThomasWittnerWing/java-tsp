package Algorithms;

import dataStructure_TSP.Point;
import dataStructure_TSP.Tour;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 
 * @author Thomas
 *
 */
public class NN_Heuristik_HashMap {	   
  
	/**
     * 
     * @param coordinates (x-/y-Coordinates; ("Geo") Longitude/Latitude) of the tspFile
     * @return A HashMap with <key, Point> pairs
     *    
     */  
    public static HashMap<Integer, Point> createHashMap (double[][] coordinates){
    	HashMap<Integer, Point> hashMap = new HashMap<Integer, Point>();
        for (int i=0; i< coordinates.length; i++){
            Point p = new Point(i+1, coordinates[i][0], coordinates[i][1]);
            hashMap.put(i+1, p);
        }
        return hashMap;
    }
     
	/** 
	 * NN Heuristic
	 * @param p is a PointList to create a Tour
	 * @return
	 */
	public static Tour nnHeuristik(HashMap<Integer, Point> hMap){
		Tour tour = new Tour();
		HashMap <Integer, Point> hashMap = hMap;
		Point startPoint;
		int schleifenLaeufe = hashMap.size()-1;
		
		//StartPoint of the Tour. Standard Point 1
		startPoint = hashMap.get(1);
		tour.addPoint(startPoint);
		
		Point von = startPoint;
		Point nach;
		
		//for debugging
		int counter = 0;
		
		//add the nearest Neighbor to Tour
		for (int i = 0; i < schleifenLaeufe; i++) {
			counter++;
			if (counter%10 == 1) System.out.println(counter-1);
			
			hashMap.remove(von.getIndexPointNumber());
			nach = nnSearch(von, hashMap);
			tour.addPoint(nach);
			tour.increaseDistance(von.eucDistanceTo(nach));
			von = nach;			
		}	
		
		//add the startPoint to the Tour with distance to last Tour entry
		Point lastPoint = tour.getLastPoint();		
		tour.increaseDistance(lastPoint.eucDistanceTo(startPoint));
		tour.addPoint(startPoint);
		
		return tour;
	}
	
	/**
	 * 
	 * @param p is a Point to search for a NN in pList
	 * @param pList contains a LinkedList with Points
	 * @return return of a Point who is NN to Point p
	 */
	public static Point nnSearch(Point p, HashMap<Integer, Point> hashMap) {
		//for debugging
		//long zstVorher;
		//long zstNachher;
		//zstVorher = System.currentTimeMillis();
		
		//store the nearest Distance from Point p to nnPoint
		double nnDistance = 999999999.0;
		double pointDistance;
		//Stores the NN Point in nnPoint. Take the first Point of the pList
		Point nnPoint = p;
		
		//check if Point p is in the hashMap --> Yes: remove Point p from pList
		if(hashMap.containsKey(p.getIndexPointNumber())) {
			hashMap.remove(p.getIndexPointNumber());
		}
				
		//search for the nn
		Point value;
		
		
		//forward
//		for(Integer key : hashMap.keySet()) {
//			int test = hashMap.size() - key +1;
//			value = hashMap.get(test);		
//			pointDistance = p.eucDistanceTo(value);
//			if(pointDistance <= nnDistance) {
//			//Store the index of the nn of Point i in nnLink[i]
//				nnPoint = value;
//				nnDistance = pointDistance;
//			}
//		}
		
		//backwards
		ArrayList<Integer> keys = new ArrayList<Integer>(hashMap.keySet());
        for(int i=keys.size()-1; i>=0;i--){
			value = hashMap.get(i);	
			if(value == null) {
				int stop = 8;
			}
			pointDistance = p.eucDistanceTo(value);
			if(pointDistance <= nnDistance) {
			//Store the index of the nn of Point i in nnLink[i]
				nnPoint = value;
				nnDistance = pointDistance;
			}
        }
		
		
		
		//alternative for loop
		//for(Map.Entry<Integer, Point> entry : hashMap.entrySet()) {
			//value = entry.getValue();			
			//pointDistance = p.eucDistanceTo(value);
			//if(pointDistance <= nnDistance) {
			//Store the index of the nn of Point i in nnLink[i]
				//nnPoint = value;
				//nnDistance = pointDistance;
			//}
		//}	
		//zstNachher = System.currentTimeMillis();
		//System.out.println((zstVorher-zstNachher) + " milli sec.");
		return nnPoint;
	}
	public static int geoDistance(Point p1, Point p2) {
		//radius of the world
		final double r = 6378.388;
		//x Value is latitude; y value longitude
		//distance calculation
		
		double q1 = Math.cos(p1.getLongitudeValue() - p2.getLongitudeValue());
		double q2 = Math.cos(p1.getLatitudeValue() - p2.getLatitudeValue());
		double q3 = Math.cos(p1.getLatitudeValue() + p2.getLatitudeValue());
		
		int distance = (int) (r * Math.acos(0.5 * ((1.0 + q1) * q2 - (1.0 - q1) * q3)) +1.0);
		
		return distance;
	}
}
