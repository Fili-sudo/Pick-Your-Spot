package PickYourSpot.Controllers;

import PickYourSpot.Main;
import PickYourSpot.Model.Locuri;
import PickYourSpot.Model.Movie;
import PickYourSpot.Model.Reservation;
import PickYourSpot.services.LocuriService;
import PickYourSpot.services.ReservationService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import org.dizitart.no2.Document;


import java.io.IOException;
import java.util.Objects;


public class ConfirmController {

    @FXML
    private Text res_no;
    @FXML
    private Text movie;
    @FXML
    private Text day;
    @FXML
    private Text seats;
    @FXML
    private Text time;

    private static int res = 1000000;

    @FXML
    public void initialize(){
        int []row;
        int []column;
        String ora;
        String minut;
        String locuri = "";
        row = TheatreController.getRow();
        column = TheatreController.getColumn();

        res = (int)Math.floor(Math.random()*(9999999-1000000+1)+1000000);

        res_no.setText(String.valueOf(res));
        movie.setText(SeeMovieListController.getMovie().getTitlu());
        day.setText(SeeMovieListController.getMovie().getTimetable().get(TimetableController.getJ()).getWeek_day());
        ora = String.valueOf(SeeMovieListController.getMovie().getTimetable().get(TimetableController.getJ()).getProgram().get(TimetableController.getI()-1).getOra());
        minut = String.valueOf(SeeMovieListController.getMovie().getTimetable().get(TimetableController.getJ()).getProgram().get(TimetableController.getI()-1).getMinut());
        time.setText(ora + ":" + minut);

        for (int i= row.length-1; i>= 0;i--){
            if(i==0){
                locuri = locuri + seatDeduction(row[i], column[i]);
            }
            else{
                locuri = locuri + seatDeduction(row[i], column[i]) + ", " ;
            }

        }

        seats.setText(locuri);

    }

    private int seatDeduction(int row, int column){
        int seat;
        seat = (row*8)+column+1;
        if(row <= 2){
            seat = seat - 2*row;
            if(column >= 5){
                seat = seat - 2;
            }
        }
            else{
                seat = seat -6;
        }
        return seat;
    }

    public void cancelButtonClicked() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("SeeMovieList.fxml")));
        Main.getWindow().setScene(new Scene(root, 600, 400));
    }

    public void confirmButtonClicked() throws IOException{
        int []row;
        int []column;
        int [][] a = SeeMovieListController.getMovie().getTimetable()
                .get(TimetableController.getJ()).getProgram().get(TimetableController.getI()-1).getSala();
        row = TheatreController.getRow();
        column = TheatreController.getColumn();
        for (int i= row.length-1; i>= 0;i--){
            a[row[i]][column[i]]=1;
        }
        SeeMovieListController.getMovie().getTimetable()
                .get(TimetableController.getJ()).getProgram().get(TimetableController.getI()-1).setSala(a);
        Reservation reservation = new Reservation(RegistrationController.getUsername(),SeeMovieListController.getMovie().getTitlu(),
                seats.getText(),day.getText(),time.getText(),"pending",TheatreController.getRow(),
                TheatreController.getColumn(),res);
        ReservationService.addReservation(reservation);
        Locuri locuri = new Locuri(SeeMovieListController.getMovie().getTitlu(),SeeMovieListController.getMovie().getTimetable().get(TimetableController.getJ()).getWeek_day()
                , SeeMovieListController.getMovie().getTimetable().get(TimetableController.getJ()).getProgram().get(TimetableController.getI()-1).getOra()
                ,SeeMovieListController.getMovie().getTimetable().get(TimetableController.getJ()).getProgram().get(TimetableController.getI()-1).getMinut()
                ,SeeMovieListController.getMovie().getTimetable().get(TimetableController.getJ()).getProgram().get(TimetableController.getI()-1).getSala());
        LocuriService.addLocuri(locuri, SeeMovieListController.getMovie());

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("SeeMovieList.fxml")));
        Main.getWindow().setScene(new Scene(root, 600, 400));
    }
}
