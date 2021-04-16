package PickYourSpot.Controllers;

import PickYourSpot.Main;
import javafx.application.Platform;
import java.io.IOException;
import java.util.Objects;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class FirstController {
    public void exitButtonClicked(){
        Platform.exit();

    }
    public void loginButtonClicked() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("register.fxml")));
        Main.getWindow().setScene(new Scene(root, 400, 275));

    }
}
