
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;

public class Part1 {
    public void tester() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        for (CSVRecord record: parser) {
            System.out.print(record.get("Country") + " ");
            System.out.print(record.get("Exports") + " ");
            System.out.println(record.get("Value (dollars)"));
        }
    }    
    
    public String contryInfo(CSVParser parser, String countryofInterest) {
        for (CSVRecord record: parser) {
            String currentCountry = record.get("Country");
            if (currentCountry.equals(countryofInterest)){
                String output = record.get("Country") + ": "
                              + record.get("Exports") + ": "
                              + record.get("Value (dollars)");
                return output;
            }
        }
        return "NO RECORD";
    }
    public void testContryInfo() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        String country = "Nauru";
        System.out.println(contryInfo(parser, country));
    }

    public void listExportTwoProducts(CSVParser parser, 
                                      String exportItem1, 
                                      String exportItem2) {
        for (CSVRecord record: parser) {
            String exports = record.get("Exports");
            if (exports.contains(exportItem1) && 
                exports.contains(exportItem2)) {
                System.out.println(record.get("Country"));
            }
        }
    }
    public void testListExportTwoProducts() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        listExportTwoProducts(parser, "cotton", "flowers");
    }
    
    public int numberOfExporters(CSVParser parser, String exportItem) {
        int count = 0;
        for (CSVRecord record: parser) {
            if (record.get("Exports").contains(exportItem)) {
                count += 1;
            }
        }
        return count;
    }
    public void testNumberOfExporters() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        System.out.println(numberOfExporters(parser, "cocoa"));
    }
    
    public void bigExporters(CSVParser parser, String amount) {
        int thresholdLength = amount.length();
        for (CSVRecord record: parser) {
            String exportValue = record.get("Value (dollars)");
            if (exportValue.length() > thresholdLength) {
                System.out.print(record.get("Country") + " ");
                System.out.println(record.get("Value (dollars)"));
            }
        }
    }
    public void testbigExporters() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        bigExporters(parser, "$999,999,999,999");
    }    
}
