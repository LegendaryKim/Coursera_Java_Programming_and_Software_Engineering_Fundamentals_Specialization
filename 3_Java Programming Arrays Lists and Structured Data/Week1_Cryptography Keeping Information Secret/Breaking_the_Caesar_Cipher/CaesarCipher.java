
/**
 * Write a description of CaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class CaesarCipher {
    public String encrypt(String input, int key) {
        StringBuilder encrypted = new StringBuilder(input);
        
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shifted = alphabet.substring(key) + alphabet.substring(0,key);
        
        for (int i=0; i < input.length(); i++) {
            char ch = Character.toUpperCase(input.charAt(i));
            int idx = alphabet.indexOf(ch);
            boolean isLowerCase = Character.isLowerCase(input.charAt(i));
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
    public void testEncrypt() {
        //String test = "FIRST LEGION ATTACK EAST FLANK!";
        //String test = "First Legion";
        String test = "At noon be in the conference room with your hat on for a surprise party. YELL LOUD!";
        int key = 15;
        System.out.println(test);
        System.out.println(encrypt(test, key));
    }
    public void testCaeser() {
        FileResource fr = new FileResource();
        String message = fr.asString();
        int key = 23;
        String encrypted = encrypt(message, key);
        System.out.println("key is " + key + "\n" + encrypted);
    }
    
    public String encyptTwoKeys(String input, int key1, int key2) {
        StringBuilder encrypted = new StringBuilder(input);
        
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shifted1 = alphabet.substring(key1) + alphabet.substring(0,key1);
        String shifted2 = alphabet.substring(key2) + alphabet.substring(0,key2);
        String shifted = null;
        
        for (int i=0; i < input.length(); i++) {
            char ch = Character.toUpperCase(input.charAt(i));
            int idx = alphabet.indexOf(ch);
            boolean isLowerCase = Character.isLowerCase(input.charAt(i));
            
            if (i%2 == 0) {
                shifted = shifted1;
            } else {
                shifted = shifted2;
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
    public void testEncryptTwoKeys() {
        // String test = "First Legion";
        String test = "At noon be in the conference room with your hat on for a surprise party. YELL LOUD!";
        int key1 = 8;
        int key2 = 21;
        System.out.println(test);
        System.out.println(encyptTwoKeys(test, key1, key2));
    }
}
