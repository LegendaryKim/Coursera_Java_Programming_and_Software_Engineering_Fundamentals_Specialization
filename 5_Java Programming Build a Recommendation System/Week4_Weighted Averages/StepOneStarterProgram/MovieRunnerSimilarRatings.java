
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
        String raterID = "71";              
        
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
        String raterID = "964";
        
        String genre = "Mystery";        
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
        int minimalRaters = 2;
        int numSimilarRaters = 10;
        String raterID = "120";
        
        String directrors = "Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh";
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
        int minimalRaters = 3;
        int numSimilarRaters = 10;
        String raterID = "168";
        
        String genre = "Drama";        
        GenreFilter genreFilter = new GenreFilter(genre);      
        
        int minMinutes = 80;
        int maxMinutes = 160;
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
        String raterID = "314";
        
        int yearAfter = 1975;
        YearAfterFilter yearFilter = new YearAfterFilter(yearAfter);    
        
        int minMinutes = 70;
        int maxMinutes = 200;
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
