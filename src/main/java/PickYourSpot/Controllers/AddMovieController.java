package PickYourSpot.Controllers;

import PickYourSpot.Main;
import PickYourSpot.Model.Movie;
import PickYourSpot.services.MovieService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.Objects;

public class AddMovieController {

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

    public void okButtonClicked() throws IOException {
        if (TitluField.getText() == "" || DirectorField.getText() == "" || AnAparitieField.getText() == "" ||
                GenField.getText() == "" || DurataField.getText() == "" || PeopleField.getText() == "" ||
                RatingField.getText() == "") {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Please fill all the fields");
            a.show();
        } else {
            try {
                int AnAparitie = Integer.parseInt(AnAparitieField.getText());
                double Rating = Double.parseDouble(RatingField.getText());
                int Durata = Integer.parseInt(DurataField.getText());
                Movie movie = new Movie(TitluField.getText(), Integer.parseInt(AnAparitieField.getText())
                        , DirectorField.getText(), PeopleField.getText(), Double.parseDouble(RatingField.getText())
                        , GenField.getText(), Integer.parseInt(DurataField.getText()));
                MovieService.addMovie(movie);
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
    }

    public void cancelButtonClicked(){
        SeeMovieListController.getWindow().close();
    }
}
