
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
    public int findStopCodon(String dna, int startIndex, String stopCodon) {
        int currIndex = dna.indexOf(stopCodon, startIndex + 3);
        while (currIndex != -1) {
            if ((currIndex - startIndex) % 3 == 0) {
                return currIndex;
            } else {
                currIndex = dna.indexOf(stopCodon, currIndex + 1);
            }
        }
        return dna.length();
    }
    
    public void testFindStopCodoen() {
        //            01234567890123456789012345
        String dna = "xxxyyyzzzTAAxxxyyyzzzTAAxx";
        int dex = findStopCodon(dna, 0, "TAA");
        if (dex != 9) System.out.println("error on 9");
        dex = findStopCodon(dna, 9, "TAA");
        if (dex != 21) System.out.println("error on 21");
        dex = findStopCodon(dna, 0, "TAG");
        if (dex != 26) System.out.println("error on 26");
        System.out.println("tests finished");        
    }
    
    public String findGene(String dna) {
        int startIndex = dna.indexOf("ATG");
        if (startIndex == -1) {
            return "";
        }
        
        int taaIndex = findStopCodon(dna, startIndex, "TAA");
        int tagIndex = findStopCodon(dna, startIndex, "TAG");
        int atgIndex = findStopCodon(dna, startIndex, "ATG");
        
        int minIndex = Math.min(Math.min(taaIndex, tagIndex), atgIndex);
        if (minIndex == dna.length()) {
            return "";
        }
        return dna.substring(startIndex, minIndex + 3);
    }
    public void testFindGene() {
        String dna = "xxxyyyzzzTAA";
        System.out.println("dna : " + dna);
        if (!findGene(dna).equals("")) System.out.println("error on ATG");
        dna = "ATGxxxyyyzzzTAG";
        System.out.println("dna : " + dna);        
        if (!findGene(dna).equals("ATGxxxyyyzzzTAG")) System.out.println("error on TAG");
        dna = "ATGxxxyyyTAATAG";
        System.out.println("dna : " + dna);        
        if (!findGene(dna).equals("ATGxxxyyyTAA")) System.out.println("error on TAA");
        dna = "ATGxxxyyyzzz";
        System.out.println("dna : " + dna);        
        if (!findGene(dna).equals("")) System.out.println("error on NoStopCodon");                
        
        System.out.println("tests finished");
    }
    
    public void printAllGenes() {
        String dna = "";
        
    }
}
