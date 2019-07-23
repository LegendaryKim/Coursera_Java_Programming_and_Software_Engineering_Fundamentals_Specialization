
/**
 * Write a description of TestCaesarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class TestCaesarCipherTwo {
    private String halfOfString(String message, int start) {
        StringBuilder halfMessage = new StringBuilder();
        for (int i = start; i < message.length(); i+=2) {
            halfMessage.append(message.charAt(i));
        }
        return halfMessage.toString();
    }
    
    private int[] countLetters(String message) {
        String alph = "abcdefghijklmnopqrstuvwxyz";
        int [] counts = new int[26];
        for (int k=0; k < message.length(); k++) {
            char ch = Character.toLowerCase(message.charAt(k));
            int dex = alph.indexOf(ch);
            if (dex != -1) {
                counts[dex] += 1;
            }
        }
        return counts;
    }  
    
    private int maxIndex(int[] vals) {
        int maxDex = 0;
        for (int k=0; k<vals.length; k++) {
            if (vals[k] > vals[maxDex]) {
                maxDex = k;
            }
        }
        return maxDex;
    }
    
    public void simpleTests() {
        int key1 = 21;
        int key2 = 8;
        // FileResource file = new FileResource("../ProgrammingBreakingCaesarData/wordsLotsOfEs.txt");
        // String message = file.asString();
        String message = "Can you imagine life WITHOUT the internet AND computers in your pocket?";

        CaesarCipherTwo cc = new CaesarCipherTwo(key1, key2);
        String encrypted = cc.encrypt(message);

        System.out.println("Original message: " + message);        
        System.out.println("Encrypted message: " + encrypted);
        System.out.println("Decrypted message: " + cc.decrypt(encrypted));
        System.out.println("Decrypted message by breakCeasarCipher: " 
                            + breakCaesarCipher(encrypted));
    }
    
    private int getKey(String s) {
        int[] counts = countLetters(s);
        return maxIndex(counts);
    }      
    
    private String breakCaesarCipher(String input) {
        String firstString = halfOfString(input, 0);
        String secondString = halfOfString(input, 1);
        //System.out.println("first String: " + firstString);
        //System.out.println("secodn String: " + secondString);
        
        int firstKey = getKey(firstString);
        int secondKey = getKey(secondString);        
        int dFirstKey = firstKey - 4;
        if (firstKey < 4) {
            dFirstKey = 26 - (4-firstKey);
        }
        int dSecondKey = secondKey - 4;
        if (secondKey < 4) {
            dSecondKey = 26 - (4-secondKey);
        }
        //System.out.println("first Key: " + dFirstKey);
        //System.out.println("second key: " + dSecondKey);           
        
        CaesarCipherTwo cc = new CaesarCipherTwo(dFirstKey, dSecondKey);
        return cc.decrypt(input);
    }
    
    
}
