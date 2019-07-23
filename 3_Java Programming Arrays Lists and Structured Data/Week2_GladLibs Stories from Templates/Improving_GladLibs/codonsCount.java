
/**
 * Write a description of CodonCount here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;

public class codonsCount {
    private HashMap<String,Integer> codonsCount;
    
    public codonsCount() {
        codonsCount = new HashMap<String,Integer>();
    }
    
    public void buildCodonMap(int start, String dna) {
        codonsCount.clear();
        
        for (int i = start; i < dna.length() - 2 ; i += 3) {
            String codon = dna.substring(i, i+3);
            codon = codon.toUpperCase();
            if (!codonsCount.containsKey(codon)) {
                codonsCount.put(codon, 1);
            } else {
                codonsCount.put(codon, codonsCount.get(codon) + 1);
            }
        }
    }
    
    public String getMostCommonCodon() {
        int maxCount = 0;
        String maxCodon = null;
        for (String codon: codonsCount.keySet()) {
            int currentCount = codonsCount.get(codon);
            if (maxCount < currentCount) {
                maxCount = currentCount;
                maxCodon = codon;
            }
        }
        return maxCodon;
    }
    
    public void printCodonCounts(int start, int end) {
        for (String codon: codonsCount.keySet()) {
            int currentCount = codonsCount.get(codon);
            if (currentCount >= start && currentCount <= end) {
                System.out.println(codon + "\t" + currentCount);
            }
        }
    }
    
    public void tester() {
        FileResource fr = new FileResource();
        String dna = fr.asString().trim();
        for (int start = 0; start < 3; start++) {
            System.out.println("starting with : "  + start);
            buildCodonMap(start, dna);
            String mostCommonCodon = getMostCommonCodon();
            System.out.println("most common codon: "
                               + mostCommonCodon + "\t" + codonsCount.get(mostCommonCodon));
            System.out.println("number of unique codon: " + codonsCount.size());
            printCodonCounts(7,7);
        }
        
    }
}
