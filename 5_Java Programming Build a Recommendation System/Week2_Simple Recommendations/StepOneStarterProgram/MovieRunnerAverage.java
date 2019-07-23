
/**
 * Write a description of MovieRunnerAverage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class MovieRunnerAverage {
    private String moviesFilename;
    private String ratersFilename;
    
    public MovieRunnerAverage() {
        moviesFilename = "data/ratedmovies_short.csv";
        ratersFilename = "data/ratings_short.csv";
    }
    
    public void printAverageRatings() {
        SecondRatings sRatings = new SecondRatings(moviesFilename, ratersFilename);
        
        System.out.println("number of movies: " + sRatings.getMovieSize());
        System.out.println("number of raters: " + sRatings.getRaterSize());   
        
        
        // print a list of movies and their average ratings
        int minimalRaters = 3;
        ArrayList<Rating> aveRatings = sRatings.getAverageRatings(minimalRaters);
        Collections.sort(aveRatings);
        for (Rating rating: aveRatings) {
            System.out.println(rating.getValue() + " " + sRatings.getTitle(rating.getItem()));
        }
    }
    
    public void getAverateRatingOneMovie() {
        SecondRatings sRatings = new SecondRatings(moviesFilename, ratersFilename);
        String title = "The Godfather";
        int minimalRaters = 0;
        String id = sRatings.getID(title);

        ArrayList<Rating> aveRatings = sRatings.getAverageRatings(minimalRaters);
        for (Rating rating: aveRatings) {
            if (id.equals(rating.getItem())) {
                System.out.println("ave. ratings of " + title +": " 
                    + rating.getValue());
                break;
            }
        }        
    }
}
