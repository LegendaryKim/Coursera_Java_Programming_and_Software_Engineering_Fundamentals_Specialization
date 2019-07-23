
/**
 * Write a description of MarkovOne here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class EfficientMarkovModel extends AbstractMarkovModel {
    private int numKey;
    private HashMap<String, ArrayList<String>> followsMap;
    
    public EfficientMarkovModel(int N) {
        myRandom = new Random();
        numKey = N;
        followsMap = new HashMap<String, ArrayList<String>>();
    }
    
    public void setTraining(String s){
        myText = s.trim();
    }
    
    public void buildMap() {
        for (int k=0; k < myText.length() - numKey + 1; k ++) {
            String key = myText.substring(k, k + numKey);
            
            if (!followsMap.containsKey(key)) {
                ArrayList<String> follows = getFollows(key);
                followsMap.put(key, follows);                
            }
        }
    }
    
    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length() - numKey);
        String key = myText.substring(index, index + numKey);
        sb.append(key);
        buildMap();
        
        for (int k=0; k < numChars - numKey; k++) {
            ArrayList<String> follows = new ArrayList<String>();
            if (followsMap.containsKey(key)) {
                follows = followsMap.get(key);
            }
            if (follows.size() == 0) break;
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            key = key.substring(1) + next;
        }
        return sb.toString();
    }
    
    public void printHashMapInfo() {
        System.out.println(followsMap);
        System.out.println("size: " + followsMap.size());
    
        int maxSize = 0;
        String maxKey = "";
        for (String key: followsMap.keySet()) {
            ArrayList<String> follows = followsMap.get(key);
            if (follows.size() > maxSize) {
                maxSize = follows.size();
                maxKey = key;
            }
        }
        System.out.println("max key: " + maxKey);
        System.out.println("max size: " + maxSize);        
    }    
    
    public String toString() {
        return "Efficient MarkovModel of order " + numKey;
    }
}
