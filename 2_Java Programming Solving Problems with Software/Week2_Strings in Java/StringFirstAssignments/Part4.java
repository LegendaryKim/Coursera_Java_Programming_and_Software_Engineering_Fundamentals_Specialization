
/**
 * Write a description of Part4 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
public class Part4 {
    public void searchYouTube(URLResource url) {
        for (String line: url.lines()) {
            System.out.println("No YouTube link");
            int indexYouTube = line.toLowerCase().indexOf("youtube.com");
            if (indexYouTube == -1) {
                System.out.println("No YouTube link");
            } else {
                int beg = line.lastIndexOf("\"",indexYouTube);
                int end = line.indexOf("\"", indexYouTube+11);
                System.out.println(line.substring(beg+1,end));
            }            
        }
    }
    
    public void test() {
        String url = 
        "http://www.dukelearntoprogram.com/course2/data/manylinks.html";
        URLResource ur = new URLResource(url);
        searchYouTube(ur);
    }
}
