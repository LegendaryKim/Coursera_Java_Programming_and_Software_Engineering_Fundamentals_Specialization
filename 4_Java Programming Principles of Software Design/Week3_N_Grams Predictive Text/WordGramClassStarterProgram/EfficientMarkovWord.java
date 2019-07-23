
/**
 * Write a description of MarkovWord here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class EfficientMarkovWord implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    private int myOrder;
    private HashMap<WordGram, ArrayList<String>> followsMap;
    
    public EfficientMarkovWord(int order) {
        myOrder = order;
        myRandom = new Random();
        followsMap = new HashMap<WordGram, ArrayList<String>>();
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text) {
        myText = text.split("\\s+");
        // buildMap();
    }
    
    private int indexOf(String[] words, WordGram target, int start) {
        for (int k=start; k < words.length - myOrder; k++) {
            if (target.equals(new WordGram(words, k, myOrder))) {
                return k;
            }
        }
        return -1;
    }
    
    public void buildMap() {
        for (int k=0; k < myText.length - myOrder + 1; k++) {
            WordGram keyGram = new WordGram(myText, k, myOrder);
            //int key = keyGram.hashCode();
            if (!followsMap.containsKey(keyGram)) {
                //System.out.println("keyGram: " + keyGram);
                //System.out.println("key: " + key);
                // ArrayList<String> follows = getFollows(keyGram);
                ArrayList<String> follows = getFollowsEfficient(keyGram, k);
                followsMap.put(keyGram, follows);
            }
        }
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
    
    private ArrayList<String> getFollowsEfficient(WordGram kGram, int posInput) {
        ArrayList<String> follows = new ArrayList<String>(); 
        int pos = posInput;
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
        buildMap();
        // printHashMapInfo();
        
        // System.out.println("keys: " + keys.toString());
        for(int k=0; k < numWords-1; k++){
            // System.out.println("k :" + k);
            // ArrayList<String> follows = getFollows(keys);
            ArrayList<String> follows = new ArrayList<String>();
            if (followsMap.containsKey(keys)) {
                follows = followsMap.get(keys);
            }
            if (follows.size() == 0) break;
            //System.out.println("follows: " + follows);
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            keys = keys.shiftAdd(next);
        }
        return sb.toString().trim();
    }    
    
    
    public void printHashMapInfo() {
        System.out.println(followsMap);
        System.out.println("size: " + followsMap.size());
        
        int maxSize = 0;
        String maxKey = "";
        for (WordGram key: followsMap.keySet()) {
            ArrayList<String> follows = followsMap.get(key);
            if (follows.size() > maxSize) {
                maxSize = follows.size();
                maxKey = key.toString();
            }
        }
        System.out.println("max key: " + maxKey);
        System.out.println("max size: " + maxSize);        
    }
}
