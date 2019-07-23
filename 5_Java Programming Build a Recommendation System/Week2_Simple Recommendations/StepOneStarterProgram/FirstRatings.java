
/**
 * Write a description of FirstRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class FirstRatings {   
    private ArrayList<Movie> movies;
    private ArrayList<Rater> raters;
    
    public FirstRatings() {
        movies = new ArrayList<Movie>();
        raters = new ArrayList<Rater>();
    }
    
    public ArrayList<Movie> loadMovies(String filename) {
        FileResource fr = new FileResource(filename);

        for (CSVRecord record: fr.getCSVParser()) {
            String id = record.get("id");
            String title = record.get("title");
            String year = record.get("year");
            String country = record.get("country");
            String genre = record.get("genre");
            String director = record.get("director");
            int minutes = Integer.parseInt(record.get("minutes"));
            String poster = record.get("poster");
            
            movies.add(new Movie(id, title, year, genre, director, country, poster, minutes));
        }
        return movies;
    }
    
    public void testLoadMovies() {
        String filename = "data/ratedmoviesfull.csv";
        movies = loadMovies(filename);
        
        // number of movies
        System.out.println("number of movies: " + movies.size());
        // for (Movie movie: movies) {
        //     System.out.println(movie);
        // }
        
        // number of comedy movies
        int countComides = 0;
        for (Movie movie: movies) {
            if (movie.getGenres().indexOf("Comedy") > 0) {
                countComides++;
            }
        }
        System.out.println("number of Comedies: " + countComides);
        
        //  number of long movies
        int countLongs = 0;
        for (Movie movie: movies) {
            if (movie.getMinutes() > 150) {
                countLongs++;
            }
        }
        System.out.println("number of Longs: " + countLongs);
        
        // max. number of movies by any director
        HashMap<String, ArrayList<String>> directorsMap = new HashMap<String, ArrayList<String>>();
        for (Movie movie: movies) {
            String title = movie.getTitle();
            for (String director: movie.getDirector().split(", ")) {
                if (directorsMap.containsKey(director)) {
                    directorsMap.get(director).add(title);
                }
                else {
                    ArrayList<String> movieList = new ArrayList<String>();
                    movieList.add(title);
                    directorsMap.put(director, movieList);
                }
            }
        }
        int maxMovies = 0;
        String maxDirector = "";
        for (String director : directorsMap.keySet()) {
            ArrayList<String> movieList = directorsMap.get(director);
            if (maxMovies < movieList.size()) {
                maxMovies = movieList.size();
                maxDirector = director;
            }
        }
        System.out.println("max. number of movies by any director: " + maxMovies);   
        System.out.println("name of the director: " + maxDirector);   
    }
    
    public ArrayList<Rater> loadRaters(String filename) {
        FileResource fr = new FileResource(filename);        
        HashMap<String, Rater> ratersMap = new HashMap<String, Rater>();
        
        for (CSVRecord record: fr.getCSVParser()) {
            String raterId = record.get("rater_id");
            String movieId = record.get("movie_id");
            double ratingValue = Double.parseDouble(record.get("rating"));
            // String time = record.get("time");
            
            if (!ratersMap.containsKey(raterId)) {
                Rater rater = new Rater(raterId);
                rater.addRating(movieId, ratingValue);  
                // System.out.println("rater: " + rater);                
                ratersMap.put(raterId, rater);
            } else {
                Rater rater = ratersMap.get(raterId);
                rater.addRating(movieId, ratingValue);
                // System.out.println("rater: " + rater);
                ratersMap.put(raterId, rater);
            }          
        }

        ArrayList<Rater> raters = new ArrayList<Rater>();
        for (Rater rater: ratersMap.values()) {
            raters.add(rater);
        }
        return raters;
    }        
    
    public void testLoadRaters() {
        String filename = "data/ratings.csv";
        // String filename = "data/ratings.csv";
        raters = loadRaters(filename);
        
        // number of raters
        System.out.println("number of raters: " + raters.size());
        // for (Rater rater: raters) {
        //     System.out.println(rater);
        // }        
        
        // number of ratings for a particular raters
        String raterId = "193";
        for (Rater rater: raters) {
            if (raterId.equals(rater.getID())) {
                System.out.println("number of ratings for a particular rater, " 
                    + raterId + ": "                
                    + rater.numRatings());                
                break;
            }
        }
        
        // max. number of ratings by any rater
        int maxRatings = 0;
        ArrayList<String> maxRaters = new ArrayList<String>();
        for (Rater rater: raters) {
            if (maxRatings == rater.numRatings()) {
                maxRaters.add(rater.getID());
            } else if (maxRatings < rater.numRatings()) {
                maxRaters.clear();
                maxRaters.add(rater.getID());
                maxRatings = rater.numRatings();
            }
        }
        System.out.println("max. number of ratings by any rater: " + maxRatings 
            + "\t" + "raters: " + maxRaters);
            
        // number of ratings a particular movie has
        String movieId = "1798709";
        int numRatings = 0;
        for (Rater rater: raters) {
            if (rater.hasRating(movieId)) {
                numRatings++;
            }
        }
        System.out.println("number of ratings a particular movie, " + movieId 
            + ", has: " + numRatings);
            
        // number of different movies rated by all raters
        HashSet<String> moviesSet = new HashSet<String>();
        for (Rater rater: raters) {
            ArrayList<String> movieList = rater.getItemsRated();
            moviesSet.addAll(movieList);
        }
        System.out.println("number of different movies rated by all raters: " 
            + moviesSet.size());
    }
}
