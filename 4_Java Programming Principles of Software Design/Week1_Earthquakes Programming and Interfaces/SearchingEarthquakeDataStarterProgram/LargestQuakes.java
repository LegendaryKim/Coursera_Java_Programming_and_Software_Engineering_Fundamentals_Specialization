
/**
 * Write a description of LargestQuakes here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class LargestQuakes {
    
    public int indexOfLargest(ArrayList<QuakeEntry> data) {
        int largestIndex = 0;
        for (int q = 0; q < data.size(); q++) {
            QuakeEntry qe = data.get(q);
            if (qe.getMagnitude() > data.get(largestIndex).getMagnitude()) {
                largestIndex = q;
            }
        }
        return largestIndex;
    }
    
    public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData, int howMany) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for (int i =0; i < howMany; i++) {
            int largestIndex = indexOfLargest(quakeData);
            answer.add(quakeData.get(largestIndex));
            quakeData.remove(largestIndex);
            if (quakeData.size() == 0) break;
        }
        return answer;
    }
    
    public void findLargestQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedatasmall.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        
        int largestIndex = indexOfLargest(list);
        System.out.println("Index of Largest: " + largestIndex + " Magnitude: " + list.get(largestIndex).getMagnitude());
        
        ArrayList<QuakeEntry> large = getLargest(list, 50);
        for(int k=0; k < large.size(); k++){
            QuakeEntry entry = large.get(k);
            System.out.println(entry);
        }
    }
}
