/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PurpleAmericaPart5;

/**
 *
 * @author ianut
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import edu.princeton.cs.algs4.*;
import java.awt.Color;


public class Main {

    
    public static HashMap<String, HashMap<String, ArrayList<Double>>> parseGeographyFile(String[] geographyFileContents) {
        HashMap<String, HashMap<String, ArrayList<Double>>> countiesTotal = new HashMap<String, HashMap<String, ArrayList<Double>>>();
         
       
        String line1 = geographyFileContents[0];
        String line2 = geographyFileContents[1];
        String[] numbersMin = line1.split("   ");
        Double xMin = Double.parseDouble(numbersMin[0]);
        Double yMin = Double.parseDouble(numbersMin[1]);
        String[] numbersMax = line2.split("   ");
        Double xMax = Double.parseDouble(numbersMax[0]);
        Double yMax = Double.parseDouble(numbersMax[1]);
        
        for(int i = 4 ; i < geographyFileContents.length ; i++) {
            HashMap<String, ArrayList<Double>> countiesXY = new HashMap<String, ArrayList<Double>>();
            String county = geographyFileContents[i];
            i = i + 1; //skip to the next line
            
            String state = geographyFileContents[i];
            i = i + 1;
            
            int numCoordinates = Integer.parseInt(geographyFileContents[i]);
            i = i + 1;
            
            ArrayList<Double> x = new ArrayList<Double>();
            ArrayList<Double> y = new ArrayList<Double>();
            
            for(int j = 0 ; j < numCoordinates ; j++) {
                String xy = geographyFileContents[i];
                i = i + 1;
                String[] xyArray = xy.split("   ");
                Double latitude = (Double.parseDouble(xyArray[0]) - xMin) / (xMax - xMin);
                Double longitude = (Double.parseDouble(xyArray[1]) - yMin) / (yMax - yMin);
                
                x.add(latitude);
                y.add(longitude);
            }
            
            countiesXY.put("x", x);
            countiesXY.put("y",y);
            System.out.println(countiesXY);
            System.out.println(county);
            System.out.println(state);
            countiesTotal.put(county + "-" + state, countiesXY); // Montgomery-PA-x [x1,x2,x3...]
            
            
            
            
        }
        System.out.println(countiesTotal);
        return countiesTotal;
    }
    /**
     * @param args the command line arguments
     */
    
    /**
     * Read a text file, line by line, into an array of Strings
     * Adapted from https://www.journaldev.com/709/java-read-file-line-by-line
     * @param filePath the full or relative path to the file to be read
     * @param contents an array of Strings, to be populated with for each line read in the file
     * @return the contents parameter, which now contains an array of strings corresponding to the lines read from the file
     * @throws IOException if the file cannot be read (i.e., if it does not exist, or if the user does not have the required permissions to read the file)
     */
    public static String[] readFile(String filePath) throws IOException {
        ArrayList<String> contents = new ArrayList<String>();
        
        /* https://www.journaldev.com/709/java-read-file-line-by-line */
        BufferedReader reader;
        FileReader fileReader;
       
        fileReader = new FileReader(filePath);
        reader = new BufferedReader(fileReader);
            
        String line = reader.readLine();
            
        while(line != null) {
            contents.add(line);
            line = reader.readLine();
        }
        
        reader.close();
        
        String[] result = new String[contents.size()];
        result = contents.toArray(result);
        return result;
    }
    
    
    public static double[] toArray(ArrayList<Double> arr) { 
        // Make an array big enough to hold everything in the ArrayList
        double[] result = new double[arr.size()];
        
        // now copy each value!
        for(int i = 0; i < arr.size(); i++) {
            result[i] = arr.get(i);
        }
        
        return result;
}
    
    public static void readNumbers(String[] geographyFileContents) {
        String line1 = geographyFileContents[0];
        String line2 = geographyFileContents[1];
        String[] numbersMin = line1.split("   ");
        Double xMin = Double.parseDouble(numbersMin[0]);
        Double yMin = Double.parseDouble(numbersMin[1]);
        String[] numbersMax = line2.split("   ");
        Double xMax = Double.parseDouble(numbersMax[0]);
        Double yMax = Double.parseDouble(numbersMax[1]);
        System.out.println(Arrays.toString(numbersMin));
        System.out.println(Arrays.toString(numbersMax));
        System.out.println(xMin);
        System.out.println(yMin);
        System.out.println(xMax);
        System.out.println(yMax);
    }
    
    public static void drawRegion(ArrayList<Double> x , ArrayList<Double> y) {
        double[] xArr = toArray(x);
        double[] yArr = toArray(y);
        
        //for(int i = 0 ; i < xArr.length ; i++) {
            StdDraw.filledPolygon(xArr, yArr);
        //}
        
    }
    public static void main(String[] args) {
        // TODO code application logic here
        String regionPath = "data/USA-county.txt";
        
        String[] regionContents = new String[0];
        
        try {
            regionContents = readFile(regionPath);
        } catch(IOException e) {
            e.printStackTrace(System.err);
            System.exit(-1);
        }
        HashMap<String, HashMap<String, ArrayList<Double>>> counties = parseGeographyFile(regionContents);
        
        //Arrays.sort(counties.keySet().toArray());
        for(String region: counties.keySet()) {
           
            
            
            //System.out.println(region);
            
            
            HashMap<String, ArrayList<Double>> data = counties.get(region);
            ArrayList<Double> dataX = data.get("x");
            ArrayList<Double> dataY = data.get("y");
            //System.out.println(dataX);
            //System.out.println(dataY);
            //System.out.println(data.toString());
            drawRegion(dataX,dataY);
            
            
        
        }
       /* 
        int red = 0;
        int green = 0;
        int blue = 0;
        
        
        Color color = new Color(red,green,blue);
        
        StdDraw.setPenRadius();
        StdDraw.setPenColor(color);
        
        readNumbers(regionContents);
        */
    }
}
