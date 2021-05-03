package PickYourSpot.Controllers;

import PickYourSpot.Main;
import PickYourSpot.exceptions.UsernameAlreadyExistsException;
import PickYourSpot.services.UserService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.Objects;


public class NewStaffAcountController {
    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;
    @FXML
    private Text createMessage;

    public void backButtonClicked() throws IOException{
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("SeeMovieList.fxml")));
        Main.getWindow().setScene(new Scene(root, 600, 400));
    }

    public void handleRegistration(){
        try {
            UserService.addUser(usernameField.getText(), passwordField.getText(), "Staff");
            createMessage.setText("Account created successfully!");
        } catch (UsernameAlreadyExistsException e) {
            createMessage.setText(e.getMessage());
        }
    }
}
