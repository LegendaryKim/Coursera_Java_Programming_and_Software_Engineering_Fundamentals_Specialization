
/**
 * Write a description of RecommendationRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class RecommendationRunner implements Recommender{
     
    public ArrayList<String> getItemsToRate() {
        ArrayList<String> moviesToRate = new ArrayList<String>();
        
        String[] genres = {"Action", "Thriller", "Biography", "Drama", "Romance", "Comedy", "Animation", "Adventure", "Horror", 
                           "Fantasy", "Mystery", "Music", "Documentary", "Sci-Fi", "War", "Crime", "Film-Noir"};
        int yearAfter = 1980;            
        // YearAfterFilter
        YearAfterFilter yearFilter = new YearAfterFilter(yearAfter);        
            
        for (int i=0; i<genres.length; i++) {          
            // GenreFilter
            GenreFilter genreFilter = new GenreFilter(genres[i]);
        
            // Allfilters
            AllFilters allFilters = new AllFilters();
            // Exception on "Film-Noir" movies from YearAfterFilter
            if (genres[i] != "Film-Noir"){
                allFilters.addFilter(yearFilter);
            }
            allFilters.addFilter(genreFilter);
                
            ArrayList<String> movies = MovieDatabase.filterBy(allFilters);           
            Random rand = new Random();    
            String MovieId = movies.get(rand.nextInt(movies.size()));
                
            moviesToRate.add(MovieId);       
        }
               
        return moviesToRate;
    }
    
    private void printErrorZeroLength(){
        System.out.println("Error!!!: The length of recommended movies is zero");
    }
    
    public void printRecommendationsFor (String webRaterID) {
        FourthRatings fr = new FourthRatings();
        
        int numSimilarRaters = 1;
        int minimalRaters = 1;
        
        ArrayList<Rating> ratings = fr.getSimilarRatings(webRaterID, numSimilarRaters, minimalRaters);
        
        if (ratings.size() == 0) {
            printErrorZeroLength();
        }        
        
        // CSS
        System.out.println("<style>");
        System.out.println("h2 {\n" + 
                           "   font-family: Arial;\n" +
                           "   width: 100%;\n" +
                           "   text-align: center;\n" + 
                           "   border: 1px solid #ddd;\n" +
                           "    padding: 8px;\n}");
        System.out.println("TABLE {\n" +
                           "   border: 1px solid black;\n" +
                           "   border-collapse: collapse;\n" +
                           "   text-align: center;\n" +
                           "   margin: 0 auto;\n}");
        System.out.println("TD {\n" + 
                           "   border: 1px solid black;\n" +
                           "   padding: 5px;\n" +
                           "   vertical-align: middle;\n}");
        System.out.println("TH {\n" + 
                           "   border: 1px solid black;\n" +
                           "   padding: 5px;\n}");
        System.out.println("TR:nth-child(even) {\n" + 
                           "   background-color: #f2f2f2;\n}");
        System.out.println("</style>");
        
        // JavaScript for recommendation table
        System.out.println("<h2> Hwanpyo Kim recommends the following movies based on your taste. [08/15/2019]!!!</h2>");
        System.out.println("<table id=\"recommend\">\n" + 
                           "   <tr>\n" + 
                           "       <th>#</th>\n" +
                           "       <th>Poster</th>\n" + 
                           "       <th>Title</th>\n" + 
                           "       <th>Genre</th>\n" +
                           "       <th>Year</th>\n" + 
                           "       <th>Time</th>\n" +
                           "   </tr>");
        for(int i=0; i < Math.min(ratings.size(), 10); i++) {
            int num = i+1;
            System.out.println("      <td>" + num + "</td>");
            System.out.println("      <td><img src=\"data/" + MovieDatabase.getPoster(ratings.get(i).getItem()).substring(7) + "\" " + 
                                      "height=\"100\">\n" +
                               "      </td>");
            System.out.println("      <td>"+ MovieDatabase.getTitle(ratings.get(i).getItem()) + "</td>");
            System.out.println("      <td>"+ MovieDatabase.getGenres(ratings.get(i).getItem()) + "</td>");
            System.out.println("      <td>"+ MovieDatabase.getYear(ratings.get(i).getItem()) + "</td>");
            System.out.println("      <td>"+ MovieDatabase.getMinutes(ratings.get(i).getItem()) + " Min."+"</td>");
            System.out.println("   </tr>");
        }       
    }
}
