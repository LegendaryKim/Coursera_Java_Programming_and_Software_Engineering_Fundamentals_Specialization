
/**
 * Write a description of DistanceFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DistanceFilter implements Filter {
    private Location from;
    private double maxDist;
    private String name;
    
    public DistanceFilter(double max, Location loc) {
        maxDist = max;
        from = loc;
        name = "Distance";
    }
    
    public boolean satisfies(QuakeEntry qe) {
        return qe.getLocation().distanceTo(from) < maxDist;
    }
    
    public String getName() {
        return name;
    }
}
