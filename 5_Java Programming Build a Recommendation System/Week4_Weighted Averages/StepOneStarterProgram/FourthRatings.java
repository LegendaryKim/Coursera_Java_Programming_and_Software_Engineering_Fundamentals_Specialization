
/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class FourthRatings {
    public FourthRatings() {
    }
    
    private double getAverageByID(String id, int minimalRaters) {
        // number of ratings a particular movie has
        double sumRatings = 0.;
        int numRatings = 0;
        for (Rater rater: RaterDatabase.getRaters()) {
            if (rater.hasRating(id)) {
                sumRatings += rater.getRating(id);
                numRatings ++;
            }
        }
        if (numRatings >= minimalRaters) {
            return sumRatings/numRatings;
        }
        return 0.;
    }
    
    private int countRatings(String id) {
        int numRatings = 0;
        for (Rater rater: RaterDatabase.getRaters()) {
            if (rater.hasRating(id)) {
                numRatings++;
            }
        }
        return numRatings;
    }    
    
    public ArrayList<Rating> getAverageRatings(int minimalRaters) {
        ArrayList<Rating> aveRatings = new ArrayList<Rating>();
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        //for (Movie movie: MovieDatabase.filterBy(new TrueFilter()) {
        for (String id: movies) {
            //String id = movie.getID();
            int numRatings = countRatings(id);
            if (numRatings >= minimalRaters) {
                double aveRating = getAverageByID(id, minimalRaters);
                aveRatings.add(new Rating(id, aveRating));
            }
        }
        return aveRatings;
    }
    
    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria) {
        ArrayList<Rating> aveRatings = new ArrayList<Rating>();
        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
        for (String id: movies) {
            //String id = movie.getID();
            int numRatings = countRatings(id);
            if (numRatings >= minimalRaters) {
                double aveRating = getAverageByID(id, minimalRaters);
                aveRatings.add(new Rating(id, aveRating));
            }
        }
        return aveRatings;
    }
    
    private double rateScaling(double rate) {
        if (rate < 0) {
            return 0.;
        }
        return rate - 5.;
    }
    
    private double dotProduct(Rater me, Rater r) {
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        double dotProductRating = 0.;
       
        for (String movieID: movies) {
            //System.out.println("id: " + movieID);
            //System.out.println("meRating: " + me.getRating(movieID));
            if (me.hasRating(movieID) && r.hasRating(movieID)) {
                double meScaledRating = rateScaling(me.getRating(movieID));
                double rScaledRating = rateScaling(r.getRating(movieID));
                dotProductRating += meScaledRating * rScaledRating;
            }
        }
        return dotProductRating;
    }
    
    private ArrayList<Rating> getSimilarities(String id) {
        ArrayList<Rating> list = new ArrayList<Rating>();
        Rater me = RaterDatabase.getRater(id);
        for (Rater r: RaterDatabase.getRaters()) {           
            if (!r.getID().equals(me.getID())) {
                double similarityRating = dotProduct(me, r);
                // Include only raters with positive similarity ratings
                if (similarityRating > 0.) {
                    list.add(new Rating(r.getID(), similarityRating));
                }
            }
        }
        Collections.sort(list, Collections.reverseOrder());
        return list;
    }
    
    /*
    public ArrayList<Rating> getSimilarRatings(String id, int numSimilarRaters, int minimalRaters) {
        ArrayList<Rating> result = new ArrayList<Rating>();
        ArrayList<String> moviesID = MovieDatabase.filterBy(new TrueFilter());
        ArrayList<Rating> similarList = getSimilarities(id);
                    
        Rater myRater = RaterDatabase.getRater(id);
        
        for (String movieID: moviesID) {
            if (myRater.hasRating(movieID)) {
                continue;
            }
            int nRatings = 0;
            double weightedSum = 0.;
            for (int k=0; k < numSimilarRaters; k++) {
                Rating r = similarList.get(k);
                String similarRaterID = r.getItem();
                Rater similarRater = RaterDatabase.getRater(similarRaterID);
                
                if (similarRater.hasRating(movieID)) {
                    nRatings ++;
                    double rating = similarRater.getRating(movieID);
                    double similarityRating = r.getValue();
                    weightedSum += similarityRating * rating;
                }
            }
            
            if (nRatings >= minimalRaters) {
                double weightedAveRating = weightedSum / nRatings;
                result.add(new Rating(movieID, weightedAveRating));
            }
        }
        Collections.sort(result, Collections.reverseOrder());
        return result;
    }
    */
   
   public ArrayList<Rating> getSimilarRatings(String id, int numSimilarRaters, int minimalRaters) {
       return getSimilarRatingsByFilters(id, numSimilarRaters, minimalRaters, new TrueFilter());
   }      
    
    
   public ArrayList<Rating> getSimilarRatingsByFilters(String id, int numSimilarRaters, int minimalRaters, Filter filterCriteria) {
        ArrayList<Rating> result = new ArrayList<Rating>();
        ArrayList<String> moviesID = MovieDatabase.filterBy(filterCriteria);
        ArrayList<Rating> similarList = getSimilarities(id);
                    
        Rater myRater = RaterDatabase.getRater(id);
        
        for (String movieID: moviesID) {
            if (myRater.hasRating(movieID)) {
                continue;
            }
            int nRatings = 0;
            double weightedSum = 0.;
            for (int k=0; k < numSimilarRaters; k++) {
                Rating r = similarList.get(k);
                String similarRaterID = r.getItem();
                Rater similarRater = RaterDatabase.getRater(similarRaterID);
                
                if (similarRater.hasRating(movieID)) {
                    nRatings ++;
                    double rating = similarRater.getRating(movieID);
                    double similarityRating = r.getValue();
                    weightedSum += similarityRating * rating;
                }
            }
            
            if (nRatings >= minimalRaters) {
                double weightedAveRating = weightedSum / nRatings;
                result.add(new Rating(movieID, weightedAveRating));
            }
        }
        Collections.sort(result, Collections.reverseOrder());
        return result;
    }    
}
