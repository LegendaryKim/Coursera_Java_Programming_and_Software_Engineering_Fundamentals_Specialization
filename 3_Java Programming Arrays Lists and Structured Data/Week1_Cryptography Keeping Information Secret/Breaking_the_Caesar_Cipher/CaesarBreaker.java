
/**
 * Write a description of CaesarBreaker here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class CaesarBreaker {
    public int[] countLetters(String message) {
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

    public int maxIndex(int[] vals) {
        int maxDex = 0;
        for (int k=0; k<vals.length; k++) {
            if (vals[k] > vals[maxDex]) {
                maxDex = k;
            }
        }
        return maxDex;
    }
    
    public String decrypt(String encrypted) {
        CaesarCipher cc = new CaesarCipher();
        int maxDex = getKey(encrypted);
        // maxDex > e
        int dKey = maxDex - 4;
        if (maxDex < 4) {
            dKey = 26 - (4-maxDex);
        }
        return cc.encrypt(encrypted, 26 - dKey);
    }
    public void testDecrypt() {
        FileResource file = new FileResource("../ProgrammingBreakingCaesarData/wordsLotsOfEs.txt");
        String message = file.asString();
        System.out.println("Original message: " + message);
        
        CaesarCipher cc = new CaesarCipher();
        int key = 15; 
        String encrypted = cc.encrypt(message, key);
        System.out.println("Encrypted message: " + encrypted);
        
        System.out.println("Decrypted message: " + decrypt(encrypted));
    }
    
    public String halfOfString(String message, int start) {
        StringBuilder halfMessage = new StringBuilder();
        for (int i = start; i < message.length(); i+=2) {
            halfMessage.append(message.charAt(i));
        }
        return halfMessage.toString();
    }
    public void testHalfOfString() {
        String original = "Qbkm Zgis";
        System.out.println("Original: " + original);
        System.out.println("first half: " + halfOfString(original, 0));
        System.out.println("Second half: " + halfOfString(original, 1));
    }
    
    public int getKey(String s) {
        int[] counts = countLetters(s);
        return maxIndex(counts);
    }
    
    public String decryptTwoKeys(String encrypted) {
        String firstString = halfOfString(encrypted, 0);
        String secondString = halfOfString(encrypted, 1);
        System.out.println("first String: " + firstString);
        System.out.println("secodn String: " + secondString);
        
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
        System.out.println("first Key: " + dFirstKey);
        System.out.println("second key: " + dSecondKey);        
        
        CaesarCipher cc = new CaesarCipher();
        return cc.encyptTwoKeys(encrypted, 26 - dFirstKey, 26 - dSecondKey);
    }   
    
    public void testdecryptTwoKeys() {
        FileResource file = new FileResource("../ProgrammingBreakingCaesarData/wordsLotsOfEs.txt");
        String message = file.asString();
        System.out.println("Original message: " + message);
        
        CaesarCipher cc = new CaesarCipher();
        int firstKey = 15; 
        int secondKey = 3; 
        String encrypted = cc.encyptTwoKeys(message, firstKey, secondKey);
        System.out.println("Encrypted message: " + encrypted);
        
        System.out.println("Decrypted message: " + decryptTwoKeys(encrypted));
    }
    
    public void quiz8() {
        String encrypted = "Top ncmy qkff vi vguv vbg ycpx";
        
        CaesarCipher cc = new CaesarCipher();
        System.out.println(cc.encyptTwoKeys(encrypted, 26 - 2, 26 - 20));
    }
    
    public void quiz9() {
        String encrypted = "Akag tjw Xibhr awoa aoee xakex znxag xwko";
        System.out.println("Decrypted message: " + decryptTwoKeys(encrypted));
    }
    
    public void quiz10() {
        FileResource file = new FileResource("mysteryTwoKeysPractice.txt");
        String encrypted = file.asString();
        System.out.println("Decrypted message: " + decryptTwoKeys(encrypted));
    }    
 
    public void final_quiz6() {
        String encrypted = "Hfs cpwewloj loks cd Hoto kyg Cyy.";
        
        CaesarCipher cc = new CaesarCipher();
        System.out.println(cc.encyptTwoKeys(encrypted, 26 - 14, 26 - 24));
    }    
    
    public void final_quiz7() {
        String encrypted = "Aal uttx hm aal Qtct Fhljha pl Wbdl. Pvxvxlx!";
        System.out.println("Decrypted message: " + decryptTwoKeys(encrypted));
    }    
    
    
    public void final_quiz8() {
        FileResource file = new FileResource("mysteryTwoKeysQuiz.txt");
        String encrypted = file.asString();
        System.out.println("Decrypted message: " + decryptTwoKeys(encrypted));
    }        
}
