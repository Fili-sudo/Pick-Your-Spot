package PickYourSpot.Controllers;

import PickYourSpot.Main;
import PickYourSpot.Model.Movie;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.Objects;

public class TimetableController {
    @FXML
    private Hyperlink h01;
    @FXML
    private Hyperlink h02;
    @FXML
    private Hyperlink h03;
    @FXML
    private Hyperlink h11;
    @FXML
    private Hyperlink h12;
    @FXML
    private Hyperlink h13;
    @FXML
    private Hyperlink h21;
    @FXML
    private Hyperlink h22;
    @FXML
    private Hyperlink h23;
    @FXML
    private Hyperlink h31;
    @FXML
    private Hyperlink h32;
    @FXML
    private Hyperlink h33;
    @FXML
    private Hyperlink h41;
    @FXML
    private Hyperlink h42;
    @FXML
    private Hyperlink h43;

    private static int i,j;

    public static int getI() {
        return i;
    }

    public static int getJ() {
        return j;
    }

    public static void setI(int i) {
        TimetableController.i = i;
    }

    public static void setJ(int j) {
        TimetableController.j = j;
    }

    @FXML
    public void initialize(){
        Movie movie = SeeMovieListController.getMovie();
        h01.setText(""+movie.getTimetable().get(0).getProgram().get(0).getOra()+":"+movie.getTimetable().get(0).getProgram().get(0).getMinut());
        h11.setText(""+movie.getTimetable().get(1).getProgram().get(0).getOra()+":"+movie.getTimetable().get(1).getProgram().get(0).getMinut());
        h21.setText(""+movie.getTimetable().get(2).getProgram().get(0).getOra()+":"+movie.getTimetable().get(2).getProgram().get(0).getMinut());
        h31.setText(""+movie.getTimetable().get(3).getProgram().get(0).getOra()+":"+movie.getTimetable().get(3).getProgram().get(0).getMinut());
        h41.setText(""+movie.getTimetable().get(4).getProgram().get(0).getOra()+":"+movie.getTimetable().get(4).getProgram().get(0).getMinut());

        h02.setText(""+movie.getTimetable().get(0).getProgram().get(1).getOra()+":"+movie.getTimetable().get(0).getProgram().get(1).getMinut());
        h12.setText(""+movie.getTimetable().get(1).getProgram().get(1).getOra()+":"+movie.getTimetable().get(1).getProgram().get(1).getMinut());
        h22.setText(""+movie.getTimetable().get(2).getProgram().get(1).getOra()+":"+movie.getTimetable().get(2).getProgram().get(1).getMinut());
        h32.setText(""+movie.getTimetable().get(3).getProgram().get(1).getOra()+":"+movie.getTimetable().get(3).getProgram().get(1).getMinut());
        h42.setText(""+movie.getTimetable().get(4).getProgram().get(1).getOra()+":"+movie.getTimetable().get(4).getProgram().get(1).getMinut());

        h03.setText(""+movie.getTimetable().get(0).getProgram().get(2).getOra()+":"+movie.getTimetable().get(0).getProgram().get(2).getMinut());
        h13.setText(""+movie.getTimetable().get(1).getProgram().get(2).getOra()+":"+movie.getTimetable().get(1).getProgram().get(2).getMinut());
        h23.setText(""+movie.getTimetable().get(2).getProgram().get(2).getOra()+":"+movie.getTimetable().get(2).getProgram().get(2).getMinut());
        h33.setText(""+movie.getTimetable().get(3).getProgram().get(2).getOra()+":"+movie.getTimetable().get(3).getProgram().get(2).getMinut());
        h43.setText(""+movie.getTimetable().get(4).getProgram().get(2).getOra()+":"+movie.getTimetable().get(4).getProgram().get(2).getMinut());
    }

    public void backButtonClicked() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("SeeMovieList.fxml")));
        Main.getWindow().setScene(new Scene(root, 600, 400));
    }

    public void h01Clicked() throws IOException {
        i=1;
        j=0;
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("Theatre.fxml")));
        Main.getWindow().setScene(new Scene(root, 600, 400));
    }
    public void h02Clicked() throws IOException {
        i=2;
        j=0;
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("Theatre.fxml")));
        Main.getWindow().setScene(new Scene(root, 600, 400));
    }
    public void h03Clicked() throws IOException {
        i=3;
        j=0;
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("Theatre.fxml")));
        Main.getWindow().setScene(new Scene(root, 600, 400));
    }
    public void h11Clicked() throws IOException {
        i=1;
        j=1;
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("Theatre.fxml")));
        Main.getWindow().setScene(new Scene(root, 600, 400));
    }
    public void h12Clicked() throws IOException {
        i=2;
        j=1;
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("Theatre.fxml")));
        Main.getWindow().setScene(new Scene(root, 600, 400));
    }
    public void h13Clicked() throws IOException {
        i=3;
        j=1;
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("Theatre.fxml")));
        Main.getWindow().setScene(new Scene(root, 600, 400));
    }
    public void h21Clicked() throws IOException {
        i=1;
        j=2;
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("Theatre.fxml")));
        Main.getWindow().setScene(new Scene(root, 600, 400));
    }
    public void h22Clicked() throws IOException {
        i=2;
        j=2;
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("Theatre.fxml")));
        Main.getWindow().setScene(new Scene(root, 600, 400));
    }
    public void h23Clicked() throws IOException {
        i=3;
        j=2;
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("Theatre.fxml")));
        Main.getWindow().setScene(new Scene(root, 600, 400));
    }
    public void h31Clicked() throws IOException {
        i=1;
        j=3;
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("Theatre.fxml")));
        Main.getWindow().setScene(new Scene(root, 600, 400));
    }
    public void h32Clicked() throws IOException {
        i=2;
        j=3;
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("Theatre.fxml")));
        Main.getWindow().setScene(new Scene(root, 600, 400));
    }
    public void h33Clicked() throws IOException {
        i=3;
        j=3;
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("Theatre.fxml")));
        Main.getWindow().setScene(new Scene(root, 600, 400));
    }
    public void h41Clicked() throws IOException {
        i=1;
        j=4;
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("Theatre.fxml")));
        Main.getWindow().setScene(new Scene(root, 600, 400));
    }
    public void h42Clicked() throws IOException {
        i=2;
        j=4;
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("Theatre.fxml")));
        Main.getWindow().setScene(new Scene(root, 600, 400));
    }
    public void h43Clicked() throws IOException {
        i=3;
        j=4;
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("Theatre.fxml")));
        Main.getWindow().setScene(new Scene(root, 600, 400));
    }


}
