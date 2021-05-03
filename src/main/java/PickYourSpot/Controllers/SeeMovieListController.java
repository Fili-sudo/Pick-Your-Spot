package PickYourSpot.Controllers;

import PickYourSpot.Main;
import PickYourSpot.Model.Movie;
import PickYourSpot.services.MovieService;
import javafx.application.Platform;
import java.io.IOException;
import java.util.Objects;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;

public class SeeMovieListController {
    @FXML
    private Button pick;
    @FXML
    private Button see_res;
    @FXML
    private Button nnew;
    @FXML
    private Button edit;
    @FXML
    private Button delete;
    @FXML
    private Button create;

    @FXML
    private TableView<Movie> movieTable;
    @FXML
    private TableColumn<Movie, String> TitluColumn;

    @FXML
    private Label TitluLabel;
    @FXML
    private Label DirectorLabel;
    @FXML
    private Label an_aparitieLabel;
    @FXML
    private Label GenLabel;
    @FXML
    private Label DurataLabel;
    @FXML
    private Label PeopleLabel;
    @FXML
    private Label RatingLabel;

    private static boolean sw=true;
    private static Movie movie;

    public static Movie getMovie() {
        return movie;
    }


    @FXML
    public void initialize(){
        if(RegistrationController.getWhich()=="User"){
            nnew.setVisible(false);
            edit.setVisible(false);
            delete.setVisible(false);
            create.setVisible(false);
            pick.setVisible(true);
            see_res.setVisible(true);
        }
            else{
            nnew.setVisible(true);
            edit.setVisible(true);
            delete.setVisible(true);
            create.setVisible(true);
            pick.setVisible(false);
            see_res.setVisible(false);
        }
        TitluColumn.setCellValueFactory(cellData -> cellData.getValue().titlupProperty());
        if(sw){
            MovieService.exemplu(); // de sters cand cipri face butoanele
            sw=false;
        }
        movieTable.setItems(MovieService.getMovieData());
        showMovieDetails(null);
        movieTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) ->{
                    showMovieDetails(newValue);
                    movie = newValue;
                });
    }

    private void showMovieDetails(Movie movie){
        if (movie != null) {
            // Fill the labels with info from the person object.
            TitluLabel.setText(movie.getTitlu());
            DirectorLabel.setText(movie.getDirector());
            an_aparitieLabel.setText(String.valueOf(movie.getAn_aparitie()));
            GenLabel.setText(movie.getGen());
            DurataLabel.setText(String.valueOf(movie.getDurata()));
            PeopleLabel.setText(movie.getPeople());
            RatingLabel.setText(Double.toString(movie.getRating()));

        } else {
            // Person is null, remove all the text.
            TitluLabel.setText("");
            DirectorLabel.setText("");
            an_aparitieLabel.setText("");
            GenLabel.setText("");
            DurataLabel.setText("");
            PeopleLabel.setText("");
            RatingLabel.setText("");
        }
    }

    public void PickAMovieButtonClicked() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("Timetable.fxml")));
        Main.getWindow().setScene(new Scene(root, 600, 400));
    }

    public void createButtonClicked() throws IOException{
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("NewStaffAccount.fxml")));
        Main.getWindow().setScene(new Scene(root, 450, 300));
    }

}
