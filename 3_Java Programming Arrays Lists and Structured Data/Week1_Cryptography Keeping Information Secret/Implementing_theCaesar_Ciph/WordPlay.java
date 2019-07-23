
/**
 * Write a description of WordPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordPlay {
    public boolean isVowel(char ch) {
        String vowels = "aeiou";
        int idx = vowels.indexOf(Character.toLowerCase(ch));
        if (idx != -1) {
            return true;
        }
        return false;
    }
    public void testIsVowel(){
        char testCh = 'A';
        System.out.println(isVowel(testCh));
    }
    
    public String replaceVowels(String pharse, char ch) {
        StringBuilder replaced = new StringBuilder(pharse);
        for (int i = 0; i < replaced.length(); i++) {
            char originalCh = replaced.charAt(i);
            if (isVowel(originalCh)){
                replaced.setCharAt(i, ch);
            }
        }
        return replaced.toString();
    }
    public void testReplaceVoewls() {
        String test = "Hello World";
        System.out.println(test);
        System.out.println(replaceVowels(test, '*'));
    }
    
    public String emphasize(String pharse, char ch) {
        StringBuilder emphasized = new StringBuilder(pharse);
        char lowerCh = Character.toLowerCase(ch);
        for (int i = 0; i < emphasized.length(); i++) {
            char originalCh = emphasized.charAt(i);
            if (Character.toLowerCase(originalCh) == lowerCh) {
                if (i%2 ==0) {
                    emphasized.setCharAt(i, '*');
                } else {
                    emphasized.setCharAt(i, '+');
                }
            }
        }
        return emphasized.toString();
    }
    public void testEmphasize() {
        // String test = "dna ctgaaactga";
        String test = "Mary Bella Abracadabra";
        System.out.println(test);
        System.out.println(emphasize(test, 'a'));
    }
    
    
}