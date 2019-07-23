import java.util.*;
import edu.duke.*;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder sliced = new StringBuilder();
        for (int i = whichSlice; i < message.length(); i += totalSlices) {
            sliced.append(message.charAt(i));
        }
        return sliced.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        for (int i = 0; i < klength; i++) {
            String sliced = sliceString(encrypted, i, klength);
            CaesarCracker cc = new CaesarCracker(mostCommon);
            int dkey = cc.getKey(sliced);
            key[i] = dkey;
        }
        return key;
    }
    
    public HashSet<String> readDictionary(FileResource fr) {
        HashSet<String> dictionary = new HashSet<String>();
        for (String word: fr.lines()) {
            word = word.toLowerCase();
            dictionary.add(word);
        }
        return dictionary;
    }
    
    public int countWords(String message, HashSet<String> dictionary) {
        int count = 0;
        for (String word: message.split("\\W+")) {
            if (dictionary.contains(word.toLowerCase())) {
                count++;
            }
        }
        return count;
    }
    
    public String breakForLanguage(String encrypted, HashSet<String> dictionary) {
        char mostCommon = mostCommonCharln(dictionary);
        String decryption = null;
        int mostCount = 0;
        int[] mostKey = null;
        
        for (int klength = 1; klength < 101; klength++) {
            int[] key = tryKeyLength(encrypted, klength, mostCommon);
            
            VigenereCipher vc = new VigenereCipher(key);
            String decrypted = vc.decrypt(encrypted);
            int currCount = countWords(decrypted, dictionary);
            if (mostCount < currCount) {
                mostCount = currCount;
                decryption = decrypted;
                mostKey = key;
            }
        }
        
        System.out.println("most Count: " + mostCount);
        System.out.println("key length: " + mostKey.length);
        return decryption;
    }
    
    public char mostCommonCharln(HashSet<String> dictionary) {
        HashMap<Character, Integer> mapCount = new HashMap<Character, Integer>();
        for (String word: dictionary) {
            word = word.toLowerCase();
            for (int k = 0; k < word.length(); k++) {
                char charAlpha = word.charAt(k);
                if (!mapCount.containsKey(charAlpha)) {
                    mapCount.put(charAlpha, 1);
                } else {
                    mapCount.put(charAlpha, mapCount.get(charAlpha) + 1);
                }
            }
        }
        
        int mostCount = 0;
        char mostChar = 0;
        for (char currentAlpha : mapCount.keySet()) {
            int currentCount = mapCount.get(currentAlpha);
            if (mostCount < currentCount) {
                mostCount = currentCount;
                mostChar = currentAlpha;
            }
        }
        return mostChar;
    }
    
    public void breakForAllLangs(String encrypted, HashMap<String, HashSet<String>> languages) {
        int currCount = 0;        
        for (String language: languages.keySet()) {
            HashSet<String> dictionary = languages.get(language);
            String decrypted = breakForLanguage(encrypted, dictionary);
            currCount = countWords(decrypted, dictionary);
            System.out.println("language: " + language);
            System.out.println("count words: " + currCount);
            System.out.println("decrypted: " + decrypted);
        }
    }
    
    public void breakVigenere () {
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        
        //String[] languages = {"Danish", "Dutch", "English", "French", "German",
        //                      "Italian", "Portuguese", "Spanish"};
        String[] languages = {"German"};                              
        HashMap<String, HashSet<String>> languagesDic = new HashMap<String, HashSet<String>>();
        
        for (String language: languages) {
            FileResource frDictionary = new FileResource("./dictionaries/" + language);
            HashSet<String> dictionary = readDictionary(frDictionary);            
            languagesDic.put(language, dictionary);
        }
        
        breakForAllLangs(encrypted, languagesDic);
    }   
}
