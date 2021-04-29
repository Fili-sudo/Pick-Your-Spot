package PickYourSpot.services;

import PickYourSpot.Model.Movie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MovieService {

    private static ObservableList<Movie> movieData = FXCollections.observableArrayList();

    public static ObservableList<Movie> getMovieData() {
        return movieData;
    }

    public static void exemplu(){
        movieData.add(new Movie("Demolition Man", 1993, "Marco Brambilla", "Sylvester Stallone, Sandra Bullock",7.3, "Thriller", 110));
        movieData.add(new Movie("Hobbitul",2014, "Peter Jackson", " Ian McKellen, Benedict Cumberbatch", 8.5, "Adventure", 144));
        movieData.add(new Movie("What happened to Monday", 2017, "Tommy Wirkola", "Willem Dafoe, Glenn Close", 7.5, "Action", 123));

    }
}
