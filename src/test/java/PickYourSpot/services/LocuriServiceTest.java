package PickYourSpot.services;

import PickYourSpot.Controllers.TimetableController;
import PickYourSpot.Model.Locuri;
import PickYourSpot.Model.Movie;
import PickYourSpot.Model.Reservation;
import PickYourSpot.exceptions.UsernameAlreadyExistsException;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.*;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class LocuriServiceTest {


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
        TimetableController.setI(1);
        TimetableController.setJ(0);
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
    void testDatabaseIsInitialisedAndNoReservationIsPersisted() {
        assertThat(LocuriService.getAllLocuri()).isNotNull();
        assertThat(LocuriService.getAllLocuri()).isEmpty();
    }

    @Test
    void testAddLocuri(){
        Locuri locuri = new Locuri("movieTitle", "Luni" , 10, 20, new int[5][8]);
        Movie movie = new Movie("movieTitle", 1993, "Marco Brambilla", "Sylvester Stallone, Sandra Bullock",7.3, "Thriller", 110);
        LocuriService.addLocuri(locuri, movie);
        assertThat(LocuriService.getAllLocuri()).isNotEmpty();
        assertThat(LocuriService.getAllLocuri()).size().isEqualTo(1);
        int [][] a = locuri.getSala();
        a[0][0] = 1;
        locuri.setSala(a);
        LocuriService.addLocuri(locuri, movie);
        assertThat(LocuriService.getAllLocuri()).isNotEmpty();
        assertThat(LocuriService.getAllLocuri()).size().isEqualTo(1);
        locuri = LocuriService.getAllLocuri().get(0);
        assertThat(locuri.getSala()).isEqualTo(a);
    }

    @Test
    void testFindByMovie() {
        Locuri locuri = new Locuri("movieTitle", "Luni" , 10, 20, new int[5][8]);
        Movie movie = new Movie("movieTitle", 1993, "Marco Brambilla", "Sylvester Stallone, Sandra Bullock",7.3, "Thriller", 110);
        LocuriService.addLocuri(locuri, movie);
        assertThat(LocuriService.getAllLocuri()).isNotEmpty();
        assertThat(LocuriService.getAllLocuri()).size().isEqualTo(1);
        assertThat(movie.getTimetable().get(TimetableController.getJ()).getProgram().get(TimetableController.getI()-1).getSala()).isEqualTo(locuri.getSala());
        int [][] a = locuri.getSala();
        a[0][0] = 1;
        locuri.setSala(a);
        LocuriService.findByMovie(movie);
        assertThat(movie.getTimetable().get(TimetableController.getJ()).getProgram().get(TimetableController.getI()-1).getSala()).isEqualTo(a);
    }

    @Test
    void testFreeSeats() {
        int [][] a = new int[5][8];
        a[0][0] = a[0][1] = a[0][2] = 1;
        Locuri locuri = new Locuri("movieTitle", "Luni" , 10, 20, a);
        Movie movie = new Movie("movieTitle", 1993, "Marco Brambilla", "Sylvester Stallone, Sandra Bullock",7.3, "Thriller", 110);
        LocuriService.addLocuri(locuri, movie);
        Reservation reservation = new Reservation("david", "movieTitle", "1,2", "Luni", "10:20", "canceled", new int[]{0, 0, 0}, new int[]{0, 1, 2},998928392);
        assertThat(LocuriService.getAllLocuri()).isNotEmpty();
        assertThat(LocuriService.getAllLocuri()).size().isEqualTo(1);
        assertThat(LocuriService.getAllLocuri().get(0).getSala()).isEqualTo(a);
        LocuriService.freeSeats(reservation);
        locuri = LocuriService.getAllLocuri().get(0);
        assertThat(locuri.getSala()).isEqualTo(new int[5][8]);
    }

    @Test
    void testEmpty() {
        int [][] a = new int[5][8];
        a[0][0] = a[0][1] = a[0][2] = 1;
        Locuri locuri = new Locuri("movieTitle", "Luni" , 10, 20, a);
        Movie movie = new Movie("movieTitle", 1993, "Marco Brambilla", "Sylvester Stallone, Sandra Bullock",7.3, "Thriller", 110);
        LocuriService.addLocuri(locuri, movie);
        locuri = new Locuri("anotherMovieTitle", "Luni" , 10, 20, a);
        movie = new Movie("anotherMovieTitle", 1993, "Marco Brambilla", "Sylvester Stallone, Sandra Bullock",7.3, "Thriller", 110);
        LocuriService.addLocuri(locuri, movie);
        assertThat(LocuriService.getAllLocuri()).size().isEqualTo(2);
        LocuriService.empty();
        assertThat(LocuriService.getAllLocuri()).isEmpty();
    }
}