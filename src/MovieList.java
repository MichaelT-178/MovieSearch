//javac -d bin -cp bin src/MovieList.java
//java -cp bin MovieList

/**
 * Creates a MovieList object and returns different MovieList options based
 * on the user specified choice.
 *
 * @author Logan Williams
 * @author Michael Totaro
 * @author Ryan Morgan
 */
public class MovieList {

    /** Array of movie objects */
    private Movie[] movieList;

    /** Number of movies in array */
    private int movieCount;

    /**
     * Constructor for MovieList object. Sets
     * movieCount to 0.
     * @param size Given size of MovieList object
     */
    public MovieList(int size) {
        movieList = new Movie[size];
        this.movieCount = 0;
    }

    /**
     * Creates a new movie object and adds it to movieList.
     *
     * @param title The title of the movie object
     * @param year The year the movie object was released.
     * @param length The length of the movie object
     * @param genre The genre of the movie object
     */
    public void addMovie(String title, String year, String length, String genre) {
        Movie movie = new Movie(title, year, length, genre);
        movieList[movieCount] = movie;
        movieCount++;
    }

    /**
     * Adds movie to MovieList object if Movie object already exists (search results list)
     *
     * @param movie Movie object
     */
    public void addMovie(Movie movie) {
        movieList[movieCount] = movie;
        movieCount++;
    }

    /**
     * Accepts a keyword as a parameter and creates a MovieList containing
     * movies whose title contains the keyword.
     *
     * @param keyword Word searched by the user
     * @return All movies with that contain the given keyword
     */
    public MovieList titleSearch(String keyword) {
        MovieList searchResults = new MovieList(movieCount);
        for (int i = 0; i < movieCount; i++) {
            Movie movie = movieList[i];
            String title = movie.getTitle();
            String newTitle = title.toLowerCase();
            if (newTitle.contains(keyword)) {
                searchResults.addMovie(movie);
            }
        }
        return searchResults;
    }

    /**
     * Accepts a year as a parameter and creates a MovieList containing
     * movies that were released in that year.
     *
     * @param yearSearched Year searched by the user
     * @return All movies that were released in the given year.
     */
    public MovieList yearSearch(int yearSearched) {
        MovieList searchResults = new MovieList(movieCount);
        for (int i = 0; i < movieCount; i++) {
            Movie movie = movieList[i];
            String year = movie.getYear();
            int yearInt = Integer.parseInt(year);
            if (yearSearched == yearInt) {
                searchResults.addMovie(movie);
            }
        }
        return searchResults;
    }


    /**
     * Accepts a movie length and an option value. If the option value is 0, movies with
     * a length shorter than the given integer will be added to searchResults. If the
     * option value is 1, movies with a length equal to the given integer will be added to
     * searchResults. If the option value is 2, movies with a length longer than the given
     * integer will be added to searchResults.
     *
     * @param lengthSearched The length (in minutes) of the value searched by the user.
     * @param option An integer representing whether a user want's to display movies
     *        longer, equal to, or shorter in length then the given integer.
     * @return All movies that were either longer, equal, or shorter in length
     *         than the given integer based on the users choice.
     */
    public MovieList lengthSearch(int lengthSearched, int option) {
        MovieList searchResults = new MovieList(movieCount);
        for (int i = 0; i < movieCount; i++) {
            Movie movie = movieList[i];
            int length = movie.getLength();
            if (option == 0) {
                if (length < lengthSearched) {
                    searchResults.addMovie(movie);
                }
            } else if (option == 1) {
                if (length == lengthSearched) {
                    searchResults.addMovie(movie);
                }
            } else if (option == 2) {
                if (length > lengthSearched) {
                    searchResults.addMovie(movie);
                }
            }
        }
        return searchResults;
    }

    /**
     * Returns all movies that contain genres that the user entered.
     *
     * @param arr A string array containing all the genres entered by the user.
     * @return All movies that contain at least one genre in the given array.
     */
    public MovieList genreSearch(String[] arr) {
        MovieList searchResults = new MovieList(movieCount);
        for (int i = 0; i < movieCount; i++) {
            Movie movie = movieList[i];
            String genres = movie.getGenre();
            genres = genres.toLowerCase();
            for (int j = 0; j < arr.length; j++) {
                if (genres.contains(arr[j])) {
                    searchResults.addMovie(movie);
                    break;
                }
            }
        }
        return searchResults;
    }

    /**
     * Returns a MovieList as a string.
     * @return A MovieList as a string.
     */
    public String toString() {
        String list = "";
        for (int i = 0; i < movieCount; i++) {
            Movie movie = movieList[i];
            list += "• " + movie.toString() + "\n";
        }
        return list;
    }
}
