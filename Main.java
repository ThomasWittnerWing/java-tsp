package dataStructure_TSP;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import Algorithms.NN_Heuristik_HashMap;
//import smile.neighbor.KDTree;
//import smile.neighbor.Neighbor;
import net.sf.javaml.core.kdtree.*;

public class Main {
	/**
	 * 	
	 * @param args
	 */
    public static void main(String[] args) { 
    	long zstVorher;
    	long zstNachher;    	
    	HashMap<Integer, Point> hashMap;
        String dateiName = "C:\\Users\\Thomas\\Desktop\\berlin52.tsp"; 
//        String dateiName = "C:\\Users\\Thomas\\Desktop\\pla85900.tsp"; 
//        String dateiName = "C:\\Users\\Thomas\\Desktop\\pla85900.tsp"; 
        File f = new File(dateiName);
        double [][] d = null;
        
    	zstVorher = System.currentTimeMillis();
        
        try{
        	d = DateiEinlesen.fileToCoordinates(f, "euc"); 
        }
        catch (IOException e) { 
            e.printStackTrace(); 
        }
          Tour tour = MachsmalmitHashmap(d);
//        Tour tour = MitKdTree(d, d[0]);
//        Tour tour = FlexibleTree(d, d[9]);
        
//        for (int i = 0; i < tour.getTourSize(); i++) {
//			Point pointHash = tour.getTour().get(i);
//			Point pointTree = tour2.getTour().get(i);
//			if(!(pointHash.xCoordinate == pointTree.xCoordinate && pointHash.yCoordinate == pointTree.yCoordinate)) {
//		        System.out.println("Points are not the same PointHash: " + pointHash.toString() + " PointTree: " + pointTree.toString()); 
//			}
//			
//		}
        
        //distance of the tour
       // double laenge = tour.getDistance();       
        //System.out.println("Tour distance:" + laenge); 
        double laenge = tour.getDistance(); 
        System.out.println("Tour2 distance:" + laenge);  
        GetLongesDinstancesOfTour(tour);
        
        
        
        //Time for the hole calculation
        zstNachher = System.currentTimeMillis();
        System.out.println("Zeit benötigt: " + ((zstNachher - zstVorher)/1000) + " sec");
    }
    
    private static Tour MachsmalmitHashmap(double [][] d) {

        //create a HashMap with all Points for a fast NN Search
    	HashMap<Integer, Point> hashMap = NN_Heuristik_HashMap.createHashMap(d);    
        
        //construct the Tour with NN Heuristic
        Tour tour = NN_Heuristik_HashMap.nnHeuristik(hashMap);

        //print the Tour
        LinkedList<Point> t = tour.getTour();
        for( int i = 0; i < t.size(); i++ ){
        	  Point tourPoint = t.get(i);
        	  System.out.println("-"+ tourPoint.getIndexPointNumber()+ "-");
        }   
        
        return tour;
        
    }
    
//    private static Tour MitKdTree(double [][] d, double[] startpoint) {
//
//    	Tour tour = new Tour();
//    	KDTree<double[]> tree = new KDTree<>(d, d);
//    	tree.setIdenticalExcluded(true);
//    	Point point = null;
//    	int maxloops = 0;
//    	
////    	for (double[] ed : d) {
////    		Neighbor<?, double[]> nearest = tree.nearest(ed);
////    		point = new Point(nearest.index, nearest.value[0], nearest.value[1]);
////			tour.addPoint(point);
////		}
//    	
//    	
//    	
//    	
//    	while(tour.getTourSize() < d.length) {
////    		Neighbor<?, double[]> nearest = tree.nearest(startpoint);
////			point = new Point(nearest.index, nearest.value[0], nearest.value[1]);
////			tour.addPoint(point);
////			startpoint = nearest.value;
//			
//			
//    		boolean nopointadded = true;
//    		int loops = 1;
//    		double logloops = 0;
//    		
//    		
//    		while(nopointadded) {
//    			logloops = Math.pow(10, loops);
//    			Neighbor<?, double[]>[] neighbors = tree.knn(startpoint, (int)logloops);
//    			loops++;
//    			
//    			for (Neighbor<?, double[]> neighbor : neighbors) {
//    				point = new Point(neighbor.index, neighbor.value[0], neighbor.value[1]);
//    				if(!tour.containsPoint(point)) {
//    					tour.addPoint(point);
//    					startpoint = neighbor.value;
//    					nopointadded = false;
//    				}
//				}
//    			if(maxloops < loops) {
//    				maxloops = loops;
//    				System.out.println("loops-"+ loops + "-");
//    				
//    			}
//    			
//    		}
//    	}
//    	
//    	
//
////        //print the Tour
////        LinkedList<Point> t = tour.getTour();
////        for( int i = 0; i < t.size(); i++ ){
////        	  Point tourPoint = t.get(i);
////        	  System.out.println("-"+ tourPoint.getIndexPointNumber()+ "-");
////        }   
////        
//        return tour;
//        
//    }

    private static Tour FlexibleTree(double [][] d, double[] startpoint) {
    	Tour tour = new Tour();
    	Point point = null;
    	KDTree tree = new KDTree(2);
    	for (double[] e : d) {
    		tree.insert(e, e);
		}
   	
    	while(tour.getTourSize() < d.length) {
    			Object[] nearest =  tree.nearest(startpoint, 1);
    			point = new Point(1, ((double[])nearest[0])[0], ((double[])nearest[0])[1]);
    			tour.addPoint(point);
    			startpoint = (double[])nearest[0];
    			tree.delete((double[])nearest[0]);
    	}
    	
    	Point previous = null;
    	for (Point e : tour.getTour()) {
			if(previous == null) {
				previous = e;
				continue;
			}
			tour.increaseDistance(previous.eucDistanceTo(e));
			previous = e;
		}
    	
    	//zurück zum startpunkt
    	tour.increaseDistance(tour.getFirstPoint().eucDistanceTo(tour.getLastPoint()));
    	tour.addPoint(tour.getFirstPoint());
    	return tour;
    }
    
    private static void GetLongesDinstancesOfTour(Tour tour) {
    	Point previous = null;
    	double longest = 0;
    	Point first = null;
    	Point second = null;
    	for (Point e : tour.getTour()) {
			if(previous == null) {
				previous = e;
				continue;
			}
			if(longest < previous.eucDistanceTo(e)) {
				longest = previous.eucDistanceTo(e);
				first = previous;
				second = e;
			}
			previous = e;
		}

    	System.out.println("first : " + first);
    	System.out.println("second : " + second);
    }
}
