package PickYourSpot.Controllers;

import PickYourSpot.Main;
import PickYourSpot.Model.Movie;
import PickYourSpot.services.LocuriService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.Objects;

public class TheatreController {
    @FXML
    private GridPane grid;
    @FXML
    private ChoiceBox<Integer> seats;
    @FXML
    private Text nextMessage;

    private static int [] row;
    private static int [] column;

    public static int[] getRow() {
        return row;
    }

    public static int[] getColumn() {
        return column;
    }

    @FXML
    public void initialize(){
        seats.getItems().addAll(1,2,3,4,5);
        Movie movie = SeeMovieListController.getMovie();
        LocuriService.findByMovie(movie);
        int [][] a = movie.getTimetable().get(TimetableController.getJ()).getProgram().get(TimetableController.getI()-1).getSala();
        for (int i=0;i<5;i++){
            for (int j=0;j<8;j++){
                if(a[i][j]==1){
                    ToggleButton is = (ToggleButton)getNodeFromGridPane(grid, j, i);
                    if(is!=null){
                        is.setDisable(true);
                        is.setStyle("-fx-background-color:#FF0000");
                    }
                }
                else{
                    ToggleButton is = (ToggleButton)getNodeFromGridPane(grid, j, i);
                    if(is!=null){
                        is.setStyle("-fx-background-color:#228B22");
                    }
                }

            }
        }

    }

    private Node getNodeFromGridPane(GridPane gridPane, int col, int row) {
        for (Node node : gridPane.getChildren())
            if (GridPane.getColumnIndex(node) != null
                    && GridPane.getRowIndex(node) != null
                    && GridPane.getRowIndex(node) == row
                    && GridPane.getColumnIndex(node) == col)
                return node;
        return null;
    }

    private int howMany(){
        int cnt=0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 8; j++) {
                ToggleButton is = (ToggleButton) getNodeFromGridPane(grid, j, i);
                if (is != null) {
                    if(!is.isDisabled()&&is.isSelected()){
                        cnt++;
                    }
                }
            }
        }
       return cnt;
    }

    public void selectSeat() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 8; j++) {
                ToggleButton is = (ToggleButton) getNodeFromGridPane(grid, j, i);
                if (is != null) {
                    if(!is.isDisabled()&&is.isSelected()){
                        is.setStyle("-fx-background-color:#808080");
                    }
                    if(!is.isDisabled()&&!is.isSelected()){
                        is.setStyle("-fx-background-color:#228B22");
                    }
                }
            }
        }
    }

    public void nextMessageButtonClicked() throws IOException {
        int k;
        k = howMany();
        if(seats.getValue()!=howMany()){
            nextMessage.setText("Please select as many seats as you have chosen");
        }
        else{
            row = new int[k];
            column = new int[k];
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 8; j++) {
                    ToggleButton is = (ToggleButton) getNodeFromGridPane(grid, j, i);
                    if (is != null) {
                        if(!is.isDisabled()&&is.isSelected()){
                            row[k-1]=i;
                            column[k-1]=j;
                            k--;
                        }
                    }
                }
            }
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("Confirm.fxml")));
            Main.getWindow().setScene(new Scene(root, 600, 400));
        }
    }

    public void backButtonClicked() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("SeeMovieList.fxml")));
        Main.getWindow().setScene(new Scene(root, 600, 400));
    }
}
