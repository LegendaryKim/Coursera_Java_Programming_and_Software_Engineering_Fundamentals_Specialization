
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
    public Boolean twoOccurrences(String stringa, String stringb) {
        int firstIndex = stringb.indexOf(stringa);
        if (firstIndex == -1) {
            return false;
        } 
        if (stringb.indexOf(stringa, firstIndex+1) == -1) {
            return false;
        }
        return true; 
    }
    
    public void testing() {
        // String a = "by";
        // String b = "A Story by Ab Long";
        String a = "atg";
        String b = "ctgtatgta";
        Boolean result = twoOccurrences(a, b);
        System.out.println("a: " + a);
        System.out.println("b: " + b);
        System.out.println("Result: " 
            + String.valueOf(result));        
    }
    public String lastPart(String stringa, String stringb) {
        int aLen = stringa.length();
        int aIndex = stringb.indexOf(stringa);
        if (aIndex == -1) {
            return stringb;
        } else {
            return stringb.substring(aIndex + aLen);
        }     
    }
    
    public void testing2() {
        // String a = "an";
        // String b = "banana";
        String a = "zoo";
        String b = "forest";
        System.out.println("Result2: " + lastPart(a, b));
    }
}

