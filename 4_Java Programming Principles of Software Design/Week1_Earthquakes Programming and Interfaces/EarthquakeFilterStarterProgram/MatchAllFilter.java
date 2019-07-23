
/**
 * Write a description of MatchAllFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class MatchAllFilter implements Filter {
    private ArrayList<Filter> filters;
    private ArrayList<String> names;

    public MatchAllFilter() {
        filters = new ArrayList<Filter>();
        names = new ArrayList<String>();
    }
    public void addFilter(Filter f) {
        filters.add(f);
        names.add(f.getName());
    }
    
    public boolean satisfies(QuakeEntry qe) {
        for (Filter f: filters) {
            if (!f.satisfies(qe)) {
                return false;
            }
        }
        return true;
    }
    
    public String getName() {
        String answer = "";
        for (String name: names) {
            answer += name + " ";
        }
        return answer;
    }
}
