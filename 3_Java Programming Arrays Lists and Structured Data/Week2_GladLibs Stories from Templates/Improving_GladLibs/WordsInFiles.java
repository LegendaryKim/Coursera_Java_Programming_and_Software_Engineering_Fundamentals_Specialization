
/**
 * Write a description of WordsInFiles here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import java.io.File;
import edu.duke.*;

public class WordsInFiles {
    private HashMap<String, ArrayList<String>> fileMap;
    
    public WordsInFiles() {
        fileMap = new HashMap<String, ArrayList<String>>();
    }
    
    public void addWordsFromFile(File f) {
        String fName = f.getName();
        FileResource fr = new FileResource(f);
        for (String word: fr.words()) {
            if (!fileMap.containsKey(word)) {
                ArrayList<String> list = new ArrayList<String>();
                list.add(fName);
                fileMap.put(word, list);
            } else {
                ArrayList<String> list = fileMap.get(word);
                if (!list.contains(fName)) {
                    list.add(fName);
                    fileMap.put(word, list);
                }
            }
            
        }
    }
    
    public void buildWordFileMap() {
        fileMap.clear();
        DirectoryResource dr = new DirectoryResource();
        for (File f: dr.selectedFiles()) {
            addWordsFromFile(f);  
        }
    }
    
    public int maxNumber() {
        int maxLength = 0;
        for (ArrayList<String> fileList: fileMap.values()) {
            int currentLength = fileList.size();
            if (maxLength < currentLength) {
                maxLength = currentLength;
            }
        }
        return maxLength;
    }
    
    public ArrayList<String> wordsInNumFiles(int number) {
        ArrayList<String> result = new ArrayList<String>();
        for (String word: fileMap.keySet()) {
            if (number == fileMap.get(word).size()) {
                result.add(word);
            }
        }
        return result;
    }
    
    public void printFilesIn(String word) {
        if (fileMap.containsKey(word)) {
            ArrayList<String> files = fileMap.get(word);
            for (int i=0; i < files.size(); i++) {
                System.out.println(files.get(i));
            }
        }
    }
    
    public void tester() {
        buildWordFileMap();
        int maxNum = maxNumber();
        System.out.println("max number of files any word appears in: " + maxNum);
        ArrayList<String> wordsListFive = wordsInNumFiles(7);
        System.out.println("number of words in seven files: " + wordsListFive.size());
        ArrayList<String> wordsListFour = wordsInNumFiles(4);
        System.out.println("number of words that each appear in four of the seven files: " + wordsListFour.size());
        System.out.println("files with sea: ");
        for (String file: fileMap.get("sea")) {
            System.out.println(file);
        }
        System.out.println("files with tree: ");
        for (String file: fileMap.get("tree")) {
            System.out.println(file);
        }        
        //for (String word: wordsList) {
        //    System.out.println("word: " + word);
        //    printFilesIn(word);
        //}
        
    }
}
