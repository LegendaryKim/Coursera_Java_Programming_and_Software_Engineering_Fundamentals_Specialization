
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
    public String findSimpleGene(String dna) {
        int startIndex = dna.indexOf("ATG");
        if (startIndex == -1) {
            return "";
        }
        int endIndex = dna.indexOf("TAA", startIndex + 3);
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
        String a = "TTAATGTAA";
        String b = "TTAATGTTA";
        String c = "TTATTATAA";
        String d = "ATGCATTAA";
        String e = "ATGCTTAA";
        String result = findSimpleGene(e);
        System.out.println(result);
    }
}
