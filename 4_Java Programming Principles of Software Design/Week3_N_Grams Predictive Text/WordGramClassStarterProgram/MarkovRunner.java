
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class MarkovRunner {
    public void runModel(IMarkovModel markov, String text, int size){ 
        markov.setTraining(text); 
        System.out.println("running with " + markov); 
        for(int k=0; k < 1; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public void runModel(IMarkovModel markov, String text, int size, int seed){ 
        markov.setTraining(text); 
        markov.setRandom(seed);
        System.out.println("running with " + markov); 
        for(int k=0; k < 1; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public void runMarkov() { 
        FileResource fr = new FileResource(); 
        String st = fr.asString(); 
        st = st.replace('\n', ' '); 
        //MarkovWordOne markovWord = new MarkovWordOne(); 
        //runModel(markovWord, st, 200); 
    } 
    
    public void runMarkovTest1() { 
        // FileResource fr = new FileResource(); 
        // String st = fr.asString(); 
        String st = "this is just a test yes this is a simple test";
        st = st.replace('\n', ' '); 
        MarkovWord markovWord = new MarkovWord(2); 
        runModel(markovWord, st, 200); 
    }         
    
    public void runMarkovTest2() { 
        FileResource fr = new FileResource(); 
        String st = fr.asString(); 
        st = st.replace('\n', ' '); 
        MarkovWord markovWord = new MarkovWord(5); 
        runModel(markovWord, st, 100, 844); 
    }         
    
    public void testHashMap() {
        String st = "this is a test yes this is really a test"; 
        st = st.replace('\n', ' '); 
        EfficientMarkovWord markovWord = new EfficientMarkovWord(2);
        runModel(markovWord, st, 50, 42);
    }
    
    public void testHashMap2() {
        String st = "this is a test yes this is really a test yes a test this is wow"; 
        st = st.replace('\n', ' '); 
        EfficientMarkovWord markovWord = new EfficientMarkovWord(2);
        runModel(markovWord, st, 50, 42);
    }    
    
    public void testHashMap3() {
        FileResource fr = new FileResource(); 
        String st = fr.asString();
        EfficientMarkovWord markovWord = new EfficientMarkovWord(2);
        runModel(markovWord, st, 50, 65);
        markovWord.printHashMapInfo();
    }        
    
    public void runMarkovTest1_eff() { 
        // FileResource fr = new FileResource(); 
        // String st = fr.asString(); 
        String st = "this is just a test yes this is a simple test";
        st = st.replace('\n', ' '); 
        EfficientMarkovWord markovWord = new EfficientMarkovWord(2); 
        runModel(markovWord, st, 200); 
    }       
    
    public void runMarkovTest2_eff() { 
        FileResource fr = new FileResource(); 
        String st = fr.asString(); 
        st = st.replace('\n', ' '); 
        EfficientMarkovWord markovWord = new EfficientMarkovWord(3); 
        runModel(markovWord, st, 30, 643); 
    }    
    
    public void comparedMethods() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        int size = 1000;
        int seed = 42;        
        int numOrder = 2;
        int trials = 3;
        
        double begin1 = System.nanoTime();
        for (int i=0; i<trials; i++) {
            MarkovWord markovM = new MarkovWord(numOrder);
            runModel(markovM, st, size, seed);
        } 
        double end1 = System.nanoTime();

        double begin2 = System.nanoTime();
        for (int i=0; i<trials; i++) {    
            EfficientMarkovWord eMarkovM = new EfficientMarkovWord(numOrder);
            runModel(eMarkovM, st, size, seed);
        }
        double end2 = System.nanoTime();
        
        System.out.printf("%3.2f\t%3.2f\n", (end1 - begin1)/1e9/trials, (end2 - begin2)/1e9/trials);
    }

    private void printOut(String s){
        String[] words = s.split("\\s+");
        int psize = 0;
        System.out.println("----------------------------------");
        for(int k=0; k < words.length; k++){
            System.out.print(words[k]+ " ");
            psize += words[k].length() + 1;
            if (psize > 60) {
                System.out.println(); 
                psize = 0;
            } 
        } 
        System.out.println("\n----------------------------------");
    } 

}
