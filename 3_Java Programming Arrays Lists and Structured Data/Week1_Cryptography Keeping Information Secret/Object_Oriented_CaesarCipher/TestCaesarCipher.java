
/**
 * Write a description of TestCaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class TestCaesarCipher {   
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
    private int getKey(String s) {
        int[] counts = countLetters(s);
        return maxIndex(counts);
    }    
    
    public void simpleTests() {
        int key = 15;
        //FileResource file = new FileResource("../ProgrammingBreakingCaesarData/wordsLotsOfEs.txt");
        //String message = file.asString();
        String message = "Can you imagine life WITHOUT the internet AND computers in your pocket?";

        CaesarCipher cc = new CaesarCipher(key);
        String encrypted = cc.encrypt(message);

        System.out.println("Original message: " + message);        
        System.out.println("Encrypted message: " + encrypted);
        System.out.println("Decrypted message: " + cc.decrypt(encrypted));
        System.out.println("Decrypted message by breakCeasarCipher: " 
                            + breakCaesarCipher(encrypted));
    }
    
    private String breakCaesarCipher(String input) {
        int maxDex = getKey(input);
        int dKey = maxDex - 4;
        if (maxDex < 4) {
            dKey = 26 - (4-maxDex);
        }
        
        CaesarCipher cc = new CaesarCipher(dKey);
        return cc.decrypt(input);
    }
}
