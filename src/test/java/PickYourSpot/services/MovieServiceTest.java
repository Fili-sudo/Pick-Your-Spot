package PickYourSpot.services;

import PickYourSpot.Model.Movie;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.*;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MovieServiceTest {
    @BeforeEach
    void setUp() throws IOException {
        FileSystemService.APPLICATION_FOLDER = ".test";
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomeFolder().toFile());
        UserService.initDatabase();
        MovieService.initDatabase();
        ReservationService.initDatabase();
        LocuriService.initDatabase();
        UserService.empty();
        MovieService.empty();
        ReservationService.emptycol();
        LocuriService.empty();
    }

    @AfterEach
    void tearDown() {
        UserService.empty();
        MovieService.empty();
        ReservationService.emptycol();
        LocuriService.empty();
        UserService.database.close();
        MovieService.database.close();
        ReservationService.database.close();
        LocuriService.database.close();
    }

    @Test
    void testDatabaseIsInitialisedAndNoMovieIsPersisted() {
        assertThat(MovieService.getAllMovies()).isNotNull();
        assertThat(MovieService.getAllMovies()).isEmpty();
    }

    @Test
    void testMovieIsAddedToDatabase() {
        MovieService.addMovie(new Movie("Demolition Man", 1993, "Marco Brambilla", "Sylvester Stallone, Sandra Bullock",7.3, "Thriller", 110));
        assertThat(MovieService.getAllMovies()).isNotEmpty();
        assertThat(MovieService.getAllMovies()).size().isEqualTo(1);
        Movie movie = MovieService.getAllMovies().get(0);
        assertThat(movie).isNotNull();
        assertThat(movie.getTitlu()).isEqualTo("Demolition Man");
        assertThat(movie.getDirector()).isEqualTo("Marco Brambilla");
        assertThat(movie.getPeople()).isEqualTo("Sylvester Stallone, Sandra Bullock");
        assertThat(movie.getRating()).isEqualTo(7.3);
        assertThat(movie.getGen()).isEqualTo("Thriller");
        assertThat(movie.getDurata()).isEqualTo(110);

    }

    @Test
    void testFindAndUpdate() {
        Movie movie = new Movie("Demolition Man", 1993, "Marco Brambilla", "Sylvester Stallone, Sandra Bullock",7.3, "Thriller", 110);
        MovieService.addMovie(movie);
        Movie mov = MovieService.getAllMovies().get(0);
        assertThat(mov).isNotNull();
        assertThat(mov.getTitlu()).isEqualTo("Demolition Man");
        Movie newMovie = new Movie("Another Title", 1993, "Marco Brambilla", "Sylvester Stallone, Sandra Bullock",7.3, "Thriller", 110);
        MovieService.findAndUpdate(movie, newMovie);
        mov = MovieService.getAllMovies().get(0);
        assertThat(mov).isNotNull();
        assertThat(mov.getTitlu()).isEqualTo("Another Title");
    }

    @Test
    void testFindAndRemove() {
        Movie movie = new Movie("Demolition Man", 1993, "Marco Brambilla", "Sylvester Stallone, Sandra Bullock",7.3, "Thriller", 110);
        MovieService.addMovie(movie);
        Movie mov = MovieService.getAllMovies().get(0);
        assertThat(mov).isNotNull();
        MovieService.findAndRemove(movie);
        assertThat(MovieService.getAllMovies()).isEmpty();
    }

    @Test
    void testempty() {
        MovieService.addMovie(new Movie("Demolition Man", 1993, "Marco Brambilla", "Sylvester Stallone, Sandra Bullock",7.3, "Thriller", 110));
        MovieService.addMovie(new Movie("Altu", 1993, "Marco Brambilla", "Sylvester Stallone, Sandra Bullock",7.3, "Thriller", 110));
        assertThat(MovieService.getAllMovies()).size().isEqualTo(2);
        MovieService.empty();
        assertThat(MovieService.getAllMovies()).isEmpty();
    }
}