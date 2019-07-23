
/**
 * Write a description of MovieRunnerSimilarRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class MovieRunnerSimilarRatings {
    private String moviesFilename;
    private String ratersFilename;
    
    public MovieRunnerSimilarRatings() {
        moviesFilename = "ratedmoviesfull.csv";
        ratersFilename = "ratings.csv";
    }
    
    public void printAverageRatings() {
        FourthRatings fRatings = new FourthRatings();
        MovieDatabase.initialize(moviesFilename);
        RaterDatabase.initialize(ratersFilename);
        
        System.out.println("number of movies: " + MovieDatabase.size());
        System.out.println("number of raters: " + RaterDatabase.size());          
        
        // print a list of movies and their average ratings
        int minimalRaters = 1;
        ArrayList<Rating> aveRatings = fRatings.getAverageRatings(minimalRaters);
        System.out.println("found " + aveRatings.size() + " movies");

        Collections.sort(aveRatings);
        for (Rating rating: aveRatings) {
            System.out.println(rating.getValue() + " " 
                + MovieDatabase.getTitle(rating.getItem()));
        }
    }
    
    public void printAverageRatingsByYearAfterAndGenre() {
        FourthRatings fRatings = new FourthRatings();
        MovieDatabase.initialize(moviesFilename);
        RaterDatabase.initialize(ratersFilename);
        
        System.out.println("number of movies: " + MovieDatabase.size());
        System.out.println("number of raters: " + RaterDatabase.size());   
        
        // print a list of filtered movies and their average ratings 
        int minimalRaters = 1;
        String genre = "Romance";
        YearAfterFilter yearFilter = new YearAfterFilter(1980);
        GenreFilter genreFilter = new GenreFilter(genre);
        
        AllFilters allFilters = new AllFilters();
        allFilters.addFilter(yearFilter);
        allFilters.addFilter(genreFilter);
        
        ArrayList<Rating> aveRatings 
            = fRatings.getAverageRatingsByFilter(minimalRaters, allFilters);
        System.out.println("found " + aveRatings.size() + " movies");

        Collections.sort(aveRatings);
        for (Rating rating: aveRatings) {
            System.out.println(rating.getValue() + " " 
                + MovieDatabase.getYear(rating.getItem()) + " "
                + MovieDatabase.getTitle(rating.getItem()));
            System.out.println("\t" + MovieDatabase.getGenres(rating.getItem()));
        }
    }    
    
    public void printSimilarRatings() {
        FourthRatings fRatings = new FourthRatings();
        MovieDatabase.initialize(moviesFilename);
        RaterDatabase.initialize(ratersFilename);
        
        System.out.println("number of movies: " + MovieDatabase.size());
        System.out.println("number of raters: " + RaterDatabase.size());   
        
        int minimalRaters = 5;
        int numSimilarRaters = 20;
        String raterID = "65";              
        
        ArrayList<Rating> similarRatings = fRatings.getSimilarRatings(raterID, numSimilarRaters, minimalRaters);
        System.out.println("similarRatings size: " + similarRatings.size());
        
        for (Rating rating: similarRatings) {
            System.out.println(rating.getValue() + " " 
                + MovieDatabase.getTitle(rating.getItem()));
        }
    }
    
    public void printSimilarRatingsByGenre() {
        FourthRatings fRatings = new FourthRatings();
        MovieDatabase.initialize(moviesFilename);
        RaterDatabase.initialize(ratersFilename);
        
        System.out.println("number of movies: " + MovieDatabase.size());
        System.out.println("number of raters: " + RaterDatabase.size());   
        
        // print a list of filtered movies and their average ratings 
        int minimalRaters = 5;
        int numSimilarRaters = 20;
        String raterID = "65";
        
        String genre = "Action";        
        GenreFilter genreFilter = new GenreFilter(genre);      
        
        ArrayList<Rating> similarRatings = fRatings.getSimilarRatingsByFilters(raterID, numSimilarRaters, minimalRaters, genreFilter);
        System.out.println("similarRatings size: " + similarRatings.size());
        
        for (Rating rating: similarRatings) {
            System.out.println(rating.getValue() + " " 
                + MovieDatabase.getTitle(rating.getItem()));
        }
    }    
    
    public void printSimilarRatingsByDirector() {
        FourthRatings fRatings = new FourthRatings();
        MovieDatabase.initialize(moviesFilename);
        RaterDatabase.initialize(ratersFilename);
        
        System.out.println("number of movies: " + MovieDatabase.size());
        System.out.println("number of raters: " + RaterDatabase.size());   
        
        // print a list of filtered movies and their average ratings 
        int minimalRaters = 3;
        int numSimilarRaters = 10;
        String raterID = "1034";
        
        String directrors = "Clint Eastwood,Sydney Pollack,David Cronenberg,Oliver Stone";
        DirectorsFilter directorsFilter = new DirectorsFilter(directrors);     
        
        ArrayList<Rating> similarRatings = fRatings.getSimilarRatingsByFilters(raterID, numSimilarRaters, minimalRaters, directorsFilter);
        System.out.println("similarRatings size: " + similarRatings.size());
        
        for (Rating rating: similarRatings) {
            System.out.println(rating.getValue() + " " 
                + MovieDatabase.getTitle(rating.getItem()));
        }
    }    
    
    public void printSimilarRatingsByGenreAndMinutes() {
        FourthRatings fRatings = new FourthRatings();
        MovieDatabase.initialize(moviesFilename);
        RaterDatabase.initialize(ratersFilename);
        
        System.out.println("number of movies: " + MovieDatabase.size());
        System.out.println("number of raters: " + RaterDatabase.size());   
        
        // print a list of filtered movies and their average ratings 
        int minimalRaters = 5;
        int numSimilarRaters = 10;
        String raterID = "65";
        
        String genre = "Adventure";        
        GenreFilter genreFilter = new GenreFilter(genre);      
        
        int minMinutes = 100;
        int maxMinutes = 200;
        MinutesFilter minutesFilter = new MinutesFilter(minMinutes, maxMinutes);  
        
        AllFilters allFilters = new AllFilters();
        allFilters.addFilter(genreFilter);
        allFilters.addFilter(minutesFilter);
        
        ArrayList<Rating> similarRatings = fRatings.getSimilarRatingsByFilters(raterID, numSimilarRaters, minimalRaters, allFilters);
        System.out.println("similarRatings size: " + similarRatings.size());
        
        for (Rating rating: similarRatings) {
            System.out.println(rating.getValue() + " " 
                + MovieDatabase.getTitle(rating.getItem()));
        }
    }    
    
public void printSimilarRatingsByYearAfterAndMinutes() {
        FourthRatings fRatings = new FourthRatings();
        MovieDatabase.initialize(moviesFilename);
        RaterDatabase.initialize(ratersFilename);
        
        System.out.println("number of movies: " + MovieDatabase.size());
        System.out.println("number of raters: " + RaterDatabase.size());   
        
        // print a list of filtered movies and their average ratings 
        int minimalRaters = 5;
        int numSimilarRaters = 10;
        String raterID = "65";
        
        int yearAfter = 2000;
        YearAfterFilter yearFilter = new YearAfterFilter(yearAfter);    
        
        int minMinutes = 80;
        int maxMinutes = 100;
        MinutesFilter minutesFilter = new MinutesFilter(minMinutes, maxMinutes);  
        
        AllFilters allFilters = new AllFilters();
        allFilters.addFilter(yearFilter);
        allFilters.addFilter(minutesFilter);
        
        ArrayList<Rating> similarRatings = fRatings.getSimilarRatingsByFilters(raterID, numSimilarRaters, minimalRaters, allFilters);
        System.out.println("similarRatings size: " + similarRatings.size());
        
        for (Rating rating: similarRatings) {
            System.out.println(rating.getValue() + " " 
                + MovieDatabase.getTitle(rating.getItem()));
        }
    }    
    
}
