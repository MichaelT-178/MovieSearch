//javac -d bin -cp bin src/Movie.java
//java -cp bin Movie

/**
 * Movie class for MovieList client code.
 *
 * @author Logan Williams
 * @author Michael Totaro
 * @author Ryan Morgan
 */
public class Movie {

    /** Title of the movie object */
    private String title;

    /** Year the movie object was released */
    private String year;

    /** Runtime of the movie object in minutes */
    private int length;

    /** Genre (or genres) of the movie object */
    private String genre;


    /**
     * Constructor for the Movie object. Initializes the title, year, length, and genre
     * fields to the corresponding parameter values.
     *
     * @param title The title of the passed movie object.
     * @param year The year the passed movie object was released.
     * @param length The runtime (in minutes) of the passed movie object.
     * @param genre The genre (or genres) of the passed movie object.
     */
    public Movie(String title, String year, String length, String genre) {
        this.title = title;
        this.year = year;
        this.length = Integer.parseInt(length);
        this.genre = genre;
    }


    /**
     * Return's the movies title field.
     *
     * @return The movies title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Return's the movies year field.
     *
     * @return The year the movie was released
     */
    public String getYear() {
        return year;
    }

    /**
     * Return's the movies length field.
     *
     * @return The length of the movie (in minutes)
     */
    public int getLength() {
        return length;
    }

    /**
     * Return's the movies genre field.
     *
     * @return The movies genre (or genres).
     */
    public String getGenre() {
        return genre;
    }

    /**
     * Returns true if the title, year, length, and genre of the passed object are
     * the same as the title, year, length, and genre of this object.
     *
     * @param o The passed object that is being compared to this object
     * @return true if this object and the passed object have the same title, year, length,
     *         and genre. Else, return false.
     */
    public boolean equals(Object o) {
        if (o instanceof Movie) {
            Movie m = (Movie)o;
            return title.equals(m.getTitle()) && year.equals(m.getYear()) && length == m.getLength()
                   && genre.equals(m.getGenre());
        } else {
            return false;
        }
    }

    /**
     * Converts movie object to a string.
     *
     * @return A string of the movie object.
     */
    public String toString() {
        return title + " (" + year + ") " + length + " minutes. " + genre;
    }
}
