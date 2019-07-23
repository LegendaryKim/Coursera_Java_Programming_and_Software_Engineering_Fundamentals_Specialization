
/**
 * Write a description of PhraseFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PhraseFilter implements Filter {
    private String where;
    private String phrase;
    private String name;
    
    public PhraseFilter(String whereInput, String phraseInput) {
        where = whereInput;
        phrase = phraseInput;
        name = "Phrase";
    }
    
    public boolean satisfies(QuakeEntry qe) {
        String title = qe.getInfo();
        if (where.equals("start")) {
            if (title.startsWith(phrase)) {
                return true;
            }
        } else if (where.equals("end")) {
            if (title.endsWith(phrase)) {
                return true;
            }                
        } else if (where.equals("any")) {
            if (title.indexOf(phrase) > 0) {
                return true;
            }
        }
        return false;
    }
    
    public String getName() {
        return name;
    }
}
