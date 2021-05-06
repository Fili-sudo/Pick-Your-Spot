package PickYourSpot.Controllers;

import PickYourSpot.Main;
import PickYourSpot.Model.Movie;
import PickYourSpot.Model.Reservation;
import PickYourSpot.services.MovieService;
import PickYourSpot.services.ReservationService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.util.Objects;

public class MyReservationController {

    @FXML
    private TableView<Reservation> reservationTable;
    @FXML
    private TableColumn<Reservation, String> titluColumn;
    @FXML
    private TableColumn<Reservation, Integer> resColumn;

    @FXML
    private Label movieLabel;
    @FXML
    private Label reservationLabel;
    @FXML
    private Label weekdayLabel;
    @FXML
    private Label timeLabel;
    @FXML
    private Label seatsLabel;
    @FXML
    private Label statusLabel;

    private static boolean sw=true;

    @FXML
    public void initialize(){

        titluColumn.setCellValueFactory(cellData -> cellData.getValue().movieTitlepProperty());
        resColumn.setCellValueFactory(cellData -> cellData.getValue().reservationProperty().asObject());
        if(sw){
            ReservationService.populate(RegistrationController.getUsername());
            sw=false;
        }
        reservationTable.setItems(ReservationService.getReservationData());
        showReservationDetails(null);
        reservationTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) ->{
                    showReservationDetails(newValue);
                });
    }

    private void showReservationDetails(Reservation reservation){
        if (reservation != null) {
            // Fill the labels with info from the movie object.
            movieLabel.setText(reservation.getMovieTitle());
            reservationLabel.setText(String.valueOf(reservation.getRes_no()));
            weekdayLabel.setText(reservation.getWeekDay());
            timeLabel.setText(reservation.getTime());
            seatsLabel.setText(reservation.getSeats());
            statusLabel.setText(reservation.getStatus());

        } else {
            // movie is null, remove all the text.
            movieLabel.setText("");
            reservationLabel.setText("");
            weekdayLabel.setText("");
            timeLabel.setText("");
            seatsLabel.setText("");
            statusLabel.setText("");
        }
    }
    public void backButtonClicked() throws IOException{
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("SeeMovieList.fxml")));
        Main.getWindow().setScene(new Scene(root, 600, 400));
    }



}
