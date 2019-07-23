
/**
 * Write a description of CaesarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class CaesarCipherTwo {
    private String alphabet;
    private String shiftedAlphabet1;
    private String shiftedAlphabet2;
    private int mainKey1;
    private int mainKey2;   
    
    public CaesarCipherTwo(int key1, int key2) {
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        mainKey1 = key1;
        mainKey2 = key2;
        shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0,key1);
        shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0,key2);
    }
    
    public String encrypt(String input) {
        StringBuilder encrypted = new StringBuilder(input);
        String shifted = null;
        
        for (int i=0; i < input.length(); i++) {
            char ch = Character.toUpperCase(input.charAt(i));
            int idx = alphabet.indexOf(ch);
            boolean isLowerCase = Character.isLowerCase(input.charAt(i));
            
            if (i%2 == 0) {
                shifted = shiftedAlphabet1;
            } else {
                shifted = shiftedAlphabet2;
            }
            
            if (idx != -1) {
                if (isLowerCase) {
                        encrypted.setCharAt(i, Character.toLowerCase(shifted.charAt(idx)));                    
                } else {
                    encrypted.setCharAt(i, shifted.charAt(idx));
                }
            }
        }
        
        return encrypted.toString();
    }

    public String decrypt(String input) {
        CaesarCipherTwo cc = new CaesarCipherTwo(26 - mainKey1, 26 - mainKey2);
        return cc.encrypt(input);
    }

}
