package dataStructure_TSP;

import java.io.BufferedReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


/**
 * Reads in the Data of an TSP-File
 * @author Thomas
 *
 */
public class DateiEinlesen {
	/**
	 * 
	 * @param data
	 * @param pointNamed
	 * @return
	 * @throws IOException
	 */
    public static double[][] fileToCoordinates(File data, String format) throws IOException {
        String line;
        BufferedReader in;
        int i = 0;
        int size = 0;
        String[] inputLines;
        
        // get the size of the problem
        try {
            in = new BufferedReader(new FileReader(data));
            line = in.readLine();
            while (line != null) {
                line = in.readLine();
                i++;
            }
            size = i;
            in.close();
        }
        catch (IOException e) {
            System.out.println("Fehler: IOException");
        }
        
        
        in = new BufferedReader(new FileReader(data));
        line = in.readLine();
        inputLines = new String[size];
        i = 0;
        
        //store every row in an String-Array
        while (line != null) {
            inputLines[i] = line;
            line = in.readLine();
            i++;
        }
        in.close();
        
        //Array to store the Data of the x,y Coordinates
        double input[][] = new double[inputLines.length][2];
        //Convert the String-values into an Double Array and store the Coordinates
            for (int j = 0; j < inputLines.length; j++) {
                Pattern p = Pattern.compile("[+\\-]?(?:0|[1-9]\\d*)(?:(\\,|\\.)\\d*)?(?:[eE][+\\-]?\\d+)?");
                Matcher m = p.matcher(inputLines[j]);
                int k = -1;
                while (m.find()) {
                    if (k ==-1){
                    }else{
                        input[j][k] = Double.parseDouble(m.group().replace(",","."));
                    }
                    k++;
                }
            }
         
            
         //conversion from GeoCoordinates to Longitude and Latitude Value to calculate the geoDistance. x Coordinate is the latitude Value; y Coordinate is the longitude value
         if(format == "geo") {
        	 final double pi = Math.PI;
        	 for (int l = 0; l < inputLines.length; l++) {
        		 //x value into latitude
        		 double x = input[l][0];
        		 //(int) (x+0,5)
        		 int deg = (int) x;
     			 double min = x - deg;
     			 input[l][0] = pi * (deg + 5.0 * min / 3.0) / 180.0;
        		 //y value into longitude
     			 double y = input[l][1];
     			 //(int) (y+0,5)
     			 deg = (int) y;
    			 min = y - deg;
    			 input[l][1] = pi * (deg + 5.0 * min / 3.0) / 180.0;
        	 }
         }
     return input;
    }  
}
