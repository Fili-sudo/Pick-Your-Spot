package PickYourSpot.Controllers;

import PickYourSpot.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import PickYourSpot.exceptions.UsernameAlreadyExistsException;
import PickYourSpot.services.UserService;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class RegistrationController {


    @FXML
    private Text registrationMessage;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField usernameField;
    @FXML
    private ChoiceBox role;

    private static Stage window;
    private static String which="User";

    public static void setWhich(String which) {
        RegistrationController.which = which;
    }
    public static String getWhich() {
        return which;
    }

    public static Stage getWindow() {
        return window;
    }

    @FXML
    public void initialize() throws IOException{
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("RolePopUp.fxml")));
        Scene scene = new Scene(root);
        role.getItems().addAll("User", "Cinema");
        role.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) ->
        {
            if((newValue!=oldValue&&newValue=="Cinema")) {
                window = new Stage();
                window.initModality(Modality.APPLICATION_MODAL);
                window.setScene(scene);
                window.showAndWait();
            }
        });
    }

    @FXML
    public void handleRegisterAction() {
        String role_string = (String) role.getValue();
        if(role_string=="Cinema"){
            role_string=which;
        }
        if (role.getValue()=="Cinema") {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Only Users can register new accounts.\n Please select the User role or login to your Cinema account ");
            a.show();
        }
        else {
            try {
                UserService.addUser(usernameField.getText(), passwordField.getText(), role_string);
                registrationMessage.setText("Account created successfully!");
            } catch (UsernameAlreadyExistsException e) {
                registrationMessage.setText(e.getMessage());
            }
        }
    }

    @FXML
    public void backButtonClicked() {
        Main.getWindow().setScene(Main.getScene());
    }

    @FXML
    public void loginButtonClicked() throws IOException {
        String role_string = (String) role.getValue();
        if(role_string=="Cinema"){
            role_string=which;
        }
            else{
                which = "User";
        }
        if(!UserService.verifyCredentials(usernameField.getText(), passwordField.getText(), role_string)){
            registrationMessage.setText("Incorrect username or password.\n Please reenter your credentials or select the correct role");
        }
            else if (role_string=="User"|| role_string=="Admin"){
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("SeeMovieList.fxml")));
            Main.getWindow().setScene(new Scene(root, 600, 400));
        }
            else{
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("SeeMadeReservations.fxml")));
            Main.getWindow().setScene(new Scene(root, 600, 400));
        }

    }
}
