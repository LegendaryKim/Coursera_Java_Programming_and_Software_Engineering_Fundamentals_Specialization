
/**
 * Write a description of Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;

public class Tester {
    public void testCaesarCipher() {
        FileResource fr = new FileResource("../VigenereTestData/titus-small.txt");
        String original = fr.asString();
        System.out.println("Original :" + original);
                
        int key = 10;
        CaesarCipher cc = new CaesarCipher(key);        
        String encrypted = cc.encrypt(original);
        System.out.println("Key :" + key);
        System.out.println("Encrypted :" + encrypted);

        String decrypted = cc.decrypt(encrypted);
        System.out.println("Decrypted :" + decrypted);        
    }

    public void testCeasarCracker() {
        FileResource fr = new FileResource("../VigenereTestData/titus-small_key5.txt");
        String encrypted = fr.asString();
        System.out.println("Encrypted :" + encrypted);
        CaesarCracker cc = new CaesarCracker();
        String decrypted = cc.decrypt(encrypted);
        System.out.println("Decrypted :" + decrypted);
        
        FileResource fr2 = new FileResource("../VigenereTestData/oslusiadas_key17.txt");
        String encrypted2 = fr.asString();
        System.out.println("Encrypted2 :" + encrypted2);
        CaesarCracker cc2 = new CaesarCracker('a');
        String decrypted2 = cc2.decrypt(encrypted2);
        System.out.println("Decrypted2 :" + decrypted2);
    }
    
    public void testVigenereCipher() {
        FileResource fr = new FileResource("../VigenereTestData/titus-small.txt");
        String original = fr.asString();
        System.out.println("Original :" + original);
                
        int[] key = {17, 14, 12, 4};
        VigenereCipher vc = new VigenereCipher(key);        
        String encrypted = vc.encrypt(original);
        System.out.println("Encrypted :" + encrypted);

        String decrypted = vc.decrypt(encrypted);
        System.out.println("Decrypted :" + decrypted);        
    }
    
    public void testSliceString() {
        VigenereBreaker vb = new VigenereBreaker();
        String test = "abcdefghijklm";
        int[] totalSliceArray = {3, 4, 5};
        for (int totalSlice: totalSliceArray) {
            for (int whichSlice = 0; whichSlice < totalSlice; whichSlice++ ) {
                System.out.println("result with " + whichSlice + " " + totalSlice + " :" 
                           + vb.sliceString(test, whichSlice, totalSlice));
            }
        }
    }
    
    public void testTryKeyLength() {
        //FileResource fr = new FileResource("../VigenereTestData/athens_keyflute.txt");
        FileResource fr = new FileResource("../secretmessage1.txt");
        String encrypted = fr.asString();
        System.out.println("Encrypted :" + encrypted);
        
        VigenereBreaker vb = new VigenereBreaker();
        int[] key = vb.tryKeyLength(encrypted, 38, 'e');
        System.out.println(Arrays.toString(key));
    }
       
    public void testReadDictionary() {
        VigenereBreaker vb = new VigenereBreaker();
        FileResource fr = new FileResource("./dictionaries/English");
        System.out.println(vb.readDictionary(fr));
    }
    
    public void testCountwords() {
        FileResource fr = new FileResource("../VigenereTestData/titus-small.txt");
        String original = fr.asString();
        System.out.println("Original: " + original);
        FileResource frDictionary = new FileResource("./dictionaries/English");

        VigenereBreaker vb = new VigenereBreaker();        
        System.out.println("count words: " + vb.countWords(original, vb.readDictionary(frDictionary)));
    }    
    
    public void testCountwords2() {
        FileResource fr = new FileResource("../secretmessage2.txt");
        String encrypted = fr.asString();
        System.out.println("encrypted: " + encrypted);
        FileResource frDictionary = new FileResource("./dictionaries/English");

        VigenereBreaker vb = new VigenereBreaker();  
        int[] key = vb.tryKeyLength(encrypted, 38, 'e');

        VigenereCipher vc = new VigenereCipher(key);
        String decrypted = vc.decrypt(encrypted);
        HashSet<String> dictionary = vb.readDictionary(frDictionary);
        
        System.out.println("count words: " + vb.countWords(decrypted, dictionary));
    }    
    
    public void testBreakForLanguage() {
        FileResource frDictionary = new FileResource("./dictionaries/English");
        FileResource fr = new FileResource("../VigenereTestData/athens_keyflute.txt");
        String encrypted = fr.asString();
        System.out.println("Encrypted :" + encrypted);
        
        VigenereBreaker vb = new VigenereBreaker();     
        System.out.println("Decrypted: " 
                           + vb.breakForLanguage(encrypted, vb.readDictionary(frDictionary)));
    }
    
    public void testBreakVigenere() {
        VigenereBreaker vb = new VigenereBreaker();
        vb.breakVigenere();
    }
    
    public void testMostCommonCharln() {
        FileResource frDictionary = new FileResource("./dictionaries/Spanish");
        VigenereBreaker vb = new VigenereBreaker(); 
        HashSet<String> dictionary = vb.readDictionary(frDictionary);
        System.out.println("most Common char in: " + vb.mostCommonCharln(dictionary));
    }
}
