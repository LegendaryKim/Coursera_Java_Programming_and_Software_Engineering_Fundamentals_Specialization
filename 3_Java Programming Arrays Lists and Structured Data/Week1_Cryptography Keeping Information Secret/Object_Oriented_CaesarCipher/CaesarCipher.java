
/**
 * Write a description of CaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class CaesarCipher {
    private String alphabet; 
    private String shiftedAlphabet;
    private int mainKey;

    public CaesarCipher(int key) {
        mainKey = key;
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0,key);
    }
    
    public String encrypt(String input) {
        StringBuilder encrypted = new StringBuilder(input);
        for (int i=0; i < input.length(); i++) {
            char ch = Character.toUpperCase(input.charAt(i));
            int idx = alphabet.indexOf(ch);
            boolean isLowerCase = Character.isLowerCase(input.charAt(i));
            if (idx != -1) {
                if (isLowerCase) {
                    encrypted.setCharAt(i, Character.toLowerCase(shiftedAlphabet.charAt(idx)));
                } else {
                    encrypted.setCharAt(i, shiftedAlphabet.charAt(idx));
                }
            }
        }
        return encrypted.toString();
    }
 
    public String decrypt(String input) {
        CaesarCipher cc = new CaesarCipher(26 - mainKey);
        return cc.encrypt(input);
    }
    
    private int getKey(String s) {
        int[] counts = countLetters(s);
        return maxIndex(counts);
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
}
