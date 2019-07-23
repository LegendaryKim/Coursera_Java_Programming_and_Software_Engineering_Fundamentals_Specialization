
/**
 * Write a description of CharactersInPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.ArrayList;
import edu.duke.*;

public class CharactersInPlay {
    private ArrayList<String> nameChar;
    private ArrayList<Integer> freqChar;
    
    public CharactersInPlay() {
        nameChar = new ArrayList<String>();
        freqChar = new ArrayList<Integer>();
    }
    
    public void update(String person) {
        int index = nameChar.indexOf(person);
        if (index == -1) {
            nameChar.add(person);
            freqChar.add(1);
        } else {
            int freq = freqChar.get(index);
            freqChar.set(index, freq+1);
        }
    }

    public void findAllCharacters() {
        nameChar.clear();
        freqChar.clear();
               
        FileResource resource = new FileResource();
        for (String line: resource.lines()) {
            int periodIndex = line.indexOf(".");
            if (periodIndex != -1) {
                String name = line.substring(0, periodIndex);
                update(name);
            }
        }
    }
    
    public int findIndexOfMax() {
        int maxFreqs = 0;
        int maxIndex = 0;
        for (int i=0; i < freqChar.size(); i++) {
            int currentFreqs = freqChar.get(i);
            if (maxFreqs < currentFreqs) {
                maxFreqs = currentFreqs;
                maxIndex = i;
            }
        }
        return maxIndex;    
    }
    
    public void tester() {
        findAllCharacters();
        System.out.println("Number of unique characters: " + nameChar.size());
        // for (int i=0; i < nameChar.size(); i++) {
        //     System.out.println(nameChar.get(i) + " " + freqChar.get(i));
        // }        
        charactersWithNumParts(10,15);
        int maxIndex = findIndexOfMax();
        System.out.println("The word that occurs most often and its count are: " 
                          + nameChar.get(maxIndex) + " " + freqChar.get(maxIndex));        
    }
    
    public void charactersWithNumParts(int num1, int num2) {
        for (int i=0; i < nameChar.size(); i++) {
            int freq = freqChar.get(i);
            if (freq >= num1 && freq <= num2) {
                System.out.println(nameChar.get(i) + " " + freq);
            }            
        }  
    }
}
