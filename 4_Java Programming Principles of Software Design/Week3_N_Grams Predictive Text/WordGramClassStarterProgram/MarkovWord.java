
/**
 * Write a description of MarkovWord here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class MarkovWord implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    private int myOrder;
    
    public MarkovWord(int order) {
        myOrder = order;
        myRandom = new Random();
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text) {
        myText = text.split("\\s+");
    }
    
    private int indexOf(String[] words, WordGram target, int start) {
        for (int k=start; k < words.length - myOrder; k++) {
            if (target.equals(new WordGram(words, k, myOrder))) {
                return k;
            }
        }
        return -1;
    }
    
    private ArrayList<String> getFollows(WordGram kGram) {
        ArrayList<String> follows = new ArrayList<String>(); 
        int pos = 0;
        while (pos < myText.length) {
            int start = indexOf(myText, kGram, pos);
            // System.out.println("kGram: " + kGram);
            // System.out.println("start: " + start);
            if (start == -1) break;
            if (start + myOrder > myText.length - 1) break;
            
            String next = myText[start + myOrder];
            follows.add(next);
            pos = start + myOrder;
        }
        return follows;
    }
    
    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length - myOrder);  // random word to start with
        WordGram keys = new WordGram(myText, index, myOrder);
        sb.append(keys);
        sb.append(" ");
        // System.out.println("keys: " + keys.toString());
        for(int k=0; k < numWords-1; k++){
            // System.out.println("k :" + k);
            ArrayList<String> follows = getFollows(keys);
            // System.out.println("follows: " + follows);

            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            keys = keys.shiftAdd(next);
        }
        return sb.toString().trim();
    }    
    
}
