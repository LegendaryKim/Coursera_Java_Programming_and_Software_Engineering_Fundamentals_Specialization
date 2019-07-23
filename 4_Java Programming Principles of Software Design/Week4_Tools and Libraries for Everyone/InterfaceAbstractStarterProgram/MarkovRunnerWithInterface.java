
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import edu.duke.*; 

public class MarkovRunnerWithInterface {
    public void runModel(IMarkovModel markov, String text, int size, int seed) {
        markov.setTraining(text);
        markov.setRandom(seed);
        System.out.println("running with " + markov);
        for(int k=0; k < 1; k++){
            String st= markov.getRandomText(size);
            printOut(st);
        }
    }
    
    public void runMarkov() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        int size = 200;
        int seed = 0;
        
        MarkovZero mz = new MarkovZero();
        runModel(mz, st, size, seed);
    
        MarkovOne mOne = new MarkovOne();
        runModel(mOne, st, size, seed);
        
        MarkovModel mThree = new MarkovModel(3);
        runModel(mThree, st, size, seed);
        
        MarkovFour mFour = new MarkovFour();
        runModel(mFour, st, size, seed);

    }
    
    public void testHashMap() {
        int seed = 531;
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        int size = 50;
        int numOrder = 5;
        
        EfficientMarkovModel eMarkovM = new EfficientMarkovModel(numOrder);
        runModel(eMarkovM, st, size, seed);
        eMarkovM.printHashMapInfo();        
    }
    
    public void compareMethods() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        int size = 1000;
        int seed = 42;        
        int numOrder = 2;
        int trials = 3;
        
        double begin1 = System.nanoTime();
        for (int i=0; i<trials; i++) {
            MarkovModel markovM = new MarkovModel(numOrder);
            runModel(markovM, st, size, seed);
        } 
        double end1 = System.nanoTime();

        double begin2 = System.nanoTime();
        for (int i=0; i<trials; i++) {    
            EfficientMarkovModel eMarkovM = new EfficientMarkovModel(numOrder);
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
