import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class BabyBirths {
    public void printNames () {
        FileResource fr = new FileResource();
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            if (numBorn <= 100) {
                System.out.println("Name " + rec.get(0) +
                           " Gender " + rec.get(1) +
                           " Num Born " + rec.get(2));
            }
        }
    }

    public void totalBirths (FileResource fr) {
        int totalBirths = 0;
        int totalBoys = 0;
        int totalGirls = 0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            totalBirths += numBorn;
            if (rec.get(1).equals("M")) {
                totalBoys += numBorn;
            }
            else {
                totalGirls += numBorn;
            }
        }
        System.out.println("total births = " + totalBirths);
        System.out.println("female girls = " + totalGirls);
        System.out.println("male boys = " + totalBoys);
        
        int numberTotalNames = 0;
        int numberBoyNames = 0;
        int numberGirlNames = 0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            String gender = rec.get(1);
            numberTotalNames += 1;
            if (gender.equals("F")) {
                numberGirlNames += 1;
            } else {
                numberBoyNames +=1;
            }
        }
        System.out.println("the number of total names = " + numberTotalNames);
        System.out.println("the number of girl names  = " + numberGirlNames);
        System.out.println("the number of boy names = " + numberBoyNames);
        
    }
    public void testTotalBirths () {
        int year = 1905;
        FileResource fr = new FileResource("../us_babynames/us_babynames_by_year/yob" 
                                          + year +".csv");
        totalBirths(fr);
    }
    
    public int getRank(int year, String name, String gender) {
        FileResource fr = new FileResource("../us_babynames/us_babynames_by_year/yob" 
                                          + year +".csv");
        int currentRank = 0;
        for (CSVRecord record: fr.getCSVParser(false)) {
            String currentGender = record.get(1);
            if (currentGender.equals(gender)) {
                currentRank += 1;
                String currentName = record.get(0);
                if (currentName.equals(name)) {
                    return currentRank;
                }
            } 
        }
        return -1;        
    }
    public void testGetRank() {
        int year = 1971;
        String name = "Frank";
        String gender = "M";
        System.out.println("the result of getRank: " + getRank(year, name, gender)); 
    }
    
    public String getName(int year, int rank, String gender) {
        FileResource fr = new FileResource("../us_babynames/us_babynames_by_year/yob" 
                                           + year +".csv");
        int currentRank = 0;
        for (CSVRecord record: fr.getCSVParser(false)) {
            String currentGender = record.get(1);
            if (currentGender.equals(gender)) {
                currentRank += 1;
                if (currentRank == rank) {
                    return record.get(0);
                }
            } 
        }
        return "NO NAME";        
    }
    public void testGetName() {
        int year = 1982;
        int rank = 450;
        String gender = "M";        
        System.out.println("the result of getName: " + getName(year, rank, gender)); 
    }
    
    public String whatIsNameInYear(String name, int year, int newYear, String gender){
        int currentRank = getRank(year, name, gender);
        return getName(newYear, currentRank, gender);        
    }
    public void testWhatIsNameInYear() {
        int year = 1972;
        int newYear = 2014;
        String name = "Owen";
        String gender = "M";
        String newName = whatIsNameInYear(name, year, newYear, gender);
        if (gender.equals("F")) {
            System.out.println(name + " born in " + year + " would be " + newName
                               + " if she was born in " + newYear +".");
        } else {
            System.out.println(name + " born in " + year + " would be " + newName
                               + " if he was born in " + newYear +".");
        }
    }
    
    public int yearOfHighestRank(String name, String gender) {
        int highestRank = -1;
        int highestYear = -1; 
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            String fName = f.getName();
            int currYear = Integer.parseInt(fName.substring(3,7));
            int currRank = getRank(currYear, name, gender);
            if (highestRank == -1) {
                highestRank = currRank;
                highestYear = currYear;
            } else if ((currRank != -1) && (highestRank > currRank)) {
                highestRank = currRank;
                highestYear = currYear;
            }
        }
        return highestYear;
    }
    public void testYearOfHighestRank() {
        System.out.println(yearOfHighestRank("Mich", "M"));
    }
    
    public double getAverageRank(String name, String gender) {
        int sumRank = 0;
        int count = 0; 
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            String fName = f.getName();
            int currYear = Integer.parseInt(fName.substring(3,7));
            int currRank = getRank(currYear, name, gender);
            if (currRank != -1) {
                count += 1;
                sumRank += currRank;
            }
        }
        if (count != 0) {
            return (double) sumRank/count;
        } else {
            return -1.0;
        }
    }
    public void testGetAverageRank() {
        System.out.println(getAverageRank("Robert", "M"));
    }
    
    public int totalBirthsRankedHigher (int year, int rank, String gender) {
        int totalBirths = 0;
        int currentRank = 0;
        FileResource fr = new FileResource("../us_babynames/us_babynames_by_year/yob" 
                                           + year +".csv");                                           
        for (CSVRecord rec : fr.getCSVParser(false)) {
            if (rec.get(1).equals(gender)) {
                currentRank += 1;
                if (currentRank < rank) {
                    int numBorn = Integer.parseInt(rec.get(2));
                    totalBirths += numBorn;
                }
            }
        }
        return totalBirths;
    }
    public int getTotalBirthsRankedHigher(int year, String name, String gender) {
        int rank = getRank(year, name, gender);
        return totalBirthsRankedHigher(year, rank, gender);       
    }   
    public void testGetTotalBirthsRankedHigher() {
        int year = 1990;
        String name = "Drew";
        String gender = "M";
        System.out.println(getTotalBirthsRankedHigher(year, name, gender));        
    }
}
