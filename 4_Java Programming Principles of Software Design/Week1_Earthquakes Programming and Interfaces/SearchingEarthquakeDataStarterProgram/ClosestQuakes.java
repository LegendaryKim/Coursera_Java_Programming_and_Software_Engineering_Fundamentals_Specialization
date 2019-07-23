
/**
 * Find N-closest quakes
 * 
 * @author Duke Software/Learn to Program
 * @version 1.0, November 2015
 */

import java.util.*;

public class ClosestQuakes {
    public ArrayList<QuakeEntry> getClosest(ArrayList<QuakeEntry> quakeData, Location current, int howMany) {
        ArrayList<QuakeEntry> ret = new ArrayList<QuakeEntry>();
        for (int i = 0; i < howMany; i++) {
            int minDistIdx = 0;
            for (int q = 0; q < quakeData.size(); q++) {
                QuakeEntry qe = quakeData.get(q);
                //System.out.println(" -> " + qe);
                //System.out.println("distanceTo :" + qe.getLocation().distanceTo(current));
                //System.out.println("distanceTo2 :" + quakeData.get(minDistIdx).getLocation().distanceTo(current));
                if (qe.getLocation().distanceTo(current) < 
                    quakeData.get(minDistIdx).getLocation().distanceTo(current)) {
                    minDistIdx = q;
                }
            }
            ret.add(quakeData.get(minDistIdx));
            quakeData.remove(minDistIdx);
            if (quakeData.size() == 0) break;
        }
        return ret;
    }

    public void findClosestQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size());

        Location jakarta  = new Location(-6.211,106.845);

        ArrayList<QuakeEntry> close = getClosest(list,jakarta,3);
        for(int k=0; k < close.size(); k++){
            QuakeEntry entry = close.get(k);
            double distanceInMeters = jakarta.distanceTo(entry.getLocation());
            System.out.printf("%4.2f\t %s\n", distanceInMeters/1000,entry);
        }
        System.out.println("number found: "+close.size());
    }
    
}
