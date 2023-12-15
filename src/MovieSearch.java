//javac -d bin -cp bin src/MovieSearch.java
//java -cp bin MovieSearch <followed by command line arguments>
//java -cp bin MovieSearch test_files/shortmovielist.txt
//java -cp bin MovieSearch test_files/movies.txt

import java.util.*;
import java.io.*;

/**
 * Accepts a file containing movies and then makes a MovieList out of the movies
 * in a file.
 *
 * @author Logan Williams
 * @author Michael Totaro
 * @author Ryan Morgan
 */
public class MovieSearch {

    /**
     * Assigns a variable to first command line argument. Creates a Scanner for the
     * file by calling a method. Creates a new MovieList using the movies in a file.
     * Passes the new MovieList as an argument to create a new MovieGUI object.
     *
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java -cp bin MovieSearch infile");
            System.exit(1);
        }
        String file = args[0];
        Scanner input = getInput(file);
        input.close();
        MovieList movieList = getMovieList(file);
        new MovieGUI(movieList);
    }

    /**
     * Creates a scanner for the input .txt file.
     *
     * @param file The command line argument containing the filename
     * @return A scanner for the input file.
     */
    public static Scanner getInput(String file) {
        Scanner input = null;
        try {
            input = new Scanner(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            System.exit(1);
        }
        return input;
    }

    /**
     * Iterates through the movie file determing the size of the movieList then
     * creates movie objects out of all the movies in the file and adds them to
     * the MovieList. Once the loop terminates the MovieList will be returned.
     *
     * @param file The file to be read.
     * @return A MovieList that contains all the movies from the input file.
     */
    public static MovieList getMovieList(String file) {

        Scanner inputForSize = getInput(file);
        int size = 0;

        inputForSize.nextLine();
        while (inputForSize.hasNextLine()) {
            String newline = inputForSize.nextLine();
            size ++;
        }
        inputForSize.close();

        Scanner inputForMovies = getInput(file);
        MovieList movieList = new MovieList(size);

        inputForMovies.nextLine();
        while (inputForMovies.hasNextLine()) {
            String movieInfo = inputForMovies.nextLine();
            String[] movieInfoArray = movieInfo.split("\t");
            movieList.addMovie(movieInfoArray[0], movieInfoArray[1],
                               movieInfoArray[2], movieInfoArray[3]);
        }
        inputForMovies.close();
        return movieList;
    }
}
