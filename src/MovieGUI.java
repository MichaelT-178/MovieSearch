//javac -d bin -cp bin src/MovieGUI.java
//java -cp bin MovieGUI

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 * GUI application for
 *
 * @author Michael Totaro
 * @author Logan Williams
 * @author Ryan Morgan
 */
public class MovieGUI extends JFrame implements ActionListener {

    /** MovieList Object for creating lists */
    private MovieList movieList;

    /** Label for the label, "Please choose an option from the menu" */
    private JLabel label;

    /** button used to dispaly movies */
    private JButton displayMovies;

    /** button used to display movies with users string input */
    private JButton displayString;

    /** button used to display movies made in a certain year */
    private JButton displayYear;

    /** button used to display movies in a certain genre */
    private JButton displayGenre;

    /** button used to display movies of a certain length */
    private JButton displayLength;

    /** area used to display information */
    private JTextArea area;

    /** Scroll bar for the area JTextArea */
    private JScrollPane scrollPane;

    /** Width of the frame */
    public static final int SET_SIZE_WIDTH = 1650;

    /** Length of the frame */
    public static final int SET_SIZE_HEIGHT = 1080;

    /** Width of the "area" JTextArea  */
    public static final int AREA_WIDTH = 65;

    /** Height of the "area" JTextArea */
    public static final int AREA_HEIGHT = 53;

    /** The earliest year a movie has to have been made by to appear in search results */
    public static final int MIN_YEAR = 1890;

    /** The lastest year a movie has to have been made by to appear in search results */
    public static final int MAX_YEAR = 2020;

    /**
     * GUI constructor
     * @param movieList List of movies.
     */
    public MovieGUI(MovieList movieList) {
        this.movieList = movieList;
        setTitle("Movie Search GUI");
        setSize(SET_SIZE_WIDTH, SET_SIZE_HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Container c = getContentPane();
        c.setLayout(new FlowLayout());

        label = new JLabel("Please choose an option from the menu: ");
        displayMovies = new JButton("Display All Movies");
        displayString = new JButton("Search By String");
        displayYear = new JButton("Search By Year");
        displayGenre = new JButton("Search By Genre");
        displayLength = new JButton("Search By Length");

        area = new JTextArea("Waiting for you to choose an option...", AREA_HEIGHT, AREA_WIDTH);

        scrollPane = new JScrollPane(area);

        c.setBackground(Color.blue);
        label.setForeground(Color.green);

        c.add(label);
        c.add(displayMovies);
        c.add(displayString);
        c.add(displayYear);
        c.add(displayGenre);
        c.add(displayLength);
        c.add(scrollPane);

        area.setEditable(false);

        setVisible(true);

        displayMovies.addActionListener(this);
        displayString.addActionListener(this);
        displayYear.addActionListener(this);
        displayGenre.addActionListener(this);
        displayLength.addActionListener(this);
    }

    /**
     * Actions to perform as a result of button clicks.
     * @param e event that initiated action
     */
    public void actionPerformed(ActionEvent e) {
        int i;

        if (e.getSource() == displayMovies) {
            area.setText("");
            area.append("•You chose the \"Display All Movies\" option.\n");

            area.append("\nDisplaying results for all movie.\n\n");
            String results = movieList.toString();
            area.append(results);

        }

        if (e.getSource() == displayString) {

            area.setText("");
            area.append("•You chose the \"Search By String\" option.\n");

            String stringAsk = JOptionPane.showInputDialog("Please enter a string");
            String theString = stringAsk.toLowerCase();

            MovieList results = movieList.titleSearch(theString);
            String resultList = results.toString();

            while ((resultList.equals("")) || (resultList.equals(" "))) {
                stringAsk = JOptionPane.showInputDialog("No results were found for \"" +
                                          stringAsk + "\".\nPlease enter another string");

                theString = stringAsk.toLowerCase();
                results = movieList.titleSearch(theString);
                resultList = results.toString();
            }
            resultList = results.toString();

            area.append("\nDisplaying search results for items containing the string \""
                                                                    + stringAsk + "\".\n\n");
            area.append(resultList);
        }

        if (e.getSource() == displayYear) {
            area.setText("");
            area.append("•You chose the \"Search By Year\" option.\n");

            String yearAsk = JOptionPane.showInputDialog("Please enter a year");

            while (!(checkInt(yearAsk))) {
                if (yearAsk == null) {
                    break;
                }
                yearAsk = JOptionPane.showInputDialog("Please enter a valid year as an integer.");
            }

            int theYear = Integer.parseInt(yearAsk);

            while ((theYear < MIN_YEAR) || (MAX_YEAR < theYear)) {
                yearAsk = JOptionPane.showInputDialog("Please enter a year that is" +
                                                        " between the\nyears 1890 - 2020.");
                theYear = Integer.parseInt(yearAsk);
            }


            area.append("\nDisplaying search results for movies released in the year \""
                                                                      + yearAsk + "\".\n\n");

            MovieList results = movieList.yearSearch(theYear);
            String finalResults = results.toString();
            area.append(finalResults);

            if (finalResults.equals("")) {
                area.append("•No results found for movies released in the year " + yearAsk + ".");
            }
        }

        if (e.getSource() == displayGenre) {
            area.setText("");
            area.append("•You chose the \"Search By Genre\" option.\n");

            String displayMenu = "Please enter a genre from the list:\n" +
                        "•Action\n•Adventure\n•Animation\n•Biography\n•Comedy\n•Crime\n" +
                        "•Documentary\n•Drama\n•Family\n•Fantasy\n•History\n•Horror\n" +
                        "•Musical\n•Mystery\n•Romance\n•Sci-Fi\n•Sport\n•Thriller\n•War\n" +
                        "•Western\n\n *If you enter multiple genres seperate them only " +
                        "with \",\" characters.";

            String genreAsk = JOptionPane.showInputDialog(displayMenu);

            String[] theGenres = {"action", "adventure", "animation", "biography", "comedy",
                                  "crime", "documentary", "drama", "family", "fantasy", "history",
                                  "horror", "musical", "mystery", "romance", "sci-fi", "scifi",
                                  "sport", "thriller", "war", "western"};

            String theString = genreAsk;

            theString = theString.toLowerCase();
            theString = theString.replace(",", "");

            String wrongString = theString;
            theString = theString.replace(" ", "");

            int numOfGenres = 0;
            String enteredGenres = "";

            for (i = 0; i < theGenres.length; i++) {
                if (theString.contains(theGenres[i])) {
                    theString = theString.replace(theGenres[i], "");
                    wrongString = wrongString.replace(theGenres[i], " ");
                    enteredGenres += theGenres[i] + " ";
                }
            }

            String error = "";
            String[] splitString = wrongString.split(" ");

            for (i = 0; i < splitString.length; i++) {
                if (!(splitString[i].equals(""))) {
                    error += ("• \"" + splitString[i] + "\" is not a valid genre.\n");
                }
            }

            String theText = "";

            while (!(theString.equals(""))) {
                theText = "";
                genreAsk = JOptionPane.showInputDialog("There were errors in your search:\n" +
                                                                  error + "\n" + displayMenu);

                theString = genreAsk;
                theString = theString.toLowerCase();

                theString = theString.replace(",", "");
                wrongString = theString;
                theString = theString.replace(" ", "");

                for (i = 0; i < theGenres.length; i++) {
                    if (theString.contains(theGenres[i])) {
                        theString = theString.replace(theGenres[i], "");
                        wrongString = wrongString.replace(theGenres[i], " ");
                        theText += theGenres[i] + " ";
                    }
                }

                error = "";
                splitString = wrongString.split(" ");

                for (i = 0; i < splitString.length; i++) {
                    if (!(splitString[i].equals(""))) {
                        error += ("• \"" + splitString[i] + "\" is not a valid genre.\n");
                    }
                }
            }

            String theDisplayString = "";
            String[] arr;

            if (theText.equals("")) {
                arr = enteredGenres.split(" ");
            }

            else {
                arr = theText.split(" ");
            }

            for (i = 0; i < arr.length; i++) {
                if ((i == arr.length - 1) && (arr.length > 2)) {
                    theDisplayString += "and ";
                }
                theDisplayString += "\"" + arr[i] + "\"";
                if ((i != arr.length - 1) && (arr.length > 2)) {
                    theDisplayString += ", ";
                }
                if ((arr.length == 2) && (i != 1)) {
                    theDisplayString += " and ";
                }
            }

            MovieList theResults = movieList.genreSearch(arr);
            String theResultList = theResults.toString();

            if (theResultList.equals("")) {
                area.append("\n\nNo results found for " + theDisplayString + " movies.");
            }
            else {
                area.append("\nDisplaying results for " + theDisplayString + " movies.\n\n");
            }

            area.append(theResultList);
        }

        if (e.getSource() == displayLength) {
            area.setText("");
            area.append("•You chose the \"Search By Length\" option.\n");

            String runtimeAsk = JOptionPane.showInputDialog("Please enter a runtime "
                                                                 + "(in minutes).");
            while (!(checkInt(runtimeAsk))) {
                if (runtimeAsk == null) {
                    break;
                }
                runtimeAsk = JOptionPane.showInputDialog("Please enter a runtime "
                                                        + "(in minutes) as an integer.");
            }

            int runtime = Integer.parseInt(runtimeAsk);

            Object[] choices = {"Shorter", "Equal", "Longer"};

            int len = JOptionPane.showOptionDialog(null, "The runtime you entered was " +
                runtimeAsk + " minutes. How long do you want the movies \nlisted to be in " +
                "respect to the given length?", "Runtime Question",
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                choices, choices[2]);

            MovieList results = movieList.lengthSearch(runtime, len);
            String theResults = results.toString();

            if ((len == 0) && (!(theResults.equals("")))) {
                area.append("\nDisplaying results for movies shorter than " + runtime +
                            " minutes in length.\n\n");
                area.append(theResults);

            } else if ((len == 1) && (!(theResults.equals("")))) {
                area.append("\nDisplaying results for movies equal to " + runtime +
                            " minutes in length.\n\n");
                area.append(theResults);

            } else if ((len == 2) && (!(theResults.equals("")))) {
                area.append("\nDisplaying results for movies longer than " + runtime +
                            " minutes in length.\n\n");
                area.append(theResults);
            }


            if ((len == 0) && (theResults.equals(""))) {
                area.append("\n•No results were found with a runtime shorter than " + runtime +
                            " minutes in length.");
            } else if ((len == 1) && (theResults.equals(""))) {
                area.append("\n•No results were found with a runtime of " + runtime +
                            " minutes in length.");
            } else if ((len == 2) && (theResults.equals(""))) {
                area.append("\n•No results were found with a runtime longer than " + runtime +
                            " minutes in length.");
            }
        }
    }

    /**
     * Returns true if the string passed is made of all integers.
     *
     * @param str The string that will be check if it's an integer or not.
     * @return true if All characters in the string are integers. Returns false if
     *         string is not all integers or is null.
     */
    public static boolean checkInt(String str) {
        int theInt;

        if (str == null || str.equals("")) {
            return false;
        }

        try {
            theInt = Integer.parseInt(str);
            return true;
        } catch (NumberFormatException m) {
            return false;
        }
    }

}
