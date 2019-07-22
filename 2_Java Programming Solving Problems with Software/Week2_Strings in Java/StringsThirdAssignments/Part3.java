
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class Part3 { 
public int findStopCodon(String dna, int startIndex, String stopCodon) {
        int currIndex = dna.indexOf(stopCodon, startIndex + 3);
        while (currIndex != -1) {
            if ((currIndex - startIndex) % 3 == 0) {
                return currIndex;
            } else {
                currIndex = dna.indexOf(stopCodon, currIndex + 1);
            }
        }
        return -1;
    }
    
    public void testFindStopCodoen() {
        //            01234567890123456789012345
        String dna = "xxxyyyzzzTAAxxxyyyzzzTAAxx";
        int dex = findStopCodon(dna, 0, "TAA");
        if (dex != 9) System.out.println("error on 9");
        dex = findStopCodon(dna, 9, "TAA");
        if (dex != 21) System.out.println("error on 21");
        dex = findStopCodon(dna, 0, "TAG");
        if (dex != -1) System.out.println("error on -1");
        System.out.println("tests finished");        
    }
    
    public String findGene(String dna, int where) {
        int startIndex = dna.indexOf("ATG", where);
        
        if (startIndex == -1) {
            return "";
        }
        
        int taaIndex = findStopCodon(dna, startIndex, "TAA");
        int tagIndex = findStopCodon(dna, startIndex, "TAG");
        int tgaIndex = findStopCodon(dna, startIndex, "TGA");

        int minIndex = 0;
        if (taaIndex == -1 ||
            (tagIndex != -1 && tagIndex < taaIndex)) {
            minIndex = tagIndex;
        } else {
            minIndex = taaIndex;
        }
        
        if (minIndex == -1 || 
            (tgaIndex != -1 && tgaIndex < minIndex)) {
            minIndex = tgaIndex;
        }
        
        if (minIndex == -1) {
            return "";
        } else {
            return dna.substring(startIndex, minIndex + 3);
        }
    }
    
    public void printAllGenes(String dna) {
        int startIndex = 0;
        while (true) {
            String currentGene = findGene(dna, startIndex);
            if (currentGene.isEmpty()) {
                break;
            }
            System.out.println(currentGene);
            startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
        }        
    }    
    public void testPrintAllGenes() {
        String dna = "ATGxxxyyyTAAxxxATGyyyzzzxxxTAG";
        System.out.println("Testing on dna: " + dna);
        printAllGenes(dna);
    }
    
    public int countGenes(String dna) {
        int count = 0;
        int startIndex = 0;
        while (true) {
            String currentGene = findGene(dna, startIndex);
            if (currentGene.isEmpty()) {
                return count;
            }
            count = count + 1;            
            startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
        }        
    }
    public void testCountGenes() {
        String dna = "ATGxxxyyyTAAxxxATGyyyzzzxxxTAG";
        System.out.println("Testing on dna: " + dna);
        System.out.println(countGenes(dna));
    }
    
    public StorageResource getAllGenes(String dna) {
        StorageResource genes = new StorageResource();
        int startIndex = 0;
        while (true) {
            String currentGene = findGene(dna, startIndex);
            if (currentGene.isEmpty()) {
                break;
            }
            genes.add(currentGene);
            startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
        }        
        return genes;
    }       
    public void testGetAllGenes() {
        String dna = "ATGxxxyyyTAAxxxATGyyyzzzxxxTAG";
        StorageResource genes = getAllGenes(dna);
        for (String gene: genes.data()) {
            System.out.println(gene);
        }
    }    
    
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
    
    public void processGenes(StorageResource sr) {
        int countLength = 0;
        int countCGRatio = 0;  
        int longestLength = 0;        
        for (String line: sr.data()) {
            System.out.println("gene : " + line);
            if (line.length() > 60) {
                System.out.println(line);
                countLength += 1;
            }
            if (cgRatio(line) > 0.35) {
                System.out.println(line);
                countCGRatio += 1;
            }            
            int currentLength = line.length();
            if (currentLength > longestLength) {
                longestLength = currentLength;
            }            
        }
        System.out.println("count - Length : " + countLength);
        System.out.println("count - CGRatio : " + countCGRatio);
        System.out.println("Longest Length: " +longestLength);
    }
    
    public void testProcessGenes() {
        FileResource fr = new FileResource("./dna/brca1line.fa");
        String dna = fr.asString().toUpperCase();
        System.out.println(dna);
        StorageResource genes = getAllGenes(dna);
        System.out.println("size of genes: " + genes.size());
        processGenes(genes);
    }

    public void testProcessGenes2() {
        FileResource fr = new FileResource("./dna/GRch38dnapart.fa");
        String dna = fr.asString().toUpperCase();
        System.out.println(dna);
        System.out.println("count - CTG: " + howMany("CTG", dna));        
        StorageResource genes = getAllGenes(dna);
        System.out.println("size of genes: " + genes.size());
        processGenes(genes);
    }    
    
}
