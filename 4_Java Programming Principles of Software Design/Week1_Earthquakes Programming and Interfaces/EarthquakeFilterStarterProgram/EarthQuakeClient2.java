import java.util.*;
import edu.duke.*;

public class EarthQuakeClient2 {
    public EarthQuakeClient2() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, Filter f) { 
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData) { 
            if (f.satisfies(qe)) { 
                answer.add(qe); 
            } 
        } 
        
        return answer;
    } 

    public void quakesWithFilter() { 
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");

        //Filter f = new MinMagFilter(4.0); 
        //ArrayList<QuakeEntry> m7  = filter(list, f); 
        Filter f1 = new MagnitudeFilter(3.5, 4.5);
        Filter f2 = new DepthFilter(-55000.0, -20000.0);
        ArrayList<QuakeEntry> m8 = filter(list, f1);
        ArrayList<QuakeEntry> m9 = filter(m8, f2);
        for (QuakeEntry qe: m9) { 
            System.out.println(qe);
        } 
        System.out.println("Found " + m9.size() + " quakes that match that criteria");
    }
    
    
    public void quakesWithFilter2() { 
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");

        //Filter f = new MinMagFilter(4.0); 
        //ArrayList<QuakeEntry> m7  = filter(list, f);
        
        //Location tokyo = new Location(35.42, 139.43);
        Location denver = new Location(39.7392, -104.9903);
                
        Filter f1 = new DistanceFilter(1000 * 1000, denver);
        Filter f2 = new PhraseFilter("end", "a");
        ArrayList<QuakeEntry> m8 = filter(list, f1);
        ArrayList<QuakeEntry> m9 = filter(m8, f2);
        for (QuakeEntry qe: m9) { 
            System.out.println(qe);
        } 
        System.out.println("Found " + m9.size() + " quakes that match that criteria");
    }    
    
    public void testMatchAllFilter() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");
        
        MatchAllFilter maf = new MatchAllFilter();
        Filter f1 = new MagnitudeFilter(1.0, 4.0);
        Filter f2 = new DepthFilter(-180000.0, -30000.0);
        Filter f3 = new PhraseFilter("any", "o");
        
        maf.addFilter(f1);
        maf.addFilter(f2);
        maf.addFilter(f3);
        
        ArrayList<QuakeEntry> m10 = filter(list, maf);
        for (QuakeEntry qe: m10) {
            System.out.println(qe);
        }
        System.out.println("Found " + m10.size() + " quakes that match that criteria");
    }
    
    public void testMatchAllFilter2() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");
        
        MatchAllFilter maf = new MatchAllFilter();
        Filter f1 = new MagnitudeFilter(0.0, 5.0);
        Location buillund = new Location(55.7308, 9.1153);
        Filter f2 = new DistanceFilter(3000 * 1000, buillund);
        Filter f3 = new PhraseFilter("any", "e");
        
        maf.addFilter(f1);
        maf.addFilter(f2);
        maf.addFilter(f3);
        
        ArrayList<QuakeEntry> m10 = filter(list, maf);
        for (QuakeEntry qe: m10) {
            System.out.println(qe);
        }
        System.out.println("Found " + m10.size() + " quakes that match that criteria");
        System.out.println("Filters used are: " + maf.getName());
        
    }    
    

    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "../data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: "+list.size());
    }

    public void dumpCSV(ArrayList<QuakeEntry> list) {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }
    }

}
