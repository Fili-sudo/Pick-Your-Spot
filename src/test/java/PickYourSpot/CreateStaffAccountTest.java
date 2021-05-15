package PickYourSpot;

import PickYourSpot.exceptions.UsernameAlreadyExistsException;
import PickYourSpot.services.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.assertions.api.Assertions;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.io.IOException;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(ApplicationExtension.class)
class CreateStaffAccountTest {

    private static Scene scene;
    private static Stage window;

    public static Stage getWindow() {
        return window;
    }
    public static Scene getScene() {
        return scene;
    }

    @BeforeAll
    static void beforeAll() {
        FileSystemService.APPLICATION_FOLDER = ".test";
        UserService.initDatabase();
        ReservationService.initDatabase();
        MovieService.initDatabase();
        LocuriService.initDatabase();
    }

    @BeforeEach
    void setUp(){
        UserService.empty();
        try {
            UserService.addUser("admin", "admin", "Admin");

        } catch (UsernameAlreadyExistsException e) { }
    }

    @Start
    void start(Stage primaryStage) throws IOException {

        window = primaryStage;
        Main.setWindow(window);
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("firstpage.fxml")));
        primaryStage.setTitle("Pick Your Spot");
        scene = new Scene(root, 600, 400);
        Main.setScene(scene);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Test
    void testCreateStaffAccount(FxRobot robot) {

        //admin login
        robot.clickOn("#login");
        robot.clickOn("#username");
        robot.write("admin");
        robot.clickOn("#password");
        robot.write("admin");
        robot.clickOn("#role").clickOn("Cinema");
        robot.clickOn("#adminButton");
        robot.clickOn("#loginButton");
        robot.clickOn("#create");

        //account already exists
        robot.clickOn("#usernameNewStaff");
        robot.write("admin");
        robot.clickOn("#passwordNewStaff");
        robot.write("parolastaff");
        robot.clickOn("#createStaff");
        Assertions.assertThat(robot.lookup("#createMessage").queryText()).hasText("An account with the username admin already exists!");


        //create a new staff account
        robot.clickOn("#usernameNewStaff");
        robot.eraseText(5);
        robot.write("staff1");
        robot.clickOn("#passwordNewStaff");
        robot.write("1");
        robot.clickOn("#createStaff");
        Assertions.assertThat(robot.lookup("#createMessage").queryText()).hasText("Account created successfully!");

    }
}