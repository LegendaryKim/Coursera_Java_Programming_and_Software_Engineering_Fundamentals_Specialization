
public class WordGram {
    private String[] myWords;
    private int myHash;

    public WordGram(String[] source, int start, int size) {
        myWords = new String[size];
        System.arraycopy(source, start, myWords, 0, size);
    }

    public String wordAt(int index) {
        if (index < 0 || index >= myWords.length) {
            throw new IndexOutOfBoundsException("bad index in wordAt "+index);
        }
        return myWords[index];
    }

    public int length(){
        return myWords.length;
    }

    public String toString(){
        String ret = "";
        for (int k=0; k < myWords.length; k++) {
            ret += myWords[k] + " ";
        }
        return ret.trim();
    }

    public boolean equals(Object o) {
        WordGram other = (WordGram) o;
        if (length() != other.length()) return false;
        for (int k=0; k < myWords.length; k++) {
            if (!myWords[k].equals(other.wordAt(k))) {
                return false;
            }
        }
        return true;

    }

    public WordGram shiftAdd(String word) { 
        WordGram out = new WordGram(myWords, 0, myWords.length);
        for (int k=0; k < out.length() - 1; k++) {
            out.myWords[k] = out.wordAt(k+1);
        }
        out.myWords[out.length()-1] = word;        
        return out;
    }

    public int hashCode() {
        myHash = toString().hashCode();
        return myHash;
    }
}