package PickYourSpot.Controllers;

import PickYourSpot.Main;
import PickYourSpot.Model.Movie;
import PickYourSpot.Model.Reservation;
import PickYourSpot.services.LocuriService;
import PickYourSpot.services.MovieService;
import PickYourSpot.services.ReservationService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

public class SeeMadeReservationsController {

    @FXML
    private TableView<Reservation> reservationTable;
    @FXML
    private TableColumn<Reservation, String> titluColumn;
    @FXML
    private TableColumn<Reservation, Integer> resColumn;

    @FXML
    private Label userLabel;
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

    private static Reservation reservation ;
    private static boolean sw=true;

    @FXML
    public void initialize(){

        titluColumn.setCellValueFactory(cellData -> cellData.getValue().movieTitlepProperty());
        resColumn.setCellValueFactory(cellData -> cellData.getValue().reservationProperty().asObject());
        if(sw){
            ReservationService.populate();
            sw=false;
        }
        reservationTable.setItems(ReservationService.getReservationData());
        showReservationDetails(null);
        reservationTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) ->{
                    showReservationDetails(newValue);
                    reservation = newValue;
                });
    }

    private void showReservationDetails(Reservation reservation){
        if (reservation != null) {

            movieLabel.setText(reservation.getMovieTitle());
            reservationLabel.setText(String.valueOf(reservation.getRes_no()));
            weekdayLabel.setText(reservation.getWeekDay());
            timeLabel.setText(reservation.getTime());
            seatsLabel.setText(reservation.getSeats());
            statusLabel.setText(reservation.getStatus());
            userLabel.setText(reservation.getUser());

        } else {

            userLabel.setText("");
            movieLabel.setText("");
            reservationLabel.setText("");
            weekdayLabel.setText("");
            timeLabel.setText("");
            seatsLabel.setText("");
            statusLabel.setText("");
        }
    }
    public void anulleReservationButtonClicked(){
        if(reservation.getStatus().equals("canceled")){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("This reservation has been already canceled");
            a.show();
        }
        else {
            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setContentText("Are you sure you want to cancel this reservation?");
            Optional<ButtonType> result = a.showAndWait();
            if (result.get() == ButtonType.OK ) {
                LocuriService.freeSeats(reservation);
                ReservationService.find(reservation);
                ReservationService.populate();
                initialize();
            }
        }
    }

    public void acceptReservationButtonClicked(){
        if(reservation.getStatus().equals("accepted")){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("This reservation has been already accepted");
            a.show();
        }
        else {
            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setContentText("Are you sure you want to accept this reservation?");
            Optional<ButtonType> result = a.showAndWait();
            if (result.get() == ButtonType.OK ) {
                ReservationService.findAndAccept(reservation);
                ReservationService.populate();
                initialize();
            }
        }
    }


}
