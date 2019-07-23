
/**
 * Write a description of MarkovOne here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class MarkovModel extends AbstractMarkovModel {
    private int numKey;
        
    public MarkovModel(int N) {
        myRandom = new Random();
        numKey = N;
    }
    
    public void setTraining(String s){
        myText = s.trim();
    }
       
    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length() - numKey);
        String key = myText.substring(index, index + numKey);
        sb.append(key);
        
        for (int k=0; k < numChars - numKey; k++) {
            ArrayList<String> follows = getFollows(key);
            if (follows.size() == 0) break;
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            key = key.substring(1) + next;
        }
        return sb.toString();
    }
    
    public String toString() {
        return "MarkovModel of order " + numKey;
    }
}
