package PickYourSpot.Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Arrays;
import java.util.Objects;

public class Reservation implements java.io.Serializable{

    private String user;
    private String movieTitle;
    private String seats;
    private String weekDay;
    private String time;
    private String status;
    private int []row;
    private int []column;
    private int res_no;

    private final StringProperty movieTitlep;
    private final IntegerProperty reservation;


    public Reservation(String user, String movieTitle, String seats, String weekDay, String time, String status, int[] row, int[] column, int res_no) {
        this.user = user;
        this.movieTitle = movieTitle;
        this.seats = seats;
        this.weekDay = weekDay;
        this.time = time;
        this.status = status;
        this.row = row;
        this.column = column;
        this.res_no = res_no;

        movieTitlep = new SimpleStringProperty(movieTitle);
        reservation = new SimpleIntegerProperty(res_no);
    }

    public String getMovieTitlep() {
        return movieTitlep.get();
    }

    public StringProperty movieTitlepProperty() {
        return movieTitlep;
    }

    public int getReservation() {
        return reservation.get();
    }

    public IntegerProperty reservationProperty() {
        return reservation;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getSeats() {
        return seats;
    }

    public void setSeats(String seats) {
        this.seats = seats;
    }

    public String getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(String weekDay) {
        this.weekDay = weekDay;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int[] getRow() {
        return row;
    }

    public void setRow(int[] row) {
        this.row = row;
    }

    public int[] getColumn() {
        return column;
    }

    public void setColumn(int[] column) {
        this.column = column;
    }

    public int getRes_no() {
        return res_no;
    }

    public void setRes_no(int res_no) {
        this.res_no = res_no;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return res_no == that.res_no && Objects.equals(user, that.user) && Objects.equals(movieTitle, that.movieTitle) && Objects.equals(seats, that.seats) && Objects.equals(weekDay, that.weekDay) && Objects.equals(time, that.time) && Objects.equals(status, that.status) && Arrays.equals(row, that.row) && Arrays.equals(column, that.column);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(user, movieTitle, seats, weekDay, time, status, res_no);
        result = 31 * result + Arrays.hashCode(row);
        result = 31 * result + Arrays.hashCode(column);
        return result;
    }
}
