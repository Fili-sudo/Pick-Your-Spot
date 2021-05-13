package PickYourSpot.Controllers;

import PickYourSpot.Main;
import PickYourSpot.Model.Movie;
import PickYourSpot.services.MovieService;
import PickYourSpot.services.ReservationService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

public class EditMovieController {

    @FXML
    private TextField TitluField;
    @FXML
    private TextField DirectorField;
    @FXML
    private TextField AnAparitieField;
    @FXML
    private TextField GenField;
    @FXML
    private TextField DurataField;
    @FXML
    private TextField PeopleField;
    @FXML
    private TextField RatingField;

    public void initialize(){
        if(!ReservationService.isEmpty() && TitluField.getText() != SeeMovieListController.getMovie().getTitlu()){
            TitluField.setDisable(true);
        }
        TitluField.setText(SeeMovieListController.getMovie().getTitlu());
        DirectorField.setText(SeeMovieListController.getMovie().getDirector());
        AnAparitieField.setText(String.valueOf(SeeMovieListController.getMovie().getAn_aparitie()));
        GenField.setText(SeeMovieListController.getMovie().getGen());
        DurataField.setText(String.valueOf(SeeMovieListController.getMovie().getDurata()));
        PeopleField.setText(SeeMovieListController.getMovie().getPeople());
        RatingField.setText(String.valueOf(SeeMovieListController.getMovie().getRating()));
    }

    public void okButtonClicked() throws IOException {
            try {
                int AnAparitie = Integer.parseInt(AnAparitieField.getText());
                double Rating = Double.parseDouble(RatingField.getText());
                int Durata = Integer.parseInt(DurataField.getText());
                Movie movie = new Movie(TitluField.getText(), AnAparitie
                        , DirectorField.getText(), PeopleField.getText(), Rating
                        , GenField.getText(), Durata);
                MovieService.findAndUpdate(SeeMovieListController.getMovie(), movie);
                MovieService.emptyMovieData();
                SeeMovieListController.setSw(true);
                SeeMovieListController.getWindow().close();
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("SeeMovieList.fxml")));
                Main.getWindow().setScene(new Scene(root, 600, 400));
            } catch (NumberFormatException nue) {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setContentText("Please enter numbers for An aparitie, Rating and Durata fields");
                a.show();
            }
    }

    public void cancelButtonClicked(){
        SeeMovieListController.getWindow().close();
    }
}