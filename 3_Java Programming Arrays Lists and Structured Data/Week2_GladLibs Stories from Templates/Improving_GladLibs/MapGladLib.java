
/**
 * Write a description of MapGladLibs here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;

public class MapGladLib {
    private HashMap<String, ArrayList<String>> myMap;
    private Random myRandom;
    
    private HashMap<String, String> myLabelSource;
    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "data";
    
    private static String[] labels = {"adjective", "noun", "color", "country", "name", "animal", 
                                      "timeframe", "verb", "fruit"};
    
    public MapGladLib(){
        initializeLabelSource(dataSourceDirectory);
        for (String s: myLabelSource.keySet()) {
            System.out.println("label: " + s + " Source: "+ myLabelSource.get(s));
        }
        initializeFromSource();        
        myRandom = new Random();
    }    
    public MapGladLib(String source){
        initializeLabelSource(source);
        initializeFromSource();
        myRandom = new Random();
    }    
    
    private void initializeLabelSource(String source) {
        myLabelSource = new HashMap<String, String>();
        for (String label: labels) {
            myLabelSource.put(label, source + "/" + label + ".txt");
        }
    }
    
    private void initializeFromSource() {
        myMap = new HashMap<String, ArrayList<String>>();
        for (String s: myLabelSource.keySet()) {
            System.out.println("label: " + s);
            ArrayList<String> list = readIt(myLabelSource.get(s));
            myMap.put(s, list);
        }
    }    
    
    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }   
    
    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }    
    
    private String getSubstitute(String label) {
        if (myMap.containsKey(label)) {
            return randomFrom(myMap.get(label));
        } else if (label.equals("number")) {
            return ""+myRandom.nextInt(50)+5;
        } else {
            return "**UNKNOWN**";
        }
    }
    
    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        String sub = getSubstitute(w.substring(first+1,last));
        return prefix+sub+suffix;
    }
    
    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }
    
    private String fromTemplate(String source){
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }    
    
    public void makeStory(){
        System.out.println("\n");
        String story = fromTemplate("data/madtemplate2.txt");
        printOut(story, 60);
    }    

}
