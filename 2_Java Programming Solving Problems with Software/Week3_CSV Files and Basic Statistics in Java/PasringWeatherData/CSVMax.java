import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class CSVMax {
    public CSVRecord hottestHourInFile(CSVParser parser) {
        //start with largestSoFar as nothing
        CSVRecord largestSoFar = null;
        //For each row (currentRow) in the CSV File
        for (CSVRecord currentRow : parser) {
            // use method to compare two records
            largestSoFar = getLargestOfTwo(currentRow, largestSoFar);
        }
        //The largestSoFar is the answer
        return largestSoFar;
    }

    public void testHottestInDay () {
        FileResource fr = new FileResource();
        CSVRecord largest = hottestHourInFile(fr.getCSVParser());
        System.out.println("hottest temperature was " + largest.get("TemperatureF") +
                   " at " + largest.get("TimeEST"));
    }

    public CSVRecord hottestInManyDays() {
        CSVRecord largestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        // iterate over files
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            // use method to get largest in file.
            CSVRecord currentRow = hottestHourInFile(fr.getCSVParser());
            // use method to compare two records
            largestSoFar = getLargestOfTwo(currentRow, largestSoFar);
        }
        //The largestSoFar is the answer
        return largestSoFar;
    }

    public CSVRecord getLargestOfTwo (CSVRecord currentRow, CSVRecord largestSoFar) {
        //If largestSoFar is nothing
        if (largestSoFar == null) {
            largestSoFar = currentRow;
        }
        //Otherwise
        else {
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            double largestTemp = Double.parseDouble(largestSoFar.get("TemperatureF"));
            //Check if currentRow’s temperature > largestSoFar’s
            if (currentTemp > largestTemp) {
                //If so update largestSoFar to currentRow
                largestSoFar = currentRow;
            }
        }
        return largestSoFar;
    }

    public void testHottestInManyDays () {
        CSVRecord largest = hottestInManyDays();
        System.out.println("hottest temperature was " + largest.get("TemperatureF") +
                   " at " + largest.get("DateUTC"));
    }
    
    
    
    public CSVRecord coldestHourInFile(CSVParser parser) {
        CSVRecord smallestSoFar = null;
        for (CSVRecord currentRow : parser) {
            smallestSoFar = getSmallestOfTwo(currentRow, smallestSoFar);
        }
        return smallestSoFar;
    }    
    public CSVRecord getSmallestOfTwo (CSVRecord currentRow, CSVRecord smallestSoFar) {
        if (smallestSoFar == null) {
            smallestSoFar = currentRow;
        }
        else {
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            double smallestTemp = Double.parseDouble(smallestSoFar.get("TemperatureF"));
            if (currentTemp < smallestTemp && currentTemp > -100) {
                smallestSoFar = currentRow;
            }
        }
        return smallestSoFar;
    }    
    public void testColdestHourInFile() {
        FileResource fr = new FileResource();
        CSVRecord smallest = coldestHourInFile(fr.getCSVParser());
        System.out.println("coldest temperature was " + smallest.get("TemperatureF") +
                   " at " + smallest.get("DateUTC"));
    }
    
    public String fileWithColdestTemperature() {
        CSVRecord smallestSoFar = null;
        String coldestFileName = null;        
        DirectoryResource dr = new DirectoryResource();
        
        // iterate over files
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = coldestHourInFile(fr.getCSVParser());

            
            if (smallestSoFar == null) {
                smallestSoFar = currentRow;
                coldestFileName = f.getName();
            }
            else {
                double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                double smallestTemp = Double.parseDouble(smallestSoFar.get("TemperatureF"));
                if (currentTemp < smallestTemp && currentTemp > -100) {
                    smallestSoFar = currentRow;
                    coldestFileName = f.getName();
                }
            }
        }
        return coldestFileName;
    }
    public void testFileWithColdestTemperature() {
        String fileColdest = fileWithColdestTemperature();
        System.out.println("Coldest day was in file " + fileColdest);
        
        FileResource fr = new FileResource();
        CSVRecord smallest = coldestHourInFile(fr.getCSVParser());
        System.out.println("coldest temperature was " + smallest.get("TemperatureF") +
                   " at " + smallest.get("DateUTC"));
    }
    
    public CSVRecord lowestHumidityInFile(CSVParser parser) {
        CSVRecord smallestSoFar = null;
        for (CSVRecord currentRow : parser) {
            String humidity = currentRow.get("Humidity");
            if (!humidity.equals("N/A")) {
                smallestSoFar = getSmallestOfTwo2(currentRow, smallestSoFar, "Humidity");
            }
        }
        return smallestSoFar;
    }
    public CSVRecord getSmallestOfTwo2 (CSVRecord currentRow, CSVRecord smallestSoFar, 
                                        String valueOfInterest) {
        if (smallestSoFar == null) {
            smallestSoFar = currentRow;
        }
        else {
            double currentTemp = Double.parseDouble(currentRow.get(valueOfInterest));
            double smallestTemp = Double.parseDouble(smallestSoFar.get(valueOfInterest));
            if (currentTemp < smallestTemp) {
                smallestSoFar = currentRow;
            }
        }
        return smallestSoFar;
    }       
    public void testLowestHumidityInFile() {
       FileResource fr = new FileResource();
       CSVRecord smallest = lowestHumidityInFile(fr.getCSVParser());
       System.out.println("lowest humidity was " + smallest.get
       ("Humidity") + " at " + smallest.get("DateUTC"));
    }

    public CSVRecord lowestHumidityInManyDays() {
        CSVRecord smallestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        // iterate over files
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            // use method to get largest in file.
            CSVRecord currentRow = hottestHourInFile(fr.getCSVParser());
            // use method to compare two records
            smallestSoFar = getSmallestOfTwo2(currentRow, smallestSoFar, "Humidity");
        }
        //The largestSoFar is the answer
        return smallestSoFar;
    }
    public void testlowestHumidityInManyDays() {
       CSVRecord smallest = lowestHumidityInManyDays();
       System.out.println("lowest humidity was " + smallest.get
       ("Humidity") + " at " + smallest.get("DateUTC"));
    }

    public double averageTemperatureFile(CSVParser parser) {
        int count = 0;
        double sumTemperature = 0;
        
        for (CSVRecord currentRow: parser) {
            double temperature = Double.parseDouble(currentRow.get("TemperatureF"));
            sumTemperature += temperature;
            count += 1;
        }
        return sumTemperature/count;
    }
    public void testAverageTemperatureFile() {
       FileResource fr = new FileResource();
       double avgTemperature = averageTemperatureFile(fr.getCSVParser());
       System.out.println("Average temperature in file is " + avgTemperature);
    }
    
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value) {
        int count = 0;
        double sumTemperature = 0;
        for (CSVRecord currentRow: parser) {
            String humidity = currentRow.get("Humidity");
            if ((!humidity.equals("N/A")) && (Double.parseDouble(humidity) >= value)) {
                double temperature = Double.parseDouble(currentRow.get("TemperatureF"));
                sumTemperature += temperature;
                count += 1;
            }
        }
        
        return sumTemperature/count;
    }
    public void testAverageTemperatureWithHighHumidityInFile() {
       FileResource fr = new FileResource();
       double avgTemperature = averageTemperatureWithHighHumidityInFile(fr.getCSVParser(), 80);
       if (avgTemperature==(0/0.)) { 
           System.out.println("No temperatures with that humidity");
       } else {
           System.out.println("Average temperature in file is " + avgTemperature);
       }
    }    
}
