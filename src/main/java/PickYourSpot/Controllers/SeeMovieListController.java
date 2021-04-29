package PickYourSpot.Controllers;

import PickYourSpot.Main;
import javafx.application.Platform;
import java.io.IOException;
import java.util.Objects;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
    }




}
