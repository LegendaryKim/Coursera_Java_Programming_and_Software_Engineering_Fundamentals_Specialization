
/**
 * Write a description of WordFrequencies here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.ArrayList;
import edu.duke.*;

public class WordFrequencies {
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;
    
    public WordFrequencies() {
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }
    
    public void findUnique() {
        myWords.clear();
        myFreqs.clear();
        
        FileResource resource = new FileResource();
        for (String word: resource.words()) {
            word = word.toLowerCase();
            
            int index = myWords.indexOf(word);
            if (index == -1) {
                myWords.add(word);
                myFreqs.add(1);
            } else {
                int freq = myFreqs.get(index);
                myFreqs.set(index, freq+1);
            }
        }
    }
    public int findIndexOfMax() {
        int maxFreqs = 0;
        int maxIndex = 0;
        for (int i=0; i < myFreqs.size(); i++) {
            int currentFreqs = myFreqs.get(i);
            if (maxFreqs < currentFreqs) {
                maxFreqs = currentFreqs;
                maxIndex = i;
            }
        }
        return maxIndex;
    }    
    public void tester() {
        findUnique();
        System.out.println("Number of unique words: " + myWords.size());
        // for (int i=0; i < myWords.size(); i++) {
        //     System.out.println(myFreqs.get(i) + " " + myWords.get(i));
        // }
        int maxIndex = findIndexOfMax();
        System.out.println("The word that occurs most often and its count are: " 
                          + myWords.get(maxIndex) + " " + myFreqs.get(maxIndex));
        
    }
    

    
}
