
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    public String findSimpleGene(String dna, String startCodon, String stopCodon) {
        dna = dna.toUpperCase();
        startCodon  = startCodon.toUpperCase();
        stopCodon = stopCodon.toUpperCase();
        
        int startIndex = dna.indexOf(startCodon);
        if (startIndex == -1) {
            return "";
        }
        int endIndex = dna.indexOf(stopCodon, startIndex + 3);
        if (endIndex == -1) {
            return "";
        }
        if ((endIndex - startIndex) % 3 == 0 ) {
            return dna.substring(startIndex, endIndex + 3);
        } else {
            return "";
        }
    }
    
    public void testSimpleGene() {
        String a = "ATGGGTTAAGTC";
        String b = "gatgctataat";
        String result = findSimpleGene(b, "ATG", "TAA");
        System.out.println(result);
    }
}
