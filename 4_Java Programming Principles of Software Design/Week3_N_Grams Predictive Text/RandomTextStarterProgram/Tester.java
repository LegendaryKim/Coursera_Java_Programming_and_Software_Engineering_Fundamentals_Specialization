
/**
 * Write a description of Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;

public class Tester {
    public void testGetFollows() {
        MarkovOne markov = new MarkovOne();
        String test = "this is a test yes this is a test.";
        markov.setTraining(test);
        String key = "t.";
        ArrayList<String> follows = markov.getFollows(key);
        System.out.println("key: " + key + " " + follows);
    }
    
    public void testGetFollowsWithFile() {
        MarkovOne markov = new MarkovOne();
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        markov.setTraining(st);
        String key = "he";
        ArrayList<String> follows = markov.getFollows(key);
        System.out.println("key: " + key + " " + follows);
        System.out.println("length: " + follows.size());
    }
}
