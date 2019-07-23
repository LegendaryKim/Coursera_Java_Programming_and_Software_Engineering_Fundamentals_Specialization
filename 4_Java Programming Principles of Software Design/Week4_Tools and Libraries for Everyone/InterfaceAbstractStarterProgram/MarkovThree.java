import java.util.ArrayList;
import java.util.Random;

public class MarkovThree implements IMarkovModel {
    private String myText;
    private Random myRandom;


    public MarkovThree() {
    }

    @Override
    public void setTraining(String text) {
    }

    @Override
    public String getRandomText(int numChars) {
        if (myText == null){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length() - 3);
        String key = myText.substring(index, index + 3);
        sb.append(key);

//        for (int k=0; k < numChars - 3; k++) {
//            ArrayList<String> follows = getFollows(key);
//            if (follows.size() == 0) break;
//            index = myRandom.nextInt(follows.size());
//            String next = follows.get(index);
//            sb.append(next);
//            key = key.substring(1) + next;
//        }
//        return sb.toString();
        return null;
    }

    @Override
    public void setRandom(int seed) {
    }
}
