
/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    
    public SecondRatings() {
        // default constructor
        this("ratedmoviesfull.csv", "ratings.csv");
    }
    
    public SecondRatings(String movieFile, String ratingFile) {
        FirstRatings fRatings = new FirstRatings();
        myMovies = fRatings.loadMovies(movieFile);
        myRaters = fRatings.loadRaters(ratingFile);
    }
    
    public int getMovieSize() {
        return myMovies.size();
    }
    
    public int getRaterSize() {
        return myRaters.size();
    }
    
    private double getAverageByID(String id, int minimalRaters) {
        // number of ratings a particular movie has
        double sumRatings = 0.;
        int numRatings = 0;
        for (Rater rater: myRaters) {
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
        for (Rater rater: myRaters) {
            if (rater.hasRating(id)) {
                numRatings++;
            }
        }
        return numRatings;
    }    
    
    public ArrayList<Rating> getAverageRatings(int minimalRaters) {
        ArrayList<Rating> aveRatings = new ArrayList<Rating>();
        for (Movie movie: myMovies) {
            String id = movie.getID();
            int numRatings = countRatings(id);
            if (numRatings >= minimalRaters) {
                double aveRating = getAverageByID(id, minimalRaters);
                aveRatings.add(new Rating(id, aveRating));
            }
        }
        return aveRatings;
    }
    
    public String getTitle(String id) {
        for (Movie movie: myMovies) {
            String movieID = movie.getID();
            if (id.equals(movieID)) {
                return movie.getTitle();
            }
        }
        return "NO SUCH MOVIE_ID";
    }
    
    public String getID(String title) {
        for (Movie movie: myMovies) {
            String movieTitle = movie.getTitle();
            if (title.equals(movieTitle)) {
                return movie.getID();
            }   
        }
        return "NO SUCH MOVIE_TITLE";
    }
}
