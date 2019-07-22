
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    public int howMany(String stringa, String stringb) {
        int counta = 0;
        int startIndex = 0;
        while (true) {
            int currIndex = stringb.indexOf(stringa, startIndex);
            if (currIndex ==-1) {
                return counta;
            } else {
                counta = counta + 1;
                startIndex = currIndex + stringa.length();
            }
        }
    }    
    public float cgRatio(String dna) {
        int dnaLength = dna.length();
        int countC = howMany("C", dna);
        int countG = howMany("G", dna);
        return (float) (countC + countG)/dnaLength;
        
    }
    public void testCGRatio() {
        String dna = "ATGCCATAG";
        System.out.println(cgRatio(dna));
    }
    
    public int countCTG(String dna) {
        return howMany("CTG", dna);
    }
    public void testCountCTG() {
        String dna = "ATGCCATAGCTGCTGCTG";
        System.out.println(countCTG(dna));
    }
}
