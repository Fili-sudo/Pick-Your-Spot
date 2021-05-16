package PickYourSpot.services;

import PickYourSpot.Model.Movie;
import PickYourSpot.Model.Reservation;
import PickYourSpot.Model.User;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.*;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ReservationServiceTest {
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
    void testReservationIsAddedToDatabase() {
        Reservation reservation = new Reservation("david", "TitluDeFilm", "1,2", "Luni", "10:20", "canceled", new int[]{0, 1, 2}, new int[]{0, 1, 2},998928392);
        ReservationService.addReservation(reservation);
        assertThat(ReservationService.getAllReservations()).isNotEmpty();
        assertThat(ReservationService.getAllReservations()).size().isEqualTo(1);
        Reservation res = ReservationService.getAllReservations().get(0);
        assertThat(res).isNotNull();
        assertThat(res.getMovieTitle()).isEqualTo("TitluDeFilm");
        assertThat(res.getUser()).isEqualTo("david");
        assertThat(res.getSeats()).isEqualTo("1,2");
        assertThat(res.getWeekDay()).isEqualTo("Luni");
        assertThat(res.getTime()).isEqualTo("10:20");
        assertThat(res.getStatus()).isEqualTo("canceled");
        assertThat(res.getRes_no()).isEqualTo(998928392);
    }

    @Test
    void testPopulateByUser() {
        Reservation reservation = new Reservation("david", "TitluDeFilm", "1,2", "Luni", "10:20", "canceled", new int[]{0, 1, 2}, new int[]{0, 1, 2},998928392);
        ReservationService.addReservation(reservation);
        assertThat(ReservationService.getAllReservations()).isNotEmpty();
        assertThat(ReservationService.getAllReservations()).size().isEqualTo(1);
        ReservationService.populate("david");
        assertThat(ReservationService.getReservationData()).size().isEqualTo(1);
        reservation = ReservationService.getReservationData().get(0);
        assertThat(reservation).isNotNull();
        assertThat(reservation.getMovieTitle()).isEqualTo("TitluDeFilm");
        assertThat(reservation.getUser()).isEqualTo("david");
        assertThat(reservation.getSeats()).isEqualTo("1,2");
        assertThat(reservation.getWeekDay()).isEqualTo("Luni");
        assertThat(reservation.getTime()).isEqualTo("10:20");
        assertThat(reservation.getStatus()).isEqualTo("canceled");
        assertThat(reservation.getRes_no()).isEqualTo(998928392);
        ReservationService.getReservationData().removeAll(ReservationService.getReservationData());
        ReservationService.populate("ciprian");
        assertThat(ReservationService.getReservationData()).size().isEqualTo(0);
    }

    @Test
    void testFind() {
        Reservation reservation = new Reservation("david", "TitluDeFilm", "1,2", "Luni", "10:20", "pending", new int[]{0, 1, 2}, new int[]{0, 1, 2},998928392);
        ReservationService.addReservation(reservation);
        assertThat(ReservationService.getAllReservations()).isNotEmpty();
        assertThat(ReservationService.getAllReservations()).size().isEqualTo(1);
        ReservationService.populate("david");
        assertThat(ReservationService.getReservationData()).size().isEqualTo(1);
        ReservationService.find(reservation);
        reservation = ReservationService.getAllReservations().get(0);
        assertThat(reservation.getStatus()).isEqualTo("canceled");
    }

    @Test
    void testFinAndAccept() {
        Reservation reservation = new Reservation("david", "TitluDeFilm", "1,2", "Luni", "10:20", "pending", new int[]{0, 1, 2}, new int[]{0, 1, 2},998928392);
        ReservationService.addReservation(reservation);
        assertThat(ReservationService.getAllReservations()).isNotEmpty();
        assertThat(ReservationService.getAllReservations()).size().isEqualTo(1);
        ReservationService.populate("david");
        assertThat(ReservationService.getReservationData()).size().isEqualTo(1);
        ReservationService.findAndAccept(reservation);
        reservation = ReservationService.getAllReservations().get(0);
        assertThat(reservation.getStatus()).isEqualTo("accepted");
    }

    @Test
    void testEmptyCol() {
        ReservationService.addReservation(new Reservation("david", "TitluDeFilm", "1,2", "Luni", "10:20", "pending", new int[]{0, 1, 2}, new int[]{0, 1, 2},998928392));
        ReservationService.addReservation(new Reservation("cipri", "TitluDeFilm", "1,2", "Luni", "10:20", "pending", new int[]{0, 1, 2}, new int[]{0, 1, 2},998928392));
        assertThat(ReservationService.getAllReservations()).size().isEqualTo(2);
        ReservationService.emptycol();
        assertThat(ReservationService.getAllReservations()).isEmpty();
    }
}