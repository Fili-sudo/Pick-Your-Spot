package PickYourSpot.services;

import PickYourSpot.Model.Days;
import PickYourSpot.Model.Movie;
import PickYourSpot.Model.Reservation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.dizitart.no2.Cursor;
import org.dizitart.no2.Document;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.NitriteCollection;
import org.dizitart.no2.filters.Filters;

import java.util.ArrayList;
import java.util.List;

import static PickYourSpot.services.FileSystemService.getPathToFile;

public class MovieService {

    private static NitriteCollection collection;
    private static ObservableList<Movie> movieData = FXCollections.observableArrayList();

    public static ObservableList<Movie> getMovieData() {
        return movieData;
    }

    public static void initDatabase() {
        FileSystemService.initDirectory();
        Nitrite database = Nitrite.builder()
                .filePath(getPathToFile("movie-database.db").toFile())
                .openOrCreate("test", "test");

        collection = database.getCollection("filme");
    }
    public static List<Movie> getAllMovies(){
        List<Movie> list = new ArrayList<>();
        Cursor cursor = collection.find();
        for (Document doc : cursor){
            Movie mov = new Movie(doc.get("movieTitle", String.class), doc.get("anAparitie", Integer.class)
                    , doc.get("director", String.class), doc.get("people", String.class), doc.get("rating", Double.class)
                    , doc.get("gen", String.class), doc.get("durata", Integer.class));

            list.add(mov);
        }
        return list;
    }

    public static void addMovie(Movie movie){
        Document doc = Document.createDocument("movieTitle", movie.getTitlu())
                .put("anAparitie", movie.getAn_aparitie()).put("director", movie.getDirector())
                .put("people", movie.getPeople()).put("rating", movie.getRating()).put("gen" ,movie.getGen())
                .put("durata", movie.getDurata());
        collection.insert(doc);
    }
    public static void populate(){
        Cursor cursor = collection.find();
        for (Document doc : cursor){
           Movie mov = new Movie(doc.get("movieTitle", String.class), doc.get("anAparitie", Integer.class)
           , doc.get("director", String.class), doc.get("people", String.class), doc.get("rating", Double.class)
           , doc.get("gen", String.class), doc.get("durata", Integer.class));

           movieData.add(mov);
        }
    }

    public static void findAndUpdate(Movie movie, Movie newMovie) {
        Cursor cursor = collection.find();
        for (Document doc : cursor) {
            Movie mov = new Movie(doc.get("movieTitle", String.class), doc.get("anAparitie", Integer.class)
                    , doc.get("director", String.class), doc.get("people", String.class), doc.get("rating", Double.class)
                    , doc.get("gen", String.class), doc.get("durata", Integer.class));
            if (movie.equals(mov)) {
                doc.put("movieTitle", newMovie.getTitlu());
                doc.put("anAparitie", newMovie.getAn_aparitie());
                doc.put("director", newMovie.getDirector());
                doc.put("people", newMovie.getPeople());
                doc.put("rating", newMovie.getRating());
                doc.put("gen", newMovie.getGen());
                doc.put("durata", newMovie.getDurata());
                collection.update(doc);
            }
        }
    }

    public static  void findAndRemove(Movie movie){
        Cursor cursor = collection.find();
        for (Document doc : cursor) {
            Movie mov = new Movie(doc.get("movieTitle", String.class), doc.get("anAparitie", Integer.class)
                    , doc.get("director", String.class), doc.get("people", String.class), doc.get("rating", Double.class)
                    , doc.get("gen", String.class), doc.get("durata", Integer.class));
            if (movie.equals(mov)) {
               collection.remove(doc);
            }
        }
    }

    public static void exemplu(){
       addMovie(new Movie("Demolition Man", 1993, "Marco Brambilla", "Sylvester Stallone, Sandra Bullock",7.3, "Thriller", 110));
       addMovie(new Movie("Hobbitul",2014, "Peter Jackson", " Ian McKellen, Benedict Cumberbatch", 8.5, "Adventure", 144));
       addMovie(new Movie("What happened to Monday", 2017, "Tommy Wirkola", "Willem Dafoe, Glenn Close", 7.5, "Action", 123));

    }
    public static void empty(){
        collection.remove(Filters.ALL);
    }

    public static void emptyMovieData(){
        movieData.removeAll(movieData);
    }
}
