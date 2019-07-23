
/**
 * Write a description of TitleLastAndMagnitudeComparator here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry> {
     
    public int compare(QuakeEntry q1, QuakeEntry q2) {
        String last1 = q1.getInfo().substring(q1.getInfo().lastIndexOf(" ") + 1);
        String last2 = q2.getInfo().substring(q2.getInfo().lastIndexOf(" ") + 1);
        // System.out.println("last1: " + last1 + " last2: " + last2);
        int resultLast = last1.compareTo(last2);
        
        if (resultLast!=0) {
            return resultLast;
        } else {
            return Double.compare(q1.getMagnitude(), q2.getMagnitude());
        }

    } 

}
