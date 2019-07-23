
/**
 * Write a description of MovieRunnerWithFilters here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class MovieRunnerWithFilters {
    private String moviesFilename;
    private String ratersFilename;
    
    public MovieRunnerWithFilters() {
        moviesFilename = "ratedmovies_short.csv";
        ratersFilename = "data/ratings_short.csv";
    }
    
    public void printAverageRatings() {
        //String moviesFilename = "ratedmovies_short.csv";
        //String ratersFilename = "data/ratings_short.csv";
        
        //SecondRatings sRatings = new SecondRatings(moviesFilename, ratersFilename);
        ThirdRatings tRatings = new ThirdRatings(ratersFilename);
        MovieDatabase.initialize(moviesFilename);
        
        System.out.println("number of movies: " + MovieDatabase.size());
        System.out.println("number of raters: " + tRatings.getRaterSize());          
        
        // print a list of movies and their average ratings
        int minimalRaters = 1;
        ArrayList<Rating> aveRatings = tRatings.getAverageRatings(minimalRaters);
        System.out.println("found " + aveRatings.size() + " movies");

        Collections.sort(aveRatings);
        for (Rating rating: aveRatings) {
            System.out.println(rating.getValue() + " " 
                + MovieDatabase.getTitle(rating.getItem()));
        }
    }
    
    public void printAverageRatingsByYear() {
        ThirdRatings tRatings = new ThirdRatings(ratersFilename);
        MovieDatabase.initialize(moviesFilename);
        
        System.out.println("number of movies: " + MovieDatabase.size());
        System.out.println("number of raters: " + tRatings.getRaterSize());   
        
        // print a list of filtered movies and their average ratings 
        int minimalRaters = 1;
        YearAfterFilter yearFilter = new YearAfterFilter(2000);
        ArrayList<Rating> aveRatings 
            = tRatings.getAverageRatingsByFilter(minimalRaters, yearFilter);
        System.out.println("found " + aveRatings.size() + " movies");

        Collections.sort(aveRatings);
        for (Rating rating: aveRatings) {
            System.out.println(rating.getValue() + " " 
                + MovieDatabase.getYear(rating.getItem()) + " "
                + MovieDatabase.getTitle(rating.getItem()));
        }
    }
    
    public void printAverageRatingsByGenre() {
        ThirdRatings tRatings = new ThirdRatings(ratersFilename);
        MovieDatabase.initialize(moviesFilename);
        
        System.out.println("number of movies: " + MovieDatabase.size());
        System.out.println("number of raters: " + tRatings.getRaterSize());   
        
        // print a list of filtered movies and their average ratings 
        int minimalRaters = 1;
        String genre = "Crime";
        GenreFilter genreFilter = new GenreFilter(genre);
        ArrayList<Rating> aveRatings 
            = tRatings.getAverageRatingsByFilter(minimalRaters, genreFilter);
        System.out.println("found " + aveRatings.size() + " movies");

        Collections.sort(aveRatings);
        for (Rating rating: aveRatings) {
            System.out.println(rating.getValue() + " " 
                + MovieDatabase.getTitle(rating.getItem()));
            System.out.println("\t" + MovieDatabase.getGenres(rating.getItem()));
        }
    }    
    
    public void printAverageRatingsByMinutes() {
        ThirdRatings tRatings = new ThirdRatings(ratersFilename);
        MovieDatabase.initialize(moviesFilename);
        
        System.out.println("number of movies: " + MovieDatabase.size());
        System.out.println("number of raters: " + tRatings.getRaterSize());   
        
        // print a list of filtered movies and their average ratings 
        int minimalRaters = 1;
        int minMinutes = 110;
        int maxMinutes = 170;
        MinutesFilter minutesFilter = new MinutesFilter(minMinutes, maxMinutes);
        ArrayList<Rating> aveRatings 
            = tRatings.getAverageRatingsByFilter(minimalRaters, minutesFilter);
        System.out.println("found " + aveRatings.size() + " movies");

        Collections.sort(aveRatings);
        for (Rating rating: aveRatings) {
            System.out.println(rating.getValue() + " " 
                + "Time: " + MovieDatabase.getMinutes(rating.getItem()) + " "
                + MovieDatabase.getTitle(rating.getItem()));
        }
    }        
    
    public void printAverageRatingsByDirectors() {
        ThirdRatings tRatings = new ThirdRatings(ratersFilename);
        MovieDatabase.initialize(moviesFilename);
        
        System.out.println("number of movies: " + MovieDatabase.size());
        System.out.println("number of raters: " + tRatings.getRaterSize());   
        
        // print a list of filtered movies and their average ratings 
        int minimalRaters = 1;
        String directrors = "Charles Chaplin,Michael Mann,Spike Jonze";
        DirectorsFilter directorsFilter = new DirectorsFilter(directrors);
        ArrayList<Rating> aveRatings 
            = tRatings.getAverageRatingsByFilter(minimalRaters, directorsFilter);
        System.out.println("found " + aveRatings.size() + " movies");

        Collections.sort(aveRatings);
        for (Rating rating: aveRatings) {
            System.out.println(rating.getValue() + " " 
                + MovieDatabase.getTitle(rating.getItem()));
            System.out.println("\t" + MovieDatabase.getDirector(rating.getItem()));
        }
    }
    
    public void printAverageRatingsByYearAfterAndGenre() {
        ThirdRatings tRatings = new ThirdRatings(ratersFilename);
        MovieDatabase.initialize(moviesFilename);
        
        System.out.println("number of movies: " + MovieDatabase.size());
        System.out.println("number of raters: " + tRatings.getRaterSize());   
        
        // print a list of filtered movies and their average ratings 
        int minimalRaters = 1;
        String genre = "Romance";
        YearAfterFilter yearFilter = new YearAfterFilter(1980);
        GenreFilter genreFilter = new GenreFilter(genre);
        
        AllFilters allFilters = new AllFilters();
        allFilters.addFilter(yearFilter);
        allFilters.addFilter(genreFilter);
        
        ArrayList<Rating> aveRatings 
            = tRatings.getAverageRatingsByFilter(minimalRaters, allFilters);
        System.out.println("found " + aveRatings.size() + " movies");

        Collections.sort(aveRatings);
        for (Rating rating: aveRatings) {
            System.out.println(rating.getValue() + " " 
                + MovieDatabase.getYear(rating.getItem()) + " "
                + MovieDatabase.getTitle(rating.getItem()));
            System.out.println("\t" + MovieDatabase.getGenres(rating.getItem()));
        }
    }    
    
    public void printAverageRatingsByDirectorsAndMinutes() {
        ThirdRatings tRatings = new ThirdRatings(ratersFilename);
        MovieDatabase.initialize(moviesFilename);
        
        System.out.println("number of movies: " + MovieDatabase.size());
        System.out.println("number of raters: " + tRatings.getRaterSize());   
        
        // print a list of filtered movies and their average ratings 
        int minimalRaters = 1;
        String directrors = "Spike Jonze,Michael Mann,Charles Chaplin,Francis Ford Coppola";
        DirectorsFilter directorsFilter = new DirectorsFilter(directrors);
        int minMinutes = 30;
        int maxMinutes = 170;
        MinutesFilter minutesFilter = new MinutesFilter(minMinutes, maxMinutes);        
        
        AllFilters allFilters = new AllFilters();
        allFilters.addFilter(directorsFilter);
        allFilters.addFilter(minutesFilter);
        
        ArrayList<Rating> aveRatings 
            = tRatings.getAverageRatingsByFilter(minimalRaters, allFilters);
        System.out.println("found " + aveRatings.size() + " movies");

        Collections.sort(aveRatings);
        for (Rating rating: aveRatings) {
            System.out.println(rating.getValue() + " " 
                 + "Time: " + MovieDatabase.getMinutes(rating.getItem()) + " "
                + MovieDatabase.getTitle(rating.getItem()));
            System.out.println("\t" + MovieDatabase.getDirector(rating.getItem()));
        }
    }       

}