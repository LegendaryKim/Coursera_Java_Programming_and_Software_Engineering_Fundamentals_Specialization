
/**
 * Write a description of WordLengths here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class WordLengths {
    public void countWordLengths(FileResource resource, int[] counts) {
        for (String word: resource.words()) {
            int length = word.length();
            boolean firstLetter = Character.isLetter(word.charAt(0));
            boolean lastLetter = Character.isLetter(word.charAt(length - 1));
            if (!firstLetter) length --;
            if (!lastLetter) length --;
            
            if (length >= counts.length) {                 
                length = counts.length - 1;       
            }      
            if (length > 0 ) {            
                counts[length] ++;        
            }       
            System.out.println("word: " + word+ " & length: " + length);
        }
        for (int i=0; i < counts.length; i++) {
            System.out.println("Length of word: " + i+ " & counts: " + counts[i]);
        }
        System.out.println("index of the largest element: " + indexOfMax(counts));
    }
    public void testCountWordLengths() {
        FileResource file = new FileResource("manywords.txt");
        int[] counts = new int[31];
        countWordLengths(file, counts);
        
    }
    
    public int indexOfMax(int[] values) {
        int maxIndex = 0;
        for (int i=0; i < values.length; i++) {
            if (values[maxIndex] < values[i]) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }
    
}
